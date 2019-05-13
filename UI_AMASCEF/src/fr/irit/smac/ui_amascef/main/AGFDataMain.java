package fr.irit.smac.ui_amascef.main;

import java.io.IOException;

import fr.irit.smac.modelui.AGFDataModel;
import fr.irit.smac.ui_amascef.models.AMASCEFModel;
import fr.irit.smac.ui_amascef.views.AGFDataController;
import fr.irit.smac.ui_amascef.views.ResultController;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class AGFDataMain extends Application{
	private Stage primaryStage;

	private AnchorPane rootLayout;

	private AMASCEFModel model;
	
	private String name;

	public AGFDataMain(AMASCEFModel model2, String name) {
		this.model = model2;
		this.name = name;
	}


	@Override
	public void start(Stage primaryStage) throws Exception {

		this.primaryStage = primaryStage;
		initRootLayout();
	}


	/**
	 * Initializes the root layout.
	 */
	public void initRootLayout() {
		try {
			// Load root layout from fxml file.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("../views/AGFData.fxml"));
			rootLayout = (AnchorPane) loader.load();

			AGFDataController controller = loader.getController();
			controller.setMainApp(this);

			controller.setModel(this.model,this.name);

			// Show the scene containing the root layout.
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.show();



		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	/**
	 * Returns the main stage.
	 * @return
	 */
	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public static void main(String[] args) {
		launch(args);
	}
}
