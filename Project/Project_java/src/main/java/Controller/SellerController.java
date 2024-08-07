package Controller;

import java.io.IOException;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Dao.SellerDao;
import model.Seller;
import email_services.Servicess;
/**
 * Servlet implementation class SellerController
 */
@WebServlet("/SellerController")
public class SellerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SellerController() {
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
			Seller s= new Seller();
			s.setName(request.getParameter("name"));
			s.setContact(Long.parseLong(request.getParameter("contact")));
			s.setAddress(request.getParameter("address"));
			s.setEmail(request.getParameter("email"));
			s.setPassword(request.getParameter("password"));
			System.out.println(s);
			String email=request.getParameter("email");
			boolean flag=SellerDao.checkEmail(email);
			if(flag==false)
			{
				SellerDao.insertSeller(s);
				response.sendRedirect("seller-login.jsp");
			}
			else
			{
				request.setAttribute("msg", "Account already exist");
				request.getRequestDispatcher("seller-registration.jsp").forward(request, response);
			}
		}
		else if(action.equalsIgnoreCase("login"))
		{
			Seller s= new Seller();
			s.setEmail(request.getParameter("email"));
			s.setPassword(request.getParameter("password"));
			String email=request.getParameter("email");
			boolean flag=SellerDao.checkEmail(email);
			if(flag==true)
			{
				Seller s1=SellerDao.getLogin(s);
				if(s1!=null)
				{
					HttpSession session = request.getSession();
					session.setAttribute("data", s1);
					request.getRequestDispatcher("seller-home.jsp").forward(request, response);
				}
				else
				{
					request.setAttribute("msg", "Password is Incorrect");
					request.getRequestDispatcher("seller-login.jsp").forward(request, response);
				}
			}
			else
			{
				request.setAttribute("msg", "Email is incorrect");
				request.getRequestDispatcher("seller-login.jsp").forward(request, response);
			}
		}
		else if(action.equalsIgnoreCase("update"))
		{
			Seller s=new Seller();
			s.setId(Integer.parseInt(request.getParameter("id")));
			s.setName(request.getParameter("name"));
			s.setContact(Long.parseLong(request.getParameter("contact")));
			s.setAddress(request.getParameter("address"));
			s.setEmail(request.getParameter("email"));
			SellerDao.updateSeller(s);
			HttpSession session=request.getSession();
			session.setAttribute("data", s);
			request.getRequestDispatcher("seller-home.jsp").forward(request, response);
		}
		else if(action.equalsIgnoreCase("changepswd"))
		{
			String email=request.getParameter("email");
			String op=request.getParameter("oldpassword");
			String np=request.getParameter("newpassword");
			String cnp=request.getParameter("confirmpassword");
			boolean flag=SellerDao.checkEmailandOP(email, op);
			if(flag==true)
			{
				if(np.equals(cnp))
				{
					SellerDao.changePassword(email, np);
					response.sendRedirect("seller-home.jsp");
				}
				else
				{
					request.setAttribute("msg", "New Password and Confirm new password is different");
					request.getRequestDispatcher("seller-change-pswd.jsp").forward(request, response);
				}
			}
			else
			{
				request.setAttribute("msg", "Password is incorrect");
				request.getRequestDispatcher("seller-change-pswd.jsp").forward(request, response);
			}
		}
		else if(action.equalsIgnoreCase("getotp"))
		{
			String email=request.getParameter("email");
			boolean flag=SellerDao.checkEmail(email);
			if(flag==true)
			{
				Random r=new Random();
				int num=r.nextInt(999999);
				Servicess s=new Servicess();
				s.sendMail(email,num);
				request.setAttribute("email", email);
				request.setAttribute("otp", num);
				request.getRequestDispatcher("seller-verify-otp.jsp").forward(request, response);
			}
			else
			{
				request.setAttribute("msg", "Email not valid");
				request.getRequestDispatcher("seller-forget-password.jsp").forward(request, response);
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
				request.getRequestDispatcher("seller-new-password.jsp").forward(request, response);
			}
			else
			{
				request.setAttribute("email", email);
				request.setAttribute("otp", otp1);
				request.setAttribute("msg", "OTP doesn't match");
				request.getRequestDispatcher("seller-verify-otp.jsp").forward(request, response);
			}
		}
		else if(action.equalsIgnoreCase("up"))
		{
			String pswd=request.getParameter("np");
			String cnp=request.getParameter("cnp");
			String email=request.getParameter("email");
			System.out.println(email+" "+pswd+" "+cnp);
			if(pswd.equals(cnp))
			{
				SellerDao.changePassword(email, pswd);
				response.sendRedirect("seller-login.jsp");
			}
			else
			{
				request.setAttribute("email", email);
				request.setAttribute("msg", "New password and Confirm new Password are different");
				request.getRequestDispatcher("seller-new-password.jsp").forward(request, response);
			}
		}
	}

}
