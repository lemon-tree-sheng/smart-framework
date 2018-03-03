package org.smart4j.framework;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.smart4j.framework.bean.Data;
import org.smart4j.framework.bean.Handler;
import org.smart4j.framework.bean.Param;
import org.smart4j.framework.bean.View;
import org.smart4j.framework.helper.BeanHelper;
import org.smart4j.framework.helper.ConfigHelper;
import org.smart4j.framework.helper.ControllerHelper;
import org.smart4j.framework.util.CodecUtil;
import org.smart4j.framework.util.JsonUtil;
import org.smart4j.framework.util.ReflectionUtil;
import org.smart4j.framework.util.StreamUtil;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * @author shengxingyue, created on 2018/3/4
 */
@WebServlet(urlPatterns = "/*", loadOnStartup = 0)
public class DispatcherServlet extends HttpServlet {
    @Override
    public void init(ServletConfig config) throws ServletException {
        HelperLoader.init();

        // 注册 jsp servlet
        ServletContext servletContext = config.getServletContext();
        ServletRegistration jspServlet = servletContext.getServletRegistration("jsp");
        jspServlet.addMapping(ConfigHelper.getAppJspPath() + "*");
        // 注册静态资源 servlet
        ServletRegistration defaultServlet = servletContext.getServletRegistration("default");
        defaultServlet.addMapping(ConfigHelper.getAppAssetPath() + "*");
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取请求方法和请求路径
        String requestMethod = req.getMethod();
        String requestPath = req.getPathInfo();
        // 找到对应 handler
        Handler handler = ControllerHelper.getHandler(requestMethod, requestPath);
        if (handler != null) {
            Class<?> controllerClass = handler.getControllerClass();
            Object controllerBean = BeanHelper.getBean(controllerClass);
            Map<String, Object> paramMap = new HashMap<>();
            // 提取请求头中的请求参数
            Enumeration<String> parameterNames = req.getParameterNames();
            while (parameterNames.hasMoreElements()) {
                String paramName = parameterNames.nextElement();
                String paramValue = req.getParameter(paramName);
                paramMap.put(paramName, paramValue);
            }
            // 提取请求体中的请求参数
            String body = CodecUtil.decodeUrl(StreamUtil.getString(req.getInputStream()));
            if (StringUtils.isNotEmpty(body)) {
                String[] params = StringUtils.split(body, "&");
                for (String param : params) {
                    String[] params2 = param.split("=");
                    if (ArrayUtils.isNotEmpty(params2) && params2.length == 2) {
                        String paramName = params2[0];
                        String paramValue = params2[1];
                        paramMap.put(paramName, paramValue);
                    }
                }
            }
            // 组装请求参数
            Param param = new Param(paramMap);
            // 调用 action 方法
            Method method = handler.getActionMethod();
            Object result = ReflectionUtil.invokeMethod(controllerBean, method, param);
            // 处理 action 方法的返回值
            if (result instanceof View) {
                // 返回 jsp 页面
                View view = (View) result;
                String path = view.getPath();
                Map<String, Object> model = view.getModel();
                if (path.startsWith("/")) {
                    resp.sendRedirect(req.getContextPath() + path);
                } else {
                    for (Map.Entry<String, Object> entry : model.entrySet()) {
                        req.setAttribute(entry.getKey(), entry.getValue());
                    }
                    req.getRequestDispatcher(path).forward(req, resp);
                }
            } else if (result instanceof Data) {
                Data data = (Data) result;
                Object model = data.getModel();
                if (model != null) {
                    resp.setContentType("application/json");
                    resp.setCharacterEncoding("UTF-8");
                    PrintWriter writer = resp.getWriter();
                    String json = JsonUtil.toJson(data);
                    writer.write(json);
                    writer.flush();
                    writer.close();
                }
            }
        }
    }
}
