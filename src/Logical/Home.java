package Logical;

import java.io.Serializable;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;

public class Home implements Serializable {
	private static final long serialVersionUID = 1L;
	private int number;
	private String address;
	private int quantityOfInhabitants;
	private LinkedList<DailyConsumption> dailyConsumptions;
	private CollectorData collectorData;

	public Home(int number, String address, int quantityOfInhabitants, 
			LinkedList<DailyConsumption> dailyConsumptions, CollectorData collectorData) {
		super();
		this.number = number;
		this.address = address;
		this.quantityOfInhabitants = quantityOfInhabitants;
		this.dailyConsumptions = new LinkedList<DailyConsumption>();
		this.collectorData = collectorData;
	}

	public Home() {	
	}

	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public LinkedList<DailyConsumption> getDc() {
		return dailyConsumptions;
	}
	
	public void setDc(LinkedList<DailyConsumption> dc) {
		dailyConsumptions = dc;
	}
	
	public int getNumber() {
		return number;
	}
	
	public void setNumber(int number) {
		this.number = number;
	}
	
	public int getQuantityOfInhabitants() {
		return quantityOfInhabitants;
	}

	public void setQuantityOfInhabitants(int quantityOfInhabitants) {
		this.quantityOfInhabitants = quantityOfInhabitants;
	}

	public void existDC(DailyConsumption DailyCons)throws Exception{
		boolean found = false;
		Iterator<DailyConsumption> it = dailyConsumptions.iterator();
		while(it.hasNext()&&!found) {
			DailyConsumption dcon = it.next();
			if(isEquals(dcon.getDate(), DailyCons.getDate())) {
				found = true;
				throw new Exception("This consumption already exist!!");
			}
		}
	}

	public void AddDailyConsuption(DailyConsumption dc) throws Exception{
		try {
			existDC(dc);
			dailyConsumptions.add(dc);
		} catch (Exception e) {
			throw e;
		}
	}

	public boolean isEquals(Date date , Date date2){
		boolean equals = false;
		
		String s = date.toString();
		String[] fechaString = s.split(" ");
		String mesS = fechaString[1];
		String diaNumeroS = fechaString[2];
		String anhoS = fechaString[5]; 
	
		String s2 = date2.toString();
		String[] fechaString2 = s2.split(" ");
		String mesS2 = fechaString2[1];
		String diaNumeroS2 = fechaString2[2];
		String anhoS2 = fechaString2[5]; 
	
		if(mesS.equalsIgnoreCase(mesS2)&&diaNumeroS.equalsIgnoreCase(diaNumeroS2) && anhoS.equalsIgnoreCase(anhoS2)){
			equals = true;
		}
		return equals;
	}

	public DailyConsumption find(Date date) {
		boolean found=false;
		DailyConsumption dc = null ;
			Iterator<DailyConsumption> it = dailyConsumptions.iterator();
			while(it.hasNext()&&!found){
				DailyConsumption dcon=it.next();
				if (isEquals(date, dcon.getDate())){
					dc = dcon;
					found = true;
				}
			}
			return dc;
	}

	public int totalComsuption(){
		int sum = 0;
		
		for (DailyConsumption item : dailyConsumptions) {
			sum += item.dailyCons();
		}
		return sum;
	}

	public double realTotalPay(){
		return (double) (totalComsuption() * 0.15);
	}

	public CollectorData getCD() {
		return collectorData;
	}

	public void setCD(CollectorData cd) {
		collectorData = cd;
	}

	public double alteredConsumption(){
		return collectorData.unrealTotalPay();
	}
}
