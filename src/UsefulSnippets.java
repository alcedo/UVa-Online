import static java.lang.System.out;



public class UsefulSnippets {

	public static final boolean DEBUG = false;
	

	
	
	
	
    /**************************************************************************
     *  Math related Functions
     **************************************************************************/
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
