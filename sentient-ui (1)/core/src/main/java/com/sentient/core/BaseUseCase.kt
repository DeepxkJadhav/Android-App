package com.sentient.core

interface BaseUseCase<in P, out R> {
    fun execute(params: P): R
}