
package Math;

/**
 *
 * @author Willy Bazan
 * @date   18/11/2019
 * @time   10:45 am
 */
public class Vector2D {
	private double x,y;
	
	public Vector2D(double x, double y)
	{
		this.x = x;
		this.y = y;
	}
	
	public Vector2D()
	{
		x = 0;
		y = 0;
	}
        
        public Vector2D add(Vector2D v)
        {
            return new Vector2D(x+v.getX(), y+ v.getY());
        }
        
        
        public Vector2D scale( double value )
        {
            return new Vector2D (x*value, y * value);
        }
        
        public Vector2D limit(double value)
        {
            if (getMagnitude()> value)
            {
                return this.normalize().scale(value);
            }
           
            return this;
        }
        
        public Vector2D normalize()
        {
            double magnitude = getMagnitude(); //Para ahorrar tiempo ya que esto se esta llamando unas 60 veces por segundo
            
            return new Vector2D(x/magnitude, y/magnitude);
        }
        
        public double getMagnitude()
	{
            return Math.sqrt(x*x + y*y);
	}
	
	public Vector2D setDirection(double angle)
	{
            double magnitude = getMagnitude();
            return new Vector2D(Math.cos(angle)*magnitude, Math.sin(angle)*magnitude);
	}
        
        //Modifica la magnitud del vector direccion (heading)
	public double getX() {
            return x;
	}

	public void setX(double x) {
            this.x = x;
	}

	public double getY() {
            return y;
	}

	public void setY(double y) {
            this.y = y;
	}


}
