package org.vitaly.homework04.composite;

import java.util.*;

import static org.vitaly.util.InputChecker.requireNonEmptyString;

/**
 * Created by vitaly on 2017-03-04.
 */
public class ComponentFactory {
    private static final String TOKEN = "Token";
    private static ComponentFactory instance = new ComponentFactory();

    public static final String PLUS = "+";
    public static final String MINUS = "-";
    public static final String MULTIPLY = "*";
    public static final String DIVIDE = "/";
    public static final String LEFT_PARENTHESIS = "(";
    public static final String RIGHT_PARENTHESIS = ")";

    private final String OPERATORS_REGEXP = "[\\*\\/\\+\\-]";
    private final String SUPPLIED_TOKEN_IS_NOT_SUPPORTED = "Supplied token is not supported!";
    public static final String PARENTHESIS_MISMATCH = "Parenthesis mismatch!";

    private ComponentFactory() {
    }

    public static ComponentFactory getInstance() {
        return instance;
    }

    public Component createComponentForExpression(String expression) {
        Queue<String> reversePolishNotationTokens = toReversePolishNotation(expression);
        Deque<Component> componentStack = new LinkedList<>();

        while (!reversePolishNotationTokens.isEmpty()) {
            String token = reversePolishNotationTokens.poll();
            if (token.matches(OPERATORS_REGEXP)) {
                Component right = componentStack.pop();
                Component left = componentStack.pop();
                Component operation = createComponentFromToken(token, left, right);
                componentStack.push(operation);
            } else {
                componentStack.push(new Number(Integer.parseInt(token)));
            }
        }
        return componentStack.poll();
    }

    public Component createComponentFromToken(String token, Component left, Component right) {
        requireNonEmptyString(token, TOKEN);

        switch (token) {
            case PLUS:
                return new Sum(left, right);
            case MINUS:
                return new Subtraction(left, right);
            case MULTIPLY:
                return new Multiplication(left, right);
            case DIVIDE:
                return new Division(left, right);
            default:
                throw new IllegalArgumentException(SUPPLIED_TOKEN_IS_NOT_SUPPORTED);
        }
    }

    public Queue<String> toReversePolishNotation(String expression) {
        requireNonEmptyString(expression, "Expression");

        String whitespacesRegex = "\\s+";
        String positiveIntegerOrZeroRegex = "\\d+";
        List<String> tokenArray = Arrays.asList(expression.split(whitespacesRegex));
        Queue<String> tokenQueue = new LinkedList<>(tokenArray);
        Deque<String> operatorStack = new LinkedList<>();
        Queue<String> result = new LinkedList<>();

        for (String token : tokenQueue) {
            if (token.matches(positiveIntegerOrZeroRegex)) {
                result.offer(token);
            }
            if (token.matches(OPERATORS_REGEXP)) {
                moveOperatorsWithHigherPriorityToResult(operatorStack, result, token);
                operatorStack.push(token);
            }
            if (LEFT_PARENTHESIS.equals(token)) {
                operatorStack.push(token);
            }
            if (RIGHT_PARENTHESIS.equals(token)) {
                moveOperatorsToResultUntilLeftParenthesis(operatorStack, result);
                operatorStack.pop();
            }
        }

        moveRestOfOperatorsToResult(operatorStack, result);

        return result;
    }

    public void moveOperatorsWithHigherPriorityToResult(Deque<String> operatorStack,
                                                        Queue<String> result, String token) {
        while (!operatorStack.isEmpty() && (getPriority(token) < getPriority(operatorStack.peek()))) {
            result.offer(operatorStack.pop());
        }
    }

    public void moveOperatorsToResultUntilLeftParenthesis(Deque<String> operatorStack, Queue<String> result) {
        while (!LEFT_PARENTHESIS.equals(operatorStack.peek())) {
            result.offer(operatorStack.pop());
            if (operatorStack.isEmpty()) {
                throw new IllegalArgumentException(PARENTHESIS_MISMATCH);
            }
        }
    }

    public void moveRestOfOperatorsToResult(Deque<String> operatorStack, Queue<String> result) {
        while (!operatorStack.isEmpty()) {
            String nextOperator = operatorStack.peek();

            if (!(LEFT_PARENTHESIS.equals(nextOperator) || RIGHT_PARENTHESIS.equals(nextOperator))) {
                result.offer(operatorStack.pop());
            } else {
                throw new IllegalArgumentException(PARENTHESIS_MISMATCH);
            }
        }
    }

    public int getPriority(String token) {
        requireNonEmptyString(token, TOKEN);

        switch (token) {
            case MULTIPLY:
            case DIVIDE:
                return 3;
            case PLUS:
            case MINUS:
                return 2;
            case LEFT_PARENTHESIS:
            case RIGHT_PARENTHESIS:
                return 1;
            default:
                throw new IllegalArgumentException(SUPPLIED_TOKEN_IS_NOT_SUPPORTED);
        }
    }
}
