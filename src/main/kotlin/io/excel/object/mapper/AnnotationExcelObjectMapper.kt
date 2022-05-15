package io.excel.`object`.mapper

import io.excel.`object`.mapper.resource.ResourcesExcelResource
import org.apache.poi.ss.usermodel.CellType
import org.apache.poi.ss.usermodel.Row
import java.lang.IllegalArgumentException
import java.lang.reflect.Field
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type
import java.util.*
import java.util.regex.Pattern
import kotlin.reflect.KClass
import kotlin.reflect.full.findAnnotation

class AnnotationExcelObjectMapper<T> (val excelResource: ResourcesExcelResource) : ExcelObjectMapper<T> {

    override fun parse(resource: String, type: KClass<out Any>): List<T> {
        return this.parse(resource, 0, 0, type)
    }

    override fun parse(resource: String, startRowIndex: Int, type: KClass<out Any>): List<T> {
        return this.parse(resource, 0, startRowIndex, type)
    }

    override fun parse(resource: String, sheetIndex: Int, startRowIndex: Int, type: KClass<out Any>): List<T> {
        this.excelResource.getResource(resource).use {
            val rows = ArrayList<T>()
            val sheet = it.getSheetAt(sheetIndex)
            
            for (i in startRowIndex until sheet.physicalNumberOfRows) {
                val instance = type.java.getConstructor().newInstance()
                val row = sheet.getRow(i)
                
                Arrays.stream(instance.javaClass.getDeclaredFields())
                    .peek { it.setAccessible(true) }
                    .forEach { setFieldValue(row, instance, it) }
                    
                rows.add(instance as T)
            }
            
            return rows
        }
    }

    private fun setFieldValue(row: Row, instance: Any, field: Field) {
        if (notAvailableAccessField(field)) {
            return
        }

        val cellIndex = field.getDeclaredAnnotation(CellIndex::class.java)
        val cell = row.getCell(cellIndex.index)

        if (Objects.isNull(cell)) {
            throw IllegalArgumentException(cellIndex.message)
        }
        
        cell.cellType = CellType.STRING

        if (field.type.equals(RowCell::class.java)) {
            val value = getPrimitiveTypeValue(cell.stringCellValue, getRowCellGenericType(field))
            val rowCell = RowCell<T>(row, cellIndex.index, value)
            setValue(field, instance, rowCell)
        } else {
            val value = getPrimitiveTypeValue(cell.stringCellValue, field.genericType)
            setValue(field, instance, value)
        }
    }

    private fun getRowCellGenericType(field: Field): Type {
        return (field.genericType as ParameterizedType).getActualTypeArguments().get(0)
    }

    private fun setValue(field: Field, instance: Any, value: Any) {
        try {
            field.set(instance, value)
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
        }
    }
    
    private fun getPrimitiveTypeValue(value: String, fieldType: Type): Any {
        val typeName = fieldType.typeName

        when (typeName) {
            "int", "java.lang.Integer" -> return value.toDouble().toInt()
            "short", "java.lang.Short" -> return value.toDouble().toInt().toShort()
            "byte", "java.lang.Byte" -> return value.toByte()
            "double", "java.lang.Double" -> return value.toDouble()
            "float", "java.lang.Float" -> return value.toFloat()
            "long", "java.lang.Long" -> return value.toDouble().toLong()
            "boolean", "java.lang.Boolean" -> return value.toBoolean()
        }
        
        return value
    }
    
    private fun notAvailableAccessField(field: Field): Boolean {
        return !field.isAnnotationPresent(CellIndex::class.java)
    }
}
