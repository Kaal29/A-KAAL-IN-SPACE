
package States;

/**
 * 
 * @author Willy Bazan
 * @date   05/12/2019
 * @time   04:00 am
 */

import java.awt.Color;
import java.awt.Graphics;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.ArrayList;

import Constants.Constant;
import Graphics.Assets;
import Graphics.Text;
import io.ScoreData;
import Math.Vector2D;
import ui.Action;
import ui.Button;

public class ScoreState extends State{
	
    private Button returnButton;

    //Esto es una cola de datos con una sola entrada
    //Esta ordenada de manera que el dato menor sea el de la cabeza
    private PriorityQueue<ScoreData> highScores; 
    //Pero ya que ScoreData no es un numero se agrega un comparador
    private Comparator<ScoreData> scoreComparator; //Comparator es una interfaz y como tal se tiene que implementar su metodo compare()


    //sirve para leer los datossin tener que borrarlos de la cola de raton
    private ScoreData[] auxArray;

    public ScoreState() {
        returnButton = new Button(
                        Assets.greyBtn,
                        Assets.blueBtn,
                        Assets.greyBtn.getHeight(),
                        Constant.HEIGHT - Assets.greyBtn.getHeight() * 2,
                        Constant.RETURN,
                        new Action() {
                            @Override
                            public void doAction() {
                                State.changeState(new MenuState());
                            }
                        }
                );
        
        scoreComparator = new Comparator<ScoreData>() {
            //La cola con prioridad utiliza este metodo para el orden de los datos, pero la logica la definimos nosotros
            @Override
            public int compare(ScoreData e1, ScoreData e2) { 
                       /**
                        * Es condicional quiere decir:
                        * que voy a retornar un número negativo si e1(el puntaje) es menor que e2(dato anterior)
                        * Por otro lado si e1 es mayor que e2 entonces retorno numero positivo
                        * Si ambos son iguales devuelve cero
                       */
                return e1.getScore() < e2.getScore() ? -1: e1.getScore() > e2.getScore() ? 1: 0;
            }
        };

        highScores = new PriorityQueue<ScoreData>(10, scoreComparator); //Diez es la capacidad inicial y el comparador


        //Puntaje de prueba //cOMENTAR LUEGO
        highScores.add(new ScoreData(100) );
        highScores.add(new ScoreData(50) );
        
        /**
        //***************************************************************
        try{
            ArrayList<ScoreData> dataList = JSONParser.readFile();
            
            for (ScoreData points: dataList)
            {
                highScores.add(points);
            }
            
            while ( highScores.size()>10)
            {
                highScores.poll(); //El metodo pull devuelve y remueve ese objeto
                //Luego se tiene que escribir en el archivo cuando se termina una partida
            }
            
        } catch(FileNotFoundException ex)
        {
            ex.printStackTrace();
        }
        
        //**********************************************************************
        */
    }
    

    @Override
    public void update() {
            returnButton.update();
    }

    @Override
    public void draw(Graphics g) {
        returnButton.draw(g);

        //Aquí se pasa la cola con prioridad a un arreglo normal y le doy el tamaño
        auxArray = highScores.toArray(new ScoreData[highScores.size()]);

        //Para ordenarlo de la forma que habia definido usando el mismo comparador
        Arrays.sort(auxArray, scoreComparator);


        Vector2D scorePos = new Vector2D(
                        Constant.WIDTH / 2 - 200,
                        100
                        );
        Vector2D datePos = new Vector2D(
                        Constant.WIDTH / 2 + 200,
                        100
                        );

        Text.drawText(g, Constant.SCORE, scorePos, true, Color.ORANGE, Assets.fontBig);
        Text.drawText(g, Constant.DATE, datePos, true, Color.YELLOW, Assets.fontBig);

        //LO que se hace aquí es dibujar un puntaje cada cuarenta pixeles
        scorePos.setY(scorePos.getY() + 40);
        datePos.setY(datePos.getY() + 40);

        for(int i = auxArray.length - 1; i > -1; i--) 
        {
            ScoreData d = auxArray[i];

            Text.drawText(g, Integer.toString(d.getScore()), scorePos, true, Color.WHITE, Assets.fontMed);
            Text.drawText(g, d.getDate(), datePos, true, Color.WHITE, Assets.fontMed);

            //Y luego se aumenta la posicion para dibuajar apropiadamente
            scorePos.setY(scorePos.getY() + 40);
            datePos.setY(datePos.getY() + 40);
        }
		
    }
	
}

