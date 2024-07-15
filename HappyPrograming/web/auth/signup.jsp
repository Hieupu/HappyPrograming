<%-- 
    Document   : signup
    Created on : May 16, 2024, 11:10:33 PM
    Author     : Admin
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Sign Up</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f2f2f2;
        }

        .container {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        .signup-link {
            display: block;
            text-align: center;
            padding: 20px;
            margin: 20px;
            width: 200px;
            background-color: #fff;
            border: 1px solid #ccc;
            border-radius: 5px;
            text-decoration: none;
            color: #333;
            transition: background-color 0.3s ease;
        }

        .signup-link:hover {
            background-color: #f9f9f9;
        }
    </style>
    
</head>
<body>
<div class="container">
    <a href="mentorsignup.jsp" class="signup-link">Sign up as a mentor</a>
    <a href="menteesignup.jsp" class="signup-link">Sign up as a mentee</a>
</div>
</body>
</html>
