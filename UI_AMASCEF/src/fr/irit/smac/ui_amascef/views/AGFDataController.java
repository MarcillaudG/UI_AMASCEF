package fr.irit.smac.ui_amascef.views;

import java.util.List;

import fr.irit.smac.modelui.NeighbourModel;
import fr.irit.smac.modelui.ReceiverModel;
import fr.irit.smac.modelui.SenderModel;
import fr.irit.smac.ui_amascef.main.AGFDataMain;
import fr.irit.smac.ui_amascef.models.AMASCEFModel;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TitledPane;
import javafx.util.Callback;

public class AGFDataController {

	
	private AMASCEFModel model;
	private AGFDataMain mainApp;
	private NeighbourModel neighbourModel;
	
	private ReceiverModel receiverModel;
	
	private SenderModel senderModel;
	
	private String name;
	
	@FXML
	private TitledPane title;
	
	@FXML
	private ListView<String> tableNeighb;
	
	@FXML
	private TableView<SenderModel> tableSents;
	
	@FXML
	private ListView<String> tableReceive;
	


	@FXML
	private TableColumn<SenderModel, String> notFixed;
	
	@FXML
	private TableColumn<SenderModel, Boolean> isReceive;
	
	@FXML
	private TableColumn<SenderModel, String> who;


	private ObservableList<SenderModel> senderData = FXCollections.observableArrayList();
	
	public void setMainApp(AGFDataMain agfdatamain) {
		this.mainApp = agfdatamain;
		
	}

	public void setModel(AMASCEFModel model, String name) {
		this.model = model;
		
		//Neighbour
		NeighbourModel nmodel = this.model.createNeighbourModel(name);
		this.neighbourModel = nmodel;
		this.title.setText(name);
		
		this.tableNeighb.setItems(nmodel.getNames());
		this.tableNeighb.itemsProperty().bind(nmodel.getNames());
		
		//SenderModel
		List<SenderModel> smodels = this.model.createSenderModel(name);
		for(SenderModel s : smodels) {
			senderData.add(s);
		}
		
		this.tableSents.setItems(senderData);
		
		// Receiver Model
		ReceiverModel rmodel = this.model.createReceiverModel(name);
		this.tableReceive.setItems(rmodel.getparams());
		this.tableReceive.itemsProperty().bind(rmodel.getparams());
		
	}
	

	@FXML
	private void initialize() {

		notFixed.setCellValueFactory(cellData -> cellData.getValue().getParam());	
		isReceive.setCellValueFactory(cellData -> cellData.getValue().isReceive().asObject());
		who.setCellValueFactory(cellData -> cellData.getValue().getAllWho());
		
		Callback<TableColumn<SenderModel, Boolean>, TableCell<SenderModel, Boolean>> cellFactory =
		        new Callback<TableColumn<SenderModel, Boolean>, TableCell<SenderModel, Boolean>>() {
		            public TableCell call(TableColumn p) {
		                TableCell cell = new TableCell<SenderModel, Boolean>() {
		                    @Override
		                    public void updateItem(Boolean item, boolean empty) {
		                        super.updateItem(item, empty);
		                        if( item == null) {
		                        	item = false;
		                        }
		                        String color = item ? " green ;" : " red ;";
		                        setStyle("-fx-background-color:"+color);
		                        
		                    }

		                    private String getString() {
		                        return getItem() == null ? "" : getItem().toString();
		                    }
		                };


		                return cell;
		            }
		        };
		  isReceive.setCellFactory(cellFactory);
		  
		   
	}
	
	
}
