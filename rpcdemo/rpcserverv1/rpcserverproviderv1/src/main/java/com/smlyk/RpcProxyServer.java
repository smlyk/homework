package com.smlyk;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author yekai
 */
public class RpcProxyServer {

  ExecutorService executorService = new ThreadPoolExecutor(0, Integer.MAX_VALUE,
          60L, TimeUnit.SECONDS,
          new SynchronousQueue<Runnable>(), new ThreadFactory() {
      private AtomicInteger atomicInteger = new AtomicInteger(0);
      @Override
      public Thread newThread(Runnable r) {
          int c = atomicInteger.incrementAndGet();
          Thread t = new Thread(r,"自定义线程名称" + c);
          System.out.println("生成线程: " + t.getName());
          return t;
      }
  });


  public void publisher(Object service, int port){

      ServerSocket serverSocket = null;

      try {
          serverSocket = new ServerSocket(port);
          while (true){
              //BIO
              Socket socket = serverSocket.accept();
              //每一个socket 交给一个processorHandler来处理
              executorService.execute(new ProcessorHandler(socket, service));
          }

      } catch (IOException e) {
          e.printStackTrace();
      }finally {
          if(serverSocket!=null) {
              try {
                  serverSocket.close();
              } catch (IOException e) {
                  e.printStackTrace();
              }
          }
      }

  }



}
