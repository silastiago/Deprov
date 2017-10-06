package pcrn.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
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
import org.primefaces.model.chart.PieChartModel;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import pcrn.model.Fabricante;
import pcrn.model.Modelo;
import pcrn.model.Pessoa;
import pcrn.model.Situacao;
import pcrn.model.Tarefa;
import pcrn.model.Veiculo;
import pcrn.security.UsuarioSistema;
import pcrn.services.ModeloService;
import pcrn.services.RelatorioService;
import pcrn.services.SituacaoService;
import pcrn.services.TarefaService;
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
	private TarefaService tarefaService;

	@Inject
	private SituacaoService situacaoService;
	
	
	@Inject
	private ModeloService modeloService;
	
	@Inject
	private RelatorioService relatorioService;
	
	@Inject
	private HttpServletResponse response;
	
	@Inject
	private EntityManager manager;
	
	@Inject
	private FacesContext facesContext;
	
	private Veiculo veiculo = new Veiculo();
	private Veiculo veiculoSelecionado;
	private Fabricante fabricante;
	
	private List<Veiculo> listaVeiculos;
	private List<Veiculo> listaVeiculosFiltrados;
	private Map<String, Object> mapaFiltros;
	private StreamedContent file;
	private String relatorio;
	private Map<String, Object> mapaParametro;
	private List<Modelo> listaModelos;
	private PieChartModel pieModel;
	private List<Boolean> condicional;
	private List<Tarefa> listaTarefa = new ArrayList<Tarefa>();
	
	@PostConstruct
	public void init(){
		createPieModels();
	}
	
	
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
	        
	        for (String key : tempString.keySet()) {
	            //System.out.println("key: " + key + " \t values: " + tempString.get(key).toString().toUpperCase());
		        listaObjetos.add(key);
		        listaValores.add(tempString.get(key).toString().toUpperCase());
	        }
	        
	        relatorio = relatorioService.escolherRelatorio(listaObjetos);
	        mapa = relatorioService.retornarParametros(listaObjetos, listaValores);
	        
	        this.setMapaParametro(mapa);
	        this.setRelatorio(relatorio);
	    }
	
	 
	 public void gerarRelatorio() {
			Map<String, Object> parametros = new HashMap<>();
			parametros = this.getMapaParametro();
			String caminhoRelatorio = this.getRelatorio();
			
			//System.out.println("Caminho relatorio: " + caminhoRelatorio);
			
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
	 
	 
	 public List<Veiculo> listarVeiculosSemFoto(){
		 
		 listaVeiculos = veiculoService.listarVeiculosSemFoto();
		 
		 return listaVeiculos;
	 }
	 
	 
	 
	 public String redirecionaParaFoto(Veiculo veiculo){
			
		 return "/site/Foto/Edicao/Foto.xhtml?codigoVeiculo="+veiculo.getCodigo()+"faces-redirect=true";
		 
	}
	 
	public String redirecionaParaOcorrencia(Veiculo veiculo){
			
		 return "/site/Ocorrencia/Consulta/Ocorrencia.xhtml?codigoVeiculo="+veiculo.getCodigo()+"faces-redirect=true";
	} 
	 
	public String redirecionaTarefa(){
		
		 return "/site/Tarefa/Consulta/Tarefa.xhtml?codigoVeiculo="+veiculoSelecionado.getCodigo()+"faces-redirect=true";
	}
	
	public String redirecionaCadastroTarefa(){
		
		 return "/site/Veiculo/Consulta/VeiculoTarefa.xhtml"+"faces-redirect=true";
	}
	
	 public String novo(){
			
			String pagina = "/site/Veiculo/Novo/Veiculo.xhtml?faces-redirect=true";
			
			return pagina;
	} 
	
	 public String edicao(){
			
			String pagina = "/site/Veiculo/Edicao/Veiculo.xhtml?codigoVeiculo="+veiculoSelecionado.getCodigo()+"faces-redirect=true";
			
			return pagina;
		} 
	 
	 
	 private void createPieModels() {
	        createPieModel();
	    }
	 
	 
	 private void createPieModel() {
		 	List<Situacao> listaSituacao = new ArrayList<Situacao>();
		 	pieModel = new PieChartModel();
		 	int numeroVeiculos = 0;
		 	String label = "";
		 	
		 	listaSituacao = situacaoService.listar();
		 	Map<String, Number> dataFormat = new HashMap<String, Number>();  
		 	
		 	
		 	for (int i = 0; i < listaSituacao.size(); i++) {
		 		numeroVeiculos = veiculoService.listarVeiculosComSituacao(listaSituacao.get(i).getCodigo()).size();
		 		label = listaSituacao.get(i).getSituacao() + " - " + numeroVeiculos ;
		 		dataFormat.put(label, numeroVeiculos);
				pieModel.set(label , numeroVeiculos);	 		
		 	} 
		 	
	        pieModel.setTitle("Grafico de Veiculos");
	        pieModel.setLegendPosition("w");
	        pieModel.setFill(true);
	        pieModel.setShowDataLabels(true);
	        
	    }
	 
	public List<Veiculo> listarVeiculosDataAtual(){
		listaVeiculos = new ArrayList<Veiculo>();
		
		listaTarefa = tarefaService.porCodigoVeiculoEData();
		
		for (int i = 0; i < listaTarefa.size(); i++) {			
			listaVeiculos.add(listaTarefa.get(i).getVeiculo());
		}
		
		return listaVeiculos;
		
	}
	
	
	 
	public PieChartModel getPieModel() {
		return pieModel;
	}

	public void carregaModelos(){
		
		if (veiculo.getFabricante() != null) {
			System.out.println("Não NULO");
			
			listaModelos = modeloService.buscarModelos(veiculo.getFabricante());
		}else{
			
			System.out.println("NULO");
			listaModelos = new ArrayList<Modelo>();
		} 
	}
	
	public Boolean compara(){	
		System.out.println("Lista de tarefas: " + listaTarefa.size());
		
		
		return false;
		
	}
	
	
	
	
	public List<Tarefa> getListaTarefa() {
		return listaTarefa;
	}


	public void setListaTarefa(List<Tarefa> listaTarefa) {
		this.listaTarefa = listaTarefa;
	}


	public List<Boolean> getCondicional() {
		return condicional;
	}


	public void setCondicional(List<Boolean> condicional) {
		this.condicional = condicional;
	}


	public Veiculo getVeiculoSelecionado() {
		return veiculoSelecionado;
	}

	public void setVeiculoSelecionado(Veiculo veiculoSelecionado) {
		this.veiculoSelecionado = veiculoSelecionado;
	}

	public Fabricante getFabricante() {
		return fabricante;
	}

	public void setFabricante(Fabricante fabricante) {
		this.fabricante = fabricante;
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
		
		if(this.veiculo!= null){
			carregaModelos();
		}
		
		return listaModelos;
		}

	public void setListaModelos(List<Modelo> listaModelos) {
			this.listaModelos = listaModelos;
		}	
	
}
