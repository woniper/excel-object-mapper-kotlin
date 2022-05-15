package io.excel.`object`.mapper

import org.apache.poi.ss.usermodel.Row

class EmployeeRow(row: Row) : AbstractRow(row) {
    val name: String = row.getCell(0).stringCellValue
    val company: String = row.getCell(1).stringCellValue
    val age: Int = row.getCell(2).numericCellValue.toInt()
}
