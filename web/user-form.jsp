<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
    if (session.getAttribute("userEmail") == null) {
        response.sendRedirect("user-login.jsp");
    }
%>

<html>
    <head>
        <title>User Management Application</title>

        <style>

            body {
                font-family: Arial, sans-serif;
                background-color: #f2f2f2;
                margin: 0;
                padding: 0;
                box-sizing: border-box;
            }

            header {
                background-color: #3399ff;
                padding: 15px;
                text-align: center;
                color: white;
            }

            .navbar-brand {
                color: #fff;
                font-size: 1.5rem;
            }

            .navbar-nav .nav-link {
                color: #fff;
                font-size: 20px;
                position: absolute;
                left: 30px;
                top: 10px;
            }

            .container {
                margin-top: 20px;
            }

            .card {
                border: none;
                box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
            }

            .card-body {
                padding: 30px;
            }

            h2 {
                color: #fff;
            }

            label {
                font-weight: bold;
            }

            input {
                margin-bottom: 10px;
            }

            button.btn-success {
                background-color: #28a745;
                border: none;
            }

            footer {
                background-color: #343a40;
                color: #fff;
                padding: 10px 0;
                text-align: center;
                position: fixed;
                bottom: 0;
                width: 100%;
            }
        </style>
        <link rel="stylesheet"
              href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
              integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
              crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css"
              integrity="sha512-iBBXm8fW90+nuLcSKlbmrPcLa0OT92xO1BIsZ+ywDWZCvqsWgccV3gFoRBv0z+8dLJgyAHIhR35VZc2oM/gI1w=="
              crossorigin="anonymous" referrerpolicy="no-referrer" />
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" integrity="sha384-NpXZmZLY6AA4ISouM6UAMnLRn4HehEnpkGjJXIXNq7sEiKA3Q6l2L/tv6tT7lHbe" crossorigin="anonymous">
    </head>
    <body>

        <header>
            <h2>HealthCare Management System</h2>
            <ul class="navbar-nav">
                <li><a href="<%=request.getContextPath()%>/list"
                       class="nav-link">
                        <i class="fas fa-arrow-left"></i>
                        back</a>
                </li>
            </ul>
        </header>
        <br>
        <div class="container col-md-5">
            <div class="card">
                <div class="card-body">
                    <c:if test="${user != null}">
                        <form action="update" method="post">
                        </c:if>
                        <c:if test="${user == null}">
                            <form action="insert" method="post">
                            </c:if>

                            <caption>
                                <h2>
                                    <c:if test="${user != null}">
                                        Edit Patient Information
                                    </c:if>
                                    <c:if test="${user == null}">
                                        Add New Patient
                                    </c:if>
                                </h2>
                            </caption>

                            <c:if test="${user != null}">
                                <input type="hidden" name="id" value="<c:out value='${user.id}' />" />
                            </c:if>

                            <fieldset class="form-group">
                                <label>Patient Name</label> <input type="text"
                                                                   value="<c:out value='${user.name}' />" class="form-control"
                                                                   name="name" required="required">
                            </fieldset>

                            <fieldset class="form-group">
                                <label>Patient Email</label> <input type="email"
                                                                    value="<c:out value='${user.email}' />" class="form-control"
                                                                    name="email">
                            </fieldset>

                            <fieldset class="form-group">
                                <label>Patient Gender</label> 
                                <select value="<c:out value='${user.gender}' />" class="form-control" name="gender">
                                    <option value="Male">Male</option>
                                    <option value="Female">Female</option>
                                </select>
                            </fieldset>

                            <fieldset class="form-group">
                                <label>Patient Age</label> <input type="number"
                                                                  value="<c:out value='${user.age}' />" class="form-control"
                                                                  name="age">
                            </fieldset>

                            <fieldset class="form-group">
                                <label>Phone Number</label> <input type="number"
                                                                   value="<c:out value='${user.phoneNumber}' />" class="form-control"
                                                                   name="phoneNumber">
                            </fieldset>

                            <fieldset class="form-group">
                                <label>Blood Group</label> <input type="text"
                                                                  value="<c:out value='${user.bloodGp}' />" class="form-control"
                                                                  name="bloodGp">
                            </fieldset>

                            <button type="submit" class="btn btn-success btn-block">
                                Save
                            </button>
                        </form>
                </div>
            </div>
        </div>
        <footer>
            Copyright &copy; 2023 health management system. All rights reserved.
        </footer>
    </body>
</html>

