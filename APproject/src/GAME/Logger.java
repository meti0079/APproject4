package GAME;

import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime; 
public class Logger {
	public static Logger logger;
	DateTimeFormatter dtf= DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"); ;  
	LocalDateTime now=  LocalDateTime.now();
	private Logger() throws IOException  {
		
	}
	
	public static Logger getinsist() throws IOException {
		if(logger==null) {
			logger=new Logger();
		}
		return logger;
	}
	
	
	public void makeLog(String name , String pass) throws IOException{
		
		FileWriter file=new FileWriter(System.getProperty("user.dir")+"\\src\\LOGFILE\\"+name,true);
		file.write("USERNAME : "+name+"\n");
		file.write("CREATED_AT: "+dtf.format(now)+"\n");
		file.write("PASSWORD : "+pass+"\n\n");
		file.close();


	}
	public void log(String name, String wr,String gd) throws IOException {
		FileWriter file=new FileWriter(System.getProperty("user.dir")+"\\src\\LOGFILE\\"+name,true);
		file.write(wr +" "+dtf.format(now)+" " +gd+"\n");
		file.close();
	}

	public void deletAccount(String name) throws IOException {
		File f=new File(System.getProperty("user.dir")+"\\src\\LOGFILE\\"+name);
		Scanner sca=new Scanner(f);  
		String sum="";
		while (sca.hasNextLine()) {
			String line=sca.nextLine();
			sum=sum+line+"\n";
			if(line.startsWith("PASSWORD")) {
				sum=sum+"\n"+"DELETED_AT: "+dtf.format(now)+"\n";
			}
		}
		FileWriter file=new FileWriter(System.getProperty("user.dir")+"\\src\\LOGFILE\\"+name);
		file.write(sum);
		file.close();
	}



}
