package Lab_1;

import java.util.Scanner;

public class Q10 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		System.out.print("Enter a string or number: ");
		String input = sc.nextLine();

		String normalized = input.replaceAll("\\s+", "").toLowerCase();
		String reversed = new StringBuilder(normalized).reverse().toString();

		if (normalized.equals(reversed)) {
			System.out.println(input + " is a palindrome.");
		} else {
			System.out.println(input + " is not a palindrome.");
		}

		sc.close();
	}
}
