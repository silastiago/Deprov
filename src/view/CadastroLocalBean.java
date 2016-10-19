package view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import model.Local;
import repository.ILocal;
import util.Repositorios;

/** Esta é uma Classe concreta que une as implementacoes das interfaces e das paginas xhtml referentes a entidade Cor.
*   
* @author silas
* @since 15-08-2016
*/

@ManagedBean(name="cadastroLocalBean")
@RequestScoped
public class CadastroLocalBean implements Serializable{

	private Repositorios repositorios = new Repositorios();
	private Local local = new Local();
	private List<Local> listaLocais = new ArrayList<Local>();
	
	/*private Cor cor = new Cor();	
	private List<Cor> listaCores = new ArrayList<Cor>();*/

	/** Este metodo cadastra uma cor.
	* 	@return retorna a pagina inicial do sistema.
	*/
	public void cadastrar(){
		//Esta linha estou instanciando a interface com sua implementação.
		ILocal Ilocal = this.repositorios.getLocal();
		//Esta linha salva a entidade cor.
		Ilocal.salvar(local);
		//Retorno da pagina.
		//return "index?faces-redirect=true";
	}
	
	/** Este metodo lista todas as cores cadastradas.
	* 	@return retorna a lista de todas as cores cadastradas no sistema.
	*/
	public List<Local> listarLocais(){
		//Esta linha estou instanciando a interface com sua implementação.
		ILocal Ilocal = this.repositorios.getLocal();
		//Esta linha lista as cores e joga em uma lista de cores.
		listaLocais = Ilocal.listar();
		//Esta linha retorna a lista de cores
		return listaLocais;
	}

	/** Este metodo Remove uma cor.
	*  @param cor, Esta cor é o objeto cor que você irá remover.
	*/
	public void excluir(Local local){
		//Esta linha estou instanciando a interface com sua implementação.
		ILocal Ilocal = this.repositorios.getLocal();
		//Esta linha remove a cor.
		Ilocal.remover(local);
		//Esta linha chama a funcao de listar as cores para que a lista de cores seja atualizada.
		this.listarLocais();
	}
	
	public Local getLocal() {
		return local;
	}

	public void setLocal(Local local) {
		this.local = local;
		if (this.local == null) {
			this.local = new Local();
		}else{
			try {
				this.local = (Local) local.clone();
			} catch (CloneNotSupportedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public List<Local> getListaLocais() {
		return listaLocais;
	}

	public void setListaLocais(List<Local> listaLocais) {
		this.listaLocais = listaLocais;
	}
}