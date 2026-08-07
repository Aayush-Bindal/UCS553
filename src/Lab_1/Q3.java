package Lab_1;

import java.util.Scanner;

public class Q3 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		System.out.print("Enter a non-negative integer: ");
		int n = sc.nextInt();

		if (n < 0) {
			System.out.println("Factorial is not defined for negative numbers.");
			sc.close();
			return;
		}

		long factorial = 1;
		for (int i = 2; i <= n; i++) {
			factorial *= i;
		}

		System.out.println("Factorial of " + n + " = " + factorial);
		sc.close();
	}
}
