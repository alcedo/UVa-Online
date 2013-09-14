package com.recursiveBackTracking;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.StringTokenizer;

import static java.lang.System.out;

/**
 * Use this file to submit. UVA only accepts class Main
 */
public class Problem_624 {
	public static final boolean DEBUG = false;
	
    public static void main(String[] args) throws IOException {
    	 
		BufferedOutputStream output = new BufferedOutputStream(System.out);
		InputReader input           = new InputReader(System.in);
		StringBuilder sb = new StringBuilder(2000); //assume initial size of 2000 chars
		  
		String str;  
		while((str = input.readLine()).length() > 0) {
			
			StringTokenizer stringTokenizer = new StringTokenizer(str);
			
			
			int tapeCapacity = Integer.parseInt(stringTokenizer.nextToken());
			int trackCount = Integer.parseInt(stringTokenizer.nextToken());
			int[] tracks = new int[trackCount];
			for(int i=0; i<tracks.length; i++) { 
				tracks[i] = Integer.parseInt(stringTokenizer.nextToken());
			}
			
			//Loop through each tracks. pick one track at a time, and recursively
			//search for a solution. The loop acts as a 'bookmark' to mark where it all
			// started. 
			for(int i=0; i<tracks.length; i++) {
				maxLength(new ArrayList<Integer>(), tracks, tapeCapacity, i);
			}
			
			if(DEBUG)	System.out.println("min: " + MIN_LENGTH);
			
			int sum = 0;
			for(int i : optimalList) {
				sum += tracks[i];
				sb.append(tracks[i]).append(" ");
			}
			
			sb.append("sum:").append(sum);
			sb.append("\n");
			
			MIN_LENGTH = Integer.MAX_VALUE;
			optimalList = new ArrayList<Integer>();
		}
		
		output.write(sb.toString().getBytes());
		output.flush();
		output.close();
    
    }// end of static void main
    
    // GLOBAL Class Variable for tracking optimal sln
    public static int MIN_LENGTH = Integer.MAX_VALUE; 
    public static ArrayList<Integer> optimalList; 
    
    // recursive backtracking method. 
    // recursively pick a track, and add it into our selected track collections
    // this method usually involves a for-loop embeded within a function. 
    // each recursive call spawns a for-loop. 
    //
    // this method also sometimes involves an "undo" operation, to BackTrack 
    //
    // @param trackIndex is the track that we are picking. 
    public static void maxLength(
    		ArrayList<Integer> selectedTracks, 
    		int[] tracks,
    		int tapeRemain, int pickedIndex ) {
    	
    	if(DEBUG) { 
        	System.out.println("----Entering Function----");
        	System.out.printf("tR: %d , pIdx: %d\n", tapeRemain, pickedIndex );
        	System.out.println("entered w:");
        	for(int i : selectedTracks) {
    			System.out.print(tracks[i] + " ");
    		}System.out.println("");
    		
    		if(pickedIndex != tracks.length)
    			System.out.println("mLen: " + MIN_LENGTH + " remain:: " + tapeRemain + " | " + (tapeRemain - tracks[pickedIndex]) + " pidX: " + pickedIndex );
    	}

		   
    	// BASE CASE
    	// if tape's remaining length can no longer contain any more tracks OR
		// we have gotten past the list of possible tracks to pick from,
		// we transfer whatever we have picked previously into our optimalSolution list. 
		// pickedIndex == tracks.length  -> means overflow. ie: exhausted all possible selections.

    	if(pickedIndex == tracks.length ||  tapeRemain - tracks[pickedIndex] < 0 ) {
    		
    		if(pickedIndex != tracks.length && DEBUG)
    			System.out.println("minlength: " + MIN_LENGTH + " tapeRemain: " + tapeRemain + " | " + (tapeRemain - tracks[pickedIndex]) + " pickedIdx: " + pickedIndex );
    		
    		if(tapeRemain < MIN_LENGTH) { // record down optimal selection of tracks, if this grp of selection less than MIN

    			optimalList = new ArrayList<Integer>(selectedTracks); //use copy-constructor to clone. safe, coz its integers
        		MIN_LENGTH = tapeRemain; //record down new MIN value 
        		
        		if(DEBUG) {
        	   		System.out.println("Selected: ");
             		for(int i : selectedTracks) 
            			System.out.print(tracks[i] + " ");
             		
            		System.out.println("new MinLength: " + MIN_LENGTH);
        		}
    		}

    	} else { 

    		//pick indicated track, and recursively try the other tracks
	    	//note: each recursive call would spawn more for-loops as bookmarks
	    	for(int i=pickedIndex +1; i<=tracks.length; i++) { 
	    		
	      		//Pick this track, and record it. 
	    		selectedTracks.add( pickedIndex );
	    		
	    		//recursive call, to try out other tracks 
	    		maxLength(selectedTracks, tracks, tapeRemain-tracks[pickedIndex], i);
	    		
	    	 	//undo selection for next loop
	    		selectedTracks.remove(selectedTracks.size()-1);
	    	}
    	}
    	
    	if(DEBUG) System.out.println("EXIT pIdx: " + pickedIndex);
    
    }
    
  

