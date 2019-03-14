package com.smlyk.dynamicproxy.ykproxy;

import javax.tools.JavaCompiler;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * 用来生成源代码的工具
 * @author yekai
 */
public class YKProxy {

    protected static final String LN = "\r\n";

    public static Object newProxyInstance(YKClassLoader ykClassLoader, Class<?>[] interfaces, YKInvocationHandler h){

        //1.动态生成源码.java文件
        String src = generateSrc(interfaces);
        System.out.println(src);

        //2.Java文件输出到磁盘
        String filePath = YKProxy.class.getResource("").getPath();
        File file = new File(filePath + "$Proxy0.java");
        try (FileWriter fw = new FileWriter(file)) {
            fw.write(src);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //3.把生成.java文件编译成.class文件
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager manage = compiler.getStandardFileManager(null,null,null);
        Iterable iterable = manage.getJavaFileObjects(file);

        JavaCompiler.CompilationTask task = compiler.getTask(null,manage,null,null,null,iterable);
        task.call();
        try {
            manage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //4.编译生成的.class文件加载到JVM中来
        try {
            Class<?> proxyClass = ykClassLoader.findClass("$Proxy0");
            Constructor<?> c = proxyClass.getConstructor(YKInvocationHandler.class);
            file.delete();

            //5.返回字节码重组以后的新的代理对象
            return  c.newInstance(h);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        return null;
    }

    private static String generateSrc(Class<?>[] interfaces) {

        StringBuffer sb = new StringBuffer();
        sb.append("package com.smlyk.dynamicproxy.ykproxy;" + LN);
        sb.append("import java.lang.reflect.*;" + LN);
        sb.append("public class $Proxy0 implements " + interfaces[0].getName() + "{" + LN);
        sb.append("YKInvocationHandler h;" + LN);
        sb.append("public $Proxy0(YKInvocationHandler h) { " + LN);
        sb.append("this.h = h;");
        sb.append("}" + LN);
        for (Method m : interfaces[0].getMethods()){
            Class<?>[] params = m.getParameterTypes();

            StringBuffer paramNames = new StringBuffer();
            StringBuffer paramValues = new StringBuffer();
            StringBuffer paramClasses = new StringBuffer();

            for (int i = 0; i < params.length; i++) {
                Class clazz = params[i];
                String type = clazz.getName();
                String paramName = toLowerFirstCase(clazz.getSimpleName());
                paramNames.append(type + " " +  paramName);
                paramValues.append(paramName);
                paramClasses.append(clazz.getName() + ".class");
                if(i > 0 && i < params.length-1){
                    paramNames.append(",");
                    paramClasses.append(",");
                    paramValues.append(",");
                }
            }
            sb.append("@Override" + LN);
            sb.append("public " + m.getReturnType().getName() + " " + m.getName() + "(" + paramNames.toString() + ") {" + LN);
            sb.append("try{" + LN);
            sb.append("Method m = " + interfaces[0].getName() + ".class.getMethod(\"" + m.getName() + "\",new Class[]{" + paramClasses.toString() + "});" + LN);
            sb.append((hasReturn(m.getReturnType()) ? "return " : "") + getCaseCode("this.h.invoke(this,m,new Object[]{" + paramValues + "})",m.getReturnType()) + ";" + LN);
            sb.append("}catch(Error _ex) { }");
            sb.append("catch(Throwable e){" + LN);
            sb.append("throw new UndeclaredThrowableException(e);" + LN);
            sb.append("}");
            sb.append(getReturnEmptyCode(m.getReturnType()));
            sb.append("}");
        }
        sb.append("}" + LN);
        return sb.toString();

    }

    private static Map<Class,Class> mappings = new HashMap<Class, Class>();
    static {
        mappings.put(int.class,Integer.class);
    }

    private static String toLowerFirstCase(String src){
        char [] chars = src.toCharArray();
        chars[0] += 32;
        return String.valueOf(chars);
    }


    private static boolean hasReturn(Class<?> clazz){
        return clazz != void.class;
    }

    private static String getCaseCode(String code,Class<?> returnClass){
        if(mappings.containsKey(returnClass)){
            return "((" + mappings.get(returnClass).getName() +  ")" + code + ")." + returnClass.getSimpleName() + "Value()";
        }
        return code;
    }

    private static String getReturnEmptyCode(Class<?> returnClass){
        if(mappings.containsKey(returnClass)){
            return "return 0;";
        }else if(returnClass == void.class){
            return "";
        }else {
            return "return null;";
        }
    }

}
