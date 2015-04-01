package com.bentyn.traincoll.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.bentyn.traincoll.base.SpaceBaseTest;


@Configuration
public class SpringConfig {
	
	@Bean
	public Test getTest(){
		System.out.println("TEST INIT");
		return new Test();
	}
	
	@Bean
	public SpaceBaseTest getSpaceBaseTest(){
		System.out.println("spaceBase INIT");
		return new SpaceBaseTest();
	}
	

	
}
