package server;

import java.lang.reflect.Constructor;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Scanner;

import server.playModel.Mapper;

public class ClassLouder extends Thread{

	Scanner scaner;
	ArrayList<Class> classes=new ArrayList<>();
	public ClassLouder() {
		scaner=new Scanner(System.in);



	}

	public Mapper getnewMapper(String state, User user1, User user2) {
		try {
			for (Class class1 : classes) {
				if (state.contains(class1.getName())) {
					Constructor constructor = class1.getConstructor(new Class[] {String.class,User.class,User.class});
					return(Mapper)constructor.newInstance("deckreader",user1,user2);		
				}
			}
			return new Mapper("deckreader", user1, user2);		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public void run() {
		while (true) {
			try {
				System.out.println(" enter jar file address :");
				String address=scaner.nextLine();
				System.out.println(" enter .class name :");
				String name=scaner.nextLine();
				URLClassLoader classLoader=new URLClassLoader(new URL[] {new URL(address)});
				Class object=classLoader.loadClass(name);
				classes.add(object);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
