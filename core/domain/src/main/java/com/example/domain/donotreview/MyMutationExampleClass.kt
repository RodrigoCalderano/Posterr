package com.example.domain.donotreview

class MyMutationExampleClass {
    fun compare(a: Int, b: Int) = when {
        (a < 0 || b < 0) -> AT_LEAST_ONE_NUMBER_NEGATIVE
        (a == 0 && b == 0) -> BOTH_ZERO
        (a > b) -> A_GRATER_THAN_B
        (b > a) -> B_GRATER_THAN_A
        else -> A_EQUALS_B
    }

    companion object {
        const val AT_LEAST_ONE_NUMBER_NEGATIVE = "Either A or B are negative"
        const val BOTH_ZERO = "Both A and B are zero"
        const val A_GRATER_THAN_B = "A is greater than B"
        const val B_GRATER_THAN_A = "B is greater than A"
        const val A_EQUALS_B = "A and B are equal"
    }
}