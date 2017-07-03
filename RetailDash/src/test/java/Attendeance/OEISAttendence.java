package Attendeance;

public class OEISAttendence {

	public static int toatlEmployee = 25;
	public static String empID;
	public static String empName;
	public static String month;
	public static float totalWorkHours;
	public static String Path_FileName ;
	public static String monthStartDate;
	public static String monthEndDate;
	public static int startRow;
	public static int endRow;
	public static int count=0;
	public static String sheetName;
	public static int totalMonthDays= 0;
	public static void main(String[] args) {
		try {
			Path_FileName = ConstantsAttendance.Path_DataFileLocation+ConstantsAttendance.File_TestFlowData;
			ExcelUtilsAttendance.setExelFile(Path_FileName);
			sheetName = "JAN 2017";
			int totalRow = ExcelUtilsAttendance.getRowcount(sheetName);
			//monthStartDate = "01-Jan-2017";
			monthStartDate = args[0];
			monthEndDate = "31-Jan-2017";

			//MONTH START ROW
			for (int i = 0; i < totalRow; i++) {
				if (ExcelUtilsAttendance.getCellStringData(i, ConstantsAttendance.Col_Date, sheetName).equalsIgnoreCase(monthStartDate)) {
					startRow = i;
					System.out.println("Start ROW:"+startRow);
					break;
				}
			}
			//MONTH END ROW
			for (int i = startRow; i < totalRow; i++) {
				if (ExcelUtilsAttendance.getCellStringData(i, ConstantsAttendance.Col_Date, sheetName).equalsIgnoreCase(monthEndDate)) {
					endRow = i;
					System.out.println("END ROW:"+endRow);
					break;
				}
			}
			totalMonthDays = endRow-startRow;
			System.out.println("END ROW:"+(startRow+totalMonthDays));
			
			//TOTAL NUMBER OF EMPLOYEE LOOP//
			for (int m = 1;m<=25; m++) {
				totalWorkHours = 0;
				for(int i=startRow; i<=(startRow+totalMonthDays);i++){
					String value1= ExcelUtilsAttendance.getCellStringData(i, ConstantsAttendance.Col_TotalWorkHours, sheetName);
					if (!value1.equalsIgnoreCase("")) {
						    int index1 = value1.indexOf(":");
							String pre = value1.substring(0, index1);
							String pos = value1.substring(index1+1);
							String newValue= pre+"."+pos;
							Float newInterger = Float.valueOf(newValue);
							totalWorkHours = totalWorkHours+newInterger;
					}
				}
				empID = ExcelUtilsAttendance.getCellStringData(startRow, ConstantsAttendance.Col_EmpID, sheetName);
				empName = ExcelUtilsAttendance.getCellStringData(startRow, ConstantsAttendance.Col_EmpName, sheetName);
				System.out.println("ID:"+empID+"- Emp Name:"+empName+"-Total Work Hours:"+totalWorkHours);
				startRow = startRow+totalMonthDays+1;	
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
