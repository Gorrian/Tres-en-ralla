import java.util.Random;
import java.util.Scanner;

public class Tablero {
    private static final Incializadores_arrays Iniciar=new Incializadores_arrays();
    private static final String RESET = "\u001B[0m";
    private static final String RED = "\u001B[31m";
    private static final Random aleat=new Random();
    private static final Scanner read=new Scanner(System.in);
    private static final String Error[]=new String[]{RED+"ERROR: Valor fuera de rango"+RESET,
                                                    RED+"ERROR: No se ha elegido un valor correcto. El simbolo de la fixa solo puede ser \'X\' o bien \'O\'"+RESET,
                                                    RED+"ERROR: Numero de fila incorrecto"+RESET,
                                                    RED+"ERROR: Numero de columna incorrecto"+RESET,
                                                    RED+"ERROR: La posicion ya esta llena, no es posible el movimiento"+RESET,
                                                    RED+"ERROR: El valor tiene que ser \'S\' o \'N\'",
                                                    RED+"ERROR: No hay juego en el que poder participar"+RESET};
    //Una celda puede estar en uno de los siguientes 3 estados:
    //vacio,marcado por jugador 1 o marado por jugador 2
    //Si esta vacio ambos booleans estaran en falso pero si uno marca entonces se cambiara el boolean que corresponda
    //al jugador correspondiente
    public static  class celda{
        //int 0=circulo
        //int 1=cruz

        public celda (boolean[] valor) {
            estado = valor;
        }
        public boolean [] estado=new boolean[]{false,false};
        public String Mostrar_cleda(){
            if(estado[0]==true){
                return "O";
            }else if (estado[1]==true){
                return "X";
            }else{
                return "N";
            }
        }
    }
    //En un tablero de tres en rallas la estructura es de 3 columnas y 3 filas con lo cual esta variable es la encargada
    //de representar eso.
    //Recordar el primer numero es la columna el segundo la fila
    private celda[][] estructura=new celda[3][3];
    public String ID_game;
    public  String Nombre;
    public int ficha_jugador;
    public int ficha_IA;
    private boolean Jugador_empieza;

    private boolean Comprobar (int columna, int fila){
        boolean ocupado=false;

        for(int i=0;i<estructura[columna][fila].estado.length && !ocupado;i++){
            System.out.print("");
            if(estructura[columna][fila].estado[i]){
                ocupado=true;
            }
        }
        return ocupado;
    }
    private int Comprobar_fila(int columna){
        int contador=0;
        if(estructura[columna][0].estado[0]){
            for(int i=0;i<estructura[columna].length;i++){
                if(estructura[columna][i].estado[0]){
                    contador++;
                }
            }
            if(contador==3){
                return 0;
            }else{
                return -1;
            }
        }else{
            for(int i=0;i<estructura[columna].length;i++){
                if(estructura[columna][i].estado[1]){
                    contador++;
                }
            }
            if(contador==3){
                return 1;
            }else{
                return -1;
            }
        }
    }
    private int Comprobar_columna(int fila){
        int contador=0;
        if(estructura[0][fila].estado[0]){
            for(int i=0;i<estructura.length;i++){
                System.out.print("");
                if(estructura[i][fila].estado[0]){
                    contador++;
                }
            }
            if(contador==3){
                return 0;
            }else{
                return -1;
            }
        }else{
            for(int i=0;i<estructura.length;i++){
                System.out.print("");
                if(estructura[i][fila].estado[1]){
                    contador++;
                }
            }
            if(contador==3){
                return 1;
            }else{
                return -1;
            }
        }
    }

