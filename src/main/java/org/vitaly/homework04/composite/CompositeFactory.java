package org.vitaly.homework04.composite;

import java.util.*;

/**
 * Created by vitaly on 2017-03-04.
 */
public class CompositeFactory {
    private static CompositeFactory instance = new CompositeFactory();

    private final String operatorsRegex = "[\\*\\/\\+\\-]";
    private final String positiveIntegerOrZeroRegex = "\\d+";

    private CompositeFactory() {
    }

    public static CompositeFactory getInstance() {
        return instance;
    }

    public Component createComposite(String expression) {
        Queue<String> reversePolishNotationTokens = toReversePolishNotation(expression);
        Deque<Component> componentStack = new LinkedList<>();

        while (!reversePolishNotationTokens.isEmpty()) {
            String token = reversePolishNotationTokens.poll();
            if (token.matches(operatorsRegex)) {
                Component left = componentStack.pop();
                Component right = componentStack.pop();
                Component operation = getOperatorFrom(token, left, right);
                componentStack.push(operation);
            } else {
                componentStack.push(new Number(Integer.parseInt(token)));
            }
        }
        return componentStack.poll();
    }

    public Component getOperatorFrom(String token, Component left, Component right) {
        if ("+".equals(token)) {
            return new Sum(left, right);
        }
        if ("-".equals(token)) {
            return new Subtraction(left, right);
        }
        if ("*".equals(token)) {
            return new Multiplication(left, right);
        }
        if ("/".equals(token)) {
            return new Division(left, right);
        }
        throw new IllegalArgumentException("Supplied token is not operator!");
    }

    public Queue<String> toReversePolishNotation(String expression) {
        String whitespacesRegex = "\\s+";
        List<String> tokenArray = Arrays.asList(expression.split(whitespacesRegex));
        Queue<String> tokenQueue = new LinkedList<>(tokenArray);
        Deque<String> operatorStack = new LinkedList<>();
        Queue<String> result = new LinkedList<>();

        for (String token : tokenQueue) {
            if (token.matches(positiveIntegerOrZeroRegex)) {
                result.offer(token);
            }
            if (token.matches(operatorsRegex)) {
                while (!operatorStack.isEmpty() && priority(token) < priority(operatorStack.peek())) {
                    result.offer(operatorStack.pop());
                }
                operatorStack.push(token);
            }
            if ("(".equals(token)) {
                operatorStack.push(token);
            }
            if (")".equals(token)) {
                while (!"(".equals(operatorStack.peek())) {
                    result.offer(operatorStack.pop());
                    if (operatorStack.isEmpty()) {
                        throw new IllegalArgumentException("Parenthesis mismatch!");
                    }
                }
                operatorStack.pop();
            }
        }
        while (!operatorStack.isEmpty()) {
            String nextOperator = operatorStack.peek();
            if (!("(".equals(nextOperator) || ")".equals(nextOperator))) {
                result.offer(operatorStack.pop());
            } else {
                throw new IllegalArgumentException("Parenthesis mismatch!");
            }
        }

        return result;
    }

    public int priority(String token) {
        if ("*".equals(token) || "/".equals(token)) {
            return 3;
        }
        if ("+".equals(token) || "-".equals(token)) {
            return 2;
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(CompositeFactory.getInstance().toReversePolishNotation("( 1 + 2 ) * 4 + 5 * ( 3 + 6 )"));
        Component component = CompositeFactory.getInstance().createComposite("( 1 + 2 ) * 4 + 5 * ( 3 + 6 )");
//        System.out.println(CompositeFactory.getInstance().toReversePolishNotation("1 + 2 + 3"));
//        Component component = CompositeFactory.getInstance().createComposite("1 + 2 + 3");
        System.out.println(component.compute());
//        System.out.println(CompositeFactory.getInstance().toReversePolishNotation("1 + 2 )"));
//        System.out.println(CompositeFactory.getInstance().toReversePolishNotation("( 1 + 2"));
    }
}
