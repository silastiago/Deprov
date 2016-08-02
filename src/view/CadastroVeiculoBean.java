package view;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import org.primefaces.model.StreamedContent;

import model.Cor;
import model.Fabricante;
import model.Modelo;
import model.Pericia;
import model.Seguro;
import model.Tipo;
import model.Veiculo;
import repository.Cores;
import repository.Fabricantes;
import repository.Modelos;
import repository.Pericias;
import repository.Seguros;
import repository.Tipos;
import repository.Veiculos;
import util.Repositorios;

@ManagedBean(name="cadastroVeiculoBean")
@ViewScoped
public class CadastroVeiculoBean implements Serializable{

	private Repositorios repositorios = new Repositorios();
	private Veiculo veiculo = new Veiculo();
	private List<Veiculo> veiculos = new ArrayList<Veiculo>();
	private List<Veiculo> veiculosFiltrados = new ArrayList<Veiculo>();
	private List<Cor> cores = new ArrayList<Cor>();
	private List<Tipo> tipos = new ArrayList<Tipo>();
	private List<Fabricante> fabricantes  = new ArrayList<Fabricante>();
	private List<Modelo> modelos = new ArrayList<Modelo>();
	private List<Seguro> seguros = new ArrayList<Seguro>();
	private List<Pericia> pericias = new ArrayList<Pericia>();
	private StreamedContent file;
	
	
	@PostConstruct
	public void init(){
		Veiculos veiculos = this.repositorios.getveiculos();
		Cores cores = this.repositorios.getCores();
		Tipos tipos = this.repositorios.getTipos();
		Fabricantes fabricantes = this.repositorios.getFabricantes();
		Modelos modelos = this.repositorios.getModelos();
		Seguros seguros = this.repositorios.getSeguros();
		Pericias pericias = this.repositorios.getPericias();

		
		this.veiculos = veiculos.listar();
		this.cores = cores.listar();
		this.tipos = tipos.listar();
		this.fabricantes = fabricantes.listar();
		this.modelos = modelos.listar();
		this.seguros = seguros.listar();
		this.pericias = pericias.listar();
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
	
	public void update(Veiculo veiculo){
		Veiculos veiculos = this.repositorios.getveiculos();
		veiculos.editar(veiculo);
	}

	public void excluir(Veiculo veiculo){
		Veiculos veiculos = this.repositorios.getveiculos();
		veiculos.remover(veiculo);
		this.init();
	}
	
	public void listarVeiculo(String codigo){
	int idVeiculo = Integer.parseInt(codigo);
	Veiculos IVeiculo= this.repositorios.getveiculos();
	veiculos = IVeiculo.listarPorPlaca(idVeiculo);
	}
	
	public List<Modelo> carregaModelos(ValueChangeEvent evento){
		Fabricante fab  = (Fabricante) evento.getNewValue();
		Modelos Imodelos = this.repositorios.getModelos();
		modelos =  Imodelos.porCodigoFabricante(fab.getCodigo());
		return modelos;	
	}
	
	
	/*public String gerarRelatorio(ActionEvent event) throws JRException, IOException{
		//Veiculo veiculo = (Veiculo) event.getComponent().getAttributes().get("codigo");
		
		
		//String codigo = veiculo.getCodigo().toString();
		System.out.println("iniciando metodo de geracao de relatorio");
		
		//int idVeiculo = Integer.parseInt(codigo);
		ConnectionFactory conexao = new ConnectionFactory();
		
		String reportSrcFile = "/opt/tomcat/webapps/Deprov/resources/relatorios/RelatorioVeiculo2.jrxml";
		//String reportSrcFile = "/var/lib/tomcat8/webapps/Deprov/resources/relatorios/RelatorioVeiculo.jrxml";
        // First, compile jrxml file.
        JasperReport jasperReport =    JasperCompileManager.compileReport(reportSrcFile);
 
        Connection conn = conexao.getConnection();
 
        // Parameters for report
        Map<String, Object> parameters = new HashMap<String, Object>();
        //parameters.put("codigoCor", idVeiculo);
        
        JasperPrint print = JasperFillManager.fillReport(jasperReport,
                parameters, conn);
         
        // PDF Exportor.
        JRPdfExporter exporter = new JRPdfExporter();
 
        ExporterInput exporterInput = new SimpleExporterInput(print);
        // ExporterInput
        exporter.setExporterInput(exporterInput);
 
        // ExporterOutput
        OutputStreamExporterOutput exporterOutput = new SimpleOutputStreamExporterOutput(
                "/var/lib/tomcat8/webapps/Deprov/resources/relatorios/FirstJasperReport.pdf");
        
        OutputStreamExporterOutput exporterOutput = new SimpleOutputStreamExporterOutput(
                      "/opt/tomcat/webapps/Deprov/resources/relatorios/relatorio.pdf");
        
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
	    String PDF_URL = "http://snmp.info.ufrn.br:8080/Deprov/resources/relatorios/relatorio.pdf";
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
		
	}*/
	
	
	
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
	
	public List<Veiculo> getVeiculos() {
		return veiculos;
	}

	public void setVeiculos(List<Veiculo> veiculos) {
		this.veiculos = veiculos;
	}
	
	public List<Veiculo> getVeiculosFiltrados() {
		return veiculosFiltrados;
	}

	public void setVeiculosFiltrados(List<Veiculo> veiculosFiltrados) {
		this.veiculosFiltrados = veiculosFiltrados;
	}

	public List<Cor> getCores() {
		return cores;
	}

	public void setCores(List<Cor> cores) {
		this.cores = cores;
	}

	public List<Tipo> getTipos() {
		return tipos;
	}

	public void setTipos(List<Tipo> tipos) {
		this.tipos = tipos;
	}

	public List<Fabricante> getFabricantes() {
		return fabricantes;
	}

	public void setFabricantes(List<Fabricante> fabricantes) {
		this.fabricantes = fabricantes;
	}

	public List<Modelo> getModelos() {
		return modelos;
	}

	public void setModelos(List<Modelo> modelos) {
		this.modelos = modelos;
	}

	public List<Seguro> getSeguros() {
		return seguros;
	}

	public void setSeguros(List<Seguro> seguros) {
		this.seguros = seguros;
	}

	public List<Pericia> getPericias() {
		return pericias;
	}

	public void setPericias(List<Pericia> pericias) {
		this.pericias = pericias;
	}

	public StreamedContent getFile() {
		return file;
	}

	public void setFile(StreamedContent file) {
		this.file = file;
	}	
}