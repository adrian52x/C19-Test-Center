package Covid19project;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordGenerator {
    public static void main(String[] args){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        String encodePassword = encoder.encode("123");
        System.out.println(encodePassword);

    }


}
