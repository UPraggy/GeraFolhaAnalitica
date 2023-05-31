package GeraFolha;

import java.util.List;
import java.util.Map;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

public class CreateExcel {

	private static String fileName;
	private static String dirAtual;
	private static String[] dirAtualArr;
	private static String locArquivo;
	
	public static void main() {
		dirAtual = System.getProperty("user.dir");
		
		//VOLTANDO UM DIRETORIO PARA PEGAR ARQUIVO
		dirAtualArr = dirAtual.split("\\\\"); 
		
		dirAtual = "";
		//lenght-1
		for (int x=0; x<dirAtualArr.length; x++) {
			dirAtual += dirAtualArr[x]+"/"; //unindo lista sem o ultimo diretorio
		}
		
		fileName = dirAtual + "FolhaAnalitica.xls";
		
		int rownum = 0;
		Map<String, List<String>> listaComValores;
		listaComValores = FilterTxtFile.main();
		
		HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheetFolha = workbook.createSheet("FolhaAnalitica");
		
		int sizeRows = listaComValores.get("CARGOS EFETIVOS").size();
		
		
        for (int rowTMP = 0; rowTMP < sizeRows; rowTMP ++) {

        	if (rowTMP == 0) {
        		Row row = sheetFolha.createRow(rownum++);
        		int cellnum = 0;
        		Cell cellNome = row.createCell(cellnum++);
        		cellNome.setCellValue("NOME DO EMPREGADO");
        		Cell cellSit = row.createCell(cellnum++);
        		cellSit.setCellValue("SITUA��O");
        		Cell cellCargEft = row.createCell(cellnum++);
        		cellCargEft.setCellValue("CARGOS EFETIVOS");
        		Cell cellCargCom = row.createCell(cellnum++);
        		cellCargCom.setCellValue("CARGOS EM COMISS�O");
        		Cell cellNomn = row.createCell(cellnum++);
        		cellNomn.setCellValue("SAL�RIO NOMINAL");
        		Cell cellRemBrt = row.createCell(cellnum++);
        		cellRemBrt.setCellValue("REMUNERA��O BRUTA NO M�S");
        		Cell cellRemLiq = row.createCell(cellnum++);
        		cellRemLiq.setCellValue("REMUNERA��O L�QUIDA NO M�S");
        	}else {
        		Row row = sheetFolha.createRow(rownum++);
        		int cellnum = 0;
        		Cell cellNome = row.createCell(cellnum++);
        		cellNome.setCellValue(listaComValores.get("NOME DO EMPREGADO").get(rowTMP-1));
        		Cell cellSit = row.createCell(cellnum++);
        		cellSit.setCellValue(listaComValores.get("SITUA��O").get(rowTMP-1));
        		Cell cellCargEft = row.createCell(cellnum++);
        		cellCargEft.setCellValue(listaComValores.get("CARGOS EFETIVOS").get(rowTMP-1));
        		Cell cellCargCom = row.createCell(cellnum++);
        		cellCargCom.setCellValue(listaComValores.get("CARGOS EM COMISS�O").get(rowTMP-1));
        		Cell cellNomn = row.createCell(cellnum++);
        		cellNomn.setCellValue(listaComValores.get("SAL�RIO NOMINAL").get(rowTMP-1));
        		Cell cellRemBrt = row.createCell(cellnum++);
        		cellRemBrt.setCellValue(listaComValores.get("REMUNERA��O BRUTA NO M�S").get(rowTMP-1));
        		Cell cellRemLiq = row.createCell(cellnum++);
        		cellRemLiq.setCellValue(listaComValores.get("REMUNERA��O L�QUIDA NO M�S").get(rowTMP-1));
        	}
        }
        
	        try {
	            FileOutputStream out = 
	                    new FileOutputStream(new File(fileName));
	            workbook.write(out);
	            out.close();
	            System.out.println("Arquivo Excel criado com sucesso!");
	             
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	               System.out.println("Arquivo n�o encontrado!");
	        } catch (IOException e) {
	            e.printStackTrace();
	               System.out.println("Erro na edi��o do arquivo!");
	        }
        }

}
