package UnitTests;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.math.MathContext;
import java.text.DecimalFormat;

import org.apache.poi.ss.formula.functions.FinanceLib;
import org.junit.Test;

import ch.makery.address.model.Rate;
import ch.makery.address.view.MortgageController;
import ch.qos.logback.core.net.SyslogOutputStream;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class RateBLL_Test {
	
	
	@Test
    public void output_test() {
       double myPmt = FinanceLib.pmt((double)0.04/12, (double)360, (double)300000, (double)0,false);
       BigDecimal mypmT = new BigDecimal(myPmt, MathContext.DECIMAL32);
       System.out.println(mypmT.shortValue());
    }

	
	@Test
	public void mortgageCalc_Test() {
		System.out.println((long)FinanceLib.pmt((double)0.04/12, (double)360, (double)-300000, (double)0,false));
		//Testing getPayment() method in Rate class. Comparing results to direct pmt call
		double mortgage = Rate.getPayment(360, 730, 300000);
		BigDecimal test = new BigDecimal(FinanceLib.pmt((double).04/12, (double)360, (double) -300000, (double) 0, false), MathContext.DECIMAL32);
		double new_Value = test.doubleValue();
		assertTrue(mortgage == new_Value);
	}

	@Test
	public void costTooHigh_Test() {
	//Testing to see if the program will determine if house is too expensive
		MortgageController mortgageCont = new MortgageController();
		Double mortgage = Rate.getPayment(360, 730, 300000);
		
		//(90000/12)*.36 = 2700; ((90000/12)-1300)*.28 = 1736; 
		//both are greater than mortgage payment of 1432.25. This test should assert True
		mortgageCont.setExpenseInt(new Integer(1300));
		mortgageCont.setIncomeInt(new Integer(90000));
		assertTrue(mortgageCont.canPurchase(mortgage) == true);
		
		
		//(24000/12)*.36 = 720;
		//The test has a value less than mortgage payment of 1432.25. This test should
		//pass when comparing it to false
		mortgageCont.setExpenseInt(new Integer(1400));
		mortgageCont.setIncomeInt(new Integer(24000));
		assertTrue(mortgageCont.canPurchase(mortgage) == false);
		
	
		
		
	}
}