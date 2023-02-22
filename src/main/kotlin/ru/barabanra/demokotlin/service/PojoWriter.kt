package ru.barabanra.demokotlin.service

interface PojoWriter {
    fun writeAsString(obj: Any): String?
}