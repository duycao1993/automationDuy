package utilities;

import Environement.Configuration;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

public class ExcelReader {
    private Logger log = Logger.getLogger(ExcelReader.class.toString());

//    public List<TestData> readDataExcel(String excelFilePath) {
//        List<TestData> listUsers = new ArrayList<>();
//        try{
//            log.info("Reading excel file!");
//            FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
//
//            Workbook workbook = getWorkbook(inputStream, excelFilePath);
//            Sheet sheet = workbook.getSheet("Data");
//            Iterator<Row> rowIterator = sheet.iterator();
//
//            //Next the header
//            rowIterator.next();
//
//            while (rowIterator.hasNext()) {
//                Row row = rowIterator.next();
//
//                // Now let's iterate over the columns of the current row
//                Iterator<Cell> cellIterator = row.cellIterator();
//                int columnIndex = 0;
//
//                TestData data = new TestData();
//
//                while (cellIterator.hasNext()) {
//                    Cell cell = cellIterator.next();
//
//                    if(cell.getColumnIndex() == 0){
//                        int testIndex = (int) cell.getNumericCellValue();
//                      data.setCaseIndex(testIndex);
//                    } else if (cell.getColumnIndex() == 1){
//                        data.setUserName(cell.getStringCellValue());
//                    } else if(cell.getColumnIndex() == 2){
//                        data.setPassWord(cell.getStringCellValue());
//                    }
//                }
//
//                listUsers.add(data);
//            }
//
//            workbook.close();
//            inputStream.close();
//        } catch (Exception e){
//            log.throwing(ExcelReader.class.toString(), "readDataExcel", e );
//        }
//        log.info("Reading excel file successful");
//        return listUsers;
//    }

    //For Test parameters
    public Collection<Object> readDataExcel(String excelFilePath) {
        List<Object> listUsers = new ArrayList<>();
        try{
            log.info("Reading excel file!");
            FileInputStream inputStream = new FileInputStream(new File(excelFilePath));

            Workbook workbook = getWorkbook(inputStream, excelFilePath);

            //Read environnment sheet
            Sheet sheet = workbook.getSheet("Environnement");
            Iterator<Row> rowIterator = sheet.iterator();

            //Next the header
            rowIterator.next();

            Row row = rowIterator.next();
            Iterator<Cell> cellIterator = row.cellIterator();
            while (cellIterator.hasNext()){
                Cell cell = cellIterator.next();
                if(cell.getColumnIndex() == 3){
                    Configuration.getInstance().setBrowser(cell.getStringCellValue());
                } else  if (cell.getColumnIndex() == 4){
                    Configuration.getInstance().setWebSite(cell.getStringCellValue());
                } else if (cell.getColumnIndex() == 5){
                    int timeOut = (int) cell.getNumericCellValue();
                    Configuration.getInstance().setTimeOut(timeOut);
                }
            }

            sheet = workbook.getSheet("Data");
            rowIterator = sheet.iterator();

            //Next the header
            rowIterator.next();

            while (rowIterator.hasNext()) {
                row = rowIterator.next();

                // Now let's iterate over the columns of the current row
                cellIterator = row.cellIterator();

                List<Object> user = new ArrayList<>();

                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();

                    if(cell.getColumnIndex() == 0){
                        int rowIndex = (int) cell.getNumericCellValue();
                        user.add(rowIndex);
                    } else if (cell.getColumnIndex() == 1){
                        user.add(cell.getStringCellValue());
                    } else if(cell.getColumnIndex() == 2){
                        user.add(cell.getStringCellValue());
                    }
                }

                listUsers.add(user.toArray());
            }

            workbook.close();
            inputStream.close();
        } catch (Exception e){
            log.throwing(ExcelReader.class.toString(), "readDataExcel", e );
        }
        log.info("Reading excel file successful");
        return listUsers;
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
