package com.arrays;

import java.util.Arrays;
import java.util.Scanner;

import static java.lang.System.out;

/**
 * spent 3 hours doing this BUT TLE! :( 
 * main idea is to calculate the cycles between each green appearance.
 * 
 * discrete time steps of 1 second is simply too slow...
 * must figure out a way to optimise / re-write algorithm
 */
// sln: https://github.com/infnty/acm/blob/master/acm.uva.es/467.c
public class Problem_467 {
	public static final boolean DEBUG = false;
	
    public static void main(String[] args) {
    	 double _stopWatchStart = getTime();
    	 
    	 Scanner in = new Scanner(System.in);
    	 int signalSet = 1; 
    	 final int ONE_HOUR = 60*60;
    	 final int YELLOW_TIME = 5;
    	 
         while(in.hasNext()) {
        	 String line = in.nextLine();
        	 String[] tokens = line.split(" ");
        	 int[] signals = new int[tokens.length];
        	 int bitmask = 0;
        	 boolean isSync = false;
        	 int syncTime = 0;
        	 boolean firstAllGreen = false; 
        	 
        	 for(int i=0; i<tokens.length; i++){
        		 signals[i] = Integer.parseInt(tokens[i]);
        	 }
        	 
        	 Arrays.sort(signals);
        	 int startTime = signals[0] - YELLOW_TIME;
        	 
        	 //clock OPTIMISE HERE!
        	 for(int seconds=startTime*2; seconds<=ONE_HOUR+1; seconds++) {
                		 
        		 myDebug("*********************");
        		 myDebug("t: " + seconds);
        		 for(int i=0; i<signals.length; i++) {
        			
        			 int cycleTime = signals[i];
        			 int isMultiple = seconds % (cycleTime*2);
        			 
        			 if(isMultiple > 0 && isMultiple <= cycleTime - YELLOW_TIME) {
        				 //Set bitmask for this signal to be true. (ie: its green nw!)
        				 bitmask = bitmask | (1<<i);
        				 myDebug("bitmask true[" + i + "] " + Integer.toBinaryString(bitmask) + " cycleTime: " + cycleTime);

        			 }else {
        				 //Set bitmask for this signal to be false (ie: not green!) 
        				 myDebug("bitmask false [" + i + "] " + Integer.toBinaryString(bitmask) + " cycleTime: " + cycleTime);

        				 bitmask = bitmask & ~(1<<i);
        			 }
        			 
        			 myDebug("\n");
        		 }
        		 myDebug("*********************");
        		 
        		 if(bitmask == (1<<signals.length) -1 ) {
        			 myDebug("break at: " + Integer.toBinaryString(bitmask));
        			 isSync = true;
        			 syncTime = seconds;
        			 break;
        		 }
        	 }
        	 
        	 // OUTPUT RESULTS !
        	 
        	 syncTime = syncTime -1; // subtract 1 second to get 1 second baseline before all turns green
        	 int mins = syncTime / 60;
        	 int secs = syncTime % 60;
        	 myDebug("syncTime: " + syncTime);
        	 
        	 if(syncTime <= 0)
        		 out.print("Set " + signalSet + " is unable to synch after one hour.\n");
        	 else 
        		 out.print("Set " + signalSet + " synchs again at " + mins + " minute(s) and " + secs + " second(s) after all turning green.\n");

        	 
        	 //next set
        	 signalSet++;
         }
    	 
         double _stopWatchStop = getTime();
         double _elapsedTime = _stopWatchStop - _stopWatchStart;
         //out.println("RunTime: " + _elapsedTime);
         
    }
   
    // DEBUG FUNCTIONS    
    public static void myDebug(String text) {
    	if(DEBUG) out.println(text);
    }
    
    public static double getTime() {
        return 0.001*System.currentTimeMillis();
    }
    
    
    
}//End of Main Class


//43 67 26 55 18 67
//58 59 29 31 71 75 62 86 59
//46 73 24 22 73 39 61 42 60 15
//64 41 32 69 59
//66 41 79 81 15 64 43
//42 89 90 48 50 56 60 68 10 27
//58 74 60 28 67 86 71 58 72
//12 50 59 36 50
//40 17 89 10 57 59
//76 68 46 74 17 36 77
//27 64 78 25 52 27 53 44 34
//61 41 55 63 34 79 88
//83 32
//44 12 64 84 54 38 88 90 32
//58 89 82 40
//23 86 65 73 62 39 74
//83 85
//22 48
//26 30 75 88 77 12
//16 40 14 10 63 59 80 28 63 53



//Set 1 synchs again at 11 minute(s) and 28 second(s) after all turning green.
//Set 2 is unable to synch after one hour.
//Set 3 is unable to synch after one hour.
//Set 4 synchs again at 4 minute(s) and 36 second(s) after all turning green.
//Set 5 synchs again at 3 minute(s) and 0 second(s) after all turning green.
//Set 6 synchs again at 10 minute(s) and 0 second(s) after all turning green.
//Set 7 is unable to synch after one hour.
//Set 8 synchs again at 2 minute(s) and 24 second(s) after all turning green.
//Set 9 synchs again at 4 minute(s) and 0 second(s) after all turning green.
//Set 10 synchs again at 5 minute(s) and 8 second(s) after all turning green.
//Set 11 is unable to synch after one hour.
//Set 12 synchs again at 53 minute(s) and 18 second(s) after all turning green.
//Set 13 synchs again at 3 minute(s) and 12 second(s) after all turning green.
//Set 14 synchs again at 15 minute(s) and 12 second(s) after all turning green.
//Set 15 synchs again at 4 minute(s) and 0 second(s) after all turning green.
//Set 16 synchs again at 52 minute(s) and 8 second(s) after all turning green.
//Set 17 synchs again at 2 minute(s) and 50 second(s) after all turning green.
//Set 18 synchs again at 1 minute(s) and 36 second(s) after all turning green.
//Set 19 synchs again at 6 minute(s) and 4 second(s) after all turning green.
//Set 20 synchs again at 53 minute(s) and 20 second(s) after all turning green.
