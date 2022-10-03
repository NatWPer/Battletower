package graphics;

import fighters.PlayerCharacter;
import fighters.enemies.Enemy;
import fighters.enemies.FleshGolem;
import fighters.enemies.Owlbear;
import fighters.enemies.Spider;
import processing.core.PApplet;
import processing.core.PImage;

import java.util.ArrayList;
import java.util.Random;

public class GraphicsMain extends PApplet {
    PImage character;
    PImage bg;
    PImage enemy;

    PImage door;

    PImage cursorImage;

    ArrayList<String> enemyImages = new ArrayList<>();
    enum GameState {
        GAMEOVER, RUNNING, COMBAT,STARTUP,SETDIFFICULTY,DEMONMODE,NAMING,WEAPONSELECT
    }

    enum CombatState{
        MELEE, ARCANE, INVENTORY, SPECIAL
    }

    enum RunningState{
        PEACE,ENEMYAROUND
    }
    static GameState currentState;
    public float enemyX=334;
    public float enemyY=334;
    public boolean moveForward = false;
    public boolean moveBackward = false;
    public boolean moveLeft=false;
    public boolean moveRight=false;
    public boolean moving = false;
    public float speed = 5;
    public float playerX = 350;
    public float playerY = 650;
    MyButton[] difficulties = new MyButton[10];
    MyButton demon;
    MyButton wand;
    MyButton sword;
    MyButton nonDemon;
    String name="";
    PlayerCharacter player;
    Enemy currentEnemy;
    Random rand=new Random();
    static RunningState runningState;
    int currentRoomNum = 0;
    MapBuilder mapBuilder;
    public int difficulty;
    public ArrayList<Enemy> enemies=new ArrayList<>();

    // initializes variables
    //demonCounter determine game's hardcore mode
    public int demonCounter=1;
    //helps determine order(will be updated to work with multiple fighters.enemies
    public int second;

    public ArrayList<String> roomImages = new ArrayList<>();

    public void keyPressed() {
        System.out.println(playerX+" "+playerY);
        switch (currentState) {
            case RUNNING:
                if (key == 's') {
                    moveForward= true;
                    moving = true;
                    character = loadImage("Images/CharFront.png");
                }
                if (key == 'w') {
                    moveBackward= true;
                    moving = true;
                    character = loadImage("Images/CharBack.png");
                }
                if (key == 'd') {
                    moveRight = true;
                    moving = true;
                    character = loadImage("Images/CharRight.png");
                }
                if (key == 'a') {
                    moveLeft = true;
                    moving = true;
                    character = loadImage("Images/CharLeft.png");
                }
                break;
            case NAMING:
                if(key=='\n'){
                    currentState=GameState.WEAPONSELECT;
                    player=new PlayerCharacter(name);
                    break;
                }
                if(key=='\b' && name.length()>0){
                    name=name.substring(0,name.length()-1);
                }else {
                    name = name + key;
                }
                break;
        }
    }

    public void keyReleased() {
        if (key == 's') {
            moveForward = false;
            moving = false;
        }
        if (key == 'w') {
            moveBackward = false;
            moving = false;
        }
        if (key == 'd') {
            moveRight = false;
            moving = false;
        }
        if (key == 'a') {
            moveLeft = false;
            moving = false;
        }
    }

    public Wall getSouthWall(){
        ArrayList<Wall> horWalls= new ArrayList<>();
        for (Wall w:
             mapBuilder.getRoom(currentRoomNum)) {
            if(w.getY1()==w.getY2()){
                horWalls.add(w);
            }
        }
        horWalls.removeIf(w -> w.getY2() < playerY);
        Wall tempWall = new Wall();
        for (Wall w:
             horWalls) {
            if (tempWall.getY2()==-1){
                tempWall=w;
            } else if (tempWall.getY2()<w.getY2()) {
                tempWall=w;
            }
        }
        return tempWall;
    }

    public Wall getNorthWall(){
        ArrayList<Wall> horWalls= new ArrayList<>();
        for (Wall w:
                mapBuilder.getRoom(currentRoomNum)) {
            if(w.getY1()==w.getY2()){
                horWalls.add(w);
            }
        }
        horWalls.removeIf(w -> w.getY2() > playerY);
        Wall tempWall = new Wall();
        for (Wall w:
                horWalls) {
            if (tempWall.getY2()==-1){
                tempWall=w;
            } else if (tempWall.getY2()>w.getY2()) {
                tempWall=w;
            }
        }
        return tempWall;
    }

    public Wall getEastWall(){
        ArrayList<Wall> verWalls= new ArrayList<>();
        for (Wall w:
                mapBuilder.getRoom(currentRoomNum)) {
            if(w.getX1()==w.getX2()){
                verWalls.add(w);
            }
        }
        verWalls.removeIf(w -> w.getX2() < playerX);
        Wall tempWall = new Wall();
        for (Wall w:
                verWalls) {
            if (tempWall.getX2()==-1){
                tempWall=w;
            } else if (tempWall.getX2()<w.getX2()) {
                tempWall=w;
            }
        }
        return tempWall;
    }

