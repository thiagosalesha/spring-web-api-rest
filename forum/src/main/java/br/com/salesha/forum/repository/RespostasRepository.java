package br.com.salesha.forum.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.salesha.forum.model.Resposta;

public interface RespostasRepository extends JpaRepository<Resposta, Long> {

}
