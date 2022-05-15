package io.excel.`object`.mapper

import org.apache.poi.ss.usermodel.Row

class CompanyRow(row: Row) : AbstractRow(row) {
    val company: String = row.getCell(0).stringCellValue
    val address: String = row.getCell(1).stringCellValue 
    val employeeCount: Int = row.getCell(2).numericCellValue.toInt()
}    
