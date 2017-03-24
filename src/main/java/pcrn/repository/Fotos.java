package pcrn.repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import pcrn.interfaces.IFoto;
import pcrn.model.Foto;

public class Fotos implements IFoto, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Foto> porCodigoVeiculo(Integer codigo_veiculo) {
		List<Foto> listaFotos = new ArrayList<Foto>();
		Query query = manager.createQuery("from Foto where codigo_veiculo = :codigo_veiculo");
		query.setParameter("codigo_veiculo", codigo_veiculo);
		listaFotos = query.getResultList();
		return listaFotos;
	}

	@Override
	public void salvar(Foto foto) {
		manager.merge(foto);
	}

	@Override
	public void remover(Foto foto) {
		Foto fotoTemporaria = manager.find(Foto.class, foto.getCodigo());
		manager.remove(fotoTemporaria);
	}

}
