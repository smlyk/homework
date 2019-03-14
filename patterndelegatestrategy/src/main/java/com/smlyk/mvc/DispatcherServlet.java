package com.smlyk.mvc;

import com.smlyk.mvc.controller.MemberController;
import com.smlyk.mvc.controller.OrderController;
import com.smlyk.mvc.controller.SystemController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * 相当于项目经理的角色
 * @author yekai
 */
public class DispatcherServlet extends HttpServlet {

    private Map<String, Handler> handlerMapping = new HashMap<>();

    @Override
    public void init() throws ServletException {

        try {
            Class<?> memberControllerClass = MemberController.class;
            handlerMapping.put("/getMemberById", new Handler().setController(memberControllerClass.newInstance())
                    .setMethod(memberControllerClass.getMethod("getMemberById",new Class[]{String.class,HttpServletResponse.class}))
                    .setUri("/getMemberById"));
            Class<?> orderControllerClass = OrderController.class;
            handlerMapping.put("/getOrderById", new Handler().setController(orderControllerClass.newInstance())
                    .setMethod(orderControllerClass.getMethod("getOrderById",new Class[]{String.class,HttpServletResponse.class}))
                    .setUri("/getOrderById"));
            Class<?> systemControllerClass = SystemController.class;
            handlerMapping.put("/logout", new Handler().setController(systemControllerClass.newInstance())
                    .setMethod(systemControllerClass.getMethod("logout",new Class[]{String.class,HttpServletResponse.class}))
                    .setUri("/logout"));
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

    }

    private void doDispatch(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String uri = request.getRequestURI();
        String mid = request.getParameter("mid");

        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

//        if ("/getMemberById".equals(uri)){
//            new MemberController().getMemberById(mid, response);
//        }else if ("/getOrderById".equals(uri)){
//            new OrderController().getOrderById(mid, response);
//        }else if ("/logout".equals(uri)){
//            new SystemController().logout(mid, response);
//        }else {
//                response.getWriter().write("404 NOT FOUND");
//        }

        Handler handler = handlerMapping.get(uri);
        if (null != handler){
            //将具体的任务分发给Method（通过反射去调用其对应的方法）
            Object obj = handler.getMethod().invoke(handler.getController(), request.getParameter("mid"), response);
        }else {
            response.getWriter().write("404 NOT FOUND");
        }

    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            doDispatch(req,resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    class Handler{
        private Object controller;
        private Method method;
        private String uri;

        public Object getController() {
            return controller;
        }

        public Handler setController(Object controller) {
            this.controller = controller;
            return this;
        }

        public Method getMethod() {
            return method;
        }

        public Handler setMethod(Method method) {
            this.method = method;
            return this;
        }

        public String getUri() {
            return uri;
        }

        public Handler setUri(String uri) {
            this.uri = uri;
            return this;
        }
    }

}
