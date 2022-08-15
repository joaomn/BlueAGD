package br.com.agendaApp.service;

import java.util.Optional;

import org.springframework.http.ResponseEntity;

import br.com.agendaApp.entity.Agenda;
import br.com.agendaApp.entity.ResponseAgenda;

public interface AgendaService {

	ResponseEntity<?> store(Agenda agenda);

	Iterable<Agenda> findAll();

	ResponseEntity<ResponseAgenda> delete(String email);

	ResponseEntity<?> findById(String email, ResponseAgenda ra);

	ResponseEntity<?> update(Agenda agenda);

	Optional<?> findById(String email);

}
