package com.ukg.transport.service;

import com.ukg.transport.util.DataTransformerUtil;
import com.ukg.transport.dto.DistanceMatrix;
import com.ukg.transport.dto.RosterData;
import com.ukg.transport.dto.TransportRoute;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RouteSchedulerServiceImpl {

	private static List<Integer> locationIds = new ArrayList<>();

	@Autowired
	public RosterServiceImpl rosterService;

	public List<List<RosterData>> getRoutes() {

		RouteSchedulerServiceImpl obj = new RouteSchedulerServiceImpl();
		List<TransportRoute> routes = obj.prepareRoutes();
		routes.forEach(route -> System.out.println(route.toString()));
		return DataTransformerUtil.transformRoutesToRoster(locationIds, routes, new RosterServiceImpl().fetchData());
	}

	public List<TransportRoute> prepareRoutes() {

		List<TransportRoute> routes = new ArrayList<>();
		List<DistanceMatrix> distanceMatrixPojoList = getDistanceMatrixFromXsls();
		List<List<Integer>> matrix = createDistanceList(distanceMatrixPojoList);

		Map<Integer, Integer> processed = new HashMap<>();
		int totalNodes = matrix.get(0).size();
		for (int i = 0; i < totalNodes; i++) {
			int drop3 = getMaxNodeIndex(matrix.get(0), processed);

			TransportRoute route = new TransportRoute();
			route.setDrop3(drop3);
			route.setTotal(matrix.get(i).get(drop3));
			routes.add(route);
			processed.put(drop3, matrix.get(i).get(drop3));

			getSecondSharingPartner(matrix, processed, route);
			getThirdSharingPartner(matrix, processed, route);
			if (processed.keySet().size() >= totalNodes - 1) {
				break;
			}
		}
		return routes;
	}

	private static void getSecondSharingPartner(List<List<Integer>> matrix, Map<Integer, Integer> processed, TransportRoute route) {

		int drop3 = route.getDrop3();
		int drop2 = getMinNodeIndex(matrix.get(drop3), processed);
		int distanceFromOffice = matrix.get(0).get(drop2);
		if (distanceFromOffice <= matrix.get(0).get(drop3)) {
			route.setDrop2(drop2);
			route.setTotal(distanceFromOffice + matrix.get(drop2).get(drop3));
		} else {
			route.setDrop2(drop3);
			route.setDrop3(drop2);
			route.setTotal(distanceFromOffice + matrix.get(drop2).get(drop3));
		}
		processed.put(drop2, distanceFromOffice);
	}

	private static void getThirdSharingPartner(List<List<Integer>> matrix, Map<Integer, Integer> processed, TransportRoute route) {

		int drop2 = route.getDrop2();
		int drop1 = getMinNodeIndex(matrix.get(drop2), processed);
		int distanceFromOffice = matrix.get(0).get(drop1);
		if (distanceFromOffice <= matrix.get(0).get(drop2)) {
			route.setDrop1(drop1);
			route.setTotal(distanceFromOffice + matrix.get(drop1).get(drop2));
		} else {
			route.setDrop1(drop2);
			route.setDrop2(drop1);
			route.setTotal(distanceFromOffice + matrix.get(drop1).get(drop2));
		}
		processed.put(drop1, distanceFromOffice);
	}

	private static int getMaxNodeIndex(List<Integer> matrix, Map<Integer, Integer> processed) {

		int maxValueIndex = -1;
		for (int i = 1; i < matrix.size(); i++) {
			if (!processed.containsKey(i)) {
				maxValueIndex = maxValueIndex == -1 ? i : maxValueIndex;
				if (matrix.get(maxValueIndex) < matrix.get(i)) {
					maxValueIndex = i;
				}
			}
		}
		return maxValueIndex;
	}

	private static int getMinNodeIndex(List<Integer> matrix, Map<Integer, Integer> processed) {

		int minValueIndex = -1;
		for (int i = 1; i < matrix.size(); i++) {
			if (!processed.containsKey(i)) {
				minValueIndex = minValueIndex == -1 ? i : minValueIndex;
				if (matrix.get(minValueIndex) > matrix.get(i)) {
					minValueIndex = i;
				}
			}
		}
		return minValueIndex;
	}

	/**
	 * 0 - UKG Office
	 * 1 - Orange County Intrapuram
	 * 2 - Gaurs Indrapuram
	 * 3 - Elite homs, sec 77 Noida
	 * 4 - ajnara sec 76
	 * 5 - Telecom, sec 62
	 * 6 - 14th avenue, gaur city
	 * 7 - gaur sondarayam, gaur city
	 * 8 - preteek, sec 77
	 * 9 - Cleo county
	 */
	private static List<List<Integer>> prepareDistanceMatrixList() {

		List<List<Integer>> matrix = new ArrayList<>();
		//matrix[0] = new int[] { 0,  O,  G,  E,  A,  T,  14, G,  P,  C };
		//matrix[0] = new int[] { 0,  1,  2,  3,  4,  5,  6,  7,  8,  9 };
		matrix.add(Arrays.asList(0, 5, 6, 10, 9, 2, 10, 12, 11, 7));
		matrix.add(Arrays.asList(5, 0, 1, 13, 12, 5, 17, 19, 14, 10));
		matrix.add(Arrays.asList(6, 1, 0, 14, 13, 6, 18, 20, 15, 11));
		matrix.add(Arrays.asList(10, 13, 14, 0, 1, 9, 7, 5, 1, 5));
		matrix.add(Arrays.asList(9, 12, 13, 1, 0, 9, 7, 5, 1, 5));
		matrix.add(Arrays.asList(2, 5, 6, 9, 9, 0, 10, 7, 8, 0));
		matrix.add(Arrays.asList(10, 17, 18, 7, 7, 10, 0, 1, 8, 7));
		matrix.add(Arrays.asList(12, 19, 20, 5, 5, 7, 1, 0, 9, 8));
		matrix.add(Arrays.asList(11, 14, 15, 1, 1, 8, 8, 9, 0, 5));
		matrix.add(Arrays.asList(7, 10, 11, 5, 5, 9, 7, 8, 5, 0));

		return matrix;
	}

	private static List<List<Integer>> createDistanceList(List<DistanceMatrix> distanceMatrixPojoList) {

		List<List<Integer>> distanceList = new ArrayList<>();
		List<Integer> distance = new ArrayList<>();
		for (int i = 0; i <= distanceMatrixPojoList.size() - 1; i++) {
			DistanceMatrix distanceMatrixPojo = distanceMatrixPojoList.get(i);
			distance.add(distanceMatrixPojo.getDistance());
			if (distance.size() == locationIds.size()) {
				distanceList.add(distance);
				distance = new ArrayList<>();
			}
		}
		return distanceList;
	}

	public List<DistanceMatrix> getDistanceMatrixFromXsls() {

		List<List<Integer>> matrix = new ArrayList<>();
		List<Integer> locationMap = new ArrayList<>();
		try {
			FileInputStream file = new FileInputStream(System.getProperty("user.dir") + "\\automated-transport-scheduling\\transport\\src\\main\\resources\\csvData\\DistanceMatrix.xlsx");
			XSSFWorkbook workbook = new XSSFWorkbook(file);
			XSSFSheet mXSSFSheet = workbook.getSheetAt(0);
			int firstRow = mXSSFSheet.getFirstRowNum();
			int lastRow = mXSSFSheet.getLastRowNum();
			setAllLocationIds(mXSSFSheet.getRow(firstRow));
			for (int index = firstRow + 1; index <= lastRow; index++) {
				List<Integer> l = new ArrayList<>();
				Row row = mXSSFSheet.getRow(index);
				for (int cellIndex = row.getFirstCellNum() + 1; cellIndex < row.getLastCellNum(); cellIndex++) {
					Cell cell = row.getCell(cellIndex, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
					l.add(getDataFromCell(cell));
				}
				matrix.add(l);
			}
			for (int index = firstRow + 1; index <= lastRow; index++) {
				Row row = mXSSFSheet.getRow(index);
				Cell cell = row.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
				locationMap.add(getDataFromCell(cell));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return createDistanceMatrix(matrix, locationMap);
	}

	private void setAllLocationIds(XSSFRow row) {

		locationIds = new ArrayList<>();
		for (int cellIndex = row.getFirstCellNum(); cellIndex < row.getLastCellNum(); cellIndex++) {
			Cell cell = row.getCell(cellIndex, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
			locationIds.add(getDataFromCell(cell));
		}
	}

	private static int getDataFromCell(Cell cell) {

		int value = 0;
		switch (cell.getCellTypeEnum()) {
			case NUMERIC:
				value = ((Double) cell.getNumericCellValue()).intValue();
				break;
			default:
				System.out.print("Invalid value");
				break;
		}
		return value;
	}

	private static List<DistanceMatrix> createDistanceMatrix(List<List<Integer>> arr) {

		List<DistanceMatrix> distanceMatrixPojoList = new ArrayList<>();
		for (int j = 0; j <= arr.size() - 1; j++) {
			for (int i = 0; i <= arr.size() - 1; i++) {
				DistanceMatrix distanceMatrixPojo = new DistanceMatrix();
				distanceMatrixPojo.setSourceLocationId(j);
				distanceMatrixPojo.setDestinationLocationId(i);
				distanceMatrixPojo.setDistance(arr.get(j).get(i));
				distanceMatrixPojoList.add(distanceMatrixPojo);
			}
		}
		return distanceMatrixPojoList;
	}

	private static List<DistanceMatrix> createDistanceMatrix(List<List<Integer>> arr, List<Integer> locationMap) {

		List<DistanceMatrix> matrices = new ArrayList<>();
		for (int i = 0; i <= arr.size() - 1; i++) {
			for (int j = 0; j <= arr.size() - 1; j++) {
				DistanceMatrix distanceMatrixPojo = new DistanceMatrix();
				distanceMatrixPojo.setSourceLocationId(locationMap.get(i));
				distanceMatrixPojo.setDestinationLocationId(locationMap.get(j));
				distanceMatrixPojo.setDistance(arr.get(i).get(j));
				matrices.add(distanceMatrixPojo);
			}
		}
		return matrices;
	}
}
