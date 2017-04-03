/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package standard;

/**
 *
 * @author Tarcisio
 */
public class Token {
    private int numero;
    private String lexograma;
    private String descricao;

    public Token(int numero, String lexograma, String descricao) {
        this.numero = numero;
        this.lexograma = lexograma;
        this.descricao = descricao;
    }

    public int getNumero() {
        return numero;
    }

    public String getLexograma() {
        return lexograma;
    }

    public String getDescricao() {
        return descricao;
    }
    
}
