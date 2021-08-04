package Covid19project.Model.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Address {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int addressId;
    private String streetName;
    private int streetNumber;
    private int postCode;
    private String city;

    public Address() {
    }

    public Address(int addressId, String streetName, int streetNumber, int postCode, String city) {
        this.addressId = addressId;
        this.streetName = streetName;
        this.streetNumber = streetNumber;
        this.postCode = postCode;
        this.city = city;
    }

    @Override
    public String toString() {
        return streetName + ", " + streetNumber + ", " + postCode + ", " + city;
    }



    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public int getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(int streetNumber) {
        this.streetNumber = streetNumber;
    }

    public int getPostCode() {
        return postCode;
    }

    public void setPostCode(int postCode) {
        this.postCode = postCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
