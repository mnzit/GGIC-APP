<%@ page import="com.ggic.app.model.Student" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: manjitshakya
  Date: 08/02/2022
  Time: 6:10 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>GGIC</title>
</head>
<body>
<% if( request.getAttribute("failure") == null) {%>
<table style="border: 1px solid black">
    <thead>
<tr>
   <th>Id</th>
   <th>Name</th>
   <th>DOB</th>
   <th>Contact No</th>
   <th>Address</th>
</tr>
    </thead>
    <tbody>
    <% for(Student student: (List<Student>) request.getAttribute("students")) { %>
    <tr>
        <td><%=student.getId()%></td>
        <td><%=student.getName()%></td>
        <td><%=student.getDob()%></td>
        <td><%=student.getContactNo()%></td>
        <td><%=student.getAddress()%></td>
    </tr>
    <% }%>
    </tbody>
</table>
<%} else{ %>
<h1 style="padding:20px; background-color: red; color:white; margin: 20px">Failure Message: <%=request.getAttribute("failure")%></h1>
<%}%>

</body>
</html>
