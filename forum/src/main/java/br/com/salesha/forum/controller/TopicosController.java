package br.com.salesha.forum.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.salesha.forum.controller.form.AtualizaTopicoForm;
import br.com.salesha.forum.controller.form.TopicoForm;
import br.com.salesha.forum.dto.DetalharTopicoDTO;
import br.com.salesha.forum.dto.TopicoDTO;
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
	public Page<TopicoDTO> lista(@RequestParam(required = false) String nomeCurso, @RequestParam int pagina, 
			@RequestParam int qtd, @RequestParam String ordenacao) {
		
		Pageable paginacao = PageRequest.of(pagina, qtd, Direction.DESC, ordenacao);
	
		
		if(nomeCurso == null) {
			Page<Topico> topicos = topicoRepository.findAll(paginacao);
			return TopicoDTO.toTopico(topicos);
		} else {
			Page<Topico> topicos = topicoRepository.findByCursoNome(nomeCurso, paginacao);
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
	
	@DeleteMapping("/{id}") 
	public ResponseEntity<?> deletar(@PathVariable Long id){
		topicoRepository.deleteById(id);
		return ResponseEntity.ok("Fim");
	}
}
