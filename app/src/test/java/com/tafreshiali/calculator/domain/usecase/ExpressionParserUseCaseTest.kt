package com.tafreshiali.calculator.domain.usecase

import com.google.common.truth.Truth.assertThat
import com.tafreshiali.calculator.domain.ExpressionPart
import com.tafreshiali.calculator.domain.Operations
import com.tafreshiali.calculator.domain.ParenthesesType
import org.junit.Before
import org.junit.Test

class ExpressionParserUseCaseTest {
    private lateinit var expressionParserUseCase: ExpressionParserUseCase

    @Before
    fun `initialize the expression parser before using it in test function`() {
        expressionParserUseCase = ExpressionParserUseCase()
    }

    @Test
    fun `Simple expression simply parsed`() {
        //1.GIVEN
        /**
         * here step 1 and 2 merge to eachOther*/

        //2.DO SOMETHING WITH WHAT'S GIVEN
        val actual = expressionParserUseCase("3+5/8*3+17")

        //3.ASSERT EXPECTED == ACTUAL
        val expected = listOf(
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

        assertThat(expected).isEqualTo(actual)
    }

    @Test
    fun `Expression with parentheses is properly parsed`() {
        //1.GIVEN
        /**
         * here step 1 and 2 merge to eachOther*/

        //2.DO SOMETHING WITH WHAT'S GIVEN
        val actual = expressionParserUseCase("3*(4+5)/3.5")

        //3.ASSERT EXPECTED == ACTUAL
        val expected = listOf(
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

        assertThat(expected).isEqualTo(actual)
    }
}