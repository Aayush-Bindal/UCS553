package Lab_1;

import java.util.Scanner;

public class Q4 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		System.out.print("Enter number of terms: ");
		int terms = sc.nextInt();

		if (terms <= 0) {
			System.out.println("Please enter a positive number of terms.");
			sc.close();
			return;
		}

		int first = 0;
		int second = 1;

		System.out.print("Fibonacci Series: ");
		for (int i = 1; i <= terms; i++) {
			System.out.print(first);
			if (i < terms) {
				System.out.print(" ");
			}

			int next = first + second;
			first = second;
			second = next;
		}
		System.out.println();

		sc.close();
	}
}
