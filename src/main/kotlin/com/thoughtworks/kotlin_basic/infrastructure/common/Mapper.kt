package com.thoughtworks.kotlin_basic.infrastructure.common

interface Mapper<S, D> {
    fun mapTo(dto: List<S>): List<D>
    fun mapTo(dto: S): D
}