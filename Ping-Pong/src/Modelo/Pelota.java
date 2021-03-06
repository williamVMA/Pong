package Modelo;

import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

import Vista.TableroJuego;

public class Pelota {

    public static Pelota pelota= new Pelota();
	
	public int x, y,cx,cy,r=20;
    private int dx=1, dy=1;
    private int dmax = 2;
    private double t = 0;
    private double dt = 0.001;    
    private double tmax = 10;
    private int inct = 1; 
 
    
    
    public Pelota() {
    	inicio();
    }
    
    private void inicio(){
    	this.x = 400;
        this.y = 200;
        t = 0;
        dx =1;
        dy =1;
        
    }
    
    private void calcularCentros(){
    	cx = x + r/2;
    	cy = y + r/2;
    }
    
    
    public Pelota(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public Rectangle2D getPelota() {
    	calcularCentros();
        return new Rectangle2D.Double(x, y, r, r);
    }
     
    
    private void acelerar(){
    	if(t<tmax){
    		System.out.println(""+t/inct);
    		t+=dt;
    		if(Math.abs(dy)<dmax){
    			if(dy>0){
    				dy = dy + (int)(t/inct);
    			}else{
    				dy = dy - (int)(t/inct);
    			}    				
    		}
    		if(Math.abs(dx)<dmax){
    			if(dx>0){
    				dx = dx + (int)(t/inct);
    			}else{
    				dx = dx - (int)(t/inct);
    			}    				
    		}
    	}else{
    		t = 0;
    	}
    }  
    
 
    public void mover(Rectangle limites, boolean colisionR1, boolean colisionR2) {
    	acelerar();    	
    	TableroJuego tj = TableroJuego.getInstance();
    	x+=dx;
        y+=dy;
        if(colisionR1 == true ){
            dx=-dx;
            x=25;
        }
        if (colisionR2 == true) {
            dx=-dx;
            x=750;
        }
        if(x>limites.getMaxX()){
            dx=-dx;
            tj.puntoR1();
            inicio();
        }
        if (y>limites.getMaxY()-15){
           dy=-dy;
        }
        if (x<-20){
            dx=-dx;
            tj.puntoR2();
            inicio();
        }
        if (y<0){
            dy=-dy;
        }
        tj.mostrarScore();
        
    }
}
