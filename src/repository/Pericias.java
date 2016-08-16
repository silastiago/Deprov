package repository;

import java.util.List;
import model.Pericia;

/** Esta � uma Interface que possui as assinaturas dos metodos da classe Pericia,
*   
* @author silas
* @since 15-08-2016
*/

public interface Pericias {

	/** Este metodo lista de todos os Exames veiculares cadastrados.
	*   
	* @return retorna a lista de todos as Pericias cadastradas.
	*   	
	*/
	public List<Pericia> listar();
	
	/** Este metodo pesquisa uma pericia por seu id.
	*  	
	*  @param codigo, Este codigo � o id do Exame Veicular que voc� est� procurando.
	*  @return retorna a Pericia daquele id que voc� est� pesquisando.
	*   	
	*/
	public Pericia porCodigo(Integer codigo);
	
	/** Este metodo cria ou altera um Exame Veicular.
	*  	
	*  @param pericia, Esta pericia � o objeto Pericia que voc� ir� criar ou modificar.
	*   	
	*/
	public void salvar(Pericia pericia);
	
	/** Este metodo Remove uma Pericia.
	*  	
	*  @param pericia, Esta pericia � o objeto Pericia que voc� ir� remover.
	*   	
	*/
	public void remover(Pericia pericia);
}