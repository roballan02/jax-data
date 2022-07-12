package com.avlty.process;

import java.util.ArrayDeque;
import java.util.Deque;

public class ValidateParenthesis {

	public static void main(String[] args) {

		String exprValid = "([{}])";

		// Function call
		if (isValid(exprValid))
			System.out.println("exprValid " + "Well Formatted ");
		else
			System.out.println("exprValid " + "Not Formatted ");
		
		String exprNotValid = "([{}]))";

		// Function call
		if (isValid(exprNotValid))
			System.out.println("exprNotValid " + "Well Formatted ");
		else
			System.out.println("exprNotValid " + "Not Formatted ");
	}

	public static boolean isValid(String str) {

		Deque<Character> characterStack = new ArrayDeque<Character>();

		for (int i = 0; i < str.length(); i++) {
			char chr = str.charAt(i);

			if (chr == '(' || chr == '[' || chr == '{') {
				// Push the element in the stack
				characterStack.push(chr);
				continue;
			}

			if (characterStack.isEmpty()) {
				return false;
			}

			char check;
			switch (chr) {
			case ')':
				check = characterStack.pop();
				if (check == '{' || check == '[')
					return false;
				break;

			case '}':
				check = characterStack.pop();
				if (check == '(' || check == '[')
					return false;
				break;

			case ']':
				check = characterStack.pop();
				if (check == '(' || check == '{')
					return false;
				break;
			}
		}
		return (characterStack.isEmpty());
	}

}
