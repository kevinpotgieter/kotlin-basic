package com.thoughtworks.kotlin_basic.util


class ColumnNumberToLabelConverter {

    companion object {
        private const val ASCII_ALPHABET_STARTING_INDEX: Int = 'A'.code
        private const val NUMBER_OF_LETTER_IN_ALPHABET = 26

        fun convertToLabel(columnNumber: Int): String {
            when {
                columnNumber <= 0 -> throw IllegalArgumentException("Column number must be positive and greater than zero")
            }
            val columnNumberAtZeroIndex: Int = zeroIndexColumnNumber(columnNumber)
            val multiplesOfAlphabetLength: Int = (columnNumberAtZeroIndex / NUMBER_OF_LETTER_IN_ALPHABET)

            return "A".repeat(multiplesOfAlphabetLength) +
                    Char(ASCII_ALPHABET_STARTING_INDEX + (columnNumberAtZeroIndex - (multiplesOfAlphabetLength * NUMBER_OF_LETTER_IN_ALPHABET))).toString()

        }

        private fun zeroIndexColumnNumber(columnNumber: Int) = (columnNumber - 1)

    }
}
