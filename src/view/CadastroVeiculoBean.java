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
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpServletResponse;

import org.primefaces.event.data.FilterEvent;
import org.primefaces.model.StreamedContent;

import conexao.ConnectionFactory;
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
	
	
	@PostConstruct
	public void init(){
		Veiculos veiculos = this.repositorios.getveiculos();
		this.listaVeiculos = veiculos.listar();
	}


	public String cadastrar(){
			Veiculos veiculos = this.repositorios.getveiculos();
			veiculos.salvar(veiculo);
		return "index?faces-redirect=true";
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
	        Map<String, String> mapaRelatorioParametro = new HashMap<String, String>();
	        mapaRelatorioParametro = util.escolherRelatorio(tempString);
	        
	        for (String key : mapaRelatorioParametro.keySet()) {
	            System.out.println("Parametro: " + key + " \t Relatorio: "
	                    + mapaRelatorioParametro.get(key).toString());
	            parametro = key;
	            relatorio = mapaRelatorioParametro.get(key).toString();
	        }
	        this.setParametro(parametro);
	        this.setRelatorio(relatorio);
	    }
	
	//public void gerarRelatorio(ActionEvent event) throws JRException, IOException{
	 public String gerarRelatorio() throws JRException, IOException {
		//Veiculo veiculo = (Veiculo) event.getComponent().getAttributes().get("codigo");
		
		//String codigo = veiculo.getCodigo().toString();
		//System.out.println("Codigo de veiculos: "+ veiculo.getCodigo());
		
		//int idVeiculo = Integer.parseInt(codigo);
				
		
		 ConnectionFactory conexao = new ConnectionFactory();
		 
		 String reportSrcFile = this.getRelatorio();
		 
		//String reportSrcFile = "/var/lib/tomcat8/webapps/Deprov/resources/relatorios/RelatorioVeiculo.jrxml";
	        // First, compile jrxml file.
	    JasperReport jasperReport =    JasperCompileManager.compileReport(reportSrcFile);
	 
	    Connection conn = conexao.getConnection();
	    
	    // Parameters for report
        Map<String, Object> parameters = new HashMap<String, Object>();
        
        parameters.put(this.getParametro(), this.getValor());
	    
        
        JasperPrint print = JasperFillManager.fillReport(jasperReport,
                parameters, conn);
         
        // PDF Exportor.
        JRPdfExporter exporter = new JRPdfExporter();
 
        ExporterInput exporterInput = new SimpleExporterInput(print);
        // ExporterInput
        exporter.setExporterInput(exporterInput);
	    
     // ExporterOutput
        /*OutputStreamExporterOutput exporterOutput = new SimpleOutputStreamExporterOutput(
                "/var/lib/tomcat8/webapps/Deprov/resources/relatorios/"+idVeiculo+".pdf");*/
        
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

	    response.reset();   // Algum filtro pode ter configurado alguns cabe�alhos no buffer de antem�o. Queremos livrar-se deles, sen�o ele pode colidir.
	    response.setHeader("Content-Type", "application/pdf");  // Define apenas o tipo de conte�do, Utilize se necess�rio ServletContext#getMimeType() para detec��o autom�tica com base em nome de arquivo. 
	    OutputStream responseOutputStream = response.getOutputStream();

	    //String PDF_URL = "http://sinf.policiacivil.rn.gov.br:8080/Deprov/resources/relatorios/"+idVeiculo+".pdf";
	    String PDF_URL = "http://snmp.info.ufrn.br:8080/Deprov/resources/relatorios/parametros/1/relatorio.pdf";
		// L� o conte�do do PDF
	    URL url = new URL(PDF_URL);
	    InputStream pdfInputStream = url.openStream();

	    // L� o conte�do do PDF e grava para sa�da
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
	
	
	
}