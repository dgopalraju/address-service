package com.addressservice.repositary;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.addressservice.entity.Address;

public interface AddressRepositary extends JpaRepository<Address, Integer> {

	List<Address> findAllByEmpId(int empId);

	Address findByEmpId(Integer empId);

	@Query(value = "SELECT * FROM ADDRESS WHERE EMP_ID = :empId AND ADDRESS_ID = :addressId", nativeQuery = true)
	Address findByEmpIdAndAddressId(@Param("empId") int empId, @Param("addressId") int addressId);

	@Query(value = "SELECT * FROM ADDRESS WHERE EMP_ID = :empId AND ROWNUM = 1", nativeQuery = true)
	Address findByEmpIdDetails(@Param("empId") Integer empId);

	void deleteByEmpId(int empId);

}
