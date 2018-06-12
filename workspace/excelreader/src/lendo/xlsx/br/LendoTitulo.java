package lendo.xlsx.br;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.DefaultListModel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellUtil;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Br
 */
public class LendoTitulo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    	String titulo = "Titulo 1";
		String compare = "Titulo 1";
		String compare2 = "teste 1";
		String conteudo = "teste 1";
		String as1, as2, as3, as4;
		int x;
		

        FileInputStream fisPlanilha = null;

        try {
            File file = new File("C:\\Users\\Rian.Rian\\Desktop\\Pesquisa\\IMDb\\teste.xlsx");
            fisPlanilha = new FileInputStream(file);

            //cria um workbook = planilha toda com todas as abas
            XSSFWorkbook workbook = new XSSFWorkbook(fisPlanilha);

            //recuperamos apenas a primeira aba ou primeira planilha
            XSSFSheet sheet = workbook.getSheetAt(0);

            //retorna todas as linhas da planilha 0 (aba 1)
            Iterator<Row> rowIterator = sheet.iterator();

            //varre todas as linhas da planilha 0
            while (rowIterator.hasNext()) {

                //recebe cada linha da planilha
                Row row = rowIterator.next();

                //pegamos todas as celulas desta linha
                Iterator<Cell> cellIterator = row.iterator();

                //varremos todas as celulas da linha atual
              /*  while (cellIterator.hasNext()) {

                    //criamos uma celula
                    Cell cell = cellIterator.next();

                    switch (cell.getCellType()) {

                        case Cell.CELL_TYPE_STRING:
                            System.out.println("TIPO STRING: " + cell.getStringCellValue());
                            break;

                        case Cell.CELL_TYPE_NUMERIC:
                            System.out.println("TIPO NUMERICO: " + cell.getNumericCellValue());
                            break;
                            
                        case Cell.CELL_TYPE_FORMULA:
                            System.out.println("TIPO FORMULA: " + cell.getCellFormula());
                    }

                }*/
                
            } 
            for (int rowIndex = 1; rowIndex<7; rowIndex++){
            	Row linhas = CellUtil.getRow(0, sheet);
                String titulos = CellUtil.getCell(linhas, 0).toString();
            	
            	if(titulos.equals(compare)){
                Row row = CellUtil.getRow(rowIndex, sheet);
                Cell cell = CellUtil.getCell(row, 0);
                System.out.println(cell);
            	}
            }
            for (int rowIndex = 0; rowIndex<7; rowIndex++){
            	Row linhas = CellUtil.getRow(0, sheet);
                String titulos = CellUtil.getCell(linhas, 1).toString();
            	
            	if(titulos.equals(compare)){
                Row row = CellUtil.getRow(rowIndex, sheet);
                Cell cell = CellUtil.getCell(row, 1);
                System.out.println(cell);
            	}
            }
            for (int rowIndex = 0; rowIndex<7; rowIndex++){
            	Row linhas = CellUtil.getRow(0, sheet);
                String titulos = CellUtil.getCell(linhas, 2).toString();
            	
            	if(titulos.equals(compare)){
                Row row = CellUtil.getRow(rowIndex, sheet);
                Cell cell = CellUtil.getCell(row, 2);
                System.out.println(cell);
            	}
            }
            for (int rowIndex = 0; rowIndex<7; rowIndex++){
            	Row linhas = CellUtil.getRow(0, sheet);
                String titulos = CellUtil.getCell(linhas, 3).toString();
            	
            	if(titulos.equals(compare)){
                Row row = CellUtil.getRow(rowIndex, sheet);
                Cell cell = CellUtil.getCell(row, 3);
                System.out.println(cell);
            	}
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(LendoTitulo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(LendoTitulo.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fisPlanilha.close();
            } catch (IOException ex) {
                Logger.getLogger(LendoTitulo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
}
