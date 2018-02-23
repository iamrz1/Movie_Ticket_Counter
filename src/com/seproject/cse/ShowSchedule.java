package com.seproject.cse;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

public class ShowSchedule {
	private int size;
	private String[] showList = new String [5];
	private String[] timeSlot = {"09:00 AM - 11:00 AM","12:00 PM - 02:00 PM","03:00 PM - 05:00 PM","06:00 PM - 08:00 PM","09:00 PM - 11:00 PM"};
	
	public ShowSchedule() {
		try {
			readScheduleFromFile();
			runUI();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated constructor stub
	}
	
	
	
	public void runUI(){
		JFrame frame=new JFrame();//creating instance of JFrame
		
		JLabel showLabel = new JLabel ();
		showLabel.setText("Movie Schedule");
		showLabel.setBounds(140,50,150, 33);//x axis, y axis, width, height
		
		String Jtext = "<=========================================>\n";
		for (int i=0;i<size;i++){
			Jtext=Jtext+"\n"+showList[i];
		}
		Jtext=Jtext+"\n\n<=========================================>";
			
		
	
		JTextArea area=new JTextArea();
		area.setText(Jtext);
        area.setBounds(40,100, 300,200);  
        frame.add(area);    
		
		
		frame.add(showLabel);

		frame.setSize(400,500);//400 width and 500 height  
		frame.setLocationRelativeTo(null);//using no layout managers
		frame.setLayout(null);//using no layout managers 
		frame.setVisible(true);//making the frame visible
		
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	
	
	private void readScheduleFromFile() throws IOException{
		//reads from file and stores the data in MovieList array
		
        FileReader scFile=new FileReader("Schedule.txt");   
        Scanner scan = new Scanner (scFile);
        
        String text,movie;
        int code;
        
        for(size=0;scan.hasNext();size++){
        	text=scan.nextLine();
        	code=Integer.parseInt(text.substring(1,2));
        	movie=text.substring(4);
        	//JOptionPane.showMessageDialog(null,code+" "+ movie);
        	showList[size]=timeSlot[code-1]+" - "+movie;
        	//JOptionPane.showMessageDialog(null,size+" "+timeSlotCode[size]);
        	
        }

        scan.close();
        scFile.close();
        //for(int i=0;i<size;i++)
        	//System.out.println(showList[i]);
        
		
	}
	


}
