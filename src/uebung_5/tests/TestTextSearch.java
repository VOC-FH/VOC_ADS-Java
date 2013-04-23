package uebung_5.tests;

import uebung_5.ue.TextSearch;

public class TestTextSearch {
	public static void main(String[] args) {
		char[] text = "Hallo, das ist ein Text".toCharArray();
		char[] pattern = "ext".toCharArray();
		
		System.out.println(TextSearch.searchRecursive(text, pattern, 0, 0));
		System.out.println(TextSearch.searchIterative(text,pattern));
	}
}
