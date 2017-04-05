package pcrn.services;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import pcrn.model.Ocorrencia;
import pcrn.repository.Ocorrencias;
import pcrn.util.jpa.Transactional;

public class OcorrenciaService implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private Ocorrencias ocorrencias;
	
	@Transactional
	public Ocorrencia porCodigo(int codigo){
		return ocorrencias.porCodigo(codigo);
	}
	
	@Transactional
	public void salvar(Ocorrencia ocorrencia){
		ocorrencias.salvar(ocorrencia);
	}
	
	@Transactional
	public void remover(Ocorrencia ocorrencia){
		ocorrencias.remover(ocorrencia);
	}
	
	@Transactional
	public List<Ocorrencia> porCodigoVeiculo(int codigoVeiculo){
		return ocorrencias.porCodigoVeiculo(codigoVeiculo);
	}
}
