package pcrn.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
public class Tarefa implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer codigo;
	private String descricaoTarefa;
	private Boolean ativada;
	private Date dataTarefa;
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
	public String getDescricaoTarefa() {
		return descricaoTarefa;
	}

	public void setDescricaoTarefa(String descricaoTarefa) {
		this.descricaoTarefa = descricaoTarefa;
	}

	@Column
	public Boolean getAtivada() {
		return ativada;
	}

	public void setAtivada(Boolean ativada) {
		this.ativada = ativada;
	}

	@Column
	public Date getDataTarefa() {
		return dataTarefa;
	}

	public void setDataTarefa(Date dataTarefa) {
		this.dataTarefa = dataTarefa;
	}
	
	@ManyToOne
	@JoinColumn(name="codigo_veiculo")
	public Veiculo getVeiculo() {
		return veiculo;
	}
	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}
	
	
}
