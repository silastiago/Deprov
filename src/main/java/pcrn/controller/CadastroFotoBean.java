package pcrn.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
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
	private Foto fotoSelecionada;
	private List<Foto> listaFotos = new ArrayList<Foto>();
	private Veiculo veiculo = new Veiculo();
    private UploadedFile file;
    
    public String voltar(String codigoVeiculo){
		
		String pagina = "/site/Veiculo/Edicao/Veiculo.xhtml?codigoVeiculo="+codigoVeiculo+"faces-redirect=true";
		System.out.println("pagina: " + pagina);
		return pagina;
	}
    
    /*public String upload(FileUploadEvent event, String codigoVeiculo){
    	String diretorioArquivo = "../../../resources/images/"+codigoVeiculo; 
    	
    	File outDir = new File(diretorioArquivo);
    	
    	if (outDir.exists()) {
			System.out.println("Diretorio ja criado ");
		}else {
		//Caso o diretorio nao exista ele e criado.
			outDir. mkdirs();
		}    	
    	
    	FileOutputStream fos;
        //variavel nomeArquivo recebe o nome da foto.
        String nomeArquivo = file.getFileName();
        //variavel fotos recebe o array de bytes da foto.
        byte[] fotos = file.getContents();
        
        //Diretorio das fotos.
        
		try {
		//Variavel fos recebe o caminho da foto.
		fos = new FileOutputStream(diretorioArquivo+"/"+event.getFile().getFileName());
		//variavel fos escreve a foto no diretorio que a variavel path+nomeArquivo sao referenciados..
		fos.write(fotos);
		//Variavel fos fecha a conexao de arquivo.
		fos.close();
		} catch (FileNotFoundException ex) {
		} catch (IOException ex) {
		}
		
		//Estamos setando no atributo path o caminho do arquivo, para que esse path seja salvo no banco.
		this.foto.setPath(diretorioArquivo+"/"+event.getFile().getFileName());
		//Estamos setando no atributo codigo, o identificador do veiculo.
		veiculo.setCodigo(Integer.parseInt(codigoVeiculo));
		//Estamos setando no atributo veiculo o veiculo que aquela foto faz parte.
		this.foto.setVeiculo(veiculo);
		//Esta linha salva a entidade foto.
		fotoService.salvar(foto);
		//Esta linha faz um redirecionamento de pagina para a pagina do veiculo que voc� cadastrou a foto.
	    String pagina = "/site/Veiculo/Edicao/Veiculo.xhtml?codigoVeiculo="+veiculo.getCodigo()+"faces-redirect=true";
	    System.out.println(pagina);
	    return pagina;
    	
    }*/
    
    public String upload(String codigoVeiculo) throws IOException{
		int codigo = Integer.parseInt(codigoVeiculo);
		//Diretorio de imagens onde as fotos ser�o salvas.
		String diretorioArquivo = "../../../resources/images/"+ codigo+"/";
		File outDir = new File(diretorioArquivo);
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
        //String path = "/var/lib/tomcat/webapps/Deprov/resources/images/"+ codigo+"/";
        String path = "/opt/tomcat/webapps/Deprov/resources/images/"+ codigo+"/";
        
        //Referencia do caminho das fotos para ser salvos no banco, 
        //pois as consultas s�o mais r�pidas salvando as fotos em 1 diretorio e n�o dentro do banco, no banco salvamos apenas o caminho da foto.
        //String pathBanco = "/resources/images/"+codigo+"/";
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
		this.foto.setPath(diretorioArquivo+nomeArquivo);
		//Estamos setando no atributo codigo, o identificador do veiculo.
		veiculo.setCodigo(codigo);
		//Estamos setando no atributo veiculo o veiculo que aquela foto faz parte.
		this.foto.setVeiculo(veiculo);
		//Esta linha salva a entidade foto.
		fotoService.salvar(foto);
		
		
		
		//Esta linha faz um redirecionamento de pagina para a pagina do veiculo que voc� cadastrou a foto.
	    String pagina = "/site/Veiculo/Edicao/Veiculo.xhtml?codigoVeiculo="+codigo+"faces-redirect=true";
	    return pagina;
	}
    
    public List<Foto> listarFotos(String codigo){
		
		int idVeiculo = Integer.parseInt(codigo); 
		listaFotos = fotoService.porCodigoVeiculo(idVeiculo);
		
		return listaFotos;
	}
    
    public String excluir() {
		
		
		fotoService.remover(fotoSelecionada);
		
		//A variavel path recebe o caminho de onde a foto est� inserida.
		//String path = "/opt/tomcat/webapps/Deprov/resources/"+ foto.getPath();
		//String path = "/var/lib/tomcat/webapps/Deprov/resources/"+ foto.getPath();
		String path = fotoSelecionada.getPath();
		//Criando arquivo para ser deletado com o caminho especificado logo acima.
		File f = new File(path);
		//Deletando o arquivo do diretorio.
		f.delete();
	    
	    String pagina = "/site/Veiculo/Edicao/Veiculo.xhtml?codigoVeiculo="+fotoSelecionada.getVeiculo().getCodigo()+"faces-redirect=true";
	    
	    return pagina;
	    
	}
    
	
	
	public Foto getFotoSelecionada() {
		return fotoSelecionada;
	}

	public void setFotoSelecionada(Foto fotoSelecionada) {
		this.fotoSelecionada = fotoSelecionada;
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