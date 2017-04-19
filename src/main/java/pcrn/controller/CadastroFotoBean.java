package pcrn.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.Part;

import org.primefaces.model.UploadedFile;

import pcrn.model.Foto;
import pcrn.model.Veiculo;
import pcrn.services.FotoService;

@Named
@ViewScoped
public class CadastroFotoBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private FotoService fotoService;
	
	private Foto foto = new Foto();
	private List<Foto> listaFotos = new ArrayList<Foto>();
	private Veiculo veiculo = new Veiculo();
    private UploadedFile file;
    private Part arquivo;
    
    public String voltar(String codigoVeiculo){
		
		String pagina = "/site/Veiculo/Edicao/Veiculo.xhtml?codigoVeiculo="+codigoVeiculo+"faces-redirect=true";
		System.out.println("pagina: " + pagina);
		return pagina;
	}
    
    public String enviar(String codigoVeiculo){
    	String pagina = "/site/Veiculo/Edicao/Veiculo.xhtml?codigoVeiculo="+codigoVeiculo;
    	
    	File outDir = new File("/resources/images/"+ codigoVeiculo);
    	
    	if (outDir.exists()) {
			System.out.println("Diretorio ja criado ");
		}else {
		//Caso o diretorio nao exista ele e criado.
			outDir. mkdirs();
		}
    	
    	String nomeArquivo = "/resources/images/"+ codigoVeiculo+arquivo.getName();
    	
    	try (InputStream is = arquivo.getInputStream();
    			OutputStream out = new FileOutputStream(nomeArquivo)) {
			
    		int read = 0;
    		byte[] bytes = new byte[1024];
    		while ((read = is.read()) != -1 ){
    			out.write(bytes, 0, read);
    		}
    		
		} catch (Exception e) {
			// TODO: handle exception
		}
		return pagina;
    	
    	
    }
    
    
    public String upload(ActionEvent event) throws IOException{
		//Pegando o valor do atributo event e colocando em veiculo.
		veiculo = (Veiculo) event.getComponent().getAttributes().get("codigo");

		//Variavel codigo recebe o codigo do veiculo referente aquela foto.
		int codigo = veiculo.getCodigo();
		//Diretorio de imagens onde as fotos ser�o salvas.
		//File outDir = new File("/var/lib/tomcat/webapps/Deprov/resources/images/"+ codigo);
		File outDir = new File("/resources/images/"+ codigo);
		//File outDir = new File("/opt/tomcat/webapps/Deprov/resources/images/"+ codigo);
        //Verifica se o diretorio j� existe.
		if (outDir.exists()) {
			System.out.println("Diretorio ja criado ");
		}else {
		//Caso o diretorio nao exista ele e criado.
			outDir. mkdirs();
		}
        
		//Variavel que operar� a operacao de leitura e escrita.
        FileOutputStream fos;
        //variavel nomeArquivo recebe o nome da foto.
        String nomeArquivo = file.getFileName();
        //variavel fotos recebe o array de bytes da foto.
        byte[] fotos = file.getContents();
        
        //Diretorio das fotos.
        String path = "/resources/images/"+ codigo+"/";
        //String path = "/var/lib/tomcat/webapps/Deprov/resources/images/"+ codigo+"/";
        //String path = "/opt/tomcat/webapps/Deprov/resources/images/"+ codigo+"/";
        //Referencia do caminho das fotos para ser salvos no banco, 
        //pois as consultas s�o mais r�pidas salvando as fotos em 1 diretorio e n�o dentro do banco, no banco salvamos apenas o caminho da foto.
        String pathBanco = "/resources/images/"+codigo+"/";
		try {
		//Variavel fos recebe o caminho da foto.
		fos = new FileOutputStream(path+nomeArquivo);
		//variavel fos escreve a foto no diretorio que a variavel path+nomeArquivo sao referenciados..
		fos.write(fotos);
		//Variavel fos fecha a conexao de arquivo.
		fos.close();
		} catch (FileNotFoundException ex) {
		} catch (IOException ex) {
		}
		
		//Estamos setando no atributo path o caminho do arquivo, para que esse path seja salvo no banco.
		this.foto.setPath(pathBanco+nomeArquivo); 
		
		//Estamos setando no atributo codigo, o identificador do veiculo.
		veiculo.setCodigo(codigo);
		//Estamos setando no atributo veiculo o veiculo que aquela foto faz parte.
		this.foto.setVeiculo(veiculo);
		//Esta linha salva a entidade foto.
		fotoService.salvar(foto);
		//Esta linha faz um redirecionamento de pagina para a pagina do veiculo que voc� cadastrou a foto.
	    String pagina = "/site/Veiculo/Edicao/Veiculo.xhtml?codigoVeiculo="+veiculo.getCodigo(); 
	    System.out.println(pagina);
	    return pagina;
	}
    
    public List<Foto> listarFotos(){
		
		String codigo = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("codigoVeiculo");
		int idVeiculo = Integer.parseInt(codigo); 
		listaFotos = fotoService.porCodigoVeiculo(idVeiculo);
		
		return listaFotos;
	}
    
    public void excluir() throws IOException {
		
		
		fotoService.remover(foto);
		
		//A variavel path recebe o caminho de onde a foto est� inserida.
		//String path = "/opt/tomcat/webapps/Deprov/resources/"+ foto.getPath();
		//String path = "/var/lib/tomcat/webapps/Deprov/resources/"+ foto.getPath();
		String path = foto.getPath();
		//Criando arquivo para ser deletado com o caminho especificado logo acima.
		File f = new File(path);
		//Deletando o arquivo do diretorio.
		f.delete();
		//Esta linha faz um redirecionamento de pagina para a pagina inicial do sistema.
	    FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml?faces-redirect=true");
	}

    
    
	public Part getArquivo() {
		return arquivo;
	}


	public void setArquivo(Part arquivo) {
		this.arquivo = arquivo;
	}


	public Foto getFoto() {
		return foto;
	}

	public void setFoto(Foto foto) {
		this.foto = foto;
	}

	public List<Foto> getListaFotos() {
		return listaFotos;
	}

	public void setListaFotos(List<Foto> listaFotos) {
		this.listaFotos = listaFotos;
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