package pcrn.interfaces;

import java.util.List;

import pcrn.model.Local;

/** Esta � uma Interface que possui as assinaturas dos metodos da classe Cor,
*   
* @author silas
* @since 15-08-2016
*/

public interface ILocal {

	/** Este metodo lista todos os Locais cadastrados.
	* 	@return retorna a lista de Locais cadastrados.
	*/
	public List<Local> listar();
	
	/** Este metodo pesquisa uma cor por seu id.
	*  	
	*  @param codigo, Este codigo � o id da cor que voc� est� procurando.
	*  @return Cor, retorna a Cor daquele id que voc� est� pesquisando.
	*   	
	*/
	public Local porCodigo(Integer codigo);
	
	/** Este metodo cria ou altera uma cor.
	*  	
	*  @param cor, Esta cor � o objeto cor que voc� ir� criar ou modificar.
	*   	
	*/
	public void salvar(Local local);
	
	/** Este metodo Remove uma cor.
	*  	
	*  @param cor, Esta cor � o objeto cor que voc� ir� remover.
	*   	
	*/
	public void remover(Local local);

	/** Este metodo pesquisa uma cor pelo seu nome.
	*  	
	*  @param cor, Este cor � o nome do corque voc� est� procurando.
	*  @return retorna o cor daquele nome que voc� est� pesquisando.
	*   	
	*/
	public Local pegaCodigo(String local);
}