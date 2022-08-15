package br.com.agendaApp.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
@Table(name = "AGD")
public class Agenda {
	
	private String name;
	@Id
	private String email;
	
	private Long phone;

}
