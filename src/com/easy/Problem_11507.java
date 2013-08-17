package com.easy;

import java.util.Scanner;

import static java.lang.System.*;

/**
 * Solved using state machines.
 * Wrote out all state on a piece of paper Table first, 
 * and realizing that opposite bend directions are simply the inverse of the 
 * other columns entries 
 * 
 * Time taken: 1hr30min 
 * Reason: 
 * Forgot to input a 0 at the end of file in my personal test case, 
 * resulting in weird null errors
 * 
 * Forgetting JAVA syntax on switch statements, and String compare. 
 * use String.equals to compare instead of '==' !!!
 * http://stackoverflow.com/questions/513832/how-do-i-compare-strings-in-java
 * 
 * @author Victor
 */
public class Problem_11507 {

	 public static void main(String[] args) {
	    	Scanner in = new Scanner(System.in);
	        
	    	int length = in.nextInt();
	    	String state = "+x";
	    	
	        while(0 != length) {

	        	// Read bend direction
	        	for(int i=0; i<length-1; i++) {
	        		String bend = in.next();

	        		if(bend.equals("No")) continue;
	        		
	        		// X
	        		if(state == "+x") {
	        			state = x_positive(bend);
	        			continue;
	            	}
	        	
	        		if(state == "-x") {
	        			state = x_negative(bend);
	        			continue;
	            	}
	        		
	        		// Y
	        		if(state == "+y") {
	        			state = y_positive(bend);
	        			continue;
	            	}
	        		
	        		if(state == "-y") {
	        			state = y_negative(bend);
	        			continue;
	            	}
	        		
	        		// Z
	        		if(state == "+z") {
	        			state = z_positive(bend);
	        			continue;
	            	}

	        		if(state == "-z") {
	        			state = z_negative(bend);
	        			continue;
	            	}
	        		
	        	}// end of bends
	        	
	        	out.println(state);
	        	
	        	// read next wire length
	        	length = in.nextInt();
	        	
	        	//reset state 
	        	state = "+x";
	        	
	        }// End of computation
	        
	        
	    }
	    
	    // X - States 
	    public static String x_positive (String bend ) {
	    	
	    	if("+y".equals(bend)){
	    		return "+y";
	    	}
	    	
	    	if("-y".equals(bend)){
	    		return "-y";
	    	}
	    	
	    	if(bend.equals("+z")){
	    		return "+z";
	    	}

	    	if("-z".equals(bend)){
	    		return "-z";
	    	}
	    	
	    	return null;
	    }
	    
	    public static String x_negative (String bend ) {
	    	
	    	if("+y".equals(bend)){
	    		return "-y";
	    	}
	    	
	    	if("-y".equals(bend)){
	    		return "+y";
	    	}
	    	
	    	if("+z".equals(bend)){
	    		return "-z";
	    	}

	    	if("-z".equals(bend)){
	    		return "+z";
	    	}
	    	
	    	return null; 
	    }
	    
	    // Y - States
	    public static String y_positive (String bend ) {
	    	
	    	if("+y".equals(bend)){
	    		return "-x";
	    	}
	    	
	    	if("-y".equals(bend)){
	    		return "+x";
	    	}
	    	
	    	if("+z".equals(bend)){
	    		return "+y";
	    	}

	    	if("-z".equals(bend)){
	    		return "+y";
	    	}
	    	
	    	return null; 
	    	
	    }
	    public static String y_negative (String bend ) {

	    	if("+y".equals(bend)){
	    		return "+x";
	    	}
	    	
	    	if("-y".equals(bend)){
	    		return "-x";
	    	}
	    	
	    	if("+z".equals(bend)){
	    		return "-y";
	    	}

	    	if("-z".equals(bend)){
	    		return "-y";
	    	}
	    	
	    	return null; 
	    }
	    
	    
	    // Z - States 
	    public static String z_positive (String bend ) {
	    	
	    	if("+y".equals(bend)){
	    		return "+z";
	    	}
	    	
	    	if("-y".equals(bend)){
	    		return "+z";
	    	}
	    	
	    	if("+z".equals(bend)){
	    		return "-x";
	    	}

	    	if("-z".equals(bend)){
	    		return "+x";
	    	}
	    	
	    	return "Z_positive err"; 
	    }
	    
	    public static String z_negative (String bend ) {
	    	
	    	if("+y".equals(bend)){
	    		return "-z";
	    	}
	    	
	    	if("-y".equals(bend)){
	    		return "-z";
	    	}
	    	
	    	if("+z".equals(bend)){
	    		return "+x";
	    	}

	    	if("-z".equals(bend)){
	    		return "-x";
	    	}
	    	
	    	return "z_negative error"; 
	    }
}
