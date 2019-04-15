package fr.irit.smac.ui_amascef.views;

import fr.irit.smac.ui_amascef.main.Main;
import fr.irit.smac.ui_amascef.models.AMASCEFModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;

public class RunController {

	@FXML
	private TextField nbAgents;
	
	@FXML
	private TextField nbTypes;
	
	@FXML
	private TextField nbVariables;
	
	@FXML
	private TextField nbFixes;
	
	@FXML
	private ToggleButton cross;
	
	@FXML
	private Button run;
	
	private Main mainApp;
	
	private AMASCEFModel model;
	
	public RunController() {
		
	}
	
	@FXML
	private void initialize() {
		nbAgents.setText("10");
		nbTypes.setText("50");
		nbVariables.setText("20");
		nbFixes.setText("100");
		
		model = new AMASCEFModel();
	}
	
	/**
    * Is called by the main application to give a reference back to itself.
    * 
    * @param mainApp
    */
   public void setMainApp(Main mainApp) {
       this.mainApp = mainApp;

       
   }
   
   @FXML
   private void runAmas() {
	   String crossValue = (cross.isSelected()) ? "True" : "False";
	   
	   this.model.run(nbAgents.getText(), nbFixes.getText(), nbVariables.getText(), nbTypes.getText(), crossValue);
   }
   
   @FXML
   private void handleCrossButton() {
	   cross.setText((cross.isSelected()) ? "Yes" : "No");
   }
}
