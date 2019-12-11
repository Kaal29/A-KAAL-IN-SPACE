
package States;

/**
 * CREA LA INTERFAZ
 * @author Willy Bazan
 * @date   04/12/2019
 * @time   09:39 pm
 */

import java.awt.Graphics;
import java.util.ArrayList;

import Constants.Constant;
import Graphics.Assets;
import ui.Action;
import ui.Button;

public class MenuState extends State{
	
    private ArrayList<Button> buttons;

    public MenuState() {
        buttons = new ArrayList<Button>();
        
        //*************************************************************************
        buttons.add(new Button(
                        Assets.greyBtn,
                        Assets.blueBtn,
                        Constant.WIDTH / 2 - Assets.greyBtn.getWidth()/2,
                        Constant.HEIGHT / 2 - Assets.greyBtn.getHeight()*5,
                        Constant.PLAY,
                        new Action() {
                                @Override
                                public void doAction() {
                                        State.changeState(new GameState());
                                }
                        }
                        ));

        //***********************************************************************************
        buttons.add(new Button(
				Assets.greyBtn,
				Assets.blueBtn,
				Constant.WIDTH / 2 - Assets.greyBtn.getWidth()/2,
				Constant.HEIGHT / 2 - Assets.greyBtn.getHeight()*3,
				Constant.CHOOSE_SHIP,
				new Action() {
					@Override
					public void doAction() {
						State.changeState(new ChooseShip());
					}
				}
				));
        
        //***********************************************************************************
        buttons.add(new Button(
				Assets.greyBtn,
				Assets.blueBtn,
				Constant.WIDTH / 2 - Assets.greyBtn.getWidth()/2,
				Constant.HEIGHT / 2 - Assets.greyBtn.getHeight()*1,
				Constant.CHOOSE_STAGE,
				new Action() {
					@Override
					public void doAction() {
                                            State.changeState(new ChooseStage());
                                            
					}
				}
				));
        
        //************************************************************************************
        
        buttons.add(new Button(
				Assets.greyBtn,
				Assets.blueBtn,
				Constant.WIDTH / 2 - Assets.greyBtn.getWidth()/2,
				Constant.HEIGHT /2 + Assets.greyBtn.getHeight()*1,
				Constant.HIGH_SCORES,
				new Action() {
					@Override
					public void doAction() {
						State.changeState(new ScoreState());
					}
				}
				));
        //************************************************************************************
        
        buttons.add(new Button(
                        Assets.greyBtn,
                        Assets.blueBtn,
                        Constant.WIDTH / 2 - Assets.greyBtn.getWidth()/2,
                        Constant.HEIGHT / 2 + Assets.greyBtn.getHeight()*3 ,
                        Constant.EXIT,
                        new Action() {
                                @Override
                                public void doAction() {
                                        System.exit(0);
                                }
                        }
                        ));
    }

    @Override
    public void update() {
        for(Button b: buttons) {
            b.update();
        }
    }

    @Override
    public void draw(Graphics g) {
        for(Button b: buttons) {
            b.draw(g);
        }
    }

}
