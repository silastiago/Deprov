package repository;

import java.util.List;

import model.Veiculo;

/** Esta � uma Interface que possui as assinaturas dos metodos da classe veiculo,
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
	*  @param codigo, Este codigo � o id do Veiculo que voc� est� procurando.
	*  @return Veiculo, retorna o veiculo daquele id que voc� est� pesquisando.
	*   	
	*/
	public Veiculo porCodigo(Integer codigo);
	
	/** Este metodo cria ou altera um veiculo.
	*  	
	*  @param veiculo, Esta veiculo � o objeto veiculo que voc� ir� criar ou modificar.
	*   	
	*/
	public void salvar(Veiculo veiculo);
	
	/** Este metodo Remove um veiculo.
	*  	
	*  @param veiculo, Esta veiculo � o objeto Veiculo que voc� ir� remover.
	*   	
	*/
	public void remover(Veiculo veiculo);
	
	/** Este metodo pesquisa um veiculo por sua placa.
	*  	
	*  @param codigo, Este codigo � a placa do veiculo que voc� est� procurando.
	*  @return retorna uma lista contendo todos os carros com aquela placa.
	*   	
	*/
	public List<Veiculo> listarPorPlaca(String codigo);
}