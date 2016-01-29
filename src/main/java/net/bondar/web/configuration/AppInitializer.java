package net.bondar.web.configuration;

import org.springframework.orm.hibernate4.support.OpenSessionInViewFilter;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

/**
 * Created by Azeral on 24.11.2015.
 */
public class AppInitializer implements WebApplicationInitializer {
    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.register(WebAppConfig.class);
        servletContext.addListener(new ContextLoaderListener(context));

        context.setServletContext(servletContext);

        ServletRegistration.Dynamic servlet = servletContext.addServlet("dispatcher", new DispatcherServlet(context));
        servlet.addMapping("/");
        servlet.setLoadOnStartup(1);

        FilterRegistration.Dynamic hibernateFilter = servletContext.addFilter("hibernateFilter", new OpenSessionInViewFilter());
        hibernateFilter.setInitParameter("sessionFactoryBeanName", "sessionFactory");
        hibernateFilter.addMappingForUrlPatterns(null, true, "/*");

        FilterRegistration.Dynamic encodingFilterRegistration = servletContext.addFilter("charsetFilter", new CharacterEncodingFilter());
        encodingFilterRegistration.setInitParameter("encoding", "UTF-8");
        encodingFilterRegistration.setInitParameter("forceEncoding", "true");
        encodingFilterRegistration.addMappingForUrlPatterns(null, true, "/*");

    }
}
