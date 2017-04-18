package pcrn.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import pcrn.interfaces.ICor;
import pcrn.interfaces.IFabricante;
import pcrn.interfaces.ILocal;
import pcrn.interfaces.IModelo;
import pcrn.interfaces.IPericia;
import pcrn.interfaces.ISeguro;
import pcrn.interfaces.ISituacao;
import pcrn.model.Cor;
import pcrn.model.Fabricante;
import pcrn.model.Local;
import pcrn.model.Modelo;
import pcrn.model.Pericia;
import pcrn.model.Seguro;
import pcrn.model.Situacao;
import pcrn.repository.Cores;
import pcrn.repository.Fabricantes;
import pcrn.repository.Locais;
import pcrn.repository.Modelos;
import pcrn.repository.Pericias;
import pcrn.repository.Seguros;
import pcrn.repository.Situacoes;




/** Esta � uma Classe utilitaria que implementa alguns metodos que servirao para ser reutilizados em algumas classes.
*   
* @author silas
* @since 17-08-2016
*/

public class FacesUtil {
	
	/** Este metodo pega a senha e gera um hash md5.
	* 	
	* 	@param senha, Esta senha � sua senha que voc~e cadastrou.
	* 	@return retorna o hash md5 da sua senha.
	*/
	public static String md5(String senha) {
	       MessageDigest m = null;
		try {
			m = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	       m.update(senha.getBytes(),0, senha.length());
	       String hash = new BigInteger(1,m.digest()).toString(16);
	       //System.out.println("MD5: "+hash);
	       
		return hash;
	}
	
	
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
	
	public static void contextFlash(){
		FacesContext fc = FacesContext.getCurrentInstance(); 
		fc.getExternalContext().getFlash().setKeepMessages(true);
	}
	
	
	public static void addErrorMessage(String message) {
		FacesContext.getCurrentInstance().addMessage(null, 
				new FacesMessage(FacesMessage.SEVERITY_ERROR, message, message));
	}
	
	public static void addInfoMessage(String message) {
		FacesContext.getCurrentInstance().addMessage("message", 
				new FacesMessage(FacesMessage.SEVERITY_INFO, "", message));
	}
	
	public Integer retornaPosicao(String nome, ArrayList<String> lista){
		    
		    for(int pos = 0; pos < lista.size(); pos++){
		         if(nome == lista.get(pos)){
		            return pos;
		         }
		     }
		    return -1; // menos um porque vc pode fazer um tratamento depois :D 
		}
	
	public String escolherRelatorio(ArrayList<String> listaObjetos){
		String relatorio = "";
		
		if (listaObjetos.size() == 0) {	
			relatorio = "/relatorios/parametros/0/Todos.jasper";
		}		
			if (listaObjetos.size() == 1) {
				//System.out.println("Parametro 1");
	            if (listaObjetos.get(0).equals("fabricante.fabricante")) {
					//relatorio = "/opt/tomcat/webapps/Deprov/resources/relatorios/parametros/1/Fabricante.jasper";
					//relatorio = "/var/lib/tomcat/webapps/Deprov/resources/relatorios/parametros/1/Fabricante.jasper";
	            	relatorio = "/relatorios/parametros/1/Fabricante.jasper";
				}else if (listaObjetos.get(0).equals("modelo.modelo")) {
					//relatorio = "/opt/tomcat/webapps/Deprov/resources/relatorios/parametros/1/Modelo.jasper";
					//relatorio = "/var/lib/tomcat/webapps/Deprov/resources/relatorios/parametros/1/Modelo.jasper";
					relatorio = "/relatorios/parametros/1/Modelo.jasper";
				}else if (listaObjetos.get(0).equals("cor.cor")) {
					//relatorio = "/opt/tomcat/webapps/Deprov/resources/relatorios/parametros/1/Cor.jasper";
					//relatorio = "/var/lib/tomcat/webapps/Deprov/resources/relatorios/parametros/1/Cor.jasper";
					relatorio = "/relatorios/parametros/1/Cor.jasper";
				}else if (listaObjetos.get(0).equals("seguro.seguro")) {
					//relatorio = "/opt/tomcat/webapps/Deprov/resources/relatorios/parametros/1/Seguro.jasper";
					//relatorio = "/var/lib/tomcat/webapps/Deprov/resources/relatorios/parametros/1/Seguro.jasper";
					relatorio = "/relatorios/parametros/1/Seguro.jasper";
				}else if (listaObjetos.get(0).equals("pericia.pericia")) {
					//relatorio = "/opt/tomcat/webapps/Deprov/resources/relatorios/parametros/1/Pericia.jasper";
					//relatorio = "/var/lib/tomcat/webapps/Deprov/resources/relatorios/parametros/1/Pericia.jasper";
					relatorio = "/relatorios/parametros/1/Pericia.jasper";
				}else if (listaObjetos.get(0).equals("situacao.situacao")) {
					//relatorio = "/opt/tomcat/webapps/Deprov/resources/relatorios/parametros/1/Situacao.jasper";
					//relatorio = "/var/lib/tomcat/webapps/Deprov/resources/relatorios/parametros/1/Situacao.jasper";
					relatorio = "/relatorios/parametros/1/Situacao.jasper";
				}else if (listaObjetos.get(0).equals("local.local")) {
					//relatorio = "/opt/tomcat/webapps/Deprov/resources/relatorios/parametros/1/Local.jasper";
					//relatorio = "/var/lib/tomcat/webapps/Deprov/resources/relatorios/parametros/1/Local.jasper";
					relatorio = "/relatorios/parametros/1/Local.jasper";
				}
	            
			}else if (listaObjetos.size() == 2) {
				//System.out.println("Parametro 2");	
				
				if (listaObjetos.get(0).equals("fabricante.fabricante")  && listaObjetos.get(1).equals("cor.cor")) {
					//relatorio = "/opt/tomcat/webapps/Deprov/resources/relatorios/parametros/2/CorFabricante.jasper";
					//relatorio = "/var/lib/tomcat/webapps/Deprov/resources/relatorios/parametros/2/CorFabricante.jasper";
					relatorio = "/relatorios/parametros/2/CorFabricante.jasper";
				}else if (listaObjetos.get(0).equals("cor.cor") && listaObjetos.get(1).equals("fabricante.fabricante")) {
					//relatorio = "/opt/tomcat/webapps/Deprov/resources/relatorios/parametros/2/CorFabricante.jasper";
					//relatorio = "/var/lib/tomcat/webapps/Deprov/resources/relatorios/parametros/2/CorFabricante.jasper";
					relatorio = "/relatorios/parametros/2/CorFabricante.jasper";
				}else if (listaObjetos.get(0).equals("modelo.modelo")  && listaObjetos.get(1).equals("cor.cor")) {
					//relatorio = "/opt/tomcat/webapps/Deprov/resources/relatorios/parametros/2/CorModelo.jasper";
					//relatorio = "/var/lib/tomcat/webapps/Deprov/resources/relatorios/parametros/2/CorModelo.jasper";
					relatorio = "/relatorios/parametros/2/CorModelo.jasper";
				}else if (listaObjetos.get(0).equals("cor.cor") && listaObjetos.get(1).equals("modelo.modelo")) {
					//relatorio = "/opt/tomcat/webapps/Deprov/resources/relatorios/parametros/2/CorModelo.jasper";
					//relatorio = "/var/lib/tomcat/webapps/Deprov/resources/relatorios/parametros/2/CorModelo.jasper";
					relatorio = "/relatorios/parametros/2/CorModelo.jasper";
				}else if (listaObjetos.get(0).equals("seguro.seguro")  && listaObjetos.get(1).equals("cor.cor")) {
					//relatorio = "/opt/tomcat/webapps/Deprov/resources/relatorios/parametros/2/CorSeguro.jasper";
					//relatorio = "/var/lib/tomcat/webapps/Deprov/resources/relatorios/parametros/2/CorSeguro.jasper";
					relatorio = "/relatorios/parametros/2/CorSeguro.jasper";
				}else if (listaObjetos.get(0).equals("cor.cor") && listaObjetos.get(1).equals("seguro.seguro")) {
					//relatorio = "/opt/tomcat/webapps/Deprov/resources/relatorios/parametros/2/CorSeguro.jasper";
					//relatorio = "/var/lib/tomcat/webapps/Deprov/resources/relatorios/parametros/2/CorSeguro.jasper";
					relatorio = "/relatorios/parametros/2/CorSeguro.jasper";
				}else if (listaObjetos.get(0).equals("pericia.pericia")  && listaObjetos.get(1).equals("cor.cor")) {
					//relatorio = "/opt/tomcat/webapps/Deprov/resources/relatorios/parametros/2/CorPericia.jasper";
					//relatorio = "/var/lib/tomcat/webapps/Deprov/resources/relatorios/parametros/2/CorPericia.jasper";
					relatorio = "/relatorios/parametros/2/CorPericia.jasper";
				}else if (listaObjetos.get(0).equals("cor.cor") && listaObjetos.get(1).equals("pericia.pericia")) {
					//relatorio = "/opt/tomcat/webapps/Deprov/resources/relatorios/parametros/2/CorPericia.jasper";
					//relatorio = "/var/lib/tomcat/webapps/Deprov/resources/relatorios/parametros/2/CorPericia.jasper";
					relatorio = "/relatorios/parametros/2/CorPericia.jasper";
				}else if (listaObjetos.get(0).equals("situacao.situacao")  && listaObjetos.get(1).equals("cor.cor")) {
					//relatorio = "/opt/tomcat/webapps/Deprov/resources/relatorios/parametros/2/CorSituacao.jasper";
					//relatorio = "/var/lib/tomcat/webapps/Deprov/resources/relatorios/parametros/2/CorSituacao.jasper";
					relatorio = "/relatorios/parametros/2/CorSituacao.jasper";
				}else if (listaObjetos.get(0).equals("cor.cor") && listaObjetos.get(1).equals("situacao.situacao")) {
					//relatorio = "/opt/tomcat/webapps/Deprov/resources/relatorios/parametros/2/CorSituacao.jasper";
					//relatorio = "/var/lib/tomcat/webapps/Deprov/resources/relatorios/parametros/2/CorSituacao.jasper";
					relatorio = "/relatorios/parametros/2/CorSituacao.jasper";
				}else if (listaObjetos.get(0).equals("local.local")  && listaObjetos.get(1).equals("cor.cor")) {
					//relatorio = "/opt/tomcat/webapps/Deprov/resources/relatorios/parametros/2/CorLocal.jasper";
					//relatorio = "/var/lib/tomcat/webapps/Deprov/resources/relatorios/parametros/2/CorLocal.jasper";
					relatorio = "/relatorios/parametros/2/CorLocal.jasper";
				}else if (listaObjetos.get(0).equals("cor.cor") && listaObjetos.get(1).equals("local.local")) {
					//relatorio = "/opt/tomcat/webapps/Deprov/resources/relatorios/parametros/2/CorLocal.jasper";
					//relatorio = "/var/lib/tomcat/webapps/Deprov/resources/relatorios/parametros/2/CorLocal.jasper";
					relatorio = "/relatorios/parametros/2/CorLocal.jasper";
				}else if (listaObjetos.get(0).equals("fabricante.fabricante")  && listaObjetos.get(1).equals("seguro.seguro")) {
					//relatorio = "/opt/tomcat/webapps/Deprov/resources/relatorios/parametros/2/FabricanteSeguro.jasper";
					//relatorio = "/var/lib/tomcat/webapps/Deprov/resources/relatorios/parametros/2/FabricanteSeguro.jasper";
					relatorio = "/relatorios/parametros/2/FabricanteSeguro.jasper";
				}else if (listaObjetos.get(0).equals("seguro.seguro") && listaObjetos.get(1).equals("fabricante.fabricante")) {
					//relatorio = "/opt/tomcat/webapps/Deprov/resources/relatorios/parametros/2/FabricanteSeguro.jasper";
					//relatorio = "/var/lib/tomcat/webapps/Deprov/resources/relatorios/parametros/2/FabricanteSeguro.jasper";
					relatorio = "/relatorios/parametros/2/FabricanteSeguro.jasper";
				}else if (listaObjetos.get(0).equals("fabricante.fabricante")  && listaObjetos.get(1).equals("pericia.pericia")) {
					//relatorio = "/opt/tomcat/webapps/Deprov/resources/relatorios/parametros/2/FabricantePericia.jasper";
					//relatorio = "/var/lib/tomcat/webapps/Deprov/resources/relatorios/parametros/2/FabricantePericia.jasper";
					relatorio = "/relatorios/parametros/2/FabricantePericia.jasper";
				}else if (listaObjetos.get(0).equals("pericia.pericia") && listaObjetos.get(1).equals("fabricante.fabricante")) {
					//relatorio = "/opt/tomcat/webapps/Deprov/resources/relatorios/parametros/2/FabricantePericia.jasper";
					//relatorio = "/var/lib/tomcat/webapps/Deprov/resources/relatorios/parametros/2/FabricantePericia.jasper";
					relatorio = "/relatorios/parametros/2/FabricantePericia.jasper";
				}else if (listaObjetos.get(0).equals("fabricante.fabricante")  && listaObjetos.get(1).equals("situacao.situacao")) {
					//relatorio = "/opt/tomcat/webapps/Deprov/resources/relatorios/parametros/2/FabricanteSituacao.jasper";
					//relatorio = "/var/lib/tomcat/webapps/Deprov/resources/relatorios/parametros/2/FabricanteSitaucao.jasper";
					relatorio = "/relatorios/parametros/2/FabricanteSitaucao.jasper";
				}else if (listaObjetos.get(0).equals("situacao.situacao") && listaObjetos.get(1).equals("fabricante.fabricante")) {
					//relatorio = "/opt/tomcat/webapps/Deprov/resources/relatorios/parametros/2/FabricanteSituacao.jasper";
					//relatorio = "/var/lib/tomcat/webapps/Deprov/resources/relatorios/parametros/2/FabricanteSituacao.jasper";
					relatorio = "/relatorios/parametros/2/FabricanteSituacao.jasper";
				}else if (listaObjetos.get(0).equals("fabricante.fabricante")  && listaObjetos.get(1).equals("local.local")) {
					//relatorio = "/opt/tomcat/webapps/Deprov/resources/relatorios/parametros/2/FabricanteLocal.jasper";
					//relatorio = "/var/lib/tomcat/webapps/Deprov/resources/relatorios/parametros/2/FabricanteLocal.jasper";
					relatorio = "/relatorios/parametros/2/FabricanteLocal.jasper";
				}else if (listaObjetos.get(0).equals("local.local") && listaObjetos.get(1).equals("fabricante.fabricante")) {
					//relatorio = "/opt/tomcat/webapps/Deprov/resources/relatorios/parametros/2/FabricanteLocal.jasper";
					//relatorio = "/var/lib/tomcat/webapps/Deprov/resources/relatorios/parametros/2/FabricanteLocal.jasper";
					relatorio = "/relatorios/parametros/2/FabricanteLocal.jasper";
				}else if (listaObjetos.get(0).equals("modelo.modelo")  && listaObjetos.get(1).equals("seguro.seguro")) {
					//relatorio = "/opt/tomcat/webapps/Deprov/resources/relatorios/parametros/2/ModeloSeguro.jasper";
					//relatorio = "/var/lib/tomcat/webapps/Deprov/resources/relatorios/parametros/2/ModeloSeguro.jasper";
					relatorio = "/relatorios/parametros/2/ModeloSeguro.jasper";
				}else if (listaObjetos.get(0).equals("seguro.seguro") && listaObjetos.get(1).equals("modelo.modelo")) {
					//relatorio = "/opt/tomcat/webapps/Deprov/resources/relatorios/parametros/2/ModeloSeguro.jasper";
					//relatorio = "/var/lib/tomcat/webapps/Deprov/resources/relatorios/parametros/2/ModeloSeguro.jasper";
					relatorio = "/relatorios/parametros/2/ModeloSeguro.jasper";
				}else if (listaObjetos.get(0).equals("modelo.modelo")  && listaObjetos.get(1).equals("pericia.pericia")) {
					//relatorio = "/opt/tomcat/webapps/Deprov/resources/relatorios/parametros/2/ModeloPericia.jasper";
					//relatorio = "/var/lib/tomcat/webapps/Deprov/resources/relatorios/parametros/2/ModeloPericia.jasper";
					relatorio = "/relatorios/parametros/2/ModeloPericia.jasper";
				}else if (listaObjetos.get(0).equals("pericia.pericia") && listaObjetos.get(1).equals("modelo.modelo")) {
					//relatorio = "/opt/tomcat/webapps/Deprov/resources/relatorios/parametros/2/ModeloPericia.jasper";
					//relatorio = "/var/lib/tomcat/webapps/Deprov/resources/relatorios/parametros/2/ModeloPericia.jasper";
					relatorio = "/relatorios/parametros/2/ModeloPericia.jasper";
				}else if (listaObjetos.get(0).equals("modelo.modelo")  && listaObjetos.get(1).equals("situacao.situacao")) {
					//relatorio = "/opt/tomcat/webapps/Deprov/resources/relatorios/parametros/2/ModeloSituacao.jasper";
					//relatorio = "/var/lib/tomcat/webapps/Deprov/resources/relatorios/parametros/2/ModeloSituacao.jasper";
					relatorio = "/relatorios/parametros/2/ModeloSituacao.jasper";
				}else if (listaObjetos.get(0).equals("situacao.situacao") && listaObjetos.get(1).equals("modelo.modelo")) {
					//relatorio = "/opt/tomcat/webapps/Deprov/resources/relatorios/parametros/2/ModeloSituacao.jasper";
					//relatorio = "/var/lib/tomcat/webapps/Deprov/resources/relatorios/parametros/2/ModeloSituacao.jasper";
					relatorio = "/relatorios/parametros/2/ModeloSituacao.jasper";
				}else if (listaObjetos.get(0).equals("modelo.modelo")  && listaObjetos.get(1).equals("local.local")) {
					//relatorio = "/opt/tomcat/webapps/Deprov/resources/relatorios/parametros/2/ModeloLocal.jasper";
					//relatorio = "/var/lib/tomcat/webapps/Deprov/resources/relatorios/parametros/2/ModeloLocal.jasper";
					relatorio = "/relatorios/parametros/2/ModeloLocal.jasper";
				}else if (listaObjetos.get(0).equals("local.local") && listaObjetos.get(1).equals("modelo.modelo")) {
					//relatorio = "/opt/tomcat/webapps/Deprov/resources/relatorios/parametros/2/ModeloLocal.jasper";
					//relatorio = "/var/lib/tomcat/webapps/Deprov/resources/relatorios/parametros/2/ModeloLocal.jasper";
					relatorio = "/relatorios/parametros/2/ModeloLocal.jasper";
				}else if (listaObjetos.get(0).equals("seguro.seguro")  && listaObjetos.get(1).equals("pericia.pericia")) {
					//relatorio = "/opt/tomcat/webapps/Deprov/resources/relatorios/parametros/2/SeguroPericia.jasper";
					//relatorio = "/var/lib/tomcat/webapps/Deprov/resources/relatorios/parametros/2/SeguroPericia.jasper";
					relatorio = "/relatorios/parametros/2/SeguroPericia.jasper";
				}else if (listaObjetos.get(0).equals("pericia.pericia") && listaObjetos.get(1).equals("seguro.segurp")) {
					//relatorio = "/opt/tomcat/webapps/Deprov/resources/relatorios/parametros/2/SeguroPericia.jasper";
					//relatorio = "/var/lib/tomcat/webapps/Deprov/resources/relatorios/parametros/2/SeguroPericia.jasper";
					relatorio = "/relatorios/parametros/2/SeguroPericia.jasper";
				}else if (listaObjetos.get(0).equals("seguro.seguro")  && listaObjetos.get(1).equals("situacao.situacao")) {
					//relatorio = "/opt/tomcat/webapps/Deprov/resources/relatorios/parametros/2/SeguroSituacao.jasper";
					//relatorio = "/var/lib/tomcat/webapps/Deprov/resources/relatorios/parametros/2/SeguroSituacao.jasper";
					relatorio = "/relatorios/parametros/2/SeguroSituacao.jasper";
				}else if (listaObjetos.get(0).equals("situacao.situacao") && listaObjetos.get(1).equals("seguro.seguro")) {
					//relatorio = "/opt/tomcat/webapps/Deprov/resources/relatorios/parametros/2/SeguroSituacao.jasper";
					//relatorio = "/var/lib/tomcat/webapps/Deprov/resources/relatorios/parametros/2/SeguroSituacao.jasper";
					relatorio = "/relatorios/parametros/2/SeguroSituacao.jasper";
				}else if (listaObjetos.get(0).equals("seguro.seguro")  && listaObjetos.get(1).equals("local.local")) {
					//relatorio = "/opt/tomcat/webapps/Deprov/resources/relatorios/parametros/2/SeguroLocal.jasper";
					//relatorio = "/var/lib/tomcat/webapps/Deprov/resources/relatorios/parametros/2/SeguroLocal.jasper"; 
					relatorio = "/relatorios/parametros/2/SeguroLocal.jasper";
				}else if (listaObjetos.get(0).equals("local.local") && listaObjetos.get(1).equals("seguro.seguro")) {
					//relatorio = "/opt/tomcat/webapps/Deprov/resources/relatorios/parametros/2/SeguroLocal.jasper";
					//relatorio = "/var/lib/tomcat/webapps/Deprov/resources/relatorios/parametros/2/SeguroLocal.jasper";
					relatorio = "/relatorios/parametros/2/SeguroLocal.jasper";
				}else if (listaObjetos.get(0).equals("situacao.situacao")  && listaObjetos.get(1).equals("local.local")) {
					//relatorio = "/opt/tomcat/webapps/Deprov/resources/relatorios/parametros/2/LocalSituacao.jasper";
					//relatorio = "/var/lib/tomcat/webapps/Deprov/resources/relatorios/parametros/2/LocalSituacao.jasper";
					relatorio = "/relatorios/parametros/2/LocalSituacao.jasper";
				}else if (listaObjetos.get(0).equals("local.local") && listaObjetos.get(1).equals("situacao.situacao")) {
					//relatorio = "/opt/tomcat/webapps/Deprov/resources/relatorios/parametros/2/LocalSituacao.jasper";
					//relatorio = "/var/lib/tomcat/webapps/Deprov/resources/relatorios/parametros/2/LocalSituacao.jasper";
					relatorio = "/relatorios/parametros/2/LocalSituacao.jasper";
				}else if (listaObjetos.get(0).equals("pericia.pericia")  && listaObjetos.get(1).equals("situacao.situacao")) {
					//relatorio = "/opt/tomcat/webapps/Deprov/resources/relatorios/parametros/2/PericiaSituacao.jasper";
					//relatorio = "/var/lib/tomcat/webapps/Deprov/resources/relatorios/parametros/2/PericiaSituacao.jasper";
					relatorio = "/relatorios/parametros/2/PericiaSituacao.jasper";
				}else if (listaObjetos.get(0).equals("situacao.situacao") && listaObjetos.get(1).equals("pericia.pericia")) {
					//relatorio = "/opt/tomcat/webapps/Deprov/resources/relatorios/parametros/2/PericiaSituacao.jasper";
					//relatorio = "/var/lib/tomcat/webapps/Deprov/resources/relatorios/parametros/2/PericiaSituacao.jasper";
					relatorio = "/relatorios/parametros/2/PericiaSituacao.jasper";
				}else if (listaObjetos.get(0).equals("pericia.pericia")  && listaObjetos.get(1).equals("local.local")) {
					//relatorio = "/opt/tomcat/webapps/Deprov/resources/relatorios/parametros/2/PericiaLocal.jasper";
					//relatorio = "/var/lib/tomcat/webapps/Deprov/resources/relatorios/parametros/2/PericiaLocal.jasper"; 
					relatorio = "/relatorios/parametros/2/PericiaLocal.jasper";
				}else if (listaObjetos.get(0).equals("local.local") && listaObjetos.get(1).equals("pericia.pericia")) {
					//relatorio = "/opt/tomcat/webapps/Deprov/resources/relatorios/parametros/2/PericiaLocal.jasper";
					//relatorio = "/var/lib/tomcat/webapps/Deprov/resources/relatorios/parametros/2/PericiaLocal.jasper";
					relatorio = "/relatorios/parametros/2/PericiaLocal.jasper";
				}
			}
		//}
		return relatorio;
	}	
	
	public Map<String, Object> retornarParametros(ArrayList<String> listaObjetos, ArrayList<String> listavalores){
		String parametro = "";
		Map<String, Object> mapaParametro = new HashMap<String, Object>();
		int valor = 0;
		
		//for (int i = 0; i < listaObjetos.size(); i++) {
			//System.out.println("Lista: "+ listaObjetos.get(i));
		
			if (listaObjetos.size() == 1) {
				//System.out.println("Parametro 1");
	            if (listaObjetos.get(0).equals("fabricante.fabricante")) {
	            	Fabricante fabricante = new Fabricante();
	        		IFabricante fabricantes = new Fabricantes();
	        		
	        		fabricante = fabricantes.pegaCodigo(listavalores.get(0));
	            	
	        		valor = fabricante.getCodigo();
	            	parametro = "codigo_fabricante";
	            	
	            	mapaParametro.put(parametro, valor);
				}else if (listaObjetos.get(0).equals("modelo.modelo")) {
					
					Modelo modelo = new Modelo();
					IModelo modelos = new Modelos();
					
					modelo = modelos.pegaCodigo(listavalores.get(0));
					
					valor = modelo.getCodigo();
	            	parametro = "codigo_modelo";
	            	
	            	mapaParametro.put(parametro, valor);
					
				}else if (listaObjetos.get(0).equals("cor.cor")) {
					Cor cor = new Cor();
					ICor cores = new Cores();
					System.out.println("Codigo Cor: " + listavalores.get(0));
					
					cor = cores.pegaCodigo(listavalores.get(0));
					
					valor = cor.getCodigo();
	            	parametro = "codigo_cor";
	            	
	            	mapaParametro.put(parametro, valor);		
					
				}else if (listaObjetos.get(0).equals("seguro.seguro")) {
					Seguro seguro = new Seguro();
					ISeguro seguros = new Seguros();
					seguro = seguros.pegaCodigo(listavalores.get(0));
					
					valor = seguro.getCodigo();
	            	parametro = "codigo_seguro";
	            	
	            	mapaParametro.put(parametro, valor);
	            	
				}else if (listaObjetos.get(0).equals("pericia.pericia")) {
					Pericia pericia = new Pericia();
					IPericia pericias = new Pericias();
					pericia = pericias.pegaCodigo(listavalores.get(0));
					
					valor = pericia.getCodigo();
	            	parametro = "codigo_pericia";
	            	
	            	mapaParametro.put(parametro, valor);
					
				}else if (listaObjetos.get(0).equals("situacao.situacao")) {
					Situacao situacao = new Situacao();
					ISituacao ISituacao = new Situacoes();
					situacao = ISituacao.pegaCodigo(listavalores.get(0));
					
					valor = situacao.getCodigo();
	            	parametro = "codigo_situacao";
	            	
	            	mapaParametro.put(parametro, valor);	            	
				
				}else if (listaObjetos.get(0).equals("local.local")) {
					Local local = new Local();
					ILocal Ilocal = new Locais();
					
					
					local = Ilocal.pegaCodigo(listavalores.get(0));
					
					valor = local.getCodigo();
	            	parametro = "codigo_local";
	            	
	            	mapaParametro.put(parametro, valor);	            	
				}
	            
			}else if (listaObjetos.size() == 2) {
				//System.out.println("Parametro 2");
				if (listaObjetos.get(0).equals("fabricante.fabricante")  && listaObjetos.get(1).equals("cor.cor")) {
					Fabricante fabricante = new Fabricante();
	        		IFabricante fabricantes = new Fabricantes();
	        		fabricante = fabricantes.pegaCodigo(listavalores.get(0));
	            	
	        		valor = fabricante.getCodigo();
	            	parametro = "codigo_fabricante";
	            	
	            	mapaParametro.put(parametro, valor);
	            	
	            	Cor cor = new Cor();
					ICor cores = new Cores();
					cor = cores.pegaCodigo(listavalores.get(1));
					
					valor = cor.getCodigo();
	            	parametro = "codigo_cor";
	            	
	            	mapaParametro.put(parametro, valor);
					
					
				}else if (listaObjetos.get(0).equals("cor.cor") && listaObjetos.get(1).equals("fabricante.fabricante")) {
					Cor cor = new Cor();
					ICor cores = new Cores();
					cor = cores.pegaCodigo(listavalores.get(0));
					
					valor = cor.getCodigo();
	            	parametro = "codigo_cor";
	            	
	            	mapaParametro.put(parametro, valor);
	            	
	            	Fabricante fabricante = new Fabricante();
	        		IFabricante fabricantes = new Fabricantes();
	        		fabricante = fabricantes.pegaCodigo(listavalores.get(1));
	            	
	        		valor = fabricante.getCodigo();
	            	parametro = "codigo_fabricante";
	            	
	            	mapaParametro.put(parametro, valor);
					
				}else if (listaObjetos.get(0).equals("modelo.modelo")  && listaObjetos.get(1).equals("cor.cor")) {
					Modelo modelo = new Modelo();
					IModelo modelos = new Modelos();
					modelo = modelos.pegaCodigo(listavalores.get(0));
					
					valor = modelo.getCodigo();
	            	parametro = "codigo_modelo";
	            	
	            	mapaParametro.put(parametro, valor);
		            		        	           	
		            Cor cor = new Cor();
					ICor cores = new Cores();
					cor = cores.pegaCodigo(listavalores.get(1));
						
					valor = cor.getCodigo();
		            parametro = "codigo_cor";
		            	
		            mapaParametro.put(parametro, valor);
						
				}else if (listaObjetos.get(0).equals("cor.cor") && listaObjetos.get(1).equals("modelo.modelo")) {
					Cor cor = new Cor();
					ICor cores = new Cores();
					cor = cores.pegaCodigo(listavalores.get(0));
						
					valor = cor.getCodigo();
		            parametro = "codigo_cor";
		            	
		            mapaParametro.put(parametro, valor);
					
					Modelo modelo = new Modelo();
					IModelo modelos = new Modelos();
					modelo = modelos.pegaCodigo(listavalores.get(1));
					
					valor = modelo.getCodigo();
	            	parametro = "codigo_modelo";
	            	
	            	mapaParametro.put(parametro, valor);
		            
				}else if (listaObjetos.get(0).equals("seguro.seguro")  && listaObjetos.get(1).equals("cor.cor"))  {
					Seguro seguro = new Seguro();
					ISeguro seguros = new Seguros();
					seguro = seguros.pegaCodigo(listavalores.get(0));
					
					valor = seguro.getCodigo();
	            	parametro = "codigo_seguro";
	            	
	            	mapaParametro.put(parametro, valor);
					
	            	Cor cor = new Cor();
					ICor cores = new Cores();
					cor = cores.pegaCodigo(listavalores.get(1));
						
					valor = cor.getCodigo();
		            parametro = "codigo_cor";
		            	
		            mapaParametro.put(parametro, valor);
					
				}else if (listaObjetos.get(0).equals("cor.cor") && listaObjetos.get(1).equals("seguro.seguro")) {
					Cor cor = new Cor();
					ICor cores = new Cores();
					cor = cores.pegaCodigo(listavalores.get(0));
						
					valor = cor.getCodigo();
		            parametro = "codigo_cor";
		            	
		            mapaParametro.put(parametro, valor);
					
		            Seguro seguro = new Seguro();
					ISeguro seguros = new Seguros();
					seguro = seguros.pegaCodigo(listavalores.get(1));
					
					valor = seguro.getCodigo();
	            	parametro = "codigo_seguro";
	            	
	            	mapaParametro.put(parametro, valor);
					
				}else if (listaObjetos.get(0).equals("cor.cor") && listaObjetos.get(1).equals("pericia.pericia")) {
					Cor cor = new Cor();
					ICor cores = new Cores();
					cor = cores.pegaCodigo(listavalores.get(0));
						
					valor = cor.getCodigo();
		            parametro = "codigo_cor";
		            	
		            mapaParametro.put(parametro, valor);
					
		            Pericia pericia = new Pericia();
					IPericia pericias = new Pericias();
					pericia = pericias.pegaCodigo(listavalores.get(1));
					
					valor = pericia.getCodigo();
	            	parametro = "codigo_pericia";
	            	
	            	mapaParametro.put(parametro, valor);
		            
				}else if (listaObjetos.get(0).equals("pericia.pericia") && listaObjetos.get(1).equals("cor.cor")) {	
					Pericia pericia = new Pericia();
					IPericia pericias = new Pericias();
					pericia = pericias.pegaCodigo(listavalores.get(0));
					
					valor = pericia.getCodigo();
	            	parametro = "codigo_pericia";
	            	
	            	mapaParametro.put(parametro, valor);
					
	            	Cor cor = new Cor();
					ICor cores = new Cores();
					cor = cores.pegaCodigo(listavalores.get(1));
						
					valor = cor.getCodigo();
		            parametro = "codigo_cor";
		            	
		            mapaParametro.put(parametro, valor);
					
				}else if (listaObjetos.get(0).equals("cor.cor") && listaObjetos.get(1).equals("situacao.situacao")) {
					Cor cor = new Cor();
					ICor cores = new Cores();
					cor = cores.pegaCodigo(listavalores.get(0));
						
					valor = cor.getCodigo();
		            parametro = "codigo_cor";
		            	
		            mapaParametro.put(parametro, valor);
		            
		            Situacao situacao = new Situacao();
					ISituacao ISituacao = new Situacoes();
					situacao = ISituacao.pegaCodigo(listavalores.get(1));
					
					valor = situacao.getCodigo();
	            	parametro = "codigo_situacao";
	            	
	            	mapaParametro.put(parametro, valor);		            
		            
				}else if (listaObjetos.get(0).equals("situacao.situacao") && listaObjetos.get(1).equals("cor.cor")) {
					Situacao situacao = new Situacao();
					ISituacao ISituacao = new Situacoes();
					situacao = ISituacao.pegaCodigo(listavalores.get(0));
					
					valor = situacao.getCodigo();
	            	parametro = "codigo_situacao";
	            	
	            	mapaParametro.put(parametro, valor);
	            	
	            	Cor cor = new Cor();
					ICor cores = new Cores();
					cor = cores.pegaCodigo(listavalores.get(1));
						
					valor = cor.getCodigo();
		            parametro = "codigo_cor";
		            	
		            mapaParametro.put(parametro, valor);
	            	
				}else if (listaObjetos.get(0).equals("local.local")  && listaObjetos.get(1).equals("cor.cor")) {
					Local local = new Local();
					ILocal Ilocal = new Locais();
					
					
					local = Ilocal.pegaCodigo(listavalores.get(0));
					
					valor = local.getCodigo();
	            	parametro = "codigo_local";
	            	
	            	mapaParametro.put(parametro, valor);
		            		        	           	
		            Cor cor = new Cor();
					ICor cores = new Cores();
					cor = cores.pegaCodigo(listavalores.get(1));
						
					valor = cor.getCodigo();
		            parametro = "codigo_cor";
		            	
		            mapaParametro.put(parametro, valor);
						
				}else if (listaObjetos.get(0).equals("cor.cor") && listaObjetos.get(1).equals("local.local")) {
					Cor cor = new Cor();
					ICor cores = new Cores();
					cor = cores.pegaCodigo(listavalores.get(0));
						
					valor = cor.getCodigo();
		            parametro = "codigo_cor";
		            	
		            mapaParametro.put(parametro, valor);
					
		            Local local = new Local();
					ILocal Ilocal = new Locais();
					
					
					local = Ilocal.pegaCodigo(listavalores.get(1));
					
					valor = local.getCodigo();
	            	parametro = "codigo_local";
	            	
	            	mapaParametro.put(parametro, valor);
		            
				}else if (listaObjetos.get(0).equals("fabricante.fabricante")  && listaObjetos.get(1).equals("seguro.seguro")) {
					Fabricante fabricante = new Fabricante();
	        		IFabricante fabricantes = new Fabricantes();
	        		fabricante = fabricantes.pegaCodigo(listavalores.get(0));
	            	
	        		valor = fabricante.getCodigo();
	            	parametro = "codigo_fabricante";
	            	
	            	mapaParametro.put(parametro, valor); 
					
					Seguro seguro = new Seguro();
					ISeguro seguros = new Seguros();
					seguro = seguros.pegaCodigo(listavalores.get(1));
					
					valor = seguro.getCodigo();
	            	parametro = "codigo_seguro";
	            	
	            	mapaParametro.put(parametro, valor);
					
				}else if (listaObjetos.get(0).equals("seguro.seguro") && listaObjetos.get(1).equals("fabricante.fabricante")) {
					Seguro seguro = new Seguro();
					ISeguro seguros = new Seguros();
					seguro = seguros.pegaCodigo(listavalores.get(0));
					
					valor = seguro.getCodigo();
	            	parametro = "codigo_seguro";
	            	
	            	mapaParametro.put(parametro, valor);
	            	
	            	Fabricante fabricante = new Fabricante();
	        		IFabricante fabricantes = new Fabricantes();
	        		fabricante = fabricantes.pegaCodigo(listavalores.get(1));
	            	
	        		valor = fabricante.getCodigo();
	            	parametro = "codigo_fabricante";
	            	
	            	mapaParametro.put(parametro, valor);
				}else if (listaObjetos.get(0).equals("fabricante.fabricante")  && listaObjetos.get(1).equals("pericia.pericia")) {
					Fabricante fabricante = new Fabricante();
	        		IFabricante fabricantes = new Fabricantes();
	        		fabricante = fabricantes.pegaCodigo(listavalores.get(0));
	            	
	        		valor = fabricante.getCodigo();
	            	parametro = "codigo_fabricante";
	            	
	            	mapaParametro.put(parametro, valor); 
					
	            	Pericia pericia = new Pericia();
					IPericia pericias = new Pericias();
					pericia = pericias.pegaCodigo(listavalores.get(1));
					
					valor = pericia.getCodigo();
	            	parametro = "codigo_pericia";
	            	
	            	mapaParametro.put(parametro, valor);
					
				}else if (listaObjetos.get(0).equals("pericia.pericia") && listaObjetos.get(1).equals("fabricante.fabricante")) {
					Pericia pericia = new Pericia();
					IPericia pericias = new Pericias();
					pericia = pericias.pegaCodigo(listavalores.get(0));
					
					valor = pericia.getCodigo();
	            	parametro = "codigo_pericia";
	            	
	            	mapaParametro.put(parametro, valor);
	            	
	            	Fabricante fabricante = new Fabricante();
	        		IFabricante fabricantes = new Fabricantes();
	        		fabricante = fabricantes.pegaCodigo(listavalores.get(1));
	            	
	        		valor = fabricante.getCodigo();
	            	parametro = "codigo_fabricante";
	            	
	            	mapaParametro.put(parametro, valor);
				}else if (listaObjetos.get(0).equals("fabricante.fabricante")  && listaObjetos.get(1).equals("situacao.situacao")) {
					Fabricante fabricante = new Fabricante();
	        		IFabricante fabricantes = new Fabricantes();
	        		fabricante = fabricantes.pegaCodigo(listavalores.get(0));
	            	
	        		valor = fabricante.getCodigo();
	            	parametro = "codigo_fabricante";
	            	
	            	mapaParametro.put(parametro, valor); 
					
	            	Situacao situacao = new Situacao();
					ISituacao ISituacao = new Situacoes();
					situacao = ISituacao.pegaCodigo(listavalores.get(1));
					
					valor = situacao.getCodigo();
	            	parametro = "codigo_situacao";
	            	
	            	mapaParametro.put(parametro, valor);					
					
				}else if (listaObjetos.get(0).equals("situacao.situacao") && listaObjetos.get(1).equals("fabricante.fabricante")) {
					Situacao situacao = new Situacao();
					ISituacao ISituacao = new Situacoes();
					situacao = ISituacao.pegaCodigo(listavalores.get(0));
					
					valor = situacao.getCodigo();
	            	parametro = "codigo_situacao";
	            	
	            	mapaParametro.put(parametro, valor);					
					
					Fabricante fabricante = new Fabricante();
	        		IFabricante fabricantes = new Fabricantes();
	        		fabricante = fabricantes.pegaCodigo(listavalores.get(1));
	            	
	        		valor = fabricante.getCodigo();
	            	parametro = "codigo_fabricante";
	            	
	            	mapaParametro.put(parametro, valor);
					
				}else if (listaObjetos.get(0).equals("fabricante.fabricante")  && listaObjetos.get(1).equals("local.local")) {
					Fabricante fabricante = new Fabricante();
	        		IFabricante fabricantes = new Fabricantes();
	        		fabricante = fabricantes.pegaCodigo(listavalores.get(0));
	            	
	        		valor = fabricante.getCodigo();
	            	parametro = "codigo_fabricante";
	            	
	            	mapaParametro.put(parametro, valor);
	            	
	            	Local local = new Local();
					ILocal Ilocal = new Locais();
					
					
					local = Ilocal.pegaCodigo(listavalores.get(1));
					
					valor = local.getCodigo();
	            	parametro = "codigo_local";
	            	
	            	mapaParametro.put(parametro, valor);	
					
				}else if (listaObjetos.get(0).equals("local.local") && listaObjetos.get(1).equals("fabricante.fabricante")) {
					Local local = new Local();
					ILocal Ilocal = new Locais();
					
					local = Ilocal.pegaCodigo(listavalores.get(0));
					
					valor = local.getCodigo();
	            	parametro = "codigo_local";
	            	
	            	mapaParametro.put(parametro, valor);
	            	
	            	Fabricante fabricante = new Fabricante();
	        		IFabricante fabricantes = new Fabricantes();
	        		fabricante = fabricantes.pegaCodigo(listavalores.get(1));
	            	
	        		valor = fabricante.getCodigo();
	            	parametro = "codigo_fabricante";
	            	
	            	mapaParametro.put(parametro, valor);
					
				}else if (listaObjetos.get(0).equals("modelo.modelo")  && listaObjetos.get(1).equals("seguro.seguro")) {
					Modelo modelo = new Modelo();
					IModelo modelos = new Modelos();
					modelo = modelos.pegaCodigo(listavalores.get(0));
					
					valor = modelo.getCodigo();
	            	parametro = "codigo_modelo";
	            	
	            	mapaParametro.put(parametro, valor); 
					
					Seguro seguro = new Seguro();
					ISeguro seguros = new Seguros();
					seguro = seguros.pegaCodigo(listavalores.get(1));
					
					valor = seguro.getCodigo();
	            	parametro = "codigo_seguro";
	            	
	            	mapaParametro.put(parametro, valor);
					
				}else if (listaObjetos.get(0).equals("seguro.seguro") && listaObjetos.get(1).equals("modelo.modelo")) {
					Seguro seguro = new Seguro();
					ISeguro seguros = new Seguros();
					seguro = seguros.pegaCodigo(listavalores.get(0));
					
					valor = seguro.getCodigo();
	            	parametro = "codigo_seguro";
	            	
	            	mapaParametro.put(parametro, valor);
	            	
	            	Modelo modelo = new Modelo();
					IModelo modelos = new Modelos();
					modelo = modelos.pegaCodigo(listavalores.get(1));
					
					valor = modelo.getCodigo();
	            	parametro = "codigo_modelo";
	            	
	            	mapaParametro.put(parametro, valor);
	            	
				}else if (listaObjetos.get(0).equals("modelo.modelo")  && listaObjetos.get(1).equals("pericia.pericia")) {
					Modelo modelo = new Modelo();
					IModelo modelos = new Modelos();
					modelo = modelos.pegaCodigo(listavalores.get(0));
					
					valor = modelo.getCodigo();
	            	parametro = "codigo_modelo";
	            	
	            	mapaParametro.put(parametro, valor); 
					
	            	Pericia pericia = new Pericia();
					IPericia pericias = new Pericias();
					pericia = pericias.pegaCodigo(listavalores.get(1));
					
					valor = pericia.getCodigo();
	            	parametro = "codigo_pericia";
	            	
	            	mapaParametro.put(parametro, valor);
					
				}else if (listaObjetos.get(0).equals("pericia.pericia") && listaObjetos.get(1).equals("modelo.modelo")) {
					Pericia pericia = new Pericia();
					IPericia pericias = new Pericias();
					pericia = pericias.pegaCodigo(listavalores.get(0));
					
					valor = pericia.getCodigo();
	            	parametro = "codigo_pericia";
	            	
	            	mapaParametro.put(parametro, valor);
	            	
	            	Modelo modelo = new Modelo();
					IModelo modelos = new Modelos();
					modelo = modelos.pegaCodigo(listavalores.get(1));
					
					valor = modelo.getCodigo();
	            	parametro = "codigo_modelo";
	            	
	            	mapaParametro.put(parametro, valor);
	            	
				}else if (listaObjetos.get(0).equals("modelo.modelo")  && listaObjetos.get(1).equals("situacao.situacao")) {
					Modelo modelo = new Modelo();
					IModelo modelos = new Modelos();
					modelo = modelos.pegaCodigo(listavalores.get(0));
					
					valor = modelo.getCodigo();
	            	parametro = "codigo_modelo";
	            	
	            	mapaParametro.put(parametro, valor); 
					
	            	Situacao situacao = new Situacao();
					ISituacao ISituacao = new Situacoes();
					situacao = ISituacao.pegaCodigo(listavalores.get(1));
					
					valor = situacao.getCodigo();
	            	parametro = "codigo_situacao";
	            	
	            	mapaParametro.put(parametro, valor);					
					
				}else if (listaObjetos.get(0).equals("situacao.situacao") && listaObjetos.get(1).equals("modelo.modelo")) {
					Situacao situacao = new Situacao();
					ISituacao ISituacao = new Situacoes();
					situacao = ISituacao.pegaCodigo(listavalores.get(0));
					
					valor = situacao.getCodigo();
	            	parametro = "codigo_situacao";
	            	
	            	mapaParametro.put(parametro, valor);					
					
	            	Modelo modelo = new Modelo();
					IModelo modelos = new Modelos();
					modelo = modelos.pegaCodigo(listavalores.get(1));
					
					valor = modelo.getCodigo();
	            	parametro = "codigo_modelo";
	            	
	            	mapaParametro.put(parametro, valor);
					
				}else if (listaObjetos.get(0).equals("modelo.modelo")  && listaObjetos.get(1).equals("local.local")) {
					Modelo modelo = new Modelo();
					IModelo modelos = new Modelos();
					modelo = modelos.pegaCodigo(listavalores.get(0));
					
					valor = modelo.getCodigo();
	            	parametro = "codigo_modelo";
	            	
	            	mapaParametro.put(parametro, valor); 
					
	            	Local local = new Local();
					ILocal Ilocal = new Locais();
					
					local = Ilocal.pegaCodigo(listavalores.get(1));
					
					valor = local.getCodigo();
	            	parametro = "codigo_local";
	            	
	            	mapaParametro.put(parametro, valor);
					
				}else if (listaObjetos.get(0).equals("local.local") && listaObjetos.get(1).equals("modelo.modelo")) {
					Local local = new Local();
					ILocal Ilocal = new Locais();
					
					local = Ilocal.pegaCodigo(listavalores.get(0));
					
					valor = local.getCodigo();
	            	parametro = "codigo_local";
	            	
	            	mapaParametro.put(parametro, valor);
	            	
	            	Modelo modelo = new Modelo();
					IModelo modelos = new Modelos();
					modelo = modelos.pegaCodigo(listavalores.get(1));
					
					valor = modelo.getCodigo();
	            	parametro = "codigo_modelo";
	            	
	            	mapaParametro.put(parametro, valor);
	            	
				}else if (listaObjetos.get(0).equals("seguro.seguro")  && listaObjetos.get(1).equals("pericia.pericia")) {
					Seguro seguro = new Seguro();
					ISeguro seguros = new Seguros();
					seguro = seguros.pegaCodigo(listavalores.get(0));
					
					valor = seguro.getCodigo();
	            	parametro = "codigo_seguro";
	            	
	            	mapaParametro.put(parametro, valor);
					
					Pericia pericia = new Pericia();
					IPericia pericias = new Pericias();
					pericia = pericias.pegaCodigo(listavalores.get(1));
					
					valor = pericia.getCodigo();
	            	parametro = "codigo_pericia";
	            	
	            	mapaParametro.put(parametro, valor);
					
				}else if (listaObjetos.get(0).equals("pericia.pericia") && listaObjetos.get(1).equals("seguro.seguro")) {
					Pericia pericia = new Pericia();
					IPericia pericias = new Pericias();
					pericia = pericias.pegaCodigo(listavalores.get(0));
					
					valor = pericia.getCodigo();
	            	parametro = "codigo_pericia";
	            	
	            	mapaParametro.put(parametro, valor);
					
	            	Seguro seguro = new Seguro();
					ISeguro seguros = new Seguros();
					seguro = seguros.pegaCodigo(listavalores.get(1));
					
					valor = seguro.getCodigo();
	            	parametro = "codigo_seguro";
	            	
	            	mapaParametro.put(parametro, valor);
	            	
				}else if (listaObjetos.get(0).equals("seguro.seguro")  && listaObjetos.get(1).equals("situacao.situacao")) {
					Seguro seguro = new Seguro();
					ISeguro seguros = new Seguros();
					seguro = seguros.pegaCodigo(listavalores.get(0));
					
					valor = seguro.getCodigo();
	            	parametro = "codigo_seguro";
	            	
	            	mapaParametro.put(parametro, valor);
					
					Situacao situacao = new Situacao();
					ISituacao ISituacao = new Situacoes();
					situacao = ISituacao.pegaCodigo(listavalores.get(1));
					
					valor = situacao.getCodigo();
	            	parametro = "codigo_situacao";
	            	
	            	mapaParametro.put(parametro, valor);
					
				}else if (listaObjetos.get(0).equals("situacao.situacao") && listaObjetos.get(1).equals("seguro.seguro")) {
					Situacao situacao = new Situacao();
					ISituacao ISituacao = new Situacoes();
					situacao = ISituacao.pegaCodigo(listavalores.get(0));
					
					valor = situacao.getCodigo();
	            	parametro = "codigo_situacao";
	            	
	            	mapaParametro.put(parametro, valor);
					
	            	Seguro seguro = new Seguro();
					ISeguro seguros = new Seguros();
					seguro = seguros.pegaCodigo(listavalores.get(1));
					
					valor = seguro.getCodigo();
	            	parametro = "codigo_seguro";
	            	
	            	mapaParametro.put(parametro, valor);
	            	
				}else if (listaObjetos.get(0).equals("seguro.seguro")  && listaObjetos.get(1).equals("local.local")) {
					Seguro seguro = new Seguro();
					ISeguro seguros = new Seguros();
					seguro = seguros.pegaCodigo(listavalores.get(0));
					
					valor = seguro.getCodigo();
	            	parametro = "codigo_seguro";
	            	
	            	mapaParametro.put(parametro, valor);
					
	            	Local local = new Local();
					ILocal Ilocal = new Locais();
					
					local = Ilocal.pegaCodigo(listavalores.get(1));
					
					valor = local.getCodigo();
	            	parametro = "codigo_local";
	            	
	            	mapaParametro.put(parametro, valor);
					
				}else if (listaObjetos.get(0).equals("local.local") && listaObjetos.get(1).equals("seguro.seguro")) {
					Local local = new Local();
					ILocal Ilocal = new Locais();
					
					local = Ilocal.pegaCodigo(listavalores.get(0));
					
					valor = local.getCodigo();
	            	parametro = "codigo_local";
	            	
	            	mapaParametro.put(parametro, valor);
					
	            	Seguro seguro = new Seguro();
					ISeguro seguros = new Seguros();
					seguro = seguros.pegaCodigo(listavalores.get(1));
					
					valor = seguro.getCodigo();
	            	parametro = "codigo_seguro";
	            	
	            	mapaParametro.put(parametro, valor);
				}else if (listaObjetos.get(0).equals("pericia.pericia")  && listaObjetos.get(1).equals("situacao.situacao")) {
					Pericia pericia = new Pericia();
					IPericia pericias = new Pericias();
					pericia = pericias.pegaCodigo(listavalores.get(0));
					
					valor = pericia.getCodigo();
	            	parametro = "codigo_pericia";
	            	
	            	mapaParametro.put(parametro, valor);
					
					Situacao situacao = new Situacao();
					ISituacao ISituacao = new Situacoes();
					situacao = ISituacao.pegaCodigo(listavalores.get(1));
					
					valor = situacao.getCodigo();
	            	parametro = "codigo_situacao";
	            	
	            	mapaParametro.put(parametro, valor);
					
				}else if (listaObjetos.get(0).equals("situacao.situacao") && listaObjetos.get(1).equals("pericia.pericia")) {
					Situacao situacao = new Situacao();
					ISituacao ISituacao = new Situacoes();
					situacao = ISituacao.pegaCodigo(listavalores.get(0));
					
					valor = situacao.getCodigo();
	            	parametro = "codigo_situacao";
	            	
	            	mapaParametro.put(parametro, valor);
					
	            	Pericia pericia = new Pericia();
					IPericia pericias = new Pericias();
					pericia = pericias.pegaCodigo(listavalores.get(1));
					
					valor = pericia.getCodigo();
	            	parametro = "codigo_pericia";
	            	
	            	mapaParametro.put(parametro, valor);
	            	
				}else if (listaObjetos.get(0).equals("pericia.pericia")  && listaObjetos.get(1).equals("local.local")) {
					Pericia pericia = new Pericia();
					IPericia pericias = new Pericias();
					pericia = pericias.pegaCodigo(listavalores.get(0));
					
					valor = pericia.getCodigo();
	            	parametro = "codigo_pericia";
	            	
	            	mapaParametro.put(parametro, valor);
					
					
	            	Local local = new Local();
					ILocal Ilocal = new Locais();
					
					local = Ilocal.pegaCodigo(listavalores.get(1));
					
					valor = local.getCodigo();
	            	parametro = "codigo_local";
	            	
	            	mapaParametro.put(parametro, valor);
					
				}else if (listaObjetos.get(0).equals("local.local") && listaObjetos.get(1).equals("pericia.pericia")) {
					Local local = new Local();
					ILocal Ilocal = new Locais();
					
					local = Ilocal.pegaCodigo(listavalores.get(0));
					
					valor = local.getCodigo();
	            	parametro = "codigo_local";
	            	
	            	mapaParametro.put(parametro, valor);
					
	            	Pericia pericia = new Pericia();
					IPericia pericias = new Pericias();
					pericia = pericias.pegaCodigo(listavalores.get(1));
					
					valor = pericia.getCodigo();
	            	parametro = "codigo_pericia";
	            	
	            	mapaParametro.put(parametro, valor);
	            	
				}else if (listaObjetos.get(0).equals("situacao.situacao")  && listaObjetos.get(1).equals("local.local")) {
					Situacao situacao = new Situacao();
					ISituacao ISituacao = new Situacoes();
					situacao = ISituacao.pegaCodigo(listavalores.get(0));
					
					valor = situacao.getCodigo();
	            	parametro = "codigo_situacao";
	            	
	            	mapaParametro.put(parametro, valor);
					
					
	            	Local local = new Local();
					ILocal Ilocal = new Locais();
					
					local = Ilocal.pegaCodigo(listavalores.get(1));
					
					valor = local.getCodigo();
	            	parametro = "codigo_local";
	            	
	            	mapaParametro.put(parametro, valor);
					
				}else if (listaObjetos.get(0).equals("local.local") && listaObjetos.get(1).equals("situacao.situacao")) {
					Local local = new Local();
					ILocal Ilocal = new Locais();
					
					local = Ilocal.pegaCodigo(listavalores.get(0));
					
					valor = local.getCodigo();
	            	parametro = "codigo_local";
	            	
	            	mapaParametro.put(parametro, valor);
					
	            	Situacao situacao = new Situacao();
					ISituacao ISituacao = new Situacoes();
					situacao = ISituacao.pegaCodigo(listavalores.get(1));
					
					valor = situacao.getCodigo();
	            	parametro = "codigo_situacao";
	            	
	            	mapaParametro.put(parametro, valor);
					}
				}
		//}
		return mapaParametro;
	}	
}