    public Wall getWestWall(){
        ArrayList<Wall> verWalls= new ArrayList<>();
        for (Wall w:
                mapBuilder.getRoom(currentRoomNum)) {
            if(w.getX1()==w.getX2()){
                verWalls.add(w);
            }
        }
        verWalls.removeIf(w -> w.getX2() > playerX);
        Wall tempWall = new Wall();
        for (Wall w:
                verWalls) {
            if (tempWall.getX2()==-1){
                tempWall=w;
            } else if (tempWall.getX1()>w.getX1()) {
                tempWall=w;
            }
        }
        return tempWall;
    }
    public void forward() {
        Wall tempWall = getSouthWall();
        if (moveForward && ((playerY<tempWall.getY1()) || ((playerX>tempWall.getX2())||playerX<tempWall.getX1()))) {
            playerY+=speed;
        }
    }

    public void back(){
        Wall tempWall = getNorthWall();
        if (moveBackward && ((playerY>tempWall.getY1()) || ((playerX>tempWall.getX2())||playerX<tempWall.getX1()))) {
            playerY-=speed;
        }
    }

    public void left(){
        Wall tempWall = getWestWall();
        if (moveLeft && ((playerX>tempWall.getX2() || (playerY<tempWall.getY1()||playerY>tempWall.getY2())))) {
            playerX-=speed;
        }
    }

    public void right(){
        Wall tempWall = getEastWall();
        if (moveRight && ((playerX<tempWall.getX2() || (playerY<tempWall.getY1()||playerY>tempWall.getY2())))) {
            playerX+=speed;
        }
    }

    public static void main(String[] args) {
        PApplet.main("graphics.GraphicsMain");
    }
    public void settings() {
        size (700, 700);
    }

    public void setup() {
        roomImages.add("Images/outside.jpg");
        roomImages.add("Images/approaching.jpg");
        roomImages.add("Images/Entrance.jpg");
        roomImages.add("Images/map.jpg");
        mapBuilder= new MapBuilder();
        runningState = RunningState.PEACE;
        door = loadImage("Images/Door.png");
        cursorImage=loadImage("Images/CursorA.png");
        character = loadImage("Images/CharBack.png");
        bg = loadImage(roomImages.get(0));
        enemyImages.add("Images/Owlbear.png");
        enemies.add(new Owlbear("Gathren"));
        enemyImages.add("Images/FleshGolem.png");
        enemies.add(new FleshGolem("Gelmue"));
        enemyImages.add("Images/Spider.png");
        enemies.add(new Spider("Hethral"));
        for (int i = 0; i < enemyImages.size(); i++) {
            enemies.get(i).setPng(enemyImages.get(i));
        }
        currentEnemy=enemies.get(rand.nextInt(enemies.size()));
        enemy=loadImage(currentEnemy.getPng());
        enemy.resize(64,64);
        bg.resize(700,700);
        currentState = GameState.STARTUP;
    }

    public void createEnemy(){
        imageMode(CENTER);
        image(enemy, enemyX, enemyY);
        if((abs(playerX-enemyX) < 30) && abs(playerY-enemyY)< 30 ){
            currentState=GameState.GAMEOVER;
        }
    }
    boolean b = true;
    public void createDoor(){
        imageMode(CENTER);
        image(door,350,getNorthWall().getY1()+20);
        if((abs(playerX-350) < 15) && abs(playerY-(getNorthWall().getY1()+20))< 15 ){
            playerY=getSouthWall().getY2()-15;
            if (currentRoomNum<3) {
                currentRoomNum++;
            }else {
                currentEnemy=enemies.get(rand.nextInt(enemies.size()));
                runningState=RunningState.ENEMYAROUND;
            }
            PImage p = loadImage(roomImages.get(currentRoomNum));
            p.resize(700,700);
            bg=p;
        }
    }

