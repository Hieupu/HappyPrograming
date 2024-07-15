<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="style.css">
        <title>List Mentor</title>
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
        <div class="table">
            <div class="table_section">
                <h2>List Mentor Suggestion for ${id} Course</h2>
                <table>
                    <caption></caption>
                    <thead>
                        <tr>
                            <th style = " color:#06BBCC">Mentor Name</th>
                            <th style = " color:#06BBCC">Image</th>
                            <th style = " color:#06BBCC">Rating</th>
                            <th style = " color:#06BBCC">Numbers of request</th>
                            <th style = " color:#06BBCC">View Profile</th>
                            <th style = " color:#06BBCC">Invite</th>

                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${listMentor}" var="o">
                            <tr>
                                <td>${o.name}</td>
                                <td><img class="ava" src="${o.ava}"></td>
                                <td><button>Rating</button></td>
                                <td>Number of request</td>
                                <td>
                                    <a  href="ViewInforOfMentor?id=${o.id}">
                                        <img class="view" src="image/viewIcon2.png" alt="View Information">
                                    </a>
                                </td>
                                <td><button>Invite</button></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>            
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
        th, td{
            border-bottom: 1px solid #dddddd;
            padding: 10px 20px;
            word-break: break-all;
            text-align: center;
        }
        .ava{
            height: 100px;
            width: 100px;
            object-fit: cover;
            border-radius:15px;
            border: 5px solid #ced0d2;
        }

        .table_section {
            max-height: 500px;
            overflow-y: auto;

            table {
                width: 100%;
                table-layout: fixed;
                border-collapse: collapse;
            }

            th,td {
                border-bottom: 1px solid #dddddd;
                padding: 10px 20px;
                word-break: break-all;
                text-align: center;
                max-width: 200px;
                white-space: nowrap;
                overflow: hidden;
                text-overflow: ellipsis;
            }
        </style>

    </html>