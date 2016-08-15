package repository.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import model.Foto;
import model.Ocorrencia;
import repository.IFoto;


public class FotoImpl implements IFoto{
	private Session sessao;

	public FotoImpl(Session sessao){
		this.sessao = sessao;
	}

	@Override
	public void salvar(Foto foto) {
		this.sessao.merge(foto);
	}

	@Override
	public void remover(Foto foto) {
		this.sessao.delete(foto);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Foto> porCodigoVeiculo(Integer codigo_veiculo) {
		return sessao.createCriteria(Foto.class).add(Restrictions.eq("veiculo.codigo", codigo_veiculo)).list();
	}
}