package com.utils;

public class BinaryUtils {
	
	
	
	/**
    * Given n, Returns a binary string that has been reversed.
    * @param n
    * @return reversed binary number in integer
    * eg: 1101 -> 1011 ( which corresponds to number 11 )
    */
    public static int reverseBinary(int n) { 
    	int reversed = 0;
		
		int digits = getBinaryDigits(n);
		for(int i=digits-1; i>=0; i--){ // iterate from LHS of Var: reversed 
			if( (1 & n) == 1 ) {
				reversed = reversed | (1<< i); // Switch on bit to 1 from LHS  
			}
			
			n = (n >> 1); 
		}
		
		return reversed;
    }

	  
    /**
     * Returns amount of binary digits places if given integer n 
     * @param n 
     * @return number of binary digit places. ie, given n = 13, binary = 1101, 
     * number of binary places = 4 
     */
    public static int getBinaryDigits( int n ) { 
    	if( n == 0 || n == 1) return 1; 
    	
    	int k = 0;
    	while( n - (1<<k) >= 0) { 
    		k++;
    	}
    	
    	return k; 
    }

}
