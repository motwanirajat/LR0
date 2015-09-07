/*  
*   rule.JAVA
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

import java.util.Objects;

public class rule 
{
    String lhs;
    String rhs;

    public rule(String lhs, String rhs) 
    {
        this.lhs = lhs;
        this.rhs = rhs;
    }
    
    @Override
    public boolean equals(Object obj) 
    {
        
        //System.out.println("*************");
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final rule other = (rule) obj;
        
        if (!Objects.equals(this.lhs, other.lhs)) {
            return false;
        }
        if (!Objects.equals(this.rhs, other.rhs)) {
            return false;
        }
        
        
        return true;
    }

    @Override
    public int hashCode() 
    {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.lhs);
        hash = 67 * hash + Objects.hashCode(this.rhs);
        return hash;
    }
    
    public void display()
    {
        System.out.println(lhs+"->"+rhs);
    }
    
    public String show()
    {
        return lhs+"->"+rhs;
    }
}
