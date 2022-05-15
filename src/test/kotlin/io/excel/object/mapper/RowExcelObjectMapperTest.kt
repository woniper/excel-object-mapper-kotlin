package io.excel.`object`.mapper

import io.excel.`object`.mapper.resource.ResourcesExcelResource
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class RowExcelObjectMapperTest {

    @Test
    internal fun `company excel row를 EmployeeRow로 매핑`() {
        val mapper = RowExcelObjectMapper<CompanyRow>(ResourcesExcelResource())

        val companies = mapper.parse("company.xlsx", 1, CompanyRow::class)
        
        assertThat(companies).hasSize(3);
        assertThat(companies.get(0).company).isEqualTo("카페");
        assertThat(companies.get(0).address).isEqualTo("서울");
        assertThat(companies.get(0).employeeCount).isEqualTo(10);
    }
    
    @Test
    internal fun `employee excel row를 employee row로 매핑`() {
        // given
        val parser = RowExcelObjectMapper<EmployeeRow>(ResourcesExcelResource())

        // when
        val employeeRows = parser.parse("employee.xlsx", 1, EmployeeRow::class)

        // then
        assertThat(employeeRows).hasSize(3)
        assertThat(employeeRows.get(0).name).isEqualTo("이경원")
        assertThat(employeeRows.get(0).company).isEqualTo("카페")
        assertThat(employeeRows.get(0).age).isEqualTo(20)
    }
}
