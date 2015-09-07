/*  
*   draw.JAVA
*   This file is a part of the program LR0 (SLR) Automaton for a given Context Free Grammar.
*   
*   Copyright (C) 2015  Rajat Motwani
*   LR0 (SLR) Automaton for a given Context Free Grammar [https://goo.gl/SZO25E]
*   
*   Full GPL license can be found here: https://goo.gl/iIDWBG
*   This program is free software; you can redistribute it and/or modify
*   it under the terms of the GNU General Public License as published by
*   the Free Software Foundation; either version 2 of the License, or
*   (at your option) any later version.
*
*   This program is distributed in the hope that it will be useful,
*   but WITHOUT ANY WARRANTY; without even the implied warranty of
*   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
*   GNU General Public License for more details.
*
*   You should have received a copy of the GNU General Public License along
*   with this program; if not, write to the Free Software Foundation, Inc.,
*   51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
*/


import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

class draw extends Canvas 
{
    ArrayList<state> states;
    ArrayList<shape> shapes;
    int h=120,w=120;
    public draw( ArrayList<state> states) 
    {
        this.states=states;
        shapes = new ArrayList<>();
    }
    
    public void makeRecs()
    {
        int stX=0,stY=0;
        for(int i=0;i<states.size();i++,stX++)
        {
            
            if(i%6==0 && i!=0)
            {
                stX=0;
                stY+=210;
            }
            shapes.add(new shape(stX*210, stY, w, h));
        }
    }
    
    @Override
    public void paint(Graphics g) 
    {
        System.out.println("in paint");
        makeRecs();
        // step one of the recipe; cast Graphics object as Graphics2D
        Graphics2D g2d = (Graphics2D) g;
        
        // step two-set the graphics context
        g2d.setColor(Color.BLACK); //setting context
        
        //step three-render something
        for(shape s: shapes)
        {
            //s.display();
            g2d.drawRect(s.x,s.y,s.length,s.width);
            g2d.drawString("i"+String.valueOf(shapes.indexOf(s)), s.x+20, s.y+20);
            int d=30;
            for(rule r: states.get(shapes.indexOf(s)).rules)
            {
                g2d.drawString(r.show(), s.x+20, s.y+d);
                d+=15;
            }
        }
        
        for(int i=0;i<states.size();i++)
        {
            int linkCount=0;
            for(link l: states.get(i).links)
            {
                //if shape is next figure, or diaognally upwards/downwards in forward direction
                if( (l.to==i+1 && l.to%6!=0)  || ( (l.to == i+6 || l.to == i-5 )&& l.to %6!=0) )
                {
                    shape from = shapes.get(l.from);
                    int xf = from.x+w;
                    int yf = from.y+10;
                    shape to = shapes.get(l.to);
                    int xt = to.x;
                    int yt = to.y;
                    g2d.drawLine(xf,yf,xt,yt);
                    g2d.fill(new Ellipse2D.Float(xf, yf, 8, 8));
                    g2d.setColor(Color.red);
                    g2d.fill(new Ellipse2D.Float(xt, yt, 8, 8));
                    g2d.setColor(Color.BLACK);
                    g2d.drawString(l.on, (2*xf+xt)/3, (yf+2*yt)/3+15);
                }
                else if((l.to == i+6)) // if shape is vertically downwards
                {
                    shape from = shapes.get(l.from);
                    int xf = from.x+10;
                    int yf = from.y+h;
                    shape to = shapes.get(l.to);
                    int xt = to.x;
                    int yt = to.y;
                    g2d.drawLine(xf,yf,xt,yt);
                    g2d.fill(new Ellipse2D.Float(xf, yf, 8, 8));
                    g2d.setColor(Color.red);
                    g2d.fill(new Ellipse2D.Float(xt, yt, 8, 8));
                    g2d.setColor(Color.BLACK);
                    g2d.drawString(l.on, (xf+2*xt)/3+5, (yf+2*yt)/3+15);
                }
                else if(i/6 == l.to/6) // if shapes are in same row
                {
                    int count=0,d=10;
                    shape from = shapes.get(l.from);
                    int xf = from.x+(w/3)+d*count;
                    int yf = from.y+h+d*count;
                    shape to = shapes.get(l.to);
                    int xt = to.x+(w/3)+d*count;
                    int yt = to.y+h+d*count;
                    g2d.drawLine(xf+32,yf,(xt+2*xf)/3,yf+70);
                    g2d.drawLine((xt+2*xf)/3,yf+70,xt,yt);
                    g2d.fill(new Ellipse2D.Float(xf+32, yf, 8, 8));
                    g2d.setColor(Color.red);
                    g2d.fill(new Ellipse2D.Float(xt, yt, 8, 8));
                    g2d.setColor(Color.BLACK);
                    g2d.drawString(l.on, (2*xf+xt)/3+5, yf+85);
                    count++;
                    
                }
                else //in all other cases
                {
                    int xf,yf,xt,yt;
                    int a = 20*linkCount;
                    shape from = shapes.get(l.from);
                    g2d.drawLine(xf = from.x+w, yf = from.y+2*(h/3)+a, xt = from.x+4*(w/3), yt = from.y+2*(h/3)+a);
                    //g2d.drawString("i"+String.valueOf(l.from), from.x+160, from.y+110+a);
                    g2d.fill(new Ellipse2D.Float(xf, yf, 8, 8));
                    g2d.setColor(Color.red);
                    g2d.fill(new Ellipse2D.Float(xt, yt, 8, 8));
                    g2d.setColor(Color.BLACK);
                    g2d.drawString(l.on, from.x+w+20, from.y+2*(h/3)+a-3);
                    g2d.drawString("i"+String.valueOf(l.to), from.x+w+40, from.y+2*(h/3)+a-3);
                    linkCount++;
                }
            }
        }
    }
}
