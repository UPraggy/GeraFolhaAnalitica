package GeraFolha;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class ContentReadTxt {

	Boolean OnDelimiter = false;
	String delimiter = "----------------------------";
	String delimiterFinal = "============================";
	int contaLinha = 0;
	String tempText;
	int cargosComissao = 0;
	int cargosEfetivos = 0;
	public Map<String, List<String>> valores = new TreeMap<>(); 
	
	public ContentReadTxt(){
		valores.put("NOME DO EMPREGADO", new ArrayList<>());
		valores.put("SITUA��O", new ArrayList<>());
		valores.put("CARGOS EFETIVOS", new ArrayList<>());
		valores.put("CARGOS EM COMISS�O", new ArrayList<>());
		valores.put("SAL�RIO NOMINAL", new ArrayList<>());
		valores.put("REMUNERA��O BRUTA NO M�S", new ArrayList<>());
		valores.put("REMUNERA��O L�QUIDA NO M�S", new ArrayList<>());
	}

}
