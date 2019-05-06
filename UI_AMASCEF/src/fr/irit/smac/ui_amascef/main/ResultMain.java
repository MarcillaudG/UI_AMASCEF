package fr.irit.smac.ui_amascef.main;

import java.io.IOException;

import fr.irit.smac.ui_amascef.models.AMASCEFModel;
import fr.irit.smac.ui_amascef.views.ResultController;
import fr.irit.smac.ui_amascef.views.RunController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TitledPane;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.CheckBoxTreeCell;
import javafx.stage.Stage;
import javafx.util.Callback;

public class ResultMain extends Application{


	private Stage primaryStage;
	
	private TitledPane rootLayout;
	
	private AMASCEFModel model;
	
	public ResultMain(AMASCEFModel model) {
		this.model = model;
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
			loader.setLocation(Main.class.getResource("../views/Result.fxml"));
			rootLayout = (TitledPane) loader.load();
			
			ResultController controller = loader.getController();
			controller.setMainApp(this);
			
			controller.setModel(this.model);
			

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
