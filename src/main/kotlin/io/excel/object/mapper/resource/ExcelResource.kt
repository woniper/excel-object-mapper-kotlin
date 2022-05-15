package io.excel.`object`.mapper.resource

import org.apache.poi.ss.usermodel.Workbook

interface ExcelResource {
    fun getResource(resource: String) : Workbook
}
