import java.sql.*;
public class connection {

	
	public static void main(String[] args) throws Exception 
	{
		
		try{
	
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:Oracle:thin:@oeis-d02:1521:xe","vecatalogb","vecatalogb");
			Statement stm=con.createStatement();
			ResultSet s1=stm.executeQuery("select * from orders");
			while(s1.next())
			{
				
				System.out.println(s1.getString("Orderid")+ " "+ s1.getString("customerid"));
			}
		}

		catch(Exception e)
		{
			System.out.println(e);
		}
	}

}
