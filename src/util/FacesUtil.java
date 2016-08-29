package util;

import java.util.HashMap;
import java.util.Map;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import conexao.ConnectionFactory;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;

/** Esta � uma Classe utilitaria que implementa alguns metodos que servirao para ser reutilizados em algumas classes.
*   
* @author silas
* @since 17-08-2016
*/

public class FacesUtil {
	
	/** Este metodo captura o atributo da requisicao pelo nome .
	* 	
	* 	@param name, Este name e o nome do atributo que voce vai pegar.
	* 	@return retorna o objeto referente aquele name.
	*/
	public static Object getRequestAttribute(String name) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ExternalContext externalContext = facesContext.getExternalContext();
		HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
		return request.getAttribute(name);
	}	
	
	/*public String pegarRelatorio(int tamanhoAtributos, Map<String, Object> listaObjetos){
		String reportSrcFile = "";
		
		switch (tamanhoAtributos) {
		case 1:
			reportSrcFile = this.escolherRelatorio(listaObjetos);
		break;
		case 2:
		System.out.println("Segunda-feira");
		
		break;
		case 3:
		System.out.println("Ter�a-feira");
		
		break;
		case 4:
		System.out.println("Quarta-feira");
		
		break;
		case 5:
		System.out.println("Quinta-feira");
		
		break;
		case 6:
		System.out.println("Sexta-feira");
		
		break;
		case 7:
		System.out.println("S�bado");
		
		break;
		default:
		System.out.println("Este n�o � um dia v�lido!");
		
		}
		
		
		return reportSrcFile;
	}*/
	
	
	public Map<String, String> escolherRelatorio(Map<String, Object> listaObjetos){
		Map<String, String> mapaRelatorioParametro = new HashMap<String, String>();
		
		String relatorio = "";
		String parametro = "";
		
		for (String key : listaObjetos.keySet()) {
            if (key.equals("placa")) {
				relatorio = "/opt/tomcat/webapps/Deprov/resources/relatorios/parametros/1/"+"Placa.jrxml";
				parametro = "placa";
			}else if (key.equals("placaOriginal")) {
				relatorio = "/opt/tomcat/webapps/Deprov/resources/relatorios/parametros/1/"+"PlacaOriginal.jrxml";
				parametro = "placaoriginal";
			}else if (key.equals("fabricante.fabricante")) {
				relatorio = "/opt/tomcat/webapps/Deprov/resources/relatorios/parametros/1/"+"Fabricante.jrxml";
				parametro = "codigo_fabricante";
			}else if (key.equals("modelo.modelo")) {
				relatorio = "/opt/tomcat/webapps/Deprov/resources/relatorios/parametros/1/"+"Modelo.jrxml";
				parametro = "codigo_modelo";
			}else if (key.equals("chassi")) {
				relatorio = "/opt/tomcat/webapps/Deprov/resources/relatorios/parametros/1/"+"Chassi.jrxml";
				parametro = "chassi";
			}else if (key.equals("cor.cor")) {
				relatorio = "/opt/tomcat/webapps/Deprov/resources/relatorios/parametros/1/"+"Cor.jrxml";
				parametro = "codigo_cor";
			}
			/*System.out.println("key: " + key + " \t values: "
                    + listaObjetos.get(key).toString().toUpperCase());*/
            mapaRelatorioParametro.put(parametro, relatorio);   
        }
		
		
		return mapaRelatorioParametro;
	}
	
	
	
	
}