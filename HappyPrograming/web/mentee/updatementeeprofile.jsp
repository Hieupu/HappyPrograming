<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <title>eLEARNING - eLearning HTML Template</title>
        <meta content="width=device-width, initial-scale=1.0" name="viewport">
        <meta content="" name="keywords">
        <meta content="" name="description">

        <!-- Favicon -->
        <link href="img/favicon.ico" rel="icon">

        <!-- Google Web Fonts -->
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Heebo:wght@400;500;600&family=Nunito:wght@600;700;800&display=swap" rel="stylesheet">

        <!-- Icon Font Stylesheet -->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">

        <!-- Libraries Stylesheet -->
        <link href="lib/animate/animate.min.css" rel="stylesheet">
        <link href="lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">

        <!-- Customized Bootstrap Stylesheet -->
        <link href="css/bootstrap.min.css" rel="stylesheet">

        <!-- Template Stylesheet -->
        <link href="css/style.css" rel="stylesheet">
    </head>
    <body>
         <nav class="navbar navbar-expand-lg bg-white navbar-light shadow sticky-top p-0">
            <a href="/HappyPrograming/menteelogedinhomepage.jsp?id=${param.id}" class="navbar-brand d-flex align-items-center px-4 px-lg-5">
                <h2 class="m-0 text-primary"><i class="fa fa-book me-3"></i>eLEARNING</h2>
            </a>
            <button type="button" class="navbar-toggler me-4" data-bs-toggle="collapse" data-bs-target="#navbarCollapse">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarCollapse">
                <div class="navbar-nav ms-auto p-4 p-lg-0">
                    <a href="/HappyPrograming/menteelogedinhomepage.jsp?id=${param.id}" class="nav-item nav-link active">Home</a>

                    <div class="nav-item dropdown">
                        <a href="#" class="nav-link dropdown-toggle" data-bs-toggle="dropdown">Work</a>
                        <div class="dropdown-menu fade-down m-0">
                            <a style="margin-left: 30px" href="/HappyPrograming/listmentor?id=${param.id}" class="nav-item nav-link">List Mentor</a>
                            <a style="margin-left: 50px" href="listskill" class="nav-item nav-link">Skills</a>
                            <a style="margin-left: 30px" href="/HappyPrograming/mentee/test.jsp?id=${param.id}" class="nav-item nav-link">List Request</a>
                        </div>
                    </div>
                </div>
                <a href="/HappyPrograming/mentee/viewmentee?id=${param.id}" class="btn btn-primary py-4 px-lg-5 d-none d-lg-block">${account.fullname}</a>
            </div>
        </nav>
        <div class="container">                   
            <a href="/HappyPrograming/auth/changepassword.jsp?id=${param.id}"><button type="button">Change Password</button></a>
            <h2>Mentee Profile</h2>                   
            <form action="menteeprofile" method="get">
                <input type="hidden" name="id" value="${param.id}"/>
                <label for="avatar">Avatar:</label>
                <img src="uploadsMentee/${ava}" alt="${ava}">
                <br>
                <label for="fullName">Full Name:</label>
                <input type="text" id="fullName" name="fullName" value="${acc.fullname}">

                <label for="dob">Date of Birth:</label>
                <input type="date" id="dob" name="dob" value="${acc.dob}">

                <label for="fullName">Phone</label>
                <input type="text" id="phone" name="phone" value="${acc.phone}">

                <label for="email">Email:</label>
                <input type="email" id="email" name="email" value="${acc.email}" readonly>

                <div style="display: flex">
                    <label>Gender:</label>
                    <c:if test="${acc.gender eq 1}">
                        <label for="male">Male</label>
                    </c:if>
                    <c:if test="${acc.gender eq 0}">
                        <label for="female">Female</label>
                    </c:if>
                    <c:if test="${acc.gender eq 2}">
                        <label for="other">Other</label>
                    </c:if>
                </div>
                <label for="address">Address:</label>
                <input type="text" id="address" name="address" value="${acc.address}">

                <input type="submit" value="Update">
            </form>
        </div>
        <div class="container-fluid bg-dark text-light footer pt-5 mt-5 wow fadeIn" data-wow-delay="0.1s">
        <div class="container py-5">
            <div class="row g-5">
                <div class="col-lg-6 col-md-12">
                    <h4 class="text-white mb-3">Quick Link</h4>
                    <a class="btn btn-link" href="">About Us</a>
                    <a class="btn btn-link" href="">Contact Us</a>
                    <a class="btn btn-link" href="">Privacy Policy</a>
                    <a class="btn btn-link" href="">Terms & Condition</a>
                    <a class="btn btn-link" href="">FAQs & Help</a>
                </div>
                <div class="col-lg-6 col-md-12">
                    <h4 class="text-white mb-3">Contact</h4>
                    <p class="mb-2"><i class="fa fa-map-marker-alt me-3"></i>123 Street, New York, USA</p>
                    <p class="mb-2"><i class="fa fa-phone-alt me-3"></i>+012 345 67890</p>
                    <p class="mb-2"><i class="fa fa-envelope me-3"></i>info@example.com</p>                
                </div>             
            </div>
        </div>     
    </div>
    </body>
    <style>
/*        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f9f9f9;
        }*/
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
            margin-bottom: 20px;
        }
        form {
            display: flex;
            flex-direction: column;
        }
        label, input {
            margin-bottom: 15px;
        }
        input[type="text"],
        input[type="email"],
        input[type="date"],
        select {
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }
        input[type="file"] {
            margin-bottom: 20px;
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
        img{
            height: 200px;
            width: 200px;
        }
    </style>
</html>
