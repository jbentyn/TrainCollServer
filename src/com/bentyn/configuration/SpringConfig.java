package com.bentyn.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.bentyn.WebSocketDemo;


@Configuration
public class SpringConfig {
	
	@Bean
	public Test getTest(){
		System.out.println("TEST INIT");
		return new Test();
	}
	
//	@Bean
//	public WebSocketDemo getWebSocketDemo(){
//		System.out.println("WEB SOCKET INIT");
//		return new WebSocketDemo(new Test());
//	}
	
}