    public void mousePressed(){
        cursorImage=loadImage("Images/CursorB.png");
        switch (currentState){
            case GAMEOVER:
                if (mouseX>width / 2 - 125 && mouseX<width / 2 + 125
                        && mouseY>height / 2 - 80 && mouseY<height / 2 + 80){
                    playerX=334;
                    playerY=550;
                    currentState=GameState.RUNNING;
                }
                break;
            case SETDIFFICULTY:
                if (mouseY>difficulties[0].getyPos()-difficulties[0].getyRadius()/2 && mouseY<difficulties[0].getyPos()+difficulties[0].getyRadius()/2) {
                    for (MyButton m: difficulties) {
                        difficulty++;
                        if (mouseX > m.getxPos()-m.getxRadius()/2 && mouseX<m.getxPos()+m.getxRadius()/2) {
                            currentState=GameState.DEMONMODE;
                        }
                    }
                }
                break;
            case DEMONMODE:
                if (mouseY>demon.getyPos()-demon.getyRadius()/2 && mouseY<demon.getyPos()+demon.getyRadius()/2) {
                    if (mouseX > demon.getxPos()-demon.getxRadius()/2 && mouseX<demon.getxPos()+demon.getxRadius()/2) {
                        currentState=GameState.NAMING;
                    }
                    if (mouseX > nonDemon.getxPos()-nonDemon.getxRadius()/2 && mouseX<nonDemon.getxPos()+nonDemon.getxRadius()/2) {
                        currentState=GameState.NAMING;
                    }
                }
                break;
            case NAMING:
                currentState=GameState.WEAPONSELECT;
                player=new PlayerCharacter(name);
                break;
            case WEAPONSELECT:
                if (mouseY>wand.getyPos()-wand.getyRadius()/2 && mouseY<wand.getyPos()+wand.getyRadius()/2) {
                    if (mouseX > wand.getxPos()-wand.getxRadius()/2 && mouseX<wand.getxPos()+wand.getxRadius()/2) {
                        currentState=GameState.RUNNING;
                    }
                    if (mouseX > sword.getxPos()-sword.getxRadius()/2 && mouseX<sword.getxPos()+sword.getxRadius()/2) {
                        currentState=GameState.RUNNING;
                    }
                }
        }
    }

    public void mouseReleased() {
        cursorImage=loadImage("Images/CursorA.png");
    }

    public void drawGameOver(){
        fill(255, 190, 190);
        noStroke();
        rectMode(CENTER);
        rect(width / 2, height / 2-70, 250, 160);
        fill(255, 100, 100);
        textAlign(CENTER);
        text("Game Over!", width / 2, height / 2 - 50);
    }

    public void drawDifficultyButtons(){
        int x=60;
        int y=600;
        for (int i = 0; i < 10; i++) {
            difficulties[i]= new MyButton((i+1)+"",40,40,x,y);
            drawButton(difficulties[i],20);
            x+=60;
        }
    }

    public void drawDemonMode(){
        demon=new MyButton("Demon\nMode",80, 80, 500,350);
        nonDemon= new MyButton("Normal\nMode",80, 80, 200,350);
        drawButton(demon,17);
        drawButton(nonDemon,17);

    }

    public void drawWeaponMode(){
        wand=new MyButton("Wand",80, 80, 500,350);
        sword= new MyButton("Sword",80, 80, 200,350);
        drawButton(wand,17);
        drawButton(sword,17);
    }

    public void drawButton(MyButton myButton,float textSize){
        rectMode(CENTER);
        fill(255, 190, 190);
        noStroke();
        rect(myButton.getxPos(), myButton.getyPos(), myButton.getxRadius(), myButton.getyRadius());
        fill(0, 0, 0);
        textSize(textSize);
        textAlign(CENTER);
        text(myButton.getText(), myButton.getxPos(), myButton.getyPos()+5);
    }
    public void drawSetDifficulty(){
        textSize(20);
        fill(0, 0, 0);
        noStroke();
        rect(width / 2 - 125, height / 2 - 80, 250, 160);
        fill(255, 255, 255);
        textAlign(CENTER);
        text("How difficult would you like this to be on a scale of 1 to 10?", width / 2, height / 2 - 50);
        drawDifficultyButtons();
    }

    public void drawSetName(){
        textSize(20);
        fill(0, 0, 0);
        noStroke();
        fill(255, 255, 255);
        textAlign(CENTER);
        text("Type your character name. (Then click to continue.)", width / 2, height / 2 - 50);
    }

    public void drawName(){
        textSize(20);
        fill(0, 0, 0);
        noStroke();
        fill(255, 255, 255);
        textAlign(CENTER);
        text(name,width/2,height-200);
    }

    public void draw() {
        cursor(cursorImage);
        switch (currentState) {
            case STARTUP:
                background(0,0,0);
                currentState=GameState.SETDIFFICULTY;
                break;
            case SETDIFFICULTY:
                background(0,0,0);
                drawSetDifficulty();
                break;
            case DEMONMODE:
                background(0,0,0);
                drawDemonMode();
                break;
            case RUNNING:
                background(bg);
                forward();
                back();
                left();
                right();
                imageMode(CENTER);
                image(character,playerX,playerY);
                createDoor();
                switch (runningState){
                    case PEACE:
                        break;
                    case ENEMYAROUND:
                        createEnemy();
                        break;
                }
                break;
            case NAMING:
                background(0,0,0);
                drawSetName();
                drawName();
                break;
            case WEAPONSELECT:
                background(0,0,0);
                drawWeaponMode();
                break;
            case GAMEOVER:
                drawGameOver();
                break;

        }
    }
}
