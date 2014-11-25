<%--
  Created by IntelliJ IDEA.
  User: amahagna
  Date: 11/23/14
  Time: 5:55 PM
  To change this template use File | Settings | File Templates.
--%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
    <title>Generate Easy Url</title>
</head>
<body>

<h2>Submitted Url Information</h2>
<table>
    <tr>
        <td>your input:</td>
        <td>${fullUrl}</td>
    </tr>
    <tr>
        <td>easyURL:</td>
        <td>${easyURL}</td>
    </tr>

</table>
<form action="../">
    <input type="submit" value="Home">
</form>
</body>
</html>