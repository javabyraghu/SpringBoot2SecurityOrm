package in.nareshit.raghu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import in.nareshit.raghu.model.User;
import in.nareshit.raghu.service.IUserService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private IUserService service; //HAS-A
	
	//1. show Register Page
	@GetMapping("/register")
	public String showRegister() {
		return "UserRegister";
	}
	
	//2. on click register read data and save
	@PostMapping("/save")
	public String saveUser(
			@ModelAttribute User user,
			Model model
			) 
	{
		Integer id = service.saveUser(user);
		String message ="User '"+user.getUsrName()+"'("+id+") created!";
		model.addAttribute("message", message);
		return "UserRegister";
	}
	
}
