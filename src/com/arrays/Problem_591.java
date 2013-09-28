package com.arrays;

import java.util.Arrays;
import java.util.Scanner;

import static java.lang.System.out;

/**
 * Time taken approx 15 mins.
 * Mistake: Did not read this condition in question: 'Output a blank line after each set'
 */
public class Problem_591 {
	
	public static final boolean DEBUG = false;
	
    public static void main(String[] args) {
    	 double _stopWatchStart = getTime();
    	 
    	 Scanner in = new Scanner(System.in);
    	 
    	 int set = 1;
    	 while(in.hasNext()) {
    		 
    		 int stackCount = in.nextInt();
    		 if(stackCount == 0) break;
    		 
    		 int[] stacks = new int[stackCount];
    		 int sum = 0;
    		 int myOutput = 0;
    		 
    		 for(int i=0; i<stackCount; i++) {
    			 int height = in.nextInt();
    			 sum += height;
    			 stacks[i] = height;
    		 }
    		 
    		 int avgHeight = sum / stackCount;
    		 
    		 for(int i=0; i<stacks.length; i++) {
    			 stacks[i] -= avgHeight;
    			 
    			 if(stacks[i] < 0) 
    				 myOutput += stacks[i] * -1;
    		 }
    		 
    		 
    		 out.print("Set #" + set + "\n");
    		 out.print("The minimum number of moves is " + myOutput + ".\n\n");
    		 set++;
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
