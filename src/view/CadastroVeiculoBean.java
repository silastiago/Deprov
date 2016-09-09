package view;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.URL;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.servlet.http.HttpServletResponse;

import org.primefaces.event.data.FilterEvent;
import org.primefaces.model.StreamedContent;

import conexao.ConnectionFactory;
import model.Cor;
import model.Fabricante;
import model.Modelo;
import model.Seguro;
import model.Veiculo;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.ExporterInput;
import net.sf.jasperreports.export.OutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import repository.Cores;
import repository.Fabricantes;
import repository.Modelos;
import repository.Seguros;
import repository.Veiculos;
import util.FacesUtil;
import util.Repositorios;

@ManagedBean(name="cadastroVeiculoBean")
@ViewScoped
public class CadastroVeiculoBean implements Serializable{

	private Repositorios repositorios = new Repositorios();
	private Veiculo veiculo = new Veiculo();
	private List<Veiculo> listaVeiculos = new ArrayList<Veiculo>();
	private List<Veiculo> listaVeiculosFiltrados = new ArrayList<Veiculo>();
	private Map<String, Object> mapaFiltros = new HashMap<String, Object>();
	private StreamedContent file;
	private String relatorio;
	private String parametro;
	private String valor;
	private Object instancia;
	private List<Modelo> listaModelos;
	
	
	
	@PostConstruct
	public void init(){
		Veiculos veiculos = this.repositorios.getveiculos();
		this.listaVeiculos = veiculos.listar();
		this.listaModelos = null;
	}


	public void lerFabricante(ValueChangeEvent evento){
		Fabricante fabricante = (Fabricante) evento.getNewValue();
		System.out.println("Codigo do Fabricante: " + fabricante.getCodigo());
		this.veiculo.setFabricante(fabricante);
		//return codigoSistemaOperacional;
	}
	
	public List<Modelo> listaModelos(){
		System.out.println("Codigo do fabricante2: " + this.veiculo.getFabricante().getCodigo());
		//Esta linha estou instanciando a interface com sua implementação.
		Modelos modelos = this.repositorios.getModelos();
		//A lista de modelos recebe as modelos.
		listaModelos = modelos.pegaModelos(this.veiculo.getFabricante().getCodigo().toString());
		//Retorna a lista de modelos
		return listaModelos;
	}
	
	
	public void cadastrar(){
			Veiculos veiculos = this.repositorios.getveiculos();
			System.out.println("Chave: " + veiculo.getChave());
			System.out.println("Lista de veiculos: " + veiculos.chaveExistente(veiculo).size());
			if (veiculo.getChave().toUpperCase().equals("NÃO") || veiculo.getChave().toUpperCase().equals("")) {
				veiculos.salvar(veiculo);
				try {
					FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else{
				if (veiculos.chaveExistente(veiculo).size() > 0) {
					FacesContext.getCurrentInstance().addMessage("message", new FacesMessage(FacesMessage.SEVERITY_ERROR, "","Local da Chave já ocupado"));
				}else{
					veiculos.salvar(veiculo);
					try {
						FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}	
		//return "index?faces-redirect=true";
	}
	
	
	public void editar(){
		Veiculos veiculos = this.repositorios.getveiculos();
		System.out.println("Chave: " + veiculo.getChave());
		System.out.println("Lista de veiculos: " + veiculos.chaveExistente(veiculo).size());
		if (veiculo.getChave().toUpperCase().equals("NÃO") || veiculo.getChave().toUpperCase().equals("")) {
			veiculos.editar(veiculo);
			try {
				FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			if (veiculos.chaveExistente(veiculo).size() > 0) {
				FacesContext.getCurrentInstance().addMessage("message", new FacesMessage(FacesMessage.SEVERITY_ERROR, "","Local da Chave já ocupado"));
			}else{
				veiculos.editar(veiculo);
				try {
					FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	public String ocorrencia(ActionEvent event){
		Veiculo veiculo = (Veiculo) event.getComponent().getAttributes().get("codigo");
		String codigo = veiculo.getCodigo().toString();
		return "Ocorrencia?codigo="+codigo+"faces-redirect=true";
	}

	public void excluir(Veiculo veiculo){
		Veiculos veiculos = this.repositorios.getveiculos();
		veiculos.remover(veiculo);
		this.init();
	}
	
	 public void listenFilter(FilterEvent event) {
	        // update datasource
	        Map<String, Object> tempString = event.getFilters();
	        System.out.println("size filter: "+ tempString.size());
	        FacesUtil util = new FacesUtil();
	        
	        
	        
	        for (String key : tempString.keySet()) {
	            System.out.println("key: " + key + " \t values: "
	                    + tempString.get(key).toString().toUpperCase());
	            
	            valor = tempString.get(key).toString().toUpperCase();
	            
	        }
	        this.setValor(valor);
	        Map<String, Map<String, String>> mapaRelatorioParametroInstancia = new HashMap<String, Map<String, String>>();
	        Map<String, String> mapaRelatorioParametro = new HashMap<String, String>();
	        mapaRelatorioParametroInstancia = util.escolherRelatorio2(tempString);
	        
	        for (String key : mapaRelatorioParametroInstancia.keySet()) {
	            System.out.println("Parametro: " + key + " \t Relatorio: "
	                    + mapaRelatorioParametroInstancia.get(key).toString());
	            instancia = key;
	            
	            mapaRelatorioParametro = mapaRelatorioParametroInstancia.get(key);
	            
	            for (String key2 : mapaRelatorioParametro.keySet()) {
	            	System.out.println("Parametro2: " + key2 + " \t Relatorio: "
		                    + mapaRelatorioParametro.get(key2).toString());
	            	parametro = key2;
	            	relatorio = mapaRelatorioParametro.get(key2).toString();
	            }
	            
	            
	            //relatorio = mapaRelatorioParametro.get(key).toString();
	        }
	        this.setInstancia(instancia);
	        this.setParametro(parametro);
	        this.setRelatorio(relatorio);
	    }
	
	//public void gerarRelatorio(ActionEvent event) throws JRException, IOException{
	 public String gerarRelatorio() throws JRException, IOException, ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException {
		 
		Cor cor = null;
		Cores cores = null;
		
		Modelo modelo = null;
		Modelos modelos = null;
				
		Fabricante fabricante = null;
		Fabricantes fabricantes = null;
		
		Seguro seguro = null;
		Seguros seguros = null;
		
		
		ConnectionFactory conexao = new ConnectionFactory();
		 String reportSrcFile = this.getRelatorio();
		 
		
	    // First, compile jrxml file.
	    JasperReport jasperReport =    JasperCompileManager.compileReport(reportSrcFile);
	 
	    Connection conn = conexao.getConnection();
	    
	    // Parameters for report
        Map<String, Object> parameters = new HashMap<String, Object>();
        
        
        if (this.getParametro().toString().equals("codigo_cor")) {
			cor = new Cor();
			cores = this.repositorios.getCores();
			cor = cores.pegaCodigo(this.getValor());
			System.out.println("Parametro utilizado: " + this.getParametro());
			System.out.println("Codigo da cor: " + cor.getCodigo());
			
			parameters.put(this.getParametro().toString(), cor.getCodigo());
		}else if (this.getParametro().toString().equals("codigo_modelo")) {
			modelo = new Modelo();
			modelos = this.repositorios.getModelos();
			modelo = modelos.pegaCodigo(this.getValor());
			
			System.out.println("Parametro utilizado: " + this.getParametro());
			System.out.println("Codigo do modelo: " + modelo.getCodigo());
			
			parameters.put(this.getParametro().toString(), modelo.getCodigo());
		}else if (this.getParametro().toString().equals("codigo_fabricante")) {
			fabricante = new Fabricante();
			fabricantes = this.repositorios.getFabricantes();
			fabricante = fabricantes.pegaCodigo(this.getValor());
			
			System.out.println("Parametro utilizado: " + this.getParametro());
			System.out.println("Codigo do fabricante: " + fabricante.getCodigo());
			
			parameters.put(this.getParametro().toString(), fabricante.getCodigo());
		}else if (this.getParametro().toString().equals("codigo_seguro")) {
			seguro = new Seguro();
			seguros = this.repositorios.getSeguros();
			seguro = seguros.pegaCodigo(this.getValor());
			
			System.out.println("Parametro utilizado: " + this.getParametro());
			System.out.println("Codigo do seguro: " + seguro.getCodigo());
			
			parameters.put(this.getParametro().toString(), seguro.getCodigo());
		}
        
        JasperPrint print = JasperFillManager.fillReport(jasperReport,
                parameters, conn);
         
        // PDF Exportor.
        JRPdfExporter exporter = new JRPdfExporter();
 
        ExporterInput exporterInput = new SimpleExporterInput(print);
        // ExporterInput
        exporter.setExporterInput(exporterInput);
	    
        //ExporterOutput
        /*OutputStreamExporterOutput exporterOutput = new SimpleOutputStreamExporterOutput(
                "/var/lib/tomcat8/webapps/Deprov/resources/relatorios/parametros/1/relatorio.pdf");*/
        
        OutputStreamExporterOutput exporterOutput = new SimpleOutputStreamExporterOutput(
                      "/opt/tomcat/webapps/Deprov/resources/relatorios/parametros/1/relatorio.pdf");
        
        // Output
        exporter.setExporterOutput(exporterOutput);
        
		 
        SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();
        exporter.setConfiguration(configuration);
        exporter.exportReport();	
        System.out.print("Relatorio criado com sucesso!");
        
        FacesContext facesContext = FacesContext.getCurrentInstance();
	    HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();

	    response.reset();   // Algum filtro pode ter configurado alguns cabeï¿½alhos no buffer de antemï¿½o. Queremos livrar-se deles, senï¿½o ele pode colidir.
	    response.setHeader("Content-Type", "application/pdf");  // Define apenas o tipo de conteï¿½do, Utilize se necessï¿½rio ServletContext#getMimeType() para detecï¿½ï¿½o automï¿½tica com base em nome de arquivo. 
	    OutputStream responseOutputStream = response.getOutputStream();

	    //String PDF_URL = "http://sinf.policiacivil.rn.gov.br:8080/Deprov/resources/relatorios/parametros/1/relatorio.pdf";
	    String PDF_URL = "http://snmp.info.ufrn.br:8080/Deprov/resources/relatorios/parametros/1/relatorio.pdf";
		// Lï¿½ o conteï¿½do do PDF
	    URL url = new URL(PDF_URL);
	    InputStream pdfInputStream = url.openStream();

	    // Lï¿½ o conteï¿½do do PDF e grava para saï¿½da
	    byte[] bytesBuffer = new byte[2048];
	    int bytesRead;
	    while ((bytesRead = pdfInputStream.read(bytesBuffer)) > 0) {
	        responseOutputStream.write(bytesBuffer, 0, bytesRead);
	    }    
	    responseOutputStream.flush();

	    // Fecha os streams
	    pdfInputStream.close();
	    responseOutputStream.close();         
	    facesContext.responseComplete();
        
        return "index?faces-redirect=true";
        
        
        
	}
	
	
	
	public Veiculo getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(Veiculo veiculo) throws CloneNotSupportedException {
		this.veiculo = veiculo;
		if (this.veiculo == null) {
			this.veiculo = new Veiculo();
		}else{
			this.veiculo = (Veiculo) veiculo.clone();
		}
	}

	public List<Veiculo> getListaVeiculos() {
		return listaVeiculos;
	}


	public void setListaVeiculos(List<Veiculo> listaVeiculos) {
		this.listaVeiculos = listaVeiculos;
	}


	public List<Veiculo> getListaVeiculosFiltrados() {
		return listaVeiculosFiltrados;
	}


	public void setListaVeiculosFiltrados(List<Veiculo> listaVeiculosFiltrados) {
		this.listaVeiculosFiltrados = listaVeiculosFiltrados;
	}

	


	public Map<String, Object> getMapaFiltros() {
		return mapaFiltros;
	}


	public void setMapaFiltros(Map<String, Object> mapaFiltros) {
		this.mapaFiltros = mapaFiltros;
	}


	public StreamedContent getFile() {
		return file;
	}

	public void setFile(StreamedContent file) {
		this.file = file;
	}


	public String getRelatorio() {
		return relatorio;
	}


	public void setRelatorio(String relatorio) {
		this.relatorio = relatorio;
	}


	public String getParametro() {
		return parametro;
	}


	public void setParametro(String parametro) {
		this.parametro = parametro;
	}


	public String getValor() {
		return valor;
	}


	public void setValor(String valor) {
		this.valor = valor;
	}


	public Object getInstancia() {
		return instancia;
	}


	public void setInstancia(Object instancia) {
		this.instancia = instancia;
	}


	public List<Modelo> getListaModelos() {
		return listaModelos;
	}


	public void setListaModelos(List<Modelo> listaModelos) {
		this.listaModelos = listaModelos;
	}
	
	
	
}