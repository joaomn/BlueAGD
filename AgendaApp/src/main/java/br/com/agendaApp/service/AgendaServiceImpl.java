package br.com.agendaApp.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.agendaApp.entity.Agenda;
import br.com.agendaApp.entity.ResponseAgenda;
import br.com.agendaApp.repository.AgendaRepository;

@Service
public class AgendaServiceImpl implements AgendaService {

	@Autowired
	private AgendaRepository ar;

	@Autowired
	private ResponseAgenda ra;

	@Override
	public ResponseEntity<?> store(Agenda agenda) {

		if (agenda.getName().equals("") || agenda.getName().equals(null)) {
			ra.setMenssage("Nome não informado");
			return new ResponseEntity<ResponseAgenda>(ra, HttpStatus.BAD_REQUEST);
		}
		if (agenda.getEmail().equals("") || agenda.getEmail().equals(null)) {
			ra.setMenssage("Email não informado");
			return new ResponseEntity<ResponseAgenda>(ra, HttpStatus.BAD_REQUEST);
		}
		if (agenda.getPhone().equals("") || agenda.getPhone().equals(null)) {
			ra.setMenssage("Telefone não informado");
			return new ResponseEntity<ResponseAgenda>(ra, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Agenda>(ar.save(agenda), HttpStatus.CREATED);
	}

	@Override
	public Iterable<Agenda> findAll() {

		return ar.findAll();
	}

	

	@Override
	public ResponseEntity<ResponseAgenda> delete(String email) {
		
		ar.deleteById(email);
		
		ra.setMenssage("Usuario Remevido com Sucesso");
		return new ResponseEntity<ResponseAgenda>(ra, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> update(Agenda agenda) {

		if (agenda.getName().equals("") || agenda.getName().equals(null)) {
			ra.setMenssage("Nome não informado");
			return new ResponseEntity<ResponseAgenda>(ra, HttpStatus.BAD_REQUEST);
		}
		if (agenda.getEmail().equals("") || agenda.getEmail().equals(null)) {
			ra.setMenssage("Email não informado");
			return new ResponseEntity<ResponseAgenda>(ra, HttpStatus.BAD_REQUEST);
		}
		if (agenda.getPhone().equals("") || agenda.getPhone().equals(null)) {
			ra.setMenssage("Telefone não informado");
			return new ResponseEntity<ResponseAgenda>(ra, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Agenda>(ar.save(agenda), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> findById(String email, ResponseAgenda ra) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<?> findById(String email) {
		// TODO Auto-generated method stub
		return ar.findById(email);
	}

	

	
	

}
