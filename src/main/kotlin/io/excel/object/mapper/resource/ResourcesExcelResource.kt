package io.excel.`object`.mapper.resource

import org.apache.poi.ss.usermodel.Workbook
import org.apache.poi.ss.usermodel.WorkbookFactory
import java.io.File

class ResourcesExcelResource : ExcelResource {
    override fun getResource(resource: String): Workbook {
        return WorkbookFactory.create(findFile(resource))
    }
    
    private fun findFile(resource: String) : File {
        return File(javaClass.classLoader.getResource(resource).file)
    }
}
