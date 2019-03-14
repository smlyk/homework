package com.smlyk.dynamicproxy.ykproxy;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author yekai
 */
public class YKClassLoader extends ClassLoader{

    private File classPathFile;

    public YKClassLoader(){
        //得到类路径
        String classPath = YKClassLoader.class.getResource("").getPath();
        System.out.println("classPath = " + classPath);
        this.classPathFile = new File(classPath);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {

        String className = YKClassLoader.class.getPackage().getName()+"."+name;
        if (classPathFile != null){
            System.out.println("name = " + name);
            File classFile = new File(classPathFile, name.replaceAll("\\.", "/") + ".class");
            if (classFile.exists()){
                FileInputStream in = null;
                ByteArrayOutputStream out = null;
                try{
                    in = new FileInputStream(classFile);
                    out = new ByteArrayOutputStream();
                    byte [] buff = new byte[1024];
                    int len;
                    while ((len = in.read(buff)) != -1){
                        out.write(buff,0,len);
                    }
                    //将类文件读写成Class文件
                    return defineClass(className,out.toByteArray(),0,out.size());
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    try {
                        out.close();
                        in.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        }

        return null;
    }
}
