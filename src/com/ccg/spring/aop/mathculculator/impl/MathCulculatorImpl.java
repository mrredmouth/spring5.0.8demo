package com.ccg.spring.aop.mathculculator.impl;

import org.springframework.stereotype.Component;

import com.ccg.spring.aop.mathculculator.MathCulculator;

@Component("mathCulculator")
public class MathCulculatorImpl implements MathCulculator{

	@Override
	public int add(int i,int j) {
		int result = i+j;
		System.out.println(result);
		return result;
	}

	@Override
	public double div(int i, int j) {
		double result = i/j;
		System.out.println(result);
		return result;
	}

}
