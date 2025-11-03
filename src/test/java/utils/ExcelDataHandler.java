package utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Logger;

public class ExcelDataHandler {
    private static final Logger LOGGER = Logger.getLogger(ExcelDataHandler.class.getName());

    private static final Path EXCEL_FILE_PATH = Paths.get("./src/test/resources/data/DataFile.xlsx");

    public void writeLog(String text) {
        LOGGER.info("Log: " + text);
    }

    public Map<String, String> readExcelRowData(String sheetName, int dataRowNumber) throws IOException {

        int poiRowIndex = dataRowNumber - 1;

        try (InputStream fileStream = Files.newInputStream(EXCEL_FILE_PATH);
             XSSFWorkbook workbook = new XSSFWorkbook(fileStream)) {

            XSSFSheet sheet = workbook.getSheet(sheetName);

            if (sheet == null) {
                writeLog("ERROR: La hoja de cálculo '" + sheetName + "' no existe.");
                throw new RuntimeException("Hoja de Excel no encontrada: " + sheetName);
            }

            Row headerRow = sheet.getRow(0);
            if (headerRow == null) {
                throw new RuntimeException("El Excel no tiene encabezados en la fila 1.");
            }
            int maxRows = sheet.getLastRowNum();

            if (dataRowNumber == 1) {
                writeLog("Error: La fila 1 contiene las 'keys', no los datos. Use una fila > 1.");
                throw new RuntimeException("Fila de datos inválida: " + dataRowNumber);
            }
            if (poiRowIndex > maxRows) {
                writeLog("Error: La fila " + dataRowNumber + " del Excel excede el número de filas disponibles (" + (maxRows) + ").");
                throw new RuntimeException("Fila de datos fuera de rango: " + dataRowNumber);
            }

            Row dataRow = sheet.getRow(poiRowIndex);
            if (dataRow == null) {
                throw new RuntimeException("La fila " + dataRowNumber + " está vacía o es nula.");
            }

            Map<String, String> mapItems = new LinkedHashMap<>();
            int totalColumns = headerRow.getLastCellNum();

            for (int col = 0; col < totalColumns; col++) {
                Cell headerCell = headerRow.getCell(col);
                Cell dataCell = dataRow.getCell(col);

                String key = getCellValueAsString(headerCell);
                String value = getCellValueAsString(dataCell);

                mapItems.put(key, value);
            }

            writeLog("Datos de la fila " + dataRowNumber + " cargados exitosamente de la hoja " + sheetName);
            return mapItems;

        } catch (IOException e) {
            writeLog("Error de E/S al acceder al archivo: " + EXCEL_FILE_PATH.toAbsolutePath());
            throw e;
        } catch (Exception e) {
            writeLog("Error general en la lectura de Excel: " + e.getMessage());
            throw new RuntimeException("Fallo en la lectura de Excel.", e);
        }
    }

    private String getCellValueAsString(Cell cell) {
        if (cell == null) {
            return "";
        }
        DataFormatter formatter = new DataFormatter();
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue().trim();
            case NUMERIC:
                return formatter.formatCellValue(cell).trim();
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                Workbook workbook = cell.getSheet().getWorkbook();
                FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
                return formatter.formatCellValue(evaluator.evaluateInCell(cell)).trim();
            case BLANK:
                return "";
            default:
                return cell.toString();
        }
    }
}