    public String indicar_ficha_j(){
        return ficha_jugador==0 ? "O" : "X";
    }
    //Tambien podria llamarla lanzar ficha en un espacio vacio al azar.
    public void IA_jugada(int fixa){
        boolean Seleccionado=false;
        int fila;
        int columna;

        do{
            fila=aleat.nextInt(3);
            columna=aleat.nextInt(3);
            if(!Comprobar(columna,fila)){
                Seleccionado=true;
                estructura[columna][fila].estado[fixa]=true;
            }
        }while(!Seleccionado);
    }
    public void Jugador_jugada(int fixa){
        int opcion;
        int opcion1;
        boolean Salir=false;
        do{
            System.out.println("Selecciona la fila donde hacer la jugada");
            opcion=read.nextInt()-1;
            if(opcion<estructura.length&&opcion>=0){
                System.out.println("Selecciona la columna donde hacer la jugada");
                opcion1=read.nextInt()-1;
                if(opcion<estructura.length&&opcion>=0){
                    if(!Comprobar(opcion,opcion1)){
                        estructura[opcion][opcion1].estado[fixa]=true;
                        Salir=true;
                    }else{
                        System.out.println(Error[4]);
                    }
                }else{
                    System.out.println(Error[2]);
                }
            }else{
                System.out.println(Error[3]);
            }
        }while(!Salir);
    }
    public void Decidir_primer_movimiento(){
        Iniciar.Iniciar(estructura);
        if(aleat.nextInt(2)==1){
            Jugador_empieza=true;
        }else{
            Jugador_empieza=false;
        }
    }
    //No me acuerdo porque lo cree pero le puedo encontrarle uso a esta funcion.
    public String Quien_empieza(){
        return Jugador_empieza ? "Empieza el jugador":"Empieza la IA";
    }
    public boolean Ver_quien_mueve(){
        return Jugador_empieza;
    }
    public void Mostrar_tabla(){
        int i;
        int i1;
        for(i=0;i<7;i++){
            System.out.print("_");
        }
        System.out.println();
        for(i=0;i<estructura.length;i++){
            for(i1=0;i1<estructura[i].length;i1++){
                if(i1==0){
                    System.out.print("|"+estructura[i][i1].Mostrar_cleda());
                }else if(i1==1){
                    System.out.print("|"+estructura[i][i1].Mostrar_cleda()+"|");
                }else{
                    System.out.print(estructura[i][i1].Mostrar_cleda()+"|");
                }
            }
            System.out.println();
            for(i1=0;i1<7;i1++){
                System.out.print("_" +
                        "");
            }
            System.out.println();
        }
    }
    public int Comprobar_ganador(){
        int i,i1;
        for (i=0;i<estructura.length;i++){
            switch (Comprobar_fila(i)){
                case 0:
                    return 0;
                case 1:
                    return 1;
            }
        }
        for(i=0;i<estructura.length;i++){
            switch (Comprobar_columna(i)){
                case 0:
                    return 0;
                case 1:
                    return 1;
            }
        }
        int contador=0;
        if(estructura[0][0].estado[0] ){
            for(i=0;i<estructura.length;i++){
                if(estructura[i][i].estado[0]){
                    contador++;
                }
            }
            if(contador==3){
                return 0;
            }
        }else{
            for(i=0;i<estructura.length;i++){
                if(estructura[i][i].estado[1]){
                    contador++;
                }
            }
            if(contador==3){
                return 1;
            }
        }
        contador=0;
        if(estructura[0][2].estado[0]){
            for(i=0, i1=2;i<estructura.length; i++, i1--){
                if(estructura[i][i1].estado[0]){
                    contador++;
                }
            }
            if(contador==3){
                return 0;
            }
        }else{
            for(i=0, i1=2;i<estructura.length; i++, i1--){
                if(estructura[i][i1].estado[1]){
                    contador++;
                }
            }
            if(contador==3){
                return 1;
            }
        }
        return -1;
    }
    public boolean Comprobar_lleno(){
        int i, i1;
        boolean lleno=true;
        //He usado do while para estas cosas pero aun sigo leyendo mejor esto.
        for(i=0;i<estructura.length && lleno;i++){
            for(i1=0;i<estructura.length && lleno;i++){
                if(!estructura[i][i1].estado[0] && !estructura[i][i1].estado[1]){
                    lleno=false;
                }
            }
        }
        return lleno;
    }
}
