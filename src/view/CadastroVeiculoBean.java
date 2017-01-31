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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.primefaces.event.data.FilterEvent;
import org.primefaces.model.StreamedContent;

import conexao.ConnectionFactory;
import model.Fabricante;
import model.Modelo;
import model.Pessoa;
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
import repository.Modelos;
import repository.Pessoas;
import repository.Veiculos;
import util.FacesUtil;
import util.Repositorios;

/** Esta é uma Classe concreta que une as implementacoes das interfaces e das paginas xhtml referentes a entidade Veiculo.
*   
* @author silas
* @since 15-08-2016
*/

@ManagedBean(name="cadastroVeiculoBean")
@ViewScoped
public class CadastroVeiculoBean implements Serializable{

	private Repositorios repositorios = new Repositorios();
	private Veiculo veiculo = new Veiculo();
	private List<Veiculo> listaVeiculos;
	private List<Veiculo> listaVeiculosFiltrados;
	private Map<String, Object> mapaFiltros;
	private StreamedContent file;
	private String relatorio;
	private Map<String, Object> mapaParametro;
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
	
	private HttpServletRequest getRequest() {
		FacesContext context = FacesContext.getCurrentInstance();
		return (HttpServletRequest) context.getExternalContext().getRequest();
	}
	
	public void cadastrar(){
		Veiculos veiculos = this.repositorios.getveiculos();
		Pessoas pessoas = this.repositorios.getPessoas();
		
		String placas = "";
		Pessoa pessoa = new Pessoa();
		String login = this.getRequest().getSession().getAttribute("usuario").toString();
		pessoa = pessoas.retornaPessoa(login);
		
		System.out.println("Chave: " + veiculo.getChave());
		System.out.println("Lista de veiculos: " + veiculos.chaveExistenteEditar(veiculo).size());
		
		if (veiculo.getChave().toUpperCase().equals("NÃO") || veiculo.getChave().toUpperCase().equals("NAO") || 
				veiculo.getChave().toUpperCase().equals("Não") || veiculo.getChave().toUpperCase().equals("Nao") ||
				veiculo.getChave().toUpperCase().equals("não") || veiculo.getChave().toUpperCase().equals("nao") || 
				veiculo.getChave().toUpperCase().equals("")) {
			veiculo.setPessoa(pessoa);
			veiculos.salvar(veiculo);
			
			FacesContext.getCurrentInstance().addMessage("message", new FacesMessage(FacesMessage.SEVERITY_INFO, "","Veiculo Cadastrado"));
			
			/*try {
				FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
			} catch (IOException e) {
				
				e.printStackTrace();
			}*/
		}else{
			
			if (veiculos.chaveExistenteCadastrar(veiculo).size() > 0) {
				List<Veiculo> listaVeiculo = veiculos.chaveExistenteEditar(veiculo);	
				for (int i = 0; i < listaVeiculo.size(); i++) {
					placas = placas + " " + listaVeiculo.get(i).getPlaca();
				}
				FacesContext.getCurrentInstance().addMessage("message", new FacesMessage(FacesMessage.SEVERITY_ERROR, "","Local da Chave já ocupado por veiculos de placas "+ placas));
			}else{
				veiculo.setPessoa(pessoa);
				veiculos.salvar(veiculo);
				FacesContext.getCurrentInstance().addMessage("message", new FacesMessage(FacesMessage.SEVERITY_INFO, "","Veiculo Cadastrado"));
				/*try {
					FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
				} catch (IOException e) {
					
					e.printStackTrace();
				}*/
			}
		}
	}
	
	
	public void editar(){
		Veiculos veiculos = this.repositorios.getveiculos();
		Pessoas pessoas = this.repositorios.getPessoas();
		
		String placas = "";
		Pessoa pessoa = new Pessoa();
		String login = this.getRequest().getSession().getAttribute("usuario").toString();
		pessoa = pessoas.retornaPessoa(login);		
		
		
		System.out.println("Chave: " + veiculo.getChave());
		System.out.println("Lista de veiculos: " + veiculos.chaveExistenteEditar(veiculo).size());
		if (veiculo.getChave().toUpperCase().equals("NÃO") || veiculo.getChave().toUpperCase().equals("NAO") || 
				veiculo.getChave().toUpperCase().equals("Não") || veiculo.getChave().toUpperCase().equals("Nao") ||
				veiculo.getChave().toUpperCase().equals("não") || veiculo.getChave().toUpperCase().equals("nao") || 
				veiculo.getChave().toUpperCase().equals("")) {
			veiculo.setPessoa(pessoa);
			veiculos.editar(veiculo);
			FacesContext.getCurrentInstance().addMessage("message", new FacesMessage(FacesMessage.SEVERITY_INFO, "","Veiculo Editado"));
			
			/*try {
				FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
			} catch (IOException e) {
				
				e.printStackTrace();
			}*/
			
		}else{
			if (veiculos.chaveExistenteEditar(veiculo).size() > 0) {
				List<Veiculo> listaVeiculo = veiculos.chaveExistenteEditar(veiculo);	
				for (int i = 0; i < listaVeiculo.size(); i++) {
					placas = placas + " " + listaVeiculo.get(i).getPlaca();
				}
				FacesContext.getCurrentInstance().addMessage("message", new FacesMessage(FacesMessage.SEVERITY_ERROR, "","Local da Chave já ocupado por veiculos de placas "+ placas));
			}else{
				veiculo.setPessoa(pessoa);
				veiculos.editar(veiculo);
				FacesContext.getCurrentInstance().addMessage("message", new FacesMessage(FacesMessage.SEVERITY_INFO, "","Veiculo Editado"));
				/*try {
					FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
				} catch (IOException e) {
					
					e.printStackTrace();
				}*/
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
	        ArrayList<String> listaObjetos = new ArrayList<String>();
	        ArrayList<String> listaValores = new ArrayList<String>();
	        String relatorio = "";
	        Map<String, Object> mapa = null;
	        
	        
	        System.out.println("size filter: "+ tempString.size());
	        FacesUtil util = new FacesUtil();
	        
	        if (tempString.size() == 0) {
	        	//relatorio = "/opt/tomcat/webapps/Deprov/resources/relatorios/parametros/0/Todos.jrxml";
				relatorio = "/var/lib/tomcat/webapps/Deprov/resources/relatorios/parametros/0/Todos.jrxml";
			}else{
	        
	        for (String key : tempString.keySet()) {
	            System.out.println("key: " + key + " \t values: "
	                    + tempString.get(key).toString().toUpperCase());
	            
	        
	        listaObjetos.add(key);
	        listaValores.add(tempString.get(key).toString().toUpperCase());
	        	}
	        relatorio = util.escolherRelatorio(listaObjetos);
	        mapa = util.retornarParametros(listaObjetos, listaValores);
			}
	        
	        
	        
	        System.out.println("Relatorio: " + relatorio);
	            /*for (String key : mapaRelatorioParametro.keySet()) {
	            	System.out.println("Nome do codigo: " + key + " \t Relatorio: "
		                    + mapaRelatorioParametro.get(key).toString());
	            	parametro = key;
	            	relatorio = mapaRelatorioParametro.get(key).toString();
	            }*/
	        
	        
	        this.setMapaParametro(mapa);
	        this.setRelatorio(relatorio);
	    }
	 
	 
	 public String gerarRelatorio() throws JRException, IOException, ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException {
		
		ConnectionFactory conexao = new ConnectionFactory();
		String reportSrcFile = this.getRelatorio();
		 
	    // First, compile jrxml file.
	    JasperReport jasperReport =    JasperCompileManager.compileReport(reportSrcFile);
	 
	    Connection conn = conexao.getConnection();
	    
	    // Parameters for report
        Map<String, Object> parameters = new HashMap<String, Object>();
        
        parameters = this.getMapaParametro();
        
        JasperPrint print = JasperFillManager.fillReport(jasperReport,
                parameters, conn);
         
        // PDF Exportor.
        JRPdfExporter exporter = new JRPdfExporter();
 
        ExporterInput exporterInput = new SimpleExporterInput(print);
        // ExporterInput
        exporter.setExporterInput(exporterInput);
	    
        //ExporterOutput
        OutputStreamExporterOutput exporterOutput = new SimpleOutputStreamExporterOutput(
                "/var/lib/tomcat/webapps/Deprov/resources/relatorios/parametros/1/relatorio.pdf");
        
        /*OutputStreamExporterOutput exporterOutput = new SimpleOutputStreamExporterOutput(
                      "/opt/tomcat/webapps/Deprov/resources/relatorios/parametros/1/relatorio.pdf");*/
        
        // Output
        exporter.setExporterOutput(exporterOutput);
        	 
        SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();
        exporter.setConfiguration(configuration);
        exporter.exportReport();	
        System.out.print("Relatorio criado com sucesso!");
        
        FacesContext facesContext = FacesContext.getCurrentInstance();
	    HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();

	    //response.reset();   // Algum filtro pode ter configurado alguns cabeï¿½alhos no buffer de antemï¿½o. Queremos livrar-se deles, senï¿½o ele pode colidir.
	    response.setHeader("Content-Type", "application/pdf");  // Define apenas o tipo de conteï¿½do, Utilize se necessï¿½rio ServletContext#getMimeType() para detecï¿½ï¿½o automï¿½tica com base em nome de arquivo. 
	    OutputStream responseOutputStream = response.getOutputStream();

	    String PDF_URL = "http://sinf.policiacivil.rn.gov.br:8080/Deprov/resources/relatorios/parametros/1/relatorio.pdf";
	    //String PDF_URL = "http://snmp.info.ufrn.br:8080/Deprov/resources/relatorios/parametros/1/relatorio.pdf";
		// Lï¿½ o conteúdo do PDF
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



	public Map<String, Object> getMapaParametro() {
		return mapaParametro;
	}


	public void setMapaParametro(Map<String, Object> mapaParametro) {
		this.mapaParametro = mapaParametro;
	}

	public List<Modelo> getListaModelos() {
		return listaModelos;
	}

	public void setListaModelos(List<Modelo> listaModelos) {
		this.listaModelos = listaModelos;
	}	
}