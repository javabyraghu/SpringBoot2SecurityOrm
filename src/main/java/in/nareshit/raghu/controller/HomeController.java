package in.nareshit.raghu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

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
	public String showCommon() {
		return "CommonPage";
	}
	
	@GetMapping("/denied")
	public String showDenied() {
		return "DeniedPage";
	}
}
