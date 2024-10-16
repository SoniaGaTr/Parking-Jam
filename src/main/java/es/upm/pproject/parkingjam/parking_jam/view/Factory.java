package es.upm.pproject.parkingjam.parking_jam.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.UIManager;

import es.upm.pproject.parkingjam.parking_jam.controller.Controller;
import es.upm.pproject.parkingjam.parking_jam.model.Game;
import javafx.util.Pair;

public class Factory {
	// Colors:
	static Color levelBColor = new Color(39,193,245);
	static Color lockedLevelBColor = new Color(80,155,180);

	// Fonts:
	private static String menuTextPath = "src/main/resources/fonts/menuText.ttf";
	private static String titleFontPath = "src/main/resources/fonts/titlefont.ttf";
	private static String pointsFontPath = "src/main/resources/fonts/pointsfont.ttf";
	static Font menuFont= genFont(menuTextPath, 16f);
	static Font titleFont = genFont(titleFontPath, 45f);
	static Font buttonFont = genFont(menuTextPath, 30f);
	static Font titleFont2 = genFont(titleFontPath, 35f);
	static Font gamePointsFont = genFont(pointsFontPath, 23f);
	static Font levelFont = genFont(titleFontPath , 27f);
	static Font levelPointsFont =genFont(pointsFontPath, 50f);
	static Font infoFont = genFont(pointsFontPath, 27f);
	static Font levelPointsFont2 = genFont(pointsFontPath, 27f);
	static Font newGameFont = genFont(menuTextPath, 23f);

	//Sounds
	static Clip clip;

	// Private constructor
	private Factory() {

	}

	// Generates a new Font from the file and if it doesn't work, assigns a default one
	private static Font genFont(String path, float size) {
		Font font = null;
		try {
			font = Font.createFont(Font.TRUETYPE_FONT, new File(path)).deriveFont(size);
		} catch (FontFormatException | IOException e1) {
			e1.printStackTrace();
		}
		return font;
	}

	// Assign the characteristics passed as parameters to the 'b' button
	public static void setFormatButton (JButton b, String t, Dimension size, ImageIcon ic, Pair<Color,Color> colors, Font font, Integer sc) {
		Color foreg= colors.getKey();
		Color backg= colors.getValue();

		if(t!=null) b.setText(t);
		if(size!=null) b.setPreferredSize(size);
		if(ic!=null) b.setIcon(ic);
		if(foreg!=null) b.setForeground(foreg);
		if(backg!=null) b.setBackground(backg);
		if(font!=null) b.setFont(font);
		if(sc!=null) b.setHorizontalAlignment(sc);
	}

	// Resize the icon 'icon' according to the parameters 'i' and 'j'
	public static ImageIcon resizeIcon(ImageIcon icon, int i, int j) {
		Image img = icon.getImage();
		Image resizedImg = img.getScaledInstance(i, j, Image.SCALE_SMOOTH);
		return new ImageIcon(resizedImg);
	}

	// Rotate the 'icon' according to the angle passed as a parameter
	public static ImageIcon rotateIcon(ImageIcon icon, double angle) {
		Image img = icon.getImage();
		BufferedImage bufferedImage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = bufferedImage.createGraphics();
		AffineTransform transform = new AffineTransform();
		transform.rotate(Math.toRadians(angle), img.getWidth(null) / 2.0, img.getHeight(null) / 2.0);
		g2d.setTransform(transform);
		g2d.drawImage(img, 0, 0, null);
		g2d.dispose();

		return new ImageIcon(bufferedImage);
	}

	// Called when a level file is incorrect, it shows the error message and if 'checkStatus' is true it updates the view of the level buttons
	public static void corruptLevel(Integer n, JFrame frame, Game game, Controller cont, List<JButton> buttons, Boolean checkStatus) {
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

		if(checkStatus && game!=null) {
			game.setOkLevel(n, false);
			game.setUltimoLevelPassed(n);
			for(JButton b : buttons) {
				levelsStatus(b, game);
			}
		}

		if( res instanceof Integer && (Integer)res == JOptionPane.OK_OPTION) {			
			frame.getContentPane().removeAll();
			try {
				if(n==4) cont.endGame();
				else if(cont.showLevel(n+1) == 1) corruptLevel(n+1, frame, game, cont, buttons, checkStatus);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} 
	}

	// Updates the status (locked/unlocked) of the level buttons
	public static void levelsStatus(JButton b, Game game) {
		int last = game.getUltimoLevelPassed() + 1;
		if(Integer.parseInt(b.getText()) <= last 
				&& game.getOkLevel(Integer.parseInt(b.getText())) ) {
			b.setBackground(levelBColor);
			b.setEnabled(true);
		} else {
			b.setBackground(lockedLevelBColor);
			b.setEnabled(false);
		}
	}

	// Generate a JTextArea with the 'text' message and specific characteristics
	public static JTextArea genTextArea(String text) {
		JTextArea ta = new JTextArea(text);
		ta.setWrapStyleWord(true);
		ta.setLineWrap(true);
		ta.setOpaque(false);
		ta.setEditable(false);
		ta.setFocusable(false);
		ta.setForeground(Color.red);
		ta.setBackground(UIManager.getColor("Label.background"));
		ta.setFont(Factory.menuFont);
		ta.setMargin(new Insets(10, 10, 10, 10));

		return ta;
	}

	// Generate a JPanel with with the background color 'bg' and the layout 'l'
	public static JPanel genPanel(Color bg, LayoutManager l) {
		JPanel p = new JPanel();
		p.setBackground(bg);
		p.setLayout(l);
		return p;
	}

	// Generate a background JPanel with the 'icon' image and the indicated size
	public static JPanel genPanelBg(Color bg, Integer d1, Integer d2, Integer pictd1, Integer pictd2, ImageIcon icon) {
		Image parkingImage= icon.getImage().getScaledInstance(500, Math.max(pictd1, pictd2), Image.SCALE_SMOOTH);		

		JPanel panelBg = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(parkingImage,100, 30, this);
			}
		};
		panelBg.setBackground(bg);
		panelBg.setBounds(0, 0, 700, Math.max(d1, d2) ); 
		return panelBg;
	}

	// Plays the sound 'soundFile'
	public static void playSound(String soundFile) { 
		File soundPath = new File(soundFile); 
		if(clip!=null) {
			clip.close();
			clip.drain();
			clip.stop();
		}
		if (!soundPath.exists()) { 
			return; 
		} 
		try (AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundPath)) { 
			clip = AudioSystem.getClip(); 
			clip.open(audioInputStream); 
			clip.start(); } 
		catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) { 
			e.printStackTrace(); 
		} 
	}


}
