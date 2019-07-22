package com.smlyk;

import com.smlyk.registry.IRegistryCenter;
import com.smlyk.registry.RegistryCenterWithZk;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.util.StringUtils;

import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author yekai
 */
public class YKRpcServer implements ApplicationContextAware, InitializingBean{

    ExecutorService executorService = new ThreadPoolExecutor(0, Integer.MAX_VALUE,
            60L, TimeUnit.SECONDS,
            new SynchronousQueue<Runnable>(), new ThreadFactory() {
        private AtomicInteger atomicInteger = new AtomicInteger(0);
        @Override
        public Thread newThread(Runnable r) {
            int c = atomicInteger.incrementAndGet();
            Thread t = new Thread(r,"自定义线程名称yk_" + c);
            System.out.println("生成线程: " + t.getName());
            return t;
        }
    });

    private Map<String, Object> handlerMap = new HashMap<>();

    private IRegistryCenter registryCenter = new RegistryCenterWithZk();

    private int port;

    public YKRpcServer(int port) {
        this.port = port;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

        //从容器中获取注解为RpcService的bean集合
        Map<String, Object> serviceBeanMap = applicationContext.getBeansWithAnnotation(RpcService.class);

        if (!serviceBeanMap.isEmpty()){

            serviceBeanMap.forEach((k, serviceBean) -> {

                //拿到注解
                RpcService rpcService = serviceBean.getClass().getAnnotation(RpcService.class);
                //得到注解中的属性
                //服务接口
                String serviceName = rpcService.value().getName();
                //版本号
                String version = rpcService.version();

                if (!StringUtils.isEmpty(version)){
                    serviceName += "_" + version;
                }
                handlerMap.put(serviceName, serviceBean);
                registryCenter.registry(serviceName, getAddress() + ":" + port);
            });
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {

        ServerSocket serverSocket = null;

        try {
            serverSocket = new ServerSocket(port);
            while (true){
                Socket socket = serverSocket.accept();
                //每个socket交给一个ProcessorHandler去处理
                executorService.execute(new ProcessorHandler(socket, handlerMap));
            }
        } finally {
            if (null != serverSocket){
                serverSocket.close();
            }
        }
    }


    private static String getAddress(){
        InetAddress inetAddress=null;
        try {
            inetAddress=InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return inetAddress.getHostAddress();// 获得本机的ip地址
    }
}
