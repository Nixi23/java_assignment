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
import email_services.Servicess;
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
			u.setContact(Long.parseLong(request.getParameter("contact")));
			u.setAddress(request.getParameter("address"));
			u.setGender(request.getParameter("gender"));
			u.setPassword(request.getParameter("password"));
			u.setC_pswd(request.getParameter("cpswd"));
			String pswd=request.getParameter("password");
			String c_pswd=request.getParameter("cpswd");
			System.out.println(u);
			String email=request.getParameter("email");
			boolean flag=UserDao.checkEmail(email);
			System.out.println(flag);
			boolean check=pswd.equals(c_pswd);
			System.out.println(check);
			if(flag==false)
			{
				if(check==true)
				{
					UserDao.insertUser(u);
					response.sendRedirect("login.jsp");
				}
				else
				{
					request.setAttribute("msg", "Password and Confirm Password are different");
					request.getRequestDispatcher("signup.jsp").forward(request, response);
				}
			}
			else
			{
				request.setAttribute("msg", "Account already exist");
				request.getRequestDispatcher("signup.jsp").forward(request, response);
			}
		}
		else if(action.equalsIgnoreCase("login"))
		{
			User c=new User();
			c.setEmail(request.getParameter("email"));
			c.setPassword(request.getParameter("pswd"));
			String email=request.getParameter("email");
			boolean flag=UserDao.checkEmail(email);
			if(flag==true)
			{
				User c1=UserDao.LoginUser(c);
				//System.out.println(c1);
				if(c1!=null)
				{
					HttpSession session=request.getSession();
					session.setAttribute("data", c1);
					request.getRequestDispatcher("home.jsp").forward(request, response);
				}
				else
				{
					request.setAttribute("msg", "Password is Incorrect");
					request.getRequestDispatcher("login.jsp").forward(request, response);
				}
			}
			else
			{
				request.setAttribute("msg", "Email is incorrect");
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}
		}
		else if(action.equalsIgnoreCase("getotp"))
		{
			String email=request.getParameter("email");
			System.out.println(email);
			boolean flag=UserDao.checkEmail(email);
			System.out.println(flag);
			if(flag==true)
			{
				Random r=new Random();
				int num=r.nextInt(999999);
				Servicess s=new Servicess();
				s.sendMail(email,num);
				request.setAttribute("email",email);
				request.setAttribute("otp", num);
				request.getRequestDispatcher("verify-otp.jsp").forward(request, response);
			}
			else
			{
				request.setAttribute("email",email);
				request.setAttribute("msg", "Email is incorrect");
				request.getRequestDispatcher("forget-password.jsp").forward(request, response);
			}
		}
		else if(action.equalsIgnoreCase("verify"))
		{
			String email=request.getParameter("email");
			int otp1=Integer.parseInt(request.getParameter("otp1"));
			int otp2=Integer.parseInt(request.getParameter("otp2"));
			if(otp1==otp2)
			{
				request.setAttribute("email", email);
				request.getRequestDispatcher("generate-new-pswd.jsp").forward(request, response);
			}
			else
			{
				request.setAttribute("otp", otp1);
				request.setAttribute("msg", "Otp is incorrect");
				request.getRequestDispatcher("verify-otp.jsp").forward(request, response);
			}
		}
		else if(action.equalsIgnoreCase("change"))
		{
			String email=request.getParameter("email");
			String pswd=request.getParameter("pswd");
			String c_pswd=request.getParameter("cpswd");
			System.out.println(pswd+" "+c_pswd);
			if(pswd.equals(c_pswd))
			{
				UserDao.updatePassword(pswd, email);
				response.sendRedirect("login.jsp");
			}
			else
			{
				request.setAttribute("msg", "Password and Confirm Password are different");
				request.getRequestDispatcher("generate-new-pswd.jsp").forward(request, response);
			}
		}
		else if(action.equalsIgnoreCase("update"))
		{
			String email=request.getParameter("email");
			String opswd=request.getParameter("oldpswd");
			String np=request.getParameter("pswd");
			String cnp=request.getParameter("cpswd");
			System.out.println(email);
			System.out.println(np);
			System.out.println(cnp);
			boolean flag=UserDao.checkPassword(opswd);
			if(flag==true)
			{
				if(np.equals(cnp))
				{
					UserDao.updatePassword(np, email);
					response.sendRedirect("home.jsp");
				}
				else
				{
					request.setAttribute("msg", "Password and confirm password are different");
					request.getRequestDispatcher("change-pswd.jsp").forward(request, response);
				}
			}
			else
			{
				request.setAttribute("msg", "Old Password is incorrect");
				request.getRequestDispatcher("change-pswd.jsp").forward(request, response);
			}
		}
	}

}
