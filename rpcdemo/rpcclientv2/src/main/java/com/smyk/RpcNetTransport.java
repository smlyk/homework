package com.smyk;

import com.google.common.base.Splitter;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

/**
 * rpc网络传输
 * @author yekai
 */
public class RpcNetTransport {

    private String serviceAddress;


    public RpcNetTransport(String serviceAddress) {
        this.serviceAddress = serviceAddress;
    }


    public Object send(RpcRequest request){

        Socket socket = null;
        Object result = null;
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;


        try {
            //建立连接
            List<String> addrList = Splitter.on(":")
                    .omitEmptyStrings()
                    .trimResults()
                    .splitToList(serviceAddress);
            socket = new Socket(addrList.get(0), Integer.parseInt(addrList.get(1)));

            //输出流->写
            oos = new ObjectOutputStream(socket.getOutputStream());
            //序列化
            oos.writeObject(request);
            oos.flush();

            //输入流->读
            try {
                ois = new ObjectInputStream(socket.getInputStream());
                result = ois.readObject();
            } catch (EOFException e) {
                System.out.println("----null");
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally {

            try {
                if (null != ois){
                    ois.close();
                }
                if (null != oos){
                    oos.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        return result;
    }
}
