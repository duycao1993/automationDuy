package utilities;

import org.apache.poi.ss.usermodel.*;

public class ExcelWriter {

    public void createHeaderRow(Sheet sheet) {
        CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
        Font font = sheet.getWorkbook().createFont();
        font.setBold(true);
        font.setFontHeightInPoints((short) 16);
        cellStyle.setFont(font);

        Row row = sheet.createRow(0);
        Cell cellTestcase = row.createCell(1);

        cellTestcase.setCellStyle(cellStyle);
        cellTestcase.setCellValue("TestCase");

        Cell cellName = row.createCell(2);
        cellName.setCellStyle(cellStyle);
        cellName.setCellValue("Name");

        Cell cellEmail = row.createCell(3);
        cellEmail.setCellStyle(cellStyle);
        cellEmail.setCellValue("Email");

        Cell cellPass = row.createCell(4);
        cellPass.setCellStyle(cellStyle);
        cellPass.setCellValue("Pass");

        Cell cellPassConfirm = row.createCell(5);
        cellPassConfirm.setCellStyle(cellStyle);
        cellPassConfirm.setCellValue("PassConfirm");

        Cell cellTestResult = row.createCell(6);
        cellPassConfirm.setCellStyle(cellStyle);
        cellPassConfirm.setCellValue("TestResult");
    }


//    private Workbook getWorkbook(String excelFilePath)
//            throws IOException {
//        Workbook workbook = null;
//
//        if (excelFilePath.endsWith("xlsx")) {
//            workbook = new XSSFWorkbook();
//        } else if (excelFilePath.endsWith("xls")) {
//            workbook = new HSSFWorkbook();
//        } else {
//            throw new IllegalArgumentException("The specified file is not Excel file");
//        }
//
//        return workbook;
//    }
}
