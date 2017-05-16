package pcrn.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import pcrn.model.Tarefa;
import pcrn.model.Veiculo;
import pcrn.services.TarefaService;
import pcrn.util.FacesUtil;

@Named
@ViewScoped
public class CadastroTarefaBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	
	@Inject
	private TarefaService tarefaService;
	
	private Tarefa tarefa = new Tarefa();
	private Tarefa tarefaSelecionada;
	private List<Tarefa> tarefas = new ArrayList<Tarefa>();
	private List<Tarefa> listaTodasTarefas = new ArrayList<Tarefa>();
	private Veiculo veiculo = new Veiculo();
	
	
	@PostConstruct
	public void init(){
		//tarefas = this.listarTarefas();
	}
	
	
	public String cadastrar(String codigoVeiculo) throws IOException {
		
		int idVeiculo = Integer.parseInt(codigoVeiculo);
		veiculo.setCodigo(idVeiculo);
		tarefa.setVeiculo(veiculo);
		tarefaService.salvar(tarefa);
		FacesUtil.addInfoMessage("Tarefa cadastrada com sucesso");		
		FacesUtil.contextFlash();
		
		return "/site/Tarefa/Consulta/Tarefa.xhtml?codigoVeiculo="+codigoVeiculo+"faces-redirect=true";
	}
	
	public String editar(String codigoVeiculo) throws IOException {
		
		int idVeiculo = Integer.parseInt(codigoVeiculo);
		veiculo.setCodigo(idVeiculo);
		tarefa.setVeiculo(veiculo);
		tarefaService.salvar(tarefa);
		FacesUtil.addInfoMessage("Tarefa alterada com sucesso");		
		FacesUtil.contextFlash();
		
	    //FacesContext.getCurrentInstance().getExternalContext().redirect("/Deprov/site/Ocorrencia/Consulta/Ocorrencia.xhtml?codigoVeiculo="+codigoVeiculo);
	    return "/site/Tarefa/Consulta/Tarefa.xhtml?codigoVeiculo="+codigoVeiculo+"faces-redirect=true";
	}
	
	/** Este metodo redireciona para a pagina de ocorrencias de um determinado veiculo.
	 * 	@param codigo, este codigo � o identificador do veiculo.
	 * 	@return, retorna a pagina das ocorrencias daquele veiculo.
	*/
	public String voltar(String codigoVeiculo){
		
		String pagina = "/site/Veiculo/Edicao/Veiculo.xhtml?codigoVeiculo="+codigoVeiculo+"faces-redirect=true";
		
		return pagina;
	}
	
	
	public String novo(String codigoVeiculo){
		
		String pagina = "/site/Tarefa/Novo/Tarefa.xhtml?codigoVeiculo="+codigoVeiculo+"faces-redirect=true";
		
		return pagina;
	}
	
	public String edicao(){
		
		String pagina = "/site/Tarefa/Edicao/Tarefa.xhtml?codigoTarefa="+tarefaSelecionada.getCodigo()+"&codigoVeiculo="+tarefaSelecionada.getVeiculo().getCodigo()+"faces-redirect=true";
		
		return pagina;
	}
	
	public String redirecionarParaPaginaVeiculo(){
		
		String pagina = "/site/Veiculo/Edicao/Veiculo.xhtml?codigoVeiculo="+tarefaSelecionada.getVeiculo().getCodigo()+"faces-redirect=true";
		
		return pagina;
	}
	
	
	
	/** Este metodo remove uma ocorrencia de um determinado veiculo.
	 * 	@param codigo, este codigo � o identificador da ocorrencia.
	*/
	public String excluir(String codigoVeiculo) throws IOException {
		
		
		System.out.println("Entrou no metodo de remoção");
		
		tarefaService.remover(tarefaSelecionada);
		FacesUtil.addInfoMessage("Tarefa: " +tarefaSelecionada.getDescricaoTarefa()+ " removida com sucesso");
		FacesUtil.contextFlash();
		
		return "/site/Ocorrencia/Consulta/Ocorrencia.xhtml?codigoVeiculo="+codigoVeiculo+"faces-redirect=true";
		
	}
	
	/** Este metodo lista todoas as ocorrencias de um determinado veiculo.
	 * 	@return List<Ocorrencia>, retorna a lista de ocorrencias daquele veiculo.
	*/
	public List<Tarefa> listarTarefas(int codigoVeiculo){
		
		//String codigo = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("codigoVeiculo");
		//Convertendo a string codigo que o id do veiculo para um inteiro.
		//int idVeiculo = Integer.parseInt(codigo);
		//A lista de ocorrencias recebe as ocorrencias daquele veiculo que se passou a identificador.
		tarefas = tarefaService.porCodigoVeiculo(codigoVeiculo);
		//retorna a lista das ocorrencias daquele veiculo.
		return tarefas;
	}

	
	/** Este metodo lista todoas as ocorrencias de um determinado veiculo.
	 * 	@return List<Ocorrencia>, retorna a lista de ocorrencias daquele veiculo.
	*/
	public List<Tarefa> listarTodasTarefas(){
		
		listaTodasTarefas = tarefaService.porCodigoVeiculoData();
		
		return listaTodasTarefas;
	}

	
	public Boolean comparaData(Tarefa tarefa){
		Boolean condicao = false;
		String dataHoje = FacesUtil.retornaDataAtualString();
		
		if (FacesUtil.retornaDataAtualDate(dataHoje).before(tarefa.getDataTarefa())) {
			condicao = true;
		}
		
		System.out.println("Tarefa: " + tarefa.getDescricaoTarefa());
		System.out.println("Condicao: " + condicao);
		return condicao;
	}
	
	public List<Tarefa> getListaTodasTarefas() {
		return listaTodasTarefas;
	}


	public void setListaTodasTarefas(List<Tarefa> listaTodasTarefas) {
		this.listaTodasTarefas = listaTodasTarefas;
	}


	public Tarefa getTarefa() {
		return tarefa;
	}


	public void setTarefa(Tarefa tarefa) {
		this.tarefa = tarefa;
	}


	public Tarefa getTarefaSelecionada() {
		return tarefaSelecionada;
	}


	public void setTarefaSelecionada(Tarefa tarefaSelecionada) {
		this.tarefaSelecionada = tarefaSelecionada;
	}


	public List<Tarefa> getTarefas() {
		return tarefas;
	}


	public void setTarefas(List<Tarefa> tarefas) {
		this.tarefas = tarefas;
	}


	public Veiculo getVeiculo() {
		return veiculo;
	}


	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}  
	
	
}