package Controller;

import java.io.IOException;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Dao.UserDao;
import email_services.Services;
import email_services.Servicess;
import model.Message;
import model.User;

/**
 * Servlet implementation class UserController
 */
@WebServlet("/UserController")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action=request.getParameter("action");
		if(action.equalsIgnoreCase("register"))
		{
			User u=new User();
			u.setFname(request.getParameter("fname"));
			u.setLname(request.getParameter("lname"));
			u.setEmail(request.getParameter("email"));
			u.setMobile(Long.parseLong(request.getParameter("mobile")));
			u.setPassword(request.getParameter("password"));
			String email=request.getParameter("email");
			boolean flag=UserDao.checkEmail(email);
			if(flag==true)
			{
				request.setAttribute("msg", "Account already exist");
				request.getRequestDispatcher("user-registration.jsp").forward(request, response);
			}
			else
			{
				UserDao.insertUser(u);
				Random r =new Random();
				int num=r.nextInt(999999);
				Servicess s=new Servicess();
				s.sendMail(email, num);
				request.setAttribute("otp",num);
				request.setAttribute("email", email);
				request.getRequestDispatcher("verify-otp.jsp").forward(request, response);
			}
		}
		else if(action.equalsIgnoreCase("verify"))
		{
			String email=request.getParameter("email");
			int otp1=Integer.parseInt(request.getParameter("otp1"));
			int otp2=Integer.parseInt(request.getParameter("otp2"));
			System.out.println(email+" "+otp1+" "+otp2);
			if(otp1==otp2)
			{
				response.sendRedirect("user-login.jsp");
			}
			else
			{
				request.setAttribute("msg", "Otp didn't match");
				request.getRequestDispatcher("verify-otp.jsp").forward(request, response);
			}
		}
		else if(action.equalsIgnoreCase("login"))
		{
			String email=request.getParameter("email");
			String password=request.getParameter("password");
			boolean flag = UserDao.checkEmail(email);
			if(flag==true)
			{
				User u1=UserDao.loginUser(email, password);
				if(u1!=null)
				{
					HttpSession session=request.getSession();
					session.setAttribute("data", u1);
					request.getRequestDispatcher("user-home.jsp").forward(request, response);
				}
				else
				{
					request.setAttribute("msg", "Password is incorrect");
					request.getRequestDispatcher("user-login.jsp").forward(request, response);
				}
			}
			else
			{
				request.setAttribute("msg", "Email doesnot exist");
				request.getRequestDispatcher("user-login.jsp").forward(request, response);
			}
		}
		else if(action.equalsIgnoreCase("send"))
		{
			String email=request.getParameter("to");
			String msg=request.getParameter("msg");
			Message m=new Message();
			m.setFrom(request.getParameter("from"));
			m.setTo(request.getParameter("to"));
			m.setMsg(request.getParameter("msg"));
			m.setUid(Integer.parseInt(request.getParameter("uid")));
			UserDao.insertMessage(m);
			Services s=new Services();
			s.sendMail(email, msg);
			System.out.println("Message sent");
			response.sendRedirect("user-home.jsp");
			
		}
		else if(action.equalsIgnoreCase("update"))
		{
			User u=new User();
			u.setId(Integer.parseInt(request.getParameter("uid")));
			u.setFname(request.getParameter("fname"));
			u.setLname(request.getParameter("lname"));
			u.setEmail(request.getParameter("email"));
			u.setMobile(Long.parseLong(request.getParameter("mobile")));
			UserDao.updateUser(u);
			response.sendRedirect("user-home.jsp");
			
		}
		
	}

}
