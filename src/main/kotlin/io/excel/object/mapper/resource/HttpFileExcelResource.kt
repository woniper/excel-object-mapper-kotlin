package io.excel.`object`.mapper.resource

import org.apache.poi.ss.usermodel.Workbook
import org.apache.poi.ss.usermodel.WorkbookFactory
import java.net.HttpURLConnection
import java.net.URL

class HttpFileExcelResource : ExcelResource {
    override fun getResource(resource: String): Workbook {
        val url = URL(resource)
        val connection = url.openConnection() as HttpURLConnection
        connection.requestMethod = "GET"
        connection.inputStream.use {
            return WorkbookFactory.create(it)
        }
    }
}
