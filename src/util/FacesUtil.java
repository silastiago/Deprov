package util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import model.Cor;
import model.Fabricante;
import model.Local;
import model.Modelo;
import model.Pericia;
import model.Seguro;
import model.Situacao;
import repository.Cores;
import repository.Fabricantes;
import repository.ILocal;
import repository.ISituacao;
import repository.Modelos;
import repository.Pericias;
import repository.Seguros;


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
		
		//for (int i = 0; i < listaObjetos.size(); i++) {
			//System.out.println("Lista: "+ listaObjetos.get(i));
		
			if (listaObjetos.size() == 1) {
				//System.out.println("Parametro 1");
	            if (listaObjetos.get(0).equals("fabricante.fabricante")) {
					//relatorio = "/opt/tomcat/webapps/Deprov/resources/relatorios/parametros/1/Fabricante.jrxml";
					relatorio = "/var/lib/tomcat/webapps/Deprov/resources/relatorios/parametros/1/Fabricante.jrxml";
				}else if (listaObjetos.get(0).equals("modelo.modelo")) {
					//relatorio = "/opt/tomcat/webapps/Deprov/resources/relatorios/parametros/1/Modelo.jrxml";
					relatorio = "/var/lib/tomcat/webapps/Deprov/resources/relatorios/parametros/1/Modelo.jrxml";
				}else if (listaObjetos.get(0).equals("cor.cor")) {
					//relatorio = "/opt/tomcat/webapps/Deprov/resources/relatorios/parametros/1/Cor.jrxml";
					relatorio = "/var/lib/tomcat/webapps/Deprov/resources/relatorios/parametros/1/Cor.jrxml";
				}else if (listaObjetos.get(0).equals("seguro.seguro")) {
					//relatorio = "/opt/tomcat/webapps/Deprov/resources/relatorios/parametros/1/Seguro.jrxml";
					relatorio = "/var/lib/tomcat/webapps/Deprov/resources/relatorios/parametros/1/Seguro.jrxml";
				}else if (listaObjetos.get(0).equals("pericia.pericia")) {
					//relatorio = "/opt/tomcat/webapps/Deprov/resources/relatorios/parametros/1/Pericia.jrxml";
					relatorio = "/var/lib/tomcat/webapps/Deprov/resources/relatorios/parametros/1/Pericia.jrxml";
				}else if (listaObjetos.get(0).equals("situacao.situacao")) {
					//relatorio = "/opt/tomcat/webapps/Deprov/resources/relatorios/parametros/1/Situacao.jrxml";
					relatorio = "/var/lib/tomcat/webapps/Deprov/resources/relatorios/parametros/1/Situacao.jrxml";
				}else if (listaObjetos.get(0).equals("local.local")) {
					//relatorio = "/opt/tomcat/webapps/Deprov/resources/relatorios/parametros/1/Local.jrxml";
					relatorio = "/var/lib/tomcat/webapps/Deprov/resources/relatorios/parametros/1/Local.jrxml";
				}
	            
			}else if (listaObjetos.size() == 2) {
				//System.out.println("Parametro 2");	
				
				if (listaObjetos.get(0).equals("fabricante.fabricante")  && listaObjetos.get(1).equals("cor.cor")) {
					//relatorio = "/opt/tomcat/webapps/Deprov/resources/relatorios/parametros/2/CorFabricante.jrxml";
					relatorio = "/var/lib/tomcat/webapps/Deprov/resources/relatorios/parametros/2/CorFabricante.jrxml";
				}else if (listaObjetos.get(0).equals("cor.cor") && listaObjetos.get(1).equals("fabricante.fabricante")) {
					//relatorio = "/opt/tomcat/webapps/Deprov/resources/relatorios/parametros/2/CorFabricante.jrxml";
					relatorio = "/var/lib/tomcat/webapps/Deprov/resources/relatorios/parametros/2/CorFabricante.jrxml";
				}else if (listaObjetos.get(0).equals("modelo.modelo")  && listaObjetos.get(1).equals("cor.cor")) {
					//relatorio = "/opt/tomcat/webapps/Deprov/resources/relatorios/parametros/2/CorModelo.jrxml";
					relatorio = "/var/lib/tomcat/webapps/Deprov/resources/relatorios/parametros/2/CorModelo.jrxml";
				}else if (listaObjetos.get(0).equals("cor.cor") && listaObjetos.get(1).equals("modelo.modelo")) {
					//relatorio = "/opt/tomcat/webapps/Deprov/resources/relatorios/parametros/2/CorModelo.jrxml";
					relatorio = "/var/lib/tomcat/webapps/Deprov/resources/relatorios/parametros/2/CorModelo.jrxml";
				}else if (listaObjetos.get(0).equals("seguro.seguro")  && listaObjetos.get(1).equals("cor.cor")) {
					//relatorio = "/opt/tomcat/webapps/Deprov/resources/relatorios/parametros/2/CorSeguro.jrxml";
					relatorio = "/var/lib/tomcat/webapps/Deprov/resources/relatorios/parametros/2/CorSeguro.jrxml";
				}else if (listaObjetos.get(0).equals("cor.cor") && listaObjetos.get(1).equals("seguro.seguro")) {
					//relatorio = "/opt/tomcat/webapps/Deprov/resources/relatorios/parametros/2/CorSeguro.jrxml";
					relatorio = "/var/lib/tomcat/webapps/Deprov/resources/relatorios/parametros/2/CorSeguro.jrxml";
				}else if (listaObjetos.get(0).equals("pericia.pericia")  && listaObjetos.get(1).equals("cor.cor")) {
					//relatorio = "/opt/tomcat/webapps/Deprov/resources/relatorios/parametros/2/CorPericia.jrxml";
					relatorio = "/var/lib/tomcat/webapps/Deprov/resources/relatorios/parametros/2/CorPericia.jrxml";
				}else if (listaObjetos.get(0).equals("cor.cor") && listaObjetos.get(1).equals("pericia.pericia")) {
					//relatorio = "/opt/tomcat/webapps/Deprov/resources/relatorios/parametros/2/CorPericia.jrxml";
					relatorio = "/var/lib/tomcat/webapps/Deprov/resources/relatorios/parametros/2/CorPericia.jrxml";	
				}else if (listaObjetos.get(0).equals("situacao.situacao")  && listaObjetos.get(1).equals("cor.cor")) {
					//relatorio = "/opt/tomcat/webapps/Deprov/resources/relatorios/parametros/2/CorSituacao.jrxml";
					relatorio = "/var/lib/tomcat/webapps/Deprov/resources/relatorios/parametros/2/CorSituacao.jrxml";
				}else if (listaObjetos.get(0).equals("cor.cor") && listaObjetos.get(1).equals("situacao.situacao")) {
					//relatorio = "/opt/tomcat/webapps/Deprov/resources/relatorios/parametros/2/CorSituacao.jrxml";
					relatorio = "/var/lib/tomcat/webapps/Deprov/resources/relatorios/parametros/2/CorSituacao.jrxml";
				}else if (listaObjetos.get(0).equals("local.local")  && listaObjetos.get(1).equals("cor.cor")) {
					//relatorio = "/opt/tomcat/webapps/Deprov/resources/relatorios/parametros/2/CorLocal.jrxml";
					relatorio = "/var/lib/tomcat/webapps/Deprov/resources/relatorios/parametros/2/CorLocal.jrxml";
				}else if (listaObjetos.get(0).equals("cor.cor") && listaObjetos.get(1).equals("local.local")) {
					//relatorio = "/opt/tomcat/webapps/Deprov/resources/relatorios/parametros/2/CorLocal.jrxml";
					relatorio = "/var/lib/tomcat/webapps/Deprov/resources/relatorios/parametros/2/CorLocal.jrxml";
				}else if (listaObjetos.get(0).equals("fabricante.fabricante")  && listaObjetos.get(1).equals("seguro.seguro")) {
					//relatorio = "/opt/tomcat/webapps/Deprov/resources/relatorios/parametros/2/FabricanteSeguro.jrxml";
					relatorio = "/var/lib/tomcat/webapps/Deprov/resources/relatorios/parametros/2/FabricanteSeguro.jrxml"; 
				}else if (listaObjetos.get(0).equals("seguro.seguro") && listaObjetos.get(1).equals("fabricante.fabricante")) {
					//relatorio = "/opt/tomcat/webapps/Deprov/resources/relatorios/parametros/2/FabricanteSeguro.jrxml";
					relatorio = "/var/lib/tomcat/webapps/Deprov/resources/relatorios/parametros/2/FabricanteSeguro.jrxml"; 
				}else if (listaObjetos.get(0).equals("fabricante.fabricante")  && listaObjetos.get(1).equals("pericia.pericia")) {
					//relatorio = "/opt/tomcat/webapps/Deprov/resources/relatorios/parametros/2/FabricantePericia.jrxml";
					relatorio = "/var/lib/tomcat/webapps/Deprov/resources/relatorios/parametros/2/FabricantePericia.jrxml"; 
				}else if (listaObjetos.get(0).equals("pericia.pericia") && listaObjetos.get(1).equals("fabricante.fabricante")) {
					//relatorio = "/opt/tomcat/webapps/Deprov/resources/relatorios/parametros/2/FabricantePericia.jrxml";
					relatorio = "/var/lib/tomcat/webapps/Deprov/resources/relatorios/parametros/2/FabricantePericia.jrxml";
				}else if (listaObjetos.get(0).equals("fabricante.fabricante")  && listaObjetos.get(1).equals("situacao.situacao")) {
					//relatorio = "/opt/tomcat/webapps/Deprov/resources/relatorios/parametros/2/FabricanteSituacao.jrxml";
					relatorio = "/var/lib/tomcat/webapps/Deprov/resources/relatorios/parametros/2/FabricanteSitaucao.jrxml"; 
				}else if (listaObjetos.get(0).equals("situacao.situacao") && listaObjetos.get(1).equals("fabricante.fabricante")) {
					//relatorio = "/opt/tomcat/webapps/Deprov/resources/relatorios/parametros/2/FabricanteSituacao.jrxml";
					relatorio = "/var/lib/tomcat/webapps/Deprov/resources/relatorios/parametros/2/FabricanteSituacao.jrxml";
				}else if (listaObjetos.get(0).equals("fabricante.fabricante")  && listaObjetos.get(1).equals("local.local")) {
					//relatorio = "/opt/tomcat/webapps/Deprov/resources/relatorios/parametros/2/FabricanteLocal.jrxml";
					relatorio = "/var/lib/tomcat/webapps/Deprov/resources/relatorios/parametros/2/FabricanteLocal.jrxml"; 
				}else if (listaObjetos.get(0).equals("local.local") && listaObjetos.get(1).equals("fabricante.fabricante")) {
					//relatorio = "/opt/tomcat/webapps/Deprov/resources/relatorios/parametros/2/FabricanteLocal.jrxml";
					relatorio = "/var/lib/tomcat/webapps/Deprov/resources/relatorios/parametros/2/FabricanteLocal.jrxml";
				}else if (listaObjetos.get(0).equals("modelo.modelo")  && listaObjetos.get(1).equals("seguro.seguro")) {
					//relatorio = "/opt/tomcat/webapps/Deprov/resources/relatorios/parametros/2/ModeloSeguro.jrxml";
					relatorio = "/var/lib/tomcat/webapps/Deprov/resources/relatorios/parametros/2/ModeloSeguro.jrxml"; 
				}else if (listaObjetos.get(0).equals("seguro.seguro") && listaObjetos.get(1).equals("modelo.modelo")) {
					//relatorio = "/opt/tomcat/webapps/Deprov/resources/relatorios/parametros/2/ModeloSeguro.jrxml";
					relatorio = "/var/lib/tomcat/webapps/Deprov/resources/relatorios/parametros/2/ModeloSeguro.jrxml"; 
				}else if (listaObjetos.get(0).equals("modelo.modelo")  && listaObjetos.get(1).equals("pericia.pericia")) {
					//relatorio = "/opt/tomcat/webapps/Deprov/resources/relatorios/parametros/2/ModeloPericia.jrxml";
					relatorio = "/var/lib/tomcat/webapps/Deprov/resources/relatorios/parametros/2/ModeloPericia.jrxml"; 
				}else if (listaObjetos.get(0).equals("pericia.pericia") && listaObjetos.get(1).equals("modelo.modelo")) {
					//relatorio = "/opt/tomcat/webapps/Deprov/resources/relatorios/parametros/2/ModeloPericia.jrxml";
					relatorio = "/var/lib/tomcat/webapps/Deprov/resources/relatorios/parametros/2/ModeloPericia.jrxml";
				}else if (listaObjetos.get(0).equals("modelo.modelo")  && listaObjetos.get(1).equals("situacao.situacao")) {
					//relatorio = "/opt/tomcat/webapps/Deprov/resources/relatorios/parametros/2/ModeloSituacao.jrxml";
					relatorio = "/var/lib/tomcat/webapps/Deprov/resources/relatorios/parametros/2/ModeloSituacao.jrxml"; 
				}else if (listaObjetos.get(0).equals("situacao.situacao") && listaObjetos.get(1).equals("modelo.modelo")) {
					//relatorio = "/opt/tomcat/webapps/Deprov/resources/relatorios/parametros/2/ModeloSituacao.jrxml";
					relatorio = "/var/lib/tomcat/webapps/Deprov/resources/relatorios/parametros/2/ModeloSituacao.jrxml";
				}else if (listaObjetos.get(0).equals("modelo.modelo")  && listaObjetos.get(1).equals("local.local")) {
					//relatorio = "/opt/tomcat/webapps/Deprov/resources/relatorios/parametros/2/ModeloLocal.jrxml";
					relatorio = "/var/lib/tomcat/webapps/Deprov/resources/relatorios/parametros/2/ModeloLocal.jrxml"; 
				}else if (listaObjetos.get(0).equals("local.local") && listaObjetos.get(1).equals("modelo.modelo")) {
					//relatorio = "/opt/tomcat/webapps/Deprov/resources/relatorios/parametros/2/ModeloLocal.jrxml";
					relatorio = "/var/lib/tomcat/webapps/Deprov/resources/relatorios/parametros/2/ModeloLocal.jrxml";
				}else if (listaObjetos.get(0).equals("seguro.seguro")  && listaObjetos.get(1).equals("pericia.pericia")) {
					//relatorio = "/opt/tomcat/webapps/Deprov/resources/relatorios/parametros/2/SeguroPericia.jrxml";
					relatorio = "/var/lib/tomcat/webapps/Deprov/resources/relatorios/parametros/2/SeguroPericia.jrxml"; 
				}else if (listaObjetos.get(0).equals("pericia.pericia") && listaObjetos.get(1).equals("seguro.segurp")) {
					//relatorio = "/opt/tomcat/webapps/Deprov/resources/relatorios/parametros/2/SeguroPericia.jrxml";
					relatorio = "/var/lib/tomcat/webapps/Deprov/resources/relatorios/parametros/2/SeguroPericia.jrxml";
				}else if (listaObjetos.get(0).equals("seguro.seguro")  && listaObjetos.get(1).equals("situacao.situacao")) {
					//relatorio = "/opt/tomcat/webapps/Deprov/resources/relatorios/parametros/2/SeguroSituacao.jrxml";
					relatorio = "/var/lib/tomcat/webapps/Deprov/resources/relatorios/parametros/2/SeguroSituacao.jrxml"; 
				}else if (listaObjetos.get(0).equals("situacao.situacao") && listaObjetos.get(1).equals("seguro.seguro")) {
					//relatorio = "/opt/tomcat/webapps/Deprov/resources/relatorios/parametros/2/SeguroSituacao.jrxml";
					relatorio = "/var/lib/tomcat/webapps/Deprov/resources/relatorios/parametros/2/SeguroSituacao.jrxml";
				}else if (listaObjetos.get(0).equals("seguro.seguro")  && listaObjetos.get(1).equals("local.local")) {
					//relatorio = "/opt/tomcat/webapps/Deprov/resources/relatorios/parametros/2/SeguroLocal.jrxml";
					relatorio = "/var/lib/tomcat/webapps/Deprov/resources/relatorios/parametros/2/SeguroLocal.jrxml"; 
				}else if (listaObjetos.get(0).equals("local.local") && listaObjetos.get(1).equals("seguro.seguro")) {
					//relatorio = "/opt/tomcat/webapps/Deprov/resources/relatorios/parametros/2/SeguroLocal.jrxml";
					relatorio = "/var/lib/tomcat/webapps/Deprov/resources/relatorios/parametros/2/SeguroLocal.jrxml";
				}else if (listaObjetos.get(0).equals("situacao.situacao")  && listaObjetos.get(1).equals("local.local")) {
					//relatorio = "/opt/tomcat/webapps/Deprov/resources/relatorios/parametros/2/LocalSituacao.jrxml";
					relatorio = "/var/lib/tomcat/webapps/Deprov/resources/relatorios/parametros/2/LocalSituacao.jrxml"; 
				}else if (listaObjetos.get(0).equals("local.local") && listaObjetos.get(1).equals("situacao.situacao")) {
					//relatorio = "/opt/tomcat/webapps/Deprov/resources/relatorios/parametros/2/LocalSituacao.jrxml";
					relatorio = "/var/lib/tomcat/webapps/Deprov/resources/relatorios/parametros/2/LocalSituacao.jrxml";
				}else if (listaObjetos.get(0).equals("pericia.pericia")  && listaObjetos.get(1).equals("situacao.situacao")) {
					//relatorio = "/opt/tomcat/webapps/Deprov/resources/relatorios/parametros/2/PericiaSituacao.jrxml";
					relatorio = "/var/lib/tomcat/webapps/Deprov/resources/relatorios/parametros/2/PericiaSituacao.jrxml"; 
				}else if (listaObjetos.get(0).equals("situacao.situacao") && listaObjetos.get(1).equals("pericia.pericia")) {
					//relatorio = "/opt/tomcat/webapps/Deprov/resources/relatorios/parametros/2/PericiaSituacao.jrxml";
					relatorio = "/var/lib/tomcat/webapps/Deprov/resources/relatorios/parametros/2/PericiaSituacao.jrxml";
				}else if (listaObjetos.get(0).equals("pericia.pericia")  && listaObjetos.get(1).equals("local.local")) {
					//relatorio = "/opt/tomcat/webapps/Deprov/resources/relatorios/parametros/2/PericiaLocal.jrxml";
					relatorio = "/var/lib/tomcat/webapps/Deprov/resources/relatorios/parametros/2/PericiaLocal.jrxml"; 
				}else if (listaObjetos.get(0).equals("local.local") && listaObjetos.get(1).equals("pericia.pericia")) {
					//relatorio = "/opt/tomcat/webapps/Deprov/resources/relatorios/parametros/2/PericiaLocal.jrxml";
					relatorio = "/var/lib/tomcat/webapps/Deprov/resources/relatorios/parametros/2/PericiaLocal.jrxml";
				}
				
				
			}
		//}
		return relatorio;
	}	
	
	public Map<String, Object> retornarParametros(ArrayList<String> listaObjetos, ArrayList<String> listavalores){
		String parametro = "";
		Map<String, Object> mapaParametro = new HashMap<String, Object>();
		Repositorios repositorios = new Repositorios();
		int valor = 0;
		
		//for (int i = 0; i < listaObjetos.size(); i++) {
			//System.out.println("Lista: "+ listaObjetos.get(i));
		
			if (listaObjetos.size() == 1) {
				//System.out.println("Parametro 1");
	            if (listaObjetos.get(0).equals("fabricante.fabricante")) {
	            	Fabricante fabricante = new Fabricante();
	        		Fabricantes fabricantes = repositorios.getFabricantes();
	        		fabricante = fabricantes.pegaCodigo(listavalores.get(0));
	            	
	        		valor = fabricante.getCodigo();
	            	parametro = "codigo_fabricante";
	            	
	            	mapaParametro.put(parametro, valor);
				}else if (listaObjetos.get(0).equals("modelo.modelo")) {
					
					Modelo modelo = new Modelo();
					Modelos modelos = repositorios.getModelos();
					modelo = modelos.pegaCodigo(listavalores.get(0));
					
					valor = modelo.getCodigo();
	            	parametro = "codigo_modelo";
	            	
	            	mapaParametro.put(parametro, valor);
					
				}else if (listaObjetos.get(0).equals("cor.cor")) {
					Cor cor = new Cor();
					Cores cores = repositorios.getCores();
					cor = cores.pegaCodigo(listavalores.get(0));
					
					valor = cor.getCodigo();
	            	parametro = "codigo_cor";
	            	
	            	mapaParametro.put(parametro, valor);		
					
				}else if (listaObjetos.get(0).equals("seguro.seguro")) {
					Seguro seguro = new Seguro();
					Seguros seguros = repositorios.getSeguros();
					seguro = seguros.pegaCodigo(listavalores.get(0));
					
					valor = seguro.getCodigo();
	            	parametro = "codigo_seguro";
	            	
	            	mapaParametro.put(parametro, valor);
	            	
				}else if (listaObjetos.get(0).equals("pericia.pericia")) {
					Pericia pericia = new Pericia();
					Pericias pericias = repositorios.getPericias();
					pericia = pericias.pegaCodigo(listavalores.get(0));
					
					valor = pericia.getCodigo();
	            	parametro = "codigo_pericia";
	            	
	            	mapaParametro.put(parametro, valor);
					
				}else if (listaObjetos.get(0).equals("situacao.situacao")) {
					Situacao situacao = new Situacao();
					ISituacao ISituacao = repositorios.getSituacao();
					situacao = ISituacao.pegaCodigo(listavalores.get(0));
					
					valor = situacao.getCodigo();
	            	parametro = "codigo_situacao";
	            	
	            	mapaParametro.put(parametro, valor);	            	
				
				}else if (listaObjetos.get(0).equals("local.local")) {
					Local local = new Local();
					ILocal Ilocal = repositorios.getLocal();
					
					
					local = Ilocal.pegaCodigo(listavalores.get(0));
					
					valor = local.getCodigo();
	            	parametro = "codigo_local";
	            	
	            	mapaParametro.put(parametro, valor);	            	
				}
	            
			}else if (listaObjetos.size() == 2) {
				//System.out.println("Parametro 2");
				if (listaObjetos.get(0).equals("fabricante.fabricante")  && listaObjetos.get(1).equals("cor.cor")) {
					Fabricante fabricante = new Fabricante();
	        		Fabricantes fabricantes = repositorios.getFabricantes();
	        		fabricante = fabricantes.pegaCodigo(listavalores.get(0));
	            	
	        		valor = fabricante.getCodigo();
	            	parametro = "codigo_fabricante";
	            	
	            	mapaParametro.put(parametro, valor);
	            	
	            	Cor cor = new Cor();
					Cores cores = repositorios.getCores();
					cor = cores.pegaCodigo(listavalores.get(1));
					
					valor = cor.getCodigo();
	            	parametro = "codigo_cor";
	            	
	            	mapaParametro.put(parametro, valor);
					
					
				}else if (listaObjetos.get(0).equals("cor.cor") && listaObjetos.get(1).equals("fabricante.fabricante")) {
					Cor cor = new Cor();
					Cores cores = repositorios.getCores();
					cor = cores.pegaCodigo(listavalores.get(0));
					
					valor = cor.getCodigo();
	            	parametro = "codigo_cor";
	            	
	            	mapaParametro.put(parametro, valor);
	            	
	            	Fabricante fabricante = new Fabricante();
	        		Fabricantes fabricantes = repositorios.getFabricantes();
	        		fabricante = fabricantes.pegaCodigo(listavalores.get(1));
	            	
	        		valor = fabricante.getCodigo();
	            	parametro = "codigo_fabricante";
	            	
	            	mapaParametro.put(parametro, valor);
					
				}else if (listaObjetos.get(0).equals("modelo.modelo")  && listaObjetos.get(1).equals("cor.cor")) {
					Modelo modelo = new Modelo();
					Modelos modelos = repositorios.getModelos();
					modelo = modelos.pegaCodigo(listavalores.get(0));
					
					valor = modelo.getCodigo();
	            	parametro = "codigo_modelo";
	            	
	            	mapaParametro.put(parametro, valor);
		            		        	           	
		            Cor cor = new Cor();
					Cores cores = repositorios.getCores();
					cor = cores.pegaCodigo(listavalores.get(1));
						
					valor = cor.getCodigo();
		            parametro = "codigo_cor";
		            	
		            mapaParametro.put(parametro, valor);
						
				}else if (listaObjetos.get(0).equals("cor.cor") && listaObjetos.get(1).equals("modelo.modelo")) {
					Cor cor = new Cor();
					Cores cores = repositorios.getCores();
					cor = cores.pegaCodigo(listavalores.get(0));
						
					valor = cor.getCodigo();
		            parametro = "codigo_cor";
		            	
		            mapaParametro.put(parametro, valor);
					
					Modelo modelo = new Modelo();
					Modelos modelos = repositorios.getModelos();
					modelo = modelos.pegaCodigo(listavalores.get(1));
					
					valor = modelo.getCodigo();
	            	parametro = "codigo_modelo";
	            	
	            	mapaParametro.put(parametro, valor);
		            
				}else if (listaObjetos.get(0).equals("seguro.seguro")  && listaObjetos.get(1).equals("cor.cor"))  {
					Seguro seguro = new Seguro();
					Seguros seguros = repositorios.getSeguros();
					seguro = seguros.pegaCodigo(listavalores.get(0));
					
					valor = seguro.getCodigo();
	            	parametro = "codigo_seguro";
	            	
	            	mapaParametro.put(parametro, valor);
					
	            	Cor cor = new Cor();
					Cores cores = repositorios.getCores();
					cor = cores.pegaCodigo(listavalores.get(1));
						
					valor = cor.getCodigo();
		            parametro = "codigo_cor";
		            	
		            mapaParametro.put(parametro, valor);
					
				}else if (listaObjetos.get(0).equals("cor.cor") && listaObjetos.get(1).equals("seguro.seguro")) {
					Cor cor = new Cor();
					Cores cores = repositorios.getCores();
					cor = cores.pegaCodigo(listavalores.get(0));
						
					valor = cor.getCodigo();
		            parametro = "codigo_cor";
		            	
		            mapaParametro.put(parametro, valor);
					
		            Seguro seguro = new Seguro();
					Seguros seguros = repositorios.getSeguros();
					seguro = seguros.pegaCodigo(listavalores.get(1));
					
					valor = seguro.getCodigo();
	            	parametro = "codigo_seguro";
	            	
	            	mapaParametro.put(parametro, valor);
					
				}else if (listaObjetos.get(0).equals("cor.cor") && listaObjetos.get(1).equals("pericia.pericia")) {
					Cor cor = new Cor();
					Cores cores = repositorios.getCores();
					cor = cores.pegaCodigo(listavalores.get(0));
						
					valor = cor.getCodigo();
		            parametro = "codigo_cor";
		            	
		            mapaParametro.put(parametro, valor);
					
		            Pericia pericia = new Pericia();
					Pericias pericias = repositorios.getPericias();
					pericia = pericias.pegaCodigo(listavalores.get(1));
					
					valor = pericia.getCodigo();
	            	parametro = "codigo_pericia";
	            	
	            	mapaParametro.put(parametro, valor);
		            
				}else if (listaObjetos.get(0).equals("pericia.pericia") && listaObjetos.get(1).equals("cor.cor")) {	
					Pericia pericia = new Pericia();
					Pericias pericias = repositorios.getPericias();
					pericia = pericias.pegaCodigo(listavalores.get(0));
					
					valor = pericia.getCodigo();
	            	parametro = "codigo_pericia";
	            	
	            	mapaParametro.put(parametro, valor);
					
	            	Cor cor = new Cor();
					Cores cores = repositorios.getCores();
					cor = cores.pegaCodigo(listavalores.get(1));
						
					valor = cor.getCodigo();
		            parametro = "codigo_cor";
		            	
		            mapaParametro.put(parametro, valor);
					
				}else if (listaObjetos.get(0).equals("cor.cor") && listaObjetos.get(1).equals("situacao.situacao")) {
					Cor cor = new Cor();
					Cores cores = repositorios.getCores();
					cor = cores.pegaCodigo(listavalores.get(0));
						
					valor = cor.getCodigo();
		            parametro = "codigo_cor";
		            	
		            mapaParametro.put(parametro, valor);
		            
		            Situacao situacao = new Situacao();
					ISituacao ISituacao = repositorios.getSituacao();
					situacao = ISituacao.pegaCodigo(listavalores.get(1));
					
					valor = situacao.getCodigo();
	            	parametro = "codigo_situacao";
	            	
	            	mapaParametro.put(parametro, valor);		            
		            
				}else if (listaObjetos.get(0).equals("situacao.situacao") && listaObjetos.get(1).equals("cor.cor")) {
					Situacao situacao = new Situacao();
					ISituacao ISituacao = repositorios.getSituacao();
					situacao = ISituacao.pegaCodigo(listavalores.get(0));
					
					valor = situacao.getCodigo();
	            	parametro = "codigo_situacao";
	            	
	            	mapaParametro.put(parametro, valor);
	            	
	            	Cor cor = new Cor();
					Cores cores = repositorios.getCores();
					cor = cores.pegaCodigo(listavalores.get(1));
						
					valor = cor.getCodigo();
		            parametro = "codigo_cor";
		            	
		            mapaParametro.put(parametro, valor);
	            	
				}else if (listaObjetos.get(0).equals("local.local")  && listaObjetos.get(1).equals("cor.cor")) {
					Local local = new Local();
					ILocal Ilocal = repositorios.getLocal();
					
					
					local = Ilocal.pegaCodigo(listavalores.get(0));
					
					valor = local.getCodigo();
	            	parametro = "codigo_local";
	            	
	            	mapaParametro.put(parametro, valor);
		            		        	           	
		            Cor cor = new Cor();
					Cores cores = repositorios.getCores();
					cor = cores.pegaCodigo(listavalores.get(1));
						
					valor = cor.getCodigo();
		            parametro = "codigo_cor";
		            	
		            mapaParametro.put(parametro, valor);
						
				}else if (listaObjetos.get(0).equals("cor.cor") && listaObjetos.get(1).equals("local.local")) {
					Cor cor = new Cor();
					Cores cores = repositorios.getCores();
					cor = cores.pegaCodigo(listavalores.get(0));
						
					valor = cor.getCodigo();
		            parametro = "codigo_cor";
		            	
		            mapaParametro.put(parametro, valor);
					
		            Local local = new Local();
					ILocal Ilocal = repositorios.getLocal();
					
					
					local = Ilocal.pegaCodigo(listavalores.get(1));
					
					valor = local.getCodigo();
	            	parametro = "codigo_local";
	            	
	            	mapaParametro.put(parametro, valor);
		            
				}else if (listaObjetos.get(0).equals("fabricante.fabricante")  && listaObjetos.get(1).equals("seguro.seguro")) {
					Fabricante fabricante = new Fabricante();
	        		Fabricantes fabricantes = repositorios.getFabricantes();
	        		fabricante = fabricantes.pegaCodigo(listavalores.get(0));
	            	
	        		valor = fabricante.getCodigo();
	            	parametro = "codigo_fabricante";
	            	
	            	mapaParametro.put(parametro, valor); 
					
					Seguro seguro = new Seguro();
					Seguros seguros = repositorios.getSeguros();
					seguro = seguros.pegaCodigo(listavalores.get(1));
					
					valor = seguro.getCodigo();
	            	parametro = "codigo_seguro";
	            	
	            	mapaParametro.put(parametro, valor);
					
				}else if (listaObjetos.get(0).equals("seguro.seguro") && listaObjetos.get(1).equals("fabricante.fabricante")) {
					Seguro seguro = new Seguro();
					Seguros seguros = repositorios.getSeguros();
					seguro = seguros.pegaCodigo(listavalores.get(0));
					
					valor = seguro.getCodigo();
	            	parametro = "codigo_seguro";
	            	
	            	mapaParametro.put(parametro, valor);
	            	
	            	Fabricante fabricante = new Fabricante();
	        		Fabricantes fabricantes = repositorios.getFabricantes();
	        		fabricante = fabricantes.pegaCodigo(listavalores.get(1));
	            	
	        		valor = fabricante.getCodigo();
	            	parametro = "codigo_fabricante";
	            	
	            	mapaParametro.put(parametro, valor);
				}else if (listaObjetos.get(0).equals("fabricante.fabricante")  && listaObjetos.get(1).equals("pericia.pericia")) {
					Fabricante fabricante = new Fabricante();
	        		Fabricantes fabricantes = repositorios.getFabricantes();
	        		fabricante = fabricantes.pegaCodigo(listavalores.get(0));
	            	
	        		valor = fabricante.getCodigo();
	            	parametro = "codigo_fabricante";
	            	
	            	mapaParametro.put(parametro, valor); 
					
	            	Pericia pericia = new Pericia();
					Pericias pericias = repositorios.getPericias();
					pericia = pericias.pegaCodigo(listavalores.get(1));
					
					valor = pericia.getCodigo();
	            	parametro = "codigo_pericia";
	            	
	            	mapaParametro.put(parametro, valor);
					
				}else if (listaObjetos.get(0).equals("pericia.pericia") && listaObjetos.get(1).equals("fabricante.fabricante")) {
					Pericia pericia = new Pericia();
					Pericias pericias = repositorios.getPericias();
					pericia = pericias.pegaCodigo(listavalores.get(0));
					
					valor = pericia.getCodigo();
	            	parametro = "codigo_pericia";
	            	
	            	mapaParametro.put(parametro, valor);
	            	
	            	Fabricante fabricante = new Fabricante();
	        		Fabricantes fabricantes = repositorios.getFabricantes();
	        		fabricante = fabricantes.pegaCodigo(listavalores.get(1));
	            	
	        		valor = fabricante.getCodigo();
	            	parametro = "codigo_fabricante";
	            	
	            	mapaParametro.put(parametro, valor);
				}else if (listaObjetos.get(0).equals("fabricante.fabricante")  && listaObjetos.get(1).equals("situacao.situacao")) {
					Fabricante fabricante = new Fabricante();
	        		Fabricantes fabricantes = repositorios.getFabricantes();
	        		fabricante = fabricantes.pegaCodigo(listavalores.get(0));
	            	
	        		valor = fabricante.getCodigo();
	            	parametro = "codigo_fabricante";
	            	
	            	mapaParametro.put(parametro, valor); 
					
	            	Situacao situacao = new Situacao();
					ISituacao ISituacao = repositorios.getSituacao();
					situacao = ISituacao.pegaCodigo(listavalores.get(1));
					
					valor = situacao.getCodigo();
	            	parametro = "codigo_situacao";
	            	
	            	mapaParametro.put(parametro, valor);					
					
				}else if (listaObjetos.get(0).equals("situacao.situacao") && listaObjetos.get(1).equals("fabricante.fabricante")) {
					Situacao situacao = new Situacao();
					ISituacao ISituacao = repositorios.getSituacao();
					situacao = ISituacao.pegaCodigo(listavalores.get(0));
					
					valor = situacao.getCodigo();
	            	parametro = "codigo_situacao";
	            	
	            	mapaParametro.put(parametro, valor);					
					
					Fabricante fabricante = new Fabricante();
	        		Fabricantes fabricantes = repositorios.getFabricantes();
	        		fabricante = fabricantes.pegaCodigo(listavalores.get(1));
	            	
	        		valor = fabricante.getCodigo();
	            	parametro = "codigo_fabricante";
	            	
	            	mapaParametro.put(parametro, valor);
					
				}else if (listaObjetos.get(0).equals("fabricante.fabricante")  && listaObjetos.get(1).equals("local.local")) {
					Fabricante fabricante = new Fabricante();
	        		Fabricantes fabricantes = repositorios.getFabricantes();
	        		fabricante = fabricantes.pegaCodigo(listavalores.get(0));
	            	
	        		valor = fabricante.getCodigo();
	            	parametro = "codigo_fabricante";
	            	
	            	mapaParametro.put(parametro, valor);
	            	
	            	Local local = new Local();
					ILocal Ilocal = repositorios.getLocal();
					
					
					local = Ilocal.pegaCodigo(listavalores.get(1));
					
					valor = local.getCodigo();
	            	parametro = "codigo_local";
	            	
	            	mapaParametro.put(parametro, valor);	
					
				}else if (listaObjetos.get(0).equals("local.local") && listaObjetos.get(1).equals("fabricante.fabricante")) {
					Local local = new Local();
					ILocal Ilocal = repositorios.getLocal();
					
					local = Ilocal.pegaCodigo(listavalores.get(0));
					
					valor = local.getCodigo();
	            	parametro = "codigo_local";
	            	
	            	mapaParametro.put(parametro, valor);
	            	
	            	Fabricante fabricante = new Fabricante();
	        		Fabricantes fabricantes = repositorios.getFabricantes();
	        		fabricante = fabricantes.pegaCodigo(listavalores.get(1));
	            	
	        		valor = fabricante.getCodigo();
	            	parametro = "codigo_fabricante";
	            	
	            	mapaParametro.put(parametro, valor);
					
				}else if (listaObjetos.get(0).equals("modelo.modelo")  && listaObjetos.get(1).equals("seguro.seguro")) {
					Modelo modelo = new Modelo();
					Modelos modelos = repositorios.getModelos();
					modelo = modelos.pegaCodigo(listavalores.get(0));
					
					valor = modelo.getCodigo();
	            	parametro = "codigo_modelo";
	            	
	            	mapaParametro.put(parametro, valor); 
					
					Seguro seguro = new Seguro();
					Seguros seguros = repositorios.getSeguros();
					seguro = seguros.pegaCodigo(listavalores.get(1));
					
					valor = seguro.getCodigo();
	            	parametro = "codigo_seguro";
	            	
	            	mapaParametro.put(parametro, valor);
					
				}else if (listaObjetos.get(0).equals("seguro.seguro") && listaObjetos.get(1).equals("modelo.modelo")) {
					Seguro seguro = new Seguro();
					Seguros seguros = repositorios.getSeguros();
					seguro = seguros.pegaCodigo(listavalores.get(0));
					
					valor = seguro.getCodigo();
	            	parametro = "codigo_seguro";
	            	
	            	mapaParametro.put(parametro, valor);
	            	
	            	Modelo modelo = new Modelo();
					Modelos modelos = repositorios.getModelos();
					modelo = modelos.pegaCodigo(listavalores.get(1));
					
					valor = modelo.getCodigo();
	            	parametro = "codigo_modelo";
	            	
	            	mapaParametro.put(parametro, valor);
	            	
				}else if (listaObjetos.get(0).equals("modelo.modelo")  && listaObjetos.get(1).equals("pericia.pericia")) {
					Modelo modelo = new Modelo();
					Modelos modelos = repositorios.getModelos();
					modelo = modelos.pegaCodigo(listavalores.get(0));
					
					valor = modelo.getCodigo();
	            	parametro = "codigo_modelo";
	            	
	            	mapaParametro.put(parametro, valor); 
					
	            	Pericia pericia = new Pericia();
					Pericias pericias = repositorios.getPericias();
					pericia = pericias.pegaCodigo(listavalores.get(1));
					
					valor = pericia.getCodigo();
	            	parametro = "codigo_pericia";
	            	
	            	mapaParametro.put(parametro, valor);
					
				}else if (listaObjetos.get(0).equals("pericia.pericia") && listaObjetos.get(1).equals("modelo.modelo")) {
					Pericia pericia = new Pericia();
					Pericias pericias = repositorios.getPericias();
					pericia = pericias.pegaCodigo(listavalores.get(0));
					
					valor = pericia.getCodigo();
	            	parametro = "codigo_pericia";
	            	
	            	mapaParametro.put(parametro, valor);
	            	
	            	Modelo modelo = new Modelo();
					Modelos modelos = repositorios.getModelos();
					modelo = modelos.pegaCodigo(listavalores.get(1));
					
					valor = modelo.getCodigo();
	            	parametro = "codigo_modelo";
	            	
	            	mapaParametro.put(parametro, valor);
	            	
				}else if (listaObjetos.get(0).equals("modelo.modelo")  && listaObjetos.get(1).equals("situacao.situacao")) {
					Modelo modelo = new Modelo();
					Modelos modelos = repositorios.getModelos();
					modelo = modelos.pegaCodigo(listavalores.get(0));
					
					valor = modelo.getCodigo();
	            	parametro = "codigo_modelo";
	            	
	            	mapaParametro.put(parametro, valor); 
					
	            	Situacao situacao = new Situacao();
					ISituacao ISituacao = repositorios.getSituacao();
					situacao = ISituacao.pegaCodigo(listavalores.get(1));
					
					valor = situacao.getCodigo();
	            	parametro = "codigo_situacao";
	            	
	            	mapaParametro.put(parametro, valor);					
					
				}else if (listaObjetos.get(0).equals("situacao.situacao") && listaObjetos.get(1).equals("modelo.modelo")) {
					Situacao situacao = new Situacao();
					ISituacao ISituacao = repositorios.getSituacao();
					situacao = ISituacao.pegaCodigo(listavalores.get(0));
					
					valor = situacao.getCodigo();
	            	parametro = "codigo_situacao";
	            	
	            	mapaParametro.put(parametro, valor);					
					
	            	Modelo modelo = new Modelo();
					Modelos modelos = repositorios.getModelos();
					modelo = modelos.pegaCodigo(listavalores.get(1));
					
					valor = modelo.getCodigo();
	            	parametro = "codigo_modelo";
	            	
	            	mapaParametro.put(parametro, valor);
					
				}else if (listaObjetos.get(0).equals("modelo.modelo")  && listaObjetos.get(1).equals("local.local")) {
					Modelo modelo = new Modelo();
					Modelos modelos = repositorios.getModelos();
					modelo = modelos.pegaCodigo(listavalores.get(0));
					
					valor = modelo.getCodigo();
	            	parametro = "codigo_modelo";
	            	
	            	mapaParametro.put(parametro, valor); 
					
	            	Local local = new Local();
					ILocal Ilocal = repositorios.getLocal();
					
					local = Ilocal.pegaCodigo(listavalores.get(1));
					
					valor = local.getCodigo();
	            	parametro = "codigo_local";
	            	
	            	mapaParametro.put(parametro, valor);
					
				}else if (listaObjetos.get(0).equals("local.local") && listaObjetos.get(1).equals("modelo.modelo")) {
					Local local = new Local();
					ILocal Ilocal = repositorios.getLocal();
					
					local = Ilocal.pegaCodigo(listavalores.get(0));
					
					valor = local.getCodigo();
	            	parametro = "codigo_local";
	            	
	            	mapaParametro.put(parametro, valor);
	            	
	            	Modelo modelo = new Modelo();
					Modelos modelos = repositorios.getModelos();
					modelo = modelos.pegaCodigo(listavalores.get(1));
					
					valor = modelo.getCodigo();
	            	parametro = "codigo_modelo";
	            	
	            	mapaParametro.put(parametro, valor);
	            	
				}else if (listaObjetos.get(0).equals("seguro.seguro")  && listaObjetos.get(1).equals("pericia.pericia")) {
					Seguro seguro = new Seguro();
					Seguros seguros = repositorios.getSeguros();
					seguro = seguros.pegaCodigo(listavalores.get(0));
					
					valor = seguro.getCodigo();
	            	parametro = "codigo_seguro";
	            	
	            	mapaParametro.put(parametro, valor);
					
					Pericia pericia = new Pericia();
					Pericias pericias = repositorios.getPericias();
					pericia = pericias.pegaCodigo(listavalores.get(1));
					
					valor = pericia.getCodigo();
	            	parametro = "codigo_pericia";
	            	
	            	mapaParametro.put(parametro, valor);
					
				}else if (listaObjetos.get(0).equals("pericia.pericia") && listaObjetos.get(1).equals("seguro.seguro")) {
					Pericia pericia = new Pericia();
					Pericias pericias = repositorios.getPericias();
					pericia = pericias.pegaCodigo(listavalores.get(0));
					
					valor = pericia.getCodigo();
	            	parametro = "codigo_pericia";
	            	
	            	mapaParametro.put(parametro, valor);
					
	            	Seguro seguro = new Seguro();
					Seguros seguros = repositorios.getSeguros();
					seguro = seguros.pegaCodigo(listavalores.get(1));
					
					valor = seguro.getCodigo();
	            	parametro = "codigo_seguro";
	            	
	            	mapaParametro.put(parametro, valor);
	            	
				}else if (listaObjetos.get(0).equals("seguro.seguro")  && listaObjetos.get(1).equals("situacao.situacao")) {
					Seguro seguro = new Seguro();
					Seguros seguros = repositorios.getSeguros();
					seguro = seguros.pegaCodigo(listavalores.get(0));
					
					valor = seguro.getCodigo();
	            	parametro = "codigo_seguro";
	            	
	            	mapaParametro.put(parametro, valor);
					
					Situacao situacao = new Situacao();
					ISituacao ISituacao = repositorios.getSituacao();
					situacao = ISituacao.pegaCodigo(listavalores.get(1));
					
					valor = situacao.getCodigo();
	            	parametro = "codigo_situacao";
	            	
	            	mapaParametro.put(parametro, valor);
					
				}else if (listaObjetos.get(0).equals("situacao.situacao") && listaObjetos.get(1).equals("seguro.seguro")) {
					Situacao situacao = new Situacao();
					ISituacao ISituacao = repositorios.getSituacao();
					situacao = ISituacao.pegaCodigo(listavalores.get(0));
					
					valor = situacao.getCodigo();
	            	parametro = "codigo_situacao";
	            	
	            	mapaParametro.put(parametro, valor);
					
	            	Seguro seguro = new Seguro();
					Seguros seguros = repositorios.getSeguros();
					seguro = seguros.pegaCodigo(listavalores.get(1));
					
					valor = seguro.getCodigo();
	            	parametro = "codigo_seguro";
	            	
	            	mapaParametro.put(parametro, valor);
	            	
				}else if (listaObjetos.get(0).equals("seguro.seguro")  && listaObjetos.get(1).equals("local.local")) {
					Seguro seguro = new Seguro();
					Seguros seguros = repositorios.getSeguros();
					seguro = seguros.pegaCodigo(listavalores.get(0));
					
					valor = seguro.getCodigo();
	            	parametro = "codigo_seguro";
	            	
	            	mapaParametro.put(parametro, valor);
					
	            	Local local = new Local();
					ILocal Ilocal = repositorios.getLocal();
					
					local = Ilocal.pegaCodigo(listavalores.get(1));
					
					valor = local.getCodigo();
	            	parametro = "codigo_local";
	            	
	            	mapaParametro.put(parametro, valor);
					
				}else if (listaObjetos.get(0).equals("local.local") && listaObjetos.get(1).equals("seguro.seguro")) {
					Local local = new Local();
					ILocal Ilocal = repositorios.getLocal();
					
					local = Ilocal.pegaCodigo(listavalores.get(0));
					
					valor = local.getCodigo();
	            	parametro = "codigo_local";
	            	
	            	mapaParametro.put(parametro, valor);
					
	            	Seguro seguro = new Seguro();
					Seguros seguros = repositorios.getSeguros();
					seguro = seguros.pegaCodigo(listavalores.get(1));
					
					valor = seguro.getCodigo();
	            	parametro = "codigo_seguro";
	            	
	            	mapaParametro.put(parametro, valor);
				}else if (listaObjetos.get(0).equals("pericia.pericia")  && listaObjetos.get(1).equals("situacao.situacao")) {
					Pericia pericia = new Pericia();
					Pericias pericias = repositorios.getPericias();
					pericia = pericias.pegaCodigo(listavalores.get(0));
					
					valor = pericia.getCodigo();
	            	parametro = "codigo_pericia";
	            	
	            	mapaParametro.put(parametro, valor);
					
					Situacao situacao = new Situacao();
					ISituacao ISituacao = repositorios.getSituacao();
					situacao = ISituacao.pegaCodigo(listavalores.get(1));
					
					valor = situacao.getCodigo();
	            	parametro = "codigo_situacao";
	            	
	            	mapaParametro.put(parametro, valor);
					
				}else if (listaObjetos.get(0).equals("situacao.situacao") && listaObjetos.get(1).equals("pericia.pericia")) {
					Situacao situacao = new Situacao();
					ISituacao ISituacao = repositorios.getSituacao();
					situacao = ISituacao.pegaCodigo(listavalores.get(0));
					
					valor = situacao.getCodigo();
	            	parametro = "codigo_situacao";
	            	
	            	mapaParametro.put(parametro, valor);
					
	            	Pericia pericia = new Pericia();
					Pericias pericias = repositorios.getPericias();
					pericia = pericias.pegaCodigo(listavalores.get(1));
					
					valor = pericia.getCodigo();
	            	parametro = "codigo_pericia";
	            	
	            	mapaParametro.put(parametro, valor);
	            	
				}else if (listaObjetos.get(0).equals("pericia.pericia")  && listaObjetos.get(1).equals("local.local")) {
					Pericia pericia = new Pericia();
					Pericias pericias = repositorios.getPericias();
					pericia = pericias.pegaCodigo(listavalores.get(0));
					
					valor = pericia.getCodigo();
	            	parametro = "codigo_pericia";
	            	
	            	mapaParametro.put(parametro, valor);
					
					
	            	Local local = new Local();
					ILocal Ilocal = repositorios.getLocal();
					
					local = Ilocal.pegaCodigo(listavalores.get(1));
					
					valor = local.getCodigo();
	            	parametro = "codigo_local";
	            	
	            	mapaParametro.put(parametro, valor);
					
				}else if (listaObjetos.get(0).equals("local.local") && listaObjetos.get(1).equals("pericia.pericia")) {
					Local local = new Local();
					ILocal Ilocal = repositorios.getLocal();
					
					local = Ilocal.pegaCodigo(listavalores.get(0));
					
					valor = local.getCodigo();
	            	parametro = "codigo_local";
	            	
	            	mapaParametro.put(parametro, valor);
					
	            	Pericia pericia = new Pericia();
					Pericias pericias = repositorios.getPericias();
					pericia = pericias.pegaCodigo(listavalores.get(1));
					
					valor = pericia.getCodigo();
	            	parametro = "codigo_pericia";
	            	
	            	mapaParametro.put(parametro, valor);
	            	
				}else if (listaObjetos.get(0).equals("situacao.situacao")  && listaObjetos.get(1).equals("local.local")) {
					Situacao situacao = new Situacao();
					ISituacao ISituacao = repositorios.getSituacao();
					situacao = ISituacao.pegaCodigo(listavalores.get(0));
					
					valor = situacao.getCodigo();
	            	parametro = "codigo_situacao";
	            	
	            	mapaParametro.put(parametro, valor);
					
					
	            	Local local = new Local();
					ILocal Ilocal = repositorios.getLocal();
					
					local = Ilocal.pegaCodigo(listavalores.get(1));
					
					valor = local.getCodigo();
	            	parametro = "codigo_local";
	            	
	            	mapaParametro.put(parametro, valor);
					
				}else if (listaObjetos.get(0).equals("local.local") && listaObjetos.get(1).equals("situacao.situacao")) {
					Local local = new Local();
					ILocal Ilocal = repositorios.getLocal();
					
					local = Ilocal.pegaCodigo(listavalores.get(0));
					
					valor = local.getCodigo();
	            	parametro = "codigo_local";
	            	
	            	mapaParametro.put(parametro, valor);
					
	            	Situacao situacao = new Situacao();
					ISituacao ISituacao = repositorios.getSituacao();
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