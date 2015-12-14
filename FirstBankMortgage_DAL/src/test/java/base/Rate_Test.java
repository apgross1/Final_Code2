package base;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import domain.RateDomainModel;

/**
 * @author Andrew
 *
 */
public class Rate_Test {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	
	
	/**
	 * Testing to see if the application reads in the table
	 */
	@Test
	public void ratePull_Test() {
		ArrayList<RateDomainModel> rates = RateDAL.getRateDomainModel();
		assertTrue(rates.size() > 0);
	}
	
	/**
	 * Testing getRate()
	 */
	@Test
	public void rateGet_Test() {
		//If the credit score is 749 the rate should still be 4
		double rate = RateDAL.getRate(749);
		assertTrue(rate == 4.0);
		
		//But if we make add 1 to the credit score they enter a new interest bracket
		double rate_1 = RateDAL.getRate(750);
		assertTrue(rate_1 == 3.75);
		
		//If the credit score is over 800 the rate should hold at 3.5
		double rate_high = RateDAL.getRate(1200);
		assertTrue(rate_high == 3.5);
	}

}