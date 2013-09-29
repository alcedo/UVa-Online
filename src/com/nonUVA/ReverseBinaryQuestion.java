package com.nonUVA;

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
 *  Task
	Your task will be to write a program for reversing numbers in binary. For instance, the binary representation of 13 is 1101, and reversing it gives 1011, which corresponds to number 11.
	
	Input
	The input contains a single line with an integer N, 1 ² N ² 1000000000.
	
	Output
	Output one line with one integer, the number we get by reversing the binary representation of N.

	Sample input 1
	13
	Sample output 1
	11

 */
public class ReverseBinaryQuestion {
	public static final boolean DEBUG = false;
	
    public static void main(String[] args) throws IOException {
    	 
		BufferedOutputStream output = new BufferedOutputStream(System.out);
		InputReader input           = new InputReader(System.in);
		StringBuilder sb = new StringBuilder(2000); //assume initial size of 2000 chars
		  
		String str;  
		while((str = input.readLine()).length() > 0) {
			
			StringTokenizer stringTokenizer = new StringTokenizer(str);
			int N = Integer.parseInt(stringTokenizer.nextToken());
			
			sb.append( reverseBinary(N) );
			sb.append("\n");
			
		}
		
		output.write(sb.toString().getBytes());
		output.flush();
		output.close();
    
    }// end of static void main
    
    
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





