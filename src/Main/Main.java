package Main;

import CRUD.ConnStmt;
//import Controller.AfterLoginController;
import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/*
 * Commenced on 2nd Sept. 2019
 * Finalised on ..............
 *
 * CODED BY MR. PRAMOSH SHRESTHA
 */

public class Main extends Application {

	public static Stage logStage;

	@Override
	public void start(Stage stage) throws Exception {

		URL resource = Main.class.getResource("/Controller/Login.fxml");

/*		The following syntax can be used instead of above to get the url resource of login.fxml file.
		Note that "/" is not needed for the syntax below.

		URL resource = Main.class.getClassLoader().getResource("Controller/Login.fxml");
*/

/*
 		The syntax at line 41 can be implemented in order to get the url (named as resource at
		line 41) leading to Login.fxml file located inside the Controller package.

		The syntax works by getting the resource of the class in the file AfterLoginController.java
		which is located alongside Login.fxml file inside the same Controller package. However,
		the class must be imported from the Controller package. Please note for the instance,
		the import syntax situated at line 4 is commented.

		Alternately, it is concievable to use LoginController class, once it has been imported,
		instead of class AfterLoginController. Since both of the mentioned class are situated
		inside the same package

 		URL resource = AfterLoginController.class.getResource("Login.fxml");
 */

		GridPane gp = FXMLLoader.load(resource);
		gp.getStylesheets().add("Controller/CSS/login.css");

		Scene scene = new Scene(gp, 400, 300);

		stage.setTitle("LETS GO!");
		stage.setScene(scene);
		logStage = stage;
		stage.show();
	}

	public static void main(String[] args) {
		final ConnStmt cs = new ConnStmt();

		cs.createORconnectToDB();
		launch(args);
		cs.closeConnection();
	}

	/*
    public static void main(String[] args)
    {
        String user, pass;

        final Scanner sc = new Scanner(System.in);
        final ConnStmt cc = new ConnStmt();
        cc.createORconnectToDB();

        new WriteValues().write();

        System.out.println("Enter username and password line by line");
        user = sc.nextLine();
        pass = sc.nextLine();
        new Login().credentials(user, pass);

        cc.closeConnection();
    }
	 */
}
