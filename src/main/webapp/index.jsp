<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:url value="/webapp/previsao" var="linkServlet" />

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/html">
<head>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/indexTeste.css">
    <meta charset="ISO-8859-1">
    <title>Index</title>
</head>
<body>
<div class="form">
    <form action="${linkServlet}" method="post" id="formCEP">
        <div class="title">Bem Vindo</div>
        <div class="subtitle">Digite o seu CEP</div>
        <div class="input-container ic1">
            <input id="CEP" name="cep" class="input" type="text" placeholder=" " />
            <div class="cut "></div>
            <label for="CEP" class="placeholder">CEP</label>
        </div>
        <div class="input-container icSubmit">
            <button type="submit" class="submit " form="formCEP" >Previsão Para Minha Região</button>
        </div>
    </form>
</div>
</body>
</html>