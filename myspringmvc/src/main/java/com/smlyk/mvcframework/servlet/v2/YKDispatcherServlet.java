package com.smlyk.mvcframework.servlet.v2;

import com.alibaba.fastjson.JSON;
import com.smlyk.mvcframework.annotation.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.net.URL;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author yekai
 */
@Slf4j
public class YKDispatcherServlet extends HttpServlet{

    private static final String LOCATION = "contextConfigLocation";

    private static final String SCANPACKAGE = "scan-package";

    private Properties properties = new Properties();

    private List<String> classNames = new ArrayList<>();

    private Map<String,Object> ioc = new HashMap<>();

    //保存所有的Url和方法的映射关系
    private List<Handler> handlerMapping = new ArrayList<Handler>();


    /**
     * 初始化
     * @param config
     * @throws ServletException
     */
    @Override
    public void init(ServletConfig config) throws ServletException {

        //1.加载配置文件
        doLoadConfig(config.getInitParameter(LOCATION));

        //2.扫描所有相关的类
        doScanner(properties.getProperty(SCANPACKAGE));

        //3.初始化所有相关的类的实例，并保存到IOC容器中
        doInstance();

        //4.依赖注入
        doAutowried();

        //5.构造HandlerMapping
        initHandlerMapping();

        //6、等待请求，匹配URL，定位方法， 反射调用执行
        //调用doGet或者doPost方法

        log.info("my springmvcframework is init!!!");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            doDispatch(req, resp);
        }catch (Exception e){
            log.error("do Post error: ", e);
            //如果匹配过程出现异常，将异常信息打印出去
            resp.getWriter().write("服务器异常...");
        }
    }

    /**
     * 匹配URL
     * @param req
     * @param resp
     */
    private void doDispatch(HttpServletRequest req, HttpServletResponse resp) throws Exception{

        //解决中文乱码
        resp.setHeader("Content-type", "text/html;charset=UTF-8");

        Handler handler = getHandler(req);
        if (null == handler){
            //如果没有匹配上，返回404错误
            resp.getWriter().write("404 Not Found");
            return;
        }

        //获取方法的参数列表
        Class<?>[] paramTypes = handler.method.getParameterTypes();
        //保存所有需要自动赋值的参数值的数组
        Object[] paramValues = new Object[paramTypes.length];
        //传的参数
        Map<String, String[]> paramMap = req.getParameterMap();
        if (paramTypes.length != paramMap.size()){
            resp.getWriter().write("请输入参数");
            return;
        }

        paramMap.forEach((paramKey, paramVauleArr)->{
            //( /s 空白字符：[ /t/n/x0B/f/r])
            String value = Arrays.toString(paramVauleArr).replaceAll("\\[|\\]", "").replaceAll(",\\s", ",");

            if (!handler.paramIndexMapping.containsKey(paramKey)) return;
            //如果找到匹配的对象，则开始填充参数值
            int index = handler.paramIndexMapping.get(paramKey);
            paramValues[index] = convert(paramTypes[index], value);
        });

        //设置方法中的request和response对象
        if (handler.paramIndexMapping.containsKey(HttpServletRequest.class.getName())){
            int reqIndex = handler.paramIndexMapping.get(HttpServletRequest.class.getName());
            paramValues[reqIndex] = req;
        }

        if (handler.paramIndexMapping.containsKey(HttpServletResponse.class.getName())){
            int respIndex = handler.paramIndexMapping.get(HttpServletResponse.class.getName());
            paramValues[respIndex] = resp;
        }

        //反射调用方法
        Object obj = handler.method.invoke(handler.controller, paramValues);
        if (handler.controller.getClass().isAnnotationPresent(YKRestController.class)){
            resp.getWriter().write(JSON.toJSON(obj.toString()).toString());
        }
    }

    private Object convert(Class<?> paramType, String value) {
        if (Integer.class == paramType){
            return Integer.valueOf(value);
        }
        return value;
    }

    private Handler getHandler(HttpServletRequest req) {
        if (CollectionUtils.isEmpty(handlerMapping)) return null;

        String url  = req.getRequestURI();
        String contextPath = req.getContextPath();
        url = url.replace(contextPath, "").replaceAll("/+", "/");

        for (Handler handler : handlerMapping) {
            Matcher matcher = handler.pattern.matcher(url);

            if (matcher.matches()) return handler;
        }
        return null;
    }

    private void initHandlerMapping() {
        if (ioc.isEmpty()) return;

        ioc.forEach((key, instance) -> {
            Class<?> clazz = instance.getClass();
            if (!clazz.isAnnotationPresent(YKRestController.class) && !clazz.isAnnotationPresent(YKController.class)) return;

            String url = "";
            //获取Controller的url配置
            if (clazz.isAnnotationPresent(YKRequestMapping.class)){
                YKRequestMapping requestMapping = clazz.getAnnotation(YKRequestMapping.class);
                url = requestMapping.value();
            }

            //获取Method的url配置
            Method[] methods = clazz.getMethods();
            for (Method method: methods){
                //没有加RequestMapping注解的直接忽略
                if (!method.isAnnotationPresent(YKRequestMapping.class)) continue;

                //映射URL
                YKRequestMapping requestMapping = method.getAnnotation(YKRequestMapping.class);
                String regex = ("/" + url + "/"+requestMapping.value()).replaceAll("/+","/");
                Pattern pattern = Pattern.compile(regex);

                handlerMapping.add(new Handler(instance, method,pattern));
                log.info("Mapping: "+ regex + "," + method);
            }
        });

    }

    private void doAutowried() {
        if (CollectionUtils.isEmpty(ioc)) return;

        ioc.forEach((key, instance) ->{
            //拿到实例对象中的所有属性
            Field[] fields = instance.getClass().getDeclaredFields();
            for (Field field : fields){
                field.setAccessible(true); //设置私有属性的访问权限
                if (!field.isAnnotationPresent(YKAutowired.class)) return;

                YKAutowired autowired = field.getAnnotation(YKAutowired.class);
                String beanName = autowired.value().trim();
                if (StringUtils.isEmpty(beanName)){
                    beanName = field.getType().getName();
                    log.info( "field.getType().getName()----" + beanName);
                }

                try {
                    field.set(instance, ioc.get(beanName));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                    return;
                }
            }
        });
    }

    private void doInstance() {
        if (CollectionUtils.isEmpty(classNames)) return;

        classNames.stream()
                .forEach(className -> {

                    try {
                        Class<?> clazz = Class.forName(className);
                        if (clazz.isAnnotationPresent(YKRestController.class)||clazz.isAnnotationPresent(YKController.class)){
                            //默认将首字母小写作为beanName
                            String beanName = lowerFirst(clazz.getSimpleName());
                            ioc.put(beanName, clazz.newInstance());
                        }else if (clazz.isAnnotationPresent(YKService.class)){

                            YKService service = clazz.getAnnotation(YKService.class);
                            String beanName = service.value().trim();
                            //如果用户设置了名字，就用用户自己设置
                            if (!StringUtils.isEmpty(beanName)){
                                ioc.put(beanName, clazz.newInstance());
                                return;
                            }

                            //如果自己没设，就按接口类型创建一个实例
                            Class<?>[] interfaces = clazz.getInterfaces();
                            for (Class<?> i : interfaces){
                                ioc.put(i.getName(), clazz.newInstance());
                            }
                        }else {
                            return;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
    }

    /**
     * 首字母小写
     * @param str
     * @return
     */
    private String lowerFirst(String str) {
        char [] chars = str.toCharArray();
        chars[0] += 32;
        return String.valueOf(chars);

    }

    private void doScanner(String packageName) {
        //将所有的包路径转换为文件路径
        URL url = this.getClass().getClassLoader().getResource("/" + packageName.replaceAll("\\.", "/"));
        log.info("url.getPath()----" + url.getPath());
        log.info("url.getFile()----" + url.getFile());
        File file = new File(url.getPath());

        for (File f : file.listFiles()){
            log.info(f.getName());
            //如果是文件夹，继续递归
            if (f.isDirectory()){
                doScanner(packageName + "." + f.getName());
            }else {
                log.info("className----" + packageName + "." + f.getName().replace(".class", ""));
                classNames.add(packageName + "." + f.getName().replace(".class", ""));
            }

        }

    }

    private void doLoadConfig(String location) {

        InputStream is = this.getClass().getClassLoader().getResourceAsStream(location);
        try {
            //读取配置文件
            properties.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (null!=is){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Handler记录Controller中的RequestMapping和Method的对应关系
     */
    private class Handler{
        //保存方法对应的实例
        protected Object controller;

        //保存映射的方法
        protected Method method;

        protected Pattern pattern;

        //参数顺序
        protected Map<String,Integer> paramIndexMapping;

        /**
         * 构造一个Handler基本的参数
         * @param controller
         * @param method
         * @param pattern
         */
        public Handler(Object controller, Method method, Pattern pattern) {
            this.controller = controller;
            this.method = method;
            this.pattern = pattern;

            paramIndexMapping = new HashMap<>();
            putParamIndexMapping(method);
            log.info("paramIndexMapping-----"+ JSON.toJSONString(paramIndexMapping));
        }

        private void putParamIndexMapping(Method method) {
            Parameter[] parameters = method.getParameters();

            //提取方法中加了注解的参数
            Annotation[][] pa = method.getParameterAnnotations();

            for (int i= 0; i < pa.length; i++){
                //遍历某一个参数的注解数组
                for (int j= 0; j<pa[i].length; j++){
                    if (pa[i][j] instanceof YKRequestParam){
                        String paramName = ((YKRequestParam) pa[i][j]).value().trim();
                        if (!StringUtils.isEmpty(paramName)){
                            paramIndexMapping.put(paramName, i);
                        }else {
                            /**
                             * 获取的为什么是argn?解决：https://blog.csdn.net/wthfeng/article/details/72112967
                             */
                            paramIndexMapping.put(parameters[i].getName(),i);
                        }
                    }
                }
            }

            //提取方法中的request和response参数
            Class<?>[] parameterTypes = method.getParameterTypes();
            for (int i = 0; i<parameterTypes.length; i++){
                Class<?> type = parameterTypes[i];
                if(type == HttpServletRequest.class ||
                        type == HttpServletResponse.class){
                    paramIndexMapping.put(type.getName(),i);
                }
            }
        }
    }


}
