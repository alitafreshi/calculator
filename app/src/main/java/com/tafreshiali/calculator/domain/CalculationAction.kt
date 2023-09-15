package com.tafreshiali.calculator.domain

sealed interface CalculationAction {
    data class Number(val number: Int) : CalculationAction
    data class Operation(val operation: Operations) : CalculationAction
    object Clear : CalculationAction
    object Delete : CalculationAction
    object Parentheses : CalculationAction
    object Calculate : CalculationAction
    object Decimal : CalculationAction
}