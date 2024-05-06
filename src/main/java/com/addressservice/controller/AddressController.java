package com.addressservice.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.addressservice.model.AddressModel;
import com.addressservice.service.AddressService;
import com.employeeservice.exception.ValidationException;

@RestController
@RequestMapping("address")
public class AddressController {

	private AddressService addressService;

	public AddressController(AddressService addressService) {
		super();
		this.addressService = addressService;
	}

	@PostMapping
	ResponseEntity<List<AddressModel>> saveAddressDetails(@RequestBody List<AddressModel> addressModel) {
		Optional.ofNullable(addressModel)
				.orElseThrow(() -> new ValidationException(4001, "AddressModel Object can't be null"));
		addressService.saveAddressDetails(addressModel);
		return new ResponseEntity<>(addressModel, HttpStatus.CREATED);
	}

	@GetMapping("/{empId}")
	ResponseEntity<List<AddressModel>> getAddressDetails(@PathVariable int empId) {
		Optional.ofNullable(empId).orElseThrow(() -> new ValidationException(2003, "EmpId can't be null"));
		List<AddressModel> addressDetails = addressService.getAddressDetails(empId);
		return new ResponseEntity<>(addressDetails, HttpStatus.OK);
	}

	@PutMapping("/{empId}")
	ResponseEntity<List<AddressModel>> updateAddressDetails(@RequestBody List<AddressModel> addressModel,
			@PathVariable Integer empId) {
		if (addressModel == null || empId == null) {
			throw new ValidationException(2003, "AddressModel (or) EmpId can't be null");
		}
		List<AddressModel> updateAddressDetails = addressService.updateAddressDetails(addressModel, empId);
		return new ResponseEntity<>(updateAddressDetails, HttpStatus.OK);
	}

	@DeleteMapping("/{empId}")
	ResponseEntity<String> deleteAddress(@PathVariable int empId) {
		addressService.deleteAddress(empId);
		return new ResponseEntity<>("Address deleted successfully", HttpStatus.ACCEPTED);
	}

}
