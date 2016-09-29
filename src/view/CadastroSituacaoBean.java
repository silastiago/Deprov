package view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import model.Cor;
import model.Situacao;
import repository.Cores;
import repository.ISituacao;
import util.Repositorios;

/** Esta é uma Classe concreta que une as implementacoes das interfaces e das paginas xhtml referentes a entidade Cor.
*   
* @author silas
* @since 15-08-2016
*/

@ManagedBean(name="cadastroSituacaoBean")
@RequestScoped
public class CadastroSituacaoBean implements Serializable{

	private Repositorios repositorios = new Repositorios();
	private Situacao situacao = new Situacao();	
	private List<Situacao> listaSituacoes = new ArrayList<Situacao>();

	/** Este metodo cadastra uma cor.
	* 	@return retorna a pagina inicial do sistema.
	*/
	public void cadastrar(){
		//Esta linha estou instanciando a interface com sua implementação.
		ISituacao Isituacao = this.repositorios.getSituacao();
		//Esta linha salva a entidade cor.
		Isituacao.salvar(situacao);
		//Retorno da pagina.
		//return "index?faces-redirect=true";
	}
	
	/** Este metodo lista todas as cores cadastradas.
	* 	@return retorna a lista de todas as cores cadastradas no sistema.
	*/
	public List<Situacao> listarSituacoes(){
		//Esta linha estou instanciando a interface com sua implementação
		ISituacao Isituacao = this.repositorios.getSituacao();
		//Esta linha lista as cores e joga em uma lista de cores.
		listaSituacoes = Isituacao.listar();
		//Esta linha retorna a lista de cores
		return listaSituacoes;
	}

	/** Este metodo Remove uma cor.
	*  @param cor, Esta cor é o objeto cor que você irá remover.
	*/
	public void excluir(Situacao situacao){
		//Esta linha estou instanciando a interface com sua implementação
		ISituacao Isituacao = this.repositorios.getSituacao();
		//Esta linha remove a cor.
		Isituacao.remover(situacao);
		//Esta linha chama a funcao de listar as cores para que a lista de cores seja atualizada.
		this.listarSituacoes();
	}	
	
	
	public Situacao getSituacao() {
		return situacao;
	}

	public void setSituacao(Situacao situacao) throws CloneNotSupportedException {
		this.situacao = situacao;
		if (this.situacao == null) {
			this.situacao = new Situacao();
		}else{
			this.situacao = (Situacao) situacao.clone();
		}
	}

	public List<Situacao> getListaSituacoes() {
		return listaSituacoes;
	}

	public void setListaSituacoes(List<Situacao> listaSituacoes) {
		this.listaSituacoes = listaSituacoes;
	}
}