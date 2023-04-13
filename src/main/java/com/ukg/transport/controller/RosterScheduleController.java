package com.ukg.transport.controller;

import com.ukg.transport.dto.RosterData;
import com.ukg.transport.service.RouteSchedulerServiceImpl;
import com.ukg.transport.util.DataTransformerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/roster")
public class RosterScheduleController {

	@Autowired
	RouteSchedulerServiceImpl routeSchedulerService;

	@GetMapping
	public String getRoster(Model model) {

		List<List<RosterData>> rosterData = DataTransformerUtil.getRosterLists(routeSchedulerService.getRoutes());
		model.addAttribute("rosterData", rosterData);
		return "rosterSchedule";
	}
}
