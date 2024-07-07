package net.javaguides.usermanagement.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import net.javaguides.usermanagement.dao.AppointmentDAO;
import net.javaguides.usermanagement.dao.DoctorDAO;
import net.javaguides.usermanagement.dao.NurseDAO;

import net.javaguides.usermanagement.dao.UserDAO;
import net.javaguides.usermanagement.model.Appointment;
import net.javaguides.usermanagement.model.Doctor;
import net.javaguides.usermanagement.model.Nurse;
import net.javaguides.usermanagement.model.Statics;
import net.javaguides.usermanagement.model.User;

@WebServlet("/")
public class UserServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private UserDAO userDAO;
    private DoctorDAO doctorDAO;
    private AppointmentDAO appoint;
    private NurseDAO nurseDAO;

    public void init() {
        userDAO = new UserDAO();
        doctorDAO = new DoctorDAO();
        appoint = new AppointmentDAO();
        nurseDAO = new NurseDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();
        System.out.println(action + "@@@@@@@@@@@@@@@@@@@");
        try {
            switch (action) {
                case "/password":
                    changePasswordForm(request, response);
                    break;
                case "/list":
                    listUser(request, response);
                    break;
                case "/new":
                    showNewForm(request, response);
                    break;
                case "/insert":
                    insertUser(request, response);
                    break;
                case "/delete":
                    deleteUser(request, response);
                    break;
                case "/edit":
                    showEditForm(request, response);
                    break;
                case "/update":
                    updateUser(request, response);
                    break;
                case "/doctor":
                    try {
                    listDoctors(request, response);
                } catch (SQLException | IOException | ServletException e) {
                    e.printStackTrace();
                }
                break;
                case "/doctor-new":
                    showNewDoctorForm(request, response);
                    break;
                case "/doctor-insert":
		        try {
                    insertDoctor(request, response);
                } catch (SQLException | IOException e) {
                    e.printStackTrace();
                }
                break;
                case "/doctor-delete":
		        try {
                    deleteDoctor(request, response);
                } catch (SQLException | IOException e) {
                    e.printStackTrace();
                }
                break;
                case "/doctor-edit":
		        try {
                    showEditDoctorForm(request, response);
                } catch (SQLException | ServletException | IOException e) {
                    e.printStackTrace();
                }
                break;
                case "/doctor-update":
		        try {
                    updateDoctor(request, response);
                } catch (SQLException | IOException e) {
                    e.printStackTrace();
                }
                break;
                case "/nurse":
                    try {
                    listNurses(request, response);
                } catch (SQLException | IOException | ServletException e) {
                    e.printStackTrace();
                }
                break;
                case "/nurse-new":
                    showNewNurseForm(request, response);
                    break;
                case "/nurse-insert":
		        try {
                    insertNurse(request, response);
                } catch (SQLException | IOException e) {
                    e.printStackTrace();
                }
                break;
                case "/nurse-delete":
		        try {
                    deleteNurse(request, response);
                } catch (SQLException | IOException e) {
                    e.printStackTrace();
                }
                break;
                case "/nurse-edit":
		        try {
                    showEditNurseForm(request, response);
                } catch (SQLException | ServletException | IOException e) {
                    e.printStackTrace();
                }
                break;
                case "/nurse-update":
		        try {
                    updateNurse(request, response);
                } catch (SQLException | IOException e) {
                    e.printStackTrace();
                }
                break;
                case "/appointments":
                    try {
                    listAppointments(request, response);
                } catch (SQLException | IOException | ServletException e) {
                    e.printStackTrace();
                }
                break;
                case "/appointment-new":
                    showAppointmentForm(request, response);
                    break;
                case "/appointment-schedule":
		        try {
                    insertAppointment(request, response);
                } catch (SQLException | IOException e) {
                    e.printStackTrace();
                }
                break;
                case "/appointment-remove":
		        try {
                    deleteAppointment(request, response);
                } catch (SQLException | IOException e) {
                    e.printStackTrace();
                }
                break;
                case "/appointment-edit":
		        try {
                    showRescheduleForm(request, response);
                } catch (SQLException | ServletException | IOException e) {
                    e.printStackTrace();
                }
                break;
                case "/appointment-reshedule":
		        try {
                    rescheduleAppointment(request, response);
                } catch (SQLException | IOException e) {
                    e.printStackTrace();
                }
                break;
                case "/dashboard":
                    showDashboard(request, response);
                    break;
                default:
                    showDashboard(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<User> listUser = userDAO.selectAllUsers();
        request.setAttribute("listUser", listUser);
        RequestDispatcher dispatcher = request.getRequestDispatcher("user-list.jsp");
        dispatcher.forward(request, response);
    }
    
    private void showDashboard(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        HttpSession session = request.getSession();
        List<User> listUser = userDAO.selectAllUsers();
        List<Doctor> listDoctors = doctorDAO.selectAllDoctors();
        List<Nurse> listNurses = nurseDAO.selectAllNurses();
        List<Appointment> appointments = appoint.selectAllAppointments();
        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        Statics numbers = new Statics(listUser.size(), listDoctors.size(), listNurses.size(), appointments.size());
        session.setAttribute("userS", numbers);
        dispatcher.forward(request, response);
    }
    
    private void changePasswordForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("update-password.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        User existingUser = userDAO.selectUser(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
        request.setAttribute("user", existingUser);
        dispatcher.forward(request, response);

    }

    private void insertUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String gender = request.getParameter("gender");
        int age = Integer.parseInt(request.getParameter("age"));
        String phone = request.getParameter("phoneNumber");
        String bloodGp = request.getParameter("bloodGp");
        User newUser = new User(name, email, gender, age, phone, bloodGp);
        userDAO.insertUser(newUser);
        response.sendRedirect("list");
    }

    private void updateUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String gender = request.getParameter("gender");
        int age = Integer.parseInt(request.getParameter("age"));
        String phone = request.getParameter("phoneNumber");
        String bloodGp = request.getParameter("bloodGp");

        User book = new User(id, name, email, gender, age, phone, bloodGp);
        userDAO.updateUser(book);
        response.sendRedirect("list");
    }

    private void deleteUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        userDAO.deleteUser(id);
        response.sendRedirect("list");

    }

    private void showNewDoctorForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("doctor-form.jsp");
        dispatcher.forward(request, response);
    }

    private void insertDoctor(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String specialization = request.getParameter("specialization");
        String email = request.getParameter("email");
        String phoneNumber = request.getParameter("phoneNumber");
        String address = request.getParameter("address");
        String licenseNumber = request.getParameter("licenseNumber");

        Doctor newDoctor = new Doctor(firstName, lastName, specialization, email, phoneNumber, address, licenseNumber);
        doctorDAO.insertDoctor(newDoctor);
        response.sendRedirect("doctor"); 
    }

    private void deleteDoctor(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        doctorDAO.deleteDoctor(id);
        response.sendRedirect("doctor");
    }

    private void showEditDoctorForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        Doctor existingDoctor = doctorDAO.selectDoctor(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("doctor-form.jsp");
        request.setAttribute("doctor", existingDoctor);
        dispatcher.forward(request, response);
    }

    private void updateDoctor(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String specialization = request.getParameter("specialization");
        String email = request.getParameter("email");
        String phoneNumber = request.getParameter("phoneNumber");
        String address = request.getParameter("address");
        String licenseNumber = request.getParameter("licenseNumber");

        Doctor doctor = new Doctor(id, firstName, lastName, specialization, email, phoneNumber, address, licenseNumber);
        doctorDAO.updateDoctor(doctor);
        response.sendRedirect("doctor");
    }

    private void listDoctors(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Doctor> listDoctors = doctorDAO.selectAllDoctors();
        request.setAttribute("listDoctors", listDoctors);
        RequestDispatcher dispatcher = request.getRequestDispatcher("doctor-list.jsp");
        dispatcher.forward(request, response);
    }
    
    
    private void showNewNurseForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("nurse-form.jsp");
        dispatcher.forward(request, response);
    }

    private void insertNurse(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String phoneNumber = request.getParameter("phoneNumber");
        String address = request.getParameter("address");
        String licenseNumber = request.getParameter("licenseNumber");

        Nurse nurse = new Nurse(firstName, lastName, email, phoneNumber, address, licenseNumber);
        nurseDAO.insertNurse(nurse);
        response.sendRedirect("nurse");
    }

    private void deleteNurse(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        nurseDAO.deleteNurse(id);
        response.sendRedirect("nurse");
    }

    private void showEditNurseForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        Nurse existingNurse = nurseDAO.selectNurse(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("nurse-form.jsp");
        request.setAttribute("nurse", existingNurse);
        dispatcher.forward(request, response);
    }

    private void updateNurse(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String phoneNumber = request.getParameter("phoneNumber");
        String address = request.getParameter("address");
        String licenseNumber = request.getParameter("licenseNumber");

        Nurse nurse = new Nurse(id, firstName, lastName, email, phoneNumber, address, licenseNumber);
        nurseDAO.updateNurse(nurse);
        response.sendRedirect("nurse");
    }

    private void listNurses(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Nurse> listNurses = nurseDAO.selectAllNurses();
        request.setAttribute("listNurses", listNurses);
        RequestDispatcher dispatcher = request.getRequestDispatcher("nurse-list.jsp");
        dispatcher.forward(request, response);
    }
    
    private void showAppointmentForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Doctor> listDoctors = doctorDAO.selectAllDoctors();
        List<User> listUser = userDAO.selectAllUsers();
        request.setAttribute("listPatient", listUser);
        request.setAttribute("listDoctors", listDoctors);
        RequestDispatcher dispatcher = request.getRequestDispatcher("appointment-form.jsp");
        dispatcher.forward(request, response);
    }
    
    private void insertAppointment(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String time = request.getParameter("time");
        String date = request.getParameter("date");
        String patientName = request.getParameter("patientName");
        String patientAge = request.getParameter("patientAge");
        String doctor = request.getParameter("doctor");
        

        Appointment appointment = new Appointment(time, date, patientName, patientAge, doctor);
        appoint.insertAppointment(appointment);
        response.sendRedirect("appointments");
    }
    
    private void deleteAppointment(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        appoint.deleteAppointment(id);
        response.sendRedirect("appointments");
    }
    
    private void showRescheduleForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        List<Doctor> listDoctors = doctorDAO.selectAllDoctors();
        List<User> listUser = userDAO.selectAllUsers();
        request.setAttribute("listPatient", listUser);
        request.setAttribute("listDoctors", listDoctors);
        Long id = Long.parseLong(request.getParameter("id"));
        Appointment existingAppointment = appoint.selectAppointment(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("appointment-form.jsp");
        request.setAttribute("appointment", existingAppointment);
        dispatcher.forward(request, response);
    }
    
    private void rescheduleAppointment(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        String time = request.getParameter("time");
        String date = request.getParameter("date");
        String patientName = request.getParameter("patientName");
        String patientAge = request.getParameter("patientAge");
        String doctor = request.getParameter("doctor");

        Appointment appointment = new Appointment(id, time, date, patientName, patientAge, doctor);
        appoint.updateAppointment(appointment);
        response.sendRedirect("appointment");
    }
    
    private void listAppointments(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Appointment> appointments = appoint.selectAllAppointments();
        request.setAttribute("appointments", appointments);
        RequestDispatcher dispatcher = request.getRequestDispatcher("appointments.jsp");
        dispatcher.forward(request, response);
    }

}
