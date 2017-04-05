package pcrn.services;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import pcrn.model.Foto;
import pcrn.repository.Fotos;
import pcrn.util.jpa.Transactional;

public class FotoService implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Inject
	private Fotos fotos;
	
	@Transactional
	public List<Foto> porCodigoVeiculo(int codigoVeiculo){
		return fotos.porCodigoVeiculo(codigoVeiculo);
	}
	
	@Transactional
	public void salvar(Foto foto){
		fotos.salvar(foto);
	}
	
	@Transactional
	public void remover(Foto foto){
		fotos.remover(foto);
	}	
}