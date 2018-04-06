package exercises;

import java.util.Arrays;

public class Main1 {

	
	public static void main(String[] args) {
		
		int[][] tab = {{3,2,3,},{1,2,3},{1,2,3}};
		
		int[] numb = {3,2,1,3};
		
		System.out.println(matrixTrace(tab));
		System.out.println(mostNumbers(numb));
		System.out.println(time("03:05:45PM"));
		
	}
	
	
	
	
	
	static int matrixTrace(int[][] a) {
        int diLeft = 0;
        int diRight = 0;
        for(int i =0; i< a.length;i++){
            for(int j = 0; j < a[i].length; j++){
             if(i==j){
                 diLeft+=a[i][j];
                 System.out.println("diLeft "+ diLeft);
             }   
             if(j==a.length-1-i){
                 diRight+=a[i][j];
                 System.out.println("diRight "+ diRight);
             }           
            }
        }
        return Math.abs(diLeft-diRight);

    }
	
	private static int mostNumbers (int[] ar) {
        Arrays.sort(ar);
        int sum = 0, temp = 0;
        for(int i = 0; i < ar.length; i++){
            if(i+1 != ar.length && ar[i] == ar[i+1]){ // element is not equal array length and is eqauls next element
                sum++;                
            } else{
            	sum++;
            	if(temp < sum){  
                    temp = sum;                
                }
                sum=0;               
            }
            
        }
        System.out.println("temp "+ temp);
        return temp;
    }
	
	
	private static String time(String s) {
        if(s.substring(s.length()-2, s.length()).equalsIgnoreCase("pm") && !s.substring(0,2).equalsIgnoreCase("12")){ // if we have a pm at the end and we dont have 12 hour 
           return String.valueOf(12 + Integer.parseInt(s.substring(0,2)))+ s.substring(2, s.length()-2);// we are adding a 12 to the hours and we are cuting off PM
        } else if(s.substring(s.length()-2, s.length()).equalsIgnoreCase("am") && s.substring(0,2).equalsIgnoreCase("12")){
            return "00"+ s.substring(2, s.length()-2);
        }
        else {
            return s.substring(0, s.length()-2);
        }
    }
	
}
