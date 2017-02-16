package com.vmware.transformer.utils;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.vmware.transformer.service.Service;

public class SpringUtils {
	
	@SuppressWarnings("resource")
	public static Service getService(){
		ApplicationContext context = new ClassPathXmlApplicationContext("ApplicationContext.xml");
		Service service = (Service)context.getBean("service");
		return service;
	}

}
