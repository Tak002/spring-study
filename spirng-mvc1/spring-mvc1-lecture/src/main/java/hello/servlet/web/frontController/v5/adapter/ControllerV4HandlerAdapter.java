package hello.servlet.web.frontController.v5.adapter;

import hello.servlet.web.frontController.ModelView;
import hello.servlet.web.frontController.v4.ControllerV4;
import hello.servlet.web.frontController.v5.MyHandlerAdapter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class ControllerV4HandlerAdapter implements MyHandlerAdapter {
    @Override
    public boolean supports(Object handler) {
        return (handler instanceof ControllerV4);
    }

    @Override
    public ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException {

        ControllerV4 controller = (ControllerV4)handler;
        Map<String, String> paramMap = createParamMap(request);
        Map<String,Object> model = new HashMap<>();

        String viewName = controller.process(paramMap, model);
        ModelView modelView = new ModelView(viewName);

        modelView.getModel().putAll(model);
        modelView.setModel(model);

        return modelView;
    }
    private static Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>();
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String parameterName = parameterNames.nextElement();
            paramMap.put(parameterName, request.getParameter(parameterName));
        }
        return paramMap;
    }

}
