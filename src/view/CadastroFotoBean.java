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
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.model.UploadedFile;

import model.Foto;
import model.Veiculo;
import repository.IFoto;
import util.Repositorios;

/** Esta é uma Classe concreta que une as implementacoes das interfaces e das paginas xhtml referentes a entidade Foto.
*   
* @author silas
* @since 22-08-2016
*/

@ManagedBean(name = "cadastroFotoBean")
@ViewScoped
public class CadastroFotoBean implements Serializable {

	private Repositorios repositorios = new Repositorios();
	private Foto foto = new Foto();
	private List<Foto> listaFotos = new ArrayList<Foto>();
	private Veiculo veiculo = new Veiculo();
    private UploadedFile file;

    //Primeiro metodo a ser executado quando entra nas views referentes a foto de veiculos.
	@PostConstruct
	public void init() {
		listaFotos = listarFotos();
	}
	
	/** Este metodo faz o upload de uma foto de um veiculo.
	* 	@param event, este event é o evento que buscamos o codigo do veiculo referente a foto.
	*/
	public void upload(ActionEvent event) throws IOException{
		//Pegando o valor do atributo event e colocando em veiculo.
		veiculo = (Veiculo) event.getComponent().getAttributes().get("codigo");

		//Variavel codigo recebe o codigo do veiculo referente aquela foto.
		String codigo = veiculo.getCodigo().toString();
		//Diretorio de imagens onde as fotos serão salvas.
		//File outDir = new File("/var/lib/tomcat8/webapps/Deprov/resources/images/"+ codigo);
		File outDir = new File("/opt/tomcat/webapps/Deprov/resources/images/"+ codigo);
        //Verifica se o diretorio já existe.
		if (outDir.exists()) {
			System.out.println("Diretorio já criado ");
		}else {
		//Caso o diretorio não exista ele é criado.
			outDir. mkdirs();
		}
        
		//Variavel que operará a operacao de leitura e escrita.
        FileOutputStream fos;
        //variavel nomeArquivo recebe o nome da foto.
        String nomeArquivo = file.getFileName();
        //variavel fotos recebe o array de bytes da foto.
        byte[] fotos = file.getContents();
        
        //Diretorio das fotos.
        //String path = "/var/lib/tomcat8/webapps/Deprov/resources/images/"+ codigo+"/";
        String path = "/opt/tomcat/webapps/Deprov/resources/images/"+ codigo+"/";
        //Referencia do caminho das fotos para ser salvos no banco, 
        //pois as consultas são mais rápidas salvando as fotos em 1 diretorio e não dentro do banco, no banco salvamos apenas o caminho da foto.
        String pathBanco = "../resources/images/"+codigo+"/";
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
        
		//Esta linha estou instanciando a interface com sua implementação.
		IFoto Ifoto = repositorios.getFoto();
		//Convertendo a string codigo que é o id do veiculo para um inteiro. 
		int idVeiculo = Integer.parseInt(codigo);
		//Estamos setando no atributo codigo, o identificador do veiculo.
		veiculo.setCodigo(idVeiculo);
		//Estamos setando no atributo veiculo o veiculo que aquela foto faz parte.
		this.foto.setVeiculo(veiculo);
		//Esta linha salva a entidade foto.
		Ifoto.salvar(foto);
		//Esta linha faz um redirecionamento de pagina para a pagina do veiculo que você cadastrou a foto.
	    FacesContext.getCurrentInstance().getExternalContext().redirect("Veiculo.xhtml?codigo="+veiculo.getCodigo());
	}
	
	/** Este metodo lista todas as fotos de um veiculo.
	* 	@return retorna a lista de todas as fotos de um veiculo.
	*/
	public List<Foto> listarFotos(){
		//variavel codigo recebe por uma requisição o codigo do veiculo.
		String codigo = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("codigo");
		//Convertendo a string codigo que é o id do veiculo para um inteiro.
		int idVeiculo = Integer.parseInt(codigo);
		//Esta linha estou instanciando a interface com sua implementação.
		IFoto Ifoto = repositorios.getFoto();
		//A listaFotos recebe todas as fotos pelo codigo do veiculo. 
		listaFotos = Ifoto.porCodigoVeiculo(idVeiculo);
		return listaFotos;
	}

	/** Este metodo Remove uma foto.
	*/
	public void excluir() throws IOException {
		
		//Esta linha estou instanciando a interface com sua implementação.
		IFoto Ifoto = this.repositorios.getFoto();
		//Esta linha remove a entidade foto do banco.
		Ifoto.remover(foto);
		
		//A variavel path recebe o caminho de onde a foto está inserida.
		String path = "/opt/tomcat/webapps/Deprov/resources/"+ foto.getPath();
		//Criando arquivo para ser deletado com o caminho especificado logo acima.
		File f = new File(path);
		//Deletando o arquivo do diretorio.
		f.delete();		
		//Esta linha faz um redirecionamento de pagina para a pagina inicial do sistema.
	    FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml?faces-redirect=true");
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