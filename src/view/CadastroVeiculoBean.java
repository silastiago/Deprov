package view;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.io.IOUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

import conexao.ConnectionFactory;
import model.Cor;
import model.Fabricante;
import model.Modelo;
import model.Pericia;
import model.Seguro;
import model.Tipo;
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
import repository.Pericias;
import repository.Seguros;
import repository.Tipos;
import repository.Veiculos;
import util.Repositorios;

@ManagedBean(name="cadastroVeiculoBean")
@RequestScoped
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

	public String gerarRelatorio(String codigo) throws JRException {
		System.out.println("iunciiando metodo de geracao de relatorio");
		
		int idVeiculo = Integer.parseInt(codigo);
		ConnectionFactory conexao = new ConnectionFactory();
		
		String reportSrcFile = "C:/Users/Sinf02/workspace/Deprov/WebContent/resources/relatorios/Relatorio.jrxml";
        
        // First, compile jrxml file.
        JasperReport jasperReport =    JasperCompileManager.compileReport(reportSrcFile);
 
        Connection conn = conexao.getConnection();
 
        // Parameters for report
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("codigo_veiculo", idVeiculo);
        
        JasperPrint print = JasperFillManager.fillReport(jasperReport,
                parameters, conn);
 
        // Make sure the output directory exists.
        //File outDir = new File("C:/Users/Sinf02/Pictures");
        //outDir.mkdirs();
 
        // PDF Exportor.
        JRPdfExporter exporter = new JRPdfExporter();
 
        ExporterInput exporterInput = new SimpleExporterInput(print);
        // ExporterInput
        exporter.setExporterInput(exporterInput);
 
        // ExporterOutput
        OutputStreamExporterOutput exporterOutput = new SimpleOutputStreamExporterOutput(
                "C:/Users/Sinf02/Downloads/FirstJasperReport.pdf");
        // Output
        exporter.setExporterOutput(exporterOutput);
 
        //
        SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();
        exporter.setConfiguration(configuration);
        exporter.exportReport();	
        System.out.print("Relatorio criado com sucesso!");
        return "index?faces-redirect=true";
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
}