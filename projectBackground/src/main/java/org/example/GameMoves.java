package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// This is the class for the the game moves
class GameMoves extends JPanel implements KeyListener, ActionListener,MouseListener{
    private static final long serialVersionUID = 1L;
    org.example.Layout la; // This is the object for the layout class
    Build_Player p; // This is the object for the build player class
    Timer time; // This is the object for the timer class
    int delay=10; // This is the delay for the timer
    int current_player,dice; // This is the variable for the current player and the dice
    int flag=0,roll,kill=0; // This is the flag for the dice roll and the kill variable

    public GameMoves() {
        setFocusTraversalKeysEnabled(false);
        requestFocus();
        current_player=0; // first player is set to red
        la = new Layout(80,50);
        p=new Build_Player(la.height,la.width);
        dice=0;
        flag=0;
        roll=0;
        kill=0;
    }

    // This is the method for drawing the game

    public void paint(Graphics g) {
        la.draw((Graphics2D)g);
        p.draw((Graphics2D)g);
        if(p.pl[current_player].coin==4) { // This is the condition for the winner
            g.setColor(Color.WHITE);
            g.fillRect(590, 100, 380,130);
            if(current_player==0) {
                g.setColor(Color.RED);
            }
            else if(current_player==1) {
                g.setColor(Color.GREEN);
            }
            else if(current_player==2) {
                g.setColor(Color.YELLOW);
            }
            else if(current_player==3) {
                g.setColor(Color.BLUE);
            }
            g.setFont(new Font("serif", Font.BOLD, 40));
            g.drawString("Player "+(current_player+1)+" wins.", 600, 350);
            g.drawString("Congratulations.", 600, 400);
            current_player=0;
            la = new Layout(80,50);
            p=new Build_Player(la.height,la.width);
            dice=0;
            flag=0;
            roll=0;
            kill=0;
        }
        else if(dice!=0) { // This is the condition for the dice roll

            g.setColor(Color.pink);
            g.fillRect(590, 300, 260,200);
            if(current_player==0) {
                g.setColor(Color.RED);
            }
            else if(current_player==1) {
                g.setColor(Color.GREEN);
            }
            else if(current_player==2) {
                g.setColor(Color.YELLOW);
            }
            else if(current_player==3) {
                g.setColor(Color.BLUE);
            }

            g.setFont(new Font("MV Boli", Font.BOLD, 30));
            if(current_player==0){
                g.drawString("RED's turn:", 600, 350);
            }
            else if(current_player==1){
                g.drawString("GREEN's turn:", 600, 350);
            }
            else if(current_player==2){
                g.drawString("YELLOW's turn:", 600, 350);
            }
            else if(current_player==3){
                g.drawString("BLUE's turn:", 600, 350);
            }

            // draw dice
            g.setColor(Color.BLACK);
            g.drawRect(670, 370, 100, 100);
            g.setFont(new Font("MV Boli", Font.BOLD, 50));
            g.drawString(""+dice, 700, 440);
        }
        if(flag==0&&dice!=0&&dice!=6&&kill==0) {
            current_player=(current_player+1)%4;
        }
        kill=0;
    }


    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER&&flag==0) { // if enter is pressed the dice is rolled
            roll=0;
            dice=1 + (int)(Math.random() * 6); // random number is generated for the dice roll
            repaint();
            for(int i=0;i<4;i++) {
                if(p.pl[current_player].pa[i].current!=-1&&p.pl[current_player].pa[i].current!=56&&(p.pl[current_player].pa[i].current+dice)<=56) {
                    flag=1;
                    break;
                }
            }
            if(flag==0&&dice==6) {
                for(int i=0;i<4;i++) {
                    if(p.pl[current_player].pa[i].current==-1) {
                        flag=1;
                        break;
                    }
                }
            }
        }
    }

    public void mouseClicked(MouseEvent e) {
        if(flag==1) {
            int x=e.getX();
            int y=e.getY();
            x=x-80;
            y=y-50;
            x=x/30;
            y=y/30;
            int value=-1;
            //System.out.println(x+" "+y);
            if(dice==6) { // if dice is 6
                for(int i=0;i<4;i++) {
                    if(p.pl[current_player].pa[i].x==x&&p.pl[current_player].pa[i].y==y&&(p.pl[current_player].pa[i].current+dice)<=56) {
                        value=i;
                        flag=0;
                        break;
                    }
                }
                if(value!=-1) { // if the player has a coin on the clicked position
                    p.pl[current_player].pa[value].current+=dice;
                    if(p.pl[current_player].pa[value].current==56) {
                        p.pl[current_player].coin++;
                    }
                    int k=0;
                    int hou=p.pl[current_player].pa[value].current;
                    if((hou%13)!=0&&(hou%13)!=8&&hou<51)
                    {
                        for(int i=0;i<4;i++) {
                            if(i!=current_player) {
                                for(int j=0;j<4;j++) {
                                    int tem1= Path.ax[current_player][p.pl[current_player].pa[value].current],tem2= Path.ay[current_player][p.pl[current_player].pa[value].current];
                                    if(p.pl[i].pa[j].x==tem1&&p.pl[i].pa[j].y==tem2) {
                                        p.pl[i].pa[j].current=-1;
                                        kill=1;
                                        k=1;
                                        break;
                                    }
                                }
                            }
                            if(k==1)
                                break;
                        }
                    }
                }
                else { // if the player does not have a coin on the clicked position
                    for(int i=0;i<4;i++) {
                        if(p.pl[current_player].pa[i].current==-1) {
                            p.pl[current_player].pa[i].current=0;
                            flag=0;
                            break;
                        }
                    }
                }
            }
            else { // if dice is not 6
                for(int i=0;i<4;i++) {
                    if(p.pl[current_player].pa[i].x==x&&p.pl[current_player].pa[i].y==y&&(p.pl[current_player].pa[i].current+dice)<=56) {
                        value=i;
                        flag=0;
                        break;
                    }
                }
                if(value!=-1) { // if the player has a coin on the clicked position
                    p.pl[current_player].pa[value].current+=dice;
                    if(p.pl[current_player].pa[value].current==56) {
                        p.pl[current_player].coin++;
                    }
                    int k=0;
                    int hou=p.pl[current_player].pa[value].current;
                    if((hou%13)!=0&&(hou%13)!=8&&hou<51)
                    {
                        for(int i=0;i<4;i++) {
                            if(i!=current_player) {
                                for(int j=0;j<4;j++) {
                                    int tem1= Path.ax[current_player][p.pl[current_player].pa[value].current],tem2= Path.ay[current_player][p.pl[current_player].pa[value].current];
                                    if(p.pl[i].pa[j].x==tem1&&p.pl[i].pa[j].y==tem2) {
                                        p.pl[i].pa[j].current=-1;
                                        kill=1;
                                        k=1;
                                        break;
                                    }
                                }
                            }
                            if(k==1)
                                break;
                        }
                    }
                }
            }
            repaint();
        }
    }

    // these functions are not used but are compulsory to be defined as they are abstract functions of MouseListener
    public void actionPerformed(ActionEvent e) {
    }


    public void keyReleased(KeyEvent arg0) {
    }

    public void keyTyped(KeyEvent arg0) {
    }

    public void mouseEntered(MouseEvent arg0) {
    }

    public void mouseExited(MouseEvent arg0) {
    }


    public void mousePressed(MouseEvent e) {
    }


    public void mouseReleased(MouseEvent arg0) {
    }
}
