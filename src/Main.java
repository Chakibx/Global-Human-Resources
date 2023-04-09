package src;
import src.Gui.Gui;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
            try {
                Gui gui = new Gui();
            }catch (Exception e){
                System.out.println("Errors: "+e.getMessage());
            }
    }
}

