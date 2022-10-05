package br.com.salesha.forum.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.salesha.forum.controller.form.AtualizaTopicoForm;
import br.com.salesha.forum.controller.form.TopicoForm;
import br.com.salesha.forum.dto.DetalharTopicoDTO;
import br.com.salesha.forum.dto.RespostaDTO;
import br.com.salesha.forum.dto.TopicoDTO;
import br.com.salesha.forum.model.Resposta;
import br.com.salesha.forum.model.Topico;
import br.com.salesha.forum.repository.CursoRepository;
import br.com.salesha.forum.repository.RespostasRepository;
import br.com.salesha.forum.repository.TopicoRepository;

@RestController
@RequestMapping("topicos")
public class TopicosController {
	
	@Autowired
	TopicoRepository topicoRepository;
	
	@Autowired
	CursoRepository cursoRepository;
	
	@Autowired
	RespostasRepository respostaRepository;
	
	@GetMapping
	public List<TopicoDTO> lista(String nomeCurso) {
		if(nomeCurso == null) {
			List<Topico> topicos = topicoRepository.findAll();
			return TopicoDTO.toTopico(topicos);
		} else {
			List<Topico> topicos = topicoRepository.findByCursoNome(nomeCurso);
			return  TopicoDTO.toTopico(topicos);
		}
	}
	
	@PostMapping
	public ResponseEntity<TopicoDTO> cadastrar(@RequestBody  @Valid TopicoForm form, UriComponentsBuilder uriBuilder) {
		Topico topico = form.toTopico(cursoRepository);
		topicoRepository.save(topico);
		URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
		return ResponseEntity.created(uri).body(new TopicoDTO(topico));
	}
	
	@GetMapping("/detalhar/{id}")
	public DetalharTopicoDTO detalhar(@PathVariable Long id) {
		Topico topico = topicoRepository.getReferenceById(id);		
		return new DetalharTopicoDTO(topico);
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<TopicoDTO> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizaTopicoForm form) {
		Topico atualizar = form.atualizar(id, topicoRepository);
		
		return ResponseEntity.ok(new TopicoDTO(atualizar));
	}
}
