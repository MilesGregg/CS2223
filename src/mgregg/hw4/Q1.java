package mgregg.hw4;

import algs.days.day04.FixedCapacityStack;
import edu.princeton.cs.algs4.StdIn;

public class Q1 {
	private static Expression expression(String op, Expression leftEval, Expression rightEval) {
		switch (op) {
			case "+":
				return new Add(leftEval, rightEval);
			case "-":
				return new Subtract(leftEval, rightEval);
			case "*":
				return new Multiply(leftEval, rightEval);
			case "/":
				return new Divide(leftEval, rightEval);
			default:
				return new Value(Integer.parseInt(op));
		}
	}

	/**
	 * Complete this implementation that takes a postfix expression string and converts it into
	 * an Expression node using a fixed Capacity stack. When done, an Expression node will
	 * be returned.
	 *
	 * Using the postfix expression as input
	 *
	 *     3 1 + 4 / 1 5 + 9 * 2 6 * - *
	 *
	 * should produce the expression from the homework, namely
	 *
	 *     (((3+1)/4 * (((1+5)*9)-(2*6)))
	 *
	 * Note that postfix expressions do not need parentheses, which is one of their
	 * major selling points.
	 */
	public static void main(String[] args) {

		// since everything IS an expression (even Values) you only need a single stack.
		FixedCapacityStack<Expression> exprs = new FixedCapacityStack<Expression>(100);

		// fill in here...
		while (!StdIn.isEmpty()) {
			String input = StdIn.readString();

			if (input.equals("+") || input.equals("-") || input.equals("*") || input.equals("/")) {
				Expression rightExpression = exprs.pop();
				Expression leftExpression = exprs.pop();
				exprs.push(expression(input, leftExpression, rightExpression));
			} else {
				exprs.push(new Value(Integer.parseInt(input)));
			}
		}
		Expression exp = exprs.pop();
		System.out.println(exp.format() + " = " + exp.eval());
	}
}
