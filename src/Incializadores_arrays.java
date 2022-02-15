// La parte positiva de este objeto es que puedo utilizarlo en otros programas de momento he incluido las variables
// mas comunes pero puedo añadir mas funciones para otras variables.


public class Incializadores_arrays {
    public static boolean [] Iniciar(boolean[] variable, boolean valor){
        int i;
        boolean resultado[]=new boolean[variable.length];
        for(i=0;i<resultado.length;i++){
            resultado[i]=valor;
        }
        return resultado;
    }
    public static int[]Iniciar (int[] variable, int valor){
        int i;
        int resultado[]=new int[variable.length];
        for(i=0;i<resultado.length;i++){
            resultado[i]=valor;
        }
        return resultado;
    }
    public static double[] Iniciar (double[] variable, double valor){
        int i;
        double resultado[]=new double[variable.length];
        for(i=0;i<resultado.length;i++){
            resultado[i]=valor;
        }
        return resultado;
    }
    public static char[] Iniciar (char[] variable, char valor){
        int i;
        char resultado[]=new char[variable.length];
        for(i=0;i<resultado.length;i++){
            resultado[i]=valor;
        }
        return resultado;
    }
    public static String[]Iniciar(String[] variable, String valor){
        int i;
        String resultado[]=new String[variable.length];
        for(i=0;i<resultado.length;i++){
            resultado[i]=valor;
        }
        return resultado;
    }
    public static void Iniciar(Tablero.celda[][] variable){
        for(int x=0;x<variable.length;x++){
            for(int y=0;y<variable[x].length;y++){
                variable[x][y] = new Tablero.celda(new boolean[]{false,false});
            }
        }
    }
    //Se me ha ocurrido una manera de inicializar un array multidimensional da igual las dimensiones
    //para hacerlo es necesario que la funcion se llame a si misma hasta que encuentre una dimension que
    //no contenga arrays cuando eso ocurra la funcion identificara el tipo de variable y llamara a la
    //funcion adecuada para inicializarla para efectuar la tarea tendre que practicar mas con la variable object un
    //tipo de variable capaz de almacenar muchas si no todas las variables que he visto. Tendre que investigar
    //mas sobre la variable.
}
