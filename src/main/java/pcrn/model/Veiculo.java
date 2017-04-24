package pcrn.model;

import java.io.Serializable;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/** Classe Veiculo que possui os metodos de acesso getter e setters, 
 * e tamb�m o mapeamento relacional das tabelas via hibernate, da entidade Veiculo.
*   
* @author silas
*
*/

@Entity
@Table
public class Veiculo implements Serializable, Cloneable{
	private Integer codigo;
	private String dossie;
	private String placa;
	private String placaOriginal;
	private Tipo tipo;
	private Cor cor;
	private Situacao situacao;
	private Fabricante fabricante;
	private Modelo modelo;
	private String Anofabricacao;
	private String chassi;
	private String motor;
	private Local local;
	private String Obs;
	private String numero_ocorrencia;
	private String ip;
	private String processo;
	private Seguro seguro;
	private String condicao;
	private String proprietario;
	private String chave;
	private String comentarios;
	private String motivoApreensao;
	private Pericia pericia;
	private Date dataEntrada;
	private Pessoa pessoa;
	private List<Ocorrencia> ocorrencias;
	private List<Foto> fotos;


	@Id
	@GeneratedValue
	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	
	@Column
	public String getDossie() {
		return dossie;
	}

	public void setDossie(String dossie) {
		this.dossie = dossie;
	}
	
	@Column
	public Date getDataEntrada() {
		return dataEntrada;
	}

	public void setDataEntrada(Date dataEntrada) {
		this.dataEntrada = dataEntrada;
	}

