package Logical;

import java.io.Serializable;

public class CollectorData implements Serializable {

	private static final long serialVersionUID = 1L;
	private int collectorConsumption;

	public CollectorData(){
	}

	public int getCollectorConsumption() {
		return collectorConsumption;
	}

	public void setCollectorConsumption(int collectorConsumption) {
		this.collectorConsumption = collectorConsumption;
	}

	public double unrealTotalPay(){
		return (double) (collectorConsumption * 0.15);
	}
}
