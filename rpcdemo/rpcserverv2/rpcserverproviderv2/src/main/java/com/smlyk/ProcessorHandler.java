package com.smlyk;

import com.smyk.RpcRequest;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;
import java.util.Map;

/**
 * @author yekai
 */
public class ProcessorHandler implements Runnable{

    private Socket socket;

    private Map<String, Object> handlerMap;

    public ProcessorHandler(Socket socket, Map<String, Object> handlerMap) {
        this.socket = socket;
        this.handlerMap = handlerMap;
    }

    @Override
    public void run() {

        ObjectInputStream ois = null;
        ObjectOutputStream oos = null;

        try {
            ois = new ObjectInputStream(socket.getInputStream());

            //输入流中应该有什么东西？
            //请求哪个类，方法名称、参数
            RpcRequest rpcRequest = (RpcRequest) ois.readObject();

            //反射调用本地服务
            Object result = invoke(rpcRequest);

            oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(result);
            oos.flush();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } finally {
            if (null != oos){
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != ois){
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private Object invoke(RpcRequest request) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        //反射调用
        String serviceName = request.getClassName();
        String version = request.getVersion();
        //增加版本号的判断
        if (!StringUtils.isEmpty(version)){
            serviceName += "_" + version;
        }

        //得到服务接口
        Object service = handlerMap.get(serviceName);
        if (null == service){
            throw new RuntimeException("service not found: "+ serviceName);
        }

        //拿到客户端请求的参数
        Object[] args = request.getParameters();
        Method method = null;
        if (null != args){
            //获取每个参数的类型
            Class<?>[] types = new Class[args.length];
            for (int i = 0; i< args.length; i++){
                types[i] = args[i].getClass();
            }
            //加载请求的类
            Class<?> clazz = Class.forName(request.getClassName());
            method = clazz.getMethod(request.getMethodName(), types);
        }else {

            Class<?> clazz = Class.forName(request.getClassName());
            method = clazz.getMethod(request.getMethodName());
        }
        return method.invoke(service, args);
    }


}
