import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;


public class test1 {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
         try{
		
		//WebDriver driver= new FirefoxDriver();
		//driver.get("http://google.co.in/");
		URL url=new URL("http://uat.penfolds.com/");
		URLConnection connection=url.openConnection();
		connection.connect();
		if(connection instanceof HttpURLConnection)
		{
			HttpURLConnection http=(HttpURLConnection) connection;
			int code=http.getResponseCode();
			System.out.println(code);
		}
		
		else {
			System.out.println("errro");
		}
		
         }
         
         catch (Exception e) {
			// TODO: handle exception
        	 
        	e.printStackTrace();
		}
		
   
	}
	
}
