package com.seproject.cse;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class RemoveMovie {
	
	private String[] movieList = new String[100];
	private int size=0;

	public RemoveMovie() {
		//constructor
		try {	readFromFile();} 
		catch (IOException e) {}
	}
	
	
	public void runUI(){
		JFrame frame=new JFrame();//creating instance of JFrame
		
		@SuppressWarnings({ "rawtypes", "unchecked" })
		JComboBox movieListComboBox = new JComboBox(movieList);;
		movieListComboBox.setBounds(25, 50, 340, 33);

		//Button and its Action
		JButton removeButton=new JButton();//creating instance of JButton  
		removeButton.setText("Remove");
		removeButton.setBounds(150,160,100, 33);//x axis, y axis, width, height		
		ActionListener removeAction= new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent a) {
				@SuppressWarnings("unused")
				String targetMovie = (String) movieListComboBox.getSelectedItem();
				int targetIndex= movieListComboBox.getSelectedIndex();
				//JOptionPane.showMessageDialog(null,size);
				for(int i=targetIndex+1;i<size;i++)
					movieList[i-1]=movieList[i];
				size--;
				JOptionPane.showMessageDialog(null, "Movie Removed.");
				
				try { writeToFile(movieList); }
				catch (Exception e) {}
				RemoveMovie rm=new RemoveMovie(); 
				rm.runUI();
				frame.dispose();
				
			}
		};
		removeButton.addActionListener(removeAction);

		
		//finishing up jframe
		frame.add(movieListComboBox);
		frame.add(removeButton);
		frame.setSize(400,500);//400 width and 500 height  
		frame.setLocationRelativeTo(null);//using no layout managers
		frame.setLayout(null);//using no layout managers 
		frame.setVisible(true);//making the frame visible
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

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
	
	private void writeToFile(String[] newList) throws Exception{
		//Writes a fresh list of movie name in the file
		FileWriter writer1 = new FileWriter("Movies.txt"); 
		PrintWriter writer = new PrintWriter(writer1); 
		for (int i=0;i<size;i++)
		writer.println(newList[i]);
		
		writer.flush();  
		writer.close(); 

	}
}
