/*
* Ngoc Pham, Hoat Vu, Sangwook Park
* CSC 135
* Assignment 1 - Recursive Descent Recognizer
* Compile: javac Recognizer.java
* Run: java Recognizer
*/
import java.io.*;
import java.util.Scanner;

public class Recognizer {
	static String streamInput;
	static int index = 0;
	static int errorFlag = 0;
	
	private char token() {
		return (streamInput.charAt(index));
	}
	
	private void match(char terminal) {
		if (terminal == token()) {
			next();
		} else {
			error();
		}
	}
	
	private void next() {
		if (index < (streamInput.length() - 1)) {
			index++;
		}
	}
	
	private void error() {
		System.out.println("Error is at position: " + index +
				"\tToken: " + token() + " caused the error");
		errorFlag = 1;
		next();
	}
//=========================================================================================================================	
	
	/*
	* <javaclass> ::= <classname> [X <classname>] B <varlist>; {<method>} E
	*/
	private void javaclass() {
		classname();
		
		if (token() == 'X') {
			match('X');
			classname();
		}
		
		match('B');
		varlist();
		match(';');
		
		while (token() == 'P' || token() == 'V') {
			method();
		}
		
		match('E');
	}


	/*
	 * <classname> ::= C|D
	 * */
	private void classname() {
		if (token() == 'C' || token() == 'D') {
			match(token());
		} 
	}


	/*
	* <varlist> ::= <vardef> {, <vardef>}
	*/
	private void varlist() {
		vardef();
		
		while(token() == ',') {
			match(',');
			vardef();
		}
	}


	/*
	* <vardef> ::= <type> <varname> | <classname> <varref>
	*/
	private void vardef() {
		if (token() == 'I' || token() == 'S') {
			type();
			varname();
		} else if (token() == 'C' || token() == 'D') {
			classname();
			varref();
		}
	}


	/*
	 * <type> ::= I|S
	 * */
	private void type() {
		if (token() == 'I' || token() == 'S') {
			match(token());
		}
	}


	/*
	* <varname> ::= <letter> {<char>}
	*/
	private void varname() {
		letter();
		
		while(token() == '0' || token() == '1' || token() == '2' || token() == '3' || token() == 'Y' || token() == 'Z') {
			character();
		}
	}


	/*
	 * <letter> ::= Y|Z
	 * */
	private void letter() {
		if (token() == 'Y' || token() == 'Z') {
			match(token());
		}
	}


	/*
	 * <char> ::= <letter> | <digit>
	 * */
	private void character() {
		if (token() == '0' || token() == '1' || token() == '2' || token() == '3') {
			digit();
		} else if (token() == 'Y' || token() == 'Z') {
			letter();
		}
	}


	/*
	 * <digit> ::= 0|1|2|3
	 * */
	private void digit() {
		if (token() == '0' || token() == '1' || token() == '2' || token() == '3') {
			match(token());
		}
	}
	
	
	/*
	 * <integer> ::= <digit> {<digit>}
	 * */
	private void integer() {
		while(token() == '0' || token() == '1' || token() == '2' || token() == '3') {
			digit();
		}
	}
	
	
	/*
	 * <varref> ::= J|K
	 * */
	private void varref() {
		if (token() == 'J' || token() == 'K') {
			match(token());
		}
	}
	

	/*
	 * method ::= <accessor> <type> <methodname> ([<varlist>]) B {<statemt>} <returnstatemt> E
	 * */
	private void method() {
		accessor();
		type();
		methodname();
		match('(');
		
		if (token() == 'I' || token() == 'S' || token() == 'C' || token() == 'D') {
			varlist();
		}
		
		match(')');
		match('B');
		
		while(token() == 'F' || token() == 'Y' || token() == 'Z' || token() == 'J' || token() == 'K' || token() == 'W') {
			statemt();
		}
		
		returnstatement();
		match('E');
	}

	
	/*
	 * <accessor> ::= P|V
	 * */
	private void accessor() {
		if (token() == 'P' || token() == 'V') {
			match(token());
		}
	}
	
	
	/*
	 * <methodname> ::= M|N
	 * */
	private void methodname() {
		if (token() == 'M' || token() == 'N') {
			match(token());
		}
	}
	
	
	/*
	 * <statemt> ::= <ifstatemt> | <assignstatemt>;|<whilestatemt>							
	 * */
	private void statemt() {
		//ifstatemt
		if (token() == 'F') {
			ifstatemt();

		//assignstatemt	
		} else if (token() == 'Y' || token() == 'Z' || token() == 'J' || token() == 'K') {
			assignstatemt();
			match(';');
		
		//whilestatemt	
		} else if (token() == 'W') {
			whilestatemt();		
		}
	}


