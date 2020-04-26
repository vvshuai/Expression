package com.vvs.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.vvs.pojo.ExpressionImpl;

/**
 * Servlet implementation class AlertServlet
 */
@WebServlet(name = "AlertServlet", urlPatterns = "/alert")
public class AlertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		String result[] = request.getParameterValues("num");
		List<ExpressionImpl> list = (List<ExpressionImpl>) session.getAttribute("list");
		int right = 0;
		int wrong = 0;
		for (int i = 0; i < result.length; i++) {
			if (result[i].equals(list.get(i).getResult())) {
				right++;
			} else {
				wrong++;
			}
		}
		response.setHeader("content-type", "text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write("<p style=\"text-align:left;\" width:900px; margin:0 auto;>正确" + right + "道，错误"
				+ wrong + "道！6秒钟跳到主页<p>");
		response.setHeader("refresh", "6;url=/ExpressionDemo/index.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
