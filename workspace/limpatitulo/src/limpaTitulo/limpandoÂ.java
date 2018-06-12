package limpaTitulo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.io.FileUtils;

import limpaTitulo.TextFileUtils;

public class limpandoÂ {
	
	public static void main(String [] args) throws Exception{
	
	ArrayList linhas = getLinhasArquivo(new File("/Users/Rian.Rian/Desktop/Pesquisa/IMDb/titulos.txt"));
	String titulo;
	String tituloCorrigido;
	
	for(int y = 1; y < linhas.size(); y++) {
		titulo = linhas.get(y).toString();
		tituloCorrigido = titulo.substring(0, titulo.length()-2);
		TextFileUtils.insertInFile("/Users/Rian.Rian/Desktop/Pesquisa/IMDb/titulosCorrigidos.txt", titulo );
					
			
	}
}

public static ArrayList<String> getLinhasArquivo(File file) throws FileNotFoundException, IOException {
	ArrayList<String> linhas;

	try (BufferedReader leitor = new BufferedReader(new FileReader(file))) {
		linhas = new ArrayList<>();
		String linha = "";

		while ((linha = leitor.readLine()) != null) {
			if (linha.length() > 0)
				linhas.add(linha);
		}
	}
	return linhas;
}
}
