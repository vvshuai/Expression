package com.vvs.servlet;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.vvs.pojo.ExpressionImpl;
import com.vvs.pojo.RandomNumber;

/**
 * Servlet implementation class ExpressionWeb
 */
@WebServlet(name = "ExpressionServlet", urlPatterns = "/hello")
public class ExpressionServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		int num = Integer.parseInt(request.getParameter("num"));
		int num1 = Integer.parseInt(request.getParameter("num1"));
		String[] users = request.getParameterValues("use");
		String[] ways = request.getParameterValues("way");
		char[] str = new char[users.length];
		if (users == null || ways == null) {
			response.sendRedirect(request.getContextPath() + "/index.jsp");
			return;
		}
		boolean hDouble = false, hCur = false, hweb = false, hfile = false;
		int cur = 0;
		for (String u : users) {
			switch (u) {
			case "add":
				str[cur++] = '+';
				break;
			case "sub":
				str[cur++] = '-';
				break;
			case "mul":
				str[cur++] = '*';
				break;
			case "div":
				str[cur++] = '/';
				break;
			}
		}
		for (String w : ways) {
			System.out.println(w);
			if (w.equals("web1")) {
				hweb = true;
			}
			if (w.equals("file1")) {
				hfile = true;
			}
			if(w.equals("cur")) {
				hCur = true;
			}
			if(w.equals("dou")) {
				hDouble = true;
			}
		}
		ExpressionImpl expressionImpl;
		RandomNumber randomNumber = new RandomNumber(str, num1);
		List<ExpressionImpl> list = new ArrayList<ExpressionImpl>();
		for (int i = 0; i < num; i++) {
			expressionImpl = new ExpressionImpl(2, hDouble, hCur);
			if(hDouble==false) {
				expressionImpl.createBTree(randomNumber);
			}
			else {
				expressionImpl.createDoubleBTree(randomNumber);
			}
			expressionImpl.CalAndVal(randomNumber);
			list.add(expressionImpl);
		}
		if (hweb == true) {
			session.setAttribute("list", list);
			request.getRequestDispatcher("/success.jsp").forward(request, response);
		}
		BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream("D:\\result.txt"));
		for (ExpressionImpl expressionImpl1 : list) {
			out.write(expressionImpl1.getResult().getBytes());
			out.write("\r\n".getBytes());
			out.flush();
		}
		out.close();
		if (hfile == true) {
			 out = new BufferedOutputStream(new FileOutputStream("D:\\expression.txt"));
			for (ExpressionImpl emExpressionImpl : list) {
				out.write(emExpressionImpl.toString().getBytes());
				out.write("\r\n".getBytes());
				out.flush();
			}
			out.close();
			if (hweb == true) {
				return;
			}
			response.setHeader("content-type", "text/html;charset=UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write("生成成功！5秒钟跳到主页");
			response.setHeader("refresh", "5;url=/ExpressionDemo/index.jsp");
		}
	}

}
