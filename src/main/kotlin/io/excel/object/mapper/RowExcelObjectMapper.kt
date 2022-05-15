package io.excel.`object`.mapper

import io.excel.`object`.mapper.resource.ExcelResource
import org.apache.poi.ss.formula.functions.T
import org.apache.poi.ss.usermodel.Row
import kotlin.reflect.KClass

class RowExcelObjectMapper<T : AbstractRow>(private val excelResource: ExcelResource) : ExcelObjectMapper<T> {

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
            
            for (x in startRowIndex until sheet.physicalNumberOfRows) {
                rows.add(newInstanceRow(type, sheet.getRow(x)) as T)
            }
            
            return rows
        }        
    }
    
    private fun newInstanceRow(type: KClass<out Any>, row: Row): Any {
        return type.java.getConstructor(Row::class.java).newInstance(row)
    }
}
