<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Mentor Timetable</title>
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
    <link href="${pageContext.request.contextPath}/lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">

    <!-- Customized Bootstrap Stylesheet -->
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">

    <!-- Template Stylesheet -->
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">

    <!-- Custom Stylesheet -->
    <style>
        .table-container {
            margin: 30px auto;
            width: 80%;
            display: flex;
            justify-content: space-between;
        }
        table {
            width: 100%;
            border-collapse: collapse;
        }
        th, td {
            text-align: center;
            padding: 0; /* Remove default padding */
            border: 1px solid #ddd;
            height: 75px;
        }
        th {
            background-color: #f2f2f2;
        }
        tr:nth-child(even) {
            background-color: #f9f9f9;
        }
        tr:hover {
            background-color: #f1f1f1;
        }
        .navbar {
            margin-bottom: 20px;
        }
        button {
            width: 100%;
            height: 100%;
            border: none;
            background-color: transparent;
            padding: 0; /* Remove default padding */
        }
        button:hover {
            background-color: #e0e0e0; /* Optional: Add a hover effect */
        }
        .slot-selected {
            background-color: #28a745; /* Màu xanh */
        }
        .success-message {
            position: fixed;
            top: 10px;
            left: 50%;
            transform: translateX(-50%);
            background-color: #28a745;
            color: white;
            padding: 10px 20px;
            border-radius: 5px;
            display: none;
        }
        .modal-footer {
            display: flex;
            justify-content: space-between;
        }
        .modal-footer button {
            width: 48%;
        }
    </style>

    <!-- JavaScript Function -->
    <script>
        function sentURL() {
            var id = document.getElementById('mentorId').value;
            var date = document.querySelector('input[name="today"]').value;
            var url = 'view?id=' + id + '&date=' + date;
            window.location.href = url;
        }

        function sentURL2(slot, date, day) {
            var id = document.getElementById('mentorId').value;
            var slotChar = String.fromCharCode(64 + slot);
            var url = 'update?id=' + id + '&date=' + date + '&slotid=' + slotChar + day;

            var confirmationPopup = new bootstrap.Modal(document.getElementById('confirmationPopup'));
            confirmationPopup.show();

            document.getElementById('confirmAction').addEventListener('click', function () {
                confirmationPopup.hide();

                window.location.href = url;
            });
        }

        function sentURL3(slot, day) {
            var id = document.getElementById('mentorId').value;
            var slotChar = String.fromCharCode(64 + slot);
            var url = 'delete?id=' + id + '&slotid=' + slotChar + day;

            var confirmationPopup = new bootstrap.Modal(document.getElementById('confirmationPopup2'));
            confirmationPopup.show();

            document.getElementById('confirmAction2').addEventListener('click', function () {
                confirmationPopup.hide();

                window.location.href = url;
            });
        }
    </script>
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
        
        
        <h1 style="text-align: center">Mentor's Schedule</h1>    
    <div class="table-container">
        <input type="hidden" id="mentorId" name="id" value="${param.id}"/>
        <input style="width: 170px; height: 50px" type="date" name="today" value="${gettoday}" class="form-control mb-3" onchange="sentURL()"/>

        <table style="margin-left: 15%">
            <thead>
                <tr>
                    <th>Slot</th>
                    <c:forEach var="index" begin="0" end="${fn:length(dates) - 1}">
                        <th>
                            ${dates[index]}<br>
                            ${dayList[index]}
                        </th>
                    </c:forEach>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="hour" begin="1" end="6">
                    <tr>
                        <td>Slot ${hour}</td>
                        <c:forEach var="index" begin="0" end="${fn:length(dates) - 1}">
                            <c:set var="slotExists" value="false" />
                            <c:forEach var="slot" items="${mentorslot}">
                                <c:if test="${slot.slotid == hour && slot.date == dates[index]}">
                                    <c:set var="slotExists" value="true" />
                                </c:if>
                            </c:forEach>
                            <c:choose>
                                <c:when test="${slotExists}">
                                    <td class="slot-selected">
                                        <button style="height: 70px; background-color: #28a745" onclick="sentURL3(${hour}, (${index} + 2))"></button>
                                    </td>
                                </c:when>
                                <c:otherwise>
                                    <td>
                                        <button type="button" class="btn btn-outline-primary" onclick="sentURL2(${hour}, '${dates[index]}', (${index} + 2))">&#43;</button>
                                    </td>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
    <!-- Popup -->
    <div id="confirmationPopup" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="confirmationModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="confirmationModalLabel"></h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    Bạn có muốn set lịch rảnh?
                </div>
                <div class="modal-footer">
                    <button id="confirmAction" type="button" class="btn btn-primary">Có</button>
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Không</button>
                </div>
            </div>
        </div>
    </div>
    
    <div id="confirmationPopup2" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="confirmationModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="confirmationModalLabel"></h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    Bạn có muốn xóa lịch rảnh?
                </div>
                <div class="modal-footer">
                    <button id="confirmAction2" type="button" class="btn btn-primary">Có</button>
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Không</button>
                </div>
            </div>
        </div>
    </div>

    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
