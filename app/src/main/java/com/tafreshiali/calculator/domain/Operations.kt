package com.tafreshiali.calculator.domain

import java.lang.IllegalArgumentException

enum class Operations(val symbol: String) {
    ADD("+"),
    SUBTRACT("-"),
    MULTIPLY("*"),
    DIVIDE("/"),
    PERCENT("%")
}

val operationSymbols = Operations.values().joinToString("") { it.symbol }

fun operationFormSymbol(symbol: String): Operations =
    Operations.values().find { it.symbol == symbol }
        ?: throw IllegalArgumentException("Invalid Symbol")