    static class InputReader {

    	private InputStream stream;
    	private byte[] buf = new byte[1024];
    	private int curChar;
    	private int numChars;
    	private SpaceCharFilter filter;

    	public InputReader(InputStream stream) {
    		this.stream = stream;
    	}

    	public int read() {
    		if (numChars == -1)
    			throw new InputMismatchException();
    		if (curChar >= numChars) {
    			curChar = 0;
    			try {
    				numChars = stream.read(buf);
    			} catch (IOException e) {
    				throw new InputMismatchException();
    			}
    			if (numChars <= 0)
    				return -1;
    		}
    		return buf[curChar++];
    	}

    	public int readInt() {
    		int c = read();
    		while (isSpaceChar(c))
    			c = read();
    		int sgn = 1;
    		if (c == '-') {
    			sgn = -1;
    			c = read();
    		}
    		int res = 0;
    		do {
    			if (c < '0' || c > '9')
    				throw new InputMismatchException();
    			res *= 10;
    			res += c - '0';
    			c = read();
    		} while (!isSpaceChar(c));
    		return res * sgn;
    	}

    	public String readLine() {
    		int c = read();
    		//theres no ASCII -1 value. this signify EOF, and we return empty string
    		if(c == -1) return ""; 
    		
    		StringBuilder res = new StringBuilder();
    		do {
    			res.appendCodePoint(c);
    			c = read();
    		} while (c != '\n' );
    		return res.toString();
    	}
    	
    	public String readString() {
    		int c = read();
    		while (isSpaceChar(c))
    			c = read();
    		StringBuilder res = new StringBuilder();
    		do {
    			res.appendCodePoint(c);
    			c = read();
    		} while (!isSpaceChar(c));
    		return res.toString();
    	}

    	public boolean isSpaceChar(int c) {
    		if (filter != null)
    			return filter.isSpaceChar(c);
    		return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
    	}

    	public String next() {
    		return readString();
    	}

    	public interface SpaceCharFilter {
    		public boolean isSpaceChar(int ch);
    	}
    }

    static class OutputWriter {
    	private final PrintWriter writer;

    	public OutputWriter(OutputStream outputStream) {
    		writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(outputStream)));
    	}

    	public OutputWriter(Writer writer) {
    		this.writer = new PrintWriter(writer);
    	}

    	public void print(Object...objects) {
    		for (int i = 0; i < objects.length; i++) {
    			if (i != 0)
    				writer.print(' ');
    			writer.print(objects[i]);
    		}
    	}

    	public void printLine(Object...objects) {
    		print(objects);
    		writer.println();
    	}

    	public void close() {
    		writer.close();
    	}

    	public void flush() {
    		writer.flush();
    	}
    }

    static class IOUtils {

    	public static int[] readIntArray(InputReader in, int size) {
    		int[] array = new int[size];
    		for (int i = 0; i < size; i++)
    			array[i] = in.readInt();
    		return array;
    	}
    }

    
    
	    
}//End of Main Class


/**************************************************************************
 *  Fast I/O functions (prevents i/o flushing by buffering)
 **************************************************************************/
//BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
//BufferedWriter output = new BufferedWriter(new OutputStreamWriter(System.out));

/* 
Slightly less efficient 
BufferedWriter output = new BufferedWriter(new OutputStreamWriter(System.out)); 
output.write(stringBuilder.toString());

More efficient: 
BufferedOutputStream output = new BufferedOutputStream(System.out); // more efficient  
output.write(stringBuilder.toString().getBytes());
*/

//or we can use the below utilitiy classes: 

/** 
* Various utility class for Fast I/O in java.  
* Readings: http://www.codechef.com/wiki/java#IO_in_Java 
* BufferedInputStream > BufferedReader > Scanner  
* 
* USAGE: 
* InputReader in 		= new InputReader(System.in);
* OutputWriter out	=	new OutputWriter(System.out);
* 
* int i = in.readInt(); //read int
* String s = in.readString(); //read string
* int[] x = IOUtils.readIntArray(in,N); //read int array of size N
* String s = in.readLine() // read a line ( to be used with tokenizer );
* 
* out.printLine("X");
*  
* out.flush(); // flush output
* out.close(); // remember to close the outputstream, at the end (might be able to not do this to save some time) 
*/





