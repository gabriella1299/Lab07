/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.poweroutages;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import it.polito.tdp.poweroutages.model.Model;
import it.polito.tdp.poweroutages.model.Nerc;
import it.polito.tdp.poweroutages.model.Poweroutage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="cmbNerc"
    private ComboBox<Nerc> cmbNerc; // Value injected by FXMLLoader

    @FXML // fx:id="txtYears"
    private TextField txtYears; // Value injected by FXMLLoader

    @FXML // fx:id="txtHours"
    private TextField txtHours; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    private Model model;
    
    @FXML
    void doRun(ActionEvent event) {
    	txtResult.clear();    	
    	
    	try {
    		
    		Nerc selectedNerc = cmbNerc.getSelectionModel().getSelectedItem();
			if (selectedNerc == null) {
				txtResult.setText("Selezionare un NERC (area identifier)!");
				return;
			}
			
    		int ore=Integer.parseInt(txtHours.getText());
    		int anni=Integer.parseInt(txtYears.getText());
    		
    		if(ore<=0 || anni<=0) {
    			txtResult.setText("Inserire un numero maggiore di zero per ore e anni!");
				return;
    		}
    		
        	List<Poweroutage> power=this.model.trovaSequenza(cmbNerc.getValue().getId(), ore, anni);
    		
    		txtResult.clear();
			txtResult.appendText("Tot people affected: " + this.model.sommaClienti(power) + "\n");
			txtResult.appendText("Tot hours of outage: " + this.model.sommaOre(power) + "\n");
    		
        	
    		for(Poweroutage p:power) {
    			//txtResult.appendText(p.toString()+"\n");
    			
    			txtResult.appendText(String.format("%d %s %s %f %d", p.getYear(), p.getStart(),p.getEnd(),p.getHours(),p.getCustomers_affected()));
    			txtResult.appendText("\n");
    			 
    		}
        	
    	}catch(NumberFormatException nfe) {
    		txtResult.setText("Inserire ore e anni in un formato valido!");
    		return;
    	} catch(NullPointerException npe) { 
    		txtResult.setText("Riempire tutti i campi!");
    		return;
    	}
     }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert cmbNerc != null : "fx:id=\"cmbNerc\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtYears != null : "fx:id=\"txtYears\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtHours != null : "fx:id=\"txtHours\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";
        
        // Utilizzare questo font per incolonnare correttamente i dati;
        txtResult.setStyle("-fx-font-family: monospace");
    }
    
    public void setModel(Model model) {
    	this.model = model;
    	cmbNerc.getItems().addAll(model.getNercList());
    }
}
