package pcrn.repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import pcrn.interfaces.IGrupo;
import pcrn.model.Grupo;

public class Grupos implements IGrupo, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Grupo> listar() {
		List<Grupo> listaGrupos = new ArrayList<Grupo>();
		Query query = manager.createQuery("Select c from Grupo c");
		listaGrupos = query.getResultList();
		return listaGrupos;
	}

	@Override
	public Grupo porCodigo(Integer codigo) {
		return manager.find(Grupo.class, codigo);
	}

	@Override
	public void salvar(Grupo grupo) {
		manager.merge(grupo);
	}

	@Override
	public void remover(Grupo grupo) {
		Grupo grupoTemporario = manager.find(Grupo.class, grupo.getCodigo());
		manager.remove(grupoTemporario);
	}
}
