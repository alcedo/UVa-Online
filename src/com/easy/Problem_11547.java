package com.easy;



import java.util.Scanner;
import static java.lang.System.*;

public class Problem_11547{
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int lines = in.nextInt();

		while (0 != lines--) {
			int value = in.nextInt();
			int current = value *567;
			current /= 9;
			current += 7492;
			current *= 235;
			current /= 47;
			current -= 498;
			current /= 10;
			current = current %10;
			out.print(Math.abs(current));
			out.print("\n");
			}
		}
}