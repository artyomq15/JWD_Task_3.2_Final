<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="localization.error" var="error"/>

<fmt:message key="header.error" bundle="${error}" var="headerError"/>
<fmt:message key="text.error" bundle="${error}" var="label"/>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="../../css/index.css"/>
    <link rel="icon" href="../../img/logo.png">
    <title>${headerError}</title>
</head>
<body>
<section style="margin: 1%;">
    <h1>${headerError}</h1>
    <p>${label}</p>
</section>
<c:import url="footer.jsp"/>
</body>
</html>
