package br.com.salesha.forum.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.salesha.forum.dto.TopicoDTO;
import br.com.salesha.forum.model.Curso;
import br.com.salesha.forum.model.Topico;
import br.com.salesha.forum.repository.TopicoRepository;

@RestController
public class TopicosController {
	
	@Autowired
	TopicoRepository topicoRepository;
	
	@RequestMapping("topicos")
	public List<TopicoDTO> lista(String nomeCurso) {
		if(nomeCurso == null) {
			List<Topico> topicos = topicoRepository.findAll();
			return TopicoDTO.toTopico(topicos);
		} else {
			List<Topico> topicos = topicoRepository.findByCursoNome(nomeCurso);
			return  TopicoDTO.toTopico(topicos);
		}
	}
}
