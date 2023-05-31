package GeraFolha;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FilterTxtFile {

	static String cargo;
	static List<String> tempArrString = new ArrayList<>();
	static String[] splitedTempText;
	static String actualText;

	public static Map<String, List<String>> main() {
		List<String> textFile;
		
		ContentReadTxt content = new ContentReadTxt();
		textFile = GetArquivo.GetContentFile();
		
		for (int x =0; x< textFile.size(); x++) {
			actualText = textFile.get(x);
			
			if (actualText.indexOf(content.delimiter) >= 0) {
				if (Boolean.FALSE.equals(content.OnDelimiter)) {
					content.OnDelimiter = !content.OnDelimiter;
				}  
				content.contaLinha = 0;
			}
			if (Boolean.TRUE.equals(content.OnDelimiter)) {
				content.contaLinha += 1;
				content.tempText = actualText;
				if (content.contaLinha == 2 && actualText.indexOf(content.delimiterFinal) >= 0) {
					content.OnDelimiter = false;
				}
				else if (content.contaLinha == 2) {
					content.tempText = content.tempText.split("  ")[0].split("-")[1].strip();
					tempArrString = content.valores.get("NOME DO EMPREGADO");
					tempArrString.add(content.tempText);
					content.valores.put("NOME DO EMPREGADO", tempArrString);
					
					content.tempText = actualText;
					content.tempText = content.tempText.split(content.tempText.split("  ")[0])[1].strip();
					content.tempText = content.tempText.split("Ativo")[0].split("Férias")[0].split("Lic. Maternidade")[0];
					
					if (content.tempText.equals(content.tempText.toUpperCase())){
						
						tempArrString = content.valores.get("CARGOS EM COMISSÃO");
						tempArrString.add(content.tempText.strip());
						content.valores.put("CARGOS EM COMISSÃO", tempArrString);
						
						tempArrString = content.valores.get("CARGOS EFETIVOS");
						tempArrString.add(" ");
						content.valores.put("CARGOS EFETIVOS", tempArrString);
						content.cargosComissao += 1;
								
					}else {
						
						tempArrString = content.valores.get("CARGOS EFETIVOS");
						tempArrString.add(content.tempText.strip());
						content.valores.put("CARGOS EFETIVOS", tempArrString);
						
						tempArrString = content.valores.get("CARGOS EM COMISSÃO");
						tempArrString.add(" ");
						content.valores.put("CARGOS EM COMISSÃO", tempArrString);
						
						content.cargosEfetivos += 1;
					}
					
					
					tempArrString = content.valores.get("SITUAÇÃO");
					
					if(actualText.indexOf("Ativo") >= 0) {
						tempArrString.add("Ativo");
					}
					else if(actualText.indexOf("Férias") >= 0) {
						tempArrString.add("Férias");
					}
					else if(actualText.indexOf("Lic. Maternidade") >= 0) {
						tempArrString.add("Lic. Maternidade");
					}
					else if(actualText.indexOf("Afast. Prev.") >= 0) {
						tempArrString.add("Afast. Prev.");
					}
					else {
						tempArrString.add("NÃO IDENTIFICADO");
					}
			                    
			        content.valores.put("SITUAÇÃO", tempArrString);	
			        
				}
				else if(content.contaLinha == 3) {
	
	               String tempText = actualText.substring(actualText.indexOf(" ")).strip().split(" ")[0];
	               tempText = tempText.replace(".","").replace(",",".");
	                				
		           Float.parseFloat(tempText);  
		           tempArrString = content.valores.get("SALÁRIO NOMINAL");
		    	   tempArrString.add(tempText);
		    	   content.valores.put("SALÁRIO NOMINAL", tempArrString);
	                
				}
				if(actualText.indexOf("Proventos") >= 0) {
                	content.tempText = actualText;
                	String valor = content.tempText.split("Proventos")[1].strip().split(" ")[0];
                	
                	tempArrString = content.valores.get("REMUNERAÇÃO BRUTA NO MÊS");
					tempArrString.add(valor);
					content.valores.put("REMUNERAÇÃO BRUTA NO MÊS", tempArrString);
					
                	valor = content.tempText.split("Líquido")[1].strip().split(" ")[0];
                	
                	tempArrString = content.valores.get("REMUNERAÇÃO LÍQUIDA NO MÊS");
					tempArrString.add(valor);
					content.valores.put("REMUNERAÇÃO LÍQUIDA NO MÊS", tempArrString);
                }
			}
		}
		
		return content.valores;
		
	
	}
}
