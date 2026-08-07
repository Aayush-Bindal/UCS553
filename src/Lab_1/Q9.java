package Lab_1;

import java.util.Scanner;

public class Q9 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		System.out.print("Enter base: ");
		double base = sc.nextDouble();
		System.out.print("Enter exponent (integer): ");
		int exponent = sc.nextInt();

		double result = 1.0;
		int exp = Math.abs(exponent);

		for (int i = 0; i < exp; i++) {
			result *= base;
		}

		if (exponent < 0) {
			result = 1.0 / result;
		}

		System.out.println(base + " ^ " + exponent + " = " + result);
		sc.close();
	}
}
