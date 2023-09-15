package com.tafreshiali.calculator.domain.usecase

import com.google.common.truth.Truth.assertThat
import com.tafreshiali.calculator.domain.CalculationAction
import com.tafreshiali.calculator.domain.Operations
import org.junit.Before
import org.junit.Test

class ExpressionWriterUseCaseTest {
    private lateinit var expressionWriterUseCase: ExpressionWriterUseCase
    private lateinit var expressionParserUseCase: ExpressionParserUseCase
    private lateinit var expressionEvaluatorUseCase: ExpressionEvaluatorUseCase

    @Before
    fun setUp() {
        expressionParserUseCase = ExpressionParserUseCase()
        expressionEvaluatorUseCase = ExpressionEvaluatorUseCase()
        expressionWriterUseCase = ExpressionWriterUseCase(
            expressionEvaluatorUseCase = expressionEvaluatorUseCase,
            expressionParserUseCase = expressionParserUseCase
        )
    }

    @Test
    fun `initial parentheses parsed`() {
        // we want to process this => (5+4)
        expressionWriterUseCase(action = CalculationAction.Parentheses)
        expressionWriterUseCase(action = CalculationAction.Number(number = 5))
        expressionWriterUseCase(action = CalculationAction.Operation(operation = Operations.ADD))
        expressionWriterUseCase(action = CalculationAction.Number(number = 4))
        expressionWriterUseCase(action = CalculationAction.Parentheses)

        assertThat(expressionWriterUseCase.expression).isEqualTo("(5+4)")
    }

    @Test
    fun `closing parentheses at the start not parsed `() {
        expressionWriterUseCase(action = CalculationAction.Parentheses)
        expressionWriterUseCase(action = CalculationAction.Parentheses)
        assertThat(expressionWriterUseCase.expression).isEqualTo("((")
    }

    @Test
    fun `parentheses around a number are parsed `() {
        expressionWriterUseCase(action = CalculationAction.Parentheses)
        expressionWriterUseCase(action = CalculationAction.Number(number = 5))
        expressionWriterUseCase(action = CalculationAction.Parentheses)
        assertThat(expressionWriterUseCase.expression).isEqualTo("(5)")
    }
}