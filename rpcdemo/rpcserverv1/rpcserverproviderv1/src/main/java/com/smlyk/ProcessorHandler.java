package com.smlyk;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;

/**
 * @author yekai
 */
public class ProcessorHandler implements Runnable{

    private Socket socket;

    private Object service;

    public ProcessorHandler(Socket socket, Object service) {
        this.socket = socket;
        this.service = service;
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

        //拿到客户端的请求参数
        Object[] args = request.getParameters();
        //获得每个参数的类型
        Class<?>[] types = new Class[args.length];
        for (int i = 0; i< args.length; i++){
            types[i] =args[i].getClass();
        }
        //加载请求的类
        Class<?> clazz = Class.forName(request.getClassName());
        //找到这个类中的方法 sayHello, saveUser
        Method method = clazz.getMethod(request.getMethodName(), types);
        //HelloServiceImpl 进行反射调用
        Object o = method.invoke(service, args);

        return o;
    }
}
