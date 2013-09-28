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
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.StringTokenizer;

import static java.lang.System.out;

/**
 * General comments: 
 * This question would seem much easier to do when you write down the Equation on paper. 
 * write out the calculation for case D = 2 for starters and everthing would fall in place
 * 
 * This question is infact VERY TEDIOUS / Confusing BUT very easy at the same time -.-
 * 
 * in anycase, if you can get all of the sample outputs, you would be fine, since its pretty
 * straight forward
 */
public class Problem_394 {


   
	public static void main(String[] args) throws IOException {
		BufferedOutputStream output = new BufferedOutputStream(System.out);
	 	InputReader input 			= new InputReader(System.in);
	 	StringBuilder sb = new StringBuilder(25000);
	 	
	 	int numArrays;
	 	int numRefs; 
	 	Data[] inputs;
	 	
	 	
		String str = input.readLine();
		StringTokenizer stringTokenizer = new StringTokenizer(str);

		numArrays = Integer.parseInt(stringTokenizer.nextToken());
		numRefs   = Integer.parseInt(stringTokenizer.nextToken());
		inputs = new Data[numArrays];
		
//		System.out.println(numArrays + " " + numRefs);
		
		// READ IN DATA 
		for(int i=0; i<numArrays; i++) { 
			str = input.readLine();
			stringTokenizer = new StringTokenizer(str);

			// read in dataset 
			String name = stringTokenizer.nextToken();
			int baseAdd = Integer.parseInt(stringTokenizer.nextToken());
			int CD = Integer.parseInt(stringTokenizer.nextToken());
			int D = Integer.parseInt(stringTokenizer.nextToken());
			inputs[i] = new Data(name, baseAdd,CD, D);

			// read in bounds 
			int[] bounds = new int[D*2]; //D pairs of bounds !
			for(int j=0; j<bounds.length; j+=2) { 
				bounds[j] = Integer.parseInt(stringTokenizer.nextToken());
				bounds[j+1] = Integer.parseInt(stringTokenizer.nextToken());
			}
			
			inputs[i].setBounds(bounds);
			
			//System.out.println(inputs[i]);
		}
		
		
		int[] results = new int[numRefs]; // 1 result for each references 
		// CALCULATE each reference query  
		for(int i=0; i<numRefs; i++) { 
			
			str = input.readLine();
			stringTokenizer = new StringTokenizer(str);
			String name = stringTokenizer.nextToken();
			int[] myRef;
			
			for(int j=0; j<inputs.length; j++) {  // find the correct data set for this particular query 
				if( inputs[j].name.compareTo(name) == 0 ) { 
					Data currData = inputs[j];
					
					myRef = new int[currData.D];
					for(int k=0; k<currData.D; k++)  //read query inputs  
						myRef[k] = Integer.parseInt(stringTokenizer.nextToken());
					
					// Store C.D  
					int[] cArray = new int[currData.D];
					cArray[currData.D -1] = currData.CD; 
					
					// calculate C.D-1 to C.1 
					for(int q=currData.D-1; q>=1; q--) {
						cArray[q-1] = cArray[q] * (currData.upperBound[q] - currData.lowerBound[q] + 1 );
					}
					
					// calc C.0
					int c0 = currData.baseAdd;
					for(int q=0; q<currData.D; q++) { 
						c0 -= ( cArray[q] * currData.lowerBound[q] );
					}
			
					sb.append(name).append("[");
					
					// calculate final answer 
					int ans = c0;
					for(int k=0; k<currData.D; k++) {
						sb.append(myRef[k]);
						if(k+1 != currData.D) sb.append(", ");
						
						ans += ( myRef[k]  * cArray[k] );
					}
					
					sb.append("] = ").append(ans);
					
//					System.out.println("Ans: " + ans); 
					sb.append("\n");
				}
			}
		}
		
	
		
	   
		output.write(sb.toString().getBytes());
		output.flush(); // flush and display 
		output.close();
	        
	}//end of void main 
	
	public static class Data { 
		
		public String name; 
		public int baseAdd;
		public int CD;
		public int D;
		public int[] bounds;
		
		public int[] lowerBound; 
		public int[] upperBound; 
		
		public Data (String name, int baseAdd, int CD, int D) { 
			this.name = name;
			this.baseAdd = baseAdd;
			this.CD = CD;
			this.D = D;
		}
		
		public void setBounds(int[] bounds) { 
			this.bounds = bounds;
			
			lowerBound = new int[bounds.length/2];
			upperBound = new int[bounds.length/2];
			int dimension = 0; 
			for(int i=0; i<bounds.length; i+=2) {
				lowerBound[dimension] = bounds[i];
				upperBound[dimension] = bounds[i+1];
				dimension++;
			}
		}
		
		public String toString() { 
			String s = "name: " + name + " baseAdd: " + baseAdd + " CD: " + CD + " D: " + D + " bounds: "; 
			for(int i=0; i<bounds.length; i++) {
				s = s + bounds[i] + " ";
			}
			
			return s;
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
