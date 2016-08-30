package repository;

import java.util.List;

import model.Modelo;

/** Esta � uma Interface que possui as assinaturas dos metodos da classe Modelo,
*   
* @author silas
* @since 15-08-2016
*/

public interface Modelos {

	/** Este metodo lista todos os Modelos cadastrados.
	*   
	* @return retorna a lista de modelos cadastrados.
	*   	
	*/
	public List<Modelo> listar();
	
	/** Este metodo pesquisa um modelo por seu id.
	*  	
	*  @param codigo, Este codigo � o id do modelo que voc� est� procurando.
	*  @return retorna o modelo daquele id que voc� est� pesquisando.
	*   	
	*/
	public Modelo porCodigo(Integer codigo);
	
	/** Este metodo cria ou altera um modelo.
	*  	
	*  @param modelo, Este modelo � o objeto Modelo que voc� ir� criar ou modificar.
	*   	
	*/
	public void salvar(Modelo modelo);
	
	/** Este metodo Remove um modelo.
	*  	
	*  @param modelo, Este modelo � o objeto Modelo que voc� ir� remover.
	*   	
	*/
	public void remover(Modelo modelo);

	public Modelo pegaCodigo(String modelo);
}