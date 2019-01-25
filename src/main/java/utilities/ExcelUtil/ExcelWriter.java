package utilities.ExcelUtil;

import Environement.Configuration;
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

public class ExcelWriter {

    private String excelFilePath;

    public ExcelWriter(String excelFilePath) {
        String[] element = excelFilePath.split("/");
        String fileName = element[element.length-1];
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
            wb = getWorkbook(fIPS, excelFilePath); //If there is already data in a workbook
            worksheet = wb.getSheet("Data");

            Row row;
            Cell cell;
            for(Map.Entry<Integer, List<Object>> dataValue : data.entrySet()) {
                row = worksheet.getRow(dataValue.getKey());
                //Actual result
                row.getCell(3).setCellType(CellType.STRING);
                row.getCell(3).setCellValue(String.valueOf(dataValue.getValue().get(1)));
                //Status
                row.getCell(5).setCellType(CellType.STRING);
                row.getCell(5).setCellValue(String.valueOf(dataValue.getValue().get(0)));
                //Execute time
                row.getCell(6).setCellType(CellType.NUMERIC);
                row.getCell(6).setCellValue(Double.valueOf(String.valueOf(dataValue.getValue().get(2))));
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
     * @param excelFilePath
     * @return the correct workbook
     * @throws IOException
     */
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
