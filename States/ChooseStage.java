
package States;

import Constants.Constant;
import Graphics.Assets;
import java.awt.Graphics;
import java.util.ArrayList;
import ui.Action;
import ui.Button;

/**
 * PARA ELEGIR EL ESCENARIO EN EL QUE SE VA A JUGAR 
 * @author Willy Bazan
 * @date   07/12/2019
 * @time   09:17 pm
 */

public class ChooseStage extends State
{
    private ArrayList<Button> buttons;
    private int index = 0;
    public static int stage;
    
    public ChooseStage() {
        buttons = new ArrayList<Button>();
        
        buttons.add(new Button(
                        Assets.greyBtn,
                        Assets.blueBtn,
                        Constant.WIDTH-Assets.greyBtn.getWidth()*2,
                        Constant.HEIGHT - Assets.greyBtn.getHeight() * 3,
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
                        Assets.stageNormal[0],
                        Assets.stageSeleccionado[0],
                        20,
                        20,
                        "",
                        new Action() {
                            @Override
                            public void doAction() 
                            {
                                ChooseStage.stage = 0;
                                State.changeState(new MenuState());
                            }
                        }));
        
        buttons.add( new Button(
                        Assets.stageNormal[1],
                        Assets.stageSeleccionado[1],
                        300,
                        20,
                        "",
                        new Action() {
                            @Override
                            public void doAction() 
                            {
                                ChooseStage.stage = 1;
                                State.changeState(new MenuState());
                            }
                        }));
        
        buttons.add( new Button(
                        Assets.stageNormal[2],
                        Assets.stageSeleccionado[2],
                        600,
                        20,
                        "",
                        new Action() {
                            @Override
                            public void doAction() 
                            {
                                ChooseStage.stage = 2;
                                State.changeState(new MenuState());
                            }
                        }));
        
        buttons.add( new Button(
                        Assets.stageNormal[3],
                        Assets.stageSeleccionado[3],
                        900,
                        20,
                        "",
                        new Action() {
                            @Override
                            public void doAction() 
                            {
                                ChooseStage.stage = 3;
                                State.changeState(new MenuState());
                            }
                        }));
        
        buttons.add( new Button(
                        Assets.stageNormal[4],
                        Assets.stageSeleccionado[4],
                        20,
                        200,
                        "",
                        new Action() {
                            @Override
                            public void doAction() 
                            {
                                ChooseStage.stage = 4;
                                State.changeState(new MenuState());
                            }
                        }));
        
        buttons.add( new Button(
                        Assets.stageNormal[5],
                        Assets.stageSeleccionado[5],
                        300,
                        200,
                        "",
                        new Action() {
                            @Override
                            public void doAction() 
                            {
                                ChooseStage.stage = 5;
                                State.changeState(new MenuState());
                            }
                        }));
        
        buttons.add( new Button(
                        Assets.stageNormal[6],
                        Assets.stageSeleccionado[6],
                        600,
                        200,
                        "",
                        new Action() {
                            @Override
                            public void doAction() 
                            {
                                ChooseStage.stage = 6;
                                State.changeState(new MenuState());
                            }
                        }));
        
        buttons.add( new Button(
                        Assets.stageNormal[7],
                        Assets.stageSeleccionado[7],
                        900,
                        200,
                        "",
                        new Action() {
                            @Override
                            public void doAction() 
                            {
                                ChooseStage.stage = 7;
                                State.changeState(new MenuState());
                            }
                        }));
        
        buttons.add( new Button(
                        Assets.stageNormal[8],
                        Assets.stageSeleccionado[8],
                        20,
                        400,
                        "",
                        new Action() {
                            @Override
                            public void doAction() 
                            {
                                ChooseStage.stage = 8;
                                State.changeState(new MenuState());
                            }
                        }));
        
        buttons.add( new Button(
                        Assets.stageNormal[9],
                        Assets.stageSeleccionado[9],
                        300,
                        400,
                        "",
                        new Action() {
                            @Override
                            public void doAction() 
                            {
                                ChooseStage.stage = 9;
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
