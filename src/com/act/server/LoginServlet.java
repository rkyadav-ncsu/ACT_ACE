package com.act.server;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.act.server.db.UserMSDB;

public class LoginServlet extends HttpServlet{
	
//    private static final long serialVersionUID = 102831973239L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
    	
    	System.out.println("hit the servlet!!!!");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        //Authentication
        String userId = request.getParameter("username");
        System.out.println("userId" + userId);
        String password  = request.getParameter("password");
        String login = request.getParameter("Login");
        
        String s = "<html> " +
        		"<head>" +
        		"<meta http-equiv='Content-Type' content='text/html; charset=ISO-8859-1>" +
        		"<title>Login ACT ACE</title><body>\n" +
        		"</head>" +
        		"<body>" +
        		
        		"<img src='/logo-top.jpg' >" +
                "<form method='POST' action='ACE'>\n" + 
        		
                "<style type='text/css'>" +
        		"input { width: 15em;  } </style>" +
                
                "<br> <h1> <b> <left> ACT ACE </center> </b> </h1>"+ "<br><br>\n" + 
                "<table align = 'left'>"+
	                "<tr>"  +
	                	"<td> User Id </td>  <td><input  type='text' name='username'></td>" +
	                "</tr>" +
	                "<tr>"  +
	                	"<td> Password </td>  <td><input type='password' name='password'></td>" +
	                "</tr>" +
                "</table> <br><br><br><br> "+
	                
                "<input type='submit' value='Login' name = 'Login' align = 'left' >" + 
                "</form></body>"+
                "</html>" ;
        
        //first check if page is opened first time
        if (login == null){
        	System.out.println("first time");
        	out.println(s);
        }
        else if (validateUser(userId, password)){
        	System.out.println("login success");
        	request.getSession().setAttribute("USERID", userId);
        	
			RequestDispatcher rs = request.getRequestDispatcher("Welcome.html");
			request.setAttribute("LoginUser", userId);
			request.setAttribute("Password", password);
			rs.include(request, response);
        }
        else{
        	System.out.println("login incorrect");
        	out.println("<font color = 'red' > User Name or Password is incorrect. </font>");
        	out.println(s);
        	

        }
    }  
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
  
    	doPost(request,response);
    }
    
    private boolean validateUser(String userName, String password){
    	UserMSDB userDB = new UserMSDB();
    	return userDB.findUser(userName, password);
    	
//    	if(userName != null && userName.equals("admin") && 
//    			password != null && password.equals("admin!@#")){
//    		return true;
//    	}
//    	return false;
    }
}



