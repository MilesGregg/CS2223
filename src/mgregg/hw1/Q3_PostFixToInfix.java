package mgregg.hw1;

import algs.days.day04.FixedCapacityStack;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * Complete this implementation that takes a postfix expression and converts it into
 * an Infix Expression using a fixed Capacity stack. Also perform the necessary
 * computation to produce its value
 *
 * Using the postfix expression as input
 *
 *     3 6 + 5 * 8 2 - /
 *
 * should produce the following as output:
 *
 *     (((3 + 6) * 5) / (8 - 2)) = 7.5
 *
 * Note that postfix expressions do not need parentheses, which is one of their
 * major selling points.
 */
public class Q3_PostFixToInfix {

	private static double evaluate(String op, double leftEval, double rightEval) {
		double output = 0;
		switch (op) {
			case "+":
				output = leftEval + rightEval;
				break;
			case "-":
				output = leftEval - rightEval;
				break;
			case "*":
				output = leftEval * rightEval;
				break;
			case "/":
				output = leftEval / rightEval;
				break;
		}
		return output;
	}

	public static void main(String[] args) {

		FixedCapacityStack<String> exprs = new FixedCapacityStack<String>(100);
		FixedCapacityStack<Double> vals = new FixedCapacityStack<Double>(100);

		// COMPLETE IN HERE...
		while (!StdIn.isEmpty()) {
			String input = StdIn.readString();

			if (input.equals("+") || input.equals("-") || input.equals("*") || input.equals("/")) {
				String rightExpression = exprs.pop();
				String leftExpression = exprs.pop();
				exprs.push("(" + leftExpression + " " + input + " " + rightExpression + ")");
				double rightValue = vals.pop();
				double leftValue = vals.pop();
				vals.push(evaluate(input, leftValue, rightValue));
			} else {
				exprs.push(input);
				vals.push(Double.parseDouble(input));
			}
		}

		StdOut.print(exprs.pop() + " = " + vals.pop());
	}
}
