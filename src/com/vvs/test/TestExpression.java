package com.vvs.test;

import com.vvs.pojo.ExpressionImpl;
import com.vvs.pojo.RandomNumber;

public class TestExpression {
	
	public static void main(String args[]){
		char arr[] = {'+','-','*','/'};
		RandomNumber randomNumber = new RandomNumber(arr, 20);
		ExpressionImpl bTree;
		for(int i = 0; i < 8; i++){
			bTree = new ExpressionImpl(2, true, true);
			bTree.createDoubleBTree(randomNumber);
			System.out.println(bTree.toString()+"="+bTree.CalAndVal(randomNumber));
		}
	}
}
