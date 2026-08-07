package Lab_1;

import java.util.Scanner;

public class Q6 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		System.out.print("Enter first number: ");
		int a = sc.nextInt();
		System.out.print("Enter second number: ");
		int b = sc.nextInt();

		if (a == 0 || b == 0) {
			System.out.println("LCM of " + a + " and " + b + " = 0");
			sc.close();
			return;
		}

		int x = Math.abs(a);
		int y = Math.abs(b);
		int first = x;
		int second = y;

		while (y != 0) {
			int temp = y;
			y = x % y;
			x = temp;
		}

		long lcm = (long) first * second / x;
		System.out.println("LCM of " + a + " and " + b + " = " + lcm);

		sc.close();
	}
}
