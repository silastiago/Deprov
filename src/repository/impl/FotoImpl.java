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

	@SuppressWarnings("unchecked")
	@Override
	public List<Foto> listar(){
		return sessao.createCriteria(Foto.class).list();
	}

	@Override
	public Foto porCodigo(Integer codigo) {
		return (Foto) sessao.get(Foto.class, codigo);
	}

	@Override
	public Foto salvar(Foto foto) {
		return (Foto) sessao.merge(foto);
	}

	@Override
	public void remover(Foto foto) {
		this.sessao.delete(foto);

	}

	@Override
	public void editar(Foto foto) {
		this.sessao.update(foto);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Foto> porCodigoVeiculo(Integer codigo_veiculo) {
		return sessao.createCriteria(Foto.class).add(Restrictions.eq("veiculo.codigo", codigo_veiculo)).list();
	}
}