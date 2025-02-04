<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User List</title>
    <!-- Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <h1 class="mb-4">User List</h1>
    <table class="table table-striped">
        <thead>
        <tr>
            <th scope="col">Name</th>
            <th scope="col">Email</th>
            <th scope="col">Role</th>
            <th scope="col">Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="user" items="${users}">
            <tr>
                <td>${user.name}</td>
                <td>${user.email}</td>
                <td>${user.role}</td>
                <td>
                        <%--<a href="${pageContext.request.contextPath}/users/edit?name=${user.name}" class="btn btn-warning">Edit</a>--%>
                    <a href="${pageContext.request.contextPath}/users/edit?userId=${user.id}" class="btn btn-warning">Edit</a>
                    <form action="${pageContext.request.contextPath}/users/delete" method="post"
                          style="display: inline;">
                        <input type="hidden" name="userId" value="${user.id}">
                        <button type="submit" class="btn btn-danger">Delete</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <a href="${pageContext.request.contextPath}/users/create" class="btn btn-primary">Create New User</a>
</div>
<!-- Bootstrap JS and dependencies -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>

</html>

<%--Разберем ${pageContext.request.contextPath} подробно:
    pageContext - это встроенный объект JSP, который предоставляет доступ к различным атрибутам страницы
    request - это объект HttpServletRequest, доступный через pageContext
    contextPath - это метод, который возвращает путь к корню веб-приложения
Примеры значений contextPath:
    "/" - если приложение развернуто как ROOT
    "/myapp" - если приложение развернуто как myapp.war
    "/onlineshop" - если приложение развернуто как onlineshop.war
--%>