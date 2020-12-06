package com.quirko.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import javax.swing.JPanel;
import com.quirko.app.GameController;
import com.quirko.logic.Score;
import com.quirko.logic.SimpleBoard;

public class MenuPanel extends JPanel implements KeyListener {
	/*
	This class is an exit menu
	Includes Settings-Load-Save-ScoreBoard-Exıt functions.
	*/
	private static final long serialVersionUID = 1L;

	private int width, height;

	public static ScoreBoardPanel scoreboard;

	public static SettingsMenuFrame settingFrame;

	public static int [] score = new int[100];

	public static int howManyScore = 0;

	public int [][] newGameMatrix;
	
	public MenuPanel() {
		this.setFocusable(true);
		this.addKeyListener(this);
		this.setBackground(Color.DARK_GRAY);

	}

	private String[] menus = new String[] { "SETTINGS", "LOAD", "SAVE", "SCOREBOARD" , "EXIT GAME" };

	private int focusIndex;

	private int menu_x = 260;

	private int[] menu_ys = new int[] { 80, 110, 140, 170, 200  };

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.setColor(new Color(0x9391d6));
		g.fillRect(0, 0, width, height);

		for (int i = 0; i < menus.length; i++) {
			int x = menu_x;
			int y = menu_ys[i];

			if (i == focusIndex) {
				g.setColor(Color.GREEN);
			} else {
				g.setColor(Color.WHITE);
			}

			g.drawString(menus[i], x, y);
		}
	}

	 
	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode(); 
		 	
		if(keyCode == KeyEvent.VK_UP || keyCode == KeyEvent.VK_W){  // up direction key
			focusIndex = (focusIndex + menus.length - 1) % menus.length;
			this.repaint();
		}
		if(keyCode == KeyEvent.VK_DOWN || keyCode == KeyEvent.VK_S){  // down arrow
			focusIndex = (focusIndex + 1) % menus.length;
			this.repaint();
		}
		if(keyCode == KeyEvent.VK_ENTER){ // Enter Key

			//Exit the program if the Exit key is pressed.
			if (focusIndex == 4)
				GuiController.exit();
				
			//If the ScoreBoard key is pressed, the scores are printed on the screen.
			if(focusIndex == 3){
				try {
					howManyScore = 0;
					loadScore();
				} catch (IOException e1) {
					e1.printStackTrace();
				}

				scoreboard = new ScoreBoardPanel();
			}
					
			//Saves the game if the Save button is pressed.
			if (focusIndex == 2) {
				int[][] currentGame = SimpleBoard.currentGameMatrix;
				Score skor = SimpleBoard.score;
				int score = skor.scoreProperty().get();
				String str = "";

				for (int i = 0; i < currentGame.length; i++) {
					for (int j = 0; j < currentGame[i].length; j++) {
						int index = currentGame[i][j];
						str += String.valueOf(index);
					}
					str += "\n";
				}

				try {
					save(Integer.toString(score), str, "game.txt");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}

			//Load the last saved game if the Load key was pressed.
			if (focusIndex == 1) {
				try {
					load();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				GameController.viewGuiController.refreshGameBackground(newGameMatrix);
			}

			//If the Settings button is pressed, the settings menu appears.
			if(focusIndex == 0){
				settingFrame = new SettingsMenuFrame();
			}
		}

		//If the ESC button is pressed after the menu is opened, the menu closes.
		if(keyCode == KeyEvent.VK_ESCAPE){ // Escape Key
			GuiController.frame.dispose();
		}
	}

	// While saving, it takes the game screen matrix and the score method that writes to the game.txt file.
	public void save(String score, String game, String name) throws IOException {
		BufferedWriter bw = null;
		bw = new BufferedWriter(new FileWriter("game.txt", false));
		bw.write("game:\n");
		bw.write(game);
		bw.write("skor:\n");
		bw.write(score);
		bw.newLine();
		bw.flush();
		bw.close();
	}

	//Method that restores the last saved game screen matrix and score ...
	public void load() throws IOException {
		String line = "";
		BufferedReader br = new BufferedReader(new FileReader("game.txt"));
		newGameMatrix = new int[SimpleBoard.currentGameMatrix.length][SimpleBoard.currentGameMatrix[0].length];
		int row = 0;
		int skor = 0;
		
		while ((line = br.readLine()) != null) {
			if(line.equals("game:")){
				while(true){
					line = br.readLine();
					if(line.equals("skor:")) break;

					for(int col= 0; col < line.length(); col++){
						newGameMatrix[row][col] = line.charAt(col) - '0';
					}
					row++;
					SimpleBoard.currentGameMatrix = newGameMatrix;
				}
			}

			if(line.equals("skor:")){
				line = br.readLine();
				skor = Integer.parseInt(line);
				SimpleBoard.score.reset();
				SimpleBoard.score.add(skor);
			}
		}
		br.close();
	}

	/*
	The score "score.txt" whenever there is a gameover in the GuiController class -
	If the user presses the scoreboard button, the scores will be
	It is read from this file and printed on the screen.
	*/ 
	public void loadScore() throws IOException {
		String line = "";
		BufferedReader br = new BufferedReader(new FileReader("score.txt"));
		while ((line = br.readLine()) != null) {
			if(line.equals("score:")){
				line = br.readLine();
				score[howManyScore] = Integer.parseInt(line);
				howManyScore++;
			}
		}
		 Arrays.sort(score , 0 , howManyScore);

		br.close();
	}
 
	@Override
	public void keyReleased(KeyEvent e) {
	}
 
	@Override
	public void keyTyped(KeyEvent e) {
	}
	
}
