package pcrn.interfaces;

import java.util.List;
import pcrn.model.Ocorrencia;

/** Esta � uma Interface que possui as assinaturas dos metodos da classe Ocorrencia,
*   
* @author silas
* @since 15-08-2016
*/

public interface IOcorrencia {

	/** Este metodo pesquisa uma ocorrencia por seu id.
	*  	
	*  @param codigo, Este codigo � o id da ocorrencia que voc� est� procurando.
	*  @return retorna a Ocorrencia daquele id que voc� est� pesquisando.
	*   	
	*/
	public Ocorrencia porCodigo(int codigo);
	
	/** Este metodo lista todas as ocorrencias de um determinado veiculo.
	*   
	*   @param codigo, Este codigo � o id do veiculo que voc� est� procurando.
	* 	@return retorna a lista de todas as ocorrencias daquele veiculo.
	*   	
	*/
	public List<Ocorrencia> porCodigoVeiculo(int codigo);
	
	/** Este metodo cria ou altera uma ocorrencia.
	*  	
	*  @param ocorrencia, Esta ocorrencia � o objeto Ocorrencia que voc� ir� criar ou modificar.
	*   	
	*/
	public void salvar(Ocorrencia ocorrencia);
	
	/** Este metodo Remove uma ocorrencia.
	*  	
	*  @param ocorrencia, Esta ocorrencia � o objeto Ocorrencia que voc� ir� remover.
	*   	
	*/
	public void remover(Ocorrencia ocorrencia);

	
}