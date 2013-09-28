package com.arrays;
// USE ECLIPSE TO UNCOMMENT ALL 
//package com.arrays;
//
//
//import java.io.*;
//import java.util.InputMismatchException;
//import java.util.StringTokenizer;
//
//class Problem_12356 {
//
//    public static void main(String[] args) throws IOException {
//        //BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
//        //BufferedWriter output = new BufferedWriter(new OutputStreamWriter(System.out));
//        //BufferedInputStream input = new BufferedInputStream(System.in);
//        BufferedOutputStream output = new BufferedOutputStream(System.out);
//    	InputReader input 			= new InputReader(System.in);
//        
//        int[] L = new int[100002];
//        int[] R = new int[100002];
//        
//        String str;
//        while ((str = input.readLine()) != null) {
//        	
//            StringTokenizer stringTokenizer = new StringTokenizer(str);
//            int numSoldiers = Integer.parseInt(stringTokenizer.nextToken());
//            int loss = Integer.parseInt(stringTokenizer.nextToken());
//            if (numSoldiers == 0 && loss == 0) break;
//            StringBuilder stringBuilder = new StringBuilder(25000);
//            final int NIL = 0;
//
//            L[0] = R[0] = NIL;
//            for (int i = 1; i <= numSoldiers; ++i) {
//                L[i] = i - 1;
//                R[i] = i + 1;
//            }
//
//            R[numSoldiers] = NIL;
//
//
//            int lhs, rhs, sL, sR;   //sL = survivor
//            for (int i = 0; i < loss; i++) {
//                str = input.readLine();
//                stringTokenizer = new StringTokenizer(str);
//                lhs = Integer.parseInt(stringTokenizer.nextToken());
//                rhs = Integer.parseInt(stringTokenizer.nextToken());
//
//                sL = L[lhs];
//                sR = R[rhs];
//
//                if (sL == 0) stringBuilder.append('*');
//                else stringBuilder.append(sL);
//
//                stringBuilder.append(' ');
//
//                if (sR == 0) stringBuilder.append('*');
//                else stringBuilder.append(sR);
//
//                stringBuilder.append('\n');
//
//                R[sL] = sR;
//                L[sR] = sL;
//            }
//            stringBuilder.append("-\n");
//            output.write(stringBuilder.toString().getBytes());
//        }
//
//        output.flush();
//        output.close();
//    }//end of void main
//}//end of class main
//
//
///* For BufferedOutputStream following things would change
//BufferedWriter output = new BufferedWriter(new OutputStreamWriter(System.out));
//becomes
//BufferedOutputStream output = new BufferedOutputStream(System.out);
//
//output.write(stringBuilder.toString());
//becomes
//output.write(stringBuilder.toString().getBytes());
//*/
//
//class InputReader {
//
//	private InputStream stream;
//	private byte[] buf = new byte[1024];
//	private int curChar;
//	private int numChars;
//	private SpaceCharFilter filter;
//
//	public InputReader(InputStream stream) {
//		this.stream = stream;
//	}
//
//	public int read() {
//		if (numChars == -1)
//			throw new InputMismatchException();
//		if (curChar >= numChars) {
//			curChar = 0;
//			try {
//				numChars = stream.read(buf);
//			} catch (IOException e) {
//				throw new InputMismatchException();
//			}
//			if (numChars <= 0)
//				return -1;
//		}
//		return buf[curChar++];
//	}
//
//	public int readInt() {
//		int c = read();
//		while (isSpaceChar(c))
//			c = read();
//		int sgn = 1;
//		if (c == '-') {
//			sgn = -1;
//			c = read();
//		}
//		int res = 0;
//		do {
//			if (c < '0' || c > '9')
//				throw new InputMismatchException();
//			res *= 10;
//			res += c - '0';
//			c = read();
//		} while (!isSpaceChar(c));
//		return res * sgn;
//	}
//
//	public String readLine() {
//		int c = read();
//		StringBuilder res = new StringBuilder();
//		do {
//			res.appendCodePoint(c);
//			c = read();
//		} while (c != '\n');
//		return res.toString();
//	}
//	
//	public String readString() {
//		int c = read();
//		while (isSpaceChar(c))
//			c = read();
//		StringBuilder res = new StringBuilder();
//		do {
//			res.appendCodePoint(c);
//			c = read();
//		} while (!isSpaceChar(c));
//		return res.toString();
//	}
//
//	public boolean isSpaceChar(int c) {
//		if (filter != null)
//			return filter.isSpaceChar(c);
//		return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
//	}
//
//	public String next() {
//		return readString();
//	}
//
//	public interface SpaceCharFilter {
//		public boolean isSpaceChar(int ch);
//	}
//}
//
//class OutputWriter {
//	private final PrintWriter writer;
//
//	public OutputWriter(OutputStream outputStream) {
//		writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(outputStream)));
//	}
//
//	public OutputWriter(Writer writer) {
//		this.writer = new PrintWriter(writer);
//	}
//
//	public void print(Object...objects) {
//		for (int i = 0; i < objects.length; i++) {
//			if (i != 0)
//				writer.print(' ');
//			writer.print(objects[i]);
//		}
//	}
//
//	public void printLine(Object...objects) {
//		print(objects);
//		writer.println();
//	}
//
//	public void close() {
//		writer.close();
//	}
//
//	public void flush() {
//		writer.flush();
//	}
//
//	}
//
//class IOUtils {
//
//	public static int[] readIntArray(InputReader in, int size) {
//		int[] array = new int[size];
//		for (int i = 0; i < size; i++)
//			array[i] = in.readInt();
//		return array;
//	}
//
//	}
