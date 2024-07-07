<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
    if (session.getAttribute("userEmail") == null) {
        response.sendRedirect("user-login.jsp");
    }
%>

<html>

    <head>
        <title>Doctor Management</title>
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

            .container {
                margin-left: 250px;
                padding: 20px;
                background-color: white;
                border-radius: 8px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
                display: flex;
            }

            table {
                width: 100%;
                border-collapse: collapse;
                margin-top: 20px;
            }

            th, td {
                padding: 12px;
                border: 1px solid #ddd;
                text-align: left;
            }

            th {
                background-color: #3399ff;
                color: white;
            }

            tr:nth-child(even) {
                background-color: #f9f9f9;
            }

            tr:hover {
                background-color: #f0f0f0;
            }

            button {
                background-color: #4CAF50;
                color: white;
                padding: 10px 15px;
                border: none;
                border-radius: 4px;
                cursor: pointer;
            }

            .btn-primary {
                background-color: #4CAF50;
                color: white;
                border: none;
                border-radius: 4px;
                cursor: pointer;
            }

            .btn-danger {
                background-color: #e65f47;
            }
            button a {
                color: white;
                padding: 5px;
                text-decoration: none;
                margin-bottom: 5px;
                border-radius: 2px;
                transition: background-color 0.3s;
            }

            .btn-primary :hover {
                background-color: #45a049;
            }
            button :hover {
                background-color: #45a049;
            }

            .btn-danger :hover {
                background-color: #e85035;
            }

            /*            .sidebar {
                            width: 200px;
                            background-color: #333;
                            padding: 20px;
                            color: white;
                        }
            
                        .sidebar a {
                            display: block;
                            color: white;
                            padding: 10px;
                            text-decoration: none;
                            margin-bottom: 10px;
                            border-radius: 4px;
                            transition: background-color 0.3s;
                        }
            
            
                        .sidebar a:hover {
                            background-color: #555;
                        }*/
            .sidebar {
                position: fixed;
                width: 250px;
                height: 100%;
                background: #e9f5f3;
                border-right: 1px solid #069687;
                transition: 0.5s;
                overflow: hidden;
            }

            .sidebar ul {

                margin-top: 100px;
                left: 0;
                width: 100%;
                list-style: none;

            }

            .sidebar ul li {
                position: relative;
                width: 100%;
                padding: 10px;
                text-decoration: none;
            }

            .sidebar ul li a {
                position: relative;
                display: block;
                width: 100%;
                display: flex;
                text-decoration: none;
                color: #343a40
            }
            .sidebar ul li:hover a,
            .sidebar ul li.hovered a {
                color: #3399ff;
            }

            .sidebar ul li a .icon {
                position: relative;
                display: block;
                min-width: 60px;
                height: 60px;
                line-height: 75px;
                text-align: center;
            }
            .sidebar ul li a .icon i {
                font-size: 1.50rem;
                margin-top: 15px;
            }

            .sidebar ul li a .title {
                position: relative;
                display: block;
                padding: 0 10px;
                height: 60px;
                line-height: 60px;
                text-align: start;
                white-space: nowrap;
            }

            .content {
                flex-grow: 1;
                padding-left: 20px;
            }

            /* Additional styles for icons */
            .table a {
                display: inline-block;
                margin-right: 5px;
            }

            .table a i {
                vertical-align: middle;
            }
        </style>
        <!-- Include Font Awesome CSS -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css"
              integrity="sha512-iBBXm8fW90+nuLcSKlbmrPcLa0OT92xO1BIsZ+ywDWZCvqsWgccV3gFoRBv0z+8dLJgyAHIhR35VZc2oM/gI1w=="
              crossorigin="anonymous" referrerpolicy="no-referrer" />
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" integrity="sha384-NpXZmZLY6AA4ISouM6UAMnLRn4HehEnpkGjJXIXNq7sEiKA3Q6l2L/tv6tT7lHbe" crossorigin="anonymous">
        <link rel="stylesheet" href="./style.css">
    </head>
    <body>

        <header>
            <h2>Heath Management System</h2>
        </header>
        <div class="sidebar">
            <ul>
                <li>
                    <a href="<%=request.getContextPath()%>/dashboard">
                        <span class="icon">
                            <i class="fas fa-hospital"></i>
                        </span>
                        <span class="title">Dashboard</span>
                    </a>
                </li>

                <li>
                    <a href="<%=request.getContextPath()%>/nurse">
                        <span class="icon">
                            <i class="fas fa-user-nurse"></i>
                        </span>
                        <span class="title">Nurse</span>
                    </a>
                </li>

                <li>
                    <a href="<%=request.getContextPath()%>/list">
                        <span class="icon">
                            <i class="fas fa-user"></i>
                        </span>
                        <span class="title">Patients</span>
                    </a>
                </li>
                <li>
                    <a href="<%=request.getContextPath()%>/appointments">
                        <span class="icon">
                            <i class="fas fa-calendar-check"></i>
                        </span>
                        <span class="title">Appointments</span>
                    </a>
                </li>
                <li>
                    <a href="<%=request.getContextPath()%>/password">
                        <span class="icon">
                            <i class="fas fa-lock-open"></i>
                        </span>
                        <span class="title">Password</span>
                    </a>
                </li>

                <li>
                    <a href="logout">
                        <span class="icon">
                            <i class="fas fa-sign-out-alt"></i>
                        </span>
                        <span class="title">Sign Out</span>
                    </a>
                </li>
            </ul>
        </div>
        <div class="container">
            <!--            <div class="sidebar">
                            <a href="#">Dashboard</a>
                            <a href="#">Doctors</a>
                            <a href="<%=request.getContextPath()%>/list">Patients</a>
                            <a href="#">Appointments</a>
                        </div>-->

            <div class="content">
                <h3>List of Doctors</h3>
                <a href="<%=request.getContextPath()%>/doctor-new" class="btn btn-success btn-add"><button>Add New Doctor</button></a>

                <table>
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Name</th>
                            <th>Specialization</th>
                            <th>Email</th>
                            <th>Phone Number</th>
                            <th>Address</th>
                            <th>License Number</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="doctor" items="${listDoctors}">
                            <tr>
                                <td>${doctor.id}</td>
                                <td>${doctor.firstName} ${doctor.lastName}</td>
                                <td>${doctor.specialization}</td>
                                <td>${doctor.email}</td>
                                <td>${doctor.phoneNumber}</td>
                                <td>${doctor.address}</td>
                                <td>${doctor.licenseNumber}</td>
                                <td>
                                    <a href="doctor-edit?id=<c:out value='${doctor.id}' />" ><button class="btn-primary">
                                            <i class="fas fa-edit"></i> Edit
                                       
                                    </button> </a>
                                    <a href="doctor-delete?id=<c:out value='${doctor.id}' />"><button class="btn-danger">  
                                            <i class="fas fa-trash-alt"></i> Delete
                                        </button></a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>

        <script>
            function editDoctor(doctorId) {
            }
        </script>

    </body>

</html>
