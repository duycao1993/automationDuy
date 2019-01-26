package Utilities.ExcelUtil;

import Environement.Configuration;
import Verification.ENUM.StatusEnum;
import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class ExcelWriter {

    private String excelFilePath;
    private String fileExtension;

    public ExcelWriter(String excelFilePath) {
        String[] element = excelFilePath.split("/");
        String fileName = element[element.length-1];
        this.fileExtension = fileName.split(Pattern.quote("."))[1];

        //Copy file to report folder
        File sourceFile = new File(excelFilePath);
        File destFile = new File(Configuration.getInstance().getReportPath() + fileName);
        try{
            if(destFile.exists()){
                FileUtils.forceDelete(destFile);
            }
            FileUtils.copyFile(sourceFile, destFile);
            this.excelFilePath = Configuration.getInstance().getReportPath() + fileName;
        } catch (Exception e){
            e.printStackTrace();
        }


    }

    /**
     * Put all data into a HashMap
     * @param testIndex
     * @param result
     * @param executeTime
     * @return Hashmap data
     */
    private HashMap<Integer, List<Object>> putDataIntoMap(int testIndex, List<Object> result, double executeTime){
        HashMap<Integer, List<Object>> map = new HashMap<>();
        result.add(executeTime);
        map.put(testIndex, result);
        return map;
    }

    /**
     * Write the result into the report file
     * @param testIndex
     * @param result
     * @param executeTime
     */
    public void writeReport(int testIndex, List result, double executeTime) {
        try {
            //Prepare the input data
            Map<Integer, List<Object>> data = putDataIntoMap(testIndex, result, executeTime);

            FileInputStream fIPS= new FileInputStream(excelFilePath); //Read the spreadsheet that needs to be updated
            Workbook wb;
            Sheet worksheet;
            wb = getWorkbook(fIPS); //If there is already data in a workbook
            worksheet = wb.getSheet("Data");
            CellStyle passedStyle = wb.createCellStyle();
            passedStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            passedStyle.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());

            CellStyle failedStyle = wb.createCellStyle();
            failedStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            failedStyle.setFillForegroundColor(IndexedColors.RED.getIndex());

            Row row = null;
            for(Map.Entry<Integer, List<Object>> dataValue : data.entrySet()) {
                row = worksheet.getRow(dataValue.getKey());
                //Actual result
                row.getCell(3).setCellType(CellType.STRING);
                row.getCell(3).setCellValue(String.valueOf(dataValue.getValue().get(1)));
                //Status
                String status = String.valueOf(dataValue.getValue().get(0));
                if(status.equals(StatusEnum.Passed.toString())){
                    row.getCell(5).setCellStyle(passedStyle);
                } else {
                    row.getCell(5).setCellStyle(failedStyle);
                }
                row.getCell(5).setCellType(CellType.STRING);
                row.getCell(5).setCellValue(status);
                //Execute time
                row.getCell(6).setCellType(CellType.NUMERIC);
                row.getCell(6).setCellValue(Double.valueOf(String.valueOf(dataValue.getValue().get(2))));
            }
            //Create hyperlink to image report
            if(row != null){
                Font hyperlinkFont = wb.createFont();
                hyperlinkFont.setColor(IndexedColors.BLUE.getIndex());
                hyperlinkFont.setItalic(true);
                hyperlinkFont.setUnderline(Font.U_SINGLE);

                CellStyle hyperStyle = wb.createCellStyle();
                hyperStyle.setFont(hyperlinkFont);

                row.createCell(7).setCellType(CellType.FORMULA);
                row.getCell(7).setCellStyle(hyperStyle);
                row.getCell(7).setCellFormula("HYPERLINK(\""+  Configuration.getInstance().getReportPath() + "screenshots/" + testIndex + ".jpg" +"\", \"Go to image\")");
            }
            fIPS.close(); //Close the InputStream
            FileOutputStream output_file = new FileOutputStream(excelFilePath);//Open FileOutputStream to write updates
            wb.write(output_file); //write changes
            output_file.close();  //close the stream

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Get the workbook
     * @param inputStream
     * @return the correct workbook
     * @throws IOException
     */
    private Workbook getWorkbook(FileInputStream inputStream) throws IOException {
        Workbook workbook;
        if (fileExtension.equalsIgnoreCase("xlsx")) {
            workbook = new XSSFWorkbook(inputStream);
        } else if (fileExtension.equalsIgnoreCase("xls")) {
            workbook = new HSSFWorkbook(inputStream);
        } else {
            throw new IllegalArgumentException("The specified file is not Excel file");
        }

        return workbook;
    }
}
