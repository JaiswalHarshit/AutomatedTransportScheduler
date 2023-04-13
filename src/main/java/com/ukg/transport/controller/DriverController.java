package com.ukg.transport.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/drivers")
public class DriverController {

	@GetMapping
	public String getAllDrivers(Model model) {

		return "drivers";
	}

}
