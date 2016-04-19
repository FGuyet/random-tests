package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

	@Override
	public void start(Stage arg0) throws Exception {
	
//		System.out.println(getClass().getResource("/View/View.fxml"));
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/View.fxml"));
		Parent root = fxmlLoader.load();
		Scene scene = new Scene(root);
		arg0.setTitle("Touch counter");
		arg0.setScene(scene);
		arg0.show();
	}
	
	public static void main(String[] args){
		launch(args);
	}

}

