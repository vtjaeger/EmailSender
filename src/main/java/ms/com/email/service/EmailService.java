package ms.com.email.service;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class EmailService {
    public List<String> filtrarColunaA(MultipartFile arquivo) {
        List<String> resultados = new ArrayList<>();

        try (InputStream inputStream = arquivo.getInputStream()) {
            Workbook workbook = new XSSFWorkbook(inputStream);
            Sheet sheet = workbook.getSheetAt(0);

            Iterator<Row> rowIterator = sheet.iterator();
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Cell cell = row.getCell(0);

                if (cell != null && cell.getCellType() == CellType.STRING) {
                    String valorCelula = cell.getStringCellValue();
                    if (valorCelula.equalsIgnoreCase("Vinicius")) {
                        resultados.add(valorCelula);
                    }
                }
            }

            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return resultados;
    }
}
