<%--
  Created by IntelliJ IDEA.
  User: amahagna
  Date: 11/24/14
  Time: 5:49 PM
  To change this template use File | Settings | File Templates.
--%>
--%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
    <title>ERROR</title>
</head>
<body>

<h2>Error please try again </h2>
<table>
    <tr>
        <td>message</td>
        <td>${message}</td>
    </tr>
    <tr>
        <td>httpStatus</td>
        <td>${httpStatus}</td>
    </tr>
</table>
</body>
</html>