package util;

import java.util.HashMap;
import java.util.Map;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;


/** Esta é uma Classe utilitaria que implementa alguns metodos que servirao para ser reutilizados em algumas classes.
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
	
	
	public Map<String, String> escolherRelatorio(Map<String, Object> listaObjetos){
		Map<String, String> mapaRelatorioParametro = new HashMap<String, String>();
		
		String relatorio = "";
		String parametro = "";
		
		for (String key : listaObjetos.keySet()) {
            if (key.equals("placa")) {
				relatorio = "/opt/tomcat/webapps/Deprov/resources/relatorios/parametros/1/Placa.jrxml";
            	//relatorio = "/var/lib/tomcat/webapps/Deprov/resources/relatorios/parametros/1/Placa.jrxml";
				parametro = "placa";
			}else if (key.equals("placaOriginal")) {
				relatorio = "/opt/tomcat/webapps/Deprov/resources/relatorios/parametros/1/PlacaOriginal.jrxml";
				//relatorio = "/var/lib/tomcat/webapps/Deprov/resources/relatorios/parametros/1/PlacaOriginal.jrxml";
				parametro = "placaoriginal";
			}else if (key.equals("fabricante.fabricante")) {
				relatorio = "/opt/tomcat/webapps/Deprov/resources/relatorios/parametros/1/Fabricante.jrxml";
				//relatorio = "/var/lib/tomcat/webapps/Deprov/resources/relatorios/parametros/1/Fabricante.jrxml";
				parametro = "codigo_fabricante";
			}else if (key.equals("modelo.modelo")) {
				relatorio = "/opt/tomcat/webapps/Deprov/resources/relatorios/parametros/1/Modelo.jrxml";
				//relatorio = "/var/lib/tomcat/webapps/Deprov/resources/relatorios/parametros/1/Modelo.jrxml";
				parametro = "codigo_modelo";
			}else if (key.equals("chassi")) {
				relatorio = "/opt/tomcat/webapps/Deprov/resources/relatorios/parametros/1/Chassi.jrxml";
				//relatorio = "/var/lib/tomcat/webapps/Deprov/resources/relatorios/parametros/1/Chassi.jrxml";
				parametro = "chassi";
			}else if (key.equals("cor.cor")) {
				relatorio = "/opt/tomcat/webapps/Deprov/resources/relatorios/parametros/1/Cor.jrxml";
				//relatorio = "/var/lib/tomcat/webapps/Deprov/resources/relatorios/parametros/1/Cor.jrxml";
				parametro = "codigo_cor";
			}else if (key.equals("seguro.seguro")) {
				relatorio = "/opt/tomcat/webapps/Deprov/resources/relatorios/parametros/1/Seguro.jrxml";
				//relatorio = "/var/lib/tomcat/webapps/Deprov/resources/relatorios/parametros/1/Seguro.jrxml";
				parametro = "codigo_seguro";
			}else if (key.equals("pericia.pericia")) {
				relatorio = "/opt/tomcat/webapps/Deprov/resources/relatorios/parametros/1/Pericia.jrxml";
				//relatorio = "/var/lib/tomcat/webapps/Deprov/resources/relatorios/parametros/1/Pericia.jrxml";
				parametro = "codigo_pericia";
			}else if (key.equals("situacao.situacao")) {
				relatorio = "/opt/tomcat/webapps/Deprov/resources/relatorios/parametros/1/Situacao.jrxml";
				//relatorio = "/var/lib/tomcat/webapps/Deprov/resources/relatorios/parametros/1/Situacao.jrxml";
				parametro = "codigo_situacao";
			}
            mapaRelatorioParametro.put(parametro, relatorio);
        }
		return mapaRelatorioParametro;
	}	
}