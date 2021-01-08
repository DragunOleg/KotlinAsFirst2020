@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson4.task1

import lesson1.task1.discriminant
import lesson3.task1.digitNumber
import kotlin.math.sqrt

// Урок 4: списки
// Максимальное количество баллов = 12
// Рекомендуемое количество баллов = 8
// Вместе с предыдущими уроками = 24/33

/**
 * Пример
 *
 * Найти все корни уравнения x^2 = y
 */
fun sqRoots(y: Double) =
    when {
        y < 0 -> listOf()
        y == 0.0 -> listOf(0.0)
        else -> {
            val root = sqrt(y)
            // Результат!
            listOf(-root, root)
        }
    }

/**
 * Пример
 *
 * Найти все корни биквадратного уравнения ax^4 + bx^2 + c = 0.
 * Вернуть список корней (пустой, если корней нет)
 */
fun biRoots(a: Double, b: Double, c: Double): List<Double> {
    if (a == 0.0) {
        return if (b == 0.0) listOf()
        else sqRoots(-c / b)
    }
    val d = discriminant(a, b, c)
    if (d < 0.0) return listOf()
    if (d == 0.0) return sqRoots(-b / (2 * a))
    val y1 = (-b + sqrt(d)) / (2 * a)
    val y2 = (-b - sqrt(d)) / (2 * a)
    return sqRoots(y1) + sqRoots(y2)
}

/**
 * Пример
 *
 * Выделить в список отрицательные элементы из заданного списка
 */
fun negativeList(list: List<Int>): List<Int> {
    val result = mutableListOf<Int>()
    for (element in list) {
        if (element < 0) {
            result.add(element)
        }
    }
    return result
}

/**
 * Пример
 *
 * Изменить знак для всех положительных элементов списка
 */
fun invertPositives(list: MutableList<Int>) {
    for (i in 0 until list.size) {
        val element = list[i]
        if (element > 0) {
            list[i] = -element
        }
    }
}

/**
 * Пример
 *
 * Из имеющегося списка целых чисел, сформировать список их квадратов
 */
fun squares(list: List<Int>) = list.map { it * it }

/**
 * Пример
 *
 * Из имеющихся целых чисел, заданного через vararg-параметр, сформировать массив их квадратов
 */
fun squares(vararg array: Int) = squares(array.toList()).toTypedArray()

/**
 * Пример
 *
 * По заданной строке str определить, является ли она палиндромом.
 * В палиндроме первый символ должен быть равен последнему, второй предпоследнему и т.д.
 * Одни и те же буквы в разном регистре следует считать равными с точки зрения данной задачи.
 * Пробелы не следует принимать во внимание при сравнении символов, например, строка
 * "А роза упала на лапу Азора" является палиндромом.
 */
fun isPalindrome(str: String): Boolean {
    val lowerCase = str.toLowerCase().filter { it != ' ' }
    for (i in 0..lowerCase.length / 2) {
        if (lowerCase[i] != lowerCase[lowerCase.length - i - 1]) return false
    }
    return true
}

/**
 * Пример
 *
 * По имеющемуся списку целых чисел, например [3, 6, 5, 4, 9], построить строку с примером их суммирования:
 * 3 + 6 + 5 + 4 + 9 = 27 в данном случае.
 */
fun buildSumExample(list: List<Int>) = list.joinToString(separator = " + ", postfix = " = ${list.sum()}")

/**
 * Простая (2 балла)
 *
 * Найти модуль заданного вектора, представленного в виде списка v,
 * по формуле abs = sqrt(a1^2 + a2^2 + ... + aN^2).
 * Модуль пустого вектора считать равным 0.0.
 */
fun abs(v: List<Double>): Double = TODO()

/**
 * Простая (2 балла)
 *
 * Рассчитать среднее арифметическое элементов списка list. Вернуть 0.0, если список пуст
 */
fun mean(list: List<Double>): Double {
    if (list.isEmpty()) return 0.0
    return list.sum() / list.size
}

/**
 * Средняя (3 балла)
 *
 * Центрировать заданный список list, уменьшив каждый элемент на среднее арифметическое всех элементов.
 * Если список пуст, не делать ничего. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun center(list: MutableList<Double>): MutableList<Double> {
    return if (list.isEmpty()) list
    else {
        val mean = mean(list)
        for (i in list.indices) {
            list[i] -= mean
        }
        list
    }
}

/**
 * Средняя (3 балла)
 *
 * Найти скалярное произведение двух векторов равной размерности,
 * представленные в виде списков a и b. Скалярное произведение считать по формуле:
 * C = a1b1 + a2b2 + ... + aNbN. Произведение пустых векторов считать равным 0.
 */
fun times(a: List<Int>, b: List<Int>): Int = TODO()

