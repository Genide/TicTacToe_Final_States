//Daniel Nguyen 1084443

package com.company;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Arrays;

public class Main {
    public static int win1=0;
    public static int win2=0;
    public static int draw=0;
    public static void main(String[] args) {
	// write your code here
        Scanner reader = new Scanner(System.in);
        System.out.println("Enter 6 digit seed.");
        int num=reader.nextInt();

        int num1=0;
        int num2=0;
        int num3=0;
        ArrayList<int[]> finalStates = new ArrayList<int[]>();
        Queue<int[]> boardQueue = new LinkedList<int[]>();

        num3 = num%100;
        num = num/100;
        num2 = num%100;
        num = num/100;
        num1 = num%100;

        num1 = num1 % 9;
        num2 = num2 % 9;
        num3 = num3 % 9;

        if(num1==num2 && num2==num3) {
            num2 = (num2+1)%9;
            num3 = (num3+2)%9;
        }else if(num1==num2) {
            num2 = (num2+1)%9;
            if(num2==num3){
                num2 = (num2+1)%9;
            }
        }else if(num1==num3) {
            num3 = (num3+1)%9;
            if(num2==num3){
                num3 = (num3+1)%9;
            }
        }else if(num2==num3) {
            num3 = (num3+1)%9;
            if(num1==num3){
                num3 = (num3+1)%9;
            }
        }

        System.out.println(num1 + " " + num2 + " " + num3);

        //Create the board.
        //1 is X
        //2 is O
        //0 is empty
        int[] board;
        board = new int[9];
        for(int i=0;i<9;i++){
            board[i]=0;
        }
        //Give the board it's initial positions.
        //num1=2;
        //num2=8;
        //num3=6;
        board[num1]=1;
        board[num2]=2;
        board[num3]=1;

        printBoard((board));

        //Now let's go through the steps and see how many wins and draws there are
        //nextStepDFS(board,finalStates);
        nextStepBFS(board,finalStates,boardQueue);
        System.out.println("X Wins : " + win1);
        System.out.println("O Wins : " + win2);
        System.out.println("Draws  : " + draw);

        printfinalStates(finalStates);
    }

    public static void nextStepDFS(int[] board, ArrayList<int[]> finalStates){
        int marker=0; //This variable will serve as the next move

        for(int i=0;i<9;i++){
            //This section determines which marker is going to be placed by counting the number of 1's and 2's
            if(countOne(board)==countTwo(board))
                marker = 1;
            else
                marker = 2;

            if(board[i]==0){
                board[i]=marker;
                if(checkWin1(board)) {
                    //printBoard((board));
                    //System.out.println("X wins");
                    //if(!infinalStates(board,finalStates)){
                        win1++;
                        int[] newBoard = board.clone();
                        finalStates.add(newBoard);
                        //System.out.println("board added");
                    //}
                }
                else if(checkWin2(board)) {
                    //printBoard((board));
                    //System.out.println("O wins");
                    //if(!infinalStates(board,finalStates)){
                        win2++;
                        int[] newBoard = board.clone();
                        finalStates.add(newBoard);
                        //System.out.println("board added");
                    //}
                }
                else if(fullBoard(board)) {
                    //printBoard((board));
                    //System.out.println("Draw");
                    //if(!infinalStates(board,finalStates)){
                        draw++;
                        int[] newBoard = board.clone();
                        finalStates.add(newBoard);
                        //System.out.println("board added");
                    //}
                }
                else {
                    int[] newBoard = board.clone();
                    //printBoard(newBoard);
                    nextStepDFS(newBoard,finalStates);
                }
                board[i]=0;
            }
        }
    }
    public static void nextStepBFS(int[] board, ArrayList<int[]> finalStates, Queue<int[]> boardQueue){
        int marker=0; //This variable will serve as the next move

        for(int i=0;i<9;i++){
            //This section determines which marker is going to be placed by counting the number of 1's and 2's
            if(countOne(board)==countTwo(board))
                marker = 1;
            else
                marker = 2;

            if(board[i]==0){
                board[i]=marker;
                if(checkWin1(board)) {
                    //printBoard((board));
                    //System.out.println("X wins");
                    //if(!infinalStates(board,finalStates)){
                        win1++;
                        int[] newBoard = board.clone();
                        finalStates.add(newBoard);
                        //System.out.println("board added");
                    //}
                }
                else if(checkWin2(board)) {
                    //printBoard((board));
                    //System.out.println("O wins");
                    //if(!infinalStates(board,finalStates)){
                        win2++;
                        int[] newBoard = board.clone();
                        finalStates.add(newBoard);
                        //System.out.println("board added");
                    //}
                }
                else if(fullBoard(board)) {
                    //printBoard((board));
                    //System.out.println("Draw");
                    //if(!infinalStates(board,finalStates)){
                        draw++;
                        int[] newBoard = board.clone();
                        finalStates.add(newBoard);
                        //System.out.println("board added");
                    //}
                }
                else {
                    int[] newBoard = board.clone();
                    //printBoard(newBoard);
                    boardQueue.add(newBoard);
                }
                board[i]=0;
            }
        }
        if(!boardQueue.isEmpty()){
            nextStepBFS(boardQueue.remove(),finalStates,boardQueue);
        }
    }

    public static int countOne(int[] board){
        int count = 0;
        for(int i=0;i<9;i++){
            if(board[i]==1){
                count++;
            }
        }
        return count;
    }
    public static int countTwo(int[] board){
        int count = 0;
        for(int i=0;i<9;i++){
            if(board[i]==2){
                count++;
            }
        }
        return count;
    }

    public static boolean checkWin1(int[] board){
        //check row win
        if((board[0]==1&&board[1]==1&&board[2]==1)||(board[3]==1&&board[4]==1&&board[5]==1)||(board[6]==1&&board[7]==1&&board[8]==1))
            return true;
        //check column win
        if((board[0]==1&&board[3]==1&&board[6]==1)||(board[1]==1&&board[4]==1&&board[7]==1)||(board[2]==1&&board[5]==1&&board[8]==1))
            return true;
        //check diagonal win
        if((board[0]==1&&board[4]==1&&board[8]==1)||(board[2]==1&&board[4]==1&&board[6]==1))
            return true;
        return false;
    }
    public static boolean checkWin2(int[] board){
        if((board[0]==2&&board[1]==2&&board[2]==2)||(board[3]==2&&board[4]==2&&board[5]==2)||(board[6]==2&&board[7]==2&&board[8]==2))
            return true;
        //check column win
        if((board[0]==2&&board[3]==2&&board[6]==2)||(board[1]==2&&board[4]==2&&board[7]==2)||(board[2]==2&&board[5]==2&&board[8]==2))
            return true;
        //check diagonal win
        if((board[0]==2&&board[4]==2&&board[8]==2)||(board[2]==2&&board[4]==2&&board[6]==2))
            return true;
        return false;
    }
    public static boolean fullBoard(int[] board){
        for(int i=0;i<9;i++){
            if(board[i]==0)
                return false;
        }
        return true;
    }

    public static void printBoard(int[] board){
        for(int i=0;i<3;i++){
            if(board[i]==0)
                System.out.print("E");
            else if(board[i]==1)
                System.out.print("X");
            else if(board[i]==2)
                System.out.print("O");
        }
        System.out.println();
        for(int i=3;i<6;i++){
            if(board[i]==0)
                System.out.print("E");
            else if(board[i]==1)
                System.out.print("X");
            else if(board[i]==2)
                System.out.print("O");
        }
        System.out.println();
        for(int i=6;i<9;i++) {
            if(board[i]==0)
                System.out.print("E");
            else if(board[i]==1)
                System.out.print("X");
            else if(board[i]==2)
                System.out.print("O");
        }
        System.out.println();
    }
    public static boolean infinalStates(int []board, ArrayList<int[]> finalStates){
        for(int i=0;i<finalStates.size();i++){
            if(Arrays.equals(board,finalStates.get(i)))
                return true;
        }
        return false;
    }

    public static void printfinalStates(ArrayList<int[]> finalStates){
        System.out.println("The final states are : ");
        int[] test;
        test = finalStates.get(0);
        for(int i=0;i<finalStates.size();i++){
            printBoard(finalStates.get(i));
            if(checkWin1(finalStates.get(i)))
                System.out.print("X Wins");
            else if(checkWin2(finalStates.get(i)))
                System.out.print("O Wins");
            else
                System.out.print("Draw");
            System.out.println("\n");
        }
    }
}

