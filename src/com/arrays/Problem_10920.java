package com.arrays;

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
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.StringTokenizer;

import static java.lang.System.out;

/**
 * General strategy: 
 * direction in this order, up > left > Down > right 
 * 
 * int steps = 2 initially, and increment it when there's 2 directional changes
 * 
 * do a fast simulation instead of a step by step simulation. 
 * eg: {11, 22, 33, 44 .. } for the maxSteps taken  
 * 
 * Actually, for ANY form of simulation question, always try to figure out if you could do a fast simulation
 * instead of a step by step thing 
 * 
 */
public class Problem_10920 {


   
	public static void main(String[] args) throws IOException {
		BufferedOutputStream output = new BufferedOutputStream(System.out);
	 	InputReader input 			= new InputReader(System.in);
	 	StringBuilder sb = new StringBuilder(25000);
	 	
	 	// directional state 
	 	final int UP = 0;
	 	final int LEFT = 1;
	 	final int DOWN = 2;
	 	final int RIGHT = 3;
	 	
	 	int currentDir = UP; 
	 	
	 	String str;
		while ((str = input.readLine()).length() != 0) {
		    
			StringTokenizer stringTokenizer = new StringTokenizer(str);
			int size = Integer.parseInt(stringTokenizer.nextToken());
			int posn  	 = Integer.parseInt(stringTokenizer.nextToken());
			
			if(size == 0 && posn == 0)  break; 
			
			currentDir = UP;
			int dirCount = 0;
			int maxSteps = 1;
			int counter = 1;
			
			// do mapping of "tap cells -> row, col format" 
			int row = (size + 1) / 2;
			int col = (size + 1) / 2;
//			System.out.println(row + " : " + col +  " c: " + counter + " size: " + size*size);

		
			while(true) { 
				
				if(counter == posn) break;
				
				dirCount++;
				if(dirCount > 2)  { dirCount = 1; maxSteps++; } //increase max step w every 2 directional changes 
				
//				System.out.println("maxSteps: " + maxSteps);
				switch (currentDir) {
				
					case UP: 
//						System.out.println(row + " : " + col +  " c: " + counter + " d: " + "up");
						currentDir = LEFT;
						
						if(counter + maxSteps < posn) { 
							counter += maxSteps;
							row += maxSteps;
						}
						else {
							for(int i=0; i<maxSteps; i++) { 
								counter ++; 
								row++;
								if(counter == posn) break;
							}
						}
						
						break;
					case LEFT:
//						System.out.println(row + " : " + col +  " c: " + counter + " d: " + "left");
						currentDir = DOWN;
						
						if(counter + maxSteps < posn) { 
							counter += maxSteps;
							col -= maxSteps;
						}
						else {
							for(int i=0; i<maxSteps; i++) { 
								counter ++; 
								col--;
								if(counter == posn) break;
							}
						}
						
						break;
						
					case DOWN:
//						System.out.println(row + " : " + col +  " c: " + counter + " d: " + "down");
						currentDir = RIGHT;
						
						if(counter + maxSteps < posn) { 
							counter += maxSteps;
							row -= maxSteps;
						}
						else {
							for(int i=0; i<maxSteps; i++) { 
								counter ++; 
								row--;
								if(counter == posn) break;
							}
						}
						
						break;
						
					case RIGHT:
//						System.out.println(row + " : " + col +  " c: " + counter + " d: " + "right");
						currentDir = UP;
						
						if(counter + maxSteps < posn) { 
							counter += maxSteps;
							col += maxSteps;
						}
						else {
							for(int i=0; i<maxSteps; i++) { 
								counter ++; 
								col++;
								if(counter == posn) break;
							}
						}
						break;
				} // end switch 
			}//end while loop
			
			
			sb.append("Line = ").append(row).append(", column = ").append(col).append(".");	
			sb.append("\n");
		}
		
	   
		output.write(sb.toString().getBytes());
		output.flush(); // flush and display 
		output.close();
	        
	}//end of void main 
	
	public static class Cell { 
		public int row;
		public int col; 
		
		public Cell(int row, int col) { 
			this.row = row; 
			this.col = col; 
		}
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

	
}//end of class main 

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

