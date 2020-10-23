package Logical;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Iterator;
import java.util.LinkedList;

import  Utils.Convert;

public class listOfHouse {
	private LinkedList<Home> houses;

	/**
	 * This is the constructor of this class
	 */
	public listOfHouse() {
		super();
		houses = new LinkedList<Home>();
	}

	/**
	 * Accessor method that returns the list of houses
	 * @return list of houses
	 */
	public LinkedList<Home> getHouses() {
		return houses;
	}

	/**
	 * Mutator method that sets the list of houses
	 * @param houses refers to the list of houses
	 */
	public void setHouses(LinkedList<Home> houses) {
		this.houses = houses;
	}

	/**
	 * This method removes a house from the list of houses
	 * @param hom refers to the Home we want to delete
	 * @return true if home can be removed, false if not
	 */
	public boolean removeHome(Home hom){
		// Find the home we want to remove
		Home temp = findHome(hom.getAddress(), hom.getNumber());
		
		if (temp != null){
			houses.remove(temp);
			return true;
		}
		else
			return false;
	}

	/**
	 * This method finds home giving its address and number
	 * @param address refers to the home's address
	 * @param number refers to the home's number
	 * @return the home we wanted to find
	 */
	public Home findHome(String address, int number){
		boolean found = false;
		Home h = null;
		Iterator<Home> it = houses.iterator();
		
		while(it.hasNext() && !found){
			Home home = it.next();
			if(home.getAddress().equalsIgnoreCase(address) && home.getNumber() == number){
				h=home;
				found=true;
			}
		}
		return h;
	}

	/**
	 * This method verifies whether a house already exists 
	 * @param home refers to the home we want to verify its existence
	 * @throws Exception if the house exists
	 */
	public void existHome(Home home) throws Exception{
		boolean found = false;
		Iterator<Home> it = houses.iterator();
		
		while(it.hasNext()&&!found){
			Home home2=it.next();
			if(home2.getAddress().equals(home.getAddress())&&home2.getNumber()==home.getNumber()){
				throw new Exception("This house already exists!");
			}
		}
	}

	/**
	 * This method adds a home into the list of houses
	 * @param home refers to the home to be added
	 * @throws Exception if a problem exist while performing the insertion
	 */
	public void AddHome(Home home) throws Exception{
		try {
			// verify if the home exists
			existHome(home);
			// add home
			houses.add(home);
		} catch (Exception e) {
			throw e;
		}
	}
	
	/**
	 * This method sorts the list of houses from highest to lowest. 
	 * This method uses the quick sort algorithm.
	 * @param houses refers to the list of houses to be sorted
	 * @param start first position to sort
	 * @param end final position to sort
	 * @return sorted list
	 */
	public LinkedList<Home> sortHousesByConsumptions(LinkedList<Home> houses, int start, int end){
		int i = start;
		int j = end;
		
		// get the center of the list
		int center = houses.get((start + end) / 2).totalComsuption();
		
		do{
			// increment j while element on i bigger than center
			while(houses.get(i).totalComsuption() > center){
				i++;
			}
			
			// decrement j while element on j less than center
			while(houses.get(j).totalComsuption() < center){
				j--;
			}
			
			if (i <= j) {
				// Switch elements on the list
				Home aux = houses.get(j);
				houses.set(j, houses.get(i)) ; // set element on position i into position j
				houses.set(i, aux); // set element on position j into position i
				i++; 
				j--;
			}
		} while (i <= j);
		
		// Verify if start position is less than j (middle index)
		if(start < j){
			// Sort list from start to middle
			sortHousesByConsumptions(houses, start, j);
		}
		
		// Verify if i (middle index) is less than end position
		if(i < end){
			// Sort list from middle to end
			sortHousesByConsumptions(houses, i, end);
		}
		
		return houses;
	}

	/**
	 * This method returns the top 10 houses with the highest consumptions.
	 * @return houses the top 10 houses with the highest consumptions
	 */
	public LinkedList<Home> topTenHighestConsumers(){
		// Sort houses from the highest to the lowest consumption
		LinkedList<Home> housesSorted = sortHousesByConsumptions(houses, 0, houses.size()-1);

		Iterator<Home> it = housesSorted.iterator();
		int cant = 10; // limit to top 10 highest consumer houses
		LinkedList<Home> highestConsumers = new LinkedList<Home>();
		
		while(it.hasNext() && cant > 0){
			Home h = it.next();
			highestConsumers.addFirst(h);
			cant --;
		}
		return  highestConsumers;
	}
	
	/**
	 * 
	 * @param b
	 * @param izq
	 * @param der
	 * @return
	 */
	public LinkedList<Home> sortByPersons(LinkedList<Home> b, int izq, int der){
		int i = izq;
		int j = der;
		int centro = b.get((izq+der)/2).getQuantityOfInhabitants();
		
		do {
			while(b.get(i).getQuantityOfInhabitants() > centro){
				i++;
			}
			while(b.get(j).getQuantityOfInhabitants() < centro){
				j--;
			}
			if (i <= j){
				Home aux = b.get(j);
				b.set(j, b.get(i)) ;
				b.set(i, aux);
				i++;
				j--;
			}
		} while (i <= j);

		if(izq < j){
			sortByPersons(b, izq, j);
		}
		
		if(i < der){
			sortByPersons(b, i, der);
		}
		
		return b;
	}

	/**
	 * This method sorts the top 10 highest consumer houses by quantity of persons.
	 * @return the list of top 10 highest consumer houses sorted by quantity of persons.
	 * @throws IOException
	 */
	public LinkedList<Home> sortByQuantityOfInhabitants() throws IOException{
		// Get the top 10 highest consumer houses
		LinkedList<Home> topConsumers = topTenHighestConsumers();
		// Sort the top  10 highest consumer houses by quantity of persons
		LinkedList<Home> topCosumersSorted = sortByPersons(topConsumers, 0, topConsumers.size()-1);
		return topCosumersSorted;
	}

	public LinkedList<Home> alteredHomes(){
		LinkedList<Home> aH = new LinkedList<Home>();
		
		for(Home item: houses){
			if(item.getCD() != null)
				if(item.getCD().getCollectorConsumption() > item.totalComsuption()){
					aH.add(item);
				}
		}
		return aH;
	}

	public void createBinaryFileAlteredConsumptions() throws IOException{
		File fb=new File("Altered Consumptions.dat");
		RandomAccessFile rAf = new RandomAccessFile(fb,"rw");
		LinkedList<Home> altH = alteredHomes();
		rAf.writeInt(altH.size());
		for(Home item: altH){
			rAf.writeInt(Convert.toBytes(item).length);
			rAf.write(Convert.toBytes(item));
		}
		rAf.close();
	}
}
