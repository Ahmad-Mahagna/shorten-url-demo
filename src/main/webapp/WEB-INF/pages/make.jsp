<%--
  Created by IntelliJ IDEA.
  User: amahagna
  Date: 11/23/14
  Time: 5:54 PM
  To change this template use File | Settings | File Templates.
--%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
    <title>Make Easy Url</title>
</head>
<body>

<h2>URL Information</h2>
<form:form method="POST" action="/tinyUrlMaker">
    <table>
        <tr>
            <td><form:label path="originalUrl">input your URL</form:label></td>
            <td><form:input path="originalUrl" /></td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="generate"/>
            </td>
        </tr>
    </table>

</form:form>
</body>
</html>