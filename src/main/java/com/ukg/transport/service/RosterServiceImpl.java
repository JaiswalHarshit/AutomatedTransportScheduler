package com.ukg.transport.service;

import com.ukg.transport.dto.EmployeeData;
import com.ukg.transport.dto.RosterData;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RosterServiceImpl {

	public List<EmployeeData> fetchData() {

		String employeeID = "";
		String name = "";
		String contact = "";
		String gender = "";
		String locationID = "";
		String address = "";
		String pincode = "";
		String zoneID = "";
		String latitude = "";
		String longitude = "";
		String costCenter = "";
		Integer age = null;
		String interest = "";
		Object employees[] = null;
		List<EmployeeData> employeeData = new ArrayList<>();
		try {
			FileInputStream file = new FileInputStream(System.getProperty("user.dir") + "\\automated-transport-scheduling\\transport\\src\\main\\resources\\csvData\\Employee Data.xlsx");
			XSSFWorkbook workbook = new XSSFWorkbook(file);
			XSSFSheet mXSSFSheet = workbook.getSheetAt(0);
			Iterator<Row> rowIterator = mXSSFSheet.iterator();
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				if (row.getRowNum() != 0) {
					Iterator<Cell> cellIterator = row.cellIterator();
					int count = 0;
					while (cellIterator.hasNext()) {
						Cell cell = cellIterator.next();
						cell.setCellType(CellType.STRING);
						if (count == 0) {
							employeeID = cell.getStringCellValue();
						} else if (count == 1) {
							name = cell.getStringCellValue();
						} else if (count == 2) {
							contact = cell.getStringCellValue();
						} else if (count == 3) {
							gender = cell.getStringCellValue();
						} else if (count == 4) {
							locationID = cell.getStringCellValue();
						} else if (count == 5) {
							address = cell.getStringCellValue();
						} else if (count == 6) {
							pincode = cell.getStringCellValue();
						} else if (count == 7) {
							zoneID = cell.getStringCellValue();
						} else if (count == 8) {
							latitude = cell.getStringCellValue();
						} else if (count == 9) {
							longitude = cell.getStringCellValue();
						} else if (count == 10) {
							costCenter = cell.getStringCellValue();
						} else if (count == 11) {
							age = Integer.parseInt(cell.getStringCellValue());
						} else if (count == 12) {
							interest = cell.getStringCellValue();
						}
						count++;
					}
					employeeData.add(new EmployeeData(employeeID, name, contact, gender, locationID, address, pincode, zoneID, latitude, longitude, costCenter, age, interest));

				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return employeeData;
	}

	public void writeToFile(List<List<RosterData>> rostersList) throws IOException {

		List<RosterData> rosters = rostersList.stream().flatMap(Collection::stream).collect(Collectors.toList());
		FileOutputStream out = null;
		try {
			out = new FileOutputStream(System.getProperty("user.dir") + "\\automated-transport-scheduling\\transport\\src\\main\\resources\\csvData\\RouteData.xlsx");
			// workbook object
			XSSFWorkbook workbook = new XSSFWorkbook();

			// spreadsheet object
			XSSFSheet spreadsheet = workbook.createSheet("Roster");

			// creating a row object
			XSSFRow row;

			int rowid = 0;

			// writing Header
			row = spreadsheet.createRow(rowid++);
			int cellId = 0;
			Cell head = row.createCell(cellId++);
			head.setCellValue("Route Number");
			Cell head1 = row.createCell(cellId++);
			head1.setCellValue("Employee ID");
			Cell head2 = row.createCell(cellId++);
			head2.setCellValue("Name");
			Cell head3 = row.createCell(cellId++);
			head3.setCellValue("Contact");
			Cell head4 = row.createCell(cellId++);
			head4.setCellValue("Gender");
			Cell head5 = row.createCell(cellId++);
			head5.setCellValue("Interest Area");
			Cell head6 = row.createCell(cellId++);
			head6.setCellValue("Cost Center");
			Cell head7 = row.createCell(cellId++);
			head7.setCellValue("Drop Number");
			Cell head8 = row.createCell(cellId++);
			head8.setCellValue("Guard Required");
			Cell head9 = row.createCell(cellId++);
			head9.setCellValue("Address");

			// writing data
			for (RosterData data : rosters) {

				row = spreadsheet.createRow(rowid++);
				cellId = 0;
				Cell cell = row.createCell(cellId++);
				cell.setCellValue(data.getRouteId());
				Cell cell1 = row.createCell(cellId++);
				cell1.setCellValue(data.getEmployeeId());
				Cell cell2 = row.createCell(cellId++);
				cell2.setCellValue(data.getName());
				Cell cell3 = row.createCell(cellId++);
				cell3.setCellValue(data.getContact());
				Cell cell4 = row.createCell(cellId++);
				cell4.setCellValue(data.getGender());
				Cell cell5 = row.createCell(cellId++);
				cell5.setCellValue(data.getInterest());
				Cell cell6 = row.createCell(cellId++);
				cell6.setCellValue(data.getCostCenter());
				Cell cell7 = row.createCell(cellId++);
				cell7.setCellValue(data.getDropNumber());
				Cell cell8 = row.createCell(cellId++);
				cell8.setCellValue(data.isGuardRequired());
				Cell cell9 = row.createCell(cellId++);
				cell9.setCellValue(data.getAddress());
			}

			// .xlsx is the format for Excel Sheets...
			// writing the workbook into the file...

			System.out.println("File written successfully");

			workbook.write(out);
		} catch (Exception ex) {
			out.close();
		}

	}
}
