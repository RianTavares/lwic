package film;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;



public class TextFileUtils {
	public static void insertInFile(String fileName, String linha, boolean pulaLinha) {
		try {
			FileWriter fw = new FileWriter(fileName,true);
			BufferedWriter bw = new BufferedWriter(fw);
			
			bw.write(linha);
			if (pulaLinha) { 
				bw.newLine();
			}	
			bw.close();
		} catch (IOException e) {	
			e.printStackTrace();
		}
	}
	// Chamar esse mÃ©todo
	public static void insertInFile(String fileName, String linha) {
		insertInFile(fileName, linha, true);
	}
	public static void insertInFile(String fileName, List<String> lista) {
		insertInFile(fileName, lista, false);
	}
	public static void insertInFile(String fileName, List<String> lista, boolean pulaLinhaAoFim) {
		try {
			FileWriter fw = new FileWriter(fileName,true);
			BufferedWriter bw = new BufferedWriter(fw);
			for (int i=0; i<lista.size(); i++) {
				String palavra = lista.get(i);
				bw.write(palavra);
				if (!(i==lista.size()-1)) bw.write(" ");//espaco entre palavras 
			}
			if (pulaLinhaAoFim) bw.newLine();

		//	bw.newLine();
			bw.close();
		} catch (IOException e) {	
			e.printStackTrace();
		}
	}
	public static void insertInFile(String fileName, List<String> lista, boolean pulaLinhaEntre, boolean pulaLinhaAoFim) {
		try {
			FileWriter fw = new FileWriter(fileName,true);
			BufferedWriter bw = new BufferedWriter(fw);
			for (int i=0; i<lista.size(); i++) {
				String palavra = lista.get(i);
				bw.write(palavra);
				if (pulaLinhaEntre) bw.newLine();
				//if (!(i==lista.size()-1)) bw.write(" ");//espaco entre palavras 
			}
			if (pulaLinhaAoFim) bw.newLine();

		//	bw.newLine();
			bw.close();
		} catch (IOException e) {	
			e.printStackTrace();
		}
	}

	public static String getStringFromInputStream(InputStream is) {
		 
		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();
 
		String line;
		try {
 
			br = new BufferedReader(new InputStreamReader(is));
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
 
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
 
		return sb.toString();
 
	}
	/**
	 * Le as linhas de um arquivo e retorna um array de inteiros.
	 * @param filePath
	 * @return
	 * FURADO
	 */
	@Deprecated
	public static int [] getIntegerLinesFromFile(String filePath) {
		List<String> lista = getLinesFromFile(filePath);
		int [] array_int = new int [lista.size()];
		for (int i = 0; i < lista.size(); i++) {
			array_int[i] = Integer.parseInt(lista.get(i));
		}
		return array_int;
	}


	/**
	 * MÄ�Å¼Ë�todo que pega as linhas de um arquivo e insere como Strings em uma lista. Cada linha Ä�Å¼Ë� um elemento na lista.
	 * @param filePath
	 * @return
	 */
	public static List<String> getLinesFromFile(String filePath) {
		FileInputStream is = null;
		try {
			is = new FileInputStream(new File(filePath));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		ArrayList<String> lista = new ArrayList<String>();
		BufferedReader br = null;
		String line;
		try {
 
		//	br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
			br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
			while ((line = br.readLine()) != null) {
				lista.add(line);
			}
 
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
 
		return lista;
 
	}
	public static Map<String, String> leArquivoReducoes(String filePath, String strToSplit) {
		List<String> lista = TextFileUtils.getLinesFromFile(filePath);
		Map<String, String> mapa = new TreeMap<String, String>();
		Iterator<String> it = lista.iterator();
		while (it.hasNext()) {
			String linha = (String) it.next();
			if (linha.startsWith("%")) continue; //comentÄ�Å¼Ë�rio
			String [] palavras = linha.split("#");
			//System.out.println(linha);
			mapa.put(palavras[0], palavras[1]);
		}
		return mapa;
	}
	public static Set<String> leArquivoStopWords(String filePath) {
		List<String> lista = TextFileUtils.getLinesFromFile(filePath);
		Set<String> set = new TreeSet<String>();
		Iterator<String> it = lista.iterator();
		while (it.hasNext()) {
			String linha = (String) it.next();
			if (linha.startsWith("%")) continue; //comentÄ�Å¼Ë�rio
			
			//System.out.println(linha);
			set.add(linha);
		}
		return set;
	}

	
	public static void persistir(Object o, String fileName) {
		   try {
	            FileOutputStream arquivoGrav = new FileOutputStream(fileName);
	            ObjectOutputStream objGravar = new ObjectOutputStream(arquivoGrav);
	            objGravar.writeObject(o);
	            objGravar.flush();
	            objGravar.close();
	            arquivoGrav.flush();
	            arquivoGrav.close();
	           // logger.info("Corpus gravado com sucesso!");
	         
	        } catch( Exception e ){
	                e.printStackTrace( );
	        }
	}
	
	public static Object lerSerializado(String arquivo) {
		String fileName = arquivo;
		File f = new File(fileName);
		 Object corpus = null;

			ObjectInputStream objLeitura = null;
	        try
	        {
	            //Carrega o arquivo
	            FileInputStream arquivoLeitura = new FileInputStream(f);
	            //Classe responsavel por recuperar os objetos do arquivo
	             objLeitura = new ObjectInputStream(arquivoLeitura);
	             
	           // System.out.println(objLeitura.readObject());
	            

	             corpus  = objLeitura.readObject();
	             
	           objLeitura.close();
	           arquivoLeitura.close();
	          
	        	
	        } catch( Exception e ){
	                e.printStackTrace( );
	        }
	        return corpus;

		}
	public static void geraMediasVariosArquivos(String inputDirectoryName, String outputFileName){
		File directory = new File(inputDirectoryName);
		File [] files = directory.listFiles();
		List<List<String>> lista = new ArrayList<List<String>>();
		//cabecalho
		TextFileUtils.insertInFile(outputFileName, "X " + "Y " + "K "+ "OBJ "+ "RESTRICOES "+"PUREZA "+ "NMI");
		for (int i=0; i< files.length; i++) {
			File f = files[i];
			List<String> linhasArquivo = TextFileUtils.getLinesFromFile(f.getAbsolutePath());
			lista.add(linhasArquivo);
		}
		int qtdLinhasArquivos = lista.get(0).size();

		for (int i=0; i< qtdLinhasArquivos; i++) {
			double fcObjetivo =0;
			double numClusters =0;
			double pureza = 0;
			double nmi = 0;
			double clusters = 0;
			
			for (int j=0; j< lista.size(); j++) {
				List<String> listaLinhasArquivos = lista.get(j);
				
				String [] linha =  listaLinhasArquivos.get(i).split(" ");
				numClusters +=Integer.parseInt(linha[2]);
				fcObjetivo +=Double.parseDouble(linha[3]);
				clusters +=Double.parseDouble(linha[4]);
				pureza +=Double.parseDouble(linha[5]);
				nmi +=Double.parseDouble(linha[6]);
			}
			TextFileUtils.insertInFile(outputFileName, "W " + "P " +(int)(numClusters/files.length)+" "+fcObjetivo/files.length + " "+
			clusters/files.length + " " +pureza/files.length + " " + nmi/files.length) ;
		}
		
		
			
		
	}	
	public static double [] transformaArrayStringArrayDouble(String [] str) {
		double d [] = new double [str.length];
		for (int i = 0; i < str.length; i++) {
			d[i] = Double.parseDouble(str[i]);
		}
		return d;
	}
}