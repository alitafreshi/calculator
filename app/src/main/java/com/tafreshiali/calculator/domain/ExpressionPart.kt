package com.tafreshiali.calculator.domain

sealed interface ExpressionPart {
    data class Number(val number: Double) : ExpressionPart
    data class Operation(val operation: Operations) : ExpressionPart
    data class Parentheses(val type: ParenthesesType): ExpressionPart
}

sealed interface ParenthesesType {
    object Opening : ParenthesesType
    object Closing : ParenthesesType
}
