package view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import model.Cor;
import repository.Cores;
import util.Repositorios;

/** Esta é uma Classe concreta que une as implementacoes das interfaces e das paginas xhtml referentes a entidade Cor.
*   
* @author silas
* @since 15-08-2016
*/

@ManagedBean(name="cadastroCorBean")
@RequestScoped
public class CadastroCorBean implements Serializable{

	private Repositorios repositorios = new Repositorios();
	private Cor cor = new Cor();	
	private List<Cor> listaCores = new ArrayList<Cor>();

	/** Este metodo cadastra uma cor.
	* 	@return retorna a pagina inicial do sistema.
	*/
	public String cadastrar(){
		//Esta linha estou instanciando a interface com sua implementação.
		Cores cores = this.repositorios.getCores();
		//Esta linha salva a entidade cor.
		cores.salvar(cor);
		//Retorno da pagina.
		return "index?faces-redirect=true";
	}
	
	/** Este metodo lista todas as cores cadastradas.
	* 	@return retorna a lista de todas as cores cadastradas no sistema.
	*/
	public List<Cor> listarCores(){
		//Esta linha estou instanciando a interface com sua implementação
		Cores cores = this.repositorios.getCores();
		//Esta linha lista as cores e joga em uma lista de cores.
		listaCores = cores.listar();
		//Esta linha retorna a lista de cores
		return listaCores;
	}

	/** Este metodo Remove uma cor.
	*  @param cor, Esta cor é o objeto cor que você irá remover.
	*/
	public void excluir(Cor cor){
		//Esta linha estou instanciando a interface com sua implementação
		Cores cores = this.repositorios.getCores();
		//Esta linha remove a cor.
		cores.remover(cor);
		//Esta linha chama a funcao de listar as cores para que a lista de cores seja atualizada.
		this.listarCores();
	}

	public Cor getCor() {
		return cor;
	}
	
	public void setCor(Cor cor) throws CloneNotSupportedException {
		this.cor = cor;
		if (this.cor == null) {
			this.cor = new Cor();
		}else {
			this.cor = (Cor) cor.clone();
		}
	}

	public List<Cor> getListaCores() {
		return listaCores;
	}

	public void setListaCores(List<Cor> listaCores) {
		this.listaCores = listaCores;
	}
}