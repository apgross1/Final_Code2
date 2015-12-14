package base;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import domain.RateDomainModel;
import util.HibernateUtil;

public class RateDAL {

	/**
	 * Returns ArrayList of RateDomainModels from the table
	 * @return
	 */
	public static ArrayList<RateDomainModel> getRateDomainModel() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		ArrayList<RateDomainModel> rates = new ArrayList<RateDomainModel>();
		
		try {
			tx = session.beginTransaction();	
			
			List rateDomains = session.createQuery("FROM RateDomainModel").list();
			for (Iterator iterator = rateDomains.iterator(); iterator.hasNext();) {
				RateDomainModel stu = (RateDomainModel) iterator.next();
				rates.add(stu);

			}
			
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}	
		
		return rates;
	}
	
	/**
	 * Returns the rate given a fixed credit score
	 * @param GivenCreditScore
	 * @return
	 */
	public static double getRate(int GivenCreditScore) {
		ArrayList<RateDomainModel> rates = getRateDomainModel();
		double rateGet = 0;
		for (RateDomainModel rDM : rates) {
			if ((GivenCreditScore  >= rDM.getMinCreditScore()) && (GivenCreditScore < rDM.getMinCreditScore() + 50)) {
				rateGet = rDM.getInterestRate();
			}
			else if (GivenCreditScore > 800) {
				rateGet = 3.5;
			}
		}
		return rateGet;
	}

}