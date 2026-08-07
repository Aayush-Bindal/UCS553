package Lab_1;

import java.util.Scanner;

public class Q5 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		System.out.print("Enter first number: ");
		int a = sc.nextInt();
		System.out.print("Enter second number: ");
		int b = sc.nextInt();

		int x = Math.abs(a);
		int y = Math.abs(b);

		while (y != 0) {
			int temp = y;
			y = x % y;
			x = temp;
		}

		System.out.println("GCD of " + a + " and " + b + " = " + x);
		sc.close();
	}
}
