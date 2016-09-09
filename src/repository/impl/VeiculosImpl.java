package repository.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import model.Modelo;
import model.Veiculo;
import repository.Veiculos;

/** Esta é uma Classe concreta que implementa a Interface Veiculos,
*   
* @author silas
* @since 16-08-2016
*/
public class VeiculosImpl implements Veiculos{
	private Session sessao;

	/**
     * Constructor.
     * @param sessao será a sessao que o hibernate cria para conexoes com o banco.
     */
	public VeiculosImpl(Session sessao){
		this.sessao = sessao;
	}

	/** Este metodo lista os veiculos cadastrados.
	* 	@return retorna a lista dos veiculos cadastrados ordenados pelo numero do dossie.
	* 	Este metodo sobrescreve o da interface Veiculos.
	*/
	@SuppressWarnings("unchecked")
	@Override
	public List<Veiculo> listar(){
		return sessao.createCriteria(Veiculo.class).addOrder(Order.desc("dossie")).list();
	}

	/** Este metodo pesquisa um veiculo por seu id.
	*  	
	*  @param codigo, Este codigo é o id do veiculo que você está procurando.
	*  @return retorna o Veiculo daquele id que você está pesquisando.
	*  Este metodo sobrescreve o da interface Veiculos.	
	*/
	@Override
	public Veiculo porCodigo(Integer codigo) {
		return (Veiculo) sessao.get(Veiculo.class, codigo);
	}

	/** Este metodo cria ou altera um veiculo.
	*  	
	*  @param veiculo, Este veiculo é o objeto Veiculo que você irá criar ou modificar.
	*  Este metodo sobrescreve o da interface Veiculos.
	*/
	@Override
	public void salvar(Veiculo veiculo) {
		this.sessao.merge(veiculo);
	}

	/** Este metodo Remove um veiculo.
	*  	
	*  @param veiculo, Esta veiculo é o objeto Veiculo que você irá remover.
	*  Este metodo sobrescreve o da interface Veiculos.
	*/
	@Override
	public void remover(Veiculo veiculo) {
		this.sessao.delete(veiculo);

	}

	/** Este metodo pesquisa um veiculo por sua placa.
	*  	
	*  @param codigo, Este codigo é a placa do veiculo que você está procurando.
	*  @return retorna uma lista contendo todos os carros com aquela placa.
	*  Este metodo sobrescreve o da interface Veiculos.   
	*/
	@SuppressWarnings("unchecked")
	@Override
	public List<Veiculo> listarPorPlaca(String codigo) {
		//Criteria criteria = sessao.createCriteria(Veiculo.class).addQueryHint("FROM Veiculo E WHERE E.Placa = jfoerhgoh");
		return sessao.createCriteria(Veiculo.class).add(Restrictions.eq("veiculo.placa", codigo)).list();
	}
	
	/** Este metodo pesquisa se o local da chave já está ocupado.
	*  	Este metodo será usado apenas quando for editar um veiculo.	
	*  
	*  @param chave, Esta chave é o local onde a chave vai ficar.
	*  @return retorna true caso o local da chave esteja já ocupado caso contrário retorna false.
	*   	
	*/
	@Override
	public List<Veiculo> chaveExistenteEditar(Veiculo veiculo) {
		boolean existe = false;
		Criteria c = this.sessao.createCriteria(Veiculo.class);
		c.add(Restrictions.eq("chave", veiculo.getChave()));
		c.add(Restrictions.ne("codigo", veiculo.getCodigo()));
		List<Veiculo> results = c.list();	
		/*
		if (results.size() > 0) {
			existe = true;
		}else{
			existe = false;
		}
		*/
		return results;
	}

	/** Este metodo pesquisa se o local da chave já está ocupado.
	*  Este metodo será usado apenas quando for cadastrar um veiculo novo.
	*  	
	*  @param chave, Esta chave é o local onde a chave vai ficar.
	*  @return retorna true caso o local da chave esteja já ocupado caso contrário retorna false.
	*  
	*   	
	*/
	@Override
	public List<Veiculo> chaveExistenteCadastrar(Veiculo veiculo) {
		boolean existe = false;
		Criteria c = this.sessao.createCriteria(Veiculo.class);
		c.add(Restrictions.eq("chave", veiculo.getChave()));
		List<Veiculo> results = c.list();	
		/*
		if (results.size() > 0) {
			existe = true;
		}else{
			existe = false;
		}
		*/
		return results;
	}
	
	
	@Override
	public void editar(Veiculo veiculo) {
		this.sessao.merge(veiculo);
	}
}