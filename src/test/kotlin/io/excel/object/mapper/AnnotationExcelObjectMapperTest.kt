package io.excel.`object`.mapper

import io.excel.`object`.mapper.resource.ResourcesExcelResource
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test


internal class AnnotationExcelObjectMapperTest {

    @Test
    fun employee_excel_row를_AnnotationEmployeeRow로_매핑() {
        // given
        val excelObjectMapper: ExcelObjectMapper<AnnotationEmployeeRow> =
            AnnotationExcelObjectMapper(ResourcesExcelResource())

        // when
        val employeeRows = excelObjectMapper.parse(
            "employee.xlsx", 1,
            AnnotationEmployeeRow::class
        )

        // then
        assertThat(employeeRows).hasSize(3)
        assertThat(employeeRows[0].name).isEqualTo("이경원")
        assertThat(employeeRows[0].company).isEqualTo("카페")
        assertThat(employeeRows[0].age).isEqualTo(20)
        assertThat(employeeRows[0].shortAge).isEqualTo(20.toShort())
        assertThat(employeeRows[0].doubleAge).isEqualTo(20.0)
        assertThat(employeeRows[0].floatAge).isEqualTo(20.0f)
        assertThat(employeeRows[0].longAge).isEqualTo(20L)
    }

    class AnnotationEmployeeRow {
        @CellIndex(index = 0)
        val name: String? = null

        @CellIndex(index = 1)
        val company: String? = null

        @CellIndex(index = 2)
        val age = 0

        @CellIndex(index = 2)
        val shortAge: Short = 0

        @CellIndex(index = 2)
        val doubleAge = 0.0

        @CellIndex(index = 2)
        val floatAge = 0f

        @CellIndex(index = 2)
        val longAge: Long = 0
    }
}
