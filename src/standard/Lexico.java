/*
 * @author Tarcisio e Lucas
 */
package standard;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;



public class Lexico {
    
    private String buffer;
    private static Lexico instance = null;

    public String getBuffer() {
        return buffer;
    }

    public static Map<Integer, String> getTABELA() {
        return TABELA;
    }
    
    protected Lexico(){
        buffer=null;
    }
    public static Lexico getInstance(){
        if(instance==null){
            instance=new Lexico();
        }
        return instance;
    }
/*   public enum tokens{
        tk_var,
        tk_virgula,
        tk_pontoVirgula,
        tk_begin,
        tk_end,
        tk_print,
        tk_read,
        tk_if,
        tk_then,
        tk_for,
        tk_to,
        tk_do,
        tk_else,
        tk_atribuir,
        tk_mais,
        tk_menos,
        tk_vezes,
        tk_dividir,
        tk_igual,
        tk_abreParenteses,
        tk_fechaParenteses,
        tk_eIgual,
        tk_diferente,
        tk_menor,
        tk_maior,
        tk_maiorIgual,
        tk_menorIgual,
        tk_variavel,
        tk_numero,
        tk_EOF;
    }  //*/
    
    private Integer getKey(String value){
        int i=0;
        for(i=1; i<TABELA.size();i++){
            String aux=TABELA.get(i);
            if(aux.equals(value)) break;
        }
        return i;
    }
    final static Map<Integer, String> TABELA;
    static {
        Map<Integer, String> aMap = new HashMap<>();
        aMap.put(1,".");
        aMap.put(2,"var");
        aMap.put(3,",");
        aMap.put(4,";");
        aMap.put(5,"begin");
        aMap.put(6,"end");
        aMap.put(7,"print");
        aMap.put(8,"read");
        aMap.put(9,"if");
        aMap.put(10,"then");
        aMap.put(11,"for");
        aMap.put(12,"to");
        aMap.put(13,"do");
        aMap.put(14,"else");
        aMap.put(15,":=");
        aMap.put(16,"+");
        aMap.put(17,"-");
        aMap.put(18,"*");
        aMap.put(19,"/");
        aMap.put(20,"(");
        aMap.put(21,")");
        aMap.put(22,"=");
        aMap.put(23,"<>");
        aMap.put(24,">");
        aMap.put(25,"<");
        aMap.put(26,">=");
        aMap.put(27,"<=");
        aMap.put(28,"variavel");
        aMap.put(29,"numero");
        aMap.put(30,"EOF");

        TABELA = Collections.unmodifiableMap(aMap);
    }
    
    
    public Token NextToken(Scanner arquivo){
        
        if(!arquivo.hasNext() && buffer.equals("EOF")){
            buffer = null;
            return new Token(this.getKey("EOF"),"EOF", "EOF");
        }
        String texto=null;
        if (buffer==null || buffer.equals("")){
            texto=arquivo.next();            
        }else{
            texto=buffer;
            buffer=null;
            if(!arquivo.hasNext()) buffer="EOF";
        }        
        
        texto=texto.replace("\t", "");
        texto=texto.replace("\n", "");
        texto=texto.replace(" ", "");
        int i=0;
        
        //Pega Variável
        while(i<texto.length() && Character.isLetter(texto.charAt(i))){
            i++;
        }
        
        if(i!=0){
            buffer = texto.substring(i); 
            int chave;
            
            switch (texto.substring(0, i)) {
                case "var":
                    chave = getKey("var");
                    break;
                case "begin": 
                    chave = getKey("begin");
                    break;
                case "end": 
                    chave = getKey("end");
                    break;
                case "print": 
                    chave = getKey("print");
                    break;
                case "read": 
                    chave = getKey("read");
                    break;
                case "if": 
                    chave = getKey("if");
                    break;
                case "then": 
                    chave = getKey("then");
                    break;
                case "for": 
                    chave = getKey("for");
                    break;
                case "to": 
                    chave = getKey("to");
                    break;
                case "do": 
                    chave = getKey("do");
                    break;
                case "else": 
                    chave = getKey("else");
                    break;
                default: 
                    chave = getKey("variavel");
                    break;
            }
            return new Token(chave, TABELA.get(chave), texto.substring(0, i));        
        }
        
        //Pega Número
        while(i<texto.length() && Character.isDigit(texto.charAt(i))){
            i++;
        }
       
        if(i!=0){
            buffer=texto.substring(i);    
            int chave=getKey("numero");
            return new Token(chave,TABELA.get(chave),texto.substring(0, i));        
        }
    
        //achar simbolos soltos
        String aux=texto.substring(i,i+1);
         
        if(TABELA.containsValue(aux) || aux.equals(":")){ //aqui eu tratei a maior partes dos tokens. //Gambi

            Token tk=null;
            int key;
            switch (texto.charAt(i)){
                case '.':
                        key=this.getKey(".");
                        tk=new Token(key,texto.substring(i,i+1),TABELA.get(key));
                    break;
                case ',':
                        key=this.getKey(",");
                        tk=new Token(key,texto.substring(i,i+1),TABELA.get(key));
                    break;    
                case ';':
                        key=this.getKey(";");
                        tk=new Token(key,texto.substring(i,i+1),TABELA.get(key));
                    break;
                case ':': //Gambi
                        if(texto.length()>i+1 && texto.charAt(i+1)=='='){
                            key=this.getKey(":=");
                            tk=new Token(key,texto.substring(i,i+2),TABELA.get(key));                              
                        }else{
                            if ((i+1)<texto.length()) buffer=texto.substring(i+1);
                            return new Token(-1,texto.substring(i,i+1),"Erro Lexico");
                        }
                    break;
                case '+':
                        key=this.getKey("+");
                        tk=new Token(key,texto.substring(i,i+1),TABELA.get(key));
                    break;
                case '-':
                        key=this.getKey("-");
                        tk=new Token(key,texto.substring(i,i+1),TABELA.get(key));
                    break;
                case '*':
                        key=this.getKey("*");
                        tk=new Token(key,texto.substring(i,i+1),TABELA.get(key));
                    break;
                case '/':
                        key=this.getKey("/");
                        tk=new Token(key,texto.substring(i,i+1),TABELA.get(key));
                    break;
                case '=':
                        if(texto.length()>i+1 && texto.charAt(i+1)=='='){
                            key=this.getKey("==");
                            tk=new Token(key,texto.substring(i,i+2),TABELA.get(key));                              
                        }else{
                            key=this.getKey("=");
                            tk=new Token(key,texto.substring(i,i+1),TABELA.get(key));
                        }
                    break;
                case '<':
                    if(texto.length()>i+1 && texto.charAt(i+1)=='>'){
                        key=this.getKey("<>");
                        tk=new Token(key,texto.substring(i,i+2),TABELA.get(key));
                    }else{
                        if(texto.length()>i+1 && texto.charAt(i+1)=='='){
                            key=this.getKey("<=");
                            tk=new Token(key,texto.substring(i,i+2),TABELA.get(key));                        
                        }else{
                            key=this.getKey("<");
                            tk=new Token(key,texto.substring(i,i+1),TABELA.get(key));                            
                        }
                    }
                    break;
                case '>':
                    if(texto.length()>i+1 && texto.charAt(i+1)=='='){
                        key=this.getKey(">=");
                        tk=new Token(key,texto.substring(i,i+2),TABELA.get(key));
                    }else{
                        key=this.getKey(">");
                        tk=new Token(key,texto.substring(i,i+1),TABELA.get(key));     
                    }
                    break;
                case '(':
                        key=this.getKey("(");
                        tk=new Token(key,texto.substring(i,i+1),TABELA.get(key));
                    break;
                case ')':
                        key=this.getKey(")");
                        tk=new Token(key,texto.substring(i,i+1),TABELA.get(key));
                    break;                
            }
            if(texto.length() > i+tk.getLexograma().length()){ //cuidado para nao por caracteres que estao em TABELA mas ainda nao foram tratados
                buffer=texto.substring(i+tk.getLexograma().length());
            }
            return tk;
        }else{
            if ((i+1)<texto.length()) buffer=texto.substring(i+1);
            
            return new Token(-1,texto.substring(i,i+1),"Erro Lexico");
        }
    }
}
