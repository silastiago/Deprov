package view;

import java.io.File;
import java.io.FileOutputStream;

import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.primefaces.event.FileUploadEvent;

public class UploadArquivo {

	private String caminho;
	private byte[] arquivo;
	private String nome;

	public UploadArquivo() {
	}


	public String getNome() {
		return nome;
	}



	public String getRealPath() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ServletContext servletContext = (ServletContext) facesContext.getExternalContext().getContext();

		return servletContext.getRealPath("/");
	}

	public void fileUpload(FileUploadEvent event, String diretorio) {
		try {
			this.caminho = getRealPath() + diretorio + getNome();
			this.arquivo = event.getFile().getContents();

			File file = new File(getRealPath() + diretorio);
			file.mkdirs();

		} catch (Exception ex) {
			System.out.println("Erro no upload do arquivo" + ex);
		}
	}

	public void gravar(){
		try {

			FileOutputStream fos;
			fos = new FileOutputStream(this.caminho);
			fos.write(this.arquivo);
			fos.close();

		} catch (Exception ex) {
			System.out.println(ex);
		}
	}
}
