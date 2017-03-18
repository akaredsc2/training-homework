package org.vitaly.homework04.composite;

import org.junit.Before;
import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.hasItems;
import static org.junit.Assert.assertEquals;

/**
 * Created by vitaly on 2017-03-18.
 */
public class ComponentFactoryTest {
    private ComponentFactory factory;
    private Number left;
    private Number right;
    private String expressionWithParenthesis;
    private String expressionWithoutParenthesis;
    private String expressionWithSubtraction;
    private String expressionWithDivision;
    private long expectedValueWithParenthesis;
    private long expectedValueWithoutParenthesis;
    private long expectedValueWithSubtraction;
    private long expectedValueWithDivision;
    private String[] expectedReversePolishNotationWithParenthesis;
    private String[] expectedReversePolishNotationWithoutParenthesis;
    private String extraLeftParenthesisExpression;
    private String extraRightParenthesisExpression;
    private String priorityOfTwoToken;
    private String priorityOfThreeToken;
    private Deque<String> operatorStack;
    private Queue<String> result;

    @Before
    public void setUp() throws Exception {
        factory = ComponentFactory.getInstance();
        left = new Number(18);
        right = new Number(3);

        expressionWithParenthesis = "( 1 + 2 ) * 4 + 5 * ( 3 + 6 )";
        expressionWithoutParenthesis = "10 + 20 + 30 + 40";
        expressionWithSubtraction = "30 - 50";
        expressionWithDivision = "72 / 5";

        expectedValueWithParenthesis = 57L;
        expectedValueWithoutParenthesis = 100L;
        expectedValueWithSubtraction = -20L;
        expectedValueWithDivision = 14L;

        expectedReversePolishNotationWithParenthesis
                = new String[]{"1", "2", "+", "4", "*", "5", "3", "6", "+", "*", "+"};
        expectedReversePolishNotationWithoutParenthesis = new String[]{"10", "20", "30", "40", "+", "+", "+"};

        extraLeftParenthesisExpression = "( 1 + 2";
        extraRightParenthesisExpression = "1 + 2 )";

        priorityOfTwoToken = ComponentFactory.MINUS;
        priorityOfThreeToken = ComponentFactory.DIVIDE;
        operatorStack = new LinkedList<>();
        result = new LinkedList<>();
    }

    @Test
    public void createComponentFromPlusTokenReturnsSumComponent() throws Exception {
        Component component = factory.createComponentFromToken(ComponentFactory.PLUS, left, right);

        assertThat(component, allOf(
                notNullValue(),
                instanceOf(Sum.class)));
    }

    @Test
    public void createComponentFromMinusTokenReturnsSubtractionComponent() throws Exception {
        Component component = factory.createComponentFromToken(ComponentFactory.MINUS, left, right);

        assertThat(component, allOf(
                notNullValue(),
                instanceOf(Subtraction.class)));
    }

    @Test
    public void createComponentFromAstrixTokenReturnsMultiplicationComponent() throws Exception {
        Component component = factory.createComponentFromToken(ComponentFactory.MULTIPLY, left, right);

        assertThat(component, allOf(
                notNullValue(),
                instanceOf(Multiplication.class)));
    }

    @Test
    public void createComponentFromSlashTokenReturnsDivisionComponent() throws Exception {
        Component component = factory.createComponentFromToken(ComponentFactory.DIVIDE, left, right);

        assertThat(component, allOf(
                notNullValue(),
                instanceOf(Division.class)));
    }

