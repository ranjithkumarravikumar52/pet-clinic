package com.ranjithkumarravikumar.petclinic.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@Getter
@Setter
@MappedSuperclass
public class Person extends BaseEntity{
	@Column(name = "first_name")
	private String firstName;
	@Column(name = "last_name") //hibernate snake_case this by default
	private String lastName;
}
