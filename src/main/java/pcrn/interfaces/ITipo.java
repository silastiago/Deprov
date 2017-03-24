package pcrn.interfaces;

import java.util.List;
import pcrn.model.Tipo;

/** Esta � uma Interface que possui assinaturas dos metodos da classe Tipo,
*   
* @author silas
* @since 15-08-2016
*/

public interface ITipo {

	/** Este metodo lista todos os tipos de veiculos cadastrados.
	*   
	* @return retorna a lista de todos os tipos de veiculos cadastrados
	*   	
	*/
	public List<Tipo> listar();
	
	/** Este metodo pesquisa um tipo de veiculo por seu id.
	*  	
	*  @param codigo, Este codigo � o id do Tipo do Veiculo que voc� est� procurando.
	*  @return retorna o Tipo do veiculo daquele id que voc� est� pesquisando.
	*   	
	*/
	public Tipo porCodigo(Integer codigo);
	
	/** Este metodo cria ou altera um Tipo de Veiculo.
	*  	
	*  @param tipo, Este tipo � o objeto Tipo que voc� ir� criar ou modificar.
	*   	
	*/
	public void salvar(Tipo tipo);
	
	/** Este metodo Remove um tipo.
	*  	
	*  @param tipo, Esta tipo � o objeto Tipo que voc� ir� remover.
	*   	
	*/
	public void remover(Tipo tipo);
}