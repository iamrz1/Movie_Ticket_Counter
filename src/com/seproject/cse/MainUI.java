package com.seproject.cse;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class MainUI {

	public MainUI() {
		
	}
	
	public void run(){

		JFrame frame=new JFrame();//creating instance of JFrame
		
		JLabel titleLabel = new JLabel ();
		titleLabel.setText("**CINE HUB**");
		titleLabel.setBounds(170,20,150, 33);//x axis, y axis, width, height
		
		
		JButton sellTicketButton=new JButton();//creating instance of JButton  
		sellTicketButton.setText("Sell Ticket");
		sellTicketButton.setBounds(100,100,200, 30);//x axis, y axis, width, height
		
		JButton refundTicketButton=new JButton();//creating instance of JButton  
		refundTicketButton.setText("Refund Ticket");
		refundTicketButton.setBounds(100,150,200, 30);//x axis, y axis, width, height
		
		JButton addMovieButton=new JButton();//creating instance of JButton  
		addMovieButton.setText("Add Movie");
		addMovieButton.setBounds(100,200,200, 30);//x axis, y axis, width, height
		
		JButton deleteMovieButton=new JButton();//creating instance of JButton  
		deleteMovieButton.setText("Remove Movie");
		deleteMovieButton.setBounds(100,250,200, 30);//x axis, y axis, width, height
		
		JButton showScButton=new JButton();//creating instance of JButton  
		showScButton.setText("Show Schedule");
		showScButton.setBounds(100,300,200, 30);//x axis, y axis, width, height
		
		JButton addShowButton=new JButton();//creating instance of JButton  
		addShowButton.setText("Add Show");
		addShowButton.setBounds(100,350,200, 30);//x axis, y axis, width, height
		
		JButton removeShowButton=new JButton();//creating instance of JButton  
		removeShowButton.setText("Remove Show");
		removeShowButton.setBounds(100,400,200, 30);//x axis, y axis, width, height
		
		ActionListener sellTicketAction= new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent a) {

				SellTickets st=new SellTickets();
			}
		};
		ActionListener refundTicketAction= new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent a) {

				RefundTickets rt=new RefundTickets();
			}
		};
		
		ActionListener addAction= new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent a) {

				AddMovie addMovie = new AddMovie();
				addMovie.runUI();
			}
		};
		ActionListener deleteAction= new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent a) {

				RemoveMovie removeMovie = new RemoveMovie();
				removeMovie.runUI();
			}
		};
		ActionListener showScAction= new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent a) {

				ShowSchedule showSc= new ShowSchedule();
				//addShow.runUI();
			}
		};
		ActionListener addShowAction= new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent a) {

				AddShow addShow= new AddShow();
				//addShow.runUI();
			}
		};
		
		ActionListener removeShowAction= new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent a) {

				RemoveShow removeShow = new RemoveShow();
				removeShow.runUI();
			}
		};
		sellTicketButton.addActionListener(sellTicketAction);
		refundTicketButton.addActionListener(refundTicketAction);
		addMovieButton.addActionListener(addAction);
		deleteMovieButton.addActionListener(deleteAction);
		showScButton.addActionListener(showScAction);
		addShowButton.addActionListener(addShowAction);
		removeShowButton.addActionListener(removeShowAction);
		
		frame.add(titleLabel);
		frame.add(sellTicketButton);//adding sellTicketButton in JFrame
		frame.add(refundTicketButton);//adding sellTicketButton in JFrame
		frame.add(addMovieButton);//adding button in JFrame
		frame.add(deleteMovieButton);//adding button in JFrame
		frame.add(showScButton);//adding button in JFrame
		frame.add(addShowButton);//adding button in JFrame
		frame.add(removeShowButton);//adding button in JFrame
		frame.setSize(400,500);//400 width and 500 height 
		frame.setLocationRelativeTo(null);//using no layout managers
		frame.setLayout(null);//using no layout managers  
		frame.setVisible(true);//making the frame visible
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
	}

}
