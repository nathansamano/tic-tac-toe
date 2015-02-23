package edu.saintjoe.cs.natedogg34.tictacstartnds;
//Nathan Samano
import com.google.devtools.simple.runtime.components.android.Button;
import com.google.devtools.simple.runtime.components.Component;
import com.google.devtools.simple.runtime.components.HandlesEventDispatching;
import com.google.devtools.simple.runtime.components.android.Form;
import com.google.devtools.simple.runtime.components.android.HorizontalArrangement;
import com.google.devtools.simple.runtime.components.android.Label;
import com.google.devtools.simple.runtime.components.android.Sound;
import com.google.devtools.simple.runtime.components.android.VerticalArrangement;
import com.google.devtools.simple.runtime.events.EventDispatcher;
import com.google.devtools.simple.runtime.components.android.Sound;
import android.graphics.Color;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class TicTacStartNDSActivity extends Form implements HandlesEventDispatching {

	private Button btnXOchanger;
	private Button btnReset;
	private Button btnClearScore;
	
	private Label lblTurn;
	private Label lblSpace;
	private Label lblXWins;
	private Label lblXWinsCount;
	private Label lblOWins;
	private Label lblOWinsCount;
	private Label lblCatsGame;
	private Label lblCatsGameCount;
	
	int ht = 75;
	int wd = 75;
	float fs = 50.0f;
	int count = 0;
	private int cat = 0;
	private int xx = 0;
	private int oo = 0;
	private Sound snd;
	
	boolean xGoesNow = true;
	
	//Button board[]={btnOne,btnTwo,btnThree,btnFour,btnFive,btnSix,btnSeven,btnEight,btnNine};
	
	private final int ROWS = 3;
	private final int COLUMNS = 3;
	private Button[][] board = new Button[ROWS][COLUMNS];

void $define() {

	
	
	this.BackgroundColor(COLOR_BLACK);
	
	snd = new Sound(this);
	snd.Source("meow.mp3");
	
	
	
	for (int i = 0; i < ROWS; i++){
		
HorizontalArrangement hr = new HorizontalArrangement(this);
		for (int j = 0; j < COLUMNS; j++){
			board[i][j] = new Button(hr);
			board[i][j].Width(80);
			board[i][j].Height(80);
			board[i][j].Text("");
			board[i][j].FontSize(50.0f);
			}
	}
	
	
	HorizontalArrangement hr = new HorizontalArrangement(this);
		
		
		btnXOchanger = new Button(hr);
		btnReset = new Button (hr);
		
		lblTurn = new Label(hr);
		
		VerticalArrangement vr = new VerticalArrangement(this);
		
		lblSpace = new Label(vr);
		btnClearScore = new Button(vr);
		lblTurn.FontSize(20.0f);
		lblTurn.Text("     X's Turn");
		lblSpace.FontSize(5.0f);
		lblSpace.Text(" ");
		btnClearScore.FontSize(10.0f);
		btnClearScore.Text("Clear Score");
		btnClearScore.Height(25);
		lblTurn.TextColor(COLOR_WHITE);
		
		btnXOchanger.Text("X");
		btnReset.Text("Reset");
		
		hr = new HorizontalArrangement(this);
		lblXWins = new Label(hr);
		lblXWins.Text("X Wins: ");
		lblXWins.TextColor(COLOR_WHITE);
		lblXWinsCount = new Label(hr);
		lblXWinsCount.Text(""+xx);
		lblXWinsCount.TextColor(COLOR_WHITE);
		
		hr = new HorizontalArrangement(this);
		lblOWins = new Label(hr);
		lblOWins.Text("O Wins: ");
		lblOWins.TextColor(COLOR_WHITE);
		lblOWinsCount = new Label(hr);
		lblOWinsCount.Text(""+oo);
		lblOWinsCount.TextColor(COLOR_WHITE);
		
		hr = new HorizontalArrangement(this);
		lblCatsGame = new Label(hr);
		lblCatsGame.Text("Cat's Games: ");
		lblCatsGame.TextColor(COLOR_WHITE);
		lblCatsGameCount = new Label(hr);
		lblCatsGameCount.Text("" + cat);
		lblCatsGameCount.TextColor(COLOR_WHITE);
	
		btnXOchanger.Height(50);
		btnXOchanger.Width(50);
		btnXOchanger.FontSize(20.0f);
		btnReset.Height(50);
		btnReset.Width(75);
		btnReset.FontSize(20.0f);
	
	

EventDispatcher.registerEventForDelegation(this, "JavaBridge", "Click");
}

@Override
public boolean dispatchEvent(Component component, String id, String eventName, Object[]args){
	
	for (int i = 0; i < ROWS; i++)
		for (int j = 0; j < COLUMNS; j++){
			if (component.equals(board[i][j]) && eventName.equals("Click") && board[i][j].Text().equals("")){
				board[i][j].Text(xGoesNow?"X":"O");
				if (checkForWinner(i,j) && xGoesNow != false) 
					lblTurn.Text("     X WINS");
					if (lblTurn.Text().equals("     X WINS"))
						xx += 1;
					 	lblXWinsCount.Text("" + xx);
				if (checkForWinner(i,j) && xGoesNow != true)
					lblTurn.Text("     O WINS");
					if (lblTurn.Text().equals("     O WINS"))
						oo += 1;
				 		lblOWinsCount.Text("" + oo);
				 		// Cat's game
				/*if (/*checkForWinner(i,j) != true && board[i][j].Text().equals("O") || board[i][j].Text().equals("X"))
					lblTurn.Text("    CAT'S GAME"); 
					*/
				xGoesNow = (xGoesNow?false:true);
				
				/*if (component.equals(board[i][j]) && eventName.equals("Click")){
					if (checkForWinner())
						lblTurn.Text("     X WINS");
				}*/
				if (lblTurn.Text().equals("     X WINS") || lblTurn.Text().equals("     O WINS")){
					for (int m = 0; m < ROWS; m++)
						for (int n = 0; n < COLUMNS; n++){
					board[m][n].Enabled(false);
						}
					return true;
				}
				// changes btnXOchanger text to display the mark of whose turn it is
				if (xGoesNow?false:true){
					btnXOchanger.Text("O");
				}
			 else{
				  btnXOchanger.Text("X");
			 }
				// disables btnXOchanger once the match has started
				if (board[i][j].Text().equals("X")||board[i][j].Text().equals("O")){
					btnXOchanger.Enabled(false);
				}
				// changes turn label with btnXOchanger text
				if (btnXOchanger.Text().equals("X")){
					lblTurn.Text("     X's Turn");
			} if (btnXOchanger.Text().equals("O")){
					lblTurn.Text("     O's Turn");
			}
				return true;
			}
			//changes who goes first
			if (component.equals(btnXOchanger) && eventName.equals("Click")) {
				xGoesNow = (xGoesNow?false:true);
				if (xGoesNow?false:true){
					btnXOchanger.Text("O");
				}
			 else{
				  btnXOchanger.Text("X");
			 }
				// changes turn label with btnXOchanger text with Click
				if (btnXOchanger.Text().equals("X")){
					lblTurn.Text("     X's Turn");
			} if (btnXOchanger.Text().equals("O")){
					lblTurn.Text("     O's Turn");
			}
				return true;
			}
		}
			// resets the board back to its original state
			if (component.equals(btnReset) && eventName.equals("Click")) {
				for (int i = 0; i < ROWS; i++)
					for (int j = 0; j < COLUMNS; j++){
				xGoesNow = true;
				board[i][j].Text("");
				btnXOchanger.Text("X"); btnXOchanger.Enabled(true);
				lblTurn.Text("     X's Turn");
				board[i][j].TextColor(COLOR_BLACK);
				board[i][j].Enabled(true);
					} // end for
				return true;
			} // end if
			// clears the scoreboard to all 0s
			if (component.equals(btnClearScore) && eventName.equals("Click")) {
				lblXWinsCount.Text("0");
				lblOWinsCount.Text("0");
				lblCatsGameCount.Text("0");
				return true;
			}
		return false;
	} // end dispatchEvent
//above code is the END of the EventDispatcher
	private boolean checkForWinner(int rowLoc, int colLoc) {
		boolean gotWinner = true;
		// Check each column for this row
		for (int i = 0; i < COLUMNS; i++) {
			if (!board[rowLoc][i].Text().equals(xGoesNow?"X":"O")) {
				gotWinner = false;
				break;
			} // end if 
		} // end for i
	if (gotWinner) {
		// The current row is the winning one
		for (int i = 0; i < COLUMNS; i++) {
			if (xGoesNow != false)
				board[rowLoc][i].TextColor(COLOR_RED);
			if (xGoesNow != true)
				board[rowLoc][i].TextColor(COLOR_BLUE);
		}
		return gotWinner;
	// Check to see if the columns are winning
	}
		gotWinner = true;
		for (int i = 0; i < ROWS; i++) {
			if (!board[i][colLoc].Text().equals(xGoesNow?"X":"O")) {
				gotWinner = false;
				break;
			} // end if 
		} // end for j
	if (gotWinner) {
		for (int i = 0; i < ROWS; i++) {
			if (xGoesNow != false)
				board[i][colLoc].TextColor(COLOR_RED);
			if (xGoesNow != true)
				board[i][colLoc].TextColor(COLOR_BLUE);
		}
		return gotWinner;
	}
		// check for diagonals
		// these squares are impossible for a diagonal
	if (rowLoc == 0 && colLoc == 1 | rowLoc == 1 && colLoc == 0 | rowLoc == 1 && colLoc == 2 | rowLoc == 2 && colLoc == 1) // use rowLoc and colLoc
		return false;  // Default case: no winner
	gotWinner = true;
	// Check for right leaning diagonal win
	for (int i = 0; i < ROWS; i++) {
		if (!board[i][i].Text().equals(xGoesNow?"X":"O")) {
			gotWinner = false;
			break;
			} // end if
		} // end for
	if (gotWinner) {
		for (int i = 0; i < ROWS; i++) {
			if (xGoesNow != false)
				board[i][i].TextColor(COLOR_RED);
			if (xGoesNow != true)
				board[i][i].TextColor(COLOR_BLUE);
		}
		return true;
	}
	gotWinner = true;
	// Check for left leaning diagonal win
	for (int i = 0, j = 2; j >= 0; i++, j--) {
		if (!board[i][j].Text().equals(xGoesNow?"X":"O")) {
			gotWinner = false;
			break;
		}
	}
	if (gotWinner) {
		for (int i = 0, j = 2; j >= 0; i++, j--) {
			if (xGoesNow != false)
				board[i][j].TextColor(COLOR_RED);
			if (xGoesNow != true)
				board[i][j].TextColor(COLOR_BLUE);
		}
		return true;
	}
	gotWinner = true;
	// Cat's game
	return false;
	} // end checkForWinner
}


