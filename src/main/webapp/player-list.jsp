<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Player Management</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <div class="container">
        <h1>Player Management</h1>
        <div class="actions">
            <a href="player?action=new" class="btn">Add New Player</a>
        </div>
        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Full Name</th>
                    <th>Age</th>
                    <th>Index ID</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="player" items="${players}">
                    <tr>
                        <td>${player.playerId}</td>
                        <td>${player.name}</td>
                        <td>${player.fullName}</td>
                        <td>${player.age}</td>
                        <td>${player.indexId}</td>
                        <td>
                            <a href="player?action=edit&id=${player.playerId}" class="btn btn-edit">Edit</a>
                            <a href="player?action=delete&id=${player.playerId}" class="btn btn-delete" onclick="return confirm('Are you sure you want to delete this player?')">Delete</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>