package com.app.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Getter;
import lombok.Setter;


@MappedSuperclass // to tell hib , not to create any tables n other entities will extend from it
@Getter
@Setter
public class BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;	
	@CreationTimestamp // adds creation date / time / time stamp for the entity
	private LocalDate createdOn;
	// updation time stamp
	@UpdateTimestamp // add last updated - date / time / time stamp for the entity
//	@Column(name = "updated_on")
	private LocalDateTime updatedOn;
}
