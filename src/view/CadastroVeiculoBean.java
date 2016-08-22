package view;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.primefaces.model.StreamedContent;

import model.Cor;
import model.Fabricante;
import model.Modelo;
import model.Pericia;
import model.Seguro;
import model.Tipo;
import model.Veiculo;
import repository.Cores;
import repository.Fabricantes;
import repository.Modelos;
import repository.Pericias;
import repository.Seguros;
import repository.Tipos;
import repository.Veiculos;
import util.Repositorios;

@ManagedBean(name="cadastroVeiculoBean")
@ViewScoped
public class CadastroVeiculoBean implements Serializable{

	private Repositorios repositorios = new Repositorios();
	private Veiculo veiculo = new Veiculo();
	private List<Veiculo> veiculos = new ArrayList<Veiculo>();
	private List<Veiculo> veiculosFiltrados = new ArrayList<Veiculo>();
	private List<Cor> cores = new ArrayList<Cor>();
	private List<Tipo> tipos = new ArrayList<Tipo>();
	private List<Fabricante> fabricantes  = new ArrayList<Fabricante>();
	private List<Modelo> modelos = new ArrayList<Modelo>();
	private List<Seguro> seguros = new ArrayList<Seguro>();
	private List<Pericia> pericias = new ArrayList<Pericia>();
	private StreamedContent file;
	
	
	@PostConstruct
	public void init(){
		Veiculos veiculos = this.repositorios.getveiculos();
		Cores cores = this.repositorios.getCores();
		Tipos tipos = this.repositorios.getTipos();
		Fabricantes fabricantes = this.repositorios.getFabricantes();
		Modelos modelos = this.repositorios.getModelos();
		Seguros seguros = this.repositorios.getSeguros();
		Pericias pericias = this.repositorios.getPericias();

		
		this.veiculos = veiculos.listar();
		this.cores = cores.listar();
		this.tipos = tipos.listar();
		this.fabricantes = fabricantes.listar();
		this.modelos = modelos.listar();
		this.seguros = seguros.listar();
		this.pericias = pericias.listar();
	}


	public String cadastrar(){
			Veiculos veiculos = this.repositorios.getveiculos();
			veiculos.salvar(veiculo);
		return "index?faces-redirect=true";
	}
	
	public String ocorrencia(ActionEvent event){
		Veiculo veiculo = (Veiculo) event.getComponent().getAttributes().get("codigo");
		String codigo = veiculo.getCodigo().toString();
		return "Ocorrencia?codigo="+codigo+"faces-redirect=true";
	}

	public void excluir(Veiculo veiculo){
		Veiculos veiculos = this.repositorios.getveiculos();
		veiculos.remover(veiculo);
		this.init();
	}
	
	
	/*public void listarVeiculo(String codigo){
	int idVeiculo = Integer.parseInt(codigo);
	Veiculos IVeiculo= this.repositorios.getveiculos();
	veiculos = IVeiculo.listarPorPlaca(idVeiculo);
	}*/
	
	public Veiculo getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(Veiculo veiculo) throws CloneNotSupportedException {
		this.veiculo = veiculo;
		if (this.veiculo == null) {
			this.veiculo = new Veiculo();
		}else{
			this.veiculo = (Veiculo) veiculo.clone();
		}
	}
	
	public List<Veiculo> getVeiculos() {
		return veiculos;
	}

	public void setVeiculos(List<Veiculo> veiculos) {
		this.veiculos = veiculos;
	}
	
	public List<Veiculo> getVeiculosFiltrados() {
		return veiculosFiltrados;
	}

	public void setVeiculosFiltrados(List<Veiculo> veiculosFiltrados) {
		this.veiculosFiltrados = veiculosFiltrados;
	}

	public List<Cor> getCores() {
		return cores;
	}

	public void setCores(List<Cor> cores) {
		this.cores = cores;
	}

	public List<Tipo> getTipos() {
		return tipos;
	}

	public void setTipos(List<Tipo> tipos) {
		this.tipos = tipos;
	}

	public List<Fabricante> getFabricantes() {
		return fabricantes;
	}

	public void setFabricantes(List<Fabricante> fabricantes) {
		this.fabricantes = fabricantes;
	}

	public List<Modelo> getModelos() {
		return modelos;
	}

	public void setModelos(List<Modelo> modelos) {
		this.modelos = modelos;
	}

	public List<Seguro> getSeguros() {
		return seguros;
	}

	public void setSeguros(List<Seguro> seguros) {
		this.seguros = seguros;
	}

	public List<Pericia> getPericias() {
		return pericias;
	}

	public void setPericias(List<Pericia> pericias) {
		this.pericias = pericias;
	}

	public StreamedContent getFile() {
		return file;
	}

	public void setFile(StreamedContent file) {
		this.file = file;
	}	
}