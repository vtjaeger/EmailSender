package ms.com.email.service;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

public class LerConteudoCelula {
    public String lerConteudoTabela(String caminhoDoArquivo) {
        try (FileInputStream fileInputStream = new FileInputStream(new File(caminhoDoArquivo));
             Workbook workbook = WorkbookFactory.create(fileInputStream)) {

            Sheet sheet = workbook.getSheetAt(0);
            StringBuilder conteudo = new StringBuilder();

            // Itera sobre as linhas da planilha
            Iterator<Row> rowIterator = sheet.iterator();
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();

                // Itera sobre as células de cada linha
                Iterator<Cell> cellIterator = row.iterator();
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();

                    // Adiciona o conteúdo da célula ao StringBuilder
                    conteudo.append(cell.toString()).append("\t");
                }

                // Adiciona uma quebra de linha ao final de cada linha da planilha
                conteudo.append("\n");
            }

            return conteudo.toString();
        } catch (IOException | EncryptedDocumentException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao ler o conteúdo da tabela: " + e.getMessage());
        }
    }
}