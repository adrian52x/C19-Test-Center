package Covid19project.Model.Data;


import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Id;
import java.util.Date;

public class Appointment {



    @Id
    private int apptID;
    private int testCenterId;  // foreign key  Appointments -> Teest Center
    private int cprOfUser;
    private String time;
    private String date;



    public Appointment(){

    }

    public Appointment(int apptID, int testCenterId, int cprOfUser, String time, String date) {
        this.apptID = apptID;
        this.testCenterId = testCenterId;
        this.cprOfUser = cprOfUser;
        this.time = time;
        this.date = date;
    }


    public int getApptId() {
        return apptID;
    }

    public void setApptId(int apptID) {
        this.apptID = apptID;
    }

    public int getCprOfUser() {
        return cprOfUser;
    }

    public void setCprOfUser(int cprOfUser) {
        this.cprOfUser = cprOfUser;
    }

    public int getTestCenterId() {
        return testCenterId;
    }

    public void setTestCenterId(int testCenterId) {
        this.testCenterId = testCenterId;
    }


    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "time=" + time +
                ", date=" + date +
                //    ", location=" + location +
                '}';
    }

}
