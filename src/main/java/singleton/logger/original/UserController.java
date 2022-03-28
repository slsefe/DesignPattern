package singleton.logger.original;

import java.io.IOException;

public class UserController {
    private Logger logger;

    public UserController() throws IOException {
        logger = new Logger();
    }

    public void login(String username, String password) throws IOException {
        logger.log(username + " login!");
    }
}
