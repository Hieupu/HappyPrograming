<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title> View Cv</title>
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
        <div class="container">
            <div class="table-wrapper">

            </div>
        </div>
        <div class="container1">
            <div class="table-title">
                <div class="row">
                    <div class="col-sm-6">
                        <h2 ><b>Cv Mentor</b></h2>
                    </div>
                </div>
                <div class="header">
                    <div>
                        <img class="ava" src="${ma.ava}">
                    </div>
                    <br>
                    <div class="full-name">
                        <span class="last-name">${ma.fullName}</span>
                    </div>
                    <div class="contact-info">
                        <span class="email">Email: </span>
                        <span class="email-val">${ma.mail}</span>
                        <span class="separator"></span>
                        <span class="phone">Phone: </span>
                        <span class="phone-val">${ma.phone}</span>
                    </div>

                    <div class="about">
                        <span class="phone">Address: </span>
                        <span class="phone-val">${ma.address}</span>
                    </div>
                    <div class="about">
                        <span class="phone">date of birth: </span>
                        <span class="phone-val">${ma.dob}</span>
                    </div>
                    <div class="about">
                        <span class="phone">Job: </span>
                        <span class="phone-val">${ma.job}</span>
                    </div>
                </div>
                <div class="details">
                    <div class="section">
                        <div class="section__title">Introduction</div>
                        <div>${ma.intro}</div>
                    </div>
                    <div class="section">
                        <div class="section__title">Achivement</div>
                        <div>
                            <p>
                                ${ma.achivement}
                            </p>
                        </div>
                    </div>
                    <div class="section">
                        <div class="section__title">Skills</div>
                        <div class="section__list">
                            <table>
                                <thead>
                                <th>Skill</th>
                                <th class="rate">Rating and Comments</th>
                                </thead>
                                <tbody>
                                    <c:forEach items="${listSkill}" var="o">
                                        <tr>
                                            <td>
                                                <h1>${o.name}</h1>
                                            </td>
                                            <td class = "rate"> <img class="rate" src="image/rate.png" alt="View Information" style="margin-right: 100px;"> </a> <br> 
                                                                                                            
                                            </td>
                                        </tr>  
                                    </c:forEach>

                                </tbody>
                            </table>

                        </div>
                    </div>

                </div>
            </div>
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

        .rate {

            margin-left: 100px;

            width: 30px;
            height: 30px;
        }

        .ava {
            width: 300px;
            height: 300px;
            border-radius: 40px;
        }

        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        html {
            height: 100%;
        }

        body {
            min-height: 100%;
            background: #eee;
            font-family: 'Lato', sans-serif;
            font-weight: 400;
            color: #222;
            font-size: 14px;
            line-height: 26px;
            padding-bottom: 0px;
        }

        .container1 {
            max-width: 700px;
            background: #fff;
            margin: 0px auto 0px;
            box-shadow: 1px 1px 2px #DAD7D7;
            border-radius: 3px;
            padding: 40px;
            margin-top: 50px;
        }

        .header {
            margin-bottom: 30px;

            .full-name {
                font-size: 40px;
                text-transform: uppercase;
                margin-bottom: 5px;
            }

            .first-name {
                font-weight: 700;
            }

            .last-name {
                font-weight: 300;
            }

            .contact-info {
                margin-bottom: 20px;
            }

            .email,
            .phone {
                color: #999;
                font-weight: 300;
            }

            .separator {
                height: 10px;
                display: inline-block;
                border-left: 2px solid #999;
                margin: 0px 10px;
            }

            .position {
                font-weight: bold;
                display: inline-block;
                margin-right: 10px;
                text-decoration: underline;
            }
        }


        .details {
            line-height: 20px;

            .section {
                margin-bottom: 40px;
            }

            .section:last-of-type {
                margin-bottom: 0px;
            }

            .section__title {
                letter-spacing: 2px;
                color: #54AFE4;
                font-weight: bold;
                margin-bottom: 10px;
                text-transform: uppercase;
            }

            .section__list-item {
                margin-bottom: 40px;
            }

            .section__list-item:last-of-type {
                margin-bottom: 0;
            }

            .left,
            .right {
                vertical-align: top;
                display: inline-block;
            }

            .left {
                width: 60%;
            }

            .right {
                text-align: right;
                width: 39%;
            }

            .name {
                font-weight: bold;
            }

            a {
                text-decoration: none;
                color: #000;
                font-style: italic;
            }

            a:hover {
                text-decoration: underline;
                color: #000;
            }

            .skills {
            }

            .skills__item {
                margin-bottom: 10px;
            }

            .skills__item .right {
                input {
                    display: none;
                }

                label {
                    display: inline-block;
                    width: 20px;
                    height: 20px;
                    background: #C3DEF3;
                    border-radius: 20px;
                    margin-right: 3px;
                }

                input:checked+label {
                    background: #79A9CE;
                }
            }
        }
    </style>
</html>