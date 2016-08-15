package repository;

import java.util.List;

import model.Foto;

/** Esta é uma Interface que possui os metodos da classe Foto,
*   
* @author silas
* @since 15-08-2016
*/

public interface IFoto {
	
	/** Este metodo lista todas as fotos de um determinado veiculo.
	*   
	*   @param codigo, Este codigo é o id do veiculo que você está procurando.
	*   
	* 	@return List<Foto>, retorna a lista de todas as fotos daquele veiculo.
	*   	
	*/
	public List<Foto> porCodigoVeiculo(Integer codigo);
	
	/** Este metodo salva uma Foto.
	*  	
	*  @param foto, Esta cor é o objeto cor que você irá criar.
	*   	
	*/
	public void salvar(Foto foto);
	
	/** Este metodo Remove uma foto.
	*  	
	*  @param foto, Esta foto é o objeto Foto que você irá remover.
	*   	
	*/
	public void remover(Foto foto);
}
