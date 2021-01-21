package lesson9.task2

import lesson8.task1.Point
import lesson9.task1.Matrix
import java.lang.IllegalArgumentException

/**
 * vertices[0] обращение к вершине по ЗНАЧЕНИЮ, не по индексу
 * индекс есть ячейка, для каждого графа, индекс статичен
 * 1  2  3  4
 * 5  6  7  8
 * 9  10 11 12
 * 13 14 15 16
 */
class Graph {

    data class Cell(val index: Int, var value: Int) {
        var neighbors = mutableSetOf<Cell>()
    }

    val field = mutableListOf<Cell>()

    fun fillFieldWithEmpty() {
        for (i in 0..15) {
            field.add(Cell(i, i))
        }
    }

    fun addCell(index: Int, value: Int) {
        field[value] = Cell(index, value)
    }

    private fun connect(first: Cell, second: Cell) {
        first.neighbors.add(second)
        second.neighbors.add(first)
    }

    fun connect(firstValue: Int, secondValue: Int) {
        connect(field[firstValue], field[secondValue])
    }

    fun copy(): Graph {
        val copy = Graph()
        field.forEach {
            val newCell = Cell(it.index, it.value)
            newCell.neighbors = it.neighbors.toSet().toMutableSet()
            copy.field.add(newCell)
        }
        return copy
    }

    fun changeValuesWithZero(value: Int) {
        val zeroCell = Cell(field[0].index, field[0].value)
        val valueCell = Cell(field[value].index, field[value].value)
        zeroCell.neighbors = field[0].neighbors.toSet().toMutableSet()
        zeroCell.neighbors.forEach {
            if (it.value == value) it.value = 0
        }
        valueCell.neighbors = field[value].neighbors.toSet().toMutableSet()
        valueCell.neighbors.forEach {
            if (it.value == 0) it.value = value
        }
        zeroCell.value = value
        valueCell.value = 0
        field[value] = zeroCell
        field[0] = valueCell
    }

    override fun equals(other: Any?) =
        other is Graph &&
                field == other.field

}