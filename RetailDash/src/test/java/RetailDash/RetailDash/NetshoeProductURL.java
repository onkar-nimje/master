package RetailDash.RetailDash;
import utility.*;

public class NetshoeProductURL {

	public static final String PATH_fileName = "D://workspaces//Selenuim//RetailDash//src//test//java//dataEngine//Netshoes-Centauro Manual Mapping - 23-200 - Dominique.xlsx"; 
	public static void main(String[] args) {
		try {
			ExcelUtils.setExelFile(PATH_fileName);
			String sheetName= "100 items";
			int totalRow= ExcelUtils.getRowcount(sheetName);
			
			//for (int i = 1; i < totalRow; i++) {
			for (int i = 1; i < 3; i++) {
				String productname = ExcelUtils.getCellStringData(i, 3, sheetName);
				int productNameLength = productname.length();
				System.out.println("product name length:"+productNameLength);
				
				String words[] = new String[productNameLength];
				int k=0;
				
				for (int j = 0; j < productNameLength ; j++) {
					int index2= productname.indexOf(" ");
					System.out.println("index of blank space:"+index2);
					
					if (index2 == -1) {
						words[k] = productname;
						break;
					}
					words[k] = productname.substring(0, index2);
					String remain1 = productname.substring(index2+1);
					System.out.println("Remain name:"+remain1);
					productname=remain1;
					j=index2;
					k++;
				}
				
				for (int j = 0; j < words.length; j++) {
					System.out.println(words[j]);
				}
			}
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
