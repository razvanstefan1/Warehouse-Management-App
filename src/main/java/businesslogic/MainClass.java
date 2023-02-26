package businesslogic;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.logging.Logger;
import presentation.View;
/**
 * @Author: Stefan Razvan Mihai
 * @Since: Apr 30, 2022
 */
public class MainClass {
    protected static final Logger LOGGER = Logger.getLogger(MainClass.class.getName());

    public static void main(String[] args) throws SQLException, FileNotFoundException {

        View v = new View("abc");


    }

}
