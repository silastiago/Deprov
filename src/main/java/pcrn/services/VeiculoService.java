package pcrn.services;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import pcrn.model.Veiculo;
import pcrn.repository.Veiculos;
import pcrn.util.jpa.Transactional;

public class VeiculoService implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private Veiculos veiculos;
	
	@Transactional
	public List<Veiculo> listar(){
		return veiculos.listar();
	}
	
	@Transactional
	public List<Veiculo> listarVeiculosSemFoto(){
		return veiculos.listarSemFoto();
	}
	
	
	@Transactional
	public Veiculo porCodigo(int codigo){
		return veiculos.porCodigo(codigo);
	}
	
	@Transactional
	public void salvar(Veiculo veiculo){
		veiculos.salvar(veiculo);
	}
	
	@Transactional
	public void remover(Veiculo veiculo){
		veiculos.remover(veiculo);
	}
	
	@Transactional
	public List<Veiculo> listarPorPlaca(String placa){
		return veiculos.listarPorPlaca(placa);
	}
	
	@Transactional
	public List<Veiculo> chaveExistenteEditar(Veiculo veiculo){
		return veiculos.chaveExistenteEditar(veiculo);
	}
	
	@Transactional
	public List<Veiculo> chaveExistenteCadastrar(Veiculo veiculo){
		return veiculos.chaveExistenteCadastrar(veiculo);
	}
	
	@Transactional
	public void editar(Veiculo veiculo){
		veiculos.editar(veiculo);
	}

	@Transactional
	public Veiculo pegaSituacaoVeiculo(String situacaoBusca){
		return veiculos.pegaSituacaoVeiculo(situacaoBusca);
	}
	
	@Transactional
	public List<Veiculo> placaxistenteCadastrar(Veiculo veiculo){
		return veiculos.placaxistenteCadastrar(veiculo);
	}
	
}
