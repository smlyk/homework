package com.smlyk.mvcframework.servlet.v1;

import com.alibaba.fastjson.JSON;
import com.smlyk.mvcframework.annotation.YKAutowired;
import com.smlyk.mvcframework.annotation.YKRequestMapping;
import com.smlyk.mvcframework.annotation.YKRestController;
import com.smlyk.mvcframework.annotation.YKService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.*;

/**
 * @author yekai
 */
@Slf4j
public class YKDispatcherServlet extends HttpServlet {

    //保存application.properties配置文件中的内容
    private Properties contextConfig = new Properties();

    //保存扫描的所有的类名
    private List<String> classNames = new ArrayList<>();

    private Map<String,Object> ioc = new HashMap<>();

    //保存url和Method的对应关系
    private Map<String,Method> handlerMapping = new HashMap<>();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            doDispatcher(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void doDispatcher(HttpServletRequest req, HttpServletResponse resp) throws Exception{
        String uri = req.getRequestURI();
        String contextPath = req.getContextPath();
        String url = uri.replaceAll(contextPath,"").replaceAll("/+","/");

        if(!this.handlerMapping.containsKey(url)){
            resp.getWriter().write("404 Not Found!!!");
            return;
        }

        Method method = this.handlerMapping.get(url);
        String beanName  = toLowerFirstCase(method.getDeclaringClass().getSimpleName());

        Map<String,String[]> params = req.getParameterMap();
        //获取方法的形参列表
        Class<?> [] parameterTypes = method.getParameterTypes();

        Object o = method.invoke(ioc.get(beanName), new Object[]{params.get("name")[0]});
        resp.getWriter().write(JSON.toJSON(o).toString());
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        //1.加载配置文件
        doLoadConfig(config.getInitParameter("contextConfigLocation"));

        //2.扫描相关的类
        doScanner(contextConfig.getProperty("scan-package"));

        //3.初始化扫描到的类,并将他们放到IOC容器中
        doInstance();

        //4.完成依赖注入
        doAutowired();

        //5.初始化HandlerMapping
        initHandlerMapping();

        log.info("YK Spring framework is init!!!");

    }

    private void initHandlerMapping() {
        if (ioc.isEmpty()) return;

        ioc.forEach((key, value) -> {
            Class<?> clazz = value.getClass();

            if (!clazz.isAnnotationPresent(YKRestController.class)) return;

            //保存写在类上面的@YKRequestMapping("/demo")
            String baseUrl = "";
            if (clazz.isAnnotationPresent(YKRequestMapping.class)){
                YKRequestMapping requestMapping = clazz.getAnnotation(YKRequestMapping.class);
                baseUrl = requestMapping.value();
            }

            //默认获取所有的public方法
            for (Method method : clazz.getMethods()){
                if (!method.isAnnotationPresent(YKRequestMapping.class)) continue;

                YKRequestMapping requestMapping = method.getAnnotation(YKRequestMapping.class);
                String url = "/"+baseUrl+"/"+requestMapping.value().replaceAll("/+","/");

                handlerMapping.put(url, method);
                log.info("Mapped ："+url+", "+method);
            }
        });
    }

    private void doAutowired() {
        if (ioc.isEmpty()) return;

        ioc.forEach((key, value) -> {
            //Declared 所有的，特定的 字段，包括private/protected/default
            Field[] fields = value.getClass().getDeclaredFields();
            for (Field field : fields){
                field.setAccessible(true);
                if (!field.isAnnotationPresent(YKAutowired.class)) continue;

                YKAutowired autowired = field.getAnnotation(YKAutowired.class);
                String beanName = autowired.value();
                //如果用户没有自定义beanName，默认就根据类型注入
                if (StringUtils.isEmpty(beanName)){
                    //获得接口的类型，作为key待会拿这个key到ioc容器中去取值
                    beanName = field.getType().getName();
                    //用反射机制，动态给字段赋值
                    try {
                        field.set(value,ioc.get(beanName));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    private void doInstance() {
        if (classNames.isEmpty()) return;

        classNames.stream()
                .forEach(className -> {

                    try {
                        Class<?> clazz = Class.forName(className);
                        
                        if (clazz.isAnnotationPresent(YKRestController.class)){
                            Object instance = clazz.newInstance();
                            //Spring默认类名首字母小写
                            String beanName = toLowerFirstCase(clazz.getSimpleName());
                            ioc.put(beanName, instance);
                        }else if (clazz.isAnnotationPresent(YKService.class)){
                            YKService service = clazz.getAnnotation(YKService.class);
                            //自定义的beanName
                            String beanName = service.value();
                            //如果没有自定义的，则默认首字母小写
                            if (StringUtils.isEmpty(beanName)){
                                beanName = toLowerFirstCase(clazz.getSimpleName());
                            }
                            Object instance = clazz.newInstance();
                            ioc.put(beanName, instance);

                            //3、根据类型自动赋值
                           for (Class<?> c : clazz.getInterfaces()){
                               if (ioc.containsKey(c.getName())){
                                   throw new RuntimeException("The '" +c.getName() +"' is exists!!!");
                               }
                               //把接口的类型直接当成key了
                               ioc.put(c.getName(),instance);
                           }
                        }else{
                            return;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
    }


    private String toLowerFirstCase(String simpleName) {
        char[] chars = simpleName.toCharArray();

        //大小写字母的ASCII码相差32
        chars[0]+=32;
        return String.valueOf(chars);
    }

    private void doScanner(String scanPackage) {

        URL url = this.getClass().getClassLoader().getResource("/" + scanPackage.replaceAll("\\.", "/"));
        File file = new File(url.getPath());
        for (File f : file.listFiles()) {
            if (f.isDirectory()){
                doScanner(scanPackage+"."+f.getName());
            }else {
                if (!f.getName().endsWith(".class")) continue;

                String className = scanPackage +"."+f.getName().replace(".class","");
                classNames.add(className);
            }

        }





    }

    private void doLoadConfig(String contextConfigLocation) {

        InputStream is = this.getClass().getClassLoader().getResourceAsStream(contextConfigLocation);
        try {
            contextConfig.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (null != is){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }
}
