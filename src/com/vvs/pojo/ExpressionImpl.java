package com.vvs.pojo;

import java.io.Serializable;
import java.util.ArrayList;

public class ExpressionImpl{
	
	private TreeNode root;
	private int num;
	private ArrayList<TreeNode> openList = new ArrayList<TreeNode>();
	private String result;
	private boolean hDouble;
	private boolean hCur;
	
	public ExpressionImpl(int num, boolean hDouble, boolean hCur) {
		this.num = num;
		this.hDouble = hDouble;
		this.hCur = hCur;
	}
	public ExpressionImpl() {
		// TODO Auto-generated constructor stub
	}
	
	public int getNum() {
		return num;
	}
	
	public void setNum(int num) {
		this.num = num;
	}
	
	public void setTreeNode(TreeNode root) {
		this.root = root;
	}
	
	public String toString() {
		String str = root.toString();
		if(hCur==true) {
			str = str.substring(1, str.length()-1);
		}
		return str;
	}
	
	public String CalAndVal(RandomNumber randomNumber) {
		if(hDouble==false) {
			this.result = root.getResult(randomNumber);
		}
		else {
			this.result = root.getDoubleResult(randomNumber);
		}
		return this.result;
	}
	
	public boolean ishDouble() {
		return hDouble;
	}
	public void sethDouble(boolean hDouble) {
		this.hDouble = hDouble;
	}
	public String getResult() {
		return this.result;
	}
	
	public int getDeep() {
		int i = this.num;
		int deep = 2;
		while(i/2 > 0) {
			deep ++;
			i /= 2;
		}
		return deep;
	}
	
	public void createBTree(RandomNumber randomNumber) {
		TreeNode left, right, lnode, rnode;
		
		if(num == 1) {
			left = new TreeNode(String.valueOf(randomNumber.getNumber()), null, null);
			right = new TreeNode(String.valueOf(randomNumber.getNumber()), null, null);
			root = new TreeNode(String.valueOf(randomNumber.getOperator()), left, right);
		}
		else {
			int num1 = 0;
			int n = getDeep() - 3;
			boolean[] place = randomNumber.getChildPlace(num);
			root = new TreeNode(String.valueOf(randomNumber.getOperator()), null, null);
			openList.add(root);
			
			for(int i = 0;i < n; i++) {
				for(int j = 0;j < (int)Math.pow(2, i); j++, num1++) {
					left = new TreeNode(String.valueOf(randomNumber.getOperator()), null, null);
					right = new TreeNode(String.valueOf(randomNumber.getOperator()), null, null);
					openList.get(j+num1).setChild(left, right);
					openList.add(left);
					openList.add(right);
				}
			}
			
			for(int i = 0;i < place.length; i++) {
				if(place[i]) {
					lnode = new TreeNode(String.valueOf(randomNumber.getNumber()), null, null);
					rnode = new TreeNode(String.valueOf(randomNumber.getNumber()), null, null);
					if(i%2==0) {
						left = new TreeNode(String.valueOf(randomNumber.getOperator()), lnode, rnode);
						openList.add(left);
						openList.get(num1).setLeft(left);
					}
					else {
						right = new TreeNode(String.valueOf(randomNumber.getOperator()), lnode, rnode);
						openList.add(right);
						openList.get(num1).setRight(right);
					}
					
				}
				else {
					if(i%2==0) {
						left = new TreeNode(String.valueOf(randomNumber.getNumber()), null, null);
						openList.get(num1).setLeft(left);
					}
					else {
						right = new TreeNode(String.valueOf(randomNumber.getNumber()), null, null);
						openList.get(num1).setRight(right);
					}
				}
				num1 = num1 + i%2;
			}
		}
	}
	
	public void createDoubleBTree(RandomNumber randomNumber) {
		TreeNode left, right, lnode, rnode;
		
		if(num == 1) {
			left = new TreeNode(String.valueOf(randomNumber.getNumber1()), null, null);
			right = new TreeNode(String.valueOf(randomNumber.getNumber1()), null, null);
			root = new TreeNode(String.valueOf(randomNumber.getOperator()), left, right);
		}
		else {
			int num1 = 0;
			int n = getDeep() - 3;
			boolean[] place = randomNumber.getChildPlace(num);
			root = new TreeNode(String.valueOf(randomNumber.getOperator()), null, null);
			openList.add(root);
			
			for(int i = 0;i < n; i++) {
				for(int j = 0;j < (int)Math.pow(2, i); j++, num1++) {
					left = new TreeNode(String.valueOf(randomNumber.getOperator()), null, null);
					right = new TreeNode(String.valueOf(randomNumber.getOperator()), null, null);
					openList.get(j+num1).setChild(left, right);
					openList.add(left);
					openList.add(right);
				}
			}
			
			for(int i = 0;i < place.length; i++) {
				if(place[i]) {
					lnode = new TreeNode(String.valueOf(randomNumber.getNumber1()), null, null);
					rnode = new TreeNode(String.valueOf(randomNumber.getNumber1()), null, null);
					if(i%2==0) {
						left = new TreeNode(String.valueOf(randomNumber.getOperator()), lnode, rnode);
						openList.add(left);
						openList.get(num1).setLeft(left);
					}
					else {
						right = new TreeNode(String.valueOf(randomNumber.getOperator()), lnode, rnode);
						openList.add(right);
						openList.get(num1).setRight(right);
					}
					
				}
				else {
					if(i%2==0) {
						left = new TreeNode(String.valueOf(randomNumber.getNumber1()), null, null);
						openList.get(num1).setLeft(left);
					}
					else {
						right = new TreeNode(String.valueOf(randomNumber.getNumber1()), null, null);
						openList.get(num1).setRight(right);
					}
				}
				num1 = num1 + i%2;
			}
		}
	}
}
