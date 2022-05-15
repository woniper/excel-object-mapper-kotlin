package io.excel.`object`.mapper

import org.apache.poi.ss.usermodel.Cell
import org.apache.poi.ss.usermodel.Row

class RowCell<T>(row: Row, cellNumber: Int, val value: Any) {
    val cell: Cell = row.getCell(cellNumber)
}
