package Covid19project.Repository.AddressRepository;

import Covid19project.Model.Data.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class AddressRepoImpl implements IAddressRepo {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public Address addAddress(Address address) {
        String sql = "INSERT INTO address(streetName, streetNumber, postCode, city) VALUES(?,?,?,?)";
        jdbcTemplate.update(sql, address.getStreetName(), address.getStreetNumber(), address.getPostCode(), address.getCity());
        return null;
    }

    @Override
    public List<Address> fetchAllAddresses() {
        String sql = "SELECT * FROM address";
        RowMapper<Address> rowMapper = new BeanPropertyRowMapper<>(Address.class);
        return jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public Address findAddressById(int addressId) {
        String sql = "SELECT * FROM address WHERE addressId=?";
        RowMapper<Address> rowMapper = new BeanPropertyRowMapper<>(Address.class);
        Address myAddress = jdbcTemplate.queryForObject(sql, rowMapper, addressId);
        return myAddress;
    }

    @Override
    public Address findAddressByPostCode(int postCode) {
        String sql = "SELECT * FROM address WHERE postCode=?";
        RowMapper<Address> rowMapper = new BeanPropertyRowMapper<>(Address.class);
        Address myAddress = jdbcTemplate.queryForObject(sql, rowMapper, postCode);
        return myAddress;
    }

    @Override
    public List<Map<String, Object>> findByPostCode(int postCode) {
        return jdbcTemplate.queryForList("SELECT * FROM address WHERE postCode=?", postCode);
    }

    @Override
    public Address updateAddress(int addressId, Address address) {
        String sql = "UPDATE address SET streetName=?, streetNumber=?, postCode=?, city=? WHERE addressId=?";
        jdbcTemplate.update(sql, address.getStreetName(), address.getStreetNumber(), address.getPostCode(), address.getCity());
        return null;
    }

    @Override
    public Boolean deleteAddress(int addressId) {
        String sql = "DELETE FROM address WHERE addressId=?";
        return jdbcTemplate.update(sql, addressId) >= 0;
    }
}
