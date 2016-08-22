package view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import model.Seguro;
import repository.Seguros;
import util.Repositorios;

/** Esta é uma Classe concreta que une as implementacoes das interfaces e das paginas xhtml referentes a entidade Seguro.
*   
* @author silas
* @since 18-08-2016
*/

@ManagedBean(name="cadastroSeguroBean")
@RequestScoped
public class CadastroSeguroBean implements Serializable{

	private Repositorios repositorios = new Repositorios();
	private Seguro seguro = new Seguro();
	private List<Seguro> listaSeguros = new ArrayList<Seguro>();		

	/** Este metodo cadastra um Seguro.
	*/
	public void cadastrar(){
		//Esta linha estou instanciando a interface com sua implementacao.
		Seguros seguros = this.repositorios.getSeguros();
		//Esta linha salva a entidade seguro.
		seguros.salvar(seguro);
	}

	/** Este metodo Remove um Seguro.
	*  @param seguro, Este seguro é o objeto Seguro que você ira remover.
	*/
	public void excluir(Seguro seguro){
		//Esta linha estou instanciando a interface com sua implementacao.
		Seguros seguros = this.repositorios.getSeguros();
		//Esta linha remove o seguro.
		seguros.remover(seguro);
		//Atualizar a lista de seguros
		this.listaSeguro();
	}

	/** Este metodo lista todos os seguros cadastrados.
	* 	@return retorna a lista de todos os seguro cadastradas no sistema.
	*/
	public List<Seguro> listaSeguro(){
		//Esta linha estou instanciando a interface com sua implementacao.
		Seguros seguros = this.repositorios.getSeguros();
		//Esta linha lista os seguros e joga em uma lista de seguros.
		listaSeguros = seguros.listar();
		//retorna a lista de seguros
		return listaSeguros;
	}
	
	public Seguro getSeguro() {
		return seguro;
	}

	public void setSeguro(Seguro seguro) throws CloneNotSupportedException {
		this.seguro = seguro;
		if (this.seguro == null) {			
			this.seguro = new Seguro();
		}else{
			this.seguro = (Seguro) seguro.clone();
		}
	}

	public List<Seguro> getListaSeguros() {
		return listaSeguros;
	}

	public void setListaSeguros(List<Seguro> listaSeguros) {
		this.listaSeguros = listaSeguros;
	}
}