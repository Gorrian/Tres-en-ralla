import java.sql.SQLOutput;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;

public class Main {
    //
    private static Random aleat=new Random();
    private static Tablero juego;
    private static ArrayList<Tablero> juegos=new ArrayList();
    private static final Scanner read=new Scanner(System.in);
    private static final String BLACK = "\u001B[30m";
    private static final String RED = "\u001B[31m";
    private static final String GREEN = "\u001B[32m";
    private static final String YELLOW = "\u001B[33m";
    private static final String BLUE = "\u001B[34m";
    private static final String PURPLE = "\u001B[35m";
    private static final String CYAN = "\u001B[36m";
    private static final String WHITE = "\u001B[37m";
    private static final String RESET = "\u001B[0m";
    private static final String Menu_principal[]=new String[]{"Iniciar juego",
                                                            "Colocar ficha",
                                                            "Salir"};
    private static final String Seleccion_ficha[]=new String[]{"Circulo",
                                                                "Cruz"};
    private static final String Error[]=new String[]{RED+"ERROR: Valor fuera de rango"+RESET,
                                                    RED+"ERROR: No se ha elegido un valor correcto. El simbolo de la fixa solo puede ser \'X\' o bien \'O\'"+RESET,
                                                    RED+"ERROR: Numero de fila incorrecto"+RESET,
                                                    RED+"ERROR: Numero de columna incorrecto"+RESET,
                                                    RED+"ERROR: La posicion ya esta llena, no es posible el movimiento"+RESET,
                                                    RED+"ERROR: El valor tiene que ser \'S\' o \'N\'",
                                                    RED+"ERROR: No hay juego en el que poder participar"+RESET};
    public static void main (String[] args){
        int opcion;
        int ID_give=0;
        int ganador;
        boolean Salir=false;
        //Esta boolean controla el final del programa
        boolean Detener=false;
        String lista[];

        do{
            //juego=(Tablero) juegos.get(0);
            //juego.IA_jugada(1);
            opcion=Menu(Menu_principal, "Menu principal");
            switch (opcion){
                case 1:
                    //Gracias al juel campos consegui areglar el problema
                    //El problema era que al parecer era que como tenia las variables en staticas sin importar cuandos
                    //objetos tableros creara solo habia una variable IDGAME por su caracteristica de estatico, la solucion
                    //fue hacer todo el objeto tablero excepto random sin la caraccteristica static.
                    juegos.add(new Tablero());
                    juego=(Tablero) juegos.get(juegos.size()-1);
                    System.out.println("Por favor introduce el nombre del jugador de esta partida");
                    juego.Nombre=read.nextLine();
                    //(Tablero)juegos.get(0).Nombre=read.nextLine();
                    opcion=Menu(Seleccion_ficha, "Por favor selecciona la ficha que quieres utilizar")-1;
                    juego.ficha_jugador=opcion;
                    if(opcion==1){
                        juego.ficha_IA=0;
                    }else{
                        juego.ficha_IA=1;
                    }
                    juego.Decidir_primer_movimiento();
                    juego.ID_game="ID tablero "+ID_give;
                    ID_give++;
                    break;
                case 2:
                    if(!juegos.isEmpty()){
                        lista=new String[juegos.size()];
                        lista=Rellenar_lista(lista);
                        opcion=Menu(lista,"Elige un tablero en el que hacer una jugada")-1;
                        juego=(Tablero)juegos.get(opcion);
                        Coloca_fixa();
                        ganador=juego.Comprobar_ganador();
                        if(ganador==1 || ganador==0){
                            System.out.println("El juego ha terminado el ganador fue la ficha "+Traducir_ficha(ganador));
                            juegos.remove(opcion);
                        }else{
                            if(juego.Comprobar_lleno()){
                                System.out.println("El juego ha terminado ya que el tablero esta lleno, la partida ha acabado en empate");
                                juegos.remove(opcion);
                            }
                        }
                    }else{
                        System.out.println(Error[6]);
                    }
                    break;
                case 3:
                    Detener=true;
            }
        }while (!Detener);
    }
    private static int Menu(String[] Opciones, String Titulo){
        int i;
        int opcion=0;
        boolean Error1;
        do{
            Error1=false;
            System.out.println();
            System.out.println(Titulo);
            try {
                for(i=0;i<Opciones.length;i++){
                    System.out.println((i+1)+". "+Opciones[i] );
                }
                System.out.print("Elige una opcion: ");
                opcion=read.nextInt();
                read.nextLine();
                if(opcion>Opciones.length || opcion<1){
                    Error1=true;
                    System.out.println(Error[0]);
                }
            }catch (Exception e){
                Error1=true;
                System.out.println(Error[0]);
            }
        }while (Error1);
        return opcion;
    }
    private static String[] Rellenar_lista(String[]lista){
        for(int i=0;i<lista.length;i++){
            juego=(Tablero) juegos.get(i);
            lista[i]=juego.ID_game;
        }
        return lista;
    }
    private static void Coloca_fixa(){
        System.out.println("Esta es la situacion actual del juego: ");
        juego.Mostrar_tabla();
        if(juego.Ver_quien_mueve()){
            System.out.println("Empieza el jugador");
            juego.Jugador_jugada(juego.ficha_jugador);
            juego.Mostrar_tabla();
            juego.IA_jugada(juego.ficha_IA);
            juego.Mostrar_tabla();
        }else{
            System.out.println("Empieza la IA");
            juego.IA_jugada(juego.ficha_IA);
            juego.Mostrar_tabla();
            System.out.println("Ahora juega el jugador");
            juego.Jugador_jugada(juego.ficha_jugador);
            juego.Mostrar_tabla();
        }
    }
    private static String Traducir_ficha(int ficha){
        if(ficha==1){
            return "X";
        }else if(ficha==0){
            return "O";
        }else{
            return "N";
        }
    }
}
