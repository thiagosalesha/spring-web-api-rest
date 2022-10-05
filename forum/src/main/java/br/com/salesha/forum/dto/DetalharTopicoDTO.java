package br.com.salesha.forum.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import br.com.salesha.forum.model.Resposta;
import br.com.salesha.forum.model.Topico;

public class DetalharTopicoDTO {
	
	private Long id;
	private String titulo;
	private String mensagem;
	private LocalDateTime dataCriacao;
	private List<RespostaDTO> resposta;
	
	public DetalharTopicoDTO(Topico topico) {
		this.id = topico.getId();
		this.titulo = topico.getTitulo();
		this.mensagem = topico.getMensagem();
		this.dataCriacao = topico.getDataCriacao();
		this.resposta = new ArrayList<>();
		this.resposta.addAll(topico.getRespostas().stream().map(RespostaDTO::new).collect(Collectors.toList()));
	}

	public Long getId() {
		return id;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getMensagem() {
		return mensagem;
	}

	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}

	public List<RespostaDTO> getResposta() {
		return resposta;
	}
	
	
	
}
