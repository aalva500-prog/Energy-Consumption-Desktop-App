package Controler;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.util.LinkedList;
import Utils.Convert;
import Logical.Home;
import Logical.listOfHouse;

/**
 * This class represent the controller of the whole application.
 * It is where the writing and reading of files takes place.
 * 
 * @author Aaron Alvarez
 *
 */
public class Consumption {
	private listOfHouse loh;
	private static Consumption c = null;

	public static Consumption getInstance() {
		if (c == null) {
			c = new Consumption();
		}
		return c;		
	}

	public Consumption() {
		super();
		this.loh = new listOfHouse();
	}

	public Consumption(File f, listOfHouse loh) {
		super();
		this.loh = loh;
	}

	public listOfHouse getloh() {
		return loh;
	}

	public void setloh(listOfHouse loh) {
		this.loh = loh;
	}
	
	public void writeFile(String filename) throws IOException, ClassNotFoundException{
		if(filename.endsWith(".dat")){
			writeFile2(filename);
		}
		else{
			writeFile2(filename.concat(".dat"));
		}
	}

	public void writeFile2(String file2) throws IOException, ClassNotFoundException {
		File file = new File(file2);

		RandomAccessFile raf = new RandomAccessFile(file,"rw");
		raf.writeInt(loh.getHouses().size());
		
		for (Home item : loh.getHouses()) {
			raf.writeInt(Convert.toBytes(item).length);
			raf.write(Convert.toBytes(item));
		}
		raf.close();
	}

	public void ReadFile(File f) throws IOException, ClassNotFoundException{
		RandomAccessFile raf=new RandomAccessFile(f,"r");
		LinkedList<Home> home  = new LinkedList<Home>();

		int  housescant = raf.readInt();
		while(housescant > 0 ){
			int temp = raf.readInt();
			byte[] info = new byte[temp];
			raf.read(info);
			Home home2 = (Home) Convert.toObject(info);
			home.addFirst(home2);
			housescant --;
		}
		loh.setHouses(home);
		raf.close();
	}
	
	public static Consumption getC() {
		return c;
	}

	public static void setC(Consumption c) {
		Consumption.c = c;
	}

	public void createTxtFileReport(String dir)throws IOException{
		File file = new File(dir);
		PrintWriter pw = new PrintWriter(file);
		LinkedList<Home> bigger = loh.sortByQuantityOfInhabitants();
		pw.print("House Number");
		pw.print("     ");
		pw.print("Number of Persons");
		pw.print("     ");
		pw.print("Monthly Consumption (kw)");
		pw.println();
		for(Home item:bigger){
			pw.print(item.getNumber());
			pw.print("              ");
			pw.print(item.getQuantityOfInhabitants());
			pw.print("                      ");
			pw.print(item.totalComsuption());
			pw.println();
		}
		pw.close();
	}
}
