package com.vvs.pojo;

import java.io.Serializable;

public class TreeNode {

	
	/**
	 * 
	 */
	private String str;
	private TreeNode right = null;
	private TreeNode left = null;
	
	public TreeNode(String str) {
		this.str = str;
	}

	public TreeNode(String str, TreeNode right, TreeNode left) {
		this.str = str;
		this.right = right;
		this.left = left;
	}
	
	public void setChild(TreeNode left, TreeNode right) {
		this.left = left;
		this.right = right;
	}

	public String getStr() {
		return str;
	}

	public void setStr(String str) {
		this.str = str;
	}

	public TreeNode getRight() {
		return right;
	}

	public void setRight(TreeNode right) {
		this.right = right;
	}

	public TreeNode getLeft() {
		return left;
	}

	public void setLeft(TreeNode left) {
		this.left = left;
	}
	
	public boolean hasChild() {
		if(left==null && right == null)
			return false;
		else
			return true;
	}
	
	public String getResult(RandomNumber randomNumber) {
		if(hasChild()) {
			switch(str) {
			case "+":
				return String.valueOf(Integer.parseInt(getLeft().getResult(randomNumber)) + Integer.parseInt(getRight().getResult(randomNumber)));
			case "-":
				return String.valueOf(Integer.parseInt(getLeft().getResult(randomNumber)) - Integer.parseInt(getRight().getResult(randomNumber)));
			case "*":
				return String.valueOf(Integer.parseInt(getLeft().getResult(randomNumber)) * Integer.parseInt(getRight().getResult(randomNumber)));
			case "/":
				if(getRight().getResult(randomNumber).equals("0")) {
					while(str.equals("/")) {
						str = String.valueOf(randomNumber.getOperator());
					}
					return this.getResult(randomNumber);
				}
				else if(Integer.parseInt(getLeft().getResult(randomNumber))%Integer.parseInt(getRight().getResult(randomNumber))!=0) {
					while(str.equals("/")) {
						str = String.valueOf(randomNumber.getOperator());
					}
					return this.getResult(randomNumber);
				}
				else {
					return String.valueOf(Integer.parseInt(getLeft().getResult(randomNumber))/Integer.parseInt(getRight().getResult(randomNumber)));
				}
			}
		}
		return str;
	}
	
	public String getDoubleResult(RandomNumber randomNumber){
		if(hasChild()){
			String s1 = getLeft().getDoubleResult(randomNumber);
			if(s1==null||s1.trim().isEmpty()) {
				s1 = "0";
			}
			String s2 = getRight().getDoubleResult(randomNumber);
			if(s2==null||s2.trim().isEmpty()) {
				s2 = "0";
			}
			switch(str){
				case "+":
					return String.format("%.1f", (Double.parseDouble(s1) + Double.parseDouble(s2)));
				case "-":
					return String.format("%.1f", (Double.parseDouble(s1) - Double.parseDouble(s2)));
				case "*":
					return String.format("%.1f", (Double.parseDouble(s1) * Double.parseDouble(s2)));
				case "/":
					if(s2.equals("0")){
						while(str.equals("/")){
							str = String.valueOf(randomNumber.getOperator());
						}
						return this.getDoubleResult(randomNumber);
					}
					else
						return String.format("%.1f", (Double.parseDouble(s1) / Double.parseDouble(s2)));
			}
		}
		return str;
	} 
	
	public String toString() {
		String lstr="", Rstr = "", Str = "";
		if(hasChild()) {
			if(getRight().hasChild()) {
				if(str.equals("/")) {
					Rstr = getRight().toString();
				}
				else if(str.equals("*")||str.equals("-")) {
					if(getRight().str.equals("+")||getRight().str.equals("-")) {
						Rstr = getRight().toString();
					}
					else {
						Rstr = getRight().toString().substring(1, getRight().toString().length()-1);
					}
				}
				else {
					Rstr = getRight().toString().substring(1, getRight().toString().length()-1);
				}
			}
			else {
				Rstr = getRight().str;
			}
			
			if(getLeft().hasChild()) {
				if(str.equals("*") || str.equals("/")) {
					if(getLeft().str.equals("+") || getRight().str.equals("-")) {
						lstr = getLeft().toString();
					}
					else {
						lstr = getLeft().toString().substring(1, getLeft().toString().length()-1);
					}
				}
				else {
					lstr = getLeft().toString().substring(1, getLeft().toString().length()-1);
				}
			}
			else {
    			lstr = getLeft().str;
			}
			Str = "(" + lstr + str + Rstr +")";
		}
		else {
			Str = str;
		}
		return Str;
	}
}
