<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Document</title>
        <link href="css/style.css" rel="stylesheet">
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
        <style>
            @import url('https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap');
        </style>
    </head>
    <body>

        <nav class="navbar navbar-expand-lg bg-white navbar-light shadow sticky-top p-0">
        <a href="/HappyPrograming/mentorlogedinhomepage.jsp?id=${param.id}" class="navbar-brand d-flex align-items-center px-4 px-lg-5">
            <h2 class="m-0 text-primary"><i class="fa fa-book me-3"></i>eLEARNING</h2>
        </a>
        <button type="button" class="navbar-toggler me-4" data-bs-toggle="collapse" data-bs-target="#navbarCollapse">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarCollapse">
            <div class="navbar-nav ms-auto p-4 p-lg-0">
                <a href="/HappyPrograming/mentorlogedinhomepage.jsp?id=${param.id}" class="nav-item nav-link active">Home</a>
                <div class="nav-item dropdown">
                    <a href="#" class="nav-link dropdown-toggle" data-bs-toggle="dropdown">Work</a>
                    <div class="dropdown-menu fade-down m-0">
                        <a style="text-align: center; margin-left: 15px" href="/HappyPrograming/mentor/schedule/view?id=${account.id}" class="nav-item nav-link">View Schedule</a>
                        <a style="text-align: center; margin-left: 10px" href="/HappyPrograming/loadMentor?id=${account.id}" class="nav-item nav-link">Update Cv of Mentor</a>
                        <a style="text-align: center; margin-left: 10px" href="/HappyPrograming/ViewCreateCv?id=${account.id}" class="nav-item nav-link">Create Cv of Mentor</a>
                        <a style="text-align: center; margin-left: 15px" href="/HappyPrograming/listMentor?id=${param.id}" class="nav-item nav-link">List Mentor</a>
                    </div>
                </div>
            </div>
            <a href="" class="btn btn-primary py-4 px-lg-5 d-none d-lg-block">${account.fullname}</a>
        </div>
    </nav>
        <section class="container1">
            <header> Update CV of Mentor</header>
            <form action="updateMentor"  method="post" class="form" enctype="multipart/form-data">   
                <label>Avatar:</label>
                <br>
                <img class="ava" src="${mentor.ava}">
                <input hidden="oldAva" type="text" value="${mentor.ava}" name="oldAva" required/>
                <br>
                <label>Upload new avatar:</label>
                <br>
                <input type="file"  name="ava"/>
                <div class="input-box1">
                    <input hidden="mentorid" type="text" value="${mentor.id}" name="mentorid" required/>
                    <input hidden="id" type="text" value="${id}" name="id" required/>
                </div>
                <div class="input-box1">
                    <label>Full Name:</label>
                    <br>          
                    <input type="text" value="${account.fullname}" name="fullname" required/>
                </div>
                <div class="input-box1">
                    <label>Address:</label>
                    <br>          
                    <input type="text" value="${account.address}" name="address" required/>
                </div>
                <div class="input-box1">
                    <label>Phone:</label>
                    <br>          
                    <input type="text" value="${account.phone}" name="phone" required/>
                </div>
                <div class="input-box1">
                    <label>Email:</label>
                    <br>          
                    <input type="text" value="${account.email}" name="email" required/>
                </div> 
                <div class="input-box1">

                    <label for="dob">Date of Birth:</label>
                    <br>
                    <input type="date" id="dob" name="dob" required value = "${account.dob}">
                </div>

                <div class="">
                    <label>Gender:</label>
                    <c:if test="${account.gender eq 1}">
                        <input type="radio" id="male" name="gender" value="1"  checked required> Male
                        <input type="radio" id="female" name="gender" value="0"    required> Female
                        <input type="radio" id="other" name="gender" value="2"   required> Other
                    </c:if>
                    <c:if test="${account.gender eq 0}">
                        <input type="radio" id="male" name="gender" value="1"   required> Male
                        <input type="radio" id="female" name="gender" value="0"  checked  required> Female
                        <input type="radio" id="other" name="gender" value="2"  required> Other
                    </c:if>
                    <c:if test="${account.gender eq 2}">
                        <input type="radio" id="male" name="gender" value="1"   required> Male
                        <input type="radio" id="female" name="gender" value="0"   required> Female
                        <input type="radio" id="other" name="gender" value="2"  checked required> Other

                    </c:if>
                </div>     
                <div class="input-box1">
                    <label>Job:</label>
                    <br>          
                    <input type="text" value="${mentor.job}" name="job" required/>
                </div>

                <div class="input-box2">
                    <label>Introduction:</label>
                    <input type="text" value="${mentor.intro}" name="intro" required />
                </div>
                <div class="input-box2">
                    <label>Achievement:</label>
                    <input type="text" value="${mentor.achivement}" name="achivement" required />
                </div>
                <label>The skill you registered for is:</label>     
                <div class="skill-option">       
                    <c:forEach items="${skillOfMentor}" var="o">
                        <label>${o.name}</label><br>                          
                    </c:forEach>
                </div>
                <div class="skill-box">
                    <label>Do you want register skill again:</label>                   
                    <div class="skill-option">       
                        <c:forEach items="${skills}" var="o">
                            <label>${o.name}</label>
                            <input type="checkbox" name="skill" value="${o.id}" style="margin-right: 50px;">
                            <br>
                        </c:forEach>
                    </div>                   
                </div>
                <input type="submit" value="Update">
            </form>
        </section>

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
        body {
            background-repeat: no-repeat;
            background-position: center center;
            height:100vh;
            background-size: cover;
        }
        body {
            background-color: #ffff;
        }
        body{
            min-height: 100vh;

        }
        .container1{
            background: #fff;
            position: relative;
            width: 40%;
            border-radius: 8px;
            box-shadow: 0 0 15px rgb(0, 0, 0.1);
            margin-top: 30px;
            margin: 50px auto;
            padding: 20px;

        }
        .container1 header{
            font-size: 1.5rem;
            color: #333;
            font-weight: 500;
            text-align: center;
            margin-top: 50px;
        }
        .container1 .form{
            margin-top: 30px;
        }
        .form .input-box0{
            width: 100%;
            margin-top: 20px;
        }
        .form .input-box0 input{
            position: relative;
            height: 50px;
            width:35%;
            outline: auto;
            border: 2px solid #ddd;
            border-radius: 6px;
            padding: 0;
            font-size: 1rem;
            color: #707070;
            margin-top: 8px;
        }
        .form .input-box1{
            width: 100%;
            margin-top: 20px;
        }
        .form .input-box1 input{
            position: relative;
            height: 50px;
            width: 50%;
            outline: auto;
            border: 2px solid #ddd;
            border-radius: 6px;
            padding: 0;
            font-size: 1rem;
            color: #707070;
            margin-top: 8px;
        }
        .form .input-box2{
            width: 100%;
            margin-top: 20px;
        }
        .input-box label{
            color:#333;
        }
        .form .input-box2 input{
            position: relative;
            height: 50px;
            width: 100%;
            outline: auto;
            border: 2px solid #ddd;
            border-radius: 6px;
            padding: 0;
            font-size: 1rem;
            color: #707070;
            margin-top: 8px;
        }
        .form .column{
            display: flex;
            column-gap: 15px;


        }
        label {
            font-weight: bold;
        }
        .form button{
            height:55px;
            width: 20%;
            background-color: rgb(130, 106, 251);
            font-size: 1rem;
            color:#fff;
            border:none;
            margin-top: 10px;
            cursor: pointer;
            border-radius: 60px;
            font-weight: 400;
        }
        .ava {
            width: 300px;
            height: 300px;
            border-radius: 40px;
        }
    </style>
</html>






