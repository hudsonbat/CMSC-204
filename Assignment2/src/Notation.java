/**
 * This class represents a notation utility capable of converting and evaluating expressions
 * containing arithmetic operators and operands. It contains static methods for converting infix expressions 
 * to postfix expressions and postfix expressions to infix expressions as well as a method for evaluating 
 * postfix expressions.
 * @author hudson
 */

public class Notation {
	
	/**
	 * No-arg constructor
	 */
	
	public Notation() {}
	
	/**
	 * This method takes an infix expression in the form of a string and converts it to a
	 * postfix expression.
	 * @param infix The expression to be converted to a postfix expression.
	 * @return The postfix expression.
	 * @throws InvalidNotationFormatException If the infix expression is imbalanced.
	 */
	
	
	public static String convertInfixToPostfix(String infix) throws InvalidNotationFormatException {
		
		QueueInterface<Character> myQueue = new NotationQueue<>();
		StackInterface<Character> myStack = new NotationStack<>();
		
		int index = 0;
		
		while(index < infix.length()) {
			switch(infix.charAt(index)) {
			
			case ' ':
				break;
			case '^':
				myStack.push(infix.charAt(index));
				break;
			case '(':
				myStack.push(infix.charAt(index));
				break;
			case '0': case '1': case '2': case '3': case '4': case '5': case '6': case '7': case '8': case '9':
				myQueue.enqueue(infix.charAt(index));
				break;
			case '+': case '-': case '/': case '*':
				Character c = infix.charAt(index);
				while(!myStack.isEmpty() && c <= myStack.top()) {
					char topOperator = myStack.pop();
					myQueue.enqueue(topOperator);
				}
				myStack.push(infix.charAt(index));
				break;
			case ')': 
				char topOperator = myStack.pop();
				while(topOperator != '(') {
					if(myStack.isEmpty()) {
						throw new InvalidNotationFormatException("No left parentheses character in stack");
					} else {
						myQueue.enqueue(topOperator);
						topOperator = myStack.pop();
					}
				}
				break;
			default:
				break;
			}
			
			index++;
		}
		
		while(!myStack.isEmpty()) {
			Character topOperator = myStack.pop();
			myQueue.enqueue(topOperator);
		}
		
		String postFix = myQueue.toString();
	
		return postFix;
	}
	
	/**
	 * This method takes a postfix expression in the form of a string and converts it
	 * to a infix expression.
	 * @param postfix The expression to be converted to an infix expression.
	 * @return The infix expression.
	 * @throws InvalidNotationFormatException If the number of operands in the stack is less than two or 
	 * there are more than two values in the stack at the conclusion of the conversion.
	 */
	
	public static String convertPostfixToInfix(String postfix) throws InvalidNotationFormatException {
		
		StackInterface<String> infixStack = new NotationStack<>();
		
		int index = 0;
		String secondOperand;
		String firstOperand;
		String concat;
		String num;
		String top;
		
		while(index < postfix.length()) {
			switch(postfix.charAt(index)) {
			
			case ' ':
				break;
				
			case '0': case '1': case '2': case '3': case '4': case '5': case '6': case '7': case '8': case '9':
				num = Character.toString(postfix.charAt(index));
				infixStack.push(num);
				break;
			case '+': case '-': case '/': case '*': case '^':
				if(infixStack.size() < 2) {
					throw new InvalidNotationFormatException("Must have at least two operands in infix stack");
				} else {
					secondOperand = infixStack.pop();
					firstOperand = infixStack.pop();
					concat = "(" + firstOperand + postfix.charAt(index) + secondOperand + ")";
					infixStack.push(concat);
				}
				break;
			default:
				break;
			}
			
			index++;
		}
		
		if(infixStack.size() != 1) {
			throw new InvalidNotationFormatException("There must remain only one value in the stack");
		} else {
			top = infixStack.top();
		}
		
		return top;
	
	}
	
	/**
	 * This method evaluates a postfix expression by performing the operations 
	 * indicated by the postfix expression using a stack of type Double.
	 * @param postFix The postfix expression to be evaluated.
	 * @return The result of evaluating the operations of the postfix expression.
	 * @throws InvalidNotationFormatException If the stack contains fewer than two operands when a given
	 * operator is identified.
	 * @throws StackUnderflowException If values are popped from an empty stack.
	 */
	
	public static double evaluatePostfixExpression(String postFix) throws InvalidNotationFormatException,StackUnderflowException {
		
		StackInterface<Double> valueStack = new NotationStack<>();
		
		double top;
		int index = 0;
		double secondOperand;
		double firstOperand;
		char operator;
		int num;
		double result;
		
		while(index < postFix.length()) {
			switch(postFix.charAt(index)) {
			
			case ' ':
				break;
			case '0': case '1': case '2': case '3': case '4': case '5': case '6': case '7': case '8': case '9':
				num = Character.digit(postFix.charAt(index), 10);
				result = num;
				valueStack.push(result);
				break;
			case '+':
				if(valueStack.size() < 2) {
					throw new InvalidNotationFormatException("Must have at least two operands in value stack");
				} else {
					secondOperand = valueStack.pop();
					firstOperand = valueStack.pop();
					result = (firstOperand + secondOperand);
					valueStack.push(result);
					break;
				}
			case '-': 
				if(valueStack.size() < 2) {
					throw new InvalidNotationFormatException("Must have at least two operands in value stack");
				}else {
					secondOperand = valueStack.pop();
					firstOperand = valueStack.pop();
					result = (firstOperand - secondOperand);
					valueStack.push(result);
					break;
				}
			case '/': 
				if(valueStack.size() < 2) {
					throw new InvalidNotationFormatException("Must have at least two operands in value stack");
				} else {
					secondOperand = valueStack.pop();
					firstOperand = valueStack.pop();
					result = firstOperand / secondOperand;
					valueStack.push(result);
					break;
				}
			case '*': 
				if(valueStack.size() < 2) {
					throw new InvalidNotationFormatException("Must have at least two operands in value stack");
				} else {
					secondOperand = valueStack.pop();
					firstOperand = valueStack.pop();
					result = (firstOperand * secondOperand);
					valueStack.push(result);
					break;
				}
			case '^':
				if(valueStack.size() < 2) {
					throw new InvalidNotationFormatException("Must have at least two operands in value stack");
				} else {
					secondOperand = valueStack.pop();
					firstOperand = valueStack.pop();
					result = (Math.pow(firstOperand,secondOperand));
					valueStack.push(result);
					break;
				}
			default:
				break;
			}
			index++;
		}
		
		if(valueStack.size() != 1) {
			throw new InvalidNotationFormatException("There must remain only one value in the stack");
		} else {
			top = valueStack.top();
		}
		
		return top;
	}

}
