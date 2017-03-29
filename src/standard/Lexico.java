/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package standard;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author Tarcisio
 */
public class Lexico {
  final static Map<Integer, String> TABELA;
    static {
        Map<Integer, String> aMap = new HashMap<>();
        aMap.put(1,"var");
        aMap.put(2,",");
        aMap.put(3,";");
        aMap.put(4,"begin");
        aMap.put(5,"end");
        aMap.put(6,"print");
        aMap.put(7,"read");
        aMap.put(8,"if");
        aMap.put(9,"then");
        aMap.put(10,"for");
        aMap.put(11,"to");
        aMap.put(12,"do");
        aMap.put(13,"else");
        aMap.put(14,":=");
        aMap.put(15,"+");
        aMap.put(16,"-");
        aMap.put(17,"*");
        aMap.put(18,"/");
        aMap.put(19,"=");
        aMap.put(20,"(");
        aMap.put(21,")");
        aMap.put(22,"=");
        aMap.put(23,"<>");
        aMap.put(24,"<");
        aMap.put(25,">");
        aMap.put(26,">=");
        aMap.put(27,"<=");
        aMap.put(28,"variavel");
        aMap.put(29,"numero");
        aMap.put(30,"EOF");

        TABELA = Collections.unmodifiableMap(aMap);
    }
    public void NextToken(Scanner arquivo){
        //String pattern= "^[a-zA-Z0-9]*$";
        String texto=arquivo.next();
        int i=0;
        while(i<texto.length() && Character.isLetter(texto.charAt(i))){
            
            i++;
        }
    }
}
