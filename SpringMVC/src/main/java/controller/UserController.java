package controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import dao.UserDao;
import model.User;



@Controller
public class UserController {
	
	@Autowired
	private UserDao dao;
	
	@RequestMapping("/")
	public String indexPage() {
		return "index";
	}

	@RequestMapping("/register")
	public String registerPage(Model m) {
		m.addAttribute("id",1);
		m.addAttribute("name", "java");
		m.addAttribute("list", Arrays.asList("java","python","php","node"));
		return "register";
	}
	
	@RequestMapping("/login")
	public String loginPage() {
		return "login";
	}
	
//	@RequestMapping(value="/insert",method = RequestMethod.POST)
//	public void insertUser(@RequestParam("name") String name,
//			@RequestParam("contact") long contact,
//			@RequestParam("email") String email,
//			@RequestParam("password") String pass) {
//	}

	@RequestMapping(value="/insert",method = RequestMethod.POST)
	public String insertUser(@ModelAttribute User u,Model m) {
		this.dao.insertOrUpdateUser(u);
		m.addAttribute("list", this.dao.getAllUsers());
		return "home";
	}
	
	@RequestMapping("/home")
	public String homePage(Model m) {
		m.addAttribute("list", this.dao.getAllUsers());
		return "home";
	}

	@RequestMapping(value="/edit/{id}")
	public ModelAndView editUser(@PathVariable("id") int id) {
		User u= this.dao.getUserById(id);
		ModelAndView m = new ModelAndView();
		m.addObject("u", u);
		m.setViewName("update");
		return m;
	}
	@RequestMapping(value="/delete/{id}")
	public String deleteUser(@PathVariable("id") int id,Model m) {
		this.dao.deleteUser(id);
		List<User> list = this.dao.getAllUsers();
		m.addAttribute("list", list);
		return "home";
	}
	
}