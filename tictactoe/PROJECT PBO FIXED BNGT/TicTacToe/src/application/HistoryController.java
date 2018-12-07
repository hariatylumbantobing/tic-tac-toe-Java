/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 *
 * @author ADIPSAGALA
 */
public class HistoryController implements Initializable{
    //FXML variables
    @FXML
    TableView TableHistory;
    
    @FXML
    private Button backButton;
    
    @FXML
    //Other variables
    private ObservableList<ObservableList> data;
    
    @Override
    public void initialize(URL url, ResourceBundle rb){
        TableHistory.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        buildData();
    }
    
    public void buildData(){
        data = FXCollections.observableArrayList();
        
        try {
            Class.forName("org.h2.Driver");
            Connection conn = DriverManager.getConnection("jdbc:h2:D:\\KULIAH\\Semester 4\\Pemrograman Berorientasi Objek\\PROYEK\\PROJECT PBO TTT\\resources", "miacrnfrska", "esjagung");
            Statement stmt = conn.createStatement();
            String sql = "SELECT * FROM history";
            ResultSet res = stmt.executeQuery(sql);
            
            for(int i = 0; i < res.getMetaData().getColumnCount(); i++){
                final int j = i;
                TableColumn col = new TableColumn(res.getMetaData().getColumnName(i+1));
                col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>(){
                    @Override
                    public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param){
                        return new SimpleStringProperty(param.getValue().get(j).toString());
                    }
                });
                
                TableHistory.getColumns().addAll(col);
            }
            
            while(res.next()){
                ObservableList<String> row = FXCollections.observableArrayList();
                for(int i = 1; i <= res.getMetaData().getColumnCount(); i++){
                    row.add(res.getString(i));
                }
                
                data.add(row);
            }
            
            TableHistory.setItems(data);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(HistoryController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
     private void handleButtonAction(ActionEvent event) throws IOException{
     Stage stage = null; 
     Parent root = null;
//     if(event.getSource()==backButton){
       stage=(Stage) TableHistory.getScene().getWindow();
       root = FXMLLoader.load(getClass().getResource("ticTacToe.fxml"));
//      }
     //create a new scene with root and set the stage
      Scene scene = new Scene(root);
      stage.setScene(scene);
      stage.show();
    }
}
