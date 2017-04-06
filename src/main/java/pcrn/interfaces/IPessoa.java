package pcrn.interfaces;

import java.util.List;
import pcrn.model.Pessoa;

/** Esta � uma Interface que possui as assinaturas dos metodos da classe Pessoa,
*   
* @author silas
* @since 15-08-2016
*/
public interface IPessoa {
	
	/** Este metodo lista todas as pessoas cadastradas
	*   
	* @return retorna a lista das pessoas cadastradas.
	*   	
	*/
	public List<Pessoa> listar();
	
	/** Este metodo pesquisa uma pessoa por seu id.
	*  	
	*  @param codigo, Este codigo � o id da pessoa que voc� est� procurando.
	*  @return retorna a Pessoa daquele id que voc� est� pesquisando.
	*   	
	*/
	public Pessoa porCodigo(Integer codigo);
	
	/** Este metodo cria ou altera uma pessoa.
	*  	
	*  @param pessoa, Esta pessoa � o objeto Pessoa que voc� ir� criar ou modificar.
	*   	
	*/
	public void salvar(Pessoa pessoa);
	
	/** Este metodo Remove uma Pessoa.
	*  	
	*  @param pessoa, Esta pessoa � o objeto Pessoa que voc� ir� remover.
	*   	
	*/
	public void remover(Pessoa pessoa);

	public Pessoa porLogin(String login);
}