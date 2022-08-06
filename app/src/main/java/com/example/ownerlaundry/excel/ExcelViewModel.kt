package com.example.ownerlaundry.excel

import android.os.Build
import android.os.Environment
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.ownerlaundry.*
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

        var rowWasher = 0
        var rowDryer = 0
//        var countMachine = 0

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

        val font5 = WritableFont(WritableFont.ARIAL, 11, WritableFont.BOLD)
        val format5 = WritableCellFormat(font5)
//        format3.setBorder(Border.ALL, BorderLineStyle.THIN)
        format5.alignment = Alignment.LEFT

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
            sheet.setColumnView(7,15)

            sheet.mergeCells(0,0,0,1)
            sheet.mergeCells(1,0,1,1)
            sheet.mergeCells(2,0,2,1)
            sheet.mergeCells(3,0,3,1)
            sheet.mergeCells(4,0,4,1)
            sheet.mergeCells(5,0,5,1)
            sheet.mergeCells(6,0,6,1)
            sheet.mergeCells(7,0,7,1)

            // column and row title
            sheet.addCell(Label(0, 0, "NO", format1))
            sheet.addCell(Label(1, 0, "JAM",format1))
            sheet.addCell(Label(2, 0, "NO MESIN",format1))
            sheet.addCell(Label(3, 0, "WASHER",format1))
            sheet.addCell(Label(4, 0, "JAM",format1))
            sheet.addCell(Label(5, 0, "NO MESIN",format1))
            sheet.addCell(Label(6, 0, "DRYER",format1))
            sheet.addCell(Label(7, 0, "TOTAL W+D",format1))

            for (i in EXCEL_VALUE_DEBUG_ALL.indices) {
                if (EXCEL_VALUE_DEBUG_ALL[i].TypeMenu == false) {
                    if (ROW_WAHSER_EMPTY.isNullOrEmpty()) {
                        sheet.addCell(Label(1, rowWasher + 2, EXCEL_VALUE_DEBUG_ALL[i].time, format2))
                        sheet.addCell(Label(2, rowWasher + 2, EXCEL_VALUE_DEBUG_ALL[i].NumberMachine.toString(), format2))
                        sheet.addCell(Label(3, rowWasher + 2, "✔", format2))
                    }
                    else {
                        for ((index, value) in ROW_WAHSER_EMPTY.withIndex()) {
                            if (rowWasher == value) {
//                                Log.d("debug", "Row Washer add : ${rowWasher} -- $value")
                                rowWasher++
                                break
                            }
                        }
                        sheet.addCell(Label(1, rowWasher + 2, EXCEL_VALUE_DEBUG_ALL[i].time, format2))
                        sheet.addCell(Label(2, rowWasher + 2, EXCEL_VALUE_DEBUG_ALL[i].NumberMachine.toString(), format2))
                        sheet.addCell(Label(3, rowWasher + 2, "✔", format2))
                    }
                    rowWasher++
                }
            }
            for (i in EXCEL_VALUE_DEBUG_ALL.indices) {
                if (EXCEL_VALUE_DEBUG_ALL[i].TypeMenu == true){
                    if (ROW_DRYER_EMPTY.isNullOrEmpty()){
                        sheet.addCell(Label(4, rowDryer + 2, EXCEL_VALUE_DEBUG_ALL[i].time, format2))
                        sheet.addCell(Label(5, rowDryer + 2, EXCEL_VALUE_DEBUG_ALL[i].NumberMachine.toString(), format2))
                        sheet.addCell(Label(6, rowDryer + 2, "✔",format2))
                    }
                    else{
                        for((index, value) in ROW_DRYER_EMPTY.withIndex()){
                            if (rowDryer == value){
                                rowDryer++
                                break
                            }
                        }
//                        Log.d("debug", "Row Dryer : ${rowDryer}")
                        sheet.addCell(Label(4, rowDryer + 2, EXCEL_VALUE_DEBUG_ALL[i].time, format2))
                        sheet.addCell(Label(5, rowDryer + 2, EXCEL_VALUE_DEBUG_ALL[i].NumberMachine.toString(), format2))
                        sheet.addCell(Label(6, rowDryer + 2, "✔",format2))
                    }
                    rowDryer++
                }
            }

            if(rowWasher > rowDryer){
                COUNT_MACHINE = 0
                Log.d("debug", "one")
                for (i in 1..rowWasher){
                    sheet.addCell(Label(0, i + 1, i.toString(), format2))
                    for((index, value) in ROW_MACHINE_PLUS_ONE.withIndex()){
                        Log.d("debug", "Row Washer $i -- value row -- $value count -- $COUNT_MACHINE")
                        Log.d("debug", "Index plus 1 $index")
                        if(i-1 == value){
                            Log.d("debug", "${i - 1} -- ${value}")
                            COUNT_MACHINE-=1
                            break
                        }
                    }
                    COUNT_MACHINE+=2
                    sheet.addCell(Label(7, i + 1, "$COUNT_MACHINE", format1))
                }

                var celllist : Int = rowWasher + 2

                sheet.mergeCells(0,celllist,2,celllist)
                sheet.mergeCells(4,celllist,5,celllist)

                sheet.mergeCells(0,celllist + 2,1,celllist + 2)
                sheet.mergeCells(0,celllist + 3,1,celllist + 3)
                sheet.mergeCells(0,celllist + 4,1,celllist + 4)
                sheet.mergeCells(0,celllist + 5,1,celllist + 5)

                sheet.addCell(Label(0, celllist, "Total Washer",format3))
                sheet.addCell(Label(4, celllist, "Total Dryer",format3))

                sheet.addCell(Label(3, celllist, "${EXCEL_VALUE_DEBUG_WASHER.size}",format3))
                sheet.addCell(Label(6, celllist, "${EXCEL_VALUE_DEBUG_DRYER.size}",format3))

                sheet.addCell(Label(0, celllist + 2, "Total Washer Kecil",format5))
                sheet.addCell(Label(0, celllist + 3, "Total Washer Besar",format5))
                sheet.addCell(Label(0, celllist + 4, "Total Dryer Kecil",format5))
                sheet.addCell(Label(0, celllist + 5, "Total Dryer Besar",format5))

                sheet.addCell(Label(2, celllist + 2, ": $WASHER_COUNT_GIANT",format5))
                sheet.addCell(Label(2, celllist + 3, ": $WASHER_COUNT_TITAN",format5))
                sheet.addCell(Label(2, celllist + 4, ": $DRYER_COUNT_GIANT",format5))
                sheet.addCell(Label(2, celllist + 5, ": $DRYER_COUNT_TITAN",format5))
            }
            else{
                COUNT_MACHINE = 0
                Log.d("debug", "two")
                for (i in 1..rowDryer){
                    sheet.addCell(Label(0, i + 1, i.toString(), format2))
//                    Log.d("debug", "$i")
                    for((index, value) in ROW_MACHINE_PLUS_ONE.withIndex()){
                        Log.d("debug", "Row Dryer $i -- value row -- $value count -- $COUNT_MACHINE")
                        Log.d("debug", "Index plus 1 $index")
                        if(i-1 == value){
                            Log.d("debug", "${i - 1} -- ${value}")
                            COUNT_MACHINE-=1
                            break
                        }
                    }
                    COUNT_MACHINE+=2
                    sheet.addCell(Label(7, i + 1, "$COUNT_MACHINE", format1))
                }

                var celllist : Int = rowDryer + 2

                sheet.mergeCells(0,celllist,2,celllist)
                sheet.mergeCells(4,celllist,5,celllist)

                sheet.mergeCells(0,celllist + 2,1,celllist + 2)
                sheet.mergeCells(0,celllist + 3,1,celllist + 3)
                sheet.mergeCells(0,celllist + 4,1,celllist + 4)
                sheet.mergeCells(0,celllist + 5,1,celllist + 5)

                sheet.addCell(Label(0, celllist, "Total Washer",format3))
                sheet.addCell(Label(4, celllist, "Total Dryer",format3))

                sheet.addCell(Label(3, celllist, "${EXCEL_VALUE_DEBUG_WASHER.size}",format3))
                sheet.addCell(Label(6, celllist, "${EXCEL_VALUE_DEBUG_DRYER.size}",format3))

                sheet.addCell(Label(0, celllist + 2, "Total Washer Kecil",format5))
                sheet.addCell(Label(0, celllist + 3, "Total Washer Besar",format5))
                sheet.addCell(Label(0, celllist + 4, "Total Dryer Kecil",format5))
                sheet.addCell(Label(0, celllist + 5, "Total Dryer Besar",format5))

                sheet.addCell(Label(2, celllist + 2, ": $WASHER_COUNT_GIANT",format5))
                sheet.addCell(Label(2, celllist + 3, ": $WASHER_COUNT_TITAN",format5))
                sheet.addCell(Label(2, celllist + 4, ": $DRYER_COUNT_GIANT",format5))
                sheet.addCell(Label(2, celllist + 5, ": $DRYER_COUNT_TITAN",format5))
            }
            
            WASHER_COUNT_GIANT = 0
            WASHER_COUNT_TITAN = 0
            DRYER_COUNT_GIANT = 0
            DRYER_COUNT_TITAN = 0

            ROW_MACHINE_PLUS_ONE.clear()
            ROW_MACHINE_PLUS_ONE.clear()

            stateExcel = 1
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
    }
}