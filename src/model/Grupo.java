package model;

import java.io.Serializable;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/** Esta Classe que possui os metodos de acesso getter e setters que representa grupo de usuarios, 
 * e tamb�m possui o mapeamento relacional das tabelas via hibernate, da entidade Grupo.
*   
* @author silas
*
*/

@Entity
@Table
public class Grupo implements Serializable{
	
	
	private Integer codigo;
	private String grupo;
	
	public Grupo(){
		
	}	
	@Id
	@GeneratedValue
	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	@Column
	public String getGrupo() {
		return grupo;
	}
	public void setGrupo(String grupo) {
		this.grupo = grupo.toUpperCase();
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		result = prime * result + ((grupo == null) ? 0 : grupo.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Grupo other = (Grupo) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		if (grupo == null) {
			if (other.grupo != null)
				return false;
		} else if (!grupo.equals(other.grupo))
			return false;
		return true;
	}
	@Override
	public Object clone() throws CloneNotSupportedException {
		
		return super.clone();
	}
	
	
}
