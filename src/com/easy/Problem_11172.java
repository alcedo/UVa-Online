package com.easy;

import java.util.Scanner;
import static java.lang.System.*;

public class Problem_11172 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int lines = in.nextInt();

        while(0 != lines--) {
            int lhs = in.nextInt();
            int rhs = in.nextInt();

            if(lhs - rhs > 0) {    // LHS > RHS
                out.print(">");

            }
            else if(lhs - rhs < 0) {    // RHS > LHS
                out.print("<");
            }
            else {
                out.print("=");     //lhs == rhs
            }

            //formatting issues
            out.print("\n");
        }
    }
}