	@Column
	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa.toUpperCase();
	}
	
	@Column
	public String getPlacaOriginal() {
		return placaOriginal;
	}

	public void setPlacaOriginal(String placaOriginal) {
		this.placaOriginal = placaOriginal.toUpperCase();
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
	@JoinColumn(name="codigo_fabricante", referencedColumnName="codigo")
	public Fabricante getFabricante() {
		return fabricante;
	}

	public void setFabricante(Fabricante fabricante) {
		this.fabricante = fabricante;
	}
	@ManyToOne
	@JoinColumn(name="codigo_modelo", referencedColumnName="codigo")
	public Modelo getModelo() {
		return modelo;
	}

	public void setModelo(Modelo modelo) {
		this.modelo = modelo;
	}	
	
	@ManyToOne
	@JoinColumn(name="codigo_local", referencedColumnName="codigo")
	public Local getLocal() {
		return local;
	}

	public void setLocal(Local local) {
		this.local = local;
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
		this.chassi = chassi.toUpperCase();
	}
	@Column
	public String getMotor() {
		return motor;
	}

	public void setMotor(String motor) {
		this.motor = motor.toUpperCase();
	}	
	
	@Column
	public String getObs() {
		return Obs;
	}

	public void setObs(String obs) {
		Obs = obs.toUpperCase();
	}
	@Column
	public String getNumero_ocorrencia() {
		return numero_ocorrencia;
	}

	public void setNumero_ocorrencia(String numero_ocorrencia) {
		this.numero_ocorrencia = numero_ocorrencia.toUpperCase();
	}
	
	@Column
	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}
	
	@Column
	public String getProcesso() {
		return processo;
	}

	public void setProcesso(String processo) {
		this.processo = processo;
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
	public String getCondicao() {
		return condicao;
	}

	public void setCondicao(String condicao) {
		this.condicao = condicao.toUpperCase();
	}
	@Column
	public String getProprietario() {
		return proprietario;
	}

	public void setProprietario(String proprietario) {
		this.proprietario = proprietario.toUpperCase();
	}
	@Column
	public String getChave() {
		return chave;
	}

	public void setChave(String chave) {
		this.chave = chave.toUpperCase();
	}
	@Column
	public String getComentarios() {
		return comentarios;
	}

	public void setComentarios(String comentarios) {
		this.comentarios = comentarios.toUpperCase();
	}

	@Column
	public String getMotivoApreensao() {
		return motivoApreensao;
	}

	public void setMotivoApreensao(String motivoApreensao) {
		this.motivoApreensao = motivoApreensao.toUpperCase();
	}

	@ManyToOne
	@JoinColumn(name="codigo_pericia")
	public Pericia getPericia() {
		return pericia;
	}

	public void setPericia(Pericia pericia) {
		this.pericia = pericia;
	}
	
	@ManyToOne
	@JoinColumn(name="codigo_situacao")
	public Situacao getSituacao() {
		return situacao;
	}

	public void setSituacao(Situacao situacao) {
		this.situacao = situacao;
	}
	
	@ManyToOne
	@JoinColumn(name="codigo_pessoa")
	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	
	@OneToMany(mappedBy="veiculo", cascade = CascadeType.REMOVE)
	public List<Ocorrencia> getOcorrencias() {
		return ocorrencias;
	}

	public void setOcorrencias(List<Ocorrencia> ocorrencias) {
		this.ocorrencias = ocorrencias;
	}

	@OneToMany(mappedBy="veiculo", cascade = CascadeType.REMOVE)
	public List<Foto> getFotos() {
		return fotos;
	}

	public void setFotos(List<Foto> fotos) {
		this.fotos = fotos;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Anofabricacao == null) ? 0 : Anofabricacao.hashCode());
		result = prime * result + ((Obs == null) ? 0 : Obs.hashCode());
		result = prime * result + ((chassi == null) ? 0 : chassi.hashCode());
		result = prime * result + ((chave == null) ? 0 : chave.hashCode());
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		result = prime * result + ((comentarios == null) ? 0 : comentarios.hashCode());
		result = prime * result + ((condicao == null) ? 0 : condicao.hashCode());
		result = prime * result + ((cor == null) ? 0 : cor.hashCode());
		result = prime * result + ((dataEntrada == null) ? 0 : dataEntrada.hashCode());
		result = prime * result + ((dossie == null) ? 0 : dossie.hashCode());
		result = prime * result + ((fabricante == null) ? 0 : fabricante.hashCode());
		result = prime * result + ((ip == null) ? 0 : ip.hashCode());
		result = prime * result + ((local == null) ? 0 : local.hashCode());
		result = prime * result + ((modelo == null) ? 0 : modelo.hashCode());
		result = prime * result + ((motivoApreensao == null) ? 0 : motivoApreensao.hashCode());
		result = prime * result + ((motor == null) ? 0 : motor.hashCode());
		result = prime * result + ((numero_ocorrencia == null) ? 0 : numero_ocorrencia.hashCode());
		result = prime * result + ((pericia == null) ? 0 : pericia.hashCode());
		result = prime * result + ((pessoa == null) ? 0 : pessoa.hashCode());
		result = prime * result + ((placa == null) ? 0 : placa.hashCode());
		result = prime * result + ((placaOriginal == null) ? 0 : placaOriginal.hashCode());
		result = prime * result + ((processo == null) ? 0 : processo.hashCode());
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
		if (dataEntrada == null) {
			if (other.dataEntrada != null)
				return false;
		} else if (!dataEntrada.equals(other.dataEntrada))
			return false;
		if (dossie == null) {
			if (other.dossie != null)
				return false;
		} else if (!dossie.equals(other.dossie))
			return false;
		if (fabricante == null) {
			if (other.fabricante != null)
				return false;
		} else if (!fabricante.equals(other.fabricante))
			return false;
		if (ip == null) {
			if (other.ip != null)
				return false;
		} else if (!ip.equals(other.ip))
			return false;
		if (local == null) {
			if (other.local != null)
				return false;
		} else if (!local.equals(other.local))
			return false;
		if (modelo == null) {
			if (other.modelo != null)
				return false;
		} else if (!modelo.equals(other.modelo))
			return false;
		if (motivoApreensao == null) {
			if (other.motivoApreensao != null)
				return false;
		} else if (!motivoApreensao.equals(other.motivoApreensao))
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
		if (pessoa == null) {
			if (other.pessoa != null)
				return false;
		} else if (!pessoa.equals(other.pessoa))
			return false;
		if (placa == null) {
			if (other.placa != null)
				return false;
		} else if (!placa.equals(other.placa))
			return false;
		if (placaOriginal == null) {
			if (other.placaOriginal != null)
				return false;
		} else if (!placaOriginal.equals(other.placaOriginal))
			return false;
		if (processo == null) {
			if (other.processo != null)
				return false;
		} else if (!processo.equals(other.processo))
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
		
		return super.clone();
	}

}