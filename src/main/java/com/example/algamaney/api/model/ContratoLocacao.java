package com.example.algamaney.api.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "contrato_locacao")
public class ContratoLocacao {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;
	
	@NotNull
	@Column(name = "data_vencimento")
	private LocalDate dataVencimento;
	
	@NotNull
	@Column(name = "valor_aluguel")
	private BigDecimal valorAluguel;
	
	@NotNull
	@Column(name = "duracao_mes")
	private Integer duracaoMeses;
	
	@NotNull
	@Column(name = "considera_pagamento_honorario")
	private Boolean considerarPagamentoHonorario;
	
	@NotNull
	private Boolean situacao;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "codigo_imovel")
	private Imovel imovel;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "codigo_locador")
	private Locador locador;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "codigo_locatario")
	private Locatario locatario;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public LocalDate getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(LocalDate dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public BigDecimal getValorAluguel() {
		return valorAluguel;
	}

	public void setValorAluguel(BigDecimal valorAluguel) {
		this.valorAluguel = valorAluguel;
	}

	public Integer getDuracaoMeses() {
		return duracaoMeses;
	}

	public void setDuracaoMeses(Integer duracaoMeses) {
		this.duracaoMeses = duracaoMeses;
	}

	public Boolean getConsiderarPagamentoHonorario() {
		return considerarPagamentoHonorario;
	}

	public void setConsiderarPagamentoHonorario(Boolean considerarPagamentoHonorario) {
		this.considerarPagamentoHonorario = considerarPagamentoHonorario;
	}

	public Boolean getSituacao() {
		return situacao;
	}

	public void setSituacao(Boolean situacao) {
		this.situacao = situacao;
	}

	public Imovel getImovel() {
		return imovel;
	}

	public void setImovel(Imovel imovel) {
		this.imovel = imovel;
	}

	public Locador getLocador() {
		return locador;
	}

	public void setLocador(Locador locador) {
		this.locador = locador;
	}

	public Locatario getLocatario() {
		return locatario;
	}

	public void setLocatario(Locatario locatario) {
		this.locatario = locatario;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		result = prime * result
				+ ((considerarPagamentoHonorario == null) ? 0 : considerarPagamentoHonorario.hashCode());
		result = prime * result + ((dataVencimento == null) ? 0 : dataVencimento.hashCode());
		result = prime * result + ((duracaoMeses == null) ? 0 : duracaoMeses.hashCode());
		result = prime * result + ((imovel == null) ? 0 : imovel.hashCode());
		result = prime * result + ((locador == null) ? 0 : locador.hashCode());
		result = prime * result + ((locatario == null) ? 0 : locatario.hashCode());
		result = prime * result + ((situacao == null) ? 0 : situacao.hashCode());
		result = prime * result + ((valorAluguel == null) ? 0 : valorAluguel.hashCode());
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
		ContratoLocacao other = (ContratoLocacao) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		if (considerarPagamentoHonorario == null) {
			if (other.considerarPagamentoHonorario != null)
				return false;
		} else if (!considerarPagamentoHonorario.equals(other.considerarPagamentoHonorario))
			return false;
		if (dataVencimento == null) {
			if (other.dataVencimento != null)
				return false;
		} else if (!dataVencimento.equals(other.dataVencimento))
			return false;
		if (duracaoMeses == null) {
			if (other.duracaoMeses != null)
				return false;
		} else if (!duracaoMeses.equals(other.duracaoMeses))
			return false;
		if (imovel == null) {
			if (other.imovel != null)
				return false;
		} else if (!imovel.equals(other.imovel))
			return false;
		if (locador == null) {
			if (other.locador != null)
				return false;
		} else if (!locador.equals(other.locador))
			return false;
		if (locatario == null) {
			if (other.locatario != null)
				return false;
		} else if (!locatario.equals(other.locatario))
			return false;
		if (situacao == null) {
			if (other.situacao != null)
				return false;
		} else if (!situacao.equals(other.situacao))
			return false;
		if (valorAluguel == null) {
			if (other.valorAluguel != null)
				return false;
		} else if (!valorAluguel.equals(other.valorAluguel))
			return false;
		return true;
	}
	
	

}
