<%@ page language="java" 
         contentType="text/html; charset=windows-1256"
         pageEncoding="windows-1256"
         
   %>
 
   <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
   "http://www.w3.org/TR/html4/loose.dtd">

   <html>

      <head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>ACT ACE Welcome</title>
	</head>

	<body>
         <center>
            <% String currentUser = (String )(session.getAttribute("LoginUser"));%>
			<applet code=com.act.client.MainApplet.class width="1350" height="650" 
			  archive= "AceApplet.jar">
			  
	           <param name="LoggedInUser"  value=<%= currentUser  %>/>
			</applet>
         </center>
	</body>
   </html>

