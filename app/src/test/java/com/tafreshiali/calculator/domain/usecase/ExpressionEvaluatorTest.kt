package com.tafreshiali.calculator.domain.usecase

import com.google.common.truth.Truth.assertThat
import com.tafreshiali.calculator.domain.ExpressionPart
import com.tafreshiali.calculator.domain.Operations
import com.tafreshiali.calculator.domain.ParenthesesType
import org.junit.Before
import org.junit.Test

class ExpressionEvaluatorTest {
    private lateinit var expressionEvaluator: ExpressionEvaluator

    @Before
    fun `initialize expression evaluator before running each testcase`() {
        expressionEvaluator = ExpressionEvaluator()
    }

    @Test
    fun `Simple expression evaluator`() {

        // The original equation is = 3+5/8*3+17

        val parsedExpressions = listOf(
            ExpressionPart.Number(number = 3.0),
            ExpressionPart.Operation(operation = Operations.ADD),
            ExpressionPart.Number(number = 5.0),
            ExpressionPart.Operation(operation = Operations.DIVIDE),
            ExpressionPart.Number(number = 8.0),
            ExpressionPart.Operation(operation = Operations.MULTIPLY),
            ExpressionPart.Number(number = 3.0),
            ExpressionPart.Operation(operation = Operations.ADD),
            ExpressionPart.Number(number = 17.0)
        )

        val expected = expressionEvaluator(expression = parsedExpressions)
        val actual = 21.875

        assertThat(expected).isEqualTo(actual)

    }

    @Test
    fun `Simple expression evaluator with parentheses and decimal numbers`() {

        // The original equation is = 3*(4+5)/3.5

        val parsedExpressions = listOf(
            ExpressionPart.Number(number = 3.0),
            ExpressionPart.Operation(operation = Operations.MULTIPLY),
            ExpressionPart.Parentheses(type = ParenthesesType.Opening),
            ExpressionPart.Number(number = 4.0),
            ExpressionPart.Operation(operation = Operations.ADD),
            ExpressionPart.Number(number = 5.0),
            ExpressionPart.Parentheses(type = ParenthesesType.Closing),
            ExpressionPart.Operation(operation = Operations.DIVIDE),
            ExpressionPart.Number(number = 3.5)
        )

        val expected = expressionEvaluator(expression = parsedExpressions)

        val actual = 7.714285714285714

        assertThat(expected).isEqualTo(actual)
    }
}