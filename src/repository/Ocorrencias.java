package repository;

import java.util.List;
import model.Ocorrencia;

/** Esta é uma Interface que possui as assinaturas dos metodos da classe Ocorrencia,
*   
* @author silas
* @since 15-08-2016
*/

public interface Ocorrencias {

	/** Este metodo pesquisa uma ocorrencia por seu id.
	*  	
	*  @param codigo, Este codigo é o id da ocorrencia que você está procurando.
	*  @return retorna a Ocorrencia daquele id que você está pesquisando.
	*   	
	*/
	public Ocorrencia porCodigo(Integer codigo);
	
	/** Este metodo lista todas as ocorrencias de um determinado veiculo.
	*   
	*   @param codigo, Este codigo é o id do veiculo que você está procurando.
	* 	@return retorna a lista de todas as ocorrencias daquele veiculo.
	*   	
	*/
	public List<Ocorrencia> porCodigoVeiculo(Integer codigo);
	
	/** Este metodo cria ou altera uma ocorrencia.
	*  	
	*  @param ocorrencia, Esta ocorrencia é o objeto Ocorrencia que você irá criar ou modificar.
	*   	
	*/
	public void salvar(Ocorrencia ocorrencia);
	
	/** Este metodo Remove uma ocorrencia.
	*  	
	*  @param ocorrencia, Esta ocorrencia é o objeto Ocorrencia que você irá remover.
	*   	
	*/
	public void remover(Ocorrencia ocorrencia);
}