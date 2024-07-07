<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
    if (session.getAttribute("userEmail") == null) {
        response.sendRedirect("user-login.jsp");
    }
%>

<html>

    <head>
        <title>Doctor Management Application</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
              integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
              crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css"
              integrity="sha512-iBBXm8fW90+nuLcSKlbmrPcLa0OT92xO1BIsZ+ywDWZCvqsWgccV3gFoRBv0z+8dLJgyAHIhR35VZc2oM/gI1w=="
              crossorigin="anonymous" referrerpolicy="no-referrer" />
        <style>
            body {
                background-color: #f0f0f0;
            }

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
    </head>

    <body>

        <header>
            <h2>HealthCare Management System</h2>
            <ul class="navbar-nav">
                <li><a href="<%=request.getContextPath()%>/appointments"
                       class="nav-link">
                        <i class="fas fa-arrow-left"></i>
                        back</a>
                </li>
            </ul>
        </header>
        <br>
        <div class="container col-md-8 col-lg-6">
            <div class="card">
                <div class="card-body">
                    <c:if test="${appointment != null}">
                        <form action="appointment-reshedule" method="post" />
                    </c:if>
                    <c:if test="${appointment == null}">
                        <form action="appointment-schedule" method="post">
                        </c:if>

                        <caption>
                            <h2>
                                <c:if test="${appointment != null}">
                                    Edit Doctor
                                </c:if>
                                <c:if test="${appointment == null}">
                                    Add New Doctor
                                </c:if>
                            </h2>
                        </caption>

                        <c:if test="${appointment != null}">
                            <input type="hidden" name="id" value="<c:out value='${appointment.id}' />" />
                        </c:if>

                        <fieldset class="form-group">
                            <label>Date</label> <input type="date" value="<c:out value='${appointment.time}' />"
                                                       class="form-control" name="date" required="required">
                        </fieldset>

                        <fieldset class="form-group">
                            <label>Time</label> 
                            <select type="text" value="<c:out value='${appointment.date}' />"
                                    class="form-control" name="time" required="required">

                                <option value="9:00">9:00</option>
                                <option value="10:00">10:00</option>
                                <option value="11:00">11:00</option>
                                <option value="14:00">14:00</option>
                                <option value="15:00">15:00</option>
                                <option value="16:00">16:00</option>


                            </select>
                        </fieldset>

                        <fieldset class="form-group">
                            <label>Patient Name</label>
                            <select  value="<c:out value='${appointment.patientName}' />"
                                     class="form-control" name="patientName">
                                <c:forEach var="patient" items="${listPatient}">
                                    <option value="${patient.name}">${patient.name}</option>
                                </c:forEach>

                            </select>
                        </fieldset>

                        <fieldset class="form-group">
                            <label>Patient Age</label> <input type="text" value="<c:out value='${appointment.patientAge}' />"
                                                              class="form-control" name="patientAge">
                        </fieldset>

                        <fieldset class="form-group">
                            <label>Doctor</label> 
                            <select  value="<c:out value='${appointment.doctor}' />"
                                     class="form-control" name="doctor">
                                <c:forEach var="doctor" items="${listDoctors}">
                                    <option value="${doctor.firstName} ${doctor.lastName}">${doctor.firstName} ${doctor.lastName}</option>
                                </c:forEach>

                            </select>

                        </fieldset>

                        <button type="submit" class="btn btn-success btn-block">
                            <i class="bi bi-save"></i> Save
                        </button>
                    </form>
                </div>
            </div>
        </div>
        <footer>
            Copyright &copy; 2023 health management system. All rights reserved.
        </footer>
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
                integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
                integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
        crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
                integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
        crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.17.0/font/bootstrap-icons.css"></script>
    </body>
</html>

