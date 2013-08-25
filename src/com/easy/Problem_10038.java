package com.easy;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Mistakes: 
 * - Forget to reset isJolly flag after each loop :-(
 * - Did not account for single input ! eg: input "1 1"
 * 
 * Overall method: Simply ensure that the difference between successive element are in running order!
 * optimisation: use a 1D bitfield, or bit mask instead of a normal array to check if its in running order
 * (use JAVA BitSet )
 *
 */
public class Problem_10038 {

  //start 4:15 end 5:10
	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		
		while(in.hasNext()) {
			boolean isJolly = true;
			int count = in.nextInt();
			
			// Single instance ! 
			if(count == 1) {
				int solo = in.nextInt();
				if(solo != 1)
					isJolly = false; 
			}else {
				
				// read sequence
				int[] seq = new int[count];
	 			int[] diff = new int[count-1];
	 			
				for(int i=0; i<count; i++) {
					seq[i] = in.nextInt();				
				}
				
				// store differences 
				for(int i=0; i<count-1; i++) {
					diff[i] = Math.abs(seq[i+1] - seq[i]);
					//System.out.println("diff: " + diff[i]);
				}
				
				Arrays.sort(diff);
				
				// ensure diffs are in running order 
				for(int i=0; i<count-2; i++) {
					if(diff[i] + 1 != diff[i+1] ){
						//System.out.println("not jolly @: " + diff[i] + " i: " + i);
						isJolly = false;
						break;
					}
				}
				
				// ensure starting base is numerical 1
				if(diff[0] != 1)
					isJolly = false; 
			}
			
			
			if(isJolly)
				System.out.print("Jolly\n");
			else 
				System.out.print("Not jolly\n");
				
		}
        
	}

}