/**
 * Средняя (3 балла)
 *
 * Рассчитать значение многочлена при заданном x:
 * p(x) = p0 + p1*x + p2*x^2 + p3*x^3 + ... + pN*x^N.
 * Коэффициенты многочлена заданы списком p: (p0, p1, p2, p3, ..., pN).
 * Значение пустого многочлена равно 0 при любом x.
 */
fun polynom(p: List<Int>, x: Int): Int = TODO()

/**
 * Средняя (3 балла)
 *
 * В заданном списке list каждый элемент, кроме первого, заменить
 * суммой данного элемента и всех предыдущих.
 * Например: 1, 2, 3, 4 -> 1, 3, 6, 10.
 * Пустой список не следует изменять. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun accumulate(list: MutableList<Int>): MutableList<Int> {
    return if (list.isEmpty() || list.size == 1) list
    else {
        for (i in list.indices) {
            if (i > 0) list[i] += list[i - 1]
        }
        list
    }
}


/**
 * Средняя (3 балла)
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде списка множителей, например 75 -> (3, 5, 5).
 * Множители в списке должны располагаться по возрастанию.
 */
fun factorize(n: Int): List<Int> = TODO()

/**
 * Сложная (4 балла)
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде строки, например 75 -> 3*5*5
 * Множители в результирующей строке должны располагаться по возрастанию.
 */
fun factorizeToString(n: Int): String = TODO()

/**
 * Средняя (3 балла)
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием base > 1.
 * Результат перевода вернуть в виде списка цифр в base-ичной системе от старшей к младшей,
 * например: n = 100, base = 4 -> (1, 2, 1, 0) или n = 250, base = 14 -> (1, 3, 12)
 */
fun convert(n: Int, base: Int): List<Int> = TODO()

/**
 * Сложная (4 балла)
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием 1 < base < 37.
 * Результат перевода вернуть в виде строки, цифры более 9 представлять латинскими
 * строчными буквами: 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: n = 100, base = 4 -> 1210, n = 250, base = 14 -> 13c
 *
 * Использовать функции стандартной библиотеки, напрямую и полностью решающие данную задачу
 * (например, n.toString(base) и подобные), запрещается.
 */
fun convertToString(n: Int, base: Int): String = TODO()

/**
 * Средняя (3 балла)
 *
 * Перевести число, представленное списком цифр digits от старшей к младшей,
 * из системы счисления с основанием base в десятичную.
 * Например: digits = (1, 3, 12), base = 14 -> 250
 */
fun decimal(digits: List<Int>, base: Int): Int = TODO()

/**
 * Сложная (4 балла)
 *
 * Перевести число, представленное цифровой строкой str,
 * из системы счисления с основанием base в десятичную.
 * Цифры более 9 представляются латинскими строчными буквами:
 * 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: str = "13c", base = 14 -> 250
 *
 * Использовать функции стандартной библиотеки, напрямую и полностью решающие данную задачу
 * (например, str.toInt(base)), запрещается.
 */
fun decimalFromString(str: String, base: Int): Int = TODO()

/**
 * Сложная (5 баллов)
 *
 * Перевести натуральное число n > 0 в римскую систему.
 * Римские цифры: 1 = I, 4 = IV, 5 = V, 9 = IX, 10 = X, 40 = XL, 50 = L,
 * 90 = XC, 100 = C, 400 = CD, 500 = D, 900 = CM, 1000 = M.
 * Например: 23 = XXIII, 44 = XLIV, 100 = C
 */
fun roman(n: Int): String = TODO()

/**
 * Очень сложная (7 баллов)
 *
 * Записать заданное натуральное число 1..999999 прописью по-русски.
 * Например, 375 = "триста семьдесят пять",
 * 23964 = "двадцать три тысячи девятьсот шестьдесят четыре"
 */
fun russian(n: Int): String {
    val digitNumber = digitNumber(n)    //цифр в числе
    val digitsToStrList = mutableListOf<String>()   //изменяемый список строк, частично формирующий итоговое число
    val units =
        (n.toString()[digitNumber - 1]).toInt() - '0'.toInt()   //-'0'.toInt() subtracts the ASCII code of 0 from each digit. Иначе передается неверный инт

    addUnitsToList(units, digitsToStrList) //добавляем единицы к листу

    if (n >= 10) {
        val tens = n.toString()[digitNumber - 2].toInt() - '0'.toInt()
        addTensToList(units, tens, digitsToStrList)

        if (n >= 100) {
            val hundreds = n.toString()[digitNumber - 3].toInt() - '0'.toInt()
            addHundredsToList(hundreds, digitsToStrList)

            if (n >= 1000) {
                val thousands = n.toString()[digitNumber - 4].toInt() - '0'.toInt()
                addThousandsToList(thousands, digitsToStrList)

                if (n >= 10000) {
                    val tensOfThousands = n.toString()[digitNumber - 5].toInt() - '0'.toInt()
                    addTensOfThousandsToList(thousands, tensOfThousands, digitsToStrList)

                    if (n >= 100000) {
                        val hundredsOfThousands = n.toString()[digitNumber - 6].toInt() - '0'.toInt()
                        addHundredsToList(hundredsOfThousands, digitsToStrList)
                    }
                }
            }
        }
    }
    while (digitsToStrList.any { it == "" }) {
        digitsToStrList.remove("")
    }
    return digitsToStrList.joinToString(separator = " ")
}

private fun addUnitsToList(units: Int, list: MutableList<String>): MutableList<String> {
    when (units) {
        0 -> list.add(0, "")
        1 -> list.add(0, "один")
        2 -> list.add(0, "два")
        3 -> list.add(0, "три")
        4 -> list.add(0, "четыре")
        5 -> list.add(0, "пять")
        6 -> list.add(0, "шесть")
        7 -> list.add(0, "семь")
        8 -> list.add(0, "восемь")
        9 -> list.add(0, "девять")
    }
    return list
}

private fun addTensToList(units: Int, tens: Int, list: MutableList<String>) /*MutableList<String>*/ {
    when (tens) {
        0 -> list.add(0, "")
        1 -> {
            when (units) {      //заменяем единицы для уникального десятка
                0 -> list[0] = "десять"
                1 -> list[0] = "одиннадцать"
                2 -> list[0] = "двенадцать"
                3 -> list[0] = "тринадцать"
                4 -> list[0] = "четырнадцать"
                5 -> list[0] = "пятнадцать"
                6 -> list[0] = "шестнадцать"
                7 -> list[0] = "семнадцать"
                8 -> list[0] = "восемнадцать"
                9 -> list[0] = "девятнадцать"
            }
        }
        2 -> list.add(0, "двадцать")
        3 -> list.add(0, "тридцать")
        4 -> list.add(0, "сорок")
        5 -> list.add(0, "пятьдесят")
        6 -> list.add(0, "шестьдесят")
        7 -> list.add(0, "семьдесят")
        8 -> list.add(0, "восемьдесят")
        9 -> list.add(0, "девяносто")
    }
    //return list
}

private fun addHundredsToList(hundreds: Int, list: MutableList<String>): MutableList<String> {
    when (hundreds) {
        0 -> list.add(0, "")
        1 -> list.add(0, "сто")
        2 -> list.add(0, "двести")
        3 -> list.add(0, "триста")
        4 -> list.add(0, "четыреста")
        5 -> list.add(0, "пятьсот")
        6 -> list.add(0, "шестьсот")
        7 -> list.add(0, "семьсот")
        8 -> list.add(0, "восемьсот")
        9 -> list.add(0, "девятьсот")
    }
    return list
}

private fun addThousandsToList(thousands: Int, list: MutableList<String>): MutableList<String> {
    when (thousands) {
        0 -> list.add(0, "тысяч")
        1 -> list.add(0, "одна тысяча")
        2 -> list.add(0, "две тысячи")
        3 -> list.add(0, "три тысячи")
        4 -> list.add(0, "четыре тысячи")
        5 -> list.add(0, "пять тысяч")
        6 -> list.add(0, "шесть тысяч")
        7 -> list.add(0, "семь тысяч")
        8 -> list.add(0, "восемь тысяч")
        9 -> list.add(0, "девять тысяч")
    }
    return list
}

private fun addTensOfThousandsToList(
    thousands: Int,
    tensOfThousands: Int,
    list: MutableList<String>
): MutableList<String> {
    when (tensOfThousands) {
        0 -> list.add(0, "")
        1 -> {
            when (thousands) {
                0 -> list[0] = "десять"
                1 -> list[0] = "одиннадцать тысяч"
                2 -> list[0] = "двенадцать тысяч"
                3 -> list[0] = "тринадцать тысяч"
                4 -> list[0] = "четырнадцать тысяч"
                5 -> list[0] = "пятнадцать тысяч"
                6 -> list[0] = "шестнадцать тысяч"
                7 -> list[0] = "семнадцать тысяч"
                8 -> list[0] = "восемнадцать тысяч"
                9 -> list[0] = "девятнадцать тысяч"
            }
        }
        2 -> list.add(0, "двадцать")
        3 -> list.add(0, "тридцать")
        4 -> list.add(0, "сорок")
        5 -> list.add(0, "пятьдесят")
        6 -> list.add(0, "шестьдесят")
        7 -> list.add(0, "семьдесят")
        8 -> list.add(0, "восемьдесят")
        9 -> list.add(0, "девяносто")
    }
    return list
}