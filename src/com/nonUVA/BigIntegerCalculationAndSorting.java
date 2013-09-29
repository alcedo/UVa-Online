package com.nonUVA;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.StringTokenizer;

import static java.lang.System.out;

/**
import java.math.*; 
Declaring A BigInteger Variable (4 Examples)
 
BigInteger bigInt0 = BigInteger.ZERO; 
BigInteger bigInt1 = BigInteger.ONE; 
BigInteger bigInt3 = new BigInteger ("3"); 
BigInteger bigInt5 = BigInteger.valueOf(5);

 
Sorting:

public static class SongsComparator implements Comparator<Song> {

    	// if o1 > o2, returns +ve 
    	// if o1 < o2 returns -ve 
		@Override
		public int compare(Song s1, Song s2) {
			//return s1.quality - s2.quality; // asc order. 
			return s2.quality.compareTo(s1.quality);
		} 
    	
}
    
// SORT ! 
Collections.sort(album, new SongsComparator());
    
*/

/**
 * taken from:
 * https://www.spotify.com/us/jobs/tech/zipfsong/
 * 
 * Summary of problem:
 * The first line of input contains two integers n and m (1 � n � 50000, 1 � m � n), 
 * the number of songs on the album, and the number of songs to select. 
 * Then follow n lines.
 * 
 * Each n lines is of format type:
 * frequency, SongName
 * 
 * Calculate the quality for each Song with the Equation:  frequency * position within the list. 
 * 
 * Output a list of the m songs with the highest quality,
 * in decreasing order of quality. If two songs have the same quality, 
 * give precedence to the one appearing first on the album. (*note this means in-order sort)  
 * 
 * TRICKY part of this question is that the final Quality calculated can be VERY LARGE, resulting
 * in integer overflow. Hence we use big integer to solve.
 *
Sample input: 
4 2    
30 one > Quality = 30 * 1
30 two > Q = 30 * 2
15 three > Q = 15 * 3
25 four  > Q = 25 * 4

Output:
four
two

Because Song "four" has highest quality, followed by "two";
we output 2 songs only, coz m = 2 

Sample input:
15 3
197812 re_hash
78906 5_4
189518 tomorrow_comes_today
39453 new_genious
210492 clint_eastwood
26302 man_research
22544 punk
19727 sound_check
17535 double_bass
18782 rock_the_house
198189 19_2000
13151 latin_simone
12139 starshine
11272 slow_country
10521 m1_a1

Sample output:
19_2000
clint_eastwood
tomorrow_comes_today

 * 
 */
public class BigIntegerCalculationAndSorting {
	public static final boolean DEBUG = false;
	
    public static void main(String[] args) throws IOException {
    	 
		BufferedOutputStream output = new BufferedOutputStream(System.out);
		InputReader input           = new InputReader(System.in);
		StringBuilder sb = new StringBuilder(2000); //assume initial size of 2000 chars
		  
		String str = input.readLine(); 
		StringTokenizer stringTokenizer = new StringTokenizer(str);
		int nSongs = Integer.parseInt(stringTokenizer.nextToken());
		int mSelect = Integer.parseInt(stringTokenizer.nextToken());
		
		ArrayList<Song> album = new ArrayList<Song>();
		for(int i=1; i<nSongs+1; i++) { 
			str = input.readLine();
			stringTokenizer = new StringTokenizer(str);
			
			String freq = stringTokenizer.nextToken();
			String name = stringTokenizer.nextToken();
			
			album.add(new Song(name,i,freq));
		}
	
		Collections.sort(album, new SongsComparator());
		
		for(int i=0; i<mSelect; i++) {
			Song s = album.get(i);
			sb.append(s).append("\n");
		}
		
		output.write(sb.toString().getBytes());
		output.flush();
		output.close();
    
    }// end of static void main
    
    public static class SongsComparator implements Comparator<Song> {

    	// if o1 > o2, returns +ve 
    	// if o1 < o2 returns -ve 
		@Override
		public int compare(Song s1, Song s2) {
			//return s1.quality - s2.quality; // asc order. 
			return s2.quality.compareTo(s1.quality);
		} 
    	
    }
    
    public static class Song  { 
    	
    	String name; 
    	BigInteger quality; 
    	BigInteger posn; 
    	BigInteger freq; 
    
    	Song (String name, int position, String freq) { 
    		this.freq = new BigInteger(freq); 
    		this.name = name; 
    		this.posn = BigInteger.valueOf(position); // psn in album
    		setQuality();
    	}
    	
    	public void setQuality () {
    		this.quality = freq.multiply(posn);
    	}
    	
    	public String toString(){ 
    		return name; 
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





