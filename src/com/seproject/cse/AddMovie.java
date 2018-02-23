package com.seproject.cse;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class AddMovie {
	private String[] movieList = new String[100];
	private int size=0;
	
	public AddMovie() {
		try {	readFromFile();} 
		catch (IOException e) {}
	}
	
	public void runUI(){
		JFrame frame=new JFrame();//creating instance of JFrame
		
		JLabel movieLabel = new JLabel ();
		movieLabel.setText("Add Movie: ");
		movieLabel.setBounds(170,50,150, 33);//x axis, y axis, width, height
		
		JTextField movieTextInput= new JTextField();//creating instance of JTextField   
		movieTextInput.setText("Movie Name");
		movieTextInput.setBounds(100,100,200,33);//x axis, y axis, width, height
		
		JButton submitButton=new JButton();//creating instance of JButton  
		submitButton.setText("Enter");
		submitButton.setBounds(150,160,100, 33);//x axis, y axis, width, height
	
		ActionListener submitAction= new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent a) {
				String movieName = movieTextInput.getText();
				boolean duplicate= false;
				for (int i=0;i<size;i++){	
					if (movieName.toLowerCase().equals(movieList[i].toLowerCase()))
						duplicate=true;
				}
				if (movieName.contains("Movie Name") || movieName.equals("")){
					JOptionPane.showMessageDialog(null, "Movie Name is not Valid! ");
				}
				
				else if(duplicate){
					JOptionPane.showMessageDialog(null, "Duplicate Exists!");
				}
				else{
					JOptionPane.showMessageDialog(null, "New Movie Added");
					try {
						writeToFile(movieName);
						movieList[size]=movieName;
						size=size+1;
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		};
		
		frame.add(movieLabel); //adding Label for text input area in JFrame
		frame.add(movieTextInput);//adding text Input area in JFrame
		frame.add(submitButton);//adding submit button in JFrame
		submitButton.addActionListener(submitAction);//sumbit action
		frame.setSize(400,500);//400 width and 500 height  
		frame.setLocationRelativeTo(null);//using no layout managers
		frame.setLayout(null);//using no layout managers 
		frame.setVisible(true);//making the frame visible
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
	}
	private void writeToFile(String movieName) throws Exception{
		//Writes a single movie name in the file
		FileWriter writer1 = new FileWriter("Movies.txt",true); 
		PrintWriter writer = new PrintWriter(writer1); 
		
		writer.println(movieName);
		
		writer.flush();  
		writer.close(); 

	}
	private void readFromFile() throws IOException{
		//reads from file and stores the data in MovieList array
		
        FileReader movieFile=new FileReader("Movies.txt");   
        Scanner scan = new Scanner (movieFile);
        
        for(size=0;scan.hasNext();size++){
        	movieList[size]=scan.nextLine();
        	
        }

        scan.close();
        movieFile.close();
        //size is one more than total number of movies
        //for(int j=0;j<size;j++) System.out.println(movieList[j]);
		
	}

}
