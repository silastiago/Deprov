package view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.event.ValueChangeEvent;

import model.Fabricante;
import model.Modelo;
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
	private List<Modelo> listaModelos = new ArrayList<Modelo>();

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
		this.listaModelo();
	}
	
	/** Este metodo lista todas os modelos cadastrados.
	* 	@return retorna a lista de todos os modelos cadastrados no sistema.
	*/
	public List<Modelo> listaModelo(){
		//Esta linha estou instanciando a interface com sua implementação.
		Modelos modelos = this.repositorios.getModelos();
		//A lista de modelos recebe as modelos.
		listaModelos = modelos.listar();
		//Retorna a lista de modelos
		return listaModelos;
	}	
	
	public void listarModelos(ValueChangeEvent evento){
		Fabricante fabricante = (Fabricante) evento.getNewValue();
		//Esta linha estou instanciando a interface com sua implementação.
		Modelos modelos = this.repositorios.getModelos();
		//A lista de modelos recebe as modelos.
		listaModelos = modelos.pegaModelos(fabricante.getCodigo().toString());
		//Retorna a lista de modelos
		//return listaModelos;
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

	public List<Modelo> getListaModelos() {
		return listaModelos;
	}

	public void setListaModelos(List<Modelo> listaModelos) {
		this.listaModelos = listaModelos;
	}
}