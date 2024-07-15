<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <title>List Request</title>
        <meta content="width=device-width, initial-scale=1.0" name="viewport">
        <meta content="" name="keywords">
        <meta content="" name="description">

        <!-- Favicon -->
        <link href="${pageContext.request.contextPath}/img/favicon.ico" rel="icon">

        <!-- Google Web Fonts -->
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Heebo:wght@400;500;600&family=Nunito:wght@600;700;800&display=swap" rel="stylesheet">

        <!-- Icon Font Stylesheet -->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">

        <!-- Libraries Stylesheet -->
        <link href="${pageContext.request.contextPath}/lib/animate/animate.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/ib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">

        <!-- Customized Bootstrap Stylesheet -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">

        <!-- Template Stylesheet -->
        <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">
        <style>
            body {
                margin: 0;
                background-color: #f9f9f9;
            }
            table {
                width: 100%;
                border-collapse: collapse;
                margin: 20px;
            }
            table, th, td {
                border: 1px solid #ccc;
            }
            th, td {
                padding: 8px;
                text-align: left;
            }
        </style>
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
        <h1>Requests of Mentee: </h1>
        <table>
            <thead>
                <tr>
                    <th style="text-align: center">STT</th>
                    <th style="width: 330px; text-align: center">Title</th>
                    <th style="text-align: center">Fee</th>
                    <th style="width: 200px; text-align: center">Deadline</th>
                    <th style="width: 75px; text-align: center">Status</th>
                    <th style="width: 330px; text-align: center">Detail</th>
                    <th></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="request" items="${requests}" varStatus="status">
                    <tr>
                        <td style="text-align: center">${status.index + 1}</td>
                        <td style="text-align: center">${request.title}</td>
                        <td style="text-align: center">${request.fee}</td>
                        <td style="text-align: center">${request.time}</td>
                        <td style="text-align: center">${request.status}</td>
                        <td style="text-align: center">${request.detail}</td>
                        <td style="display: flex; justify-content: space-around">
                            <c:choose>
                                <c:when test="${request.status eq 'Open'}">
                                    <form action="updaterequest" method="get">
                                        <input type="hidden" name="menteeid" value="${param.id}">
                                        <input type="hidden" name="id" value="${request.id}">
                                        <input type="hidden" name="mentorid" value="${request.mentorid}"/>
                                        <input type="hidden" name="detail" value="${request.detail}"/>
                                        <input type="hidden" name="title" value="${request.title}">
                                        <input type="hidden" name="schedule" value="${request.sche}">
                                        <input type="submit" value="Update">
                                    </form>
                                    <form action="mentee/deleterequest" method="post" onsubmit="return confirm('Are you sure you want to delete this request?');">
                                        <input type="hidden" name="id" value="${request.id}">
                                        <input type="submit" value="Delete">
                                    </form>
                                </c:when>
                                <c:otherwise>
                                    <form action="" method="post">
                                        <input type="hidden" name="menteeid" value="${param.id}">
                                        <input type="hidden" name="id" value="${request.id}">
                                        <input type="hidden" name="title" value="${request.title}">
                                        <input type="hidden" name="schedule" value="${request.sche}">
                                        <input type="submit" value="Rebook">
                                    </form>
                                    <form action="mentee/deleterequest" method="post" onsubmit="return confirm('Are you sure you want to delete this request?');">
                                        <input type="hidden" name="id" value="${request.id}">
                                        <input type="submit" value="Delete">
                                    </form>
                                </c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </body>
</html>
