package com.mit.spr_web;

import java.io.IOException;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.slf4j.Logger;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import com.mit.configer.ConfigUtils;

/**
 * Hello world!
 *
 */
public class App {
	public static final String WEBAPP_FOLDER = "webapp";
	public static final String CONFIG_FOLDER = "com.mit.spr_web.config";
	public static final Logger _logger = org.slf4j.LoggerFactory.getLogger(App.class);
	
    public static void main( String[] args ) throws Exception {
    	try {
	    	int port = ConfigUtils.getConfig().getInt("webserver.port", 10025);
	    	Server server = new Server(port);
	    	server.setHandler(getServletContextHandler(getContext()));
	    	server.start();
	    	_logger.info("Server start on port " + port);
	    	System.out.println("Server start on port " + port);
	    	server.join();
    	} catch (Exception e) {
    		_logger.error("Server can't start. ");
    		_logger.debug(e.getMessage());
    	}
    }
    
    public static WebApplicationContext getContext() {
    	AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
    	context.setConfigLocation(CONFIG_FOLDER);
    	return context;
    }
    
    public static ServletContextHandler getServletContextHandler(WebApplicationContext webContext) throws IOException {
    	ServletContextHandler servletContext = new ServletContextHandler();
    	servletContext.setContextPath("/");
    	servletContext.addServlet(new ServletHolder(new DispatcherServlet(webContext)), "/*");
    	servletContext.addEventListener(new ContextLoaderListener(webContext));
    	ClassPathResource resources = new ClassPathResource(WEBAPP_FOLDER);
    	servletContext.setResourceBase(resources.getURI().toString());
    	return servletContext;
    }
    
}
