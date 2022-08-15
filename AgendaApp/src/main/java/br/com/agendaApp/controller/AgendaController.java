package br.com.agendaApp.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.agendaApp.entity.Agenda;
import br.com.agendaApp.entity.ResponseAgenda;
import br.com.agendaApp.service.AgendaService;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class AgendaController {

	@Autowired
	private AgendaService service;

	@Autowired
	private ResponseAgenda respagd;

	@GetMapping("/listar")
	public Iterable<Agenda> listartodos() {

		return service.findAll();

	}

	@GetMapping("/listar/{id}")
	public ResponseEntity<?> listarPorId(@PathVariable String id) {
		
		Optional<?> objAgenda = this.service.findById(id);
		if (objAgenda.isPresent()) {
			
			

			return ResponseEntity.status(HttpStatus.OK).body(objAgenda.get());
		}
		
		respagd.setMenssage("Usuario não enontrado");
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respagd.getMenssage());
	}

	@PostMapping("/cadastrar")
	public ResponseEntity<?> store(@RequestBody Agenda agenda) {

		Optional<?> objAgenda = this.service.findById(agenda.getEmail());
		if (objAgenda.isPresent()) {
			respagd.setMenssage("Email já cadastrado");

			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respagd.getMenssage());
		}
		return service.store(agenda);
	}

	@PutMapping("/atualizar/{id}")
	public ResponseEntity<?> update(@PathVariable String id, @RequestBody Agenda agenda) {
		Optional<?> objAgenda = this.service.findById(id);

		if (objAgenda.isPresent() && objAgenda.equals(objAgenda)) {
			return service.store(agenda);
		} else {
			respagd.setMenssage("Email nao cadastrado, Por favor revise os dados e tente novamente");

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respagd.getMenssage());

			
		}

	}

	@DeleteMapping("/deletar/{id}")
	public ResponseEntity<?> delete(@PathVariable String id) {
		Optional<?> objAgenda = this.service.findById(id);
		if(objAgenda.isEmpty() || !objAgenda.isPresent()) {
			respagd.setMenssage("Email nao cadastrado, Por favor revise os dados e tente novamente");

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respagd.getMenssage());
			
		}
		
		return service.delete(id);
	}

}
