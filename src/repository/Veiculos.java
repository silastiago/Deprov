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
	
	
	/** Este metodo altera um veiculo.
	*  	
	*  @param veiculo, Esta veiculo � o objeto veiculo que voc� ir� modificar.
	*   	
	*/
	public void editar(Veiculo veiculo);
	
	
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
	
	/** Este metodo pesquisa se o local da chave j� est� ocupado, isso � feito no processo de edi��o do veiculo.
	*  	
	*  @param veiculo, Este veiculo � o veiculo que voc� est� editando.
	*  @return retorna uma lista de todos os veiculos que possuem aquela chave menos o veiculo que voc� est� editando,
	*  				   pois isso garante que voc� sempre possa editar o veiculo.
	*   	
	*/
	public List<Veiculo> chaveExistenteEditar(Veiculo veiculo);

	/** Este metodo pesquisa se o local da chave j� est� ocupado, isso � feito no processo o cadastro de um novo veiculo.
	*  	
	*  @param veiculo, Este veiculo � o veiculo que voc� est� cadastrando.
	*  @return retorna uma lista de todos os veiculos que possuem aquela chave.
	*   	
	*/
	public List<Veiculo> chaveExistenteCadastrar(Veiculo veiculo);

	Veiculo pegaSituacaoVeiculo(String situacao);
}