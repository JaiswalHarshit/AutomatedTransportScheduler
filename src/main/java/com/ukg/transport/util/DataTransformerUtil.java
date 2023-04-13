package com.ukg.transport.util;

import com.ukg.transport.dto.EmployeeData;
import com.ukg.transport.dto.RosterData;
import com.ukg.transport.dto.TransportRoute;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DataTransformerUtil {

	public static List<List<RosterData>> transformRoutesToRoster(List<Integer> locationIds, List<TransportRoute> routes, List<EmployeeData> employees) {

		Map<String, EmployeeData> locationMap = employees.stream().collect(Collectors.toMap(EmployeeData::getLocationId, employee -> employee));
		List<List<RosterData>> rosterDataList = new ArrayList<>();
		routes.forEach(route -> rosterDataList.add(prepareRosterData(locationIds, route, locationMap)));
		return rosterDataList;
	}

	public static List<RosterData> prepareRosterData(List<Integer> locationIds, TransportRoute route, Map<String, EmployeeData> locationMap) {

		List<RosterData> routeDetails = new ArrayList<>();

		EmployeeData emp3 = locationMap.get(locationIds.get(route.getDrop3()).toString());
		boolean guardRequired = "F".equals(emp3.getGender());
		RosterData rosterData3 = employeeTORosterData(emp3, guardRequired);
		rosterData3.setDropNumber(3);

		EmployeeData emp1 = locationMap.get(locationIds.get(route.getDrop1()).toString());
		RosterData rosterData1 = employeeTORosterData(emp1, guardRequired);
		rosterData1.setDropNumber(1);

		EmployeeData emp2 = locationMap.get(locationIds.get(route.getDrop2()).toString());
		RosterData rosterData2 = employeeTORosterData(emp2, guardRequired);
		rosterData2.setDropNumber(2);

		routeDetails.add(rosterData1);
		routeDetails.add(rosterData2);
		routeDetails.add(rosterData3);

		return routeDetails;
	}

	public static RosterData employeeTORosterData(EmployeeData employee, boolean guardRequired) {

		RosterData rosterData = employeeTORosterData(employee);
		rosterData.setGuardRequired(guardRequired);
		return rosterData;
	}

	public static RosterData employeeTORosterData(EmployeeData employee) {

		RosterData rosterData = new RosterData();
		rosterData.setContact(employee.getContact());
		rosterData.setEmployeeId(employee.getEmployeeID());
		rosterData.setName(employee.getName());
		rosterData.setAddress(employee.getAddress());
		rosterData.setAge(employee.getAge());
		rosterData.setCostCenter(employee.getCostCenter());
		rosterData.setGender(employee.getGender());
		rosterData.setInterest(employee.getInterest());
		return rosterData;
	}

	public static List<List<RosterData>> getRosterLists(List<List<RosterData>> rosterData) {

		int i = 1;
		for (List<RosterData> rosters : rosterData) {
			for (RosterData data : rosters) {
				data.setRouteId(Integer.toString(i));
			}
			i++;
		}
		return rosterData;
	}
}
