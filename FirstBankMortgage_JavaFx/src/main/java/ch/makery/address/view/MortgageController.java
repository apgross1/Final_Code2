package ch.makery.address.view;

import java.net.URL;
import java.util.ResourceBundle;

import ch.makery.address.MainApp;
import ch.makery.address.model.Rate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


public class MortgageController implements Initializable {
	
	@FXML
	private Label income;
	@FXML
	private TextField incomeString;
	private Integer incomeInt;
	
	@FXML
	private Label expense;
	@FXML
	private TextField expenseString;
	private Integer expenseInt;
	
	@FXML
	private Label credScore;
	@FXML
	private TextField credScoreString;

	
	@FXML
	private Label housePrice;
	@FXML
	private TextField housePriceString;

	@FXML
	private ComboBox<String> timePeriod;
	
	private boolean isClicked = false;
	
	@FXML
	private Label mortgagePrice;
	
	@FXML
	public void handleClicked() {
		isClicked = true;
		this.updateValue();
	}

    // Reference to the main application.
    private MainApp mainApp;

    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public MortgageController() {
    
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {

    }

    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        
    }
    
    
    private boolean allFilled() {
    	if ((incomeString.getText().isEmpty() != true) & (expenseString.getText().isEmpty() != true) & (credScoreString.getText().isEmpty() != true) &
    			(housePriceString.getText().isEmpty() != true) & (this.isClicked == true)) {
    		return true;
    	}
    	else
    		return false;
    }
    
    public boolean canPurchase(Double mortgage) {
    	
    	System.out.println(this.getIncomeInt()/12 * .36);
    	System.out.println(((this.getIncomeInt()/12)));
    	System.out.println(((this.getIncomeInt()/12) - this.getExpenseInt())*.18);
    	
    	System.out.println((this.getExpenseInt()));
 
    	System.out.println(((this.getIncomeInt()/12) + this.getExpenseInt())*.18);
    	
    	System.out.println(mortgage <= ((this.getIncomeInt()/12) + this.getExpenseInt())*.18);
    	
    	
    	if ((mortgage <= ((this.getIncomeInt()/12) * .36)) && (mortgage <= (((this.getIncomeInt()/12) - this.getExpenseInt())*.28))) {
    		return true;
    	}
    	else
    		return false;
    }

	@FXML
    public void updateValue() {
    	if (this.allFilled() == true) {
    		this.setExpenseInt(this.getExpenseString());
    		this.setIncomeInt(this.getIncomeString());
    		//NOTE! I changed (income+expenses) to (income-expenses); is this correct?
    		Double mortgage = Rate.getPayment(this.getTimePeriod()*12, this.getCredScoreString(), this.getHousePriceString());
    		if (canPurchase(mortgage) == true) {
    			this.mortgagePrice.setText("$" + mortgage + " a month");
    		}
    		else
    			this.mortgagePrice.setText("House cost too high");
    	}
    	else
    		this.mortgagePrice.setText("Waiting...");
    		
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ObservableList<String> options = 
			    FXCollections.observableArrayList(
			        "15 years",
			        "30 years"
			    );
		this.timePeriod.setItems(options);
	}
	
	
	public Integer getIncomeString() {
		Integer income = new Integer(incomeString.getText());
		
		return income;
	}

	public Integer getExpenseString() {	
		Integer expense = new Integer(expenseString.getText());
		return expense;
	}


	public Integer getCredScoreString() {
		Integer credScore = new Integer(credScoreString.getText());
		return credScore;
	}


	public Integer getHousePriceString() {
		Integer housePrice = new Integer(housePriceString.getText());
		return housePrice;
	}

	
	
	
	public Integer getTimePeriod() {
		Integer yr;
		if (this.timePeriod.getValue().equals("15 years")) {
			yr = new Integer(15);
		}
		
		else
			yr = new Integer(30);
		
		return yr;
	}

	public boolean isClicked() {
		return isClicked;
	}

	public void setClicked(boolean isClicked) {
		this.isClicked = isClicked;
	}

	public void setIncomeString(TextField incomeString) {
		this.incomeString = incomeString;
	}

	public void setExpenseString(TextField expenseString) {
		this.expenseString = expenseString;
	}

	public void setCredScoreString(TextField credScoreString) {
		this.credScoreString = credScoreString;
	}

	public void setHousePriceString(TextField housePriceString) {
		this.housePriceString = housePriceString;
	}

	public void setTimePeriod(ComboBox<String> timePeriod) {
		this.timePeriod = timePeriod;
	}

	public Label getMortgagePrice() {
		return mortgagePrice;
	}

	public void setMortgagePrice(Label mortgagePrice) {
		this.mortgagePrice = mortgagePrice;
	}

	public Integer getIncomeInt() {
		return incomeInt;
	}

	public void setIncomeInt(Integer incomeInt) {
		this.incomeInt = incomeInt;
	}

	public Integer getExpenseInt() {
		return expenseInt;
	}

	public void setExpenseInt(Integer expenseInt) {
		this.expenseInt = expenseInt;
	}

}