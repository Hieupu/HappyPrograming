<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>List Skill</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                margin: 0;
                padding: 0;
                background-color: #f9f9f9;
            }

            h1 {
                text-align: center;
                margin-top: 50px;
            }

            table {
                width: 80%;
                margin: 20px auto;
                border-collapse: collapse;
                border: 1px solid #ccc;
            }

            th, td {
                padding: 10px;
                border: 1px solid #ccc;
                text-align: left;
            }

            th {
                background-color: #f2f2f2;
                font-weight: bold;
            }

            td button {
                padding: 5px 10px;
                background-color: #4CAF50;
                color: #fff;
                border: none;
                border-radius: 3px;
                cursor: pointer;
                transition: background-color 0.3s ease;
            }

            td button:hover {
                background-color: #45a049;
            }

            .add-skill-button {
                display: block;
                margin: 20px auto;
                padding: 10px 20px;
                background-color: #4CAF50;
                color: #fff;
                text-align: center;
                text-decoration: none;
                border-radius: 5px;
                transition: background-color 0.3s ease;
                width: 100px;
            }

            .add-skill-button:hover {
                background-color: #45a049;
            }
        </style>
        <script>
            function confirmDelete() {
                return confirm("Are you sure you want to delete this skill?");
            }

            function updateStatus(id, name, status) {
                var form = document.createElement("form");
                form.method = "POST";
                form.action = "updateskill";

                var idField = document.createElement("input");
                idField.type = "hidden";
                idField.name = "id";
                idField.value = id;
                form.appendChild(idField);

                var nameField = document.createElement("input");
                nameField.type = "hidden";
                nameField.name = "name";
                nameField.value = name;
                form.appendChild(nameField);

                var statusField = document.createElement("input");
                statusField.type = "hidden";
                statusField.name = "status";
                statusField.value = status;
                form.appendChild(statusField);

                document.body.appendChild(form);
                form.submit();
            }
        </script>
    </head>
    <body>
        <h1>List of Skills</h1>
        <table>
            <thead>
                <tr>
                    <th>STT</th>
                    <th>ID</th>
                    <th>Name</th>
                    <th style="width: 100px">Status</th>
                    <th>Update</th>
                    <th>Delete</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="s" items="${skills}" varStatus="status">
                    <tr>
                        <td>${status.index + 1}</td>
                        <td>${s.id}</td>
                        <td>${s.name}</td>
                        <td>
                            <c:choose>
                                <c:when test="${s.status == 1}">
                                    <div style="display: flex">
                                        <button type="submit">Active</button>

                                        <form style="margin-left: 20px"  action="updateskill" method="post">
                                            <input type="hidden" name="id" value="${s.id}"/>
                                            <input type="hidden" name="name" value="${s.name}"/>
                                            <input type="hidden" name="status" value="0"/>
                                            <button style="background-color: red" type="submit">Inactive</button>
                                        </form>
                                    </div>
                                </c:when>
                                <c:otherwise>
                                    <div style="display: flex">
                                        <form style="margin-right: 20px" action="updateskill" method="post">
                                            <input type="hidden" name="id" value="${s.id}"/>
                                            <input type="hidden" name="name" value="${s.name}"/>
                                            <input type="hidden" name="status" value="1"/>
                                            <button style="background-color: red"  type="submit">Active</button>
                                        </form>
                                        <button type="submit">Inactive</button>
                                    </div>
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td>
                            <form action="updateskill" method="get">
                                <input type="hidden" name="id" value="${s.id}">
                                <button type="submit">Update</button>
                            </form>
                        </td>
                        <td>
                            <form action="deleteskill" method="post" onsubmit="return confirmDelete();">
                                <input type="hidden" name="id" value="${s.id}">
                                <button type="submit">Delete</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <a class="add-skill-button" href="../skill/addskill.jsp">Add Skill</a>
    </body>
</html>
