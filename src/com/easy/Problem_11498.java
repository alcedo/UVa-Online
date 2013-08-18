package com.easy;

import java.util.Scanner;

import static java.lang.System.*;

/**
 * Time taken: 20mins 
 * No comments. really easy problem
 * @author Victor
 */
public class Problem_11498 {
	
	   public static void main(String[] args) {
	    	 Scanner in = new Scanner(System.in);
	         int queries;
	         
	         // division coord
	         int div_x;
	         int div_y;

	         // residence coord 
	         int res_x;
	         int res_y;
	         
	         while( (queries=in.nextInt()) !=0 ) {
	        	 div_x = in.nextInt();
	        	 div_y = in.nextInt();
	      
	        	 for(int i=0; i<queries; i++){
	            	 res_x = in.nextInt();
	            	 res_y = in.nextInt();
	            	 
	            	 if(res_x == div_x || res_y == div_y) {
	            		 out.print("divisa\n");
	            	 }
	            	 
	            	 if(res_x < div_x && res_y > div_y) {
	            		 out.print("NO\n");
	            	 }
	            	 
	        		 if(res_x > div_x && res_y > div_y) {
	        			 out.print("NE\n");
	        		 }
	        		 
	        		 if(res_x > div_x && res_y < div_y) {
	        			 out.print("SE\n");
	        		 }
	        		 
	        		 if(res_x < div_x && res_y < div_y) {
	        			 out.print("SO\n");
	        		 }
	        	 }
	        	 
	         }
	    }
}
