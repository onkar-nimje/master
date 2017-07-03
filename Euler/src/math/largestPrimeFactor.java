package math;

public class largestPrimeFactor {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//int k=1000000;
		double k=600851475143d;
		int m=0,n=0,z=0;
		int[]fact=new int[1000];
		int[]prime=new int[1000];
		
		//for finding factor
		for(int i=1;i<k;i++)
		{
			if(k%i==0)
			{
				
				fact[m]=i;
				m++;
			   System.out.println("Factor of "+k+" is "+i);
			
	    	}
			
		}
		
		//for finding prime
		for(int j=0;j<m;j++)
		{
			int count=1;
			
			for(int l=2;l<=fact[j];l++)
			{
				if(fact[j]%l==0)
				{
					count++;
					
				}	
			}
			if(count==2)
			{
				prime[n]=fact[j];
				System.out.println(fact[j]+ " prime");
			
			}
			
		}
		
		//for finding highest prime
		for(int i=0; i<prime.length;i++)
		{
			for(int j=1;j<=i;j++)
			{
				if(prime[j]>prime[i])
				z++;
			}
		}
		
		System.out.println("Highest Prime "+prime[z]);
		

	}

}
