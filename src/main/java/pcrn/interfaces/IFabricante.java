package pcrn.interfaces;

import java.util.List;

import pcrn.model.Fabricante;

/** Esta � uma Interface que possui as assinaturas dos metodos da classe Fabricante,
*   
* @author silas
* @since 15-08-2016
*/

public interface IFabricante {

	/** Este metodo lista todos os Fabricantes cadastrados.
	* 	@return retorna a lista de todos os Fabricantes cadastrados.
	*/
	public List<Fabricante> listar();
	
	/** Este metodo pesquisa um Fabricante por seu id.
	*  	
	*  @param codigo, Este codigo � o id do Fabricante que voc� est� procurando.
	*  @return fabricante, retorna o Fabricante daquele id que voc� est� pesquisando.
	*   	
	*/
	public Fabricante porCodigo(Integer codigo);
	
	/** Este metodo cria ou altera um fabricante.
	*  	
	*  @param fabricante, Esta fabricante � o objeto Fabricante que voc� ir� criar ou modificar.
	*   	
	*/
	public void  salvar(Fabricante fabricante);
	
	/** Este metodo Remove um Fabricante.
	*  	
	*  @param fabricante, Esta fabricante � o objeto Fabricante que voc� ir� remover.
	*   	
	*/
	public void remover(Fabricante fabricante);

	/** Este metodo pesquisa um fabricante pelo seu nome.
	*  	
	*  @param fabricante, Este fabricante � o nome do fabricante que voc� est� procurando.
	*  @return retorna o fabricante daquele nome que voc� est� pesquisando.
	*   	
	*/
	public Fabricante pegaCodigo(String fabricante);
}