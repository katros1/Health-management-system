

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Health Management System</title>
        <style>
            .div {
                border: 1px solid #006980;
                background: linear-gradient(
                    90deg,
                    #0098b9 0%,
                    rgba(255, 255, 255, 0) 100%
                    );
                display: flex;
                flex-direction: column;
                justify-content: center;
                padding: 50px 21px 50px 60px;
            }
            @media (max-width: 991px) {
                .div {
                    padding: 0 20px;
                }
            }
            .div-2 {
                margin: 54px 0 68px 41px;
            }
            @media (max-width: 991px) {
                .div-2 {
                    max-width: 100%;
                    margin: 40px 0;
                }
            }
            .div-3 {
                gap: 20px;
                display: flex;
            }
            @media (max-width: 991px) {
                .div-3 {
                    flex-direction: column;
                    align-items: stretch;
                    gap: 0px;
                }
            }
            .column {
                display: flex;
                flex-direction: column;
                line-height: normal;
                width: 35%;
                margin-left: 0px;
            }
            @media (max-width: 991px) {
                .column {
                    width: 100%;
                }
            }
            .div-4 {
                display: flex;
                flex-direction: column;
                align-items: start;
            }
            @media (max-width: 991px) {
                .div-4 {
                    max-width: 100%;
                    margin-top: 40px;
                }
            }
            .span {
                display: flex;
                gap: 0px;
            }
            .img {
                aspect-ratio: 1;
                object-fit: contain;
                object-position: center;
                width: 148px;
                overflow: hidden;
                max-width: 100%;
            }
            .div-5 {
                color: #fff;
                align-self: start;
                margin-top: 59px;
                flex-grow: 1;
                white-space: nowrap;
                font: 400 15px Roboto, sans-serif;
            }
            @media (max-width: 991px) {
                .div-5 {
                    margin-top: 40px;
                    white-space: initial;
                }
            }
            .span-2 {
                align-self: stretch;
                display: flex;
                margin-top: 180px;
                padding-left: 20px;
                flex-direction: column;
            }
            @media (max-width: 991px) {
                .span-2 {
                    max-width: 100%;
                    margin-top: 40px;
                    padding-left: 20px;
                }
            }
            .div-6 {
                color: #fff;
                align-self: stretch;
                font: 400 16px Roboto, sans-serif;
            }
            @media (max-width: 991px) {
                .div-6 {
                    max-width: 100%;
                }
            }
            .input-username,
            .input-password {
                width: 70%;
                padding: 8px;
                margin-top: 8px;
            }
            .div-7 {
                color: #fff;
                align-self: stretch;
                margin-top: 57px;
                font: 400 16px Roboto, sans-serif;
            }
            @media (max-width: 991px) {
                .div-7 {
                    max-width: 100%;
                    margin-top: 40px;
                }
            }
            .img-2 {
                aspect-ratio: 423;
                object-fit: contain;
                object-position: center;
                width: 100%;
                stroke-width: 1px;
                stroke: #fff;
                overflow: hidden;
                align-self: stretch;
                margin-top: 14px;
            }
            @media (max-width: 991px) {
                .img-2 {
                    max-width: 100%;
                }
            }
            .img-3 {
                aspect-ratio: 423;
                object-fit: contain;
                object-position: center;
                width: 100%;
                stroke-width: 1px;
                stroke: #fff;
                overflow: hidden;
                align-self: stretch;
                margin-top: 16px;
            }
            @media (max-width: 991px) {
                .img-3 {
                    max-width: 100%;
                }
            }
            .submit-button {
                color: #fff;
                white-space: nowrap;
                border-radius: 2px;
                box-shadow: 0px 4px 4px 0px rgba(0, 0, 0, 0.25);
                background-color: #079bbb;
                align-self: flex-start;
                margin-top: 29px;
                width: 175px;
                max-width: 100%;
                justify-content: center;
                align-items: center;
                padding: 16px 60px;
                font: 400 16px Roboto, sans-serif;
                border: none;
                cursor: pointer;
            }
            @media (max-width: 991px) {
                .img-4 {
                    max-width: 100%;
                    margin-top: 40px;
                }
            }
        </style>
    </head>

    <body>
         <input type="hidden" id="status" value="<%= request.getAttribute("access") %>" />
        <div class="div">
            <div class="div-2">
                <div class="div-3">
                    <div class="column">
                        <div class="div-4">
                            <span class="span">
                                <img src="https://cdn.builder.io/api/v1/image/assets/TEMP/e88226b865bd2fa7a5528ab355e3de291913e9c45cfd890f6990ea459aa45591?" class="img" />
                                <div class="div-5">Health Care</div>
                            </span>
                            <span class="span-2">
                                <form action="login" method="post">
                                    <div class="div-6">User Name</div>
                                    <input type="text" class="input-username" name="useremail" required/>
                                    <div class="div-7">Password</div>
                                    <input type="password" class="input-password" name="password" required/>
                                    <button type="submit" class="submit-button">Submit</button>
                                </form>
                            </span>
                        </div>
                    </div>
                    <div class="column-2">
                        <!-- Adjust the image source based on your needs -->
                        <img src="https://cdn.builder.io/api/v1/image/assets/TEMP/7155d07304602422db5cff0bf7cafffcaf215d9f85dc9d6f1a815ed0f679c625?" class="img-4" />
                    </div>
                </div>
            </div>
        </div>
        <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
        <link rel="stylesheet" href="alert/dist/sweetalert.css">
        <script type="text/javascript">

            var status = document.getElementById("status").value;
            if (status === "denied") {
                swal("Access Denied", "Invalid User Name or Password", "error");
            }
        </script>
    </body>
</html>



