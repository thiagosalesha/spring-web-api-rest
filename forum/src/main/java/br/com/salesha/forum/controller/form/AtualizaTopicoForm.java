package br.com.salesha.forum.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.salesha.forum.model.Topico;
import br.com.salesha.forum.repository.TopicoRepository;

public class AtualizaTopicoForm {
	
	@NotNull @NotEmpty @Length(min = 5)
	private String titulo;
	@NotNull @NotEmpty @Length(min = 5)
	private String mensagem;
	
	
	public AtualizaTopicoForm(String titulo, String mensagem) {
		this.titulo = titulo;
		this.mensagem = mensagem;
	}
	
	
	public AtualizaTopicoForm() {
	}


	public String getTitulo() {
		return titulo;
	}
	public String getMensagem() {
		return mensagem;
	}


	public Topico atualizar(Long id, TopicoRepository topicoRepository) {
		Topico topico = topicoRepository.getReferenceById(id);
		topico.setTitulo(this.titulo);
		topico.setMensagem(this.mensagem);
		/*Não há necessidade de utilizar metodo update, tendo em vista que a instância esta sendo 
		gerenciada com JPA, após concluir a transação o próprio Spring faz o commit/update */
		return topico;
	}


}
