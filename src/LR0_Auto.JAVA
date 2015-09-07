/*  
*   LR0_Auto.JAVA
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


import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class LR0_Auto extends Frame
{
    public static ArrayList<state> states;
    
    public static void main(String[] args) throws FileNotFoundException, IOException 
    {
        BufferedReader br1 = new BufferedReader(new InputStreamReader(System.in));
        
        //Provide input file path
        String path = "C:\\Users\\Rajat\\Desktop\\c.in"; 
        
        File input = new File(path);
        BufferedReader br = new BufferedReader(new java.io.FileReader(input));
        //System.out.println("Enter number of rules");
        int n = Integer.parseInt(br.readLine());

        states = new ArrayList<>(); //ArrayList of all states
        rule[] rules = new rule[n + 1]; // RULE Input from user 
        //{ Extra State to Accomodate augumented grammar }
        state i0 = new state(); // Start state
        
        for (int i = 0; i < n; i++) 
        {
            String[] pre = br.readLine().split("->");
            String from = pre[0];//sc.next();
            //System.out.println("Enter RHS");
            String to = "." + pre[1];//sc.next();
            rules[i + 1] = new rule(from, to);
            i0.rules.add(rules[i + 1]); //Add all rules to start state
        }
        rules[0] = new rule("W'", "." + rules[1].lhs); // Augumented grammar
        i0.rules.add(rules[0]); //Add S' to start state

        state first = new state();
        first.rules.add(new rule("W'", "." + rules[1].lhs));
        boolean noNewAdd = false;
        while (!noNewAdd) 
        {
            Set<rule> pendingRuleSet = new HashSet<>(first.rules);
            //Copy already built set to pendingstate set, to add non-terminal additions

            for (rule r : first.rules) 
            {
                char reqNonTer;
                if (isNonTerm(reqNonTer = charPostDot(r))) 
                {
                    for (rule r_ : i0.rules) 
                    {
                        if (r_.lhs.charAt(0) == reqNonTer) 
                        {
                            pendingRuleSet.add(r_);
                        }
                    }
                }
            }

            if (pendingRuleSet.isEmpty()) 
            {
                //System.out.println("break");
                break;
            }
            boolean hasEachRule = true;
            //check if all new made rules are already present in temp 
            //i.e. no new rules are made due to non-terminals
            for (rule r : pendingRuleSet) 
            {
                if (!first.rules.contains(r)) 
                {
                    hasEachRule = false;
                    break;
                }
            }
            //add all the rules to temp rules set
            for (rule r : pendingRuleSet) 
            {
                //r.display();
                first.rules.add(r);
            }
            pendingRuleSet.clear();
            if (hasEachRule) 
            {
                noNewAdd = true;
            }
        }
        states.add(first); //Add inital state to state ArrayList

        boolean noNewStates = false;
        int stateIndex = 0, statesBuild = 1;
        while (!noNewStates) 
        {
            state cur = states.get(stateIndex); //current state
            Set<Character> transSymb = new HashSet<>();
            for (rule r : cur.rules) 
            {
                char ch;
                // create a set of all trans symbols from currrent state
                if( (ch = charPostDot(r))!='~') 
                    transSymb.add(ch);
            }

            for (Character ch : transSymb) 
            {
                //System.out.println("Operating on"+ch);
                // create a temp state to save production of current symbol
                state temp = new state(); 
                //shift dot character and save production in temp, to make a new state
                for (rule r : cur.rules) 
                {
                    if (charPostDot(r) == ch) 
                    {
                        temp.rules.add(shift(r)); 
                    }

                }
                boolean noNewAdditives = false;
                //Add more transitions if dot charcater comes before a non terminal
                
                while (!noNewAdditives) 
                {
                    Set<rule> pendingRuleSet = new HashSet<>(temp.rules);
                    //Copy already built set to pendingstate set, to add non-terminal productions

                    for (rule r : temp.rules) 
                    {
                        //System.out.println("temp has");
                        //r.display();
                        char reqNonTer;
                        if (isNonTerm(reqNonTer = charPostDot(r))) 
                        {
                            for (rule r_ : i0.rules) 
                            {
                                if (r_.lhs.charAt(0) == reqNonTer) 
                                {
                                    //System.out.println("Addint to pending rule set");
                                    //r_.display();
                                    pendingRuleSet.add(r_);
                                }
                            }
                        }
                    }

                    if (pendingRuleSet.isEmpty()) 
                    {
                        System.out.println("break");
                        break;
                    }
                    boolean hasEachRule = true;
                    //check if all new made rules are already present in temp 
                    //i.e. no new rules are made due to non-terminals
                    //System.out.println("peding ruleset");
                    for (rule r : pendingRuleSet) 
                    {
                        //r.display();
                        if (!temp.rules.contains(r)) 
                        {
                            hasEachRule = false;
                            //System.out.println("break");
                            break;
                        }
                    }
                    //add all the rules to temp rules set
                    for (rule r : pendingRuleSet) 
                    {
                        //r.display();
                        temp.rules.add(r);
                    }

                    pendingRuleSet.clear();
                    if (hasEachRule) 
                    {
                        noNewAdditives = true;
                    }
                    
                }
                // if state is not present in the arraylist, add to it
                //create new link from current state to new state on the character being checked
                if (!states.contains(temp)) 
                {   
                    states.add(statesBuild++, temp);
                    cur.links.add(new link(stateIndex, statesBuild - 1, String.valueOf(ch)));

                } 
                else //state is present in arraylist, don't add to array list
                    //get index and make a new link from cur to found state
                {
                    int ind = states.indexOf(temp);
                    cur.links.add(new link(stateIndex, ind, String.valueOf(ch)));
                }
            }
            if(stateIndex==statesBuild-1)
            {
                break;
            }
            else
                stateIndex++; //Start operating on next state in arraylist
        }
        for (state st : states) 
        {
            System.out.println("State: i" + states.indexOf(st));
            for (rule r : st.rules) 
            {
                System.out.println(r.lhs + "->" + r.rhs);
            }
            System.out.println("Transitions");
            if(st.links.isEmpty())
                System.out.println("none");
            for (link l : st.links)
            {
                System.out.println(l.from + "---" + l.on + "--->" + l.to);
            }
        }
        //Uncomment the next line, if you wish to have a user interface.
        //new LR0_Auto();
    }
    
    LR0_Auto()
    {
        this.add(new draw(states));
        this.setSize(1400, 800);
        this.show();
        addWindowListener(new WindowEventHandler());
        
    }
    
    class WindowEventHandler extends WindowAdapter {
        @Override
        public void windowClosing(WindowEvent e) {
          System.exit(0);
        }
    }
        
    public static rule shift(rule r1) //Shift Function for next character
    {
        rule r = new rule(r1.lhs, r1.rhs);
        String str = r.rhs;
        int index = str.indexOf('.');
        if (index < str.length() - 1) {
            return new rule(r.lhs,
                    str.substring(0, index)
                    + str.charAt(index + 1)
                    + '.'
                    + str.substring(index + 2, str.length()));
        } else {
            return r;
        }
    }

    public static char charPostDot(rule r) //Returns the Next Character after '.', 
                                            //if it is last, return '~' { epsilon }
    {
        if (r.rhs.indexOf('.') < r.rhs.length() - 1) 
        {
            return r.rhs.charAt(r.rhs.indexOf('.') + 1);
        } 
        else 
        {
            return '~';
        }

    }

    public static boolean isNonTerm(char ch) //Check if NonTerminal symbol
    {
        return ch >= 'A' && ch <= 'Z';
    }
}
