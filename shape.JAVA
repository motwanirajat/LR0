/*  
*   shape.JAVA
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


public class shape 
{
    int x, y, length, width;

    public shape(int x, int y, int length, int width) {
        this.x = x;
        this.y = y;
        this.length = length;
        this.width = width;
    }

    public void display()
    {
        System.out.println(x+","+y+","+length+","+width);
    }
}
