//File created by Andrew Chafos: 8/21/15 @ 8:48 PM
package V1.games;

import V1.card.*;
import V1.component.*;
import V1.Globals;
import java.awt.*;
import java.awt.event.MouseEvent;

//The first (and hopefully one of the, plural) game(s)
//to be in AJC Classic Card Games!
public class Cribbage extends GameComponent{
    
    private final Deck deck;
    private final Deck choosingDeck;
    private int chosenCard = -1;
    private int aiChosenCard = -1;
    private final Player player1;
    private final AIPlayer player2;
    private final Deck currentCrib;
    private final V1.component.drawn.Image cardBack;
    private int timeElapsed, currentAnim, gameState;
    private final V1.component.drawn.Button cribConfirm, startGame;
    private final V1.component.drawn.Label confirmText, choosingLabel, startLabel;
    private boolean selectCursor, playerHasCrib;
   
    private final int[] cribCardCoords = new int[4];
    private final int[] originalCardXs = new int[4];
    
    public Cribbage(){
        deck = new Deck(true);
        deck.shuffle(3);
        
        choosingDeck = new Deck(false);
        for(int i = 0;i < 13;i++){
            choosingDeck.addCard(
                new Card(i, 0, 0, 
                    (int)((650 - Globals.cardBackImage.getHeight()) / 2)
                )
            );
        }
        
        choosingDeck.shuffle(3);
        
        for(int i = 0;i < 13;i++){
            choosingDeck.getDeck().get(i).image.changeX((i + 1) * 70);
        }
        
        choosingLabel = new V1.component.drawn.Label(
            10, 150, "Choose a Card.  Player who draws the lowest card goes first.",
            new Font("Arial", Font.BOLD, 34), Color.blue
        );
        
        startLabel = new V1.component.drawn.Label(
            0, 0, "Start Cribbage",
            new Font("Arial", Font.BOLD, 48), Color.blue
        );
        
        startGame = new V1.component.drawn.Button(
            300, 450, 400, 100, Color.green
        ); 
        
        player1 = new Player();
        player2 = new AIPlayer();
        cardBack = new V1.component.drawn.Image(Globals.cardBackImage, 0, 0);
        
        for(int i = 0;i < 6;i++){
            player1.addCardToHand(deck.accessRand());
            player2.addCardToHand(deck.accessRand());
        }
        
        player1.currentHand.sortCards();
        player2.currentHand.sortCards();
        
        gameState = -2;
        timeElapsed = 0;
        currentAnim = 0;
        
        currentCrib = new Deck(false);
        cribConfirm = new V1.component.drawn.Button(325, 275, 350, 75, Color.blue,
            new V1.component.drawn.Border(Color.black, 325, 275, 350, 75, 5)
        );
        confirmText = new V1.component.drawn.Label(400, 275, "Click to Submit Crib",
            new Font("Arial", Font.BOLD, 32), Color.white
        );
        selectCursor = false;
    }//end of default constructor
    
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        drawBackground(g);
        
        if(gameState >= 0){
            cardBack.draw(g, 20, (int)((650 - Globals.cardBackImage.getHeight()) / 2));
        }
        
