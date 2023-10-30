import java.lang.reflect.Array;
import java.util.Scanner;

public class Labyrinth {
	public static void main(String args[]) {
		
		// Scanner
		Scanner scanner = new Scanner(System.in);
		
		//Labyrinthe
		char[][] labyrinth = {};
		
		char[][] labyrinth1 = {
                { '#', '#', '#', '#', '#', '#', '#', '#', '#'},
                { '#', '>', ' ', ' ', '#', ' ', ' ', ' ', '#'},
                { '#', '#', '#', ' ', ' ', ' ', '#', ' ', '#'},
                { '#', ' ', ' ', '#', '#', ' ', ' ', ' ', 'E'},
                { '#', '#', '#', '#', '#', '#', '#', ' ', '#'},
                { '#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#'},
                { '#', '#', '#', '#', '#', '#', '#', '#', '#'}
            };
		
		char[][] labyrinth2 = {
                { '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#'},
                { '#', '>', ' ', ' ', '#', ' ', ' ', ' ', '#', '#', '#', '#', '#', ' ', '#', '#', '#', '#'},
                { '#', '#', '#', ' ', ' ', ' ', '#', ' ', '#', ' ', ' ', ' ', '#', ' ', ' ', ' ', '#', '#'},
                { '#', ' ', ' ', '#', '#', ' ', ' ', ' ', ' ', ' ', '#', ' ', '#', ' ', '#', ' ', '#', '#'},
                { '#', '#', '#', '#', '#', '#', '#', ' ', '#', '#', '#', ' ', ' ', ' ', '#', ' ', ' ', 'E'},
                { '#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#'},
                { '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#'}
            };
		
		char[][] labyrinth3 = {
                { '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#'},
                { '#', '>', ' ', ' ', '#', ' ', ' ', ' ', '#', '#', '#', '#', '#', ' ', '#', '#', '#', '#'},
                { '#', '#', '#', ' ', ' ', ' ', '#', ' ', '#', ' ', ' ', ' ', '#', ' ', ' ', ' ', '#', '#'},
                { '#', ' ', ' ', '#', '#', ' ', ' ', ' ', ' ', ' ', '#', ' ', '#', ' ', '#', ' ', '#', '#'},
                { '#', '#', '#', '#', '#', '#', '#', ' ', '#', '#', '#', ' ', ' ', ' ', '#', ' ', ' ', 'E'},
                { '#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#'},
                { '#', '#', '#', ' ', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#'},
                { '#', '#', '#', ' ', '#', '#', '#', ' ', ' ', '#', '#', ' ', ' ', ' ', '#', '#', '#', '#'},
                { '#', '#', '#', ' ', '#', '#', ' ', ' ', ' ', '#', '#', ' ', '#', ' ', '#', '#', '#', '#'},
                { '#', ' ', ' ', ' ', '#', '#', ' ', '#', ' ', ' ', ' ', ' ', '#', ' ', ' ', '#', '#', '#'},
                { '#', ' ', '#', '#', '#', ' ', ' ', '#', '#', '#', ' ', '#', '#', '#', ' ', '#', '#', '#'},
                { '#', ' ', '#', '#', '#', ' ', '#', '#', '#', '#', ' ', '#', '#', '#', ' ', '#', '#', '#'}, 
                { '#', ' ', '#', '#', '#', ' ', '#', '#', '#', '#', ' ', '#', '#', '#', ' ', '#', '#', '#'}, 
                { '#', ' ', '#', ' ', ' ', ' ', '#', '#', ' ', ' ', ' ', '#', '#', '#', ' ', ' ', '#', '#'},
                { '#', ' ', ' ', ' ', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', ' ', ' ', 'E'},
                { '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#'},
                { '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#'},
                { '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#'}
            };
		
		//Leeres Labyrinth für zurückgelegten Weg von BB-8
		char[][] copy = {};
	
    	//Startpunkt
        int startX = 1; // Starting X position
        int startY = 1; // Starting Y position
        int counter = 0;
        
        // Intro zum Spiel
        System.out.println("Willkommen beim Labyrinth Spiel!");
        System.out.println("Suche dir eine Karte aus (1, 2 oder 3) und lasse BB-8 den Ausgang suchen.");
        
        // Auswahl der Karte
        int opt = scanner.nextInt();
        switch (opt) {
        case 1: 
        	labyrinth = labyrinth1;
        	copy = new char [labyrinth.length][labyrinth[0].length];
        	for(int i = 0; i < copy.length; i++) {
	        	for (int j = startX; j < copy[i].length; j++) {
	        		copy[i][j] = ' ';
	        		}        		
	        	}
        	break;
        case 2: 
        	labyrinth = labyrinth2;
        	copy = new char [labyrinth.length][labyrinth[0].length];
        	for(int i = 0; i < copy.length; i++) {
	        	for (int j = startX; j < copy[i].length; j++) {
	        		copy[i][j] = ' ';
	        		}        		
	        	}
        	break;
        case 3: 
        	labyrinth = labyrinth3;
        	copy = new char [labyrinth.length][labyrinth[0].length]; 
        	for(int i = 0; i < copy.length; i++) {
	        	for (int j = startX; j < copy[i].length; j++) {
	        		copy[i][j] = ' ';
	        		}        		
	        	}
        	break;
        }
        
        System.out.println();
        System.out.println();
        System.out.println("---------------------------------");
        
        displayLabyrinth(labyrinth); 
        char pos = labyrinth[startY][startX] = '>';
        changeDirection(labyrinth, startY, startX, pos, counter, copy);
    }
	
	/*
	 * Ausgabe vom Labyrinth
	 */
    public static void displayLabyrinth(char[][] labyrinth) {
        for (char[] row : labyrinth) {
            for (char cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }
    
    /*
     * Rechte Hand Regel - Bewegungsablauf
     */
    public static void changeDirection(char [][] labyrinth, int startY, int startX, char pos, int counter, char [][] copy) {
    	while ((startX + 1 >= 0 && startX + 1 < labyrinth[0].length) && (startY + 1 >= 0 && startY + 1 < labyrinth.length)) {  	
        	pos = labyrinth[startY][startX];
        	
        	switch(pos) {
        	
        	case '>':
    			if(labyrinth[startY + 1][startX] != '#') {            			
                	startY++;
                	labyrinth[startY][startX] = 'v'; 
        		} else if (labyrinth[startY][startX + 1] != '#') {
        			startX++;
                    labyrinth[startY][startX] = '>';
        		
        		} else if (labyrinth[startY - 1][startX] != '#') {
        			startY--;
        			labyrinth[startY][startX] = '^';
        		} else if (labyrinth[startY][startX - 1] != '#') {
        			startX--;
        			labyrinth[startY][startX - 1] = '<';
        		}
        		break;
        	case 'v':
    			if(labyrinth[startY][startX - 1] != '#') {
        			startX--;
        			labyrinth[startY][startX] = '<';
        		} else if (labyrinth[startY + 1][startX] != '#') {
        			startY++;
        			labyrinth[startY][startX] = 'v';
        		} else if (labyrinth[startY][startX + 1] != '#') {
        			startX++;
        			labyrinth[startY][startX] = '>';
        		} else if (labyrinth[startY - 1][startX] != '#') {
        			startY--;
        			labyrinth[startY][startX] = '^';
        		}
        		break;
        	case '<':
    			if(labyrinth[startY - 1][startX] != '#') {
        			startY--;
        			labyrinth[startY][startX] = '^';
        		} else if (labyrinth[startY][startX - 1] != '#') {
        			startX--;
        			labyrinth[startY][startX] = '<';
        		} else if (labyrinth[startY + 1][startX] != '#') {
        			startY++;
        			labyrinth[startY][startX] = 'v';
        		} else if (labyrinth[startY][startX + 1] != '#') {
        			startX++;
        			labyrinth[startY][startX] = '>';
        		}
        		break;
        	case '^':
    			if(labyrinth[startY][startX + 1] != '#') {
        			startX++;
        			labyrinth[startY][startX] = '>';                 	 
        		} else if (labyrinth[startY - 1][startX] != '#') {
        			startY--;
        			labyrinth[startY][startX] = '^';
        		} else if (labyrinth[startY + 1][startX] != '#') {
        			startY++;
        			labyrinth[startY][startX] = 'v';
        		} else if (labyrinth[startY][startX - 1] != '#') {
        			startX--;
        			labyrinth[startY][startX] = '<';
        		} 
        		break;
        	}
        	
	        try {
	        	Thread.sleep(150);
	        	} catch (InterruptedException e) {
	        		e.printStackTrace();
	        	}
	        
	        counter++;
	        displayLabyrinth(labyrinth);
	        System.out.println();
	        copyLabyrinth(startY, startX, copy, labyrinth);
	    } 
        print(counter, copy);
    }
    
    /*
     * Labyrinth kopieren
     */
    public static void copyLabyrinth(int startY, int startX, char[][] copy, char[][] labyrinth) {
        for(int i = startY; i < copy.length; i++) {
        	for (int j = startX; j < copy[i].length; j++) {
        		if (i == startY && j == startX) {
        			copy[i][j] = labyrinth[startY][startX];
        		}        		
        	}
        } 
    }
    
    /*
     * Ausgabe, nachdem das Spiel beendet wurde
     */
    public static void print(int counter, char[][] copy) {
        System.out.println();
        System.out.println("Glückwunsch BB-8! Du hast den Ausgang gefunden.");
        System.out.println();
        System.out.println("BB-8 hat " + counter + " Schritte benötigt.");
        System.out.println();
        System.out.println("So habe ich den Ausgang gefunden: ");
        
        for (char[] row : copy) {
            for (char cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }
}