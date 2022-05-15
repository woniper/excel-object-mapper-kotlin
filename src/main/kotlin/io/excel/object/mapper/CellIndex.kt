package io.excel.`object`.mapper

@MustBeDocumented
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FIELD)
annotation class CellIndex(val index: Int, val message: String = "")
