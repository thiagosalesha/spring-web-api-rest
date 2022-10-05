package br.com.salesha.forum.dto;

import java.time.LocalDateTime;

import br.com.salesha.forum.model.Resposta;

public class RespostaDTO {
	
	private String nomeAutor;
	private Long id;
	private LocalDateTime dataCriacao;
	private String mensagem;
	
	public RespostaDTO(Resposta resposta) {
		this.nomeAutor = resposta.getAutor().getNome();
		this.id = resposta.getId();
		this.dataCriacao = resposta.getDataCriacao();
		this.mensagem = resposta.getMensagem();
	}

	public String getNomeAutor() {
		return nomeAutor;
	}

	public Long getId() {
		return id;
	}

	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}

	public String getMensagem() {
		return mensagem;
	}
	
	
	
	
}
