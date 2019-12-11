
package io;

/**
 *
 * @author Willy Bazan
 * @date   05/12/2019
 * @time   02:35 pm
 */

import java.text.SimpleDateFormat;
import java.util.Date;

public class ScoreData {
	
	private String date;
	private int score;
	
	public ScoreData(int score) { //Este constructor se llama despues de terminar una partida
		this.score = score;
		
		Date today = new Date(System.currentTimeMillis());
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); //Ya que se necesita un formato
		date = format.format(today);
		
	}
	
        //Ya que este objeto no se construye solo al terminar una partida
        //Sino que tambien lo estoy leyendo del disco
	public ScoreData() {
		
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	
	
	
}

