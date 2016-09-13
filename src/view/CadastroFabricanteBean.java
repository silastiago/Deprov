package view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import model.Fabricante;
import repository.Fabricantes;
import util.Repositorios;

/** Esta é uma Classe concreta que une as implementacoes das interfaces e das paginas xhtml referentes a entidade Fabricante.
*   
* @author silas
* @since 18-08-2016
*/

@ManagedBean(name="cadastroFabricanteBean")
@RequestScoped
public class CadastroFabricanteBean implements Serializable{

	private Repositorios repositorios = new Repositorios();
	private Fabricante fabricante = new Fabricante();
	private List<Fabricante> listaFabricantes = new ArrayList<Fabricante>();
	
	
	/** Este metodo cadastra um fabricante.
	*/
	public void cadastrar(){
		//Esta linha estou instanciando a interface com sua implementação.
		Fabricantes fabricantes = this.repositorios.getFabricantes();
		//Esta linha salva a entidade fabricante.
		fabricantes.salvar(fabricante);
	}

	/** Este metodo Remove um fabricante.
	*  @param fabricante, Este fabricante é o objeto Fabricante que você irá remover.
	*/
	public void excluir(Fabricante fabricante){
		//Esta linha estou instanciando a interface com sua implementação.
		Fabricantes fabricantes = this.repositorios.getFabricantes();
		//Esta linha remove o fabricante.
		fabricantes.remover(fabricante);
		//Esta linha chama a funcao de listar os fabricantes para que a lista de fabricantes seja atualizada.
		this.listaFabricantes();
	}

	/** Este metodo lista todos os fabricantes cadastrados.
	* 	@return retorna a lista de todos os fabricantes cadastradas no sistema.
	*/
	public List<Fabricante> listaFabricantes(){
		//Esta linha estou instanciando a interface com sua implementação.
		Fabricantes fabricantes = this.repositorios.getFabricantes();
		//Esta linha lista os fabricantes e joga em uma lista de fabricantes.
		listaFabricantes = fabricantes.listar();
		//Esta linha retorna a lista de fabricantes
		return listaFabricantes;		
	}
	
	public Fabricante getFabricante() {
		return fabricante;
	}

	public void setFabricante(Fabricante fabricante) throws CloneNotSupportedException {
		this.fabricante = fabricante;
		if (this.fabricante == null) {
			this.fabricante = new Fabricante();
		}else {
			this.fabricante = (Fabricante) fabricante.clone();
		}
	}

	public List<Fabricante> getListaFabricantes() {
		return listaFabricantes;
	}

	public void setListaFabricantes(List<Fabricante> listaFabricantes) {
		this.listaFabricantes = listaFabricantes;
	}
}