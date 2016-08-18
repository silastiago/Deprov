package model;

import java.io.Serializable;
import java.util.Date;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/** Esta Classe que possui os metodos de acesso getter e setters que representa as ocorrencias de umn determinado veiculo, 
 * e também possui o mapeamento relacional das tabelas via hibernate, da entidade Ocorrencia.
*   
* @author silas
*
*/

@Entity
@Table
public class Ocorrencia implements Serializable, Cloneable{

	private Integer codigo;
	private String ocorrencia;
	private Date data;
	private Veiculo veiculo;
	
	@Id
	@GeneratedValue
	public Integer getCodigo() {
		return codigo;
	}
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	
	@Column
	public String getOcorrencia() {
		return ocorrencia;
	}
	public void setOcorrencia(String ocorrencia) {
		this.ocorrencia = ocorrencia.toUpperCase();
	}
	@Column
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	@ManyToOne
	@JoinColumn(name="codigo_veiculo")
	public Veiculo getVeiculo() {
		return veiculo;
	}
	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}
	@Override
	public Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}
}