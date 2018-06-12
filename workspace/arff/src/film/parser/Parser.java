package film.parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellUtil;

import com.aliasi.tokenizer.LowerCaseTokenizerFactory;
import com.aliasi.tokenizer.RegExTokenizerFactory;
import com.aliasi.tokenizer.Tokenizer;
import com.aliasi.tokenizer.TokenizerFactory;
import com.liwc.LIWC2015.model.Dictionary;
import com.liwc.core.TextProcessor;
import com.liwc.core.dictionary.DicFile;
import com.liwc.core.text.TxtFile;

public class Parser {

	/**
	 * @param args
	 * @throws Exception
	 */
	/**
	 * @param args
	 * @throws Exception
	 */
	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {

		

		DicFile dic = new DicFile(new FileInputStream("/Users/Rian.Rian/Desktop/Pesquisa/rate_automatico/LIWC2015_English.dic"));
		String texto = FileUtils.readFileToString(new File("/Users/Rian.Rian/Desktop/Pesquisa/rate_automatico/subtitlesMaster.txt"), "UTF-8");//subtitlesMaster  titlesTest
		String texto2 = FileUtils.readFileToString(new File("/Users/Rian.Rian/Desktop/Pesquisa/rate_automatico/subtitlesMaster.txt"), "UTF-8");//subtitlesMaster  titlesTest
		String auxTitulo = "";
		String auxTitulo2 = "";
		String[] filmeTitulo= new String[1175];
		String[] filmeTitulo2= new String[1175];
		String[] filmeTexto = new String[1175];
		HashMap<String, List> mapa = new HashMap<String, List>();
		HashMap<Integer, String> mapaQTDcategorias = new HashMap<Integer, String>();//Map (K,V) - mapaQTDcategorias(N categoria do dicionario, qtd de ocorrência na legenda)
		ArrayList<String> linhasDoArquivo = getLinhasArquivo(new File("/Users/Rian.Rian/Desktop/Pesquisa/rate_automatico/LIWC2015_English.dic"));//lista com todas as linhas do arquivo
		String linha;//String que recebe linha por linha a cada iteração do for
		String numero;//String que recebe apenas o número referente a cada categoria do dicionário
		String tipo;//String que recebe cada nome da categoria do dicionário
		ArrayList<String> titulos = new ArrayList<String>();
		String conjuntoDeCategorias = "";
		
		TextFileUtils.insertInFile("/Users/Rian.Rian/Desktop/Pesquisa/rate_automatico/temp/result_ocorrencias.arff", "@relation mqd");

		/*
		 * ESSE FOR VAI RETIRAR DE CADA LINHA O NÚMERO E NOME DA CATEGORIA 
		 * ESCREVENDO NO ARQUIVO ARFF EX: 1 - Function 
		 * E ADICIONANDO O NÚMERO DA CATEGORIA NO MAPA COMO CHAVE 
		 * E SETANDO NÚMERO DE OCORRÊNCIAS INICIAL = 0
		 */
		
		for(int y = 1; y <= linhasDoArquivo.size(); y++) {
			linha = linhasDoArquivo.get(y);
				if(!(linha.contains("%"))){ //quando a condição for falsa vai pro else e para de capturar as linhas
					numero = linha.substring(0, 3).trim();//retira da linha qualquer caracter da posição 0 até a 2
					int categoriaNumero = Integer.parseInt(numero);
					mapaQTDcategorias.put(categoriaNumero, "0");//Insere número como chave do mapa e quantidade inicial = 0
//					System.out.println(categoriaNumero);
					tipo = linha.substring(3, linha.indexOf("(")).trim();//retira o nome da categoria até o char "("
						if(!(numero.equals("00"))){
							TextFileUtils.insertInFile("/Users/Rian.Rian/Desktop/Pesquisa/rate_automatico/temp/result_ocorrencias.arff", "@attribute " +tipo+" numeric");//escreve no arff
						}
				} else{
					y = linhasDoArquivo.size();//insere valor máximo no y e para o for
				}
		}//for 
		
		TextFileUtils.insertInFile("/Users/Rian.Rian/Desktop/Pesquisa/rate_automatico/temp/result_ocorrencias.arff", "\n");
		TextFileUtils.insertInFile("/Users/Rian.Rian/Desktop/Pesquisa/rate_automatico/temp/result_ocorrencias.arff", "\n" + "@data");
		
		int i=0;
		int h=0;
		
		//PRIMEIRO WHILE É SÓ PARA PEGAR OS TITULOS E PREENCHER A LISTA TITULOS
				while(texto2.indexOf("<DOC>") != -1){ //Quando retornar -1 o "<DOC>" nao foi encontrado
					auxTitulo = texto2.substring(texto2.indexOf("<TITLE>")+7, texto2.indexOf("</TITLE>"));	// Pega o titulo
					auxTitulo = auxTitulo.substring(0,auxTitulo.length()-4); 				// Retira o ".xml" do titulo
					
					filmeTitulo[h] = auxTitulo.replace("_", " ");	// Recebe o titulo do filme
					titulos.add(filmeTitulo[h]); //Adiciona Título em um Arraylist
					System.out.println(filmeTitulo[h]);
					texto2 = texto2.substring(texto2.indexOf("</DOC>")+6, texto2.length());//Retira da string a legenda que já foi captada e passa a conter as legendas restantes
					h++;
				}
		
		ComparaTitulo compara = new ComparaTitulo(titulos);
		
		while(texto.indexOf("<DOC>") != -1){ //Quando retornar -1 o "<DOC>" nao foi encontrado
			auxTitulo2 = texto.substring(texto.indexOf("<TITLE>")+7, texto.indexOf("</TITLE>"));	// Pega o titulo
			auxTitulo2 = auxTitulo2.substring(0,auxTitulo2.length()-4); 				// Retira o ".xml" do titulo
			
			filmeTitulo2[i] = auxTitulo2.replace("_", " ");	// Recebe o titulo do filme
			//System.out.println(filmeTitulo[i]);
			filmeTexto[i] = texto.substring(texto.indexOf("</UNIQ_WORDS>")+14, texto.indexOf("</DOC>"));
			texto = texto.substring(texto.indexOf("</DOC>")+6, texto.length());//Retira da string a legenda que já foi captada e passa a conter as legendas restantes
			
			//String[] palavras = filmeTexto[i].replaceAll("\n", "").replace("\r","" ).replace(".","" ).split(" ");
			
			String[] palavras = limpaTexto(filmeTexto[i]).split(" ");
			
			
			for(int k=0;k<palavras.length;k++){
				//palavras[k] = limpaTexto(palavras[k]);
				boolean preenchido = mapa.containsKey((palavras[k]));
				if(!preenchido){							// Se nao estiver presente no map
					if(dic.findWord(palavras[k]) != null){	// Se a palavra estiver no dic
						//System.out.println(dic.findWord(palavras[k]));
						mapa.put(palavras[k], dic.findWord(palavras[k]));//dic.findWord(palavras[k]) pega a palavra e retorna as categorias						
						
					}
				}	
			}			
			
			Iterator it = mapa.keySet().iterator();
			while(it.hasNext()){
				String temp = (String) it.next();
				System.out.println(temp + " - " + mapa.get(temp));
			}
			
			Iterator it2 = mapa.keySet().iterator();
			while(it2.hasNext()){
				String temp2 = (String) it2.next();
				conjuntoDeCategorias+= (((mapa.get(temp2)).toString()).replace("[", " ")).replace("]", " ");//concatena todas categorias em uma String
			}//while
			//System.out.println(conjuntoDeCategorias); //teste output
			String[] categoriasNoTexto= (conjuntoDeCategorias.trim()).split("[\\W][ ]"); //expressão regular para remover caracteres e espaços da string e inserir nas posições do Array
			
			
			/*for(int l = 0; l<categoriasNoTexto.length;l++){  // teste output do array categoriasNoTexto
				System.out.println(categoriasNoTexto[l]+" cat");
			} //*/
		
			/*
			 * ESSE FOR COMPARA PARA CADA KEY DO MAP mapaQTDcategorias SE EXISTE O VALOR CORRESPONDENTE
			 * NO VETOR categoriasNoTexto, CASO EXISTE É SOMADO +1 E INSERIDO NO MAP mapaQTDcategorias
			 * ONDE A (K,V) = (NÚMERO DA CATEGORIA, QUANTIDADE DE OCORRÊNCIA DAQUELA CATEGORIA NA LEGENDA)
			 */
			
			for(Integer chave: mapaQTDcategorias.keySet()){
//				System.out.println(chave);//output test
				for(int z = 0; z < categoriasNoTexto.length; z++){
					if(chave == Integer.parseInt(categoriasNoTexto[z])){ //se categoria dentro de chave é igual a categoria no vetor
						String soma = Integer.toString(Integer.parseInt(mapaQTDcategorias.get(chave)) +1) ; //pega o valor referenta aquela key e incrementa 1
						mapaQTDcategorias.put(chave, soma); //insere novamente aquela key no map com o novo valor, atualizando assim seu registro no map
	               	 	//System.out.println("SOMEI");
	                } 
					if(chave == 00){
	               	 	//System.out.println("NÃO É CATEGORIA");
	                }
					if(chave == 126){
						mapaQTDcategorias.put(chave, "0");
					}
					if(chave == 127){
						mapaQTDcategorias.put(chave, "0");
					}
					if(chave == 128){
						mapaQTDcategorias.put(chave, "0");
					}
					if(chave == 129){
						mapaQTDcategorias.put(chave, "0");
					}
					if(chave == 130){
						mapaQTDcategorias.put(chave, "0");
					}
					if(chave == 131){
						mapaQTDcategorias.put(chave, "0");
					}
					if(chave == 132){
						mapaQTDcategorias.put(chave, "0");
					}
					
				} 
			}
			
			//SETANDO O VALOR QUE VAI IMPRIMIR NO ARFF NOS ATRIBUTOS RETIRADOS DO DATABASE
			for(int i1 = 0; i1 < compara.getFilmes().size(); i1++){
				if(titulos.get(i).equals(compara.getFilmes().get(i1).getMovie_title())){
					mapaQTDcategorias.put(126, compara.getFilmes().get(i1).getDuration());
					mapaQTDcategorias.put(127, compara.getFilmes().get(i1).getBudget());
					mapaQTDcategorias.put(128, compara.getFilmes().get(i1).getTitle_year());
					mapaQTDcategorias.put(129, compara.getFilmes().get(i1).getImdb_score());
					mapaQTDcategorias.put(130, compara.getFilmes().get(i1).getDirector_facebook_likes());
					mapaQTDcategorias.put(131, compara.getFilmes().get(i1).getMovie_facebook_likes());
					mapaQTDcategorias.put(132, compara.getFilmes().get(i1).getCast_total_facebook_likes());
				}
			}
			
			Iterator keysOrdenadas = mapaQTDcategorias.keySet().iterator(); //Iterando as keys que por definição já estão ordenadas no map
			String ocorrenciasDeCadaCategoria = "";//irá receber todas as ocorrências em uma só linha
			while(keysOrdenadas.hasNext()){
				int valor = (int) keysOrdenadas.next();
				if(!(keysOrdenadas.hasNext())){ //quando for a ultima key 
					ocorrenciasDeCadaCategoria+=(mapaQTDcategorias.get(valor));//Key inserida no final da string sem a vírgula 
				}else if(valor!=0 && keysOrdenadas.hasNext()){ //se a chave do map for diferente de 0 e está key possuir uma key sucessora no map
					ocorrenciasDeCadaCategoria+=(mapaQTDcategorias.get(valor)+", ");//Key inserida na string com uma vírgula no final para separar do proximo valor a ser concatenado
				}
			}
			
			TextFileUtils.insertInFile("/Users/Rian.Rian/Desktop/Pesquisa/rate_automatico/temp/result_ocorrencias.arff", ocorrenciasDeCadaCategoria + "\n");//escreve as ocorrencias no arff
			i++;// Atualiza casa no array
			
			/* ESSE FOR INSERE 0 EM TODOS OS VALORES DO MAP mapaQTDcategorias 
			 * PARA QUE O MESMO POSSA SER UTILIZADO NA LEITURA DA LEGENDA SEGUINTE*/
			for(Integer chave: mapaQTDcategorias.keySet()){
				mapaQTDcategorias.put(chave, "0");
			}
			
			mapa.clear();//Limpando o mapa que possuia as palavras e categorias da legenda já utilizada
			
			 /*ESSE FOR INSERE null EM TODOS OS VALORES DO ARRAY categoriasNoTexto 
			 * PARA QUE O MESMO POSSA SER UTILIZADO NA LEITURA DA LEGENDA SEGUINTE*/
			for(int g = 0; g < categoriasNoTexto.length; g++){
				categoriasNoTexto[g]=null;
			}
			
			StringBuilder x = new StringBuilder(ocorrenciasDeCadaCategoria);  
		    x.delete(0, ocorrenciasDeCadaCategoria.length());  //deletando o conteudo na String ocorrenciasDeCadaCategoria para que possa ser reutilizada no próximo ciclo
		    conjuntoDeCategorias=x.toString();
		    
		    StringBuilder s = new StringBuilder(conjuntoDeCategorias);  
		    s.delete(0, conjuntoDeCategorias.length());  //deletando o conteudo na String conjuntoDeCategorias para que possa ser reutilizada no próximo ciclo
		    conjuntoDeCategorias=s.toString(); 
		}//while	
		
		/*
		 * IMPRIMINDO O MAPA PARA TESTE
		 * */
//		for (Map.Entry entry : mapaQTDcategorias.entrySet()) {
//		    System.out.println(entry.getKey() + ", " + entry.getValue());
//		}
		
		//System.exit(0);
	}

	public static String limpaTexto(String texto) {

		// Utilizar esse trecho no mÃ¯Â¿Â½todo que irÃ¯Â¿Â½ tirar os sinais de
		// pontuaÃ¯Â¿Â½Ã¯Â¿Â½o e os sÃ¯Â¿Â½mbolos de cada linha e retornar a
		// linha jÃ¯Â¿Â½ corrigida
		String linha = "";
		
		TokenizerFactory factory = new RegExTokenizerFactory("[\\x2Da-zA-ZáàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ']+"); //EXPRESSÃO REGULAR
		
		factory = new LowerCaseTokenizerFactory(factory);
		Tokenizer tokens = factory.tokenizer(texto.toCharArray(), 0, texto.length());
		String palavra;
		while ((palavra = tokens.nextToken()) != null) {
			// System.out.println(palavra);
			linha += palavra + " ";
		}
		linha = linha.trim();
		// System.out.println(linha);
		return linha;
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
	
	
