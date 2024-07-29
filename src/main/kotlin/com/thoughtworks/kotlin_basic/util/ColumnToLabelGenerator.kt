package com.thoughtworks.kotlin_basic.util

class ColumnToLabelGenerator {
    companion object {
        fun generateColumnNames(start: Int, numberOfResults: Int):List<String> {
            when{
                start + numberOfResults >= 79 -> throw IllegalArgumentException("Cannot produce results for more than 78 columns")
            }

            val result = ArrayList<String>()
            for(i in start..<(start + numberOfResults)){
                result.add(ColumnNumberToLabelConverter.convertToLabel(i))
            }
            return result
        }
    }

}
