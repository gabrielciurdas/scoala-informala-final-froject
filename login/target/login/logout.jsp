<%
session.setAttribute("userName", null);
session.invalidate();
response.sendRedirect("index.jsp");
%>
- See more at: http://www.javawebtutor.com/articles/maven/maven_simple_login_application.php#sthash.s4spGVfg.dpuf