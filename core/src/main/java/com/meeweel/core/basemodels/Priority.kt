package com.meeweel.core.basemodels

enum class Priority(val int: Int, val text: String) {
    NONE(0, ""),
    GREEN(1, "Не к спеху"),
    YELLOW(2, "Скоро"),
    ORANGE(3, "Пора"),
    RED(4, "Срочно!")
}