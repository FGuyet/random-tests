package controller;

import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class Controller {
	
	@FXML
	private Text touchNb;
	@FXML
	private GridPane gridPane;
	
	int counter = 0; 

	public void increaseCounter(){
		counter++; 
		updateText();		
	}
	
	public void decreaseCounter(){
		counter--; 
		updateText();
	}
	
	private void updateText() {
		touchNb.setText(Integer.toString(counter));
	}
	
}