package fr.irit.smac.ui_amascef.views;


import java.util.Map;
import java.util.TreeMap;

import fr.irit.smac.mas.AGFunction;
import fr.irit.smac.modelui.AGFModel;
import fr.irit.smac.ui_amascef.main.ResultMain;
import fr.irit.smac.ui_amascef.models.AMASCEFModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBoxTreeItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.CheckBoxTreeCell;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;


public class ResultController{


	@FXML 
	private TreeView<String> tree;

	@FXML
	private TableView<AGFModel> table;

	@FXML 
	private TableColumn<AGFModel, Integer> parametersMissing;

	@FXML 
	private TableColumn<AGFModel, Integer> parametersSent;

	@FXML
	private TableColumn<AGFModel, String> names;

	private AMASCEFModel model;

	private ResultMain mainApp;

	private ObservableList<AGFModel> agfData = FXCollections.observableArrayList();

	private Map<String,AGFModel> agfNotObserved;

	public ResultController() {
		this.model = new AMASCEFModel();
	}


	@FXML
	private void initialize() {

		this.agfNotObserved = new TreeMap<String,AGFModel>();
		// Initialize the tree
		// Root Item
		CheckBoxTreeItem<String> rootItem = new CheckBoxTreeItem<String>("AGFunction");
		rootItem.setExpanded(true);
		rootItem.setSelected(true);
		/* // Item 1
        CheckBoxTreeItem<String> item1 = new CheckBoxTreeItem<String>("Test11");

        // Item 2
        CheckBoxTreeItem<String> item2 = new CheckBoxTreeItem<String>("Test12");

        // Item 3
        CheckBoxTreeItem<String> item3 = new CheckBoxTreeItem<String>("Test13");*/

		rootItem.selectedProperty().addListener((obs, oldVal, newVal) -> {
			for(TreeItem<String> child : rootItem.getChildren()) {
				((CheckBoxTreeItem<String>)child).setSelected(rootItem.isSelected());
			}
		});
		this.tree.setRoot(rootItem);
		tree.setCellFactory( CheckBoxTreeCell.<String> forTreeView());



		this.tree.setVisible(true);

		names.setCellValueFactory(cellData -> cellData.getValue().getName());	 
		parametersSent.setCellValueFactory(cellData -> cellData.getValue().getNbParamsSent().asObject());	 
		parametersMissing.setCellValueFactory(cellData -> cellData.getValue().getNbParamsReceive().asObject());

	}


	public void setMainApp(ResultMain resultMain) {
		this.mainApp = resultMain;

	}


	public void setModel(AMASCEFModel model2) {
		this.model = model2;
		for(AGFunction agf : this.model.getAmas().getAllAGF()) {
			AGFModel tmp = new AGFModel(agf.getName());
			this.model.addListenerToAGFunction(tmp,agf.getName());
			this.agfData.add(tmp);

		}

		initializeTree();
		initializeTable();
	}


	private void initializeTable() {

		this.table.setItems(this.agfData);

	}


	private void initializeTree() {
		for(AGFunction agf : this.model.getAmas().getAllAGF()) {
			CheckBoxTreeItem<String> item = new CheckBoxTreeItem<String>(agf.getName());
			item.setSelected(true);
			item.selectedProperty().addListener((obs, oldVal, newVal) -> {
				if(item.isSelected()) {
					if(this.agfNotObserved.containsKey(item.getValue())) {
						this.agfData.add(this.agfNotObserved.remove(item.getValue()));
					}
				}
				else {
					int ind = -1;
					for(int i = 0; i < this.agfData.size();i++) {
						if(this.agfData.get(i).getName().get().equals(item.getValue())) {
							ind = i;
						}
					}
					if(ind != -1)
						this.agfNotObserved.put(item.getValue(), this.agfData.remove(ind));
				}
			});
			this.tree.getRoot().getChildren().add(item);
		}
	}

	@FXML
	private void handleClickOnTree() {

	}


}
