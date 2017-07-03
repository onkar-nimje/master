package math;

public class smallestPositiveNumber {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
      
		for(int i=20;;i++)
		{
			int count=0;
			for(int j=1;j<=20;j++)
			{
				if(i%j==0)
				{
					count++;
				}
				
				else
					break;
			}
			
			if(count==20)
			{
				System.out.println("number "+i);
				break;
			}	
		}
	}

}
