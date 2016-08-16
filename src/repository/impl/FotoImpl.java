package repository.impl;

import java.util.List;


import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import model.Foto;
import repository.IFoto;

/** Esta � uma Classe concreta que implementa a Interface IFoto,
*   
* @author silas
* @since 15-08-2016
*/
public class FotoImpl implements IFoto{
	private Session sessao;

	/**
     * Constructor.
     * @param sessao ser� a sessao que o hibernate cria para conexoes com o banco.
     */
	public FotoImpl(Session sessao){
		this.sessao = sessao;
	}

	/** Este metodo lista as fotos de um determinado veiculo.
	*   
	*   @param codigo_veiculo, Este codigo � o id do veiculo que voc� est� procurando.
	*   
	* 	@return retorna a lista de todas as fotos daquele veiculo.
	*   Este metodo sobrescreve o da interface IFoto.	
	*/
	@SuppressWarnings("unchecked")
	@Override
	public List<Foto> porCodigoVeiculo(Integer codigo_veiculo) {
		return sessao.createCriteria(Foto.class).add(Restrictions.eq("veiculo.codigo", codigo_veiculo)).list();
	}
	
	/** Este metodo cria uma Foto.
	*  	
	*  @param foto, Esta cor � o objeto cor que voc� ir� criar.
	*  Este metodo sobrescreve o da interface Fabricantes.
	*/
	@Override
	public void salvar(Foto foto) {
		this.sessao.merge(foto);
	}

	/** Este metodo Remove uma foto.
	*  	
	*  @param foto, Esta foto � o objeto Foto que voc� ir� remover.
	*  Este metodo sobrescreve o da interface IFoto.
	*/
	@Override
	public void remover(Foto foto) {
		this.sessao.delete(foto);
	}
}