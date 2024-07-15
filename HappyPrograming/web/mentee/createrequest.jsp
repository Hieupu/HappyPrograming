<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Create Request</title>
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

    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            background-color: #f9f9f9;
        }
        form {
            width: auto;
            margin: auto;
            padding: 20px;
            background-color: #fff;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        label {
            font-weight: bold;
        }
        input[type="text"], input[type="checkbox"] {
            width: 95%;
            padding: 10px;
            margin: 10px 0 20px 0;
            border: 1px solid #ccc;
            border-radius: 5px;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        table, th, td {
            border: 1px solid #ccc;
        }
        th, td {
            padding: 8px;
            text-align: center;
        }
        .button-container {
            text-align: center;
            margin-top: 100px;
        }
        .button-container input[type="submit"] {
            width: 100px;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
            background-color: #4CAF50;
            color: white;
            cursor: pointer;
        }
        .button-container input[type="submit"]:hover {
            background-color: #45a049;
        }
        #fee {
            font-weight: bold;
            margin-top: 20px;
        }
        .slot-selected {
            background-color: #28a745;
        }
    </style>

    <script>
        function setLocalTime() {
            var now = new Date();
            var offset = now.getTimezoneOffset() * 60000;
            var localTime = new Date(now.getTime() - offset);
            var isoString = localTime.toISOString();
            document.getElementById("localTime").value = isoString;
        }

        function validateForm() {
            var checkboxes = document.querySelectorAll('input[name="skills"]:checked');
            if (checkboxes.length < 1) {
                alert("Please select at least 1 skill.");
                return false;
            }
            setLocalTime();
            return true;
        }

        function updateFee() {
            var fee = 0;
            var skills = document.querySelectorAll('input[name="skills"]:checked');
            var slots = document.querySelectorAll('input[name^="slot_"]:checked');

            fee += skills.length * 100;
            fee += slots.length * 50;

            document.getElementById("fee").innerText = "Fee: $" + fee;
            document.getElementById("feeInput").value = fee;
        }

        document.addEventListener("DOMContentLoaded", function () {
            var checkboxes = document.querySelectorAll('input[type="checkbox"]');
            checkboxes.forEach(function (checkbox) {
                checkbox.addEventListener("change", updateFee);
            });
            updateFee(); // Initial calculation
        });

        function convert() {
            var feeLabelText = document.getElementById("fee").innerText;
            var feeValue = feeLabelText.split(": $")[1];
            document.getElementById("feeInput").value = feeValue;
        }
    </script>
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
    <h1 style="text-align: center">Create Request</h1>
    <form action="createrequest" method="post" onsubmit="return validateForm()">
        <label for="title">Title:</label>
        <input type="text" id="title" name="title" required>

        <label for="skills">Skills:</label>
        <div>
            <c:forEach var="c" items="${skills1}">
                <input type="radio" name="skills" value="${c.name}"> ${c.name}<br>
            </c:forEach>
        </div><br>

        <label for="mentor">Mentor:</label>
        <input type="text" id="mentor" name="mentor" value="${param.fullname}" readonly required/>
        <input type="hidden" name="mentorid" value="${param.mentorid}"/>
        <input type="hidden" name="menteeid" value="${param.id}"/>
        <br><br>
        <label for="schedule">Schedule:</label>
        <input type="hidden" id="mentorId" name="id" value="${param.id}"/>
        <input style="width: 170px; height: 50px" type="hidden" name="today" value="${gettoday}" class="form-control mb-3" onchange="sentURL()"/>

        <table>
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
                                        <input type="checkbox" name="slot_${hour}_${dates[index]}" value="${hour}_${dates[index]}" />
                                <input type="hidden" name="slot_${hour}_${dates[index]}_slot" value="${hour}" />
                                <input type="hidden" name="slot_${hour}_${dates[index]}_day" value="${index+2}" />
                                <input type="hidden" name="slot_${hour}_${dates[index]}_date" value="${dates[index]}" />
                                    </td>
                                </c:when>
                                <c:otherwise>
                                    <td></td>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <br>
        <label>Detail: </label>
        <input type="text" name="detail" required=""/>
        <br>
        <label id="fee">Fee: $0</label>
        <input type="hidden" name="fee" id="feeInput"/>
        <br><br>
        <input type="hidden" id="localTime" name="localtime"/>
        <div class="button-container">
            <input type="submit" value="OK">
        </div>
    </form>
</body>
</html>
