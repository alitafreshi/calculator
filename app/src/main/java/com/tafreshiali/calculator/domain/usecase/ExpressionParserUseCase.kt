package com.tafreshiali.calculator.domain.usecase

import com.tafreshiali.calculator.domain.ExpressionPart
import com.tafreshiali.calculator.domain.ParenthesesType
import com.tafreshiali.calculator.domain.operationFormSymbol
import com.tafreshiali.calculator.domain.operationSymbols
import java.util.regex.Pattern

class ExpressionParserUseCase {
    operator fun invoke(userInput: String): List<ExpressionPart> = buildList {
        val pattern =
            "\\d+(?:\\.\\d+)?|[()*+\\-/%]" // This pattern matches integers, floating-point numbers, signs, and parentheses

        val acceptedNumbersPattern = "\\d+(?:\\.\\d+)?".toRegex()

        val matcher = Pattern.compile(pattern).matcher(userInput)

        while (matcher.find()) {
            val matchedCharacter = matcher.group()
            when {
                matchedCharacter in operationSymbols -> add(
                    ExpressionPart.Operation(
                        operation = operationFormSymbol(
                            symbol = matchedCharacter
                        )
                    )
                )

                matchedCharacter == "(" -> add(ExpressionPart.Parentheses(type = ParenthesesType.Opening))

                matchedCharacter == ")" -> add(ExpressionPart.Parentheses(type = ParenthesesType.Closing))

                matchedCharacter.matches(regex = acceptedNumbersPattern) -> {
                    add(ExpressionPart.Number(number = matchedCharacter.toDouble()))
                }
            }
        }
    }
}