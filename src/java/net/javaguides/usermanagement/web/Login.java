package net.javaguides.usermanagement.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
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

/**
 *
 * @author user
 */
@WebServlet("/login")
public class Login extends HttpServlet {
    
    private UserDAO userDAO;
    
    public void init() {
        userDAO = new UserDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String userEmail = request.getParameter("useremail");
        String userPassword = request.getParameter("password");
        HttpSession session = request.getSession();
        RequestDispatcher dispatcher = null;

        try {
            User newUser = new User(userEmail, userPassword);
            boolean rs = userDAO.loginOp(newUser);
            
            if(rs) {
                session.setAttribute("userEmail", userEmail );
                response.sendRedirect("dashboard");
            } else {
                request.setAttribute("access", "denied");
                dispatcher = request.getRequestDispatcher("user-login.jsp");
                dispatcher.forward(request, response);
            }
            
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }

    }
}