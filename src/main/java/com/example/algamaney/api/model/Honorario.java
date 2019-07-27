package com.example.algamaney.api.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "honorario")
public class Honorario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name = "tipo_transacao")
	private TipoTransacao tipoTransacao;
	
	@NotNull
	private Integer mes;
	
	@NotNull
	private Integer ano;
	
	@NotNull
	@Column(name = "data_lancamento")
	private LocalDate dataLancamento;
	
	@NotNull
	private BigDecimal valor;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "codigo_tipo_honorario")
	private TipoHonorario tipoHonorario;
	
	@ManyToOne
	@JoinColumn(name = "codigo_contrato_locacao")
	private ContratoLocacao contratoLocacao;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public TipoTransacao getTipoTransacao() {
		return tipoTransacao;
	}

	public void setTipoTransacao(TipoTransacao tipoTransacao) {
		this.tipoTransacao = tipoTransacao;
	}

	public Integer getMes() {
		return mes;
	}

	public void setMes(Integer mes) {
		this.mes = mes;
	}

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	public LocalDate getDataLancamento() {
		return dataLancamento;
	}

	public void setDataLancamento(LocalDate dataLancamento) {
		this.dataLancamento = dataLancamento;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public TipoHonorario getTipoHonorario() {
		return tipoHonorario;
	}

	public void setTipoHonorario(TipoHonorario tipoHonorario) {
		this.tipoHonorario = tipoHonorario;
	}
	
	

	public ContratoLocacao getContratoLocacao() {
		return contratoLocacao;
	}

	public void setContratoLocacao(ContratoLocacao contratoLocacao) {
		this.contratoLocacao = contratoLocacao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ano == null) ? 0 : ano.hashCode());
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		result = prime * result + ((contratoLocacao == null) ? 0 : contratoLocacao.hashCode());
		result = prime * result + ((dataLancamento == null) ? 0 : dataLancamento.hashCode());
		result = prime * result + ((mes == null) ? 0 : mes.hashCode());
		result = prime * result + ((tipoHonorario == null) ? 0 : tipoHonorario.hashCode());
		result = prime * result + ((tipoTransacao == null) ? 0 : tipoTransacao.hashCode());
		result = prime * result + ((valor == null) ? 0 : valor.hashCode());
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
		Honorario other = (Honorario) obj;
		if (ano == null) {
			if (other.ano != null)
				return false;
		} else if (!ano.equals(other.ano))
			return false;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		if (contratoLocacao == null) {
			if (other.contratoLocacao != null)
				return false;
		} else if (!contratoLocacao.equals(other.contratoLocacao))
			return false;
		if (dataLancamento == null) {
			if (other.dataLancamento != null)
				return false;
		} else if (!dataLancamento.equals(other.dataLancamento))
			return false;
		if (mes == null) {
			if (other.mes != null)
				return false;
		} else if (!mes.equals(other.mes))
			return false;
		if (tipoHonorario == null) {
			if (other.tipoHonorario != null)
				return false;
		} else if (!tipoHonorario.equals(other.tipoHonorario))
			return false;
		if (tipoTransacao != other.tipoTransacao)
			return false;
		if (valor == null) {
			if (other.valor != null)
				return false;
		} else if (!valor.equals(other.valor))
			return false;
		return true;
	}

	
	
}
