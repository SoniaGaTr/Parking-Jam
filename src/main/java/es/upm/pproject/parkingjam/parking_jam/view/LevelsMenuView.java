package es.upm.pproject.parkingjam.parking_jam.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import javax.swing.SwingConstants;


import es.upm.pproject.parkingjam.parking_jam.controller.controller;
import es.upm.pproject.parkingjam.parking_jam.model.Game;

public class LevelsMenuView {

	private JFrame frame;
	private Game game;
	private controller cont;

	
	
	private JButton l1B;
	private JButton l2B;
	private JButton l3B;
	private JButton l4B;

	public LevelsMenuView(JFrame frame, Game game, controller cont) {
		this.frame = frame;
		this.game = game;
		this.cont = cont;
				
		initLMV();
		this.frame.setVisible(true);
	}

	private void initLMV() {

		// Colores:
		Color bg = new Color(180,220,110);
		Color buttonColor = new Color(65,130,4); 
		Color levelBColor = new Color(39,193,245);
		Color lockedLevelBColor = new Color(80,155,180);

		// Dimensiones:
		Dimension buttonSize = new Dimension(40,40);
		Dimension levelBSize = new Dimension(80,80);
		Dimension buttonSize2 = new Dimension(195,40);

		// Iconos:
		ImageIcon closeMIcon = Factory.resizeIcon(new ImageIcon(getClass().getResource("/icons/close.png")),30,30);
		ImageIcon addMIcon = Factory.resizeIcon(new ImageIcon(getClass().getResource("/icons/add.png")),30,30);
		ImageIcon saveMIcon = Factory.resizeIcon(new ImageIcon(getClass().getResource("/icons/save.png")),30,30);
		ImageIcon loadMIcon = Factory.resizeIcon(new ImageIcon(getClass().getResource("/icons/upload.png")),30,30);
		ImageIcon menuIcon = Factory.resizeIcon(new ImageIcon(getClass().getResource("/icons/menu.png")),30,30);
		ImageIcon parkingIcon = new ImageIcon(getClass().getResource("/images/parking3.png"));
		ImageIcon homeMIcon = Factory.resizeIcon(new ImageIcon(getClass().getResource("/icons/home.png")),30,30);

		// Imagenes:
		Image parkingImage= parkingIcon.getImage().getScaledInstance(500, 400, Image.SCALE_SMOOTH);

		// Elementos:
		JPopupMenu menuPanel = new JPopupMenu();
		menuPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		menuPanel.setPopupSize(207,145);
		menuPanel.setBounds(50, 80, 207, 145);

		JButton gamesB= new JButton("games menu");
		Factory.setFormatButton(gamesB, null, buttonSize2, homeMIcon, Color.white, buttonColor, Factory.menuFont, SwingConstants.LEFT);
		gamesB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().removeAll();
				cont.gamesMenuButton();
			}
		});
		JButton saveB = new JButton("save game");
		Factory.setFormatButton(saveB, null, buttonSize2, saveMIcon, Color.white, buttonColor,  Factory.menuFont, SwingConstants.LEFT);
		saveB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cont.saveGame();
			}
		});
		JButton closeB = new JButton("close Parking Jam");
		Factory.setFormatButton(closeB, null, buttonSize2, closeMIcon, Color.white, buttonColor,  Factory.menuFont, SwingConstants.LEFT);
		closeB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});

		menuPanel.add(gamesB);
		menuPanel.add(saveB);
		menuPanel.add(closeB);

		JButton menuB = new JButton();
		Factory.setFormatButton(menuB, null, buttonSize, menuIcon, Color.white, buttonColor, null, null);
		menuB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!menuPanel.isVisible()) {
					menuPanel.show(frame, 50, 87);
					menuPanel.setVisible(true);
				} else {
					menuPanel.setVisible(false);
				}
			}
		});


		JLabel gamePointsL = new JLabel();
		gamePointsL.setFont(Factory.gamePointsFont);
		gamePointsL.setText("Game Points: ");

		JLabel gamePointsVL = new JLabel();
		gamePointsVL.setFont(Factory.gamePointsFont);
		gamePointsVL.setText(game.getGamePoints().toString()); 

		JLabel gameNameL = new JLabel();
		gameNameL.setFont(Factory.titleFont2);
		gameNameL.setText(game.getName()); 

		
		ArrayList<JButton> buttons = new ArrayList<>();
		l1B = new JButton();
		Factory.setFormatButton(l1B, "1", levelBSize, null, null, null,  Factory.levelFont, null);
		Factory.levelsStatus(l1B, game);
		l1B.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				frame.getContentPane().removeAll();
				try {
					if(cont.showLevel(1)==1) {
						Factory.corruptLevel(1, frame, game, cont, buttons, true);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		l2B = new JButton();
		Factory.setFormatButton(l2B, "2", levelBSize, null, null, null,  Factory.levelFont, null);
		Factory.levelsStatus(l2B, game);
		l2B.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				frame.getContentPane().removeAll();
				try {
					if(cont.showLevel(2)==1) {
						Factory.corruptLevel(2, frame, game, cont, buttons, true);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		l3B = new JButton();
		Factory.setFormatButton(l3B, "3", levelBSize, null, null, null,  Factory.levelFont, null);
		Factory.levelsStatus(l3B, game);
		l3B.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				frame.getContentPane().removeAll();
				try {
					if(cont.showLevel(3)==1) {
						Factory.corruptLevel(3, frame, game, cont, buttons, true);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		l4B = new JButton();
		Factory.setFormatButton(l4B, "4", levelBSize, null, null, null,  Factory.levelFont, null);
		Factory.levelsStatus(l4B, game);
		l4B.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				frame.getContentPane().removeAll();
				try {
					if(cont.showLevel(4)==1) {
						Factory.corruptLevel(4, frame, game, cont, buttons, true);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		buttons.add(l1B);
		buttons.add(l2B);
		buttons.add(l3B);
		buttons.add(l4B);

		// Estructura:
		JPanel panel = new JPanel();
		panel.setBackground(bg);
		panel.setLayout(new BorderLayout());

		JPanel panelNorth = new JPanel();
		panelNorth.setBackground(bg);
		panelNorth.setLayout(new BoxLayout(panelNorth, BoxLayout.Y_AXIS));
		JPanel row1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		row1.setBackground(bg);
		row1.add(Box.createHorizontalStrut(30));
		row1.add(menuB);
		row1.add(Box.createHorizontalStrut(390));
		row1.add(gamePointsL);
		row1.add(gamePointsVL);
		row1.add(Box.createHorizontalStrut(30));

		JPanel row2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		row2.setBackground(bg);
		row2.add(gameNameL);

		panelNorth.add(row1);
		panelNorth.add(row2);


		JLayeredPane panelCenter = new JLayeredPane();
		panelCenter.setPreferredSize(new Dimension(500,500));

		JPanel panelBg = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(parkingImage,100,75 , this);
			}
		};
		panelBg.setBackground(bg);
		panelBg.setBounds(0, 10, 700, 500);

		JPanel panelElem = new JPanel();
		panelElem.setBounds(100, 160, 500, 300);
		panelElem.setBackground(new Color(3,3,3,0));
		panelElem.setLayout(new BoxLayout(panelElem, BoxLayout.Y_AXIS));
		JPanel row3 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		row3.setBackground(new Color(3,3,3,0));
		row3.add(l1B);
		row3.add(Box.createHorizontalStrut(60));
		row3.add(l2B);
		JPanel row4 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		row4.setBackground(new Color(3,3,3,0));
		row4.add(l3B);
		row4.add(Box.createHorizontalStrut(60));
		row4.add(l4B);
		panelElem.add(row3);
		panelElem.add(row4);

		panelCenter.add(panelBg, JLayeredPane.DEFAULT_LAYER);
		panelCenter.add(panelElem, JLayeredPane.PALETTE_LAYER);
		panelCenter.revalidate();
		panelCenter.repaint();

		panel.add(panelNorth, BorderLayout.NORTH);
		panel.add(panelCenter, BorderLayout.CENTER);
		frame.add(panel);
	}
/*
	private void levelsStatus(JButton b) {
		int last = game.getUltimoLevelPassed() + 1;
		if(Integer.parseInt(b.getText()) <= last && game.getOkLevel(Integer.parseInt(b.getText())) ) {
			b.setBackground(levelBColor);
			b.setEnabled(true);
		} else {
			b.setBackground(lockedLevelBColor);
			b.setEnabled(false);
		}
	}
	
	private void corruptLevel(Integer n) {
		JPanel errorP = new JPanel();
		errorP.setLayout(new BorderLayout());
		JLabel peText = new JLabel();
		peText.setFont(menuFont);
		peText.setOpaque(true);
		peText.setForeground(Color.red);
		peText.setText("Sorry, this level's file is corrupt, play the next level.");
		errorP.add(peText, BorderLayout.CENTER);
		
		JDialog dialog = new JOptionPane(errorP, JOptionPane.INFORMATION_MESSAGE).createDialog(frame, "Corrupt level");
		dialog.setLocationRelativeTo(frame);
		dialog.setVisible(true);
		Object res = ((JOptionPane) dialog.getContentPane().getComponent(0)).getValue();

		game.setOkLevel(n, false);
		game.setUltimoLevelPassed(n);
		levelsStatus(l1B);
		levelsStatus(l2B);
		levelsStatus(l3B);
		levelsStatus(l4B);
		
		if( res instanceof Integer && (Integer)res == JOptionPane.OK_OPTION) {
			System.out.println("Ok new game button pressed");
			
			frame.getContentPane().removeAll();
			try {
				if(n==4) cont.endGame();
				else if(cont.showLevel(n+1) == 1) corruptLevel(n+1);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} 
	}
	*/
	
}
