package Covid19project.Model.Data;





import javax.persistence.Id;
import java.time.LocalDate;
import java.util.List;

public class User {

    @Id
    private int cpr;
    private String firstName;
    private String surname;
    private String email;
    private String password;
    private String dateOfBirth;
    private String status;
    private int addressid; // foreign key in table User




    public User(int cpr, String firstName, String surname, String email, String password, String dateOfBirth, int addressid) {
        this.cpr = cpr;
        this.firstName = firstName;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        this.addressid = addressid;
    }

    public User(){
    }

    @Override
    public String toString() {
       /* return "User{" +
                "firstName='" + firstName + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", CPR=" + cpr +
                '}';*/
        return firstName + ", " + surname;
    }

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }
    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public int getCPR() {
        return cpr;
    }
    public void setCPR(int CPR) {
        this.cpr = CPR;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }


    public int getAddressId() {
        return addressid;
    }
    public void setAddressId(int addressid) {
        this.addressid = addressid;
    }

    private List<Role> roles;

}
