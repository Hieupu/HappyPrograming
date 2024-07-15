<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Create Skill</title>
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
            input[type="number"] {
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
            <h2>Create Skill</h2>
            <form action="/HappyPrograming/admin/addskill" method="post">
                <label for="id">ID:</label>
                <input type="text" id="id" name="id" required>

                <label for="name">Name:</label>
                <input type="text" id="name" name="name" required>

                <div style="display: flex">
                    <label for="status">Status:</label>
                    <input style="margin-left: 60px" type="radio" id="active" name="status" value="1"/>
                    <label for="active">Active</label>
                    <input style="margin-left: 80px" type="radio" id="inactive" name="status" value="0"/>
                    <label for="inactive">Inactive</label>
                </div> <br>
                <input type="submit" value="Create">
            </form>
            <a class="back-link" href="skill.jsp">Back to Home</a>
        </div>
    </body>
</html>
