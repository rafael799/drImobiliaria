package com.example.algamaney.api.model.repository.filter;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.example.algamaney.api.model.TipoTransacao;

public class HonorarioFilter {
	
	private TipoTransacao tipoTransacao;
	private Integer mes;
	private Integer ano;
	private LocalDate dataLancamento;
	private BigDecimal valor;
	
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

	
}