	/*
	* <ifstatemt> ::= F <cond> T B {<statemt>} E [L B {<statemt>} E]
	*/
	private void ifstatemt() {
		match('F');
		cond();
		match('T');
		match('B');
		
		while (token() == 'F' || token() == 'Y' || token() == 'Z' || token() == 'J' || token() == 'K' || token() == 'W') {
			statemt();
		}
		
		match('E');
		
		if (token() == 'L') {
			match('L');
			match('B');
			while (token() == 'F' || token() == 'Y' || token() == 'Z' || token() == 'J' || token() == 'K' || token() == 'W') {
				statemt();
			}
			match('E');
		} 
	}
	

	/*
	* <assignstatemt> ::= <varname> = <mathexpr> | <varref> = <getvarref>
	*/
	private void assignstatemt() {
		if (token() == 'Y' || token() == 'Z') {
			varname();
			match('=');
			mathexpr();
		} else if (token() == 'J' || token() == 'K') {
			varref();
			match('=');
			getvarref();
		}
	}
	
	
	/*
	* <mathexpr> ::= <factor> {+ factor}
	*/
	private void mathexpr() {
		factor();
		
		while(token() == '+') {
			match('+');
			factor();
		}
	}


	/*
	* <factor> ::= <oprnd> {* oprnd}
	*/
	private void factor() {
		oprnd();
		while (token() == '*') {
			match('*');
			oprnd();	
		}
	}


	/*
	 * <oprnd> ::= <integer> | <varname> | (<mathexpr>) | <methodcall>
	 * */
	private void oprnd() {
		if (token() == '0' || token() == '1' || token() == '2' || token() == '3') {
			integer();
		} else if (token() == 'Y' || token() == 'Z') {
			varname();
		} else if (token() == '('){ 
			match('(');
			mathexpr();
			match(')');
		} else if (token() == 'J' || token() == 'K') {
			methodcall();
		} 
	}


	/*
	* <getvarref> ::= O <classname>() | <methodcall>
	*/
	private void getvarref() {
		if (token() == 'J' || token() == 'K') {
			methodcall();
		} else if (token() == 'O'){
			match('O');
			classname();
			match('(');
			match(')');
		}
	}
	

	/*
	* <whilestatemt> ::= W <cond> T B {<statemt>} E
	*/
	private void whilestatemt() {
		match('W');
		cond();
		match('T');
		match('B');
		
		while (token() == 'F' || token() == 'Y' || token() == 'Z' || token() == 'J' || token() == 'K' || token() == 'W') {
			statemt();
		}
		
		match('E');
	}
	

	/*
	* <cond> ::= (<oprnd> <operator> <oprnd>)
	*/
	private void cond() {
		if (token() == '(') {
			match('(');
			oprnd();
			operator();
			oprnd();
			match(')');
		}
	}


	/*
	 * <operator> ::= < | = | > | !
	 * */
	private void operator() {
		if (token() == '<' || token() == '>' || token() == '=' || token() == '!') {
			match(token());
		}
	}
	

	/*
	 * <returnstatement> ::= R <varname>;
	 * */
	private void returnstatement() {
		match('R');
		varname();
		match(';');
	}


	/*
	* <methodcall> ::= <varref>.<methodname>( [ <varlist> ] )
	*/
	private void methodcall() {
		varref();
		match('.');
		methodname();
		
		match('(');
		if (token() == 'I' || token() == 'S' || token() == 'C' || token() == 'D'){
			varlist();
		}
		match(')');	
	}
	
	
//===============================================================================================================================
	
	private void start() {
		javaclass();
		match('$');
		
		if (errorFlag == 0) {
			System.out.println("Legal.\n");
		} else {
			System.out.println("Errors found.\n");
		}
	}
	
	public static void main(String[] args) {
		Recognizer record = new Recognizer();
		
		Scanner input = new Scanner(System.in);
		System.out.print("\nEnter an expression: ");
		streamInput = input.nextLine();
		input.close();
		
		record.start();
	}

}

