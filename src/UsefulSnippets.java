import static java.lang.System.out;

import java.util.Arrays;



public class UsefulSnippets {

	public static final boolean DEBUG = false;
	
    /**************************************************************************
     *  Math related Functions
     **************************************************************************/
	
	
	/** Permutations **/
	//algo: 
	// http://codeforces.com/blog/entry/3980
	// http://en.wikipedia.org/wiki/Permutation#Generation_in_lexicographic_order
	// simply prints all permutation - to see how it works
	private static void printPermutations( Comparable[] c ) {
		System.out.println( Arrays.toString( c ) );
		while ( ( c = nextPermutation( c ) ) != null ) {
			System.out.println( Arrays.toString( c ) );
		}
	}

	// modifies c to next permutation or returns null if such permutation does not exist
	private static Comparable[] nextPermutation( final Comparable[] c ) {
		// 1. finds the largest k, that c[k] < c[k+1]
		int first = getFirst( c );
		if ( first == -1 ) return null; // no greater permutation
		// 2. find last index toSwap, that c[k] < c[toSwap]
		int toSwap = c.length - 1;
		while ( c[ first ].compareTo( c[ toSwap ] ) >= 0 )
			--toSwap;
		// 3. swap elements with indexes first and last
		swap( c, first++, toSwap );
		// 4. reverse sequence from k+1 to n (inclusive) 
		toSwap = c.length - 1;
		while ( first < toSwap )
			swap( c, first++, toSwap-- );
		return c;
	}

	// finds the largest k, that c[k] < c[k+1]
	// if no such k exists (there is not greater permutation), return -1
	private static int getFirst( final Comparable[] c ) {
		for ( int i = c.length - 2; i >= 0; --i )
			if ( c[ i ].compareTo( c[ i + 1 ] ) < 0 )
				return i;
		return -1;
	}

	// swaps two elements (with indexes i and j) in array 
	private static void swap( final Comparable[] c, final int i, final int j ) {
		final Comparable tmp = c[ i ];
		c[ i ] = c[ j ];
		c[ j ] = tmp;
	}
	
	//Sample class to be used for permutations 
	public static class Character implements Comparable<Character> { 
		public char c; 
		
		public Character(char c) {this.c = c;}
		
		public int compareTo(Character charCompare) {
			return this.c - charCompare.c;
		}	
		
		public String toString(){
			return ""+this.c;
		}
	}
	
	
	
	/** GCD  **/ 
    // GCD for arrays
    public static int gcd(int[] input) {
    	int result = input[0];
        for(int i = 1; i < input.length; i++) result = gcd(result, input[i]);
        return result;
    }
    
    //FAST GCD due to bitwise manipulations
    public static int gcd(int a, int b)
    {
        while(b>0) b ^= a ^= b ^= a %= b;
        return a;
    }
    // Normal euclidean GCD 
    public static int GCD(int a, int b) {
    	   if (b==0) return a;
    	   return GCD(b,a%b);
    }
    
    //GCD LONG 
    public static long gcd(long[] input) {
        long result = input[0];
        for(int i = 1; i < input.length; i++) result = GCD(result, input[i]);
        return result;
    }
    
    public static long GCD(long a, long b) {
    	   if (b==0) return a;
    	   return GCD(b,a%b);
    }
    
    
    
    /**************************************************************************
     *  Debug Functions
     **************************************************************************/ 
    public static void myDebug(String text) {
    	if(DEBUG) out.println(text);
    }
    
    public static double getTime() {
        return 0.001*System.currentTimeMillis();
    }
    
    
}
