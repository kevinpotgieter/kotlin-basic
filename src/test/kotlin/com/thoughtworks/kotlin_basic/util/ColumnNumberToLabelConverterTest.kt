package com.thoughtworks.kotlin_basic.util

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class ColumnNumberToLabelConverterTest {

    @Test
    fun shouldFailWithExceptionIfColumnNumberIsNegative() {
        assertFailsWith(
            exceptionClass = IllegalArgumentException::class,
            message = "Column number must be positive and greater than zero"
        ) {
            ColumnNumberToLabelConverter.convertToLabel(-1)
        }
    }

    @Test
    fun shouldFailWithExceptionIfColumnNumberIsZero() {
        assertFailsWith(
            exceptionClass = IllegalArgumentException::class,
            message = "Column number must be positive and greater than zero"
        ) {
            ColumnNumberToLabelConverter.convertToLabel(0)
        }
    }

    @Test
    fun shouldReturnAnAlphabetForEachNumberIndexInTheAlphabet() {
        assertEquals("A", ColumnNumberToLabelConverter.convertToLabel(1))
        assertEquals("B", ColumnNumberToLabelConverter.convertToLabel(2))
        assertEquals("C", ColumnNumberToLabelConverter.convertToLabel(3))
        assertEquals("D", ColumnNumberToLabelConverter.convertToLabel(4))
        assertEquals("E", ColumnNumberToLabelConverter.convertToLabel(5))
        assertEquals("F", ColumnNumberToLabelConverter.convertToLabel(6))
        assertEquals("G", ColumnNumberToLabelConverter.convertToLabel(7))
        assertEquals("H", ColumnNumberToLabelConverter.convertToLabel(8))
        assertEquals("I", ColumnNumberToLabelConverter.convertToLabel(9))
        assertEquals("J", ColumnNumberToLabelConverter.convertToLabel(10))
        assertEquals("K", ColumnNumberToLabelConverter.convertToLabel(11))
        assertEquals("L", ColumnNumberToLabelConverter.convertToLabel(12))
        assertEquals("M", ColumnNumberToLabelConverter.convertToLabel(13))
        assertEquals("N", ColumnNumberToLabelConverter.convertToLabel(14))
        assertEquals("O", ColumnNumberToLabelConverter.convertToLabel(15))
        assertEquals("P", ColumnNumberToLabelConverter.convertToLabel(16))
        assertEquals("Q", ColumnNumberToLabelConverter.convertToLabel(17))
        assertEquals("R", ColumnNumberToLabelConverter.convertToLabel(18))
        assertEquals("S", ColumnNumberToLabelConverter.convertToLabel(19))
        assertEquals("T", ColumnNumberToLabelConverter.convertToLabel(20))
        assertEquals("U", ColumnNumberToLabelConverter.convertToLabel(21))
        assertEquals("V", ColumnNumberToLabelConverter.convertToLabel(22))
        assertEquals("W", ColumnNumberToLabelConverter.convertToLabel(23))
        assertEquals("X", ColumnNumberToLabelConverter.convertToLabel(24))
        assertEquals("Y", ColumnNumberToLabelConverter.convertToLabel(25))
        assertEquals("Z", ColumnNumberToLabelConverter.convertToLabel(26))
    }

    @Test
    fun shouldReturnAnDoubleAlphabetForEachNumberIndexInTheAlphabetFrom27to52() {
        assertEquals("AA", ColumnNumberToLabelConverter.convertToLabel(27))
        assertEquals("AB", ColumnNumberToLabelConverter.convertToLabel(28))
        assertEquals("AC", ColumnNumberToLabelConverter.convertToLabel(29))
        assertEquals("AD", ColumnNumberToLabelConverter.convertToLabel(30))
        assertEquals("AE", ColumnNumberToLabelConverter.convertToLabel(31))
        assertEquals("AF", ColumnNumberToLabelConverter.convertToLabel(32))
        assertEquals("AG", ColumnNumberToLabelConverter.convertToLabel(33))
        assertEquals("AH", ColumnNumberToLabelConverter.convertToLabel(34))
        assertEquals("AI", ColumnNumberToLabelConverter.convertToLabel(35))
        assertEquals("AJ", ColumnNumberToLabelConverter.convertToLabel(36))
        assertEquals("AK", ColumnNumberToLabelConverter.convertToLabel(37))
        assertEquals("AL", ColumnNumberToLabelConverter.convertToLabel(38))
        assertEquals("AM", ColumnNumberToLabelConverter.convertToLabel(39))
        assertEquals("AN", ColumnNumberToLabelConverter.convertToLabel(40))
        assertEquals("AO", ColumnNumberToLabelConverter.convertToLabel(41))
        assertEquals("AP", ColumnNumberToLabelConverter.convertToLabel(42))
        assertEquals("AQ", ColumnNumberToLabelConverter.convertToLabel(43))
        assertEquals("AR", ColumnNumberToLabelConverter.convertToLabel(44))
        assertEquals("AS", ColumnNumberToLabelConverter.convertToLabel(45))
        assertEquals("AT", ColumnNumberToLabelConverter.convertToLabel(46))
        assertEquals("AU", ColumnNumberToLabelConverter.convertToLabel(47))
        assertEquals("AV", ColumnNumberToLabelConverter.convertToLabel(48))
        assertEquals("AW", ColumnNumberToLabelConverter.convertToLabel(49))
        assertEquals("AX", ColumnNumberToLabelConverter.convertToLabel(50))
        assertEquals("AY", ColumnNumberToLabelConverter.convertToLabel(51))
        assertEquals("AZ", ColumnNumberToLabelConverter.convertToLabel(52))
    }
}