package model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.servlet.http.Part;

import org.hibernate.annotations.ManyToAny;


@Entity
@Table
public class Veiculo implements Serializable, Cloneable{
	private Integer codigo;
	private String placa;
	private String placaOriginal;
	private Tipo tipo;
	private Cor cor;
	private Marca marca;
	private Modelo modelo;
	private String Anofabricacao;
	private String chassi;
	private String motor;
	private String LocalAtual;
	private String Obs;
	private String numero_ocorrencia;
	private String ip_processo;
	private Seguro seguro;
	private String situacao;
	private String condicao;
	private String proprietario;
	private String chave;
	private String comentarios;
	private Pericia pericia;
	private Date date4;


	@Id
	@GeneratedValue
	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	/*
	@Column(name="dataEntrada")
	@Temporal(TemporalType.DATE)
	public Date getDataEntrada() {
		return dataEntrada;
	}

	public void setDataEntrada(Date dataEntrada) {
		this.dataEntrada = dataEntrada;
	}
	@Column(name="sataSaida")
	@Temporal(TemporalType.DATE)
	public Date getDataSaida() {
		return dataSaida;
	}

	public void setDataSaida(Date dataSaida) {
		this.dataSaida = dataSaida;
	}
	*/
	
	@Column
	public Date getDate4() {
		return date4;
	}


	public void setDate4(Date date4) {
		this.date4 = date4;
	}


	@Column
	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}
	
	@Column
	public String getPlacaOriginal() {
		return placaOriginal;
	}

	public void setPlacaOriginal(String placaOriginal) {
		this.placaOriginal = placaOriginal;
	}

	@ManyToOne
	@JoinColumn(name="codigo_tipo", referencedColumnName="codigo")
	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}
	@ManyToOne
	@JoinColumn(name="codigo_cor", referencedColumnName="codigo")
	public Cor getCor() {
		return cor;
	}

	public void setCor(Cor cor) {
		this.cor = cor;
	}
	@ManyToOne
	@JoinColumn(name="codigo_marca", referencedColumnName="codigo")
	public Marca getMarca() {
		return marca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}
	@ManyToOne
	@JoinColumn(name="codigo_modelo", referencedColumnName="codigo")
	public Modelo getModelo() {
		return modelo;
	}

	public void setModelo(Modelo modelo) {
		this.modelo = modelo;
	}
	@Column
	public String getAnofabricacao() {
		return Anofabricacao;
	}

	public void setAnofabricacao(String anofabricacao) {
		Anofabricacao = anofabricacao;
	}
	@Column
	public String getChassi() {
		return chassi;
	}

	public void setChassi(String chassi) {
		this.chassi = chassi;
	}
	@Column
	public String getMotor() {
		return motor;
	}

	public void setMotor(String motor) {
		this.motor = motor;
	}
	@Column
	public String getLocalAtual() {
		return LocalAtual;
	}

	public void setLocalAtual(String localAtual) {
		LocalAtual = localAtual;
	}
	@Column
	public String getObs() {
		return Obs;
	}

	public void setObs(String obs) {
		Obs = obs;
	}
	@Column
	public String getNumero_ocorrencia() {
		return numero_ocorrencia;
	}

	public void setNumero_ocorrencia(String numero_ocorrencia) {
		this.numero_ocorrencia = numero_ocorrencia;
	}
	@Column
	public String getIp_processo() {
		return ip_processo;
	}

	public void setIp_processo(String ip_processo) {
		this.ip_processo = ip_processo;
	}
	@ManyToOne
	@JoinColumn(name="codigo_seguro", referencedColumnName="codigo")
	public Seguro getSeguro() {
		return seguro;
	}

	public void setSeguro(Seguro seguro) {
		this.seguro = seguro;
	}
	@Column
	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}
	@Column
	public String getCondicao() {
		return condicao;
	}

	public void setCondicao(String condicao) {
		this.condicao = condicao;
	}
	@Column
	public String getProprietario() {
		return proprietario;
	}

	public void setProprietario(String proprietario) {
		this.proprietario = proprietario;
	}
	@Column
	public String getChave() {
		return chave;
	}

	public void setChave(String chave) {
		this.chave = chave;
	}
	@Column
	public String getComentarios() {
		return comentarios;
	}

	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}

	@ManyToOne
	@JoinColumn(name="codigo_pericia")
	public Pericia getPericia() {
		return pericia;
	}

	public void setPericia(Pericia pericia) {
		this.pericia = pericia;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Anofabricacao == null) ? 0 : Anofabricacao.hashCode());
		result = prime * result + ((LocalAtual == null) ? 0 : LocalAtual.hashCode());
		result = prime * result + ((Obs == null) ? 0 : Obs.hashCode());
		result = prime * result + ((chassi == null) ? 0 : chassi.hashCode());
		result = prime * result + ((chave == null) ? 0 : chave.hashCode());
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		result = prime * result + ((comentarios == null) ? 0 : comentarios.hashCode());
		result = prime * result + ((condicao == null) ? 0 : condicao.hashCode());
		result = prime * result + ((cor == null) ? 0 : cor.hashCode());
		result = prime * result + ((date4 == null) ? 0 : date4.hashCode());
		result = prime * result + ((marca == null) ? 0 : marca.hashCode());
		result = prime * result + ((ip_processo == null) ? 0 : ip_processo.hashCode());
		result = prime * result + ((modelo == null) ? 0 : modelo.hashCode());
		result = prime * result + ((motor == null) ? 0 : motor.hashCode());
		result = prime * result + ((numero_ocorrencia == null) ? 0 : numero_ocorrencia.hashCode());
		result = prime * result + ((pericia == null) ? 0 : pericia.hashCode());
		result = prime * result + ((placa == null) ? 0 : placa.hashCode());
		result = prime * result + ((proprietario == null) ? 0 : proprietario.hashCode());
		result = prime * result + ((seguro == null) ? 0 : seguro.hashCode());
		result = prime * result + ((situacao == null) ? 0 : situacao.hashCode());
		result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
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
		Veiculo other = (Veiculo) obj;
		if (Anofabricacao == null) {
			if (other.Anofabricacao != null)
				return false;
		} else if (!Anofabricacao.equals(other.Anofabricacao))
			return false;
		if (LocalAtual == null) {
			if (other.LocalAtual != null)
				return false;
		} else if (!LocalAtual.equals(other.LocalAtual))
			return false;
		if (Obs == null) {
			if (other.Obs != null)
				return false;
		} else if (!Obs.equals(other.Obs))
			return false;
		if (chassi == null) {
			if (other.chassi != null)
				return false;
		} else if (!chassi.equals(other.chassi))
			return false;
		if (chave == null) {
			if (other.chave != null)
				return false;
		} else if (!chave.equals(other.chave))
			return false;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		if (comentarios == null) {
			if (other.comentarios != null)
				return false;
		} else if (!comentarios.equals(other.comentarios))
			return false;
		if (condicao == null) {
			if (other.condicao != null)
				return false;
		} else if (!condicao.equals(other.condicao))
			return false;
		if (cor == null) {
			if (other.cor != null)
				return false;
		} else if (!cor.equals(other.cor))
			return false;
		if (date4 == null) {
			if (other.date4 != null)
				return false;
		} else if (!date4.equals(other.date4))
			return false;
		if (marca == null) {
			if (other.marca != null)
				return false;
		} else if (!marca.equals(other.marca))
			return false;
		if (ip_processo == null) {
			if (other.ip_processo != null)
				return false;
		} else if (!ip_processo.equals(other.ip_processo))
			return false;
		if (modelo == null) {
			if (other.modelo != null)
				return false;
		} else if (!modelo.equals(other.modelo))
			return false;
		if (motor == null) {
			if (other.motor != null)
				return false;
		} else if (!motor.equals(other.motor))
			return false;
		if (numero_ocorrencia == null) {
			if (other.numero_ocorrencia != null)
				return false;
		} else if (!numero_ocorrencia.equals(other.numero_ocorrencia))
			return false;
		if (pericia == null) {
			if (other.pericia != null)
				return false;
		} else if (!pericia.equals(other.pericia))
			return false;
		if (placa == null) {
			if (other.placa != null)
				return false;
		} else if (!placa.equals(other.placa))
			return false;
		if (proprietario == null) {
			if (other.proprietario != null)
				return false;
		} else if (!proprietario.equals(other.proprietario))
			return false;
		if (seguro == null) {
			if (other.seguro != null)
				return false;
		} else if (!seguro.equals(other.seguro))
			return false;
		if (situacao == null) {
			if (other.situacao != null)
				return false;
		} else if (!situacao.equals(other.situacao))
			return false;
		if (tipo == null) {
			if (other.tipo != null)
				return false;
		} else if (!tipo.equals(other.tipo))
			return false;
		return true;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}

}