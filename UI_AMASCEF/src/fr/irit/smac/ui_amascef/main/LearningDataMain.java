package fr.irit.smac.ui_amascef.main;

import java.io.IOException;

import fr.irit.smac.ui_amascef.views.LearningDataController;
import fr.irit.smac.ui_amascef.views.RunController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class LearningDataMain extends Application{

	
	private Stage primaryStage;
	private AnchorPane rootLayout;

	@Override
	public void start(Stage primaryStage) {
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
			loader.setLocation(Main.class.getResource("../views/LearningData.fxml"));
			rootLayout = (AnchorPane) loader.load();
			// Show the scene containing the root layout.
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.show();
			primaryStage.setMaximized(true);
			
			LearningDataController controller = loader.getController();
			controller.setMainApp(this);
			controller.setFunctionName("Function1");
			
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
