package utilities;

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
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

public class ExcelReader {
    private Logger log = Logger.getLogger(ExcelReader.class.toString());

    private Object getCellValue(Cell cell) {
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();

            case BOOLEAN:
                return cell.getBooleanCellValue();

            case NUMERIC:
                return cell.getNumericCellValue();
            default:
                break;
        }

        return null;
    }

    public List<TestData> readDataExcel(String excelFilePath) {
        List<TestData> listUsers = new ArrayList<>();
        try{
            log.info("Reading excel file!");
            FileInputStream inputStream = new FileInputStream(new File(excelFilePath));

            Workbook workbook = getWorkbook(inputStream, excelFilePath);
            Sheet firstSheet = workbook.getSheet("Data");
            Iterator<Row> iterator = firstSheet.iterator();

            while (iterator.hasNext()) {
                Row nextRow = iterator.next();

                Iterator<Cell> cellIterator = nextRow.cellIterator();
                TestData user = new TestData();

                boolean isHeader = true;

                while (cellIterator.hasNext()) {
                    Cell nextCell = cellIterator.next();

                    if(isHeader){
                        isHeader = false;
                        continue;
                    }

                    int columnIndex = nextCell.getColumnIndex();

                    switch (columnIndex) {
                        case 0:
                            user.setCaseIndex((Integer) getCellValue(nextCell));
                            break;
                        case 1:
                            user.setUserName((String) getCellValue(nextCell));
                            break;
                        case 2:
                            user.setPassWord((String) getCellValue(nextCell));
                            break;
                    }

                }
                listUsers.add(user);
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
