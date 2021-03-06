<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:url value="/" var="linkServlet" />

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/html">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/PrevisaoTeste.css">
    <meta charset="UTF-8">
    <title>Index</title>
</head>
<body>
<div class="formCustom">

    <div class="container text-white pt-5">
        <div class="row align-items-start justify-content-between">
            <div class="col">
                <div class="text-end fs-1 pt-5">${previsao.temp}°</div>
            </div>
        </div>
        <div class="row align-items-start justify-content-between">
            <div class="col">
                <div class="text-start fs-3">${previsao.city_name}</div>
            </div>
            <div class="col">
                <div class="text-end  fs-4">${previsao.description}</div>
            </div>
        </div>
        <div class="row">
            <div class="col">
                <div class="text-start fs-4">Hoje</div>
            </div>
        </div>
    </div>
    <table class="table text-white position-relative  text-center">
        <thead>
        <tr>
            <th scope="col">Data</th>
            <th scope="col">Descrição</th>
            <th scope="col">Máxima</th>
            <th scope="col">Mínima</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="forecast" items="${previsao.forecast}">
            <tr>
                <th scope="row">${forecast.date}</th>
                <td>${forecast.description}</td>
                <td>${forecast.max}°</td>
                <td>${forecast.min}°</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <a href="${linkServlet}"><button type="submit" class="submit2">Fazer Outra Pesquisa</button></a>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>
</html>