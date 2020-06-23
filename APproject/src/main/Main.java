
package main;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

import grapic.MainFrame;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.security.AllPermission;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.SwingUtilities;


public class Main {

	public static void main(String[] args) throws Exception {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					new MainFrame();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});



//CLI s=new CLI();

	}

}
