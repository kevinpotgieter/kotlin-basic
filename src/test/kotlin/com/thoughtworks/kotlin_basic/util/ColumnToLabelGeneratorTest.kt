package com.thoughtworks.kotlin_basic.util

import org.junit.jupiter.api.Test
import kotlin.test.assertContentEquals
import kotlin.test.assertFailsWith

class ColumnToLabelGeneratorTest {


    @Test
    fun shouldGenerateAListOfStringsRepresentingTheColumnNames() {
        val results = ColumnToLabelGenerator.generateColumnNames(26, 3)
        assertContentEquals(listOf("Z", "AA", "AB"), results)
    }

    @Test
    fun shouldGenerateAListOfStringsRepresentingTheColumnNamesStartingFromtheBeginning() {
        val results = ColumnToLabelGenerator.generateColumnNames(1, 5)
        assertContentEquals(listOf("A", "B", "C", "D", "E"), results)
    }

    @Test
    fun shouldFailIfStartIsZero() {
        assertFailsWith(IllegalArgumentException::class) {
            ColumnToLabelGenerator.generateColumnNames(0, 3)
        }
    }

    @Test
    fun shouldFailIfColumnCountTOGenerateForIsLargerThan_78() {
        assertFailsWith(IllegalArgumentException::class) {
            ColumnToLabelGenerator.generateColumnNames(52, 27)
        }

        assertFailsWith(IllegalArgumentException::class) {
            ColumnToLabelGenerator.generateColumnNames(1, 79)
        }
    }


}