package fr.irit.smac.ui_amascef.views;

import java.io.IOException;

import fr.irit.smac.ui_amascef.main.Main;
import fr.irit.smac.ui_amascef.main.ResultMain;
import fr.irit.smac.ui_amascef.models.AMASCEFModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

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


		ResultMain res = new ResultMain(this.model);
		try {
			res.start(this.mainApp.getPrimaryStage());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	private void handleCrossButton() {
		cross.setText((cross.isSelected()) ? "Yes" : "No");
	}
}
