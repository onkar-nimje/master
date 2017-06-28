package executionEngine;


import java.awt.Font;
import java.awt.datatransfer.Clipboard;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import javax.swing.JPanel;

import org.jfree.chart.*;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFPicture;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.Workbook;

public class PieChartResult extends ApplicationFrame {
	public static String automationDate = new SimpleDateFormat("MM-dd-yyyy_HH-ss").format(new GregorianCalendar().getTime());;
	public static String path = "D://workspaces//Selenuim//REIMU//src//dataEngine//CopyLogin.xls";
	public PieChartResult(String title,int pass,int fail,String filePath,String automationDate) throws IOException {
		super(title);
		this.automationDate = automationDate;
		//setContentPane(createDemoPanel(pass,fail));	
		CreatePieChartInExcel(pass,fail,filePath);
	}
	
	
	public static PieDataset createDataset(int pass,int fail){
		DefaultPieDataset dataset = new DefaultPieDataset();
		dataset.setValue("FAIL", new Double(fail));  //it is in RED color
		dataset.setValue("PASS", new Double(pass));  //it is in BLUE color
		return dataset;
	}
	public static JFreeChart createChart(PieDataset dataset){
		JFreeChart chart = ChartFactory.createPieChart(
				"Automation Result in PIE-Chart Format: "+automationDate,
				dataset,
				true,
				true, 
				false
		);
		PiePlot plot= (PiePlot) chart.getPlot();
		plot.setLabelFont(new Font("SansSerif", Font.PLAIN, 12));
		plot.setNoDataMessage("No data available");
		plot.setCircular(false);
        plot.setLabelGap(0.02);
        return chart;
	}
	public static JPanel createDemoPanel(int pass,int fail){
		JFreeChart chart = createChart(createDataset(pass,fail));
		return new ChartPanel(chart);
	}
	
	public static void CreatePieChartInExcel(int pass, int fail, String filePath) throws IOException{
		FileInputStream chart_file_input = new FileInputStream(new File(filePath));
        HSSFWorkbook my_workbook = new HSSFWorkbook(chart_file_input);
        //HSSFSheet my_sheet = my_workbook.getSheetAt(0);
        HSSFSheet my_sheet = my_workbook.getSheet("Result");
        
        /* Create a logical chart object with the chart data collected */
        JFreeChart myPieChart=ChartFactory.createPieChart("Test Result in PIE-Chart Format: "+automationDate,createDataset(pass,fail),true,true,false);
        /* Specify the height and width of the Pie Chart */
        int width=640; /* Width of the chart */
        int height=480; /* Height of the chart */
        float quality=1; /* Quality factor */
        /* We don't want to create an intermediate file. So, we create a byte array output stream 
        and byte array input stream
        And we pass the chart data directly to input stream through this */             
        /* Write chart as JPG to Output Stream */
        ByteArrayOutputStream chart_out = new ByteArrayOutputStream();          
        ChartUtilities.writeChartAsJPEG(chart_out,quality,myPieChart,width,height);
        /* We now read from the output stream and frame the input chart data */
        /* We don't need InputStream, as it is required only to convert the output chart to byte array */
        /* We can directly use toByteArray() method to get the data in bytes */
        /* Add picture to workbook */
        int my_picture_id = my_workbook.addPicture(chart_out.toByteArray(), Workbook.PICTURE_TYPE_JPEG);                
        /* Close the output stream */
        chart_out.close();

        /* Create the drawing container */
        HSSFPatriarch drawing = my_sheet.createDrawingPatriarch();
        /* Create an anchor point */
        ClientAnchor my_anchor = new HSSFClientAnchor();
        /* Define top left corner, and we can resize picture suitable from there */
        my_anchor.setCol1(1);
        my_anchor.setRow1(5);
        /* Invoke createPicture and pass the anchor point and ID */
        HSSFPicture  my_picture = drawing.createPicture(my_anchor, my_picture_id);
        /* Call resize method, which resizes the image */
        my_picture.resize();
        /* Close the FileInputStream */
        chart_file_input.close();               
        /* Write Pie Chart back to the XLSX file */
        FileOutputStream chart_file_output = new FileOutputStream(new File(filePath));
        my_workbook.write(chart_file_output);
        chart_file_output.close();  
	}
	
	public static void main(String[] args) throws Exception {
		PieChartResult demo = new PieChartResult("Resultant PIE CHART",40,60,path,automationDate);
		demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);        
	}
	
}
