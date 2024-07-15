<!DOCTYPE html>
<html>
    <head>
        <title>Verify Account</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f9f9f9;
                margin: 0;
                padding: 0;
            }

            h1 {
                text-align: center;
                margin-top: 50px;
            }

            form {
                max-width: 400px;
                margin: 0 auto;
                background-color: #fff;
                padding: 20px;
                border-radius: 5px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            }

            label {
                display: block;
                margin-bottom: 10px;
                font-weight: bold;
            }

            input[type="text"] {
                width: 100%;
                padding: 10px;
                border: 1px solid #ccc;
                border-radius: 5px;
                margin-bottom: 20px;
                box-sizing: border-box; /* Ensure padding and border are included in width */
            }

            button[type="submit"] {
                padding: 10px 20px;
                background-color: #4CAF50;
                color: #fff;
                border: none;
                border-radius: 5px;
                cursor: pointer;
            }

            button[type="submit"]:hover {
                background-color: #45a049;
            }
        </style>
    </head>
    <body>
        <h1>Verify Your Account</h1>
        <form action="verify" method="post" enctype="multipart/form-data">
            <input type="hidden" name="email" value="${email}">
            <input type="hidden" name="username" value="${username}">
            <input type="hidden" name="password" value="${password}">
            <input type="hidden" name="role" value="${role}">
            <input type="hidden" name="phone" value="${phone}">
            <input type="hidden" name="address" value="${address}">
            <input type="hidden" name="dob" value="${dateOfBirth}">
            <input type="hidden" name="gender" value="${gender}">
            <input type="hidden" name="fullname" value="${fullname}">
            <input type="hidden" name="ava" value="${ava}">

            <label for="code">Verification Code:</label>
            <input type="text" id="code" name="code" required>
            <button type="submit">Verify</button>
        </form>
    </body>
</html>
