package br.com.agendaApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import br.com.agendaApp.entity.Agenda;

@Repository
public interface AgendaRepository extends  JpaRepository<Agenda, String> {

	
	
	

}
