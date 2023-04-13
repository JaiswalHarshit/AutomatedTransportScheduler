package com.ukg.transport.controller;

import com.ukg.transport.service.RosterServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

	@Autowired
	RosterServiceImpl rosterService;

	@GetMapping
	public String getAllEmployees(Model model){

		model.addAttribute("employees", rosterService.fetchData());
		return "employees";
	}

}
