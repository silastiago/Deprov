package repository;

import java.util.List;

import model.Fabricante;

/** Esta � uma Interface que possui os metodos da classe Fabricante,
*   
* @author silas
* @since 15-08-2016
*/

public interface Fabricantes {

	/** Este metodo lista de todos os Fabricantes cadastrados.
	*   
	* @return List<Fabricante>, retorna a lista de todos os Fabricantes cadastrados.
	*   	
	*/
	public List<Fabricante> listar();
	
	/** Este metodo pesquisa um Fabricante por seu id.
	*  	
	*  @param codigo, Este codigo � o id do Fabricante que voc� est� procurando.
	*  @return fabricante, retorna o Fabricante daquele id que voc� est� pesquisando.
	*   	
	*/
	public Fabricante porCodigo(Integer codigo);
	
	/** Este metodo salva ou altera um fabricante.
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
}
