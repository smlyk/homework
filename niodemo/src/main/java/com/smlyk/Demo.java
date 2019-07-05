package com.smlyk;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @author yekai
 */
public class Demo {

    public void demo() throws IOException {

        ByteBuffer buffer = ByteBuffer.allocate(1024);
        Selector selector = Selector.open();

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        //非阻塞
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.bind(new InetSocketAddress(8080));
        //注册监听事件
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (true){
            //取得所有key的集合
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            //set集合迭代出key
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()){
                SelectionKey key = iterator.next();
                if ((key.readyOps() & SelectionKey.OP_ACCEPT) == SelectionKey.OP_ACCEPT){
                    ServerSocketChannel serverChannel = (ServerSocketChannel) key.channel();
                    SocketChannel socketChannel = serverChannel.accept();
                    socketChannel.configureBlocking(false);

                    SelectionKey newKey = socketChannel.register(selector, SelectionKey.OP_READ);
                    iterator.remove();
                }else if ((key.readyOps()&SelectionKey.OP_READ) == SelectionKey.OP_READ){

                    SocketChannel socketChannel = (SocketChannel) key.channel();
                    int bytesEchoed = 0;
                    while (true){
                        buffer.clear();
                        int n = socketChannel.read(buffer);
                        if (n <= 0) break;
                        //反转缓冲区，这时操作系统就可以正确的读取字节发送出去
                        buffer.flip();
                    }
                    iterator.remove();
                }


            }


        }




    }



}
