<%--
  Created by IntelliJ IDEA.
  User: amahagna
  Date: 11/24/14
  Time: 8:46 PM
  To change this template use File | Settings | File Templates.
--%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
    <title>fetch original Url</title>
</head>
<body>

<h2>Submitted easy Url Information</h2>
<table>
    <tr>
        <td>your input: </td>
        <td>${easyUrl}</td>
    </tr>
    <tr>
        <td>original url:</td>
        <td>${originalUrl}</td>
    </tr>
</table>
<form action="../">
    <input type="submit" value="Home">
</form>
</body>
</html>
