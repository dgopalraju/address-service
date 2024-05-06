package com.addressservice.service;

import java.util.List;

import com.addressservice.model.AddressModel;

public interface AddressService {

	void saveAddressDetails(List<AddressModel> addressModel);

	List<AddressModel> getAddressDetails(int empId);

	List<AddressModel> updateAddressDetails(List<AddressModel> addressModel, Integer empId);

	void deleteAddress(int empId);

}
