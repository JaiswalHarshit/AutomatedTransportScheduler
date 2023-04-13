package com.ukg.transport.controller;

import com.ukg.transport.dto.RosterData;
import com.ukg.transport.service.RosterServiceImpl;
import com.ukg.transport.service.RouteSchedulerServiceImpl;
import com.ukg.transport.util.DataTransformerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/download")
public class RosterDownloadController {

	@Autowired
	RosterServiceImpl         rosterService;
	@Autowired
	RouteSchedulerServiceImpl routeSchedulerService;

	@GetMapping
	public String downloadCSV(Model model) throws IOException {

		List<List<RosterData>> rosterData = DataTransformerUtil.getRosterLists(routeSchedulerService.getRoutes());
		rosterService.writeToFile(rosterData);
		model.addAttribute("location", System.getProperty("user.dir") + "\\automated-transport-scheduling\\transport\\src\\main\\resources\\csvData\\RouteData.xlsx");
		return "downloadRoster";
	}

}
