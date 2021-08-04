package Covid19project.Service.AddressService;

import Covid19project.Repository.AddressRepository.IAddressRepo;
import Covid19project.Model.Data.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class AddressServiceImpl implements IAddressService{
    @Autowired
    IAddressRepo iAddressRepo;

    //CREATE
    @Override
    public Address addAddress(Address address) {
        return iAddressRepo.addAddress(address);
    }

    //READ
    @Override
    public List<Address> fetchAllAddresses() {
        return iAddressRepo.fetchAllAddresses();
    }

    @Override
    public Address findAddressById(int addressId) { return iAddressRepo.findAddressById(addressId); }

    @Override
    public Address findAddressByPostCode(int postCode) { return iAddressRepo.findAddressByPostCode(postCode); }

    @Override
    public List<Map<String, Object>> findByPostCode(int postCode) {
        return iAddressRepo.findByPostCode(postCode);
    }


    //UPDATE
    @Override
    public Address updateAddress(int addressId, Address address) {
        return iAddressRepo.updateAddress(addressId,address);
    }

    //DELETE
    @Override
    public Boolean deleteAddress(int addressId) {
        return iAddressRepo.deleteAddress(addressId);
    }
}
