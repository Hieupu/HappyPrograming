<!DOCTYPE html>
<html lang="en">
<head>
    <title>Sign In</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
    <div class="content">
        <form action="/HappyPrograming/login" method="POST">
            <div>
                <label for="user">Username:</label> 
                <input type="text" id="userID" name="username" placeholder="Enter Username">
            </div>
            <div>
                <label for="pass">Password:</label>
                <input type="password" id="pass" name="password" placeholder="Enter Password">
            </div>
            <div style="display: flex; justify-content: space-between">
                <label><input type="checkbox" name="remember" value="Y"/> Remember me</label>
                <span class="forgot-password"> <a href="resetpassword.jsp">Forgot password</a></span>
            </div><br>
            <div>
                <input type="submit" value="Login" name="submit">
            </div>
        </form>
        <div class="create-account">
            <small>Do not have an account?</small><br>
            <form action="./signup.jsp" method="post">
                <button type="submit">Create an account</button>
            </form>
        </div>
    </div>
    
    
</body>
<style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f2f2f2;
        }

        .content {
            max-width: 400px;
            margin: 50px auto;
            padding: 20px;
            background-color: #fff;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        form {
            margin-top: 10px;
        }

        label {
            font-weight: bold;
        }

        input[type="text"],
        input[type="password"] {
            width: 95%;
            padding: 10px;
            margin-top: 5px;
            margin-bottom: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }

        input[type="checkbox"] {
            margin-top: 10px;
        }

        input[type="submit"],
        button {
            width: 100%;
            padding: 10px;
            background-color: #4CAF50;
            color: #fff;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }

        input[type="submit"]:hover,
        button:hover {
            background-color: #45a049;
        }

        .forgot-password {
            font-size: 0.9em;
            margin-top: 5px;
        }

        .forgot-password a {
            color: blue;
            text-decoration: none;
        }

        .create-account {
            text-align: center;
            margin-top: 15px;
        }

        .create-account button {
            padding: 8px 15px;
            background-color: #337ab7;
            color: #fff;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }

        .create-account button:hover {
            background-color: #286090;
        }
    </style>
</html>
