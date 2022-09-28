package br.com.salesha.forum.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.salesha.forum.model.Curso;

public interface CursoRepository  extends JpaRepository<Curso, Long>{

	Curso findByNome(String nomeCurso);
	
}
