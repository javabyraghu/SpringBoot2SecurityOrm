package in.nareshit.raghu.controller;

import java.security.Principal;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import in.nareshit.raghu.service.IUserService;

@Controller
public class HomeController {
	@Autowired
	private IUserService service;

	@GetMapping("/home")
	public String showHome() {
		return "HomePage";
	}
	
	@GetMapping("/emp")
	public String showEmp() {
		return "EmpPage";
	}
	
	@GetMapping("/admin")
	public String showAdmin() {
		return "AdminPage";
	}
	
	@GetMapping("/common")
	public String showCommon(
			Principal p,
			Model model,
			HttpSession ses) {
		System.out.println("Impl class: "+p.getClass());
		model.addAttribute("currentUser", p.getName());
		//model.addAttribute("fullUser", service.findByEmai(p.getName()).get());
		ses.setAttribute("fullUser", service.findByEmai(p.getName()).get());
		return "CommonPage";
	}
	
	@GetMapping("/denied")
	public String showDenied() {
		return "DeniedPage";
	}
}
