package com.addressservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "address", schema = "address")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {

	@Id
	@GeneratedValue(generator = "ADD_SEQ", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "ADD_SEQ", sequenceName = "ADD_SEQ", initialValue = 500, allocationSize = 1)
	private int addressId;
	private String street;
	private String city;
	private String district;
	private String state;
	private int pincode;
	private int empId;

}
