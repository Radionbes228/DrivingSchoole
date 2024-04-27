package radion.app.authoshkola.exception;

import java.sql.SQLException;

public class BadConnection extends SQLException {

    public BadConnection(Throwable cause) {
        super("Не удалось подключиться к базе: " + cause);
    }
}
