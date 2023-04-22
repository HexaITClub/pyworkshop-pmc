import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

public class HolesAndHurkles 
{
    final static int MAX_WIDTH = 30;
    final static int MAX_HEIGHT = 30;

    final static int HOLE_COUNT = 20;

    static int playerX = 0;
    static int playerY = 0;

    static int board[][] = new int[MAX_WIDTH][MAX_HEIGHT];

    static int holes[][];
    static int hurkle[];

    static int score = 0;

    public static void main(String[] args) {
        holes = generateRandomHoles();
        hurkle = generateRandomHurklePos();

        Scanner scan = new Scanner(System.in);

        boolean gameFinished = false;
        while(true)
        {
            printBoard();
            System.out.print(">");
            char dir = scan.next().charAt(0);
            int steps = scan.nextInt();

            playerMover:
            for(int i = 0; i < (int) steps; i++)
            {
                moveOneStep(dir);
                for(int j = 0; j < holes.length; j++)
                {
                    if((playerX == holes[j][0]) && (playerY == holes[j][1]))
                    {
                        System.out.println("You fell into hole!\n");
                        playerX = 0;
                        playerY = 0;
                        break playerMover;
                    }
                }
            }

            // check if player is in the same position as hurkle
            if((playerX == hurkle[0]) && (playerY == hurkle[1]))
            {
                System.out.println("Hurkle found at position: " + playerX + ", " + playerY);
                System.out.println("You win!");
                gameFinished = true;
            }

            if(gameFinished)
            {
                System.out.println("Score: " + (++score));
                break;
            }
            else 
            {
                System.out.println("The hurkle is: " + getHint());
                System.out.println("Score: " + (++score));
            }
        }
        scan.close();
    }
    
    public static void printBoard()
    {
        for(int i = 0; i < MAX_WIDTH; i++)
        {
            for(int j = 0; j < MAX_HEIGHT; j++)
            {
                board[i][j] = '.';
            }
        }

        board[playerX][playerY] = '@';

        for(int i = 0; i < holes.length; i++)
        {
            board[holes[i][0]][holes[i][1]] = '0';
        }

        for(int i = 0; i < MAX_WIDTH; i++)
        {
            for(int j = 0; j < MAX_HEIGHT; j++)
            {
                System.out.printf("%c", board[i][j]);
            }
            System.out.println();
        }
    }

    public static int[] generateRandomHurklePos()
    {
        int random[] = new int[2];
        int x = generateRandomNonZero(0, 10);
        int y = generateRandomNonZero(0, 10);

        for(int i = 0; i < HOLE_COUNT; i++)
        {
            int holeX = holes[i][0];
            int holeY = holes[i][1];

            if((x == holeX) && (y == holeY))
            {
                x = generateRandomNonZero(0, MAX_WIDTH);
                y = generateRandomNonZero(0, MAX_HEIGHT);
            }
        }

        random[0] = x;
        random[1] = y;
        return random;
    }

    public static int generateRandomNonZero(int min, int max)
    {
        int x = 0;
        do {
            x = new Random().nextInt(max - min) + min;
        } while(x == 0);

        return x;
    }

    public static int[][] generateRandomHoles()
    {
        int random[][] = new int[HOLE_COUNT][2];

        List<Integer> holesX = new ArrayList<>();
        Set<Integer> holesY = new HashSet<>();
        do {
            int x = 0;
            int y = 0;
            do {
                x = new Random().nextInt(MAX_WIDTH);
                y = new Random().nextInt(MAX_HEIGHT);
            } while((x == 0) && (y == 0));

            holesX.add(x);
            holesY.add(y);
        } while((holesX.size() < HOLE_COUNT) || (holesY.size() < HOLE_COUNT));

        List<Integer> listX = new ArrayList<>(holesX);
        List<Integer> listY = new ArrayList<>(holesY);

        for(int i = 0; i < HOLE_COUNT; i++)
        {
            random[i][0] = listX.get(i);
            random[i][1] = listY.get(i);
        }
        return random;
    }
    
    public static String getHint()
    {
        String dir = "";
        if(hurkle[0] < playerX)
            dir += "N";
        
        if(hurkle[0] > playerX)
            dir += "S";

        if(hurkle[1] < playerY)
            dir += "W";
        
        if(hurkle[1] > playerY)
            dir += "E";

        return dir;
    }

    public static void moveOneStep(char dir)
    {
        switch(dir)
        {
            case 'e':
            case 'E':
            playerY++;
            if(playerY >= MAX_WIDTH)
            {
                playerY = MAX_WIDTH - 1;
            }
            break;

            case 'w':
            case 'W':
            playerY--;
            if(playerY < 0)
            {
                playerY = 0;
            }
            break;

            case 'n':
            case 'N':
            playerX--;
            if(playerX < 0)
            {
                playerX = 0;
            }
            break;

            case 's':
            case 'S':
            playerX++;
            if(playerX >= MAX_HEIGHT)
            {
                playerX = MAX_HEIGHT - 1;
            }
            break;

            case 'q':
            case 'Q':
            System.out.println("You lost!");
            System.exit(1);
            break;

            default:
            System.out.println("Not a valid direction!");
            break;
        }
    }
}
