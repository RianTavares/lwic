package film.parser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.io.FileUtils;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellUtil;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/* ATRIBUTOS RETIRADOS DO DATABASE
 * movie_title
 * duration
 * budget
 * title_year
 * imdb_score
 * director_facebook_likes
 * movie_facebook_likes
 * cast_total_facebook_likes
 * */


public class ComparaTitulo {
		
	ArrayList<String> listaTitulos = new ArrayList<String>();
	ArrayList<String> listaRecebeTitulosExistentes = new ArrayList<String>();
	ArrayList<FilmDataBase> filmes = new ArrayList<FilmDataBase>();
	Workbook wb = new XSSFWorkbook();//Workbook onde iremos criar uma planilha
	Sheet sheet1 = wb.createSheet("new sheet");//Pagina da planilha
	Row row = null;
	
	
	public ComparaTitulo(ArrayList<String> titulos) throws IOException, EncryptedDocumentException, InvalidFormatException{
		
		this.listaTitulos = titulos;
		
        FileInputStream fisPlanilha = null;

        
			try {
            File file = new File("C:\\Users\\Rian.Rian\\Desktop\\Pesquisa\\IMDb\\data01.xlsx");
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
            		 row = rowIterator.next();
            

            		//pegamos todas as celulas desta linha
            		Iterator<Cell> cellIterator = row.iterator();

            	}
            	//System.out.println(row.getRowNum());
            	
            	int count = 0;
            	Row linha = null;
            	int negativ = 0;
            	for (int rowIndex = 1; rowIndex <= row.getRowNum(); rowIndex++){
            		Row row2 = CellUtil.getRow(rowIndex, sheet);
            		Cell cell = CellUtil.getCell(row2, 0);
            		//System.out.println(cell.getStringCellValue());
            		if(listaTitulos.contains(cell.toString())){
                    	FilmDataBase film = new FilmDataBase();
                    	film.setMovie_title(row2.getCell(0).toString());
                    	
                    	if (row2.getCell(1) != null && row2.getCell(1).getCellType() != row2.getCell(1).CELL_TYPE_BLANK)
                    		film.setDuration(row2.getCell(1).toString());
                    	else 
                    		film.setDuration("-1");
                    	
                    	if (row2.getCell(2) != null && row2.getCell(2).getCellType() != row2.getCell(2).CELL_TYPE_BLANK)
                    		film.setBudget(row2.getCell(2).toString());
                    	else 
                    		film.setBudget("-1");
                    	
                    	if (row2.getCell(3) != null && row2.getCell(3).getCellType() != row2.getCell(3).CELL_TYPE_BLANK)
                    		film.setTitle_year(row2.getCell(3).toString());
                    	else 
                    		film.setTitle_year("-1");
                    	
                    	if (row2.getCell(4) != null && row2.getCell(4).getCellType() != row2.getCell(4).CELL_TYPE_BLANK)
                    		film.setImdb_score(row2.getCell(4).toString());
                    	else 
                    		film.setImdb_score("-1");
                    	
                    	if (row2.getCell(5) != null && row2.getCell(5).getCellType() != row2.getCell(5).CELL_TYPE_BLANK)
                    		film.setDirector_facebook_likes(row2.getCell(5).toString());
                    	else 
                    		film.setDirector_facebook_likes("-1");
                    	
                    	if (row2.getCell(6) != null && row2.getCell(6).getCellType() != row2.getCell(6).CELL_TYPE_BLANK)
                    		film.setMovie_facebook_likes(row2.getCell(6).toString());
                    	else 
                    		film.setMovie_facebook_likes("-1");
                    	
                    	if (row2.getCell(7) != null && row2.getCell(7).getCellType() != row2.getCell(7).CELL_TYPE_BLANK)
                    		film.setCast_total_facebook_likes(row2.getCell(7).toString());
                    	else 
                    		film.setCast_total_facebook_likes("-1");
                    	filmes.add(film);
                    	count++;
                    	
                    	//ESCREVENDO EM UM NOVO ARQUIVO XLSX
                    	linha = sheet1.createRow((short)count);//cria uma linha sempre que a comparação da True
                    	Cell celula = linha.createCell(0);//cria uma celula na posicao 0
                    	celula.setCellValue(cell.getStringCellValue());
                    }
                    else{
                    	negativ++;
                    }
                    
            	} 
            	
            	
            	FileOutputStream fileOut = new FileOutputStream("/Users/Rian.Rian/Desktop/Pesquisa/rate_automatico/workbook.xlsx");
        	    wb.write(fileOut);
        	    fileOut.close();
            	System.out.println("Negativo: "+negativ);//CONTADOR DE TITULOS DIFERENTES
            	System.out.println("Positivo: "+count);//CONTADOR DE TITULOS IGUAIS 
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ComparaTitulo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ComparaTitulo.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fisPlanilha.close();
            } catch (IOException ex) {
                Logger.getLogger(ComparaTitulo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
	}

	
	public static void removeRow(Sheet sheet, int rowIndex) {
	    int lastRowNum = sheet.getLastRowNum();
	    	if (rowIndex >= 0 && rowIndex < lastRowNum) {
	    		sheet.shiftRows(rowIndex + 1, lastRowNum, -1);
	    	}
	    	if (rowIndex == lastRowNum) {
	    		Row removingRow = sheet.getRow(rowIndex);
	        if (removingRow != null) {
	            sheet.removeRow(removingRow);
	        }
	    }
	}
	//GETTERS AND SETTERS
	
	public ArrayList<String> getListaRecebeTitulosExistentes() {
		return listaRecebeTitulosExistentes;
	}
	public ArrayList<FilmDataBase> getFilmes() {
		return filmes;
	}


	public void setFilmes(ArrayList<FilmDataBase> filmes) {
		this.filmes = filmes;
	}
	
}


