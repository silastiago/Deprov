package view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;


import model.Fabricante;
import model.Modelo;
import repository.Fabricantes;
import repository.Modelos;
import util.Repositorios;

/** Esta é uma Classe concreta que une as implementacoes das interfaces e das paginas xhtml referentes a entidade Modelo.
*   
* @author silas
* @since 18-08-2016
*/

@ManagedBean(name="cadastroModeloBean")
@RequestScoped
public class CadastroModeloBean implements Serializable{

	private Repositorios repositorios = new Repositorios();
	private Modelo modelo  = new Modelo();
	private List<Modelo> modelos = new ArrayList<Modelo>();
	private List<Fabricante> fabricantes = new ArrayList<Fabricante>();
	
	//Primeiro metodo a ser executado quando entra nas views referentes a modelo de veiculos.
	@PostConstruct
	public void init(){
		Modelos modelos = this.repositorios.getModelos();
		Fabricantes fabricantes = this.repositorios.getFabricantes();
		this.modelos = modelos.listar();
		this.fabricantes = fabricantes.listar();
	}

	/** Este metodo cadastra um Modelo.
	*/
	public void cadastrar(){
		//Esta linha estou instanciando a interface com sua implementação.
		Modelos modelos = this.repositorios.getModelos();
		//Esta linha salva a entidade modelo.
		modelos.salvar(modelo);
	}

	/** Este metodo Remove um modelo.
	*  @param modelo, Este modelo é o objeto Modelo que você irá remover.
	*/
	public void excluir(Modelo modelo){
		//Esta linha estou instanciando a interface com sua implementação.
		Modelos modelos = this.repositorios.getModelos();
		//Esta linha remove o modelo.
		modelos.remover(modelo);
		//Chamando o metodo init para atualizar a lista de modelos.
		this.init();
	}
	
	public Modelo getModelo() {
		return modelo;
	}

	public void setModelo(Modelo modelo) throws CloneNotSupportedException {
		this.modelo = modelo;
		if (this.modelo == null) {
			this.modelo = new Modelo();
		}else{
			this.modelo = (Modelo) modelo.clone();
		}
	}

	public List<Modelo> getModelos() {
		return modelos;
	}

	public void setModelos(List<Modelo> modelos) {
		this.modelos = modelos;
	}


	public List<Fabricante> getFabricantes() {
		return fabricantes;
	}

	public void setFabricantes(List<Fabricante> fabricantes) {
		this.fabricantes = fabricantes;
	}
}