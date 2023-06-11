package lr3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class UserServ {

	public static void main(String[] args) {
		SpringApplication.run(UserServ.class, args);
	}

}
