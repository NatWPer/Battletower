package graphics;

import java.util.ArrayList;

public class MapBuilder {
    ArrayList<ArrayList<Wall>> rooms = new ArrayList<>();
    public MapBuilder() {
        ArrayList<Wall> room1 = new ArrayList<>();
        room1.add(new Wall(220,220,-20,720));
        room1.add(new Wall(420,420,-20,720));
        room1.add(new Wall(220,420,0,0));
        room1.add(new Wall(220,420,700,700));
        rooms.add(room1);
        ArrayList<Wall> room2 = new ArrayList<>();
        room2.add(new Wall(100,100,-20,720));
        room2.add(new Wall(600,600,-20,720));
        room2.add(new Wall(220,420,0,0));
        room2.add(new Wall(220,420,700,700));
        rooms.add(room2);
        ArrayList<Wall> room3 = new ArrayList<>();
        room3.add(new Wall(100,100,-20,720));
        room3.add(new Wall(600,600,-20,720));
        room3.add(new Wall(220,420,0,0));
        room3.add(new Wall(220,420,700,700));
        rooms.add(room3);
        ArrayList<Wall> room4 = new ArrayList<>();
        room4.add(new Wall(70,70,-20,720));
        room4.add(new Wall(630,630,-20,720));
        room4.add(new Wall(70,630,60,60));
        room4.add(new Wall(70,630,605,605));
        rooms.add(room4);
    }

    public ArrayList<Wall> getRoom(int i){
        return rooms.get(i);
    }
}
