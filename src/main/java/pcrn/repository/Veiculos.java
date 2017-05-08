package pcrn.repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import pcrn.interfaces.IVeiculo;
import pcrn.model.Veiculo;

/** Esta � uma Classe concreta que implementa a Interface Veiculos,
*   
* @author silas
* @since 16-08-2016
*/
public class Veiculos implements IVeiculo, Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;

	/** Este metodo lista os veiculos cadastrados.
	* 	@return retorna a lista dos veiculos cadastrados ordenados pelo numero do dossie.
	* 	Este metodo sobrescreve o da interface Veiculos.
	*/
	@SuppressWarnings("unchecked")
	@Override
	public List<Veiculo> listar(){
		
		List<Veiculo> listaVeiculos = new ArrayList<Veiculo>();
		Query query = manager.createQuery("from Veiculo v "
						+ "inner join fetch v.cor inner join fetch v.pericia "
						+ "inner join fetch v.fabricante inner join fetch v.modelo "
						+ "inner join fetch v.seguro inner join fetch v.situacao "
						+ "inner join fetch v.tipo inner join fetch v.local "
						+ "order by dossie desc", Veiculo.class);
		listaVeiculos = query.getResultList();
		
		return listaVeiculos;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Veiculo> listarSemFoto(){
		
		List<Veiculo> listaVeiculos = new ArrayList<Veiculo>();
		List<Veiculo> listaVeiculosSemFoto = new ArrayList<Veiculo>();
		Query query = manager.createQuery("from Veiculo v "
						+ "inner join fetch v.cor inner join fetch v.pericia "
						+ "inner join fetch v.fabricante inner join fetch v.modelo "
						+ "inner join fetch v.seguro inner join fetch v.situacao "
						+ "inner join fetch v.tipo inner join fetch v.local "
						+ "order by dossie desc", Veiculo.class);
		listaVeiculos = query.getResultList();
		
		for (int i = 0; i < listaVeiculos.size(); i++) {
			if (listaVeiculos.get(i).getFotos().size() < 1) {
				listaVeiculosSemFoto.add(listaVeiculos.get(i));
			}
		}
		
		
		return listaVeiculosSemFoto;
	}
	
	/** Este metodo pesquisa um veiculo por seu id.
	*  	
	*  @param codigo, Este codigo � o id do veiculo que voc� est� procurando.
	*  @return retorna o Veiculo daquele id que voc� est� pesquisando.
	*  Este metodo sobrescreve o da interface Veiculos.	
	*/
	@Override
	public Veiculo porCodigo(Integer codigo) {
		return manager.find(Veiculo.class, codigo);
	}

	/** Este metodo cria um veiculo.
	*  	
	*  @param veiculo, Este veiculo � o objeto Veiculo que voc� ir� criar.
	*  Este metodo sobrescreve o da interface Veiculos.
	*/
	@Override
	public void salvar(Veiculo veiculo) {
		manager.merge(veiculo);
	}

	/** Este metodo Remove um veiculo.
	*  	
	*  @param veiculo, Esta veiculo � o objeto Veiculo que voc� ir� remover.
	*  Este metodo sobrescreve o da interface Veiculos.
	*/
	@Override
	public void remover(Veiculo veiculo) {
		Veiculo veiculoTemporario = manager.find(Veiculo.class, veiculo.getCodigo());
		manager.remove(veiculoTemporario);

	}

	/** Este metodo pesquisa um veiculo por sua placa.
	*  	
	*  @param codigo, Este codigo � a placa do veiculo que voc� est� procurando.
	*  @return retorna uma lista contendo todos os carros com aquela placa.
	*  Este metodo sobrescreve o da interface Veiculos.   
	*/
	@SuppressWarnings("unchecked")
	@Override
	public List<Veiculo> listarPorPlaca(String placa) {
		
		List<Veiculo> listaVeiculos = new ArrayList<Veiculo>();
		Query query = manager.createQuery("from Veiculo where placa = :placa");
		query.setParameter("placa", placa);
		listaVeiculos = query.getResultList();
		
		return listaVeiculos;
	}
	
	/** Este metodo pesquisa se o local da chave j� est� ocupado.
	*  	Este metodo ser� usado apenas quando for editar um veiculo.	
	*  
	*  @param chave, Esta chave � o local onde a chave vai ficar.
	*  @return retorna true caso o local da chave esteja j� ocupado caso contr�rio retorna false.
	*   	
	*/
	@Override
	public List<Veiculo> chaveExistenteEditar(Veiculo veiculo) {
		
		List<Veiculo> listaVeiculos = new ArrayList<Veiculo>();
		Query query = manager.createQuery("from Veiculo where chave = :chave and not placa = :placa");
		query.setParameter("chave", veiculo.getChave());
		query.setParameter("placa", veiculo.getPlaca());
		listaVeiculos = query.getResultList();
		
		return listaVeiculos;
	}

	/** Este metodo pesquisa se o local da chave j� est� ocupado.
	*  Este metodo ser� usado apenas quando for cadastrar um veiculo novo.
	*  	
	*  @param chave, Esta chave � o local onde a chave vai ficar.
	*  @return retorna true caso o local da chave esteja j� ocupado caso contr�rio retorna false.
	*  
	*   	
	*/
	@Override
	public List<Veiculo> chaveExistenteCadastrar(Veiculo veiculo) {
		
		List<Veiculo> listaVeiculos = new ArrayList<Veiculo>();
		Query query = manager.createQuery("from Veiculo where chave = :chave");
		query.setParameter("chave", veiculo.getChave());
		listaVeiculos = query.getResultList();
		
		return listaVeiculos;
	}
	
	/** Este metodo altera um veiculo.
	*  	
	*  @param veiculo, Esta veiculo � o objeto veiculo que voc� ir� modificar.
	*   	
	*/
	@Override
	public void editar(Veiculo veiculo) {
		manager.merge(veiculo);
	}
	
	/** Este metodo pesquisa uma cor pelo seu nome.
	*  	
	*  @param cor, Este cor � o nome do corque voc� est� procurando.
	*  @return retorna o cor daquele nome que voc� est� pesquisando.
	*   	
	*/
	@Override
	public Veiculo pegaSituacaoVeiculo(String situacaoBusca) {
		
		Query query = manager.createQuery("from Veiculo where situacao = :situacao");
		query.setParameter("situacao", situacaoBusca);
		Veiculo veiculo = (Veiculo) query.getSingleResult();
		
		return veiculo;
	}

	@Override
	public List<Veiculo> placaxistenteCadastrar(Veiculo veiculo) {
		
		List<Veiculo> listaVeiculos = new ArrayList<Veiculo>();
		Query query = manager.createQuery("from Veiculo where placa = :placa");
		query.setParameter("placa", veiculo.getPlaca());
		listaVeiculos = query.getResultList();
		
		return listaVeiculos;
	}

	@Override
	public List<Veiculo> listarVeiculosComSituacao(int codigoSituacao) {
		List<Veiculo> listaVeiculos = new ArrayList<Veiculo>();
		Query query = manager.createQuery("from Veiculo where codigo_situacao = :codigo_situacao");
		query.setParameter("codigo_situacao", codigoSituacao);
		listaVeiculos = query.getResultList();
		
		return listaVeiculos;
	}
	
	
	
	
	
	
}