/**
 * 
 */
package com.github.marocraft.trackntrace.http.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

/**
 * 
 *
 */
@WebServlet()
public class MyServlet extends HttpServletResponseWrapper {

	/**
	 * @param response
	 */
	public MyServlet(HttpServletResponse response) {
		super(response);
		// TODO Auto-generated constructor stub
	}

//  @Override
//  protected void doGet (HttpServletRequest req,
//                        HttpServletResponse resp)
//            throws ServletException, IOException {
//  
//      System.out.println("-- In MyServlet --");
//      PrintWriter writer = resp.getWriter();
//      writer.println("dummy response from MyServlet");
//  }
	
}