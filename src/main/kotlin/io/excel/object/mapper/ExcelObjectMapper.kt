package io.excel.`object`.mapper

import kotlin.reflect.KClass

interface ExcelObjectMapper<T> {
    fun parse(resource: String, type: KClass<out Any>): List<T>
    
    fun parse(resource: String, startRowIndex: Int, type: KClass<out Any>): List<T>
    
    fun parse(resource: String, sheetIndex: Int, startRowIndex: Int, type: KClass<out Any>): List<T>
}
