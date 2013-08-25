import java.util.Arrays;
import java.util.Scanner;

import static java.lang.System.out;

/**
 * Use this file to submit. UVA only accepts class Main
 */
public class Main {
	public static final boolean DEBUG = false;
	
    public static void main(String[] args) {
    	 double _stopWatchStart = getTime();
    	 
    	 Scanner in = new Scanner(System.in);
    	 while(in.hasNext()) {
    		 
    		 
    	 }
    	 
         double _elapsedTime = getTime() - _stopWatchStart;
         //out.println("RunTime: " + _elapsedTime);
    
    
    
    
    }// end of static void main
    
    
    
    
    /**************************************************************************
     *  Helper Functions
     **************************************************************************/
    // GCD for arrays
    public static int gcd(int[] input) {
    	int result = input[0];
        for(int i = 1; i < input.length; i++) result = gcd(result, input[i]);
        return result;
    }
    
    //FAST GCD due to bitwise manipulations
    public static int gcd(int a, int b)
    {
        while(b>0) b ^= a ^= b ^= a %= b;
        return a;
    }
    // Normal euclidean GCD 
    public static int GCD(int a, int b) {
    	   if (b==0) return a;
    	   return GCD(b,a%b);
    }
    
    //GCD LONG 
    public static long gcd(long[] input) {
        long result = input[0];
        for(int i = 1; i < input.length; i++) result = GCD(result, input[i]);
        return result;
    }
    
    public static long GCD(long a, long b) {
    	   if (b==0) return a;
    	   return GCD(b,a%b);
    }
    
    // DEBUG FUNCTIONS    
    public static void myDebug(String text) {
    	if(DEBUG) out.println(text);
    }
    
    public static double getTime() {
        return 0.001*System.currentTimeMillis();
    }
    
    
    
}//End of Main Class
