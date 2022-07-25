package client;
import client.core.DuckMenu;

public class DuckUs {
    public static void main(String[] args) {
        DuckOrquestador orquestator = new DuckOrquestador();
        if (Constants.SKIP_MENU) {
            orquestator.starGameWithoutMenu();
        }else{
            DuckMenu.start(orquestator);
        }
    }
}
