package exercises;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.stream.Collectors;

public class Main1 {

	
	public static void main(String[] args) {
		
		int[][] tab = {{3,2,3,},{1,2,3},{1,2,3}};
		
		int[] numb = {3,2,1,3};
		
		int[] numbers = {8,50,53,40};
		
		numbers = markss(numbers);
		System.out.println(matrixTrace(tab));
		System.out.println(mostNumbers(numb));
		System.out.println(time("03:05:45PM"));
		System.out.println(Arrays.toString(numbers));
		
		
		
		
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
	
	static int[] marks(int[] grades) {
        for(int i=0; i<grades.length; i++){
            if(grades[i]<38 || grades[i]%5==0){
                grades[i]=grades[i];
            }else if((grades[i]+2)%5==0){
                grades[i] = grades[i]+2;
            }else if((grades[i]+1)%5==0){
                grades[i] = grades[i]+1;
            }else {
                grades[i] = grades[i];
            }
        }
        return grades;
    }
	static int[] markss(int[] grades) {
		List<Integer> lista = Arrays.stream(grades).boxed().collect(Collectors.toList());
		lista = lista.stream().map( i -> { 
			if(i<38 || i%5==0) {
					return i;
		} else if((i+2)%5==0) {
				return i+2;
			} else if((i+1)%5==0) {
				return i+1;
			}
			return i;
		}
	).collect(Collectors.toList());
		grades = lista.stream().mapToInt(i -> i).toArray();
        
        return grades;
    }
	
	static void fruits(int s, int t, int a, int b, int[] app, int[] ora) {
        int sccoreA = 0, sccoreB = 0;
        /*for(int i=0; i<apples.length;i++){
            if(apples[i]+a>=s && apples[i]+a<=t){
                sccoreA++;
            }
        }
        for(int i=0; i<oranges.length;i++){
            if(oranges[i]+b>=s && oranges[i]+b<=t){
                sccoreB++;
            }
        }
        System.out.println(sccoreA + "\n" + sccoreB);*/
        
        //sccoreA = (int) Arrays.stream(app).filter(app-> app+a>=s && app+a<=t).count();
        //sccoreB = (int) Arrays.stream(ora).filter(ora-> ora+b>=s && ora+b<=t).count();
        
        System.out.println(sccoreA + "\n" + sccoreB);
    }
	
	
	
}
