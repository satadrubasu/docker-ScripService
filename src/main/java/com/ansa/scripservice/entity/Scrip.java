package com.ansa.scripservice.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor // loombok to not write code for all args constructor
@NoArgsConstructor // loombok to not write code for all args constructor
@Data  // loombok creates toString, equals, hashcode , getters and setters
@Entity
@Table(name="scrip")
public class Scrip {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String scripId;
	private String scripName;
	private int faceValue;
}
