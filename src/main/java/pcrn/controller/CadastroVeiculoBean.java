package pcrn.controller;

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

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.primefaces.event.data.FilterEvent;
import org.primefaces.model.StreamedContent;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

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
import pcrn.model.Fabricante;
import pcrn.model.Modelo;
import pcrn.model.Pessoa;
import pcrn.model.Veiculo;
import pcrn.security.UsuarioSistema;
import pcrn.services.VeiculoService;
import pcrn.util.FacesUtil;
import pcrn.util.report.ExecutorRelatorio;

@Named
@ViewScoped
public class CadastroVeiculoBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private VeiculoService veiculoService;

	@Inject
	private HttpServletResponse response;
	
	@Inject
	private EntityManager manager;
	
	@Inject
	private FacesContext facesContext;
	
	private Veiculo veiculo = new Veiculo();
	private List<Veiculo> listaVeiculos;
	private List<Veiculo> listaVeiculosFiltrados;
	private Map<String, Object> mapaFiltros;
	private StreamedContent file;
	private String relatorio;
	private Map<String, Object> mapaParametro;
	private List<Modelo> listaModelos;
	
	public void lerFabricante(ValueChangeEvent evento){
		Fabricante fabricante = (Fabricante) evento.getNewValue();
		this.veiculo.setFabricante(fabricante);
		//return codigoSistemaOperacional;
	}
	
	public void cadastrar(){		
		String placas = "";
		Pessoa pessoa = new Pessoa();
		
		pessoa = getUsuarioLogado().getPessoa();
		
		//System.out.println("Chave: " + veiculo.getChave());
		//System.out.println("Lista de veiculos: " + veiculoService.chaveExistenteEditar(veiculo).size());
		
		if (veiculo.getChave().toUpperCase().equals("NAO") || 
				veiculo.getChave().toUpperCase().equals("NÃO") ||  
				veiculo.getChave().toUpperCase().equals("")) {
			
			veiculo.setPessoa(pessoa);
			
			if (veiculoService.placaxistenteCadastrar(veiculo).size() > 0) {
				FacesContext.getCurrentInstance().addMessage("message", new FacesMessage(FacesMessage.SEVERITY_WARN, "","Esta placa j� foi cadastrada em outro carro"));
			}			
			veiculoService.salvar(veiculo);
			
			FacesContext.getCurrentInstance().addMessage("message", new FacesMessage(FacesMessage.SEVERITY_INFO, "","Veiculo Cadastrado"));
			
			
		}else{
			
			if (veiculoService.chaveExistenteCadastrar(veiculo).size() > 0) {
				List<Veiculo> listaVeiculo = veiculoService.chaveExistenteEditar(veiculo);	
				for (int i = 0; i < listaVeiculo.size(); i++) {
					placas = placas + " " + listaVeiculo.get(i).getPlaca();
				}
				FacesContext.getCurrentInstance().addMessage("message", new FacesMessage(FacesMessage.SEVERITY_ERROR, "","Local da Chave ja ocupado por veiculos de placas "+ placas));
			}else{
				veiculo.setPessoa(pessoa);
				
				if (veiculoService.placaxistenteCadastrar(veiculo).size() > 0) {
					FacesContext.getCurrentInstance().addMessage("message", new FacesMessage(FacesMessage.SEVERITY_WARN, "","Esta placa ja foi cadastrada em outro carro"));
				}
				
				veiculoService.salvar(veiculo);
				FacesContext.getCurrentInstance().addMessage("message", new FacesMessage(FacesMessage.SEVERITY_INFO, "","Veiculo Cadastrado"));
				FacesContext.getCurrentInstance()
			      .getExternalContext()
			      .getFlash().setKeepMessages(true);
				
				try {
					FacesContext.getCurrentInstance().getExternalContext().redirect("Veiculo2.xhtml");
				} catch (IOException e) {
					
					e.printStackTrace();
				}
			}
		}
	}
	
	
	public void editar(){
		
		String placas = "";
		Pessoa pessoa = new Pessoa();
		pessoa = getUsuarioLogado().getPessoa();		
		
		
		//System.out.println("Chave: " + veiculo.getChave());
		//System.out.println("Lista de veiculos: " + veiculoService.chaveExistenteEditar(veiculo).size());
		
		if (veiculo.getChave().toUpperCase().equals("NAO") || 
				veiculo.getChave().toUpperCase().equals("NÃO") || 
				veiculo.getChave().toUpperCase().equals("")) {
			
			veiculo.setPessoa(pessoa);
			veiculoService.editar(veiculo);
			
			FacesContext.getCurrentInstance().addMessage("message", new FacesMessage(FacesMessage.SEVERITY_INFO, "","Veiculo Editado"));
			
		}else{
			if (veiculoService.chaveExistenteEditar(veiculo).size() > 0) {
				List<Veiculo> listaVeiculo = veiculoService.chaveExistenteEditar(veiculo);	
				for (int i = 0; i < listaVeiculo.size(); i++) {
					placas = placas + " " + listaVeiculo.get(i).getPlaca();
				}
				FacesContext.getCurrentInstance().addMessage("message", new FacesMessage(FacesMessage.SEVERITY_ERROR, "","Local da Chave ja ocupado por veiculos de placas "+ placas));
			}else{
				veiculo.setPessoa(pessoa);				
				veiculoService.editar(veiculo);
				FacesContext.getCurrentInstance().addMessage("message", new FacesMessage(FacesMessage.SEVERITY_INFO, "","Veiculo Editado"));
				
			}
		}
	}
	
	private UsuarioSistema getUsuarioLogado() {
		UsuarioSistema usuario = null;
		
		UsernamePasswordAuthenticationToken auth = (UsernamePasswordAuthenticationToken) 
				FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();
		
		if (auth != null && auth.getPrincipal() != null) {
			usuario = (UsuarioSistema) auth.getPrincipal();
		}
		
		return usuario;
	}
	
	public String ocorrencia(ActionEvent event){
		Veiculo veiculo = (Veiculo) event.getComponent().getAttributes().get("codigo");
		String codigo = veiculo.getCodigo().toString();
		return "Ocorrencia?codigo="+codigo+"faces-redirect=true";
	}

	public void excluir(Veiculo veiculo){
		veiculoService.remover(veiculo);
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
	        
	        for (String key : tempString.keySet()) {
	            System.out.println("key: " + key + " \t values: "
	                    + tempString.get(key).toString().toUpperCase());
	            
	        
	        listaObjetos.add(key);
	        listaValores.add(tempString.get(key).toString().toUpperCase());
	        	}
	        relatorio = util.escolherRelatorio(listaObjetos);
	        System.out.println("Relatorio: " + relatorio);
	        mapa = util.retornarParametros(listaObjetos, listaValores);
	        
	        
	            /*for (String key : mapaRelatorioParametro.keySet()) {
	            	System.out.println("Nome do codigo: " + key + " \t Relatorio: "
		                    + mapaRelatorioParametro.get(key).toString());
	            	parametro = key;
	            	relatorio = mapaRelatorioParametro.get(key).toString();
	            }*/
	        
	        
	        this.setMapaParametro(mapa);
	        this.setRelatorio(relatorio);
	    }
	
	 
	 public void gerarRelatorio() {
			Map<String, Object> parametros = new HashMap<>();
			parametros = this.getMapaParametro();
			String caminhoRelatorio = this.getRelatorio();
			
			System.out.println("Caminho relatorio: " + caminhoRelatorio);
			
			ExecutorRelatorio executor = new ExecutorRelatorio(caminhoRelatorio,
					this.response, parametros, "Relatorio.pdf");
			
			Session session = manager.unwrap(Session.class);
			session.doWork(executor);
			
			if (executor.isRelatorioGerado()) {
				facesContext.responseComplete();
			} else {
				FacesUtil.addErrorMessage("A execução do relatório não retornou dados.");
			}
		}
	 
	 
	 public void gerarRelatorioVeiculo(String codigoVeiculo) {
			Map<String, Object> parametros = new HashMap<>();
			parametros.put("codigo_veiculo", codigoVeiculo);
			System.out.println("Codigo veiculo: " + codigoVeiculo);
			ExecutorRelatorio executor = new ExecutorRelatorio("/relatorios/RelatorioVeiculo.jasper",
					this.response, parametros, "Relatorio.pdf");
			
			Session session = manager.unwrap(Session.class);
			session.doWork(executor);
			
			if (executor.isRelatorioGerado()) {
				facesContext.responseComplete();
			} else {
				FacesUtil.addErrorMessage("A execução do relatório não retornou dados.");
			}
		}
	 
	 
	 public List<Veiculo> listarVeiculos(){
		 
		 listaVeiculos = veiculoService.listar();
		 
		 return listaVeiculos;
	 }
	 
	 
	 
	 public String redirecionaParaFoto(Veiculo veiculo){
			
		 return "/site/Foto/Edicao/Foto.xhtml?codigoVeiculo="+veiculo.getCodigo()+"faces-redirect=true";
		 
	}
	 
	 public String redirecionaParaOcorrencia(Veiculo veiculo){
			
		 return "/site/Ocorrencia/Consulta/Ocorrencia.xhtml?codigoVeiculo="+veiculo.getCodigo()+"faces-redirect=true";
	} 
	 
	
	 public String novo(){
			
			String pagina = "/site/Veiculo/Novo/Veiculo.xhtml?faces-redirect=true";
			
			return pagina;
		} 
	 
	 public Veiculo getVeiculo() {
			return veiculo;
		}


		public void setVeiculo(Veiculo veiculo) {
			this.veiculo = veiculo;
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
