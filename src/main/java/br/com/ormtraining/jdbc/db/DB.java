package br.com.ormtraining.jdbc.db;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DB {

    private static Connection conn;


    private static Properties loadProperties() {
        try {
            FileInputStream fs = new FileInputStream("src/main/resources/db.properties");
            Properties props = new Properties();
            props.load(fs);
            return props;
        } catch (IOException e) {
            throw new DbException(e.getMessage());
        }
    }

    public static Connection getConnection() {
        if (conn == null) {
            try {
                Properties props = loadProperties();
                String url = props.getProperty("spring.datasource.url");
                conn = DriverManager.getConnection(url, props);
                final InputStream fis = DB.class.getResourceAsStream("resources/db.properties");
                {
                    try {
                        props.load(fis);

                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    try {
                        conn = DriverManager.getConnection(String.valueOf(fis), props);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
                return conn;
            } catch (RuntimeException e) {
                throw new RuntimeException(e);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return conn;
    }
    public static void closeConnection() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
        }
    }
}