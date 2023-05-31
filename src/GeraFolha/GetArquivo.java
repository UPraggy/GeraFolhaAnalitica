package GeraFolha;

import java.nio.file.*; //Biblioteca de arquivos
import java.util.ArrayList;
import java.util.List; //Biblioteca para listas
import java.nio.charset.StandardCharsets; //formatação de arquivo em ptBr

public class GetArquivo {
	
	private static String dirAtual;
	private static String[] dirAtualArr;
	private static String locArquivo;
	private static Path folhaAnaliticaArq;
	private static List<String> textFile;
	
	
	public static List<String> GetContentFile(){
		dirAtual = System.getProperty("user.dir");
		
		//VOLTANDO UM DIRETORIO PARA PEGAR ARQUIVO
		dirAtualArr = dirAtual.split("\\\\"); 
		locArquivo = "FolhaAnalitica.txt";
		
		dirAtual = "";
		//lenght-1
		for (int x=0; x<dirAtualArr.length; x++) {
			dirAtual += dirAtualArr[x]+"/"; //unindo lista sem o ultimo diretorio
		}

			folhaAnaliticaArq = Paths.get(dirAtual+locArquivo);

		textFile = new ArrayList<>();
		//System.out.println(dirAtual+locArquivo);	
		if (Files.exists(folhaAnaliticaArq) == false) {
			textFile.add("[ERROR: File Exists] : Arquivo não existe em\n"+ dirAtual+locArquivo);
			return textFile;
		}
		
		try{
			textFile = Files.readAllLines(folhaAnaliticaArq, StandardCharsets.UTF_8);
		}catch(Exception IOException) {
			textFile.add("[ERROR: File CharSet] : Não foi possivel ler o Arquivo");
			return textFile;
		}
		
		//imprimindo linhas do arquivo
		/*for (String line:textFile) {
			System.out.println(line);
		}*/
		
		return textFile;
	}

}
