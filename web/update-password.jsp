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
                    
                  
                            <form action="update-password" method="post">
                            

                            <caption>
                                <h2>
                                    Change Password   
                                </h2>
                            </caption>
                                <input type="hidden" id="status" value="<%= request.getAttribute("status") %>" />
                            <fieldset class="form-group">
                                <label>User Name</label> <input type="text"
                                                                value="<c:out value='${userEmail}' />" class="form-control"
                                                                name="username" required="required">
                            </fieldset>

                            <fieldset class="form-group">
                                <label>New Password</label> <input type="password" class="form-control" name="password" required="required">
                            </fieldset>

                            <fieldset class="form-group">
                                <label>Confirm Password</label> <input type="password" class="form-control" name="confirm" required="required">
                            </fieldset>

                            <button type="submit" class="btn btn-success">Save</button>
                        </form>
                </div>
            </div>
        </div>
        <footer>
            Copyright &copy; 2023 health management system. All rights reserved.
        </footer>
    </body>
    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
	<link rel="stylesheet" href="alert/dist/sweetalert.css">
        <script type="text/javascript">
            
            var status = document.getElementById("status").value;
             if(status === "failed"){
                swal("Invalid Inputs","New Password and Confirm password must be same!!!","error");
            }
        </script>
</html>

