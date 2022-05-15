package io.excel.`object`.mapper.resource

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ExcelResourceTest {

    @Test
    internal fun resources에_있는_excel_파일을_Workbook으로_반환() {
        val resource = ResourcesExcelResource()
        val workbook = resource.getResource("company.xlsx")
        
        assertThat(workbook.numberOfSheets).isEqualTo(1)
        assertThat(workbook.getSheetAt(0).physicalNumberOfRows).isEqualTo(4)
    }
}
