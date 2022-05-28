package com.example.ownerlaundry.excel

import android.os.Build
import android.os.Environment
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.ownerlaundry.EXCEL_VALUE
import jxl.Workbook
import jxl.WorkbookSettings
import jxl.format.Alignment
import jxl.format.Border
import jxl.format.BorderLineStyle
import jxl.format.VerticalAlignment
import jxl.write.Label
import jxl.write.WritableCellFormat
import jxl.write.WritableFont
import jxl.write.WritableWorkbook
import java.io.File
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class ExcelViewModel: ViewModel() {
    var stateExcel: Int by mutableStateOf(0)

    var workbook: WritableWorkbook? = null

    @RequiresApi(Build.VERSION_CODES.O)
    fun createExcelSheet(date: String) {
        var dateTitle = date
        var csvFile = ""
        var a = 0

        val current = LocalDateTime.now()

        val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
        val formatted = current.format(formatter)

        if(dateTitle == ""){
            csvFile = "Report ${formatted}.xls"
        }
        else{
            csvFile = "Report ${date}.xls"
        }

        val futureStudioIconFile = File(
            Environment
                .getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
                .toString() + "/" + csvFile
        )
        val wbSettings = WorkbookSettings()
        wbSettings.locale = Locale("en", "EN")
        try {
            workbook = Workbook.createWorkbook(futureStudioIconFile, wbSettings)
            createFirstSheet()
            workbook?.write()
            workbook?.close()
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
    }

    fun createFirstSheet() {

        stateExcel = 0

        val font1 = WritableFont(WritableFont.ARIAL, 11, WritableFont.BOLD)
        val format1 = WritableCellFormat(font1)
        format1.alignment = Alignment.CENTRE
        format1.verticalAlignment = VerticalAlignment.CENTRE
        format1.setBorder(Border.ALL, BorderLineStyle.THIN)

        val font2 = WritableFont(WritableFont.ARIAL, 11)
        val format2 = WritableCellFormat(font2)
        format2.alignment = Alignment.CENTRE
        format2.setBorder(Border.ALL, BorderLineStyle.THIN)

        val font3 = WritableFont(WritableFont.ARIAL, 11, WritableFont.BOLD)
        val format3 = WritableCellFormat(font3)
        format3.setBorder(Border.ALL, BorderLineStyle.THIN)
        format3.alignment = Alignment.CENTRE

        val format4 = WritableCellFormat()
        format4.setBorder(Border.ALL, BorderLineStyle.THIN)

        try {
            val sheet = workbook!!.createSheet("sheet1", 0)
            var sumPrice = 0

            sheet.setColumnView(0,4)
            sheet.setColumnView(1,15)
            sheet.setColumnView(2,15)
            sheet.setColumnView(3,15)
            sheet.setColumnView(4,15)
            sheet.setColumnView(5,15)
            sheet.setColumnView(6,15)

            sheet.mergeCells(0,0,0,1)
            sheet.mergeCells(1,0,1,1)
            sheet.mergeCells(2,0,2,1)
            sheet.mergeCells(3,0,3,1)
            sheet.mergeCells(4,0,4,1)
            sheet.mergeCells(5,0,5,1)
            sheet.mergeCells(6,0,6,1)

            // column and row title
            sheet.addCell(Label(0, 0, "No.", format1))
            sheet.addCell(Label(1, 0, "Type",format1))
            sheet.addCell(Label(2, 0, "Date",format1))
            sheet.addCell(Label(3, 0, "Class",format1))
            sheet.addCell(Label(4, 0, "Payment",format1))
            sheet.addCell(Label(5, 0, "Price",format1))
            for (i in EXCEL_VALUE.indices) {
                sheet.addCell(Label(0, i + 2, (i+1).toString(), format2))
                sheet.addCell(Label(1, i + 2, EXCEL_VALUE[i].transactionTypeMenu, format4))
                sheet.addCell(Label(2, i + 2, EXCEL_VALUE[i].transactionDate, format2))
                sheet.addCell(Label(3, i + 2, EXCEL_VALUE[i].transactionClassMachine,format2))
                sheet.addCell(Label(4, i + 2, EXCEL_VALUE[i].transactionTypePayment,format2))
                sheet.addCell(Label(5, i + 2, EXCEL_VALUE[i].transactionPrice,format2))
                sumPrice += EXCEL_VALUE[i].transactionPrice!!.toInt()
            }
            var celllist : Int = EXCEL_VALUE.size + 2

            sheet.mergeCells(0,celllist,4,celllist)
            sheet.addCell(Label(0, celllist, "Total",format3))
            sheet.addCell(Label(5, celllist, "$sumPrice",format3))
            stateExcel = 1
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
    }
}