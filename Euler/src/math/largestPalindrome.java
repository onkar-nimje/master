package math;

import javax.sound.sampled.ReverbType;

public class largestPalindrome {

	/**
	 * @param args
	 */
	//int rev;
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		largestPalindrome p=new largestPalindrome();
		int mul=0,n=0;
		int [] pali=new int[10000];
		for(int i=100;i<=999;i++)
		{
 			for(int j=100;j<=999;j++)
			{
				mul=i*j;
				
				if(mul==p.reverse(mul))
				{
					pali[n]=mul;
					n++;
				}
			}
		}
		
		
		for(int i=0;i<pali.length-1;i++)
		{
			for(int j=1;j<pali.length;j++)
			{
				if(pali[i]<pali[j])
				{
					int temp=pali[i];
					pali[i]=pali[j];
					pali[j]=temp;
				}
			}
		}
		
		//System.out.println(pali.length);
		//for(int i =0; i<=pali.length-1;i++)
		//{
		//	System.out.println(pali[i]);
		//}
		//int len=pali.length;
		System.out.println("Largest palindrome is: "+pali[0]);
		
	}
	
	public  int reverse(int num)
	{
		int rev=0;
		while(num !=0)
		{
			rev=rev*10;
			rev=rev+num%10;
			num=num/10;
		}
		
		return rev;
	}

}
