package pcrn.repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import pcrn.interfaces.IFabricante;
import pcrn.model.Fabricante;

public class Fabricantes implements IFabricante, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Fabricante> listar() {
		List<Fabricante> listaFabricantes = new ArrayList<Fabricante>();
		Query query = manager.createQuery("Select c from Fabricante c order by fabricante asc");
		listaFabricantes = query.getResultList();
		return listaFabricantes;
	}

	@Override
	public Fabricante porCodigo(Integer codigo) {
		return manager.find(Fabricante.class, codigo);
	}

	@Override
	public void salvar(Fabricante fabricante) {
		manager.merge(fabricante);
	}

	@Override
	public void remover(Fabricante fabricante) {
		Fabricante fabricanteTemporario = manager.find(Fabricante.class, fabricante.getCodigo());
		manager.remove(fabricanteTemporario);
		
	}

	@Override
	public Fabricante pegaCodigo(String fabricanteBusca) {
		Query query = manager.createQuery("from Fabricante where fabricante = :fabricante");
		query.setParameter("fabricante", fabricanteBusca);
		Fabricante fabricante = (Fabricante) query.getSingleResult();
		return fabricante;
	}

}
