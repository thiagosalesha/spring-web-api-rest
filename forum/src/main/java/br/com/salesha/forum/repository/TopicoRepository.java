package br.com.salesha.forum.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.salesha.forum.model.Topico;

public interface TopicoRepository extends JpaRepository<Topico, Long>{

	List<Topico> findByCursoNome(String nomeCurso);
	
	
}
