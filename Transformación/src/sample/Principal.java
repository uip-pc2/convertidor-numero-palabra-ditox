package sample;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class Principal {
    @FXML
    TextField subtotal;
    @FXML
    Button btn_enviar;

    public void enviar(ActionEvent actionEvent) throws IOException {
        int st = 0;
        String st1 = subtotal.getText();
        if (st1.isEmpty()) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Hay un error en el subtotal, favor validar");
            alerta.setHeaderText("Hay un error, favor validar..");
            alerta.setContentText("El campo no puede estar vacio.");
            alerta.showAndWait();
            subtotal.setText("");
            return;

        } else {
            try {
                st = Integer.parseInt(st1);
                if (st >= 0 && st <= 9999){
                    Stage stage = (Stage) btn_enviar.getScene().getWindow();
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Secundaria.fxml"));
                    Parent root = null;
                    try {
                        root = (Parent) fxmlLoader.load();
                    } catch (Exception e) {
                        Alert alerta = new Alert(Alert.AlertType.ERROR);
                        alerta.setTitle("Error desconocido del programa");
                        alerta.setHeaderText("Hay un error, favor validar.");
                        alerta.setContentText("Contacte al programador.");
                        alerta.showAndWait();
                        Platform.exit();
                    }
                    Secundaria sc = fxmlLoader.<Secundaria>getController();
                    sc.setTotal(st);
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                }else{Alert alerta = new Alert(Alert.AlertType.ERROR);
                    alerta.setTitle("Hay un error en el subtotal, favor validar");
                    alerta.setHeaderText("Hay un error, favor validar.");
                    alerta.setContentText("El número no puede ser mayor que el 9999.");
                    alerta.showAndWait();
                    subtotal.setText("");
                    return;}
            } catch (NumberFormatException e) {
                Alert alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setTitle("Hay un error en el subtotal, favor validar");
                alerta.setHeaderText("Hay un error, favor validar.");
                alerta.setContentText("El numero debe ser entero.");
                alerta.showAndWait();
                subtotal.setText("");
                return;
            }
        }
    }

    public void limpiar(ActionEvent actionEvent) {
        subtotal.setText("");
    }


}
