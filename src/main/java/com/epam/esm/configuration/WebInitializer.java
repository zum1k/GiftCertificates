package com.epam.esm.configuration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public class WebInitializer  {
  //  implements WebApplicationInitializer
//    @Override
//    public void onStartup(ServletContext servletContext) throws ServletException {
//
//        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
//        servletContext.addListener(new ContextLoaderListener(context));
//        context.register(SpringConfig.class);
//        context.setServletContext(servletContext);
//        DispatcherServlet dispatcherServlet = new DispatcherServlet(context);
//        dispatcherServlet.setThrowExceptionIfNoHandlerFound(true);
//        ServletRegistration.Dynamic dynamic = servletContext.addServlet("dispatcher", dispatcherServlet);
//        dynamic.addMapping("/");
//
//        dynamic.setLoadOnStartup(1);
//    }
}
