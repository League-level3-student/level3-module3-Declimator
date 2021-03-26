package _00_Intro_To_String_Methods;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Base64;

/*
 * Visit the JavaDocs for the String class to view everything you can do with a String.
 * https://docs.oracle.com/javase/7/docs/api/java/lang/String.html
 * https://docs.oracle.com/javase/7/docs/api/java/lang/Character.html
 *
 * HINT:  Here are some String methods you might find useful 
 * contains
 * replace
 * trim
 * length
 * getBytes
 * endsWith
 * split 
 * indexOf
 * lastIndexOf
 * compareTo(IgnoreCase)
 * substring
 * toUpperCase/toLowerCase
 * valueOf
 *
 * Here are some Character methods you might find useful:
 * Character.toLowerCase(char c);
 * Character.toUpperCase(char c);
 * Character.isLetter(char c);
 * Character.isDigit(char c);
 * Character.getNumericValue(char c);
 */

public class _01_StringMethods {

	// Given Strings s1 and s2, return the longer String
	public static String longerString(String s1, String s2) {
		if (s1.length() > s2.length()) {
			return s1;
		}
		return s2;
	}

	// If String s contains the word "underscores", change all of the spaces
	// to underscores
	public static String formatSpaces(String s) {
		if (s.contains("underscores")) {
			s = s.replace(" ", "_");
		}
		return s;
	}

	// Return the name of the person whose LAST name would appear first if they
	// were in alphabetical order.
	// You cannot assume there are no extra spaces around the name, but you can
	// assume there is only one space between the first and last name
	public static String lineLeader(String s1, String s2, String s3) {
		System.out.println("start");
		int i = 0;
		while(s1.charAt(i) == ' ') {
			s1 = s1.substring(i+1);
		}
		i = 0;
		while(s2.charAt(i) == ' ') {
			s2 = s2.substring(i+1);
		}
		i = 0;
		while(s3.charAt(i) == ' ') {
			s3 = s3.substring(i+1);
		}
		System.out.println(s1);
		System.out.println(s2);
		System.out.println(s3);
		String s11 = s1.split(" ")[0];
		String s21 = s2.split(" ")[0];
		String s31 = s3.split(" ")[0];
		String s12 = s1.split(" ")[1];
		String s22 = s2.split(" ")[1];
		String s32 = s3.split(" ")[1];
		System.out.println(s12);
		System.out.println(s22);
		System.out.println(s32);
		if (s12.compareTo(s22) < 0 && s12.compareTo(s32) < 0) {
			System.out.println(s1);
			return s11 + " " + s12;
		}
		if (s22.compareTo(s12) < 0 && s22.compareTo(s32) < 0) {
			System.out.println(s2);
			return s21 + " " + s22;
		}
		System.out.println(s3);
		return s31 + " " + s32;
	}

	// Return the sum of all numerical digits in the String
	public static int numeralSum(String s) {
		int n = 0;
		for (int i = 0; i < s.length(); i++) {
			if ("1234567890".contains(s.substring(i, i + 1))) {
				n += Integer.parseInt(s.substring(i, i + 1));
			}
		}
		return n;
	}

	// Return the number of times String substring appears in String s
	public static int substringCount(String s, String substring) {
		int n = 0;
		int index = s.indexOf(substring);
		while(index != -1) {
			n++;
			index = s.indexOf(substring, index + substring.length());
		}
		return n;
	}

	// Call Utilities.encrypt at the bottom of this file to encrypt String s
	public static String encrypt(String s, char key) {
		return Utilities.encrypt(s.getBytes(), (byte) key);
	}

	// Call Utilities.decrypt at the bottom of this file to decrypt the
	// cyphertext (encrypted text)
	public static String decrypt(String s, char key) {
		return Utilities.decrypt(s, (byte) key);
	}

	// Return the number of words in String s that end with String substring
	// You can assume there are no punctuation marks between words
	public static int wordsEndsWithSubstring(String s, String substring) {
		String[] list = s.split(" ");
		int n = 0;
		for (int i = 0; i < list.length; i++) {
			if (list[i].length() < substring.length()) {

			} else if (list[i].substring(list[i].length() - substring.length()).equals(substring)) {
				n++;
			}
		}
		return n;
	}

	// Given String s, return the number of characters between the first
	// occurrence of String substring and the final occurrence
	// You can assume that substring will appear at least twice
	public static int distance(String s, String substring) {
		int i1 = s.indexOf(substring);
		int i2 = s.lastIndexOf(substring);
		return i2 - substring.length() - i1;
	}

	// Return true if String s is a palindrome
	// palindromes are words or phrases are read the same forward as backward.
	// HINT: ignore/remove all punctuation and spaces in the String
	public static boolean palindrome(String s) {
		for(int i = 0; i < s.length(); i++) {
			if("!@#$%^&*()-_+=[]{}|/,.<>;:'?`~".contains(s.charAt(i) + "")) {
				s = s.substring(0, i) + s.substring(i+1);
			}
		}
		s = s.replace(" ", "");
		String s1 = s.substring(0, (s.length() + 1) / 2);
		String s2 = s.substring(s.length() / 2);
		s1 = s1.toLowerCase();
		s2 = s2.toLowerCase();
		char[] s3 = s1.toCharArray();
		char[] s4 = s2.toCharArray();
		int c = 1;
		for (int i = 0; i < s3.length; i++) {
			if (s4[s4.length - c] == ' ') {
				c++;
				i--;
			} else if (s3[i] != s4[s4.length - i - c]) {
				return false;
			}
		}
		return true;
	}
}

class Utilities {
	// This basic encryption scheme is called single-byte xor. It takes a
	// single byte and uses exclusive-or on every character in the String.
	public static String encrypt(byte[] plaintext, byte key) {
		for (int i = 0; i < plaintext.length; i++) {
			plaintext[i] = (byte) (plaintext[i] ^ key);
		}
		return Base64.getEncoder().encodeToString(plaintext);
	}

	public static String decrypt(String cyphertext, byte key) {
		byte[] b = Base64.getDecoder().decode(cyphertext);
		for (int i = 0; i < b.length; i++) {
			b[i] = (byte) (b[i] ^ key);
		}
		return new String(b);
	}
}
