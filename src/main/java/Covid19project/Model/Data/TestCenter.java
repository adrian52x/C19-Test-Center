package Covid19project.Model.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class TestCenter {




    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int testCenterId;
    private String name;
    private Address address;
    private int center_addressid;


    @Override
    public String toString() {
        return "TestCenter{" +
                "name='" + name + '\'' +
                ", address=" + address +
                '}';
    }

    public int getTestCenterId() {
        return testCenterId;
    }

    public void setTestCenterId(int testCenterId) {
        this.testCenterId = testCenterId;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public int getAddressid() {
        return center_addressid;
    }

    public void setAddressid(int addressid) {
        this.center_addressid = addressid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAdderss(Address adderss) {
        this.address = adderss;
    }


    public int getCenterAddressid() {
        return center_addressid;
    }

    public void setCenterAddressid(int addressid) {
        this.center_addressid = addressid;
    }

    public TestCenter() {
    }

    public TestCenter(String name, Address adderss) {
        this.name = name;
        this.address = adderss;
    }
}
