package Logical;
import java.io.Serializable;
import java.util.Date;

public class DailyConsumption  implements Serializable{
	private static final long serialVersionUID = 1L;
	private Date date;
	private int startRegistration;
	private int finalRegistration;

	public DailyConsumption(Date date, int startRegistration, int finalRegistration) {
		super();
		this.date = date;
		this.startRegistration = startRegistration;
		this.finalRegistration = finalRegistration;
	}
	
	public Date getDate() {
		return date;
	}	
	
	public void setDate(Date date) {
		this.date = date;
	}	
	
	public int getFinalRegistration() {
		return finalRegistration;
	}	
	
	public void setFinalRegistration(int finalRegistration) {
		this.finalRegistration = finalRegistration;
	}	
	
	public int getStartRegistration() {
		return startRegistration;
	}	
	
	public void setStartRegistration(int startRegistration) {
		this.startRegistration = startRegistration;
	}	
	
	public int dailyCons() {
		return finalRegistration - startRegistration;
	}
}
