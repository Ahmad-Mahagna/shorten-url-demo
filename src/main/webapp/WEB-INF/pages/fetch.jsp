<%--
  Created by IntelliJ IDEA.
  User: amahagna
  Date: 11/24/14
  Time: 8:43 PM
  To change this template use File | Settings | File Templates.
--%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
    <title>fetch original Url</title>
</head>
<body>

<h2>easy URL Information</h2>
<form:form method="POST" action="/fetchUrl">
    <table>
        <tr>
            <td><form:label path="easyUrl">Name</form:label></td>
            <td><form:input path="easyUrl" /></td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="Submit"/>
            </td>
        </tr>
    </table>
</form:form>
</body>
</html>
