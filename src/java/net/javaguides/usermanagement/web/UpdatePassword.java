/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package net.javaguides.usermanagement.web;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import net.javaguides.usermanagement.model.User;

import net.javaguides.usermanagement.dao.UserDAO;


@WebServlet("/update-password")
public class UpdatePassword extends HttpServlet {

   private static final long serialVersionUID = 1L;
    private UserDAO userDAO;

    public void init() {
        userDAO = new UserDAO();
    }
    
        protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String userEmail = request.getParameter("username");
        String userPassword = request.getParameter("password");
        String confirmPassword = request.getParameter("confirm");
        HttpSession session = request.getSession();
        RequestDispatcher dispatcher = null;


        try {

            User newUser = new User(userEmail, userPassword);
            
            
            if(userPassword == null ? confirmPassword == null : userPassword.equals(confirmPassword)) {
                System.out.println("1@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                userDAO.updateSystemUser(newUser);
                session.setAttribute("userEmail", userEmail );
                request.setAttribute("status", "success");
                response.sendRedirect("list");
            } else {
                System.out.println("########################################################" + userPassword + " "+ confirmPassword);
                request.setAttribute("status", "failed");
                dispatcher = request.getRequestDispatcher("update-password.jsp");
                dispatcher.forward(request, response);
            }
            
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }

    }
    }

