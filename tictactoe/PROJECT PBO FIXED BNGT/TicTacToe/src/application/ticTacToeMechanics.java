package application;

import java.util.Arrays;
import javafx.event.ActionEvent;

/**
 *
 * @author ADIPSAGALA
 */

//the game logic
public class ticTacToeMechanics {
    
    //data fields
    private int currentPosition = 9;
    protected char currentLetter = 'x';
    protected boolean isX = true;
    protected boolean xWon = false;
    protected boolean oWon = false;
    protected boolean gameTied = false;
    protected boolean gameOver = false;
    protected boolean noMovesAllowed = false;
    
    //arrays for tracking player moves
    protected boolean[] positionsOccupied = new boolean[9];
    protected boolean[] xPositionsOccupied = new boolean[9];
    
    //array for tracking used spaces
    protected boolean[] oPositionsOccupied = new boolean[9];
    
    //all possible win combinations
    protected final int[] winningComboPositions = {0,1,2,3,4,5,6,
        7,8,0,3,6,1,4,7,2,5,8,0,4,8,2,4,6};


    
    //handle what happens when player clicks start button
    public void startGame(ActionEvent event) {
        
        resetGame();
      
    }
    
    //handle what happens when a player clicks on a square
    public void handleClick(int currentPosition){
        
        this.currentPosition = currentPosition;
        
        //if the box isn't occupied
        if(!xPositionsOccupied[currentPosition] 
                && !oPositionsOccupied[currentPosition]
                && !noMovesAllowed){
            
            //log position for letter
            logCurrentPosition(currentPosition);
            
            //drawLetter(currentPosition);
            drawLetter(currentPosition);
            
            //check for win or tie
            checkGameState();
            
            //if game is over, print results
            printResults();
            
            //if nobody has won, and game isn't a tie
            if(!gameOver){
                
                //switch turns
                switchTurns();
                
            }else{
                
                
                
            }
            
        }
        
    }
    
    //draw x or o
    public void drawLetter(int currentPosition){
        
        //handled in controller class
        
    }
    
    //remove x or o
    public void removeLetter(int currentPosition){
        
        //handled in controller class
        
    }
    
    //log letter position
    public void logCurrentPosition(int currentPosition){
        
        if(currentLetter == 'x'){
            xPositionsOccupied[currentPosition] = true;
        }else{
            oPositionsOccupied[currentPosition] = true;
        }
        
        positionsOccupied[currentPosition] = true;
        
    }
    
    public void switchTurns(){
        //done in controller class
    }
    
    //check for win or tie
    public void checkGameState(){
        
        if(isX){
            
            for(int x = 0; x < 22; x += 3){
                
                int i = x;
                
                if (hasXWon(winningComboPositions[i],  
                        winningComboPositions[i + 1],
                        winningComboPositions[i + 2])){
        
                    xWon = true;
                    gameOver();
                    showStartButton();

                    break;
                }
                
            }
            
        }else{
            
            for(int x = 0; x < 22; x += 3){
                
                int i = x;
                
                if (hasOWon(winningComboPositions[i],  
                        winningComboPositions[i + 1],
                        winningComboPositions[i + 2])){
                    
                    oWon = true;
                    gameOver();
                    showStartButton();

                    break;
                }
                
            }
            
        }    
        
        isGameTied();
        
    }
    
    //determine if x has won
    public boolean hasXWon(int pos1, int pos2, int pos3){
        
        if(xPositionsOccupied[pos1] && xPositionsOccupied[pos2] && xPositionsOccupied[pos3]){
          return true;
        }
        else{
            return false;
        }  
    }
    
    //determine if o has won
    public boolean hasOWon(int pos1, int pos2, int pos3){
        
        if(oPositionsOccupied[pos1] && oPositionsOccupied[pos2] && oPositionsOccupied[pos3]){
          
            return true;

        }else{

            return false;

        }
        
    }
    
        //check if game is tied
    public void isGameTied(){
        
        int positionsTaken = 0;
        
        //loop through positions
        for(boolean pos : positionsOccupied){
            
            //determin how many positions are taken
            if(pos){
                
                positionsTaken += 1;
                
            }else{
                
                break;
               
            }    
            
        }
        
        //if all positions are taken
        if(positionsTaken == positionsOccupied.length){
            
            //the game is a tie
            gameTied = true;
            gameOver();
            showStartButton();
            
        }
        
    }
    
    public void gameOver(){
        
        noMovesAllowed = true;
        gameOver = true;
    }
    
    public void showStartButton(){
    
        //handled in controller class
        
    }
    
     //display results to players
    public void printResults(){
    
        //handled in controller class
      
    }
    
    public void resetGame(){
        
        //set all array values to false
        Arrays.fill(positionsOccupied, Boolean.FALSE);
        Arrays.fill(xPositionsOccupied, Boolean.FALSE);
        Arrays.fill(oPositionsOccupied, Boolean.FALSE);

        currentLetter = 'x';
        isX = true;
        xWon = false;
        oWon = false;
        gameTied = false;
        gameOver = false;
        noMovesAllowed = false;
        
        refreshWindow();    
    }
    
    public void refreshWindow(){ 
        //handled in controller class  
    }
} 