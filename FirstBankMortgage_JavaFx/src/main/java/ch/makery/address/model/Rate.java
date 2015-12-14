package ch.makery.address.model;

import ch.makery.address.view.MortgageController;
import domain.RateDomainModel;

import java.math.BigDecimal;
import java.math.MathContext;

import org.apache.poi.ss.formula.functions.FinanceLib;

import base.RateDAL;

public class Rate extends RateDomainModel {
	
	
	public static double getPayment(int NumberOfPayments, int creditScore, int houseCost)
	{
		//FinalExam
		//	Normally this kind of method would be in a BLL, but alas...
		
		//	Figure out payment based on:
		//	Interest rate
		//	PV
		//	FV (make FV = 0, unless you want a balloon payment
		//	Compounding = True
		//	Number of Payments (passed in)
		
		double interest = RateDAL.getRate(creditScore);
		double pv = FinanceLib.pv((interest/100)/12, NumberOfPayments, -1 * houseCost, 0, true);
		double mortgage_pay = FinanceLib.pmt((double)(interest/100)/12, (double)NumberOfPayments, (double)(-1*houseCost), (double)0, false);
		BigDecimal mortgageBig = new BigDecimal(mortgage_pay, MathContext.DECIMAL32);
		mortgage_pay = mortgageBig.doubleValue();
		return mortgage_pay;
	}
}