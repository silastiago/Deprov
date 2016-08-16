package repository;

import java.util.List;

import model.Veiculo;

/** Esta é uma Interface que possui as assinaturas dos metodos da classe veiculo,
*   
* @author silas
* @since 15-08-2016
*/

public interface Veiculos {

	/** Este metodo lista todos os veiculos cadastrados.
	*   
	* @return retorna a lista de todos os veiculos cadastrados.
	*   	
	*/
	public List<Veiculo> listar();
	
	/** Este metodo pesquisa um veiculo por seu id.
	*  	
	*  @param codigo, Este codigo é o id do Veiculo que você está procurando.
	*  @return Veiculo, retorna o veiculo daquele id que você está pesquisando.
	*   	
	*/
	public Veiculo porCodigo(Integer codigo);
	
	/** Este metodo cria ou altera um veiculo.
	*  	
	*  @param veiculo, Esta veiculo é o objeto veiculo que você irá criar ou modificar.
	*   	
	*/
	public void salvar(Veiculo veiculo);
	
	/** Este metodo Remove um veiculo.
	*  	
	*  @param veiculo, Esta veiculo é o objeto Veiculo que você irá remover.
	*   	
	*/
	public void remover(Veiculo veiculo);
	
	/** Este metodo pesquisa um veiculo por sua placa.
	*  	
	*  @param codigo, Este codigo é a placa do veiculo que você está procurando.
	*  @return retorna uma lista contendo todos os carros com aquela placa.
	*   	
	*/
	public List<Veiculo> listarPorPlaca(String codigo);
}