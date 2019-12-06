
package States;

/**
 * PARA ELEGIR CON QUE NAVE SE VA A JUGAR 
 * @author Willy Bazan
 * @date   05/12/2019
 * @time   07:25 pm
 */

import Constants.Constant;
import Graphics.Assets;
import java.awt.Graphics;
import java.util.ArrayList;

import ui.Action;
import ui.Button;

public class ChooseShip extends State 
{
    private ArrayList<Button> buttons;
    private int index = 0;
    public static int avatar;
    
    public ChooseShip() {
        buttons = new ArrayList<Button>();
        
        /**
        for ( int i =0; i<4; i++)
        {
            for ( int j =0; j<5; j++)
            {
                buttons.add( new Button(
                        Assets.players[j+4*i],
                        Assets.players[j+4*i],
                        175*(j+1),
                        100*(i+1),
                        Constant.BACK,
                        new Action() {
                            @Override
                            public void doAction() 
                            {
                                ChooseShip.avatar = index;
                                State.changeState(new MenuState());
                            }
                        }));
                
                index++;
            }
        }
        */
        
        buttons.add( new Button(
                        Assets.players[0],
                        Assets.players[0],
                        180*(0+1),
                        100*(0+1),
                        Constant.BACK,
                        new Action() {
                            @Override
                            public void doAction() 
                            {
                                ChooseShip.avatar = 0;
                                State.changeState(new MenuState());
                            }
                        }));
        
        buttons.add( new Button(
                        Assets.players[1],
                        Assets.players[1],
                        180*(1+1),
                        100*(0+1),
                        Constant.BACK,
                        new Action() {
                            @Override
                            public void doAction() 
                            {
                                ChooseShip.avatar = 1;
                                State.changeState(new MenuState());
                            }
                        }));
        
        buttons.add( new Button(
                        Assets.players[2],
                        Assets.players[2],
                        180*(2+1),
                        100*(0+1),
                        Constant.BACK,
                        new Action() {
                            @Override
                            public void doAction() 
                            {
                                ChooseShip.avatar = 2;
                                State.changeState(new MenuState());
                            }
                        }));
        
        buttons.add( new Button(
                        Assets.players[3],
                        Assets.players[3],
                        180*(3+1),
                        100*(0+1),
                        Constant.BACK,
                        new Action() {
                            @Override
                            public void doAction() 
                            {
                                ChooseShip.avatar = 3;
                                State.changeState(new MenuState());
                            }
                        }));
        
        buttons.add( new Button(
                        Assets.players[4],
                        Assets.players[4],
                        180*(4+1),
                        100*(0+1),
                        Constant.BACK,
                        new Action() {
                            @Override
                            public void doAction() 
                            {
                                ChooseShip.avatar = 4;
                                State.changeState(new MenuState());
                            }
                        }));
        
        buttons.add( new Button(
                        Assets.players[5],
                        Assets.players[5],
                        180*(0+1),
                        100*(1+1),
                        Constant.BACK,
                        new Action() {
                            @Override
                            public void doAction() 
                            {
                                ChooseShip.avatar = 5;
                                State.changeState(new MenuState());
                            }
                        }));
        
        buttons.add( new Button(
                        Assets.players[6],
                        Assets.players[6],
                        180*(1+1),
                        100*(1+1),
                        Constant.BACK,
                        new Action() {
                            @Override
                            public void doAction() 
                            {
                                ChooseShip.avatar = 6;
                                State.changeState(new MenuState());
                            }
                        }));
        
        buttons.add( new Button(
                        Assets.players[7],
                        Assets.players[7],
                        180*(2+1),
                        100*(1+1),
                        Constant.BACK,
                        new Action() {
                            @Override
                            public void doAction() 
                            {
                                ChooseShip.avatar = 7;
                                State.changeState(new MenuState());
                            }
                        }));
        
        buttons.add( new Button(
                        Assets.players[8],
                        Assets.players[8],
                        180*(3+1),
                        100*(1+1),
                        Constant.BACK,
                        new Action() {
                            @Override
                            public void doAction() 
                            {
                                ChooseShip.avatar = 8;
                                State.changeState(new MenuState());
                            }
                        }));
        
        buttons.add( new Button(
                        Assets.players[9],
                        Assets.players[9],
                        180*(4+1),
                        100*(1+1),
                        Constant.BACK,
                        new Action() {
                            @Override
                            public void doAction() 
                            {
                                ChooseShip.avatar = 9;
                                State.changeState(new MenuState());
                            }
                        }));
        
        buttons.add( new Button(
                        Assets.players[10],
                        Assets.players[10],
                        180*(0+1),
                        100*(2+1),
                        Constant.BACK,
                        new Action() {
                            @Override
                            public void doAction() 
                            {
                                ChooseShip.avatar = 10;
                                State.changeState(new MenuState());
                            }
                        }));
        
        buttons.add( new Button(
                        Assets.players[11],
                        Assets.players[11],
                        180*(1+1),
                        100*(2+1),
                        Constant.BACK,
                        new Action() {
                            @Override
                            public void doAction() 
                            {
                                ChooseShip.avatar = 11;
                                State.changeState(new MenuState());
                            }
                        }));
        
        buttons.add( new Button(
                        Assets.players[12],
                        Assets.players[12],
                        180*(2+1),
                        100*(2+1),
                        Constant.BACK,
                        new Action() {
                            @Override
                            public void doAction() 
                            {
                                ChooseShip.avatar = 12;
                                State.changeState(new MenuState());
                            }
                        }));
        
        buttons.add( new Button(
                        Assets.players[13],
                        Assets.players[13],
                        180*(3+1),
                        100*(2+1),
                        Constant.BACK,
                        new Action() {
                            @Override
                            public void doAction() 
                            {
                                ChooseShip.avatar = 13;
                                State.changeState(new MenuState());
                            }
                        }));
        
        buttons.add( new Button(
                        Assets.players[14],
                        Assets.players[14],
                        180*(4+1),
                        100*(2+1),
                        Constant.BACK,
                        new Action() {
                            @Override
                            public void doAction() 
                            {
                                ChooseShip.avatar = 14;
                                State.changeState(new MenuState());
                            }
                        }));
        
        buttons.add( new Button(
                        Assets.players[15],
                        Assets.players[15],
                        180*(0+1),
                        100*(3+1),
                        Constant.BACK,
                        new Action() {
                            @Override
                            public void doAction() 
                            {
                                ChooseShip.avatar = 15;
                                State.changeState(new MenuState());
                            }
                        }));
        
        buttons.add( new Button(
                        Assets.players[16],
                        Assets.players[16],
                        180*(1+1),
                        100*(3+1),
                        Constant.BACK,
                        new Action() {
                            @Override
                            public void doAction() 
                            {
                                ChooseShip.avatar = 16;
                                State.changeState(new MenuState());
                            }
                        }));
        
        buttons.add( new Button(
                        Assets.players[17],
                        Assets.players[17],
                        180*(2+1),
                        100*(3+1),
                        Constant.BACK,
                        new Action() {
                            @Override
                            public void doAction() 
                            {
                                ChooseShip.avatar = 17;
                                State.changeState(new MenuState());
                            }
                        }));
        
        buttons.add( new Button(
                        Assets.players[18],
                        Assets.players[18],
                        180*(3+1),
                        100*(3+1),
                        Constant.BACK,
                        new Action() {
                            @Override
                            public void doAction() 
                            {
                                ChooseShip.avatar = 18;
                                State.changeState(new MenuState());
                            }
                        }));
        
        buttons.add( new Button(
                        Assets.players[19],
                        Assets.players[19],
                        180*(4+1),
                        100*(3+1),
                        Constant.BACK,
                        new Action() {
                            @Override
                            public void doAction() 
                            {
                                ChooseShip.avatar = 19;
                                State.changeState(new MenuState());
                            }
                        }));
    }
    
    
    
    @Override
    public void update() 
    {
        for(Button b: buttons) {
            b.update();
        }
    }

    @Override
    public void draw(Graphics g) 
    {
        for(Button b: buttons) {
            b.draw(g);
        }
    }
}
