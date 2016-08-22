package view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import model.Pericia;
import repository.Pericias;
import util.Repositorios;

/** Esta é uma Classe concreta que une as implementacoes das interfaces e das paginas xhtml referentes a entidade Pericia.
*   
* @author silas
* @since 18-08-2016
*/

@ManagedBean(name="cadastroPericiaBean")
@RequestScoped
public class CadastroPericiaBean implements Serializable{

	private Repositorios repositorios = new Repositorios();
	private Pericia pericia = new Pericia();	
	private List<Pericia> listaPericias = new ArrayList<Pericia>();

	/** Este metodo cadastra uma pericia.
	*/
	public void cadastrar(){
		//Esta linha estou instanciando a interface com sua implementação.
		Pericias pericias = this.repositorios.getPericias();
		//Esta linha salva a entidade pericia.
		pericias.salvar(pericia);
	}

	/** Este metodo Remove uma pericia.
	*  @param pericia, Esta pericia é o objeto Pericia que você irá remover.
	*/
	public void excluir(Pericia pericia){
		//Esta linha estou instanciando a interface com sua implementação.
		Pericias pericias = this.repositorios.getPericias();
		//Esta linha remove a pericia.
		pericias.remover(pericia);
		//Atualizar a lista de pericias.
		this.listaPericia();
	}
	
	/** Este metodo lista todas as pericias cadastradas.
	* 	@return retorna a lista de todas as pericias cadastradas no sistema.
	*/
	public List<Pericia> listaPericia(){
		//Esta linha estou instanciando a interface com sua implementação.
		Pericias pericias = this.repositorios.getPericias();
		//A lista de pericias recebe as pericias.
		listaPericias = pericias.listar();
		//Retorna a lista de pericias
		return listaPericias;
	}

	public Pericia getPericia() {
		return pericia;
	}

	public void setPericia(Pericia pericia) throws CloneNotSupportedException {
		this.pericia = pericia;
		if (this.pericia == null) {
			this.pericia = new Pericia();
		}else{
			this.pericia = (Pericia) pericia.clone();
		}
	}

	public List<Pericia> getListaPericias() {
		return listaPericias;
	}

	public void setListaPericias(List<Pericia> listaPericias) {
		this.listaPericias = listaPericias;
	}	
}