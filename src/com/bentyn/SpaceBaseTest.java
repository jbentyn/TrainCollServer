package com.bentyn;

import com.bentyn.configuration.Test;

import co.paralleluniverse.spacebase.SpaceBase;
import co.paralleluniverse.spacebase.SpaceBaseBuilder;

public class SpaceBaseTest {

	SpaceBase<Test> base;
	
	public SpaceBaseTest(){
		base  = new SpaceBaseBuilder().setDimensions(2).build("space-1");
		System.out.println("Base created");
	}

	public SpaceBase<Test> getBase() {
		return base;
	}

	public void setBase(SpaceBase<Test> base) {
		this.base = base;
	}
	
}
