package client.utils;

import client.forms.MessageDialog;

public class ErrorHelper {
    public static void showServerDisconnectError() {
        MessageDialog.showError(
            "Error Critico",
            "Haz perdido conexion con el servidor!"
        );
        System.exit(0);
    }
    
    public static void showServerForceDisconnectError() {
        MessageDialog.showError(
            "Error Critico",
            "El servidor te ha desconectado!"
        );
        System.exit(0);
    }
}

