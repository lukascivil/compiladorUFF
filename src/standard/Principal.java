/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package standard;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Tarcisio
 */

public class Principal {

    final static String FILE_NAME = "D:\\teste.txt";
    /**
     * @param args the command line arguments
     */
    final static Charset ENCODING = StandardCharsets.UTF_8;
    public static void main(String[] args) throws IOException {
        Path path = Paths.get(FILE_NAME);
        Lexico lexico= new Lexico();
        
        try (Scanner scanner =  new Scanner(path, ENCODING.name())){
            do{
                lexico.NextToken(scanner);
            }while(scanner.hasNextLine());
        }catch(IOException ex){
            System.out.println("Nao foi possivel abrir o arquivo");
        }
    }      
    
}
/*
********************************* Gramatica *********************************
PROG-> VARS BLOCO.
VARS-> var LISTA_IDENT; | ε
BLOCO -> begin COMS end
COMS -> COM;COMS | ε
COM -> print (LISTA_IDENT) | if EXP_REL then BLOCO ELSE_OPC | IDENT:= EXP | for IDENT := EXP to EXP do BLOCO| read(LISTA_IDENT)
ELSE_OPC-> else BLOCO | ε
LISTA_IDENT -> IDENT, LISTA_IDENT | IDENT
EXP-> EXP+TERMO | EXP-TERMO | TERMO
TERMO-> TERMO*FATOR | TERMO/FATOR | FATOR
FATOR-> (EXP)| IDENT | NUM
EXP_REL-> EXP OP_REL EXP
OP_REL -> = | <> | < | > | <= | >=
IDENT -> CARACTER IDENT | CARACTER
CARACTER -> a|....|z |A|.....|Z
NUM -> DIGITO NUM | DIGITO
DIGITO -> 0| .... | 9


**********************lista de comandos para tabela *************************
.
var
,
;
begin
end
print
read
if
then
for
to
do
else
:=
+
-
*
/
=
(
)
=
<>
<
>
>=
<=
variavel
numero
EOF

*/