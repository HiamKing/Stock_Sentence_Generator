package application.view;

import application.Administrator;

import java.net.URL;
import java.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.List;

import javafx.collections.ObservableList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Label;
import javafx.scene.control.DatePicker;

public class MainController implements Initializable {
	private Administrator admin;
	private String selectedDate;
	
	@FXML
	private DatePicker datePicker;
	
	@FXML
	private TextArea resultTextArea;
	
	@FXML
	private Button addButton, removeButton, removeAllButton, generateButton;
	
	@FXML
	private ListView<String> stockNameTags, otherTags, selectedTagsList;
	
	@FXML
	private Label date, stockName, others, selectedTags, result;
	
	@Override 
	public void initialize(URL location, ResourceBundle resources) {
		admin = new Administrator();
		
		loadData();
		SetMultipleSelectionMode();
	}
	
	private void clearPreviousSelection() {
		stockNameTags.getSelectionModel().clearSelection();
		otherTags.getSelectionModel().clearSelection();
		selectedTagsList.getSelectionModel().clearSelection();
		datePicker.setValue(null);
		
	}
	
	private void SetMultipleSelectionMode() {
		otherTags.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		stockNameTags.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		selectedTagsList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
	}
	
	private void loadData() {
		otherTags.getItems().addAll(admin.getOthersTag());
		stockNameTags.getItems().addAll(admin.getStockNameTag());
	}
	
	@FXML
	private void addTags(ActionEvent e) {
		ObservableList<String> otherTagsList = otherTags.getSelectionModel().getSelectedItems();
		ObservableList<String> stockNameList = stockNameTags.getSelectionModel().getSelectedItems();
		
		if( otherTagsList != null && !otherTagsList.isEmpty()) {
			selectedTagsList.getItems().addAll(otherTagsList);
		}
		
		if( stockNameList != null && !stockNameList.isEmpty()) {
			selectedTagsList.getItems().addAll(stockNameList);
		}
		
		if( datePicker.getValue() != null ) {
			selectedDate = datePicker.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		    selectedTagsList.getItems().add(selectedDate);
		}
		
		clearPreviousSelection();
	}
	
	@FXML
	private void removeTags(ActionEvent e) {
		ObservableList<String> toRemoveList = selectedTagsList.getSelectionModel().getSelectedItems();
		selectedTagsList.getItems().removeAll(toRemoveList);
		
		clearPreviousSelection();
	}
	
	@FXML
	private void removeAllTags(ActionEvent e) {
		selectedTagsList.getItems().clear();
		
		clearPreviousSelection();
	}
	
	@FXML
	private void generateTags(ActionEvent e) {
		resultTextArea.clear();
		
		List<String> sentence  = selectedTagsList.getItems();
		
		if(sentence.size() == 0) resultTextArea.appendText("Người dùng chưa nhập tag nào.");
		else {
			ArrayList<String> resultSentences = admin.searchFor(sentence);
			
			for(int i = 0; i < resultSentences.size(); ++ i) {
				resultTextArea.appendText(resultSentences.get(i) + "\n");
			}
		}
		clearPreviousSelection();
	}
}
