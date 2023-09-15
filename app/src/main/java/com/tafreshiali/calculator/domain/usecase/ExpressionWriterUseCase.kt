package com.tafreshiali.calculator.domain.usecase

import com.tafreshiali.calculator.domain.CalculationAction
import com.tafreshiali.calculator.domain.Operations
import com.tafreshiali.calculator.domain.operationSymbols

class ExpressionWriterUseCase constructor(
    private val expressionParserUseCase: ExpressionParserUseCase,
    private val expressionEvaluatorUseCase: ExpressionEvaluatorUseCase
) {
    var expression = ""
    operator fun invoke(action: CalculationAction) {
        when (action) {

            CalculationAction.Calculate -> {
                val parser = expressionParserUseCase(prepareForCalculation())
                val evaluator = expressionEvaluatorUseCase(expression = parser)
                expression = evaluator.toString()
            }

            CalculationAction.Clear -> expression = ""

            CalculationAction.Decimal -> {
                if (canEnterDecimal()) {
                    expression += "."
                }
            }

            CalculationAction.Delete -> expression = expression.dropLast(n = 1)

            is CalculationAction.Number -> expression += action.number

            is CalculationAction.Operation -> {
                if (canEnterOperation(operation = action.operation)) {
                    expression += action.operation.symbol
                }
            }

            CalculationAction.Parentheses -> {
                processParentheses()
            }
        }
    }


    private fun prepareForCalculation(): String =
        Regex("[$operationSymbols(\\d]*\\.?\\d*$").find(expression)?.value ?: "0"

    private fun processParentheses() {
        val openingCount = expression.count { it == '(' }
        val closingCount = expression.count { it == ')' }
        expression += when {
            expression.isEmpty() ||
                    expression.last() in "$operationSymbols(" -> "("

            expression.last() in "0123456789)" &&
                    openingCount == closingCount -> return

            else -> ")"
        }
    }


    private fun canEnterDecimal(): Boolean =
        expression.isEmpty() || !Regex("[.\\d]*\\d$").matches(expression)


    private fun canEnterOperation(operation: Operations): Boolean =
        when (operation) {
            Operations.ADD, Operations.SUBTRACT -> expression.isEmpty() || expression.last() in "$operationSymbols()0123456789"
            else -> expression.isEmpty() || expression.last() in "0123456789)"
        }

}