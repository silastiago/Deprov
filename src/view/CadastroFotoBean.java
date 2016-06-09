package view;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUpload;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
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
	private StreamedContent imagem;
	

	@PostConstruct
	public void init() {
		IFoto fotos = repositorios.getFoto();
		this.listaFotos = fotos.listar();
		Veiculos veiculos = this.repositorios.getveiculos();
		this.veiculos = veiculos.listar();
	}
	
	public void criaArquivo(byte[] bytes, String arquivo) {
		FileOutputStream fos;
		String path = "/opt/tomcat/webapps/Deprov/resources/images/1/";
		try {
		fos = new FileOutputStream(path+arquivo);
		fos.write(bytes);
		fos.close();
		} catch (FileNotFoundException ex) {
		} catch (IOException ex) {
		}
		}
	
	
	public void handleFileUpload(FileUploadEvent event) {
		
		UploadedFile uploadedFile = event.getFile();  	 
		
			 
		 //imagem = new DefaultStreamedContent(event.getFile().getInputstream());
		
		 String nomeArquivo = uploadedFile.getFileName();
		 System.out.println("Nome do arquivo: " + nomeArquivo);
		 byte[] foto = event.getFile().getContents();
		 this.criaArquivo(foto, nomeArquivo);
		
		 
		
		 }
	
	public String cadastrar(String codigo) {
		//IFoto fotos = repositorios.getFoto();
		
		File outDir = new File("/opt/tomcat/webapps/Deprov/resources/images/"+codigo);
        if (outDir.exists()) {
			System.out.println("Diretorio j� criado ");
		}else {
			outDir. mkdirs();
		}
		
		
		
		
		
		
		int idVeiculo = Integer.parseInt(codigo);
		
		
		//String codigo = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("codigo");

		
		//System.out.println("Codigo do veiculo: " + codigo);
		
		// FacesContext fc = FacesContext.getCurrentInstance();
		// String id_veiculo =
		// fc.getExternalContext().getRequestParameterMap().get("codigo_veiculo")
		// ;
		System.out.println("ID do veiculo para a foto : " + codigo);
		// 
		//System.out.println("ID do veiculo: " + idVeiculo);
		
		//veiculo.setCodigo(idVeiculo);
		//ocorrencia.setVeiculo(veiculo);

		//ocorrencias.salvar(ocorrencia);
		

		
	
		return "index?faces-redirect=true";
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

	public StreamedContent getImagem() {
		return imagem;
	}

	public void setImagem(StreamedContent imagem) {
		this.imagem = imagem;
	}

	
}