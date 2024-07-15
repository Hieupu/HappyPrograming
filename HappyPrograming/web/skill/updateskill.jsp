<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Update Skill</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f9f9f9;
                margin: 0;
                padding: 0;
            }
            .container {
                max-width: 600px;
                margin: 50px auto;
                padding: 20px;
                background-color: #fff;
                border-radius: 5px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            }
            h2 {
                text-align: center;
                color: #333;
            }
            form {
                display: flex;
                flex-direction: column;
            }
            label, input {
                margin: 10px 0;
            }
            input[type="text"],
            input[type="radio"] {
                padding: 10px;
                border: 1px solid #ccc;
                border-radius: 5px;
            }
            input[type="submit"] {
                padding: 10px 20px;
                background-color: #4CAF50;
                color: #fff;
                border: none;
                border-radius: 5px;
                cursor: pointer;
                transition: background-color 0.3s ease;
            }
            input[type="submit"]:hover {
                background-color: #45a049;
            }
            .back-link {
                display: block;
                text-align: center;
                margin: 20px 0;
                color: #007bff;
                text-decoration: none;
            }
            .back-link:hover {
                text-decoration: underline;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <h2>Update Skill</h2>
            <form action="updateskill" method="post">
                <input type="hidden" name="id" value="${skill.id}">

                <label for="name">Name:</label>
                <input type="text" id="name" name="name" value="${skill.name}" required>
                
                Status: <select style="width: 80px" id="status" name="status">
                    <option value="1" <c:if test="${skill.status == 1}">selected</c:if>>Active</option>
                    <option value="0" <c:if test="${skill.status == 0}">selected</c:if>>Inactive</option>
                </select>
                <input type="submit" value="Update">
            </form>
            <a class="back-link" href="skilllist">Back to Home</a>
        </div>
    </body>
</html>
