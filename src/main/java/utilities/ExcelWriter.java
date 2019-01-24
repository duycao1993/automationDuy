package utilities;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExcelWriter {

    private String excelFilePath;

    public ExcelWriter(String excelFilePath) {
        this.excelFilePath = excelFilePath;
    }

    public void writeReport(HashMap<Integer, List<String>> data) {
        try {
            FileInputStream fIPS= new FileInputStream(excelFilePath); //Read the spreadsheet that needs to be updated
            Workbook wb;
            Sheet worksheet;
            wb = getWorkbook(fIPS, excelFilePath); //If there is already data in a workbook
            worksheet = wb.getSheet("Data");
            //Access the worksheet, so that we can update / modify it
            Row row;
            Cell cell;
            for(Map.Entry<Integer, List<String>> dataValue : data.entrySet()) {
                row = worksheet.getRow(dataValue.getKey());
                row.getCell(3).setCellValue(dataValue.getValue().get(0));
                row.getCell(5).setCellValue(dataValue.getValue().get(1));
                row.getCell(6).setCellValue(dataValue.getValue().get(2));
            }
            fIPS.close(); //Close the InputStream
            FileOutputStream output_file =new FileOutputStream(excelFilePath);//Open FileOutputStream to write updates
            wb.write(output_file); //write changes
            output_file.close();  //close the stream

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Workbook getWorkbook(FileInputStream inputStream, String excelFilePath) throws IOException {
        Workbook workbook;

        if (excelFilePath.endsWith("xlsx")) {
            workbook = new XSSFWorkbook(inputStream);
        } else if (excelFilePath.endsWith("xls")) {
            workbook = new HSSFWorkbook(inputStream);
        } else {
            throw new IllegalArgumentException("The specified file is not Excel file");
        }

        return workbook;
    }
}
