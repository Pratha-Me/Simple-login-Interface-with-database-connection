package Controller;

import CRUD.ConnStmt;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import static CRUD.ConnStmt.conn;
import static CRUD.ConnStmt.stmt;
import java.sql.SQLException;

public class LoginController implements Initializable
{
    @FXML private Text pt;
    @FXML private TextField un;
    @FXML private PasswordField pf;
    @FXML private Button myBtn;
    
    final ConnStmt cs = new ConnStmt();

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        try { stmt = conn.createStatement(); }
        catch (SQLException e) { e.printStackTrace(); System.exit(0); }

        myBtn.setOnAction((ActionEvent event) ->{
            final String name = un.getText();
            final String pass = pf.getText();

            try
            {
                if (name != null && pass != null)
                {
                    ResultSet rs = stmt.executeQuery(String.format("Select * from CREDENTIALS " +
                            "Where ADMINISTRATOR == '%1$s' and PASSWORD == '%2$s';", name, pass));

                    if (rs.next())
                    {
                        try
                        {
                            Stage login = (Stage) myBtn.getScene().getWindow();
                            login.close();

                            Stage stage = new Stage();

                            //Make scene occupy full screen
                            stage.setResizable(true); stage.setMaximized(true);
                            Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();

                            AnchorPane ap = FXMLLoader.load(getClass().getResource("AfterLogin.fxml"));
                            Scene apScene = new Scene(ap, screenBounds.getWidth(), screenBounds.getHeight());
                            stage.setTitle("You are Welcomed!");
                            stage.setScene(apScene);
                            stage.show();

                        } catch (IOException ex) {
                            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }

                    else {
                        un.clear(); pf.clear();
                        pt.setText("Wrong Credentials");
                    }
                }
            }
            catch (SQLException e)
            { e.printStackTrace(); }
        });

        un.setOnMouseClicked((MouseEvent event)->{
            un.clear(); pf.clear(); pt.setText(" ");
        });
    }

    @FXML protected void exitBtnAction()
    {
        cs.closeConnection();
        System.exit(0);
    }
}
