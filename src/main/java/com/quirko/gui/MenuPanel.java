package com.quirko.gui;

import com.quirko.app.GameController;
import com.quirko.logic.Score;
import com.quirko.logic.SimpleBoard;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Arrays;
import javax.swing.*;

public class MenuPanel extends JFrame{
	 
	private static final long serialVersionUID = 1L;

	JButton settings, load, save, scoreBoard, exit;
	private int width = 400 , height = 510;
	private static int [] scores = new int[100];
	private static int howManyScore = 0;
	private int [][] newGameMatrix;

	public static int[] getScores(){
		return scores;
	}

	public static int getHowManyScore(){
		return howManyScore;
	}

	public MenuPanel(){
		this.setTitle("Escape Menu");
		this.setSize(width, height);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLayout(new FlowLayout());

		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());

		settings = new JButton("SETTINGS");
		panel.add(settings);
		settings.addActionListener(new myActionListener());
		settings.setBackground(Color.MAGENTA);

		load = new JButton("LOAD");
		panel.add(load);
		load.addActionListener(new myActionListener());
		load.setBackground(Color.GREEN);

		save = new JButton("SAVE");
		panel.add(save);
		save.addActionListener(new myActionListener());
		save.setBackground(Color.YELLOW);

		scoreBoard = new JButton("SCORE BOARD");
		panel.add(scoreBoard);
		scoreBoard.addActionListener(new myActionListener());
		scoreBoard.setBackground(Color.RED);

		exit = new JButton("EXIT");
		panel.add(exit);
		exit.addActionListener(new myActionListener());
		exit.setBackground(Color.ORANGE);

		this.getContentPane().add(panel , "CENTER");
		this.setLocationRelativeTo(null);
		this.getContentPane().setBackground(Color.DARK_GRAY);
		this.setFocusable(true);
		this.addKeyListener(new myActionListener());
		this.setVisible(true);
	}

	private class myActionListener implements ActionListener, KeyListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == settings){
				new SettingsMenuPanel();
			}

			else if(e.getSource() == load){
				try {
					load();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				GameController.getViewGuiController().refreshGameBackground(newGameMatrix);
			}

			else if(e.getSource() == save){
				int[][] currentGame = SimpleBoard.getCurrentGameMatrix();
				Score skor = SimpleBoard.getGameScore();
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

			else if(e.getSource() == scoreBoard){
				try {
					howManyScore = 0;
					loadScore();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				new ScoreBoardPanel();
			}

			else if(e.getSource() == exit){
				System.exit(0);
			}
		}

		@Override
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
				MenuPanel.this.dispose();
			}
		}

		@Override
		public void keyTyped(KeyEvent e) {
		}
		@Override
		public void keyReleased(KeyEvent e) {
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

	//Method that load the last saved game screen matrix and score ...
	public void load() throws IOException {
		String line = "";
		BufferedReader br = new BufferedReader(new FileReader("game.txt"));
		newGameMatrix = new int[SimpleBoard.getCurrentGameMatrix().length][SimpleBoard.getCurrentGameMatrix()[0].length];
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
					SimpleBoard.setCurrentGameMatrix(newGameMatrix);
				}
			}

			if(line.equals("skor:")){
				line = br.readLine();
				skor = Integer.parseInt(line);
				SimpleBoard.getGameScore().reset();
				SimpleBoard.getGameScore().add(skor);
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
				scores[howManyScore] = Integer.parseInt(line);
				howManyScore++;
			}
		}
		Arrays.sort(scores , 0 , howManyScore);
		br.close();
	}
}
