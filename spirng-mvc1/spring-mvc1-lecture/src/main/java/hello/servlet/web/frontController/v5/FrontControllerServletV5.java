package hello.servlet.web.frontController.v5;

import hello.servlet.web.frontController.ModelView;
import hello.servlet.web.frontController.MyView;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.*;

@WebServlet(name = "frontControllerServletV5", urlPatterns = "/front-controller/v5/*")
public class FrontControllerServletV5 extends HttpServlet {
    private final Map<String, Object> handlerMappingMap;
    private final List<MyHandlerAdapter> handlerAdapterList;

    public FrontControllerServletV5() {
        handlerMappingMap = HandlerMappingConfig.initHandlerMappingMap();
        handlerAdapterList = HandlerMappingConfig.initHandlerAdapters();
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Object handler = getHandler(request);
        MyHandlerAdapter handlerAdapter= getHandlerAdapter(handler);

        ModelView modelView = handlerAdapter.handle(request, response, handler);
        MyView view = viewResolver(modelView);
        view.render(modelView.getModel(), request, response);

    }

    private MyHandlerAdapter getHandlerAdapter(Object handler) {
        for (MyHandlerAdapter adapter : handlerAdapterList) {
            if (adapter.supports(handler)){
                return adapter;
            }
        }
        throw new IllegalArgumentException("handler adapter 찾을 수가 없습니다");
    }

    private Object getHandler(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        Object handler  = handlerMappingMap.get(requestURI);
        if(handler == null){
            throw new IllegalArgumentException("handler를 찾을 수가 없습니다");
        }
        return handler;
    }


    private static MyView viewResolver(ModelView modelView) {
        return new MyView("/WEB-INF/views/"+ modelView.getViewName()+".jsp");
    }
}
