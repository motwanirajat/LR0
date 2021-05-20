/*  
*   state.JAVA
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


import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class state 
{
    Set<rule> rules = new HashSet<>();
    Set<link> links = new HashSet<>();
    
    @Override
    public boolean equals(Object obj) 
    {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        
        state obj1 = (state)obj;
        //System.out.println(this.rules.size()+"&"+obj1.rules.size());
        
        if(this.rules.size()>=obj1.rules.size())
        {
            return rules.containsAll(obj1.rules);
        }
        else
        {
            return obj1.rules.contains(this.rules);
        }
    }

    @Override
    public int hashCode() 
    {
        int hash = 3;
        hash = 53 * hash + Objects.hashCode(this.rules);
        hash = 53 * hash + Objects.hashCode(this.links);
        return hash;
    }
    
}
