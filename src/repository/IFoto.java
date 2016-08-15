package repository;

import java.util.List;

import model.Foto;

/** Esta � uma Interface que possui os metodos da classe Foto,
*   
* @author silas
* @since 15-08-2016
*/

public interface IFoto {
	
	/** Este metodo lista todas as fotos de um determinado veiculo.
	*   
	*   @param codigo, Este codigo � o id do veiculo que voc� est� procurando.
	*   
	* 	@return List<Foto>, retorna a lista de todas as fotos daquele veiculo.
	*   	
	*/
	public List<Foto> porCodigoVeiculo(Integer codigo);
	
	/** Este metodo salva uma Foto.
	*  	
	*  @param foto, Esta cor � o objeto cor que voc� ir� criar.
	*   	
	*/
	public void salvar(Foto foto);
	
	/** Este metodo Remove uma foto.
	*  	
	*  @param foto, Esta foto � o objeto Foto que voc� ir� remover.
	*   	
	*/
	public void remover(Foto foto);
}
