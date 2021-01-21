@file:Suppress("UNUSED_PARAMETER")

package lesson9.task2

import lesson9.task1.Matrix
import lesson9.task1.createMatrix
import java.lang.IllegalArgumentException
import java.lang.IllegalStateException


// Все задачи в этом файле требуют наличия реализации интерфейса "Матрица" в Matrix.kt

/**
 * Пример
 *
 * Транспонировать заданную матрицу matrix.
 * При транспонировании строки матрицы становятся столбцами и наоборот:
 *
 * 1 2 3      1 4 6 3
 * 4 5 6  ==> 2 5 5 2
 * 6 5 4      3 6 4 1
 * 3 2 1
 */
fun <E> transpose(matrix: Matrix<E>): Matrix<E> {
    if (matrix.width < 1 || matrix.height < 1) return matrix
    val result = createMatrix(height = matrix.width, width = matrix.height, e = matrix[0, 0])
    for (i in 0 until matrix.width) {
        for (j in 0 until matrix.height) {
            result[i, j] = matrix[j, i]
        }
    }
    return result
}

/**
 * Пример
 *
 * Сложить две заданные матрицы друг с другом.
 * Складывать можно только матрицы совпадающего размера -- в противном случае бросить IllegalArgumentException.
 * При сложении попарно складываются соответствующие элементы матриц
 */
operator fun Matrix<Int>.plus(other: Matrix<Int>): Matrix<Int> {
    require(!(width != other.width || height != other.height))
    if (width < 1 || height < 1) return this
    val result = createMatrix(height, width, this[0, 0])
    for (i in 0 until height) {
        for (j in 0 until width) {
            result[i, j] = this[i, j] + other[i, j]
        }
    }
    return result
}

/**
 * Сложная (5 баллов)
 *
 * Заполнить матрицу заданной высоты height и ширины width
 * натуральными числами от 1 до m*n по спирали,
 * начинающейся в левом верхнем углу и закрученной по часовой стрелке.
 *
 * Пример для height = 3, width = 4:
 *  1  2  3  4
 * 10 11 12  5
 *  9  8  7  6
 */
fun generateSpiral(height: Int, width: Int): Matrix<Int> = TODO()

/**
 * Сложная (5 баллов)
 *
 * Заполнить матрицу заданной высоты height и ширины width следующим образом.
 * Элементам, находящимся на периферии (по периметру матрицы), присвоить значение 1;
 * периметру оставшейся подматрицы – значение 2 и так далее до заполнения всей матрицы.
 *
 * Пример для height = 5, width = 6:
 *  1  1  1  1  1  1
 *  1  2  2  2  2  1
 *  1  2  3  3  2  1
 *  1  2  2  2  2  1
 *  1  1  1  1  1  1
 */
fun generateRectangles(height: Int, width: Int): Matrix<Int> = TODO()

/**
 * Сложная (5 баллов)
 *
 * Заполнить матрицу заданной высоты height и ширины width диагональной змейкой:
 * в левый верхний угол 1, во вторую от угла диагональ 2 и 3 сверху вниз, в третью 4-6 сверху вниз и так далее.
 *
 * Пример для height = 5, width = 4:
 *  1  2  4  7
 *  3  5  8 11
 *  6  9 12 15
 * 10 13 16 18
 * 14 17 19 20
 */
fun generateSnake(height: Int, width: Int): Matrix<Int> = TODO()

/**
 * Средняя (3 балла)
 *
 * Содержимое квадратной матрицы matrix (с произвольным содержимым) повернуть на 90 градусов по часовой стрелке.
 * Если height != width, бросить IllegalArgumentException.
 *
 * Пример:    Станет:
 * 1 2 3      7 4 1
 * 4 5 6      8 5 2
 * 7 8 9      9 6 3
 */
fun <E> rotate(matrix: Matrix<E>): Matrix<E> {
    if (matrix.height != matrix.width) throw IllegalArgumentException()
    val result = createMatrix(matrix.height, matrix.width, matrix[1, 1])
    for (i in matrix.width - 1 downTo 0) {
        for (j in 0 until matrix.height) {
            result[j, i] = matrix[matrix.height - 1 - i, j]
        }
    }

    return result
}

/**
 * Сложная (5 баллов)
 *
 * Проверить, является ли квадратная целочисленная матрица matrix латинским квадратом.
 * Латинским квадратом называется матрица размером n x n,
 * каждая строка и каждый столбец которой содержат все числа от 1 до n.
 * Если height != width, вернуть false.
 *
 * Пример латинского квадрата 3х3:
 * 2 3 1
 * 1 2 3
 * 3 1 2
 */
fun isLatinSquare(matrix: Matrix<Int>): Boolean {
    if (matrix.height != matrix.width) return false
    val n = matrix.height
    for (i in 0 until n) {
        val numbersList = mutableListOf<Int>()  //лист чисел от 1 до N для каждой строки
        for (j in 0 until n) {
            numbersList.add(j + 1)
        }
        for (j in 0 until n) {
            if (numbersList.contains(matrix[i, j])) numbersList.remove(matrix[i, j])
            else return false
        }
    }
    for (i in 0 until n) {
        val numbersList = mutableListOf<Int>()  //лист чисел от 1 до N для каждого столбца
        for (j in 0 until n) {
            numbersList.add(j + 1)
        }
        for (j in 0 until n) {
            if (numbersList.contains(matrix[j, i])) numbersList.remove(matrix[j, i])
            else return false
        }
    }

    return true
}

/**
 * Средняя (3 балла)
 *
 * В матрице matrix каждый элемент заменить суммой непосредственно примыкающих к нему
 * элементов по вертикали, горизонтали и диагоналям.
 *
 * Пример для матрицы 4 x 3: (11=2+4+5, 19=1+3+4+5+6, ...)
 * 1 2 3       11 19 13
 * 4 5 6  ===> 19 31 19
 * 6 5 4       19 31 19
 * 3 2 1       13 19 11
 *
 * Поскольку в матрице 1 х 1 примыкающие элементы отсутствуют,
 * для неё следует вернуть как результат нулевую матрицу:
 *
 * 42 ===> 0
 */
fun sumNeighbours(matrix: Matrix<Int>): Matrix<Int> = TODO()

/**
 * Средняя (4 балла)
 *
 * Целочисленная матрица matrix состоит из "дырок" (на их месте стоит 0) и "кирпичей" (на их месте стоит 1).
 * Найти в этой матрице все ряды и колонки, целиком состоящие из "дырок".
 * Результат вернуть в виде Holes(rows = список дырчатых рядов, columns = список дырчатых колонок).
 * Ряды и колонки нумеруются с нуля. Любой из спискоов rows / columns может оказаться пустым.
 *
 * Пример для матрицы 5 х 4:
 * 1 0 1 0
 * 0 0 1 0
 * 1 0 0 0 ==> результат: Holes(rows = listOf(4), columns = listOf(1, 3)): 4-й ряд, 1-я и 3-я колонки
 * 0 0 1 0
 * 0 0 0 0
 */
fun findHoles(matrix: Matrix<Int>): Holes = TODO()

/**
 * Класс для описания местонахождения "дырок" в матрице
 */
data class Holes(val rows: List<Int>, val columns: List<Int>)

/**
 * Средняя (3 балла)
 *
 * В целочисленной матрице matrix каждый элемент заменить суммой элементов подматрицы,
 * расположенной в левом верхнем углу матрицы matrix и ограниченной справа-снизу данным элементом.
 *
 * Пример для матрицы 3 х 3:
 *
 * 1  2  3      1  3  6
 * 4  5  6  =>  5 12 21
 * 7  8  9     12 27 45
 *
 * К примеру, центральный элемент 12 = 1 + 2 + 4 + 5, элемент в левом нижнем углу 12 = 1 + 4 + 7 и так далее.
 */
fun sumSubMatrix(matrix: Matrix<Int>): Matrix<Int> = TODO()

/**
 * Простая (2 балла)
 *
 * Инвертировать заданную матрицу.
 * При инвертировании знак каждого элемента матрицы следует заменить на обратный
 */
operator fun Matrix<Int>.unaryMinus(): Matrix<Int> {
    val result = createMatrix(this.height, this.width, 0)
    for (i in 0 until this.height) {
        for (j in 0 until this.width) {
            result[i, j] = -this[i, j]
        }
    }
    return result
}

/**
 * Средняя (4 балла)
 *
 * Перемножить две заданные матрицы друг с другом.
 * Матрицы можно умножать, только если ширина первой матрицы совпадает с высотой второй матрицы.
 * В противном случае бросить IllegalArgumentException.
 * Подробно про порядок умножения см. статью Википедии "Умножение матриц".
 */
operator fun Matrix<Int>.times(other: Matrix<Int>): Matrix<Int> = TODO(this.toString())

/**
 * Сложная (7 баллов)
 *
 * Даны мозаичные изображения замочной скважины и ключа. Пройдет ли ключ в скважину?
 * То есть даны две матрицы key и lock, key.height <= lock.height, key.width <= lock.width, состоящие из нулей и единиц.
 *
 * Проверить, можно ли наложить матрицу key на матрицу lock (без поворота, разрешается только сдвиг) так,
 * чтобы каждой единице в матрице lock (штырь) соответствовал ноль в матрице key (прорезь),
 * а каждому нулю в матрице lock (дырка) соответствовала, наоборот, единица в матрице key (штырь).
 * Ключ при сдвиге не может выходить за пределы замка.
 *
 * Пример: ключ подойдёт, если его сдвинуть на 1 по ширине
 * lock    key
 * 1 0 1   1 0
 * 0 1 0   0 1
 * 1 1 1
 *
 * Вернуть тройку (Triple) -- (да/нет, требуемый сдвиг по высоте, требуемый сдвиг по ширине).
 * Если наложение невозможно, то первый элемент тройки "нет" и сдвиги могут быть любыми.
 */
fun canOpenLock(key: Matrix<Int>, lock: Matrix<Int>): Triple<Boolean, Int, Int> {
    for (lockI in 0..lock.width - key.width) {
        for (lockJ in 0..lock.height - key.height) {
            var keyCounter = 0
            for (keyI in 0 until key.width) {
                for (keyJ in 0 until key.height) {
                    if (lock[lockI + keyI, lockJ + keyJ] != key[keyI, keyJ]) keyCounter++
                }
            }
            if (keyCounter == key.height * key.width) return Triple(true, lockI, lockJ)
        }
    }
    return Triple(false, 0, 0)
}


/**
 * Сложная (8 баллов)
 *
 * В матрице matrix размером 4х4 дана исходная позиция для игры в 15, например
 *  5  7  9  1
 *  2 12 14 15
 *  3  4  6  8
 * 10 11 13  0
 *
 * Здесь 0 обозначает пустую клетку, а 1-15 – фишки с соответствующими номерами.
 * Напомним, что "игра в 15" имеет квадратное поле 4х4, по которому двигается 15 фишек,
 * одна клетка всегда остаётся пустой. Цель игры -- упорядочить фишки на игровом поле.
 *
 * В списке moves задана последовательность ходов, например [8, 6, 13, 11, 10, 3].
 * Ход задаётся номером фишки, которая передвигается на пустое место (то есть, меняется местами с нулём).
 * Фишка должна примыкать к пустому месту по горизонтали или вертикали, иначе ход не будет возможным.
 * Все номера должны быть в пределах от 1 до 15.
 * Определить финальную позицию после выполнения всех ходов и вернуть её.
 * Если какой-либо ход является невозможным или список содержит неверные номера,
 * бросить IllegalStateException.
 *
 * В данном случае должно получиться
 * 5  7  9  1
 * 2 12 14 15
 * 0  4 13  6
 * 3 10 11  8
 */
fun fifteenGameMoves(matrix: Matrix<Int>, moves: List<Int>): Matrix<Int> {
    if (moves.isEmpty()) return matrix
    if (matrix.height != 4 || matrix.width != 4 || moves.any { it !in 0..15 }) throw IllegalStateException()

    moves.forEach {
        var oneMove = true
        for (i in 0..3) {
            for (j in 0..3) {
                if (it == matrix[i, j] && oneMove) {
                    if (matrix[i - 1, j] == 0 || matrix[i + 1, j] == 0 || matrix[i, j - 1] == 0 || matrix[i, j + 1] == 0) {
                        for (ii in 0..3) {
                            for (jj in 0..3) {
                                if (matrix[ii, jj] == 0 && oneMove) {
                                    matrix[ii, jj] = it
                                    matrix[i, j] = 0
                                    oneMove = false
                                }
                            }
                        }
                    } else throw IllegalStateException()
                }
            }
        }
    }
    return matrix

}

/**
 * Очень сложная (32 балла)
 *
 * В матрице matrix размером 4х4 дана исходная позиция для игры в 15, например
 *  5  7  9  2
 *  1 12 14 15
 *  3  4  6  8
 * 10 11 13  0
 *
 * Здесь 0 обозначает пустую клетку, а 1-15 – фишки с соответствующими номерами.
 * Напомним, что "игра в 15" имеет квадратное поле 4х4, по которому двигается 15 фишек,
 * одна клетка всегда остаётся пустой.
 *
 * Цель игры -- упорядочить фишки на игровом поле, приведя позицию к одному из следующих двух состояний:
 *
 *  1  2  3  4          1  2  3  4
 *  5  6  7  8   ИЛИ    5  6  7  8
 *  9 10 11 12          9 10 11 12
 * 13 14 15  0         13 15 14  0
 *
 * Можно математически доказать, что РОВНО ОДНО из этих двух состояний достижимо из любой исходной позиции.
 *
 * Вернуть решение -- список ходов, приводящих исходную позицию к одной из двух упорядоченных.
 * Каждый ход -- это перемена мест фишки с заданным номером с пустой клеткой (0),
 * при этом заданная фишка должна по горизонтали или по вертикали примыкать к пустой клетке (но НЕ по диагонали).
 * К примеру, ход 13 в исходной позиции меняет местами 13 и 0, а ход 11 в той же позиции невозможен.
 *
 * Одно из решений исходной позиции:
 *
 * [8, 6, 14, 12, 4, 11, 13, 14, 12, 4,
 * 7, 5, 1, 3, 11, 7, 3, 11, 7, 12, 6,
 * 15, 4, 9, 2, 4, 9, 3, 5, 2, 3, 9,
 * 15, 8, 14, 13, 12, 7, 11, 5, 7, 6,
 * 9, 15, 8, 14, 13, 9, 15, 7, 6, 12,
 * 9, 13, 14, 15, 12, 11, 10, 9, 13, 14,
 * 15, 12, 11, 10, 9, 13, 14, 15]
 *
 * Перед решением этой задачи НЕОБХОДИМО решить предыдущую
 */
fun fifteenGameSolution(matrix: Matrix<Int>): List<Int> {
    val targetMatrixOne = createMatrixFromLists(
        4, 4, listOf(
            listOf(1, 2, 3, 4), listOf(5, 6, 7, 8),
            listOf(9, 10, 11, 12), listOf(13, 14, 15, 0)
        )
    )
    val targetMatrixTwo = createMatrixFromLists(
        4, 4, listOf(
            listOf(1, 2, 3, 4), listOf(5, 6, 7, 8),
            listOf(9, 10, 11, 12), listOf(13, 15, 14, 0)
        )
    )
    val targetGraphOne = matrixToGraph(targetMatrixOne)
    val targetGraphTwo = matrixToGraph(targetMatrixTwo)
    val startGraph = matrixToGraph(matrix)
    val paths = mutableSetOf<List<Graph.Cell>>()
    val steps = mutableListOf<Int>()
    val result = mutableListOf<Int>()
    dfs(startGraph, targetGraphOne, targetGraphTwo, paths, steps, result)
    return steps
}

fun dfs(
    start: Graph,
    targetOne: Graph,
    targetTwo: Graph,
    visitedFields: MutableSet<List<Graph.Cell>>,
    steps: MutableList<Int>,
    result: MutableList<Int>
) {
    if (start == targetOne || start == targetTwo) {
        result.addAll(steps.toList())
        return
    }
    if (visitedFields.contains(start.field.toList())) { //проверяем, были ли мы на аналогичном поле
        //val indexInPast = visitedFields.indexOf(start.field.toList())
        steps.removeLast()                           //да= ремуваем шаг, который сюда привел и выпрыгиваем из текущей рекурсии
    } else {                                            //если нет, продолжаем рекунсию
        visitedFields.add(start.field.toList())         //добавляем текущее поле в посещенное
        start.field[0].neighbors.forEach {              //перебираем соседей нуля, чтобы поменяться значениями с каждым соседом
            steps.add(it.value)                         //какой шаг мы делаем, чтобы вызвать следующую dfs
            val newStart = start.copy()
            newStart.changeValuesWithZero(it.value)
            dfs(newStart, targetOne, targetTwo, visitedFields, steps, result)
            //steps.removeLast()
        }
    }
}


fun matrixToGraph(matrix: Matrix<Int>): Graph {
    val graph = Graph()
    graph.fillFieldWithEmpty()
    //add all the vertices
    run {
        graph.addCell(1, matrix[0, 0])
        graph.addCell(2, matrix[0, 1])
        graph.addCell(3, matrix[0, 2])
        graph.addCell(4, matrix[0, 3])
        graph.addCell(5, matrix[1, 0])
        graph.addCell(6, matrix[1, 1])
        graph.addCell(7, matrix[1, 2])
        graph.addCell(8, matrix[1, 3])
        graph.addCell(9, matrix[2, 0])
        graph.addCell(10, matrix[2, 1])
        graph.addCell(11, matrix[2, 2])
        graph.addCell(12, matrix[2, 3])
        graph.addCell(13, matrix[3, 0])
        graph.addCell(14, matrix[3, 1])
        graph.addCell(15, matrix[3, 2])
        graph.addCell(16, matrix[3, 3])
    }
    //connect all the vertices
    run {
        graph.connect(matrix[0, 0], matrix[0, 1])
        graph.connect(matrix[0, 0], matrix[1, 0])
        graph.connect(matrix[0, 1], matrix[0, 2])
        graph.connect(matrix[0, 1], matrix[1, 1])
        graph.connect(matrix[0, 2], matrix[0, 3])
        graph.connect(matrix[0, 2], matrix[1, 2])
        graph.connect(matrix[0, 3], matrix[1, 3])
        graph.connect(matrix[1, 0], matrix[1, 1])
        graph.connect(matrix[1, 1], matrix[1, 2])
        graph.connect(matrix[1, 2], matrix[1, 3])
        graph.connect(matrix[1, 0], matrix[2, 0])
        graph.connect(matrix[1, 1], matrix[2, 1])
        graph.connect(matrix[1, 2], matrix[2, 2])
        graph.connect(matrix[1, 3], matrix[2, 3])
        graph.connect(matrix[2, 0], matrix[2, 1])
        graph.connect(matrix[2, 1], matrix[2, 2])
        graph.connect(matrix[2, 2], matrix[2, 3])
        graph.connect(matrix[2, 0], matrix[3, 0])
        graph.connect(matrix[2, 1], matrix[3, 1])
        graph.connect(matrix[2, 2], matrix[3, 2])
        graph.connect(matrix[2, 3], matrix[3, 3])
        graph.connect(matrix[3, 0], matrix[3, 1])
        graph.connect(matrix[3, 1], matrix[3, 2])
        graph.connect(matrix[3, 2], matrix[3, 3])
    }
    return graph
}

private fun <E> createMatrixFromLists(height: Int, width: Int, values: List<List<E>>): Matrix<E> {
    val matrix = createMatrix(height, width, values[0][0])
    for (row in 0 until height) {
        for (column in 0 until width) {
            matrix[row, column] = values[row][column]
        }
    }
    return matrix
}



