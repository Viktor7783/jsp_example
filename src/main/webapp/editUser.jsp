<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit User</title>
    <!-- Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <h1 class="mb-4">Edit User</h1>
    <form action="${pageContext.request.contextPath}/users/edit" method="post">
        <input type="hidden" name="userId" value="${user.id}">
        <input type="hidden" name="oldEmail" value="${user.email}">
        <div class="form-group">
            <label for="name">Name</label>
            <input type="text" class="form-control" id="name" name="name" value="${user.name}" required>
        </div>
        <div class="form-group">
            <label for="email">Email</label>
            <input type="email" class="form-control" id="email" name="email" value="${user.email}" required>
        </div>
        <div class="form-group">
            <label for="role">Role</label>
            <select class="form-control" id="role" name="role" required>
                <option value="ADMIN" ${user.role == 'ADMIN' ? 'selected' : ''}>Admin</option>
                <option value="MANAGER" ${user.role == 'MANAGER' ? 'selected' : ''}>Manager</option>
                <option value="EMPLOYEE" ${user.role == 'EMPLOYEE' ? 'selected' : ''}>Employee</option>
                <option value="CUSTOMER" ${user.role == 'CUSTOMER' ? 'selected' : ''}>Customer</option>
                <option value="GUEST" ${user.role == 'GUEST' ? 'selected' : ''}>Guest</option>
            </select>
        </div>
        <button type="submit" class="btn btn-primary">Update</button>
    </form>
</div>
<!-- Bootstrap JS and dependencies -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>

<%--Expression Language (EL) в JSP работает через геттеры класса, а не напрямую с полями. В вашем случае это возможно благодаря:
    Аннотации @Getter от Lombok, которая автоматически генерирует методы:
    getId()
    getName()
    getEmail()
    getRole()
Когда вы используете EL выражения:
    ${user.id} - вызывается метод getId()
    ${user.email} - вызывается метод getEmail()
    ${user.name} - вызывается метод getName()
EL использует JavaBeans конвенцию для доступа к свойствам объекта. Когда вы пишете ${user.email}, EL автоматически ищет метод getEmail() в классе User. Поэтому несмотря на то, что поля private, доступ к ним происходит через публичные геттеры.
Если бы у вас не было геттеров (например, не было бы аннотации @Getter), то EL выражения не смогли бы получить значения этих полей.--%>