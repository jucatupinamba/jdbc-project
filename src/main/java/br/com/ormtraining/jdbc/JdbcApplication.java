package br.com.ormtraining.jdbc;

import br.com.ormtraining.jdbc.db.DB;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;

@SpringBootApplication
public class JdbcApplication {

	public static void main(String[] args) {

		Connection conn = DB.getConnection();
		SpringApplication.run(JdbcApplication.class, args);
	}

}
