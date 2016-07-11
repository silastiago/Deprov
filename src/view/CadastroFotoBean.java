package view;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import org.primefaces.model.UploadedFile;

import model.Foto;
import model.Ocorrencia;
import model.Veiculo;
import repository.IFoto;
import repository.Ocorrencias;
import repository.Veiculos;
import util.Repositorios;

@ManagedBean(name = "cadastroFotoBean")
@ViewScoped
public class CadastroFotoBean implements Serializable {

	private Repositorios repositorios = new Repositorios();
	private Foto foto = new Foto();
	private List<Foto> listaFotos = new ArrayList<Foto>();
	private List<Veiculo> veiculos = new ArrayList<Veiculo>();
	private Veiculo veiculo = new Veiculo();
    private UploadedFile file;

	@PostConstruct
	public void init() {
		IFoto fotos = repositorios.getFoto();
		this.listaFotos = fotos.listar();
		Veiculos veiculos = this.repositorios.getveiculos();
		this.veiculos = veiculos.listar();
	
	}
	
	public String upload(ActionEvent event){
		veiculo = (Veiculo) event.getComponent().getAttributes().get("codigo");
		System.out.println("codigo do veiculo: " + veiculo.getCodigo());
		
		String codigo = veiculo.getCodigo().toString();
		
		System.out.println("Codigo do veiculo: " + codigo);
		File outDir = new File("/var/lib/tomcat8/webapps/Deprov/resources/images/"+ codigo);
		//File outDir = new File("/opt/tomcat/webapps/Deprov/resources/images/"+ codigo);
        if (outDir.exists()) {
			System.out.println("Diretorio j� criado ");
		}else {
			outDir. mkdirs();
		}
        
        FileOutputStream fos;
        String nomeArquivo = file.getFileName();
        byte[] fotos = file.getContents();
        
        String path = "/var/lib/tomcat8/webapps/Deprov/resources/images/"+ codigo+"/";
        //String path = "/opt/tomcat/webapps/Deprov/resources/images/"+ codigo+"/";
        String pathBanco = "../resources/images/"+codigo+"/";
		try {
		fos = new FileOutputStream(path+nomeArquivo);
		fos.write(fotos);
		fos.close();
		} catch (FileNotFoundException ex) {
		} catch (IOException ex) {
		}
		this.foto.setPath(pathBanco+nomeArquivo);
        
		IFoto Ifoto = repositorios.getFoto();
		int idVeiculo = Integer.parseInt(codigo);
		veiculo.setCodigo(idVeiculo);
		this.foto.setVeiculo(veiculo);
		Ifoto.salvar(foto);
		
		
        //return "index?faces-redirect=true";
		return null;
	}
	
	public List<Foto> listarFotos(int codigo){
		IFoto Ifoto = repositorios.getFoto();
		//int idVeiculo = Integer.parseInt(veiculo.getCodigo());
		listaFotos = Ifoto.porCodigoVeiculo(codigo);
		
		return listaFotos;
	}
	
	public void update(Ocorrencia ocorrencia) {
		Ocorrencias ocorrencias = this.repositorios.getocorrencia();
		ocorrencias.editar(ocorrencia);
	}

	public void excluir(Ocorrencia ocorrencia) {
		Ocorrencias ocorrencias = this.repositorios.getocorrencia();
		ocorrencias.remover(ocorrencia);
		this.init();
	}

	public Foto getFoto() {
		return foto;
	}

	public void setFoto(Foto foto) throws CloneNotSupportedException {
		this.foto = foto;
		if (this.foto == null) {
			this.foto = new Foto();
		}else{
			this.foto = (Foto) foto.clone();
		}
	}

	public List<Foto> getListaFotos() {
		return listaFotos;
	}

	public void setListaFotos(List<Foto> listaFotos) {
		this.listaFotos = listaFotos;
	}

	public List<Veiculo> getVeiculos() {
		return veiculos;
	}

	public void setVeiculos(List<Veiculo> veiculos) {
		this.veiculos = veiculos;
	}

	public Veiculo getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}	
}