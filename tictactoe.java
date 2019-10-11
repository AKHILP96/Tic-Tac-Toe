package tictactoe;
import java.util.*;

public class Main {
    public static void main(String[] args) {
      //  System.out.println("X O X\nO X O\nX X O");

      //Show the board
        Scanner s = new Scanner(System.in);
        //start game
        char[] c= new char[9];
        for(int i=0;i<9;i++){
          c[i] = ' ';
        }
       //print empty board\
       char next = 'X';
       printStart();
      // printBoard(c);
      boolean gameFinish = false;
      int count = 1;
      while(!gameFinish){
      //enter next move 
      int x = 0;
      int y = 0;
      System.out.println("Enter the coordinates:");
      //check if numbers are in integer format
        try{
          x = Integer.parseInt(s.next());
        }catch(NumberFormatException e){
          System.out.println("You should enter numbers!");
          continue;
        }
        try{
          y = Integer.parseInt(s.next());
        }catch(NumberFormatException e){
          System.out.println("You should enter numbers!");
          continue;
        }

        //check if the numbers are in range
        int pos = validatePos(x,y,c);
        if(pos == -1){
          continue;
        }else{
          c[pos] = next;
          printBoard(c);
          gameFinish = validateBoard(c);
          if(!gameFinish){
         //   printBoard(c);
            count++;
           // System.out.println(count);
          }

          if(gameFinish){
         //   printBoard(c);
            break;
          }
          next = next == 'X'?'O':'X';

          if(count>9){
            System.out.println("Draw");
            break;
          }
        }


      };
         // validate teh board
       //validateBoard(c);

    }

    //printboard 
    public static void printBoard(char[] c){
       System.out.println("---------");
       // System.out.println(c[0]);
       int temp =0;
        for(int i=0;i<3;i++){
          System.out.print("| ");
          int j=0;
          while(j<3){
            System.out.print(c[temp]+" ");
            j++;
            temp++;
          }
          System.out.println("|");
        }
         System.out.println("---------");

    }

    //starter board
    public static void printStart(){
      System.out.println("---------");
      for(int i=0;i<3;i++){
        System.out.println("|       |");
      }
      System.out.println("---------");
    }
    //validatePos
    public static int validatePos(int x,int y,char[] c)
    {
      if(x < 1 || x > 3 || y < 1 || y > 3 ){
        System.out.println("Coordinates should be from 1 to 3!");
        return -1;
      }
       int pos = 0;
        if(x == 1 &&  y == 3){
          pos = 0;
        }else if(x == 1 &&  y == 2){
          pos = 3;
        }else if(x == 1 &&  y == 1){
          pos = 6;
        }else if(x == 2 &&  y == 3){
          pos = 1;
        }else if(x == 2 &&  y == 2){
          pos = 4;
        }else if(x == 2 &&  y == 1){
          pos = 7;
        }else if(x == 3 &&  y == 3){
          pos = 2;
        }else if(x == 3 &&  y == 2){
          pos = 5;
        }else if(x == 3 &&  y == 1){
          pos = 8;
        }

        //check if that cell is empty
        if(c[pos] == 'X' || c[pos] == 'O'){
          System.out.println("This cell is occupied! Choose another one!");
          return -1;
        }else{
          return pos;
        }

    }



    //validate board
    public static boolean validateBoard(char[] c){
       boolean xwin = false;
        boolean owin = false;
          xwin = win(c,'X');
          owin = win(c,'O');

        int countX = count(c,'X');
        int countO = count(c,'O');
        //if x are 3 and o are 3 in a row then its not possible.
        if(owin == xwin && owin == true && xwin == true){
            System.out.println("Impossible");
            return true;
        }else if(countX==countO){
          if(xwin){
            System.out.println("X wins");
            return true;
          }
          else if(owin){
            System.out.println("O wins");
            return true;
          }else {
           // System.out.println("Game not finished");
            return false;
          }
        }else if(countX - 1 == countO && xwin){
            System.out.println("X wins");
            return true;
        }else if(countO - 1 == countX  && owin){
            System.out.println("O wins");
            return true;
        }
        else if(countX-1>countO ){
            System.out.println("Impossible");
            return true;
        }else if(countX<countO-1 ){
            System.out.println("Impossible");
            return true;
        } 
        return false;   
    }

    //check if x win or o wins
    public static boolean win(char[] c,char win){

      for(int i=0;i<c.length;i+=3){
        if(c[i] == win && c[i+1] == win &&c[i+2] == win){
       //   System.out.println("1"+win);
            return true;
        }
      }
      for(int i=0;i<3;i++){
        if(c[i] == win && c[i+3] == win && c[i+6] == win){
       //   System.out.println("2"+win);
          return true;
        }
      }
      if(c[0] == win &&c[4] == win && c[8] == win){
     //   System.out.println("3"+win);
        return true;
      }
      if(c[2] == win && c[4] == win && c[6] == win){
    //    System.out.println("4"+win);
        return true;
      }

      return false;
    }

    public static int count(char c[],char win){
      int count = 0;
      for(int i=0;i<c.length;i++){
          if(c[i] == win){
            count++;
          }
      }
      return count;
    }
}

