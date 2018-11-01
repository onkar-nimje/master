package math;

import java.util.Scanner;

public class numberSorting {

	static int arraysize;
	static int array[];
	int temp;
	
	void numberAscendingSort(int[] a) {

		for(int i=0;i<a.length;i++)
		{
			for(int j=i+1;j<a.length;j++) {				
				if(a[j] < a[i]) {
					swapNumber(a,i,j);
				}
			}
		}
		System.out.println("::::Sorting in Ascending Order:::::");
		displayResult(a);
		
	}
	
	void numberDescendingSort(int[] a) {

		for(int i=0;i<a.length;i++)
		{
			for(int j=i+1;j<a.length;j++) {				
				if(a[j] > a[i]) {
					swapNumber(a,i,j);
				}
			}
		}
		System.out.println("::::Sorting in Descending Order:::::");
		displayResult(a);
		
	}
	
	void displayResult(int[] result) {
		for (int i = 0; i < result.length; i++) {
			System.out.println(result[i]);
		}
	}
	
	void swapNumber(int[] a,int i, int j) {
		temp = a[i];
		a[i]=a[j];
		a[j]=temp;		
	}
	
	void readUserInput() {
		System.out.println("*Number of element to sort*");
		Scanner scan= new Scanner(System.in);
		arraysize =scan.nextInt();
		array = new int[arraysize];

		System.out.println("Enter each number for sorting in new line");
		for(int i=0;i<arraysize;i++) {
			array[i]=scan.nextInt();
		}
		
	}
	
	public static void main(String[] args) {
		numberSorting obj = new numberSorting();
		obj.readUserInput();
		obj.numberAscendingSort(array);
		obj.numberDescendingSort(array);
	}

}
