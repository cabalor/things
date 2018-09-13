package cal;

import java.util.Arrays;
import java.util.stream.Collectors;

public class UtilCalcClass {

	
	private UtilCalcClass() {
		throw new AssertionError();
	}
			
	
	public static double addDouble(Double... arsg) {
		
		//List<Double> doub = Arrays.asList(arsg);
		//return doub.stream().mapToDouble(i -> i.doubleValue()).sum();
		
		return Arrays.asList(arsg).stream().mapToDouble(i -> i.doubleValue()).sum();
	}
	
	public static int addInts(int... args) {
		return Arrays.stream(args).boxed().collect(Collectors.toList()).stream().mapToInt(i -> i.intValue()).sum();
		
	}
	
	
}
