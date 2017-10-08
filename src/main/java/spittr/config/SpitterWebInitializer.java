package spittr.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import spittr.web.WebConfig;

public class SpitterWebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
  
	@Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[] { RootConfig.class };
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[] {
            WebConfig.class
        };
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }
    
//  @Override
//  public void onStartup(ServletContext servletContext) throws ServletException {
//      // Create the 'root' Spring application context
//      //AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
////      rootContext.register(RootConfig.class, DataConfig.class);
//
//      // Manage the lifecycle of the root application context
////      servletContext.addListener(new ContextLoaderListener(rootContext));
//
//      // Create the dispatcher servlet's Spring application context
//      AnnotationConfigWebApplicationContext dispatcherServlet = new AnnotationConfigWebApplicationContext();
//      dispatcherServlet.register(WebConfig.class);
//
//      // Register and map the dispatcher servlet
//      ServletRegistration.Dynamic dispatcher = servletContext.addServlet("dispatcher", new DispatcherServlet(dispatcherServlet));
//      dispatcher.setLoadOnStartup(1);
//      dispatcher.addMapping("/");
//  }

}