package Covid19project.Service.AddressService;

import Covid19project.Model.Data.Address;

import java.util.List;
import java.util.Map;

public interface IAddressService {
    //Create
    Address addAddress(Address address);

    //READ
    List<Address> fetchAllAddresses();
    Address findAddressById(int addressId);
    Address findAddressByPostCode(int postCode);
    List<Map<String,Object>> findByPostCode(int postCode);


    //Update
    Address updateAddress(int addressId, Address address);


    //Delete
    Boolean deleteAddress(int addressId);
}
