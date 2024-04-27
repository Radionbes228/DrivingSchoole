package radion.app.authoshkola;


import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import radion.app.authoshkola.exception.BadConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Component
@PropertySource("classpath:/application.properties")
public class ConnectionJDBC {
    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;

    @SneakyThrows
    public Connection connect(){
        try {
            return DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            throw new BadConnection(e);
        }
    }
}
