package pcrn.repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import pcrn.interfaces.ICor;
import pcrn.model.Cor;

public class Cores implements ICor, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Cor> listar() {
		List<Cor> listaCores = new ArrayList<Cor>();
		Query query = manager.createQuery("Select c from Cor c");
		listaCores = query.getResultList();
		return listaCores;
	}

	@Override
	public Cor porCodigo(Integer codigo) {
		return manager.find(Cor.class, codigo);
	}

	@Override
	public void salvar(Cor cor) {
		manager.merge(cor);
	}

	@Override
	public void remover(Cor cor) {
		Cor corTemporaria = manager.find(Cor.class, cor.getCodigo());
		manager.remove(corTemporaria);
	}

	@Override
	public Cor pegaCodigo(String corBusca) {		
		Query query = manager.createQuery("from Cor where cor = :cor");
		query.setParameter("cor", corBusca);
		Cor cor = (Cor) query.getSingleResult();
		
		return cor;
	}
}
