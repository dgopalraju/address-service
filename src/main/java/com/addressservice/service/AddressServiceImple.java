package com.addressservice.service;

import java.util.Arrays;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.addressservice.entity.Address;
import com.addressservice.model.AddressModel;
import com.addressservice.repositary.AddressRepositary;
import com.employeeservice.exception.ValidationException;

import jakarta.transaction.Transactional;

@Service
public class AddressServiceImple implements AddressService {

	private AddressRepositary addressRepositary;

	private ModelMapper modelMapper;

	public AddressServiceImple(AddressRepositary addressRepositary, ModelMapper modelMapper) {
		super();
		this.addressRepositary = addressRepositary;
		this.modelMapper = modelMapper;
	}

	@Override
	public void saveAddressDetails(List<AddressModel> addressModel) {
		List<Address> address = Arrays.asList(modelMapper.map(addressModel, Address[].class));
		addressRepositary.saveAll(address);
	}

	@Override
	public List<AddressModel> getAddressDetails(int empId) {
		List<Address> addressDetails = addressRepositary.findAllByEmpId(empId);
		if (addressDetails.isEmpty()) {
			throw new ValidationException(2006, "For EmpId the Address can't be found");
		}
		List<AddressModel> addressModel = Arrays.asList(modelMapper.map(addressDetails, AddressModel[].class));
		return addressModel;
	}

	@Override
	public List<AddressModel> updateAddressDetails(List<AddressModel> addressModel, Integer empId) {
		Address addressDetail = addressRepositary.findByEmpIdDetails(empId);
		if (addressDetail == null) {
			throw new ValidationException(2006, "For EmpId the Address can't be found");
		} else {
			addressModel.forEach(address -> {
				Address findByEmpIdAndAddressId = addressRepositary.findByEmpIdAndAddressId(empId,
						address.getAddressId());
				if (findByEmpIdAndAddressId == null) {
					throw new ValidationException(4009, "EmployeeId and Address ID not found");
				} else {
					address.setEmpId(empId);
					BeanUtils.copyProperties(address, findByEmpIdAndAddressId);
					addressRepositary.save(findByEmpIdAndAddressId);
				}
			});
		}
		return addressModel;
	}

	@Override
	@Transactional
	public void deleteAddress(int empId) {
		addressRepositary.deleteByEmpId(empId);
	}

}
