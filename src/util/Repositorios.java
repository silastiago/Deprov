package util;

import java.io.Serializable;

import org.hibernate.Session;

import repository.Cores;
import repository.Fabricantes;
import repository.Grupos;
import repository.Ocorrencias;
import repository.Modelos;
import repository.Pericias;
import repository.Pessoas;
import repository.Seguros;
import repository.Tipos;
import repository.Veiculos;
import repository.impl.CoresImpl;
import repository.impl.FabricantesImpl;
import repository.impl.GruposImpl;
import repository.impl.ModelosImpl;
import repository.impl.OcorrenciaImpl;
import repository.impl.PericiasImpl;
import repository.impl.PessoasImpl;
import repository.impl.SegurosImpl;
import repository.impl.TiposImpl;
import repository.impl.VeiculosImpl;


public class Repositorios implements Serializable {

	public Pessoas getPessoas() {
		return new PessoasImpl(this.getSession());
	}
	
	public Fabricantes getFabricantes(){
		return new FabricantesImpl(this.getSession());
	}
	
	public Modelos getModelos(){
		return new ModelosImpl(this.getSession());
	}
	
	public Cores getCores(){
		return new CoresImpl(this.getSession());
	}
	
	public Tipos getTipos(){
		return new TiposImpl(this.getSession());
	}
	
	public Pericias getPericias(){
		return new PericiasImpl(this.getSession());
	}
	
	public Seguros getSeguros(){
		return new SegurosImpl(this.getSession());
	}
	
	public Veiculos getveiculos(){
		return new VeiculosImpl(this.getSession());
	}
	
	public Grupos getGrupos(){
		return new GruposImpl(this.getSession());
	}
	
	public Ocorrencias getocorrencia(){
		return new OcorrenciaImpl(this.getSession());
	}
	
	private Session getSession() {
		return (Session) FacesUtil.getRequestAttribute("session");
	}

}
