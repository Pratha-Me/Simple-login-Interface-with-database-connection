package Controller;

import Main.Main;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class AfterLoginController
{
    @FXML private AnchorPane ap;

    @FXML private void backBtn(){
        Stage UI = (Stage) ap.getScene().getWindow();
        UI.close();

        Stage stage = Main.logStage;
        stage.show();
    }
}
