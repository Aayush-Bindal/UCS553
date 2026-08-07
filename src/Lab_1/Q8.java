package Lab_1;

import java.util.Scanner;

public class Q8 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		System.out.print("Enter an integer: ");
		int number = sc.nextInt();

		int n = Math.abs(number);
		int reversed = 0;

		while (n > 0) {
			int digit = n % 10;
			reversed = reversed * 10 + digit;
			n /= 10;
		}

		if (number < 0) {
			reversed = -reversed;
		}

		System.out.println("Reversed number = " + reversed);
		sc.close();
	}
}
