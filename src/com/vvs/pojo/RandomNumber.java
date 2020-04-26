package com.vvs.pojo;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Random;

public class RandomNumber{
	
	
	private char[] op;
	private int maxValue;
	
	public RandomNumber(char[] op, int maxValue) {
		//super();
		this.op = op;
		this.maxValue = maxValue;
	}

	public char getOperator() {
		Random ran = new Random();
		//System.out.println(op[ran.nextInt(op.length)]);
		return op[ran.nextInt(op.length)];
	}
	
	public int getNumber() {
		Random  ran = new Random();
		return ran.nextInt(maxValue+1)+1;
	}
	
	public double getNumber1() {
		Random ran = new Random();
		int cur = ran.nextInt((maxValue+1)*10);
		return (cur*1.0)/10.0+1.0;
	}
	
	public boolean[] getChildPlace(int num) {
		int d = 0;
		int size = 0,j = 1;
		while(num >= (int)Math.pow(2, j)) {
			j++;
		}
		d = (int)Math.pow(2, j)-1-num;
		size = (int)Math.pow(2, j-1);
		boolean[] k = new boolean[size];
		for(int i = 0; i < size; i++){
			k[i] = true;
		}
		for(int i = 0;i < d; i++) {
			Random ran = new Random();
			int f = ran.nextInt(size);
			while(k[f] == false) {
				f = ran.nextInt(size);
			}
			k[f] = false;
		}
		return k;
	}
}
