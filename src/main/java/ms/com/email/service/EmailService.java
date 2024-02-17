package ms.com.email.service;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.StringJoiner;

@Service
public class EmailService {
    public String convertExcelToString(MultipartFile file) throws IOException {
        try (Workbook workbook = new XSSFWorkbook(file.getInputStream())) {

            Sheet sheet = workbook.getSheetAt(0); // primeira linha do excel

            //bordas
            CellStyle cellStyle = workbook.createCellStyle();
            cellStyle.setBorderBottom(BorderStyle.THIN);
            cellStyle.setBorderTop(BorderStyle.THIN);
            cellStyle.setBorderRight(BorderStyle.THIN);
            cellStyle.setBorderLeft(BorderStyle.THIN);

            StringJoiner stringJoiner = new StringJoiner("\n"); // stringJoiner para juntar as linhas

            // a cada linha da planilha
            for (Row row : sheet) {
                StringJoiner rowJoiner = new StringJoiner("\t"); // nova stringJoiner para juntar as linhas
                for (Cell cell : row) {
                    switch (cell.getCellType()) {

                        // adiciona o valor da celula na linha
                        case STRING:
                            rowJoiner.add(cell.getStringCellValue());
                            break;
                        case NUMERIC:
                            rowJoiner.add(String.valueOf(cell.getNumericCellValue()));
                            break;
                        case BOOLEAN:
                            rowJoiner.add(String.valueOf(cell.getBooleanCellValue()));
                            break;
                        default:
                            rowJoiner.add("");
                    }
                }
                // adiciona a celula na linha
                stringJoiner.add(rowJoiner.toString());
            }

            return stringJoiner.toString();
        }
    }
}