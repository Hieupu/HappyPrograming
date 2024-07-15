
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sign Up</title>
        <style>
            body {
                font-family: Arial, sans-serif;
            }
            .form-container {
                max-width: 400px;
                margin: 0 auto;
                padding: 20px;
                border: 1px solid #ccc;
                border-radius: 5px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            }
            .form-group {
                display: flex;
                flex-direction: column;
                margin-bottom: 15px;
            }
            .form-group label {
                margin-bottom: 5px;
                font-weight: bold;
            }
            .form-group input,
            .form-group select,
            .form-group textarea {
                padding: 8px;
                border: 1px solid #ccc;
                border-radius: 3px;
                width: 95%;
            }
            .form-group textarea {
                resize: vertical;
            }
            .form-group .form-row {
                display: flex;
                justify-content: space-between;
            }
            .form-group .form-row label {
                flex-basis: 30%;
                text-align: right;
                margin-right: 10px;
            }
            .form-group .form-row input,
            .form-group .form-row select {
                flex-basis: 65%;
            }
            .form-group .radio-group {
                display: flex;
                justify-content: flex-start;
            }
            .form-group .radio-group label {
                margin-right: 15px;
                font-weight: normal;
            }
            button {
                padding: 10px 15px;
                background-color: #4CAF50;
                color: white;
                border: none;
                border-radius: 5px;
                cursor: pointer;
            }
            button:hover {
                background-color: #45a049;
            }
            .error {
                color: red;
                margin-bottom: 15px;
            }
            #female {
                margin-left: 60px;
            }
        </style>
    </head>
    <body>
        <jsp:include page="header.jsp"></jsp:include>
            <div class="form-container">
                <form id="signupForm" action="/HappyPrograming/signup" method="POST" onsubmit="return validateForm();">
                <c:if test="${not empty errorMessage}">
                    <p class="error">${errorMessage}</p>
                </c:if>

                <div class="form-group">
                    <label for="username">Username:</label>
                    <input type="text" id="username" name="username" required>
                </div>

                <div class="form-group">
                    <label for="email">Email:</label>
                    <input type="email" id="email" name="email" required>
                </div>

                <div class="form-group">
                    <label for="password">Password:</label>
                    <input type="password" id="password" name="password" required>
                </div>

                <div class="form-group">
                    <label for="confirmPassword">Confirm Password:</label>
                    <input type="password" id="confirmPassword" name="confirmPassword" required>
                </div>

                <div class="form-group">
                    <label for="fullname">Full Name:</label>
                    <input type="text" id="fullname" name="fullname" required>
                </div>

                <div class="form-group">
                    <label for="phone">Phone Number:</label>
                    <input type="tel" id="phone" name="phone" required>
                </div>

                <div class="form-group">
                    <label for="dob">Date of Birth:</label>
                    <input type="date" id="dob" name="dob" required>
                </div>

                <div class="form-group">
                    <label>Gender:</label>
                    <div class="radio-group">
                        <input type="radio" id="male" name="gender" value="1" required> Male
                        <input type="radio" id="female" name="gender" value="0" required> Female
                        <input type="radio" id="other" name="gender" value="2" required> Other
                    </div>
                </div>

                <div class="form-group">
                    <label for="address">Address:</label>
                    <textarea id="address" name="address" required></textarea>
                </div>

                <input type="hidden" name="role" value="mentor"/>
                <button type="submit">Sign Up</button>
            </form>

        </div>
        <jsp:include page="footer.jsp"></jsp:include>
        <script>
            function validateForm() {
                var password = document.getElementById("password").value;
                var confirmPassword = document.getElementById("confirmPassword").value;

                if (password.length < 8) {
                    alert("Password must be at least 8 characters");
                    return false;
                }

                if (password !== confirmPassword) {
                    alert("Passwords do not match!");
                    return false;
                }

                return true;
            }
        </script>
    </body>
</html>
