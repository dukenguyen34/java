<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Player Form</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <div class="container">
        <h1>${player != null ? 'Edit Player' : 'Add New Player'}</h1>
        <form action="player" method="post" onsubmit="return validateForm()">
            <input type="hidden" name="action" value="${player != null ? 'update' : 'create'}">
            <c:if test="${player != null}">
                <input type="hidden" name="playerId" value="${player.playerId}">
            </c:if>
            <div class="form-group">
                <label for="name">Name:</label>
                <input type="text" id="name" name="name" value="${player.name}" required>
            </div>
            <div class="form-group">
                <label for="fullName">Full Name:</label>
                <input type="text" id="fullName" name="fullName" value="${player.fullName}" required>
            </div>
            <div class="form-group">
                <label for="age">Age:</label>
                <input type="text" id="age" name="age" value="${player.age}" required>
            </div>
            <div class="form-group">
                <label for="indexId">Index ID:</label>
                <input type="number" id="indexId" name="indexId" value="${player.indexId}" required>
            </div>
            <div class="form-group">
                <input type="submit" value="${player != null ? 'Update' : 'Create'}" class="btn">
            </div>
        </form>
    </div>
    <script>
        function validateForm() {
            var name = document.getElementById("name").value;
            var fullName = document.getElementById("fullName").value;
            var age = document.getElementById("age").value;
            var indexId = document.getElementById("indexId").value;

            if (name.trim() === "" || fullName.trim() === "" || age.trim() === "" || indexId.trim() === "") {
                alert("All fields are required");
                return false;
            }

            if (isNaN(age) || age < 0) {
                alert("Age must be a positive number");
                return false;
            }

            if (isNaN(indexId) || indexId < 1) {
                alert("Index ID must be a positive integer");
                return false;
            }

            return true;
        }
    </script>
</body>
</html>