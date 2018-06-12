package film.parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.aliasi.tokenizer.LowerCaseTokenizerFactory;
import com.aliasi.tokenizer.RegExTokenizerFactory;
import com.aliasi.tokenizer.Tokenizer;
import com.aliasi.tokenizer.TokenizerFactory;
import com.liwc.core.dictionary.DicFile;

public class TesteComparaTitulo {
public static void main(String[] args) throws Exception {

	

		DicFile dic = new DicFile(new FileInputStream("/Users/Rian.Rian/Desktop/Pesquisa/rate_automatico/LIWC2015_English.dic"));
		String texto = FileUtils.readFileToString(new File("/Users/Rian.Rian/Desktop/Pesquisa/rate_automatico/subtitlesMaster.txt"), "UTF-8");//subtitlesMaster  titlesTest
		String aux = "", auxTitulo = "";
		String[] filmeTitulo= new String[1175];
		String[] filmeTexto = new String[1175];
		HashMap<String, List> mapa = new HashMap<String, List>();
		ArrayList<String> titulos = new ArrayList<String>();
		
		
		int i=0;
		while(texto.indexOf("<DOC>") != -1){ //Quando retornar -1 o "<DOC>" nao foi encontrado
			auxTitulo = texto.substring(texto.indexOf("<TITLE>")+7, texto.indexOf("</TITLE>"));	// Pega o titulo
			auxTitulo = auxTitulo.substring(0,auxTitulo.length()-4); 				// Retira o ".xml" do titulo
			
			filmeTitulo[i] = auxTitulo.replace("_", " ");	// Recebe o titulo do filme
			System.out.println(filmeTitulo[i]);
			titulos.add(filmeTitulo[i]);
			filmeTexto[i] = texto.substring(texto.indexOf("</UNIQ_WORDS>")+14, texto.indexOf("</DOC>"));
			texto = texto.substring(texto.indexOf("</DOC>")+6, texto.length());//Retira da string a legenda que já foi captada e passa a conter as legendas restantes
			
						
		}//while
		ComparaTitulo compara = new ComparaTitulo(titulos);
		/*for(int i1 = 0; i1 < compara.getFilmes().size(); i1++){
		System.out.println("Filme: " + compara.getFilmes().get(i1).getMovie_title() + " " + 
		"Duração: " + compara.getFilmes().get(i1).getDuration() + " " + 
		"Orçamento: " + compara.getFilmes().get(i1).getBudget()+ " " +
		"Ano: " + compara.getFilmes().get(i1).getTitle_year() + " " +
		//compara.getFilmes().get(i1).getImdb_score() + " " +
		"Diretor Likes: " + compara.getFilmes().get(i1).getDirector_facebook_likes()+ " " +
		"Filme Likes: " + compara.getFilmes().get(i1).getMovie_facebook_likes()+ " " +
		"Cast Likes: " + compara.getFilmes().get(i1).getCast_total_facebook_likes());
		System.out.println(""); 
		}*/
		
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