        if(gameState == -2){
            choosingLabel.drawInRect(g, new Color(255, 255, 255, 122));
            for(int x = 1;x <= 13;x++){
                if(x - 1 != chosenCard){
                    cardBack.deHighlight();
                    cardBack.draw(g, choosingDeck.getDeck().get(x - 1).image.getX(),
                        choosingDeck.getDeck().get(x - 1).image.getY()
                    );
                }
                else{
                    cardBack.highlight(new Color(0, 255, 255, 122));
                    cardBack.draw(g, choosingDeck.getDeck().get(x - 1).image.getX(),
                        choosingDeck.getDeck().get(x - 1).image.getY()
                    );
                }
            }
        }
        else if(gameState == -1){
            for(int x = 1;x <= 13;x++){
                if(x - 1 != chosenCard && x - 1 != aiChosenCard){
                    cardBack.deHighlight();
                    cardBack.draw(g, x * 70, (int)((650 - Globals.cardBackImage.getHeight()) / 2) );
                }
                else if(x - 1 == aiChosenCard){
                    if(timeElapsed < FPS){
                        cardBack.draw(g, x * 70, (int)((650 - Globals.cardBackImage.getHeight()) / 2) );
                    }
                    else{
                        choosingDeck.getDeck().get(aiChosenCard).image.draw(g);
                    }
                }
                else{
                    choosingDeck.getDeck().get(chosenCard).image.draw(g);
                }
            }
            
            if(timeElapsed == FPS * 2){
                if(choosingDeck.getDeck().get(chosenCard).getValue() < 
                    choosingDeck.getDeck().get(aiChosenCard).getValue()){
                    choosingLabel.changeX(100);
                    choosingLabel.text = "You drew the lowest card; you have the 1st crib.";
                    playerHasCrib = true;
                }
                else{
                    choosingLabel.changeX(20);
                    choosingLabel.text = "Computer drew the lowest card; Computer has the 1st crib.";
                    playerHasCrib = false;
                }
            }
            
            if(timeElapsed > FPS * 2){
                choosingLabel.drawInRect(g, new Color(255, 255, 255, 122));
            }
            
            if(timeElapsed >= FPS * 3){
                startLabel.drawInButton(g, startGame);
            }
            
            if(timeElapsed * 1.0 < FPS * 3.1){
                timeElapsed++;
            }
            
        }
        else if(gameState == 0){
            if(timeElapsed < FPS){
                timeElapsed++;
            }
            else{
                gameState = 1;
            }
        }
        else if(gameState == 1){
            for(int i = 0; i < 6;i++){
                int x = (int)(currentAnim * (170 + (i * 120)) / (FPS * 2));
                int y = (int)((650 - Globals.cardBackImage.getHeight()) / 2);
                cardBack.draw(g, x, y - (int)(currentAnim * (y - 20) / (FPS * 2)));
                player1.currentHand.getDeck().get(i).image.draw(g, x, y + (currentAnim * (460 - y) / (FPS * 2)));
            }
            if(currentAnim < FPS * 2){
                currentAnim++;
            }
            else{
                currentAnim = 0;
                gameState = 2;
            }
        }
        else if(gameState == 2){
            for(int i = 0; i < 6;i++){
                cardBack.draw(g, 170 + (i * 120), 20);
                player1.currentHand.getDeck().get(i).image.draw(g);
            }
            
            if(currentCrib.getDeck().size() == 2){
                cribConfirm.draw(g);
                confirmText.drawInButton(g, cribConfirm);
            }
        }
        else if(gameState == 3){
            if(currentAnim <= FPS){
                for(int i = 0; i < 4;i++){
                    cardBack.draw(g, 170 + (i * 120), 20);
                    player1.currentHand.getDeck().get(i).image.draw(g);
                }
                currentCrib.getDeck().get(0).image.draw(g);
                currentCrib.getDeck().get(1).image.draw(g);
                cardBack.draw(g, 650, 20);
                cardBack.draw(g, 770, 20);
                currentAnim++;
            }
            else if(currentAnim > FPS && currentAnim <= FPS * 2.0){
                currentCrib.getDeck().get(0).image.draw(g, 
                    cribCardCoords[0] + (int)(((currentAnim - FPS) * (1000 - cribCardCoords[0])) / FPS),
                    cribCardCoords[1] - (int)(((currentAnim - FPS) * 200) / (FPS * 2.0))
                );
                currentCrib.getDeck().get(1).image.draw(g, 
                    cribCardCoords[2] + (int)(((currentAnim - FPS) * (1000 - cribCardCoords[2])) / FPS),
                    cribCardCoords[3] - (int)(((currentAnim - FPS) * 200) / (FPS * 2.0))
                );
                for(int i = 0;i < 4;i++){
                    int newX = 170 + (i * 120);
                    int x = originalCardXs[i]; 
                    player1.currentHand.getDeck().get(i).image.draw(g,
                        x + (int)(((currentAnim - FPS) * (newX - x) / FPS)),
                        460
                    );
                    cardBack.draw(g, 170 + (i * 120), 20);
                }
                cardBack.draw(g, 
                    650 + (int)(((currentAnim - FPS) * 350) / FPS),
                    (int)(((currentAnim - FPS) * 330) / FPS)
                );
                cardBack.draw(g, 
                    770 + (int)(((currentAnim - FPS) * 300) / FPS),
                    (int)(((currentAnim - FPS) * 300) / FPS)
                );
                  
                currentAnim++;
            }
            else{
                for(int i = 0;i < 4;i++){
                    player1.currentHand.getDeck().get(i).image.draw(g);
                    cardBack.draw(g, 170 + (i * 120), 20);
                }
            }
        }
        
    }//end of method
    
    @Override
    public void mouseMoved(MouseEvent e){
        int mouseX = e.getX();
        int mouseY = e.getY();
        
        boolean inBounds = false;
        
        if(gameState == -2){
            if(mouseX > 70 && mouseX < 910 + Globals.cardBackImage.getWidth() && 
                mouseY > (int)((650 - Globals.cardBackImage.getHeight()) / 2) &&
                mouseY < (int)((650 - Globals.cardBackImage.getHeight()) / 2) +
                    Globals.cardBackImage.getHeight()
            ){
                if(mouseX < 910){
                    if(chosenCard != (int)(Math.floor(mouseX / 70)) - 1){
                        setCursor(new Cursor(Cursor.HAND_CURSOR));
                        chosenCard = (int)(Math.floor(mouseX / 70)) - 1;
                    }
                }
                else{
                    if(chosenCard != 12){
                        setCursor(new Cursor(Cursor.HAND_CURSOR));
                        chosenCard = 12;
                    }
                }
            }
            else{
                if(chosenCard != -1){
                    setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                    chosenCard = -1;
                }
            }
        }
        else if(gameState == -1){
            if(startGame.containsCoords(mouseX, mouseY)
                && !startGame.getHighlighted()
            ){
                startGame.highlightComponent(true);
                setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
            else if(!startGame.containsCoords(mouseX, mouseY)
                && startGame.getHighlighted()
            ){
                startGame.highlightComponent(false);
                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        }
        else if(gameState == 2){
            for(Card card: player1.currentHand.getDeck()){
                if(card.image.containsCoords(mouseX, mouseY) && 
                    !card.image.getHighlighted()
                ){
                    card.image.highlight(new Color(0, 255, 255, 128));
                }
                else if(!card.image.containsCoords(mouseX, mouseY) && 
                    card.image.getHighlighted()
                ){
                    card.image.deHighlight();
                }
                
                if(card.image.containsCoords(mouseX, mouseY)){
                    inBounds = true;
                }
            }//end of for loop
            
            if(cribConfirm.containsCoords(mouseX, mouseY)){
                inBounds = true;
            }
            
            if(cribConfirm.containsCoords(mouseX, mouseY) && 
                !cribConfirm.getBool("usingBorder")){
                cribConfirm.changeBorderUse(true);
            }
            else if(!cribConfirm.containsCoords(mouseX, mouseY) && 
                cribConfirm.getBool("usingBorder")){
                cribConfirm.changeBorderUse(false);
            }
            
        }//end of if
        
        if(inBounds && !selectCursor){
            selectCursor = true;
            setCursor(new Cursor(Cursor.HAND_CURSOR));
        }
        else if(!inBounds && selectCursor){
            selectCursor = false;
            setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        }
        
    }//end of method
    
    @Override
    public void mouseClicked(MouseEvent e){
        int mouseX = e.getX();
        int mouseY = e.getY();
        
        if(gameState == -2){
            if(chosenCard != -1){
                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                int computerChoice;
                if(chosenCard == 12){
                    computerChoice = (int)(Math.round(Math.random() * 11));
                }
                else{
                    computerChoice = (int)(Math.round(Math.random() * 12));
                    if(computerChoice == chosenCard){
                        computerChoice++;
                    }
                }
                aiChosenCard = computerChoice;
                gameState++;
            }
        }
        else if(gameState == -1){
            if(startGame.containsCoords(mouseX, mouseY)){
                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                timeElapsed = 0;
                gameState = 0;
            }
        }
        else if(gameState == 2){
            for(Card card: player1.currentHand.getDeck()){
                if(card.image.containsCoords(mouseX, mouseY) && !card.image.selected
                    && currentCrib.getDeck().size() < 2){
                    card.image.selected = true;
                    card.image.changeY(440);
                    currentCrib.addCard(card);
                }
                else if(card.image.containsCoords(mouseX, mouseY) && card.image.selected){
                    card.image.selected = false;
                    card.image.changeY(460);
                    currentCrib.removeCard(card);
                }
            }//end of for loop
            
            if(currentCrib.getDeck().size() == 2){
                if(cribConfirm.containsCoords(mouseX, mouseY)){
                    setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                    
                    player1.removeCard(currentCrib.getDeck().get(0));
                    player1.removeCard(currentCrib.getDeck().get(1));
                    
                    cribCardCoords[0] = currentCrib.getDeck().get(0).image.getX();
                    cribCardCoords[1] = currentCrib.getDeck().get(0).image.getY();
                    cribCardCoords[2] = currentCrib.getDeck().get(1).image.getX();
                    cribCardCoords[3] = currentCrib.getDeck().get(1).image.getY();
                    
                    Card[] computerCards = player2.makeCribSelection();
                    
                    currentCrib.addCard(computerCards[0]);
                    currentCrib.addCard(computerCards[1]);
                    
                    for(int i = 0;i < 4;i++){
                        originalCardXs[i] = player1.currentHand.getDeck().get(i).image.getX();
                    }
                    
                    gameState++;
                }
            }
        }//end of if
    }
    
}//end of class
