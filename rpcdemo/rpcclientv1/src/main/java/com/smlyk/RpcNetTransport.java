package com.smlyk;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * @author yekai
 */
public class RpcNetTransport {

    private String host;

    private int port;

    public RpcNetTransport(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public Object send(RpcRequest request){

        Socket socket = null;
        Object result = null;
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;

        try {
            //建立连接
            socket = new Socket(host, port);

            //输出流
            oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(request);
            oos.flush();

            //输入流
            ois = new ObjectInputStream(socket.getInputStream());
            result = ois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally {

            if (null != ois){
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (null != oos){
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }


}
