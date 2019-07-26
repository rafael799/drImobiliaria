package com.example.algamaney.api.model.repository.filter;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ContratoLocacaoFilter {
	
	private LocalDate dataVencimento;
	private BigDecimal valorAluguel;
	private Integer duracaoMeses;
	private Boolean considerarPagamentoHonorario;
	private Boolean situacao;
	
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
	
	
	

}
