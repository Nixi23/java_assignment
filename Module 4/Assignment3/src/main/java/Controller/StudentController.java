package Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Dao.StudentDao;
import model.Student;

/**
 * Servlet implementation class StudentController
 */
@WebServlet("/StudentController")
public class StudentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudentController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action=request.getParameter("action");
		if(action.equalsIgnoreCase("edit"))
		{
			int id=Integer.parseInt(request.getParameter("id"));
			Student s=StudentDao.getStudentById(id);
			request.setAttribute("student", s);
			request.getRequestDispatcher("update.jsp").forward(request, response);
		}
		else if(action.equalsIgnoreCase("delete"))
		{
			int id=Integer.parseInt(request.getParameter("id"));
			Student s=StudentDao.getStudentById(id);
			System.out.println(s);
			StudentDao.insertRecord(s);
			System.out.println("----------------------");
			StudentDao.deleteRecord(id);
			response.sendRedirect("student-home.jsp");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action=request.getParameter("action");
		if(action.equalsIgnoreCase("register"))
		{
			Student s=new Student();
			s.setFname(request.getParameter("fname"));
			s.setLname(request.getParameter("lname"));
			s.setEmail(request.getParameter("email"));
			s.setMobile(Long.parseLong(request.getParameter("contact")));
			s.setGender(request.getParameter("gender"));
			s.setPassword(request.getParameter("password"));
			System.out.println(s);
			String email=request.getParameter("email");
			boolean flag=StudentDao.checkEmail(email);
			if(flag==false)
			{
				StudentDao.insertStudent(s);
				response.sendRedirect("student-login.jsp");
			}
			else
			{
				request.setAttribute("msg", "Account already exist");
				request.getRequestDispatcher("student-registration.jsp").forward(request, response);
			}
		}
		else if(action.equalsIgnoreCase("login"))
		{
			String email=request.getParameter("email");
			String password=request.getParameter("password");
			boolean flag=StudentDao.checkEmail(email);
			if(flag==true)
			{
				Student s1=StudentDao.login(email, password);
				if(s1!=null)
				{
					HttpSession session=request.getSession();
					session.setAttribute("data", s1);
					request.getRequestDispatcher("student-home.jsp").forward(request, response);
				}
				else
				{
					request.setAttribute("msg", "Password is incorrect");
					request.getRequestDispatcher("student-login.jsp").forward(request, response);
				}
			}
			else
			{
				request.setAttribute("msg", "Email is incorrect");
				request.getRequestDispatcher("student-login.jsp").forward(request, response);
			}
		}
		else if(action.equalsIgnoreCase("update"))
		{
			Student s=new Student();
			s.setId(Integer.parseInt(request.getParameter("id")));
			s.setFname(request.getParameter("fname"));
			s.setLname(request.getParameter("lname"));
			s.setEmail(request.getParameter("email"));
			s.setMobile(Long.parseLong(request.getParameter("contact")));
			s.setGender(request.getParameter("gender"));
			s.setPassword(request.getParameter("password"));
			StudentDao.updateRecord(s);
			response.sendRedirect("student-home.jsp");
		}
	}

}
