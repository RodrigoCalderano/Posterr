package com.example.domain.exampleformutation

class HighCyclomaticComplexity {
    fun complexCode(x: Int): String {
        if (x > 10) {
            if (x % 2 == 0) {
                if (x % 3 == 0) {
                    return "Number divisible by 2 and 3"
                }
            } else {
                when (x) {
                    5 -> return "Number is 5"
                    7 -> return "Number is 7"
                    else -> {
                        for (j in 1 until x) {
                            if (x % j == 0) {
                                return "Number has a divisor"
                            }
                        }
                    }
                }
            }
        } else {
            return "Number is less than or equal to 10"
        }
        return "Number is something weird"
    }
}