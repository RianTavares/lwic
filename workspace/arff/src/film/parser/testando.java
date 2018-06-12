package film.parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class testando {

	public static void main(String[] args) throws FileNotFoundException, IOException {

		
	    //create HashMap object
        HashMap hMap = new HashMap();
       
        //add key value pairs to HashMap
        hMap.put("1","One");
        hMap.put("2","Two");
        hMap.put("3","Three");
     
        /*
          To remove all values or clear HashMap use
          void clear method() of HashMap class. Clear method removes all
          key value pairs contained in HashMap.
        */
       
        hMap.clear();
       
        System.out.println("Total key value pairs in HashMap are : " + hMap.size());
      
   
     
    /*
    Output would be
    Total key value pairs in HashMap are : 0
    */


		
		/*ArrayList<String> linhasDoArquivo = getLinhasArquivo(
				new File("/Users/Rian.Rian/Desktop/Pesquisa/rate automatico/LIWC2015_English.dic"));
		String linha2 = linhasDoArquivo.get(2);
		String linha4 = linhasDoArquivo.get(4);
		String linha;
		String numero;
		String tipo;
		int i;
		int y;
		for (i = 1; i <= linhasDoArquivo.size(); i++) {
			linha = linhasDoArquivo.get(i);
			if(!(linha.contains("%"))){
			numero = linha.substring(0, 3).trim();
			tipo = linha.substring(3, linha.indexOf("(")).trim();
			System.out.println("@attribute " + numero + " - " + tipo);
			} else{
				i = linhasDoArquivo.size();
			}
			
		}//for
		*/
}//main

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