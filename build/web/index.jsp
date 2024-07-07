<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
            a {
                text-decoration: none;
            }
            .container {
                margin-left: 250px;
                padding: 20px;
                background-color: white;
                border-radius: 8px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
                display: flex;
            }
            .sidebar {
                position: fixed;
                width: 250px;
                height: 100%;
                background: #e9f5f3;
                border-right: 1px solid #cfcdcc;
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
                color: #595959;
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
                padding: 0 7px;
                height: 60px;
                line-height: 60px;
                text-align: start;
                white-space: nowrap;
            }
            .cardBox {
                position: relative;
                width: 100%;
                padding: 20px;
                display: grid;
                grid-template-columns: repeat(3, 1fr);
                grid-gap: 30px;
            }

            .cardBox .card {
                position: relative;
                background: #fff;
                padding: 30px;
                border-radius: 20px;
                display: flex;
                justify-content: space-between;
                cursor: pointer;
                box-shadow: 0 7px 25px rgba(0, 0, 0, 0.3);
            }

            .cardBox .card .numbers {
                position: relative;
                font-weight: 500;
                font-size: 2.5rem;
                color: #3399ff;
            }

            .cardBox .card .cardName {
                color: #595959;
                font-size: 1.1rem;
                margin-top: 5px;
            }

            .cardBox .card .iconBx {
                font-size: 3.5rem;
                color: #595959;
            }

            .cardBox .card:hover {
                background: #3399ff;
            }
            .cardBox .card:hover .numbers,
            .cardBox .card:hover .cardName,
            .cardBox .card:hover .iconBx {
                color: #fff;
            }
        </style>
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
                    <a href="<%=request.getContextPath()%>/doctor">
                        <span class="icon">
                            <i class="fas fa-user-md"></i>
                        </span>
                        <span class="title">Doctors</span>
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
                    <a href="logout ">
                        <span class="icon">
                            <i class="fas fa-sign-out-alt"></i>
                        </span>
                        <span class="title">Sign Out</span>
                    </a>
                </li>
            </ul>
        </div>
        <div class="container">
            <div class="cardBox">
                <a href="<%=request.getContextPath()%>/doctor">
                    <div class="card">
                        <div>
<!--                            <div class="numbers"><c:out value='${doctors}'/></div>-->
                            <div class="numbers"><c:out value='${userS.getDoctor()}'/></div>
                            <div class="cardName">Doctors</div>
                        </div>

                        <div class="iconBx">
                            <i class="fas fa-user-md"></i>
                        </div>

                    </div>
                </a>
                <a href="<%=request.getContextPath()%>/nurse">
                    <div class="card">
                        <div>
                            <div class="numbers">${userS.getNurse()}</div>
                            <div class="cardName">Nurses</div>
                        </div>

                        <div class="iconBx">
                            <i class="fas fa-user-nurse"></i>
                        </div>
                    </div>
                </a>
                <a href="<%=request.getContextPath()%>/list">
                    <div class="card">
                        <div>
                            <div class="numbers">${userS.getPatient()}</div>
                            <div class="cardName">Patients</div>
                        </div>

                        <div class="iconBx">
                            <i class="fas fa-user"></i>
                        </div>
                    </div>
                </a>
                <a href="<%=request.getContextPath()%>/appointments">
                    <div class="card">
                        <div>
                            <div class="numbers">${userS.getAppointment()}</div>
                            <div class="cardName">Appointments</div>
                        </div>

                        <div class="iconBx">
                            <i class="fas fa-calendar-check"></i>
                        </div>
                    </div>
                </a>
            </div>
        </div>
    </body>
</html>