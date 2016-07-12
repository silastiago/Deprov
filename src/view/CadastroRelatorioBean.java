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
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpServletResponse;
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
import util.Repositorios;

@ManagedBean(name = "cadastroRelatorioBean")
@RequestScoped
public class CadastroRelatorioBean implements Serializable {

	private Repositorios repositorios = new Repositorios();
	private List<Veiculo> veiculos = new ArrayList<Veiculo>();
	private Veiculo veiculo = new Veiculo();
	private StreamedContent file;

	@PostConstruct
	public void init() {
		Veiculos veiculos = this.repositorios.getveiculos();
		this.veiculos = veiculos.listar();
	}

	public String gerarRelatorio(ActionEvent event) throws JRException, IOException{
		Veiculo veiculo = (Veiculo) event.getComponent().getAttributes().get("codigo");
		System.out.println("codigo do veiculo: " + veiculo.getCodigo());
		
		String codigo = veiculo.getCodigo().toString();
		System.out.println("iniciando metodo de geracao de relatorio");
		
		int idVeiculo = Integer.parseInt(codigo);
		ConnectionFactory conexao = new ConnectionFactory();
		
		String reportSrcFile = "/opt/tomcat/webapps/Deprov/resources/relatorios/RelatorioVeiculo.jrxml";
		//String reportSrcFile = "/var/lib/tomcat8/webapps/Deprov/resources/relatorios/RelatorioVeiculo.jrxml";
        // First, compile jrxml file.
        JasperReport jasperReport =    JasperCompileManager.compileReport(reportSrcFile);
 
        Connection conn = conexao.getConnection();
 
        // Parameters for report
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("codigo_veiculo", idVeiculo);
        
        JasperPrint print = JasperFillManager.fillReport(jasperReport,
                parameters, conn);
         
        // PDF Exportor.
        JRPdfExporter exporter = new JRPdfExporter();
 
        ExporterInput exporterInput = new SimpleExporterInput(print);
        // ExporterInput
        exporter.setExporterInput(exporterInput);
 
        // ExporterOutput
        /*OutputStreamExporterOutput exporterOutput = new SimpleOutputStreamExporterOutput(
                "/var/lib/tomcat8/webapps/Deprov/resources/relatorios/FirstJasperReport.pdf");*/
        
        OutputStreamExporterOutput exporterOutput = new SimpleOutputStreamExporterOutput(
                      "/opt/tomcat/webapps/Deprov/resources/relatorios/FirstJasperReport.pdf");
        
        // Output
        exporter.setExporterOutput(exporterOutput);
 
        //
        SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();
        exporter.setConfiguration(configuration);
        exporter.exportReport();	
        System.out.print("Relatorio criado com sucesso!");
        
        
        
        FacesContext facesContext = FacesContext.getCurrentInstance();
	    HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();

	    response.reset();   // Algum filtro pode ter configurado alguns cabe�alhos no buffer de antem�o. Queremos livrar-se deles, sen�o ele pode colidir.
	    response.setHeader("Content-Type", "application/pdf");  // Define apenas o tipo de conte�do, Utilize se necess�rio ServletContext#getMimeType() para detec��o autom�tica com base em nome de arquivo. 
	    OutputStream responseOutputStream = response.getOutputStream();

	    //String PDF_URL = "http://sinf.policiacivil.rn.gov.br:8080/Deprov/resources/relatorios/FirstJasperReport.pdf";
	    String PDF_URL = "http://snmp.info.ufrn.br:8080/Deprov/resources/relatorios/FirstJasperReport.pdf";
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
	
	public StreamedContent getFile() {
		return file;
	}

	public void setFile(StreamedContent file) {
		this.file = file;
	}

	public List<Veiculo> getVeiculos() {
		return veiculos;
	}

	public void setVeiculos(List<Veiculo> veiculos) {
		this.veiculos = veiculos;
	}

	public Veiculo getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}
	

}