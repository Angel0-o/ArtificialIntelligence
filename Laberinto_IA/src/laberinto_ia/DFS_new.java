/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laberinto_ia;

import java.util.List;

/**
 *
 * @author Angel
 */
public class DFS_new {
    // Si se encontró path, este método devolverá true
    // y la lista de rutas será llenada
    // así: {xn, yn, ..., x2, y2, x1, y1, x0, y0}
    // así que el orden es invertido ...
    // x e y son la posición de búsqueda inicial
    // Nota: todos los nodos visitados del array de laberinto serán llenados con 2
    // y no se limpiará automáticamente
    
    public static boolean buscarPath(int[][] laberinto,int x, int y, List<Integer> path, int jumPoint){
        
        //Finalmente, vamos a comprobar si el nodo objetivo fue alcanzado
        //Falta preguntar por el numero de puntos de salto visitados
        if(laberinto[y][x]==9){
            path.add(x);
            path.add(y);
            return true;
        }
        
        //Cuando la posición actual (y e x) es
        //Un nodo no visitado (0 o 3), entonces lo vamos a
        //Marcar como visitado (5)
        if(laberinto[y][x] == 0 | laberinto[y][x]==3){
            //System.out.println("Nodo visitado " + y + "," + x);
            laberinto[y][x]=5;
            
            //ahora vamos a visitar a todos los nodos vecinos recursivamente
            //Si se encontró ruta de acceso, vamos a rellenar la lista de rutas
            //Con la posición actual
            
            //Arriba
            int dx=-1;
            int dy = 0;      
            if(buscarPath(laberinto, x+dx,y+dy,path,jumPoint)){
                laberinto[y][x]=4;
                path.add(x);
                path.add(y);
                return true;
            }
            //Abajo
            dx= 1;
            dy = 0;  
            if(buscarPath(laberinto, x+dx,y+dy,path,jumPoint)){
                laberinto[y][x]=4;
                path.add(x);
                path.add(y);
                return true;
            }
            //Izquierda
            dx= 0;
            dy = -1;           
            if(buscarPath(laberinto, x+dx,y+dy,path,jumPoint)){
                laberinto[y][x]=4;
                path.add(x);
                path.add(y);
                return true;
            }
            //Derecha
            dx= 0;
            dy = 1;    
            if(buscarPath(laberinto, x+dx,y+dy,path,jumPoint)){
                laberinto[y][x]=4;
                path.add(x);
                path.add(y);
                return true;
            }       
        }
        return false;   
    }
}