    @Test(expected = IllegalArgumentException.class)
    public void createComponentFromUnsupportedTokenShouldThrowException() throws Exception {
        String unsupportedToken = "^";
        factory.createComponentFromToken(unsupportedToken, left, right);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createComponentWithNullTokenShouldThrowException() throws Exception {
        factory.createComponentFromToken(null, left, right);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createComponentWithEmptyTokenShouldThrowException() throws Exception {
        factory.createComponentFromToken("", left, right);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createComponentWithNullLeftOperandThrowException() throws Exception {
        factory.createComponentFromToken(ComponentFactory.PLUS, null, right);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createComponentWithNullRightOperandThrowException() throws Exception {
        factory.createComponentFromToken(ComponentFactory.PLUS, left, null);
    }

    @Test
    public void reversePolishNotationOfExpressionWithParenthesis() throws Exception {
        Queue<String> actualReversePolishNotation = factory.toReversePolishNotation(expressionWithParenthesis);

        assertThat(actualReversePolishNotation, contains(expectedReversePolishNotationWithParenthesis));
    }

    @Test
    public void reversePolishNotationOfExpressionWithoutParenthesis() throws Exception {
        Queue<String> actualReversePolishNotation = factory.toReversePolishNotation(expressionWithoutParenthesis);

        assertThat(actualReversePolishNotation, contains(expectedReversePolishNotationWithoutParenthesis));
    }

    @Test(expected = IllegalArgumentException.class)
    public void reversePolishNotationOfNullExpressionShouldThrowException() throws Exception {
        factory.toReversePolishNotation(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void reversePolishNotationOfEmptyExpressionShouldThrowException() throws Exception {
        factory.toReversePolishNotation("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void reversePolishNotationOfExtraLeftParenthesisExpressionShouldThrowException() throws Exception {
        factory.toReversePolishNotation(extraLeftParenthesisExpression);
    }

    @Test(expected = IllegalArgumentException.class)
    public void reversePolishNotationOfExtraRightParenthesisExpressionShouldThrowException() throws Exception {
        factory.toReversePolishNotation(extraRightParenthesisExpression);
    }

    @Test
    public void createdComponentComputesValueOfExpressionWithParenthesis() throws Exception {
        Component component = factory.createComponentForExpression(expressionWithParenthesis);
        long actualValue = component.compute();

        assertEquals(expectedValueWithParenthesis, actualValue);
    }

    @Test
    public void createdComponentComputesValueOfExpressionWithSubtraction() throws Exception {
        Component component = factory.createComponentForExpression(expressionWithSubtraction);
        long actualValue = component.compute();

        assertEquals(expectedValueWithSubtraction, actualValue);
    }

    @Test
    public void createdComponentComputesValueOfExpressionWithDivision() throws Exception {
        Component component = factory.createComponentForExpression(expressionWithDivision);
        long actualValue = component.compute();

        assertEquals(expectedValueWithDivision, actualValue);
    }

    @Test
    public void createdComponentComputesValueOfExpressionWithoutParenthesis() throws Exception {
        Component component = factory.createComponentForExpression(expressionWithoutParenthesis);
        long actualValue = component.compute();

        assertEquals(expectedValueWithoutParenthesis, actualValue);
    }

    @Test(expected = IllegalArgumentException.class)
    public void creatingComponentForNullExpressionShouldThrowException() throws Exception {
        factory.createComponentForExpression(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void creatingComponentForEmptyExpressionShouldThrowException() throws Exception {
        factory.createComponentForExpression("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void creatingComponentForExtraLeftParenthesisExpressionShouldThrowException() throws Exception {
        factory.createComponentForExpression(extraLeftParenthesisExpression);
    }

    @Test(expected = IllegalArgumentException.class)
    public void creatingComponentForExtraRightParenthesisExpressionShouldThrowException() throws Exception {
        factory.createComponentForExpression(extraRightParenthesisExpression);
    }

    @Test(expected = IllegalArgumentException.class)
    public void gettingPriorityOfNullTokenShouldThrowException() throws Exception {
        factory.getPriority(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void gettingPriorityOfEmptyTokenShouldThrowException() throws Exception {
        factory.getPriority("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void gettingPriorityOfUnsupportedTokenShouldThrowException() throws Exception {
        factory.getPriority("d");
    }

    @Test
    public void moveRestOfOperatorsToResultMakesOperatorStackEmpty() throws Exception {
        operatorStack.push(ComponentFactory.MINUS);
        operatorStack.push(ComponentFactory.PLUS);
        operatorStack.push(ComponentFactory.MINUS);

        factory.moveRestOfOperatorsToResult(operatorStack, result);

        assertThat(operatorStack, empty());
    }

    @Test
    public void moveRestOfOperatorsToResultMakesResultContainMovedOperators() throws Exception {
        operatorStack.push(ComponentFactory.DIVIDE);
        operatorStack.push(ComponentFactory.PLUS);
        operatorStack.push(ComponentFactory.MULTIPLY);

        factory.moveRestOfOperatorsToResult(operatorStack, result);

        assertThat(result, contains(ComponentFactory.MULTIPLY, ComponentFactory.PLUS, ComponentFactory.DIVIDE));
    }

    @Test(expected = IllegalArgumentException.class)
    public void seeingLeftParenthesisWhileMoveRestOfOperatorsToResultShouldThrowException() throws Exception {
        operatorStack.push(ComponentFactory.PLUS);
        operatorStack.push(ComponentFactory.LEFT_PARENTHESIS);
        operatorStack.push(ComponentFactory.MULTIPLY);

        factory.moveRestOfOperatorsToResult(operatorStack, result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void seeingRightParenthesisWhileMoveRestOfOperatorsToResultShouldThrowException() throws Exception {
        operatorStack.push(ComponentFactory.PLUS);
        operatorStack.push(ComponentFactory.RIGHT_PARENTHESIS);
        operatorStack.push(ComponentFactory.MULTIPLY);

        factory.moveRestOfOperatorsToResult(operatorStack, result);
    }

    @Test
    public void movingOperatorsWithHigherPriorityToResultMakesOperatorStackContainSameOrLowerPriorityToken()
            throws Exception {
        operatorStack = new LinkedList<>();
        operatorStack.push(priorityOfTwoToken);
        operatorStack.push(priorityOfThreeToken);
        Queue<String> result = new LinkedList<>();

        factory.moveOperatorsWithHigherPriorityToResult(operatorStack, result, priorityOfTwoToken);

        assertThat(operatorStack, allOf(
                not(hasItem(priorityOfThreeToken)),
                hasItem(priorityOfTwoToken)));
    }

    @Test
    public void movingOperatorsWithHigherPriorityToResultMakesResultContainMovedTokens()
            throws Exception {
        operatorStack.push(priorityOfTwoToken);
        operatorStack.push(priorityOfThreeToken);
        operatorStack.push(priorityOfThreeToken);

        factory.moveOperatorsWithHigherPriorityToResult(operatorStack, result, priorityOfTwoToken);

        assertThat(result, contains(priorityOfThreeToken, priorityOfThreeToken));
    }

    @Test
    public void movingOperatorsWithHigherPriorityToResultDoesNotMoveOperatorsWithSamePriorityFromOperatorStack()
            throws Exception {
        operatorStack.push(priorityOfThreeToken);
        operatorStack.push(priorityOfTwoToken);

        factory.moveOperatorsWithHigherPriorityToResult(operatorStack, result, priorityOfThreeToken);

        assertThat(operatorStack, contains(priorityOfTwoToken, priorityOfThreeToken));
    }

    @Test
    public void movingOperatorsWithHigherPriorityToResultDoesNotMoveOperatorsWithLowerPriorityFromOperatorStack()
            throws Exception {
        operatorStack.push(priorityOfTwoToken);
        operatorStack.push(priorityOfThreeToken);

        factory.moveOperatorsWithHigherPriorityToResult(operatorStack, result, priorityOfThreeToken);

        assertThat(operatorStack, contains(priorityOfThreeToken, priorityOfTwoToken));
    }

    @Test
    public void movingOperatorsWithHigherPriorityToResultDoesNotMoveOperatorsWithSamePriorityToResult()
            throws Exception {
        operatorStack.push(priorityOfThreeToken);
        operatorStack.push(priorityOfTwoToken);

        factory.moveOperatorsWithHigherPriorityToResult(operatorStack, result, priorityOfThreeToken);

        assertThat(result, empty());
    }

    @Test
    public void movingOperatorsWithHigherPriorityToResultDoesNotMoveOperatorsWithLowerPriorityToResult()
            throws Exception {
        operatorStack.push(priorityOfTwoToken);
        operatorStack.push(priorityOfThreeToken);

        factory.moveOperatorsWithHigherPriorityToResult(operatorStack, result, priorityOfThreeToken);

        assertThat(result, empty());
    }

    @Test
    public void moveOperatorsToResultUntilLeftParenthesisMovesOperatorsFromOperatorStack() throws Exception {
        operatorStack.push(priorityOfTwoToken);
        operatorStack.push(ComponentFactory.LEFT_PARENTHESIS);
        operatorStack.push(priorityOfThreeToken);

        factory.moveOperatorsToResultUntilLeftParenthesis(operatorStack, result);

        assertThat(operatorStack, allOf(
                not(hasItem(priorityOfThreeToken)),
                hasItems(ComponentFactory.LEFT_PARENTHESIS, priorityOfTwoToken)));
    }

    @Test
    public void moveOperatorsToResultUntilLeftParenthesisMovesOperatorsToResult() throws Exception {
        operatorStack.push(priorityOfTwoToken);
        operatorStack.push(ComponentFactory.LEFT_PARENTHESIS);
        operatorStack.push(priorityOfThreeToken);

        factory.moveOperatorsToResultUntilLeftParenthesis(operatorStack, result);

        assertThat(result, allOf(
                hasItem(priorityOfThreeToken),
                not(hasItems(ComponentFactory.LEFT_PARENTHESIS, priorityOfTwoToken))));

    }

    @Test(expected = IllegalArgumentException.class)
    public void notHittingLeftParenthesisWhileMovingOperatorsToResultUntilLeftParenthesisShouldThrowException()
            throws Exception {
        operatorStack.push(priorityOfTwoToken);
        operatorStack.push(priorityOfThreeToken);

        factory.moveOperatorsToResultUntilLeftParenthesis(operatorStack, result);
    }
}