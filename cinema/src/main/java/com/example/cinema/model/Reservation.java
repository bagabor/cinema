package com.example.cinema.model;

import static javax.persistence.FetchType.EAGER;
import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Reservation")
public class Reservation{

	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Long id;

	@Column(name = "tickets")
	private int numberOfTickets;
	
	@ManyToOne(fetch = EAGER)
	@JoinColumn(name="user_id", nullable=false, referencedColumnName = "id")
    private User user;
    
	@ManyToOne(fetch = EAGER)
    @JoinColumn(name="screening_id", nullable=false, referencedColumnName = "id")
    private Screening screening;	
}
