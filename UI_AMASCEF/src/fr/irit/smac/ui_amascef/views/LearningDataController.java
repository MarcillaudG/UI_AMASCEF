package fr.irit.smac.ui_amascef.views;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.TreeSet;

import com.sun.javafx.scene.control.skin.TableViewSkin;

import fr.irit.smac.modelui.learning.DataLearningModel;
import fr.irit.smac.modelui.learning.InputLearningModel;
import fr.irit.smac.ui_amascef.main.LearningDataMain;
import fr.irit.smac.ui_amascef.models.LearningDataModel;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.SortType;
import javafx.scene.control.TableView;
import javafx.scene.control.TitledPane;
import javafx.scene.text.Text;

public class LearningDataController {

	private String function;

	@FXML
	private TitledPane title;

	@FXML
	private TableView<InputLearningModel> table;

	@FXML 
	private TableColumn<InputLearningModel,String> inputs;

	@FXML 
	private TableColumn<InputLearningModel,Double> add;

	@FXML 
	private TableColumn<InputLearningModel,Double> minus;

	@FXML
	private TableColumn<InputLearningModel,String> dataApplying;

	@FXML
	private TableView<DataLearningModel> TableData;

	@FXML
	private TableColumn<DataLearningModel,String> datas;

	@FXML
	private TableColumn<DataLearningModel,String> trustValues;

	private LearningDataModel model;

	private LearningDataMain mainApp;

	private static Method columnToFitMethod;

	private Set<String> alldatas;

	private Timer timer; 
	private TimerTask task; 

	private Timer timerSizeColumn; 
	private TimerTask taskSizeColumn; 



	static {
		try {
			columnToFitMethod = TableViewSkin.class.getDeclaredMethod("resizeColumnToFitContent", TableColumn.class, int.class);
			columnToFitMethod.setAccessible(true);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
	}

	private ObservableList<InputLearningModel> observableInput = FXCollections.observableArrayList();
	private ObservableList<DataLearningModel> observableData = FXCollections.observableArrayList();


	public LearningDataController() {
		this.model = new LearningDataModel();
		this.alldatas = new TreeSet<String>();
	}

	public void setMainApp(LearningDataMain mainApp) {
		this.mainApp = mainApp;
	}

	@FXML
	private void initialize() {
		this.title.setText(this.function);

		table.setColumnResizePolicy( TableView.UNCONSTRAINED_RESIZE_POLICY);
		TableData.setColumnResizePolicy( TableView.UNCONSTRAINED_RESIZE_POLICY);
		inputs.setCellValueFactory(cellData -> cellData.getValue().getName());
		add.setCellValueFactory(cellData -> cellData.getValue().getInfluenceAdd().asObject());
		minus.setCellValueFactory(cellData -> cellData.getValue().getInfluenceMinus().asObject());
		dataApplying.setCellValueFactory(cellData -> cellData.getValue().getDataApplying());
		datas.setCellValueFactory(cellData -> cellData.getValue().getName());
		trustValues.setCellValueFactory(cellData -> cellData.getValue().getTrustValuesString());
		datas.setSortType(SortType.DESCENDING);

		this.timer = new Timer();
		this.task = new TimerTask() {
			@Override
			public void run() {
				manageDataAgent();
			}
		};
		timer.schedule(task, 200, 500);

		this.timerSizeColumn = new Timer();
		this.taskSizeColumn = new TimerTask() {
			@Override
			public void run() {
				manageSizeColumn();
			}

		};
		timerSizeColumn.schedule(taskSizeColumn, 200, 1000);
	}

	public void setFunctionName(String function) {
		this.model.run(function);
		this.function = function;
		for(String input : this.model.getInputsName(this.function)) {
			InputLearningModel dlm = new InputLearningModel(input);
			this.observableInput.add(dlm);
			this.model.addListenerToInput(function,input,dlm);
		}

		this.table.setItems(observableInput);
		this.TableData.setItems(observableData);
	}


	public static void autoFitTable(TableView<?> tableView) {
		tableView.getItems().addListener(new ListChangeListener<Object>() {
			@Override
			public void onChanged(Change<?> c) {
				for (Object column : tableView.getColumns()) {
					try {
						columnToFitMethod.invoke(tableView.getSkin(), column, -1);
					} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
	}

	public void manageDataAgent() {
		if(!this.alldatas.containsAll(this.model.getDatasName(function))) {
			for(String s : this.model.getDatasName(this.function)) {
				if(!this.alldatas.contains(s)) {
					this.alldatas.add(s);
					DataLearningModel dlm = new DataLearningModel(s);
					this.observableData.add(dlm);
					this.model.addListenerToData(function, s, dlm);
				}
			}
			this.TableData.setItems(observableData);

		}
	}


	public void manageSizeColumn() {
		TableData.getColumns().stream().forEach( (column) ->
		{
			//Minimal width = columnheader
			Text t = new Text( column.getText() );
			double max = t.getLayoutBounds().getWidth();
			for ( int i = 0; i < table.getItems().size(); i++ )
			{
				//cell must not be empty
				if ( column.getCellData( i ) != null )
				{
					t = new Text( column.getCellData( i ).toString() );
					double calcwidth = t.getLayoutBounds().getWidth();
					//remember new max-width
					if ( calcwidth > max )
					{
						max = calcwidth;
					}
				}
			}
			//set the new max-widht with some extra space
			column.setPrefWidth( max + 10.0d );
		} );
	}

}
