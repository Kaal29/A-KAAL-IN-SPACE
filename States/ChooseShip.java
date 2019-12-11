
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
        
        buttons.add(new Button(
                        Assets.greyBtn,
                        Assets.blueBtn,
                        Constant.WIDTH/2-Assets.greyBtn.getWidth()/2,
                        Constant.HEIGHT - Assets.greyBtn.getHeight() * 2,
                        Constant.RETURN,
                        new Action() 
                        {
                            @Override
                            public void doAction() {
                                State.changeState(new MenuState());
                            }
                        }
                ));
        
        buttons.add( new Button(
                        Assets.ships[0],
                        Assets.shipsSeleccion[0],
                        180*(0+1),
                        100*(0+1),
                        "",
                        new Action() {
                            @Override
                            public void doAction() 
                            {
                                ChooseShip.avatar = 0;
                                State.changeState(new MenuState());
                            }
                        }));
        
        buttons.add( new Button(
                        Assets.ships[1],
                        Assets.shipsSeleccion[1],
                        180*(1+1),
                        100*(0+1),
                        "",
                        new Action() {
                            @Override
                            public void doAction() 
                            {
                                ChooseShip.avatar = 1;
                                State.changeState(new MenuState());
                            }
                        }));
        
        buttons.add( new Button(
                        Assets.ships[2],
                        Assets.shipsSeleccion[2],
                        180*(2+1),
                        100*(0+1),
                        "",
                        new Action() {
                            @Override
                            public void doAction() 
                            {
                                ChooseShip.avatar = 2;
                                State.changeState(new MenuState());
                            }
                        }));
        
        buttons.add( new Button(
                        Assets.ships[3],
                        Assets.shipsSeleccion[3],
                        180*(3+1),
                        100*(0+1),
                        "",
                        new Action() {
                            @Override
                            public void doAction() 
                            {
                                ChooseShip.avatar = 3;
                                State.changeState(new MenuState());
                            }
                        }));
        
        buttons.add( new Button(
                        Assets.ships[4],
                        Assets.shipsSeleccion[4],
                        180*(4+1),
                        100*(0+1),
                        "",
                        new Action() {
                            @Override
                            public void doAction() 
                            {
                                ChooseShip.avatar = 4;
                                State.changeState(new MenuState());
                            }
                        }));
        
        buttons.add( new Button(
                        Assets.ships[5],
                        Assets.shipsSeleccion[5],
                        180*(0+1),
                        100*(1+1),
                        "",
                        new Action() {
                            @Override
                            public void doAction() 
                            {
                                ChooseShip.avatar = 5;
                                State.changeState(new MenuState());
                            }
                        }));
        
        buttons.add( new Button(
                        Assets.ships[6],
                        Assets.shipsSeleccion[6],
                        180*(1+1),
                        100*(1+1),
                        "",
                        new Action() {
                            @Override
                            public void doAction() 
                            {
                                ChooseShip.avatar = 6;
                                State.changeState(new MenuState());
                            }
                        }));
        
        buttons.add( new Button(
                        Assets.ships[7],
                        Assets.shipsSeleccion[7],
                        180*(2+1),
                        100*(1+1),
                        "",
                        new Action() {
                            @Override
                            public void doAction() 
                            {
                                ChooseShip.avatar = 7;
                                State.changeState(new MenuState());
                            }
                        }));
        
        buttons.add( new Button(
                        Assets.ships[8],
                        Assets.shipsSeleccion[8],
                        180*(3+1),
                        100*(1+1),
                        "",
                        new Action() {
                            @Override
                            public void doAction() 
                            {
                                ChooseShip.avatar = 8;
                                State.changeState(new MenuState());
                            }
                        }));
        
        buttons.add( new Button(
                        Assets.ships[9],
                        Assets.shipsSeleccion[9],
                        180*(4+1),
                        100*(1+1),
                        "",
                        new Action() {
                            @Override
                            public void doAction() 
                            {
                                ChooseShip.avatar = 9;
                                State.changeState(new MenuState());
                            }
                        }));
        
        buttons.add( new Button(
                        Assets.ships[10],
                        Assets.shipsSeleccion[10],
                        180*(0+1),
                        100*(2+1),
                        "",
                        new Action() {
                            @Override
                            public void doAction() 
                            {
                                ChooseShip.avatar = 10;
                                State.changeState(new MenuState());
                            }
                        }));
        
        buttons.add( new Button(
                        Assets.ships[11],
                        Assets.shipsSeleccion[11],
                        180*(1+1),
                        100*(2+1),
                        "",
                        new Action() {
                            @Override
                            public void doAction() 
                            {
                                ChooseShip.avatar = 11;
                                State.changeState(new MenuState());
                            }
                        }));
        
        buttons.add( new Button(
                        Assets.ships[12],
                        Assets.shipsSeleccion[12],
                        180*(2+1),
                        100*(2+1),
                        "",
                        new Action() {
                            @Override
                            public void doAction() 
                            {
                                ChooseShip.avatar = 12;
                                State.changeState(new MenuState());
                            }
                        }));
        
        buttons.add( new Button(
                        Assets.ships[13],
                        Assets.shipsSeleccion[13],
                        180*(3+1),
                        100*(2+1),
                        "",
                        new Action() {
                            @Override
                            public void doAction() 
                            {
                                ChooseShip.avatar = 13;
                                State.changeState(new MenuState());
                            }
                        }));
        
        buttons.add( new Button(
                        Assets.ships[14],
                        Assets.shipsSeleccion[14],
                        180*(4+1),
                        100*(2+1),
                        "",
                        new Action() {
                            @Override
                            public void doAction() 
                            {
                                ChooseShip.avatar = 14;
                                State.changeState(new MenuState());
                            }
                        }));
        
        buttons.add( new Button(
                        Assets.ships[15],
                        Assets.shipsSeleccion[15],
                        180*(0+1),
                        100*(3+1),
                        "",
                        new Action() {
                            @Override
                            public void doAction() 
                            {
                                ChooseShip.avatar = 15;
                                State.changeState(new MenuState());
                            }
                        }));
        
        buttons.add( new Button(
                        Assets.ships[16],
                        Assets.shipsSeleccion[16],
                        180*(1+1),
                        100*(3+1),
                        "",
                        new Action() {
                            @Override
                            public void doAction() 
                            {
                                ChooseShip.avatar = 16;
                                State.changeState(new MenuState());
                            }
                        }));
        
        buttons.add( new Button(
                        Assets.ships[17],
                        Assets.shipsSeleccion[17],
                        180*(2+1),
                        100*(3+1),
                        "",
                        new Action() {
                            @Override
                            public void doAction() 
                            {
                                ChooseShip.avatar = 17;
                                State.changeState(new MenuState());
                            }
                        }));
        
        buttons.add( new Button(
                        Assets.ships[18],
                        Assets.shipsSeleccion[18],
                        180*(3+1),
                        100*(3+1),
                        "",
                        new Action() {
                            @Override
                            public void doAction() 
                            {
                                ChooseShip.avatar = 18;
                                State.changeState(new MenuState());
                            }
                        }));
        
        buttons.add( new Button(
                        Assets.ships[19],
                        Assets.shipsSeleccion[19],
                        180*(4+1),
                        100*(3+1),
                        "",
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
