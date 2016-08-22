package util;

import java.io.Serializable;

import org.hibernate.Session;

import repository.Cores;
import repository.Grupos;
import repository.IFoto;
import repository.Fabricantes;
import repository.Modelos;
import repository.Ocorrencias;
import repository.Pericias;
import repository.Pessoas;
import repository.Seguros;
import repository.Tipos;
import repository.Veiculos;
import repository.impl.CoresImpl;
import repository.impl.FotoImpl;
import repository.impl.GruposImpl;
import repository.impl.FabricanteImpl;
import repository.impl.ModelosImpl;
import repository.impl.OcorrenciaImpl;
import repository.impl.PericiasImpl;
import repository.impl.PessoasImpl;
import repository.impl.SegurosImpl;
import repository.impl.TiposImpl;
import repository.impl.VeiculosImpl;

/** Esta é uma Classe concreta que instancia as interfaces,
*   
* @author silas
* @since 16-08-2016
*/
public class Repositorios implements Serializable {

	/** Este metodo retorna a interface Pessoas instanciada com sua implementacao.
	* 	@return retorna a interface Pessoas.
	*/
	public Pessoas getPessoas() {
		return new PessoasImpl(this.getSession());
	}
	
	/** Este metodo retorna a interface Fabricantes instanciada com sua implementacao.
	* 	@return retorna a interface Fabricantes.
	*/
	public Fabricantes getFabricantes(){
		return new FabricanteImpl(this.getSession());
	}
	
	/** Este metodo retorna a interface Modelos instanciada com sua implementacao.
	* 	@return retorna a interface Modelos.
	*/
	public Modelos getModelos(){
		return new ModelosImpl(this.getSession());
	}
	
	/** Este metodo retorna a interface Cores instanciada com sua implementacao.
	* 	@return retorna a interface Cores.
	*/
	public Cores getCores(){
		return new CoresImpl(this.getSession());
	}
	
	/** Este metodo retorna a interface Tipos instanciada com sua implementacao.
	* 	@return retorna a interface Tipos.
	*/
	public Tipos getTipos(){
		return new TiposImpl(this.getSession());
	}
	
	/** Este metodo retorna a interface Pericias instanciada com sua implementacao.
	* 	@return retorna a interface Pericias.
	*/
	public Pericias getPericias(){
		return new PericiasImpl(this.getSession());
	}
	
	/** Este metodo retorna a interface Seguros instanciada com sua implementacao.
	* 	@return retorna a interface Seguros.
	*/
	public Seguros getSeguros(){
		return new SegurosImpl(this.getSession());
	}
	
	/** Este metodo retorna a interface Veiculos instanciada com sua implementacao.
	* 	@return retorna a interface Veiculos.
	*/
	public Veiculos getveiculos(){
		return new VeiculosImpl(this.getSession());
	}
	
	/** Este metodo retorna a interface Grupos instanciada com sua implementacao.
	* 	@return retorna a interface Grupos.
	*/
	public Grupos getGrupos(){
		return new GruposImpl(this.getSession());
	}
	
	/** Este metodo retorna a interface Ocorrencias instanciada com sua implementacao.
	* 	@return retorna a interface Ocorrencias.
	*/
	public Ocorrencias getocorrencia(){
		return new OcorrenciaImpl(this.getSession());
	}
	
	/** Este metodo retorna a interface IFoto instanciada com sua implementacao.
	* 	@return retorna a interface IFoto.
	*/
	public IFoto getFoto(){
		return new FotoImpl(this.getSession());
	}
	
	/** Este metodo pega a sessao do hibernate.
	* 	@return retorna a Sesion.
	*/
	private Session getSession() {
		return (Session) FacesUtil.getRequestAttribute("session");
	}
}