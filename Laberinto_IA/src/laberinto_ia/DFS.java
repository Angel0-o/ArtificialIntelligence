/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laberinto_ia;

import java.util.LinkedList;
import java.util.ListIterator;

/**
 *
 * @author Angel
 */
public class DFS {
    
    public static int[][] searchPath(int[][] labe, int startX, int startY, int endX, int endY, int jumpPoint)
    {
        int[] left = new int[2],right = new int[2],up = new int[2],down = new int[2];
        int[] pos = new int[2];
        int[] end = new int[2];
        LinkedList<int[]> visitedNodes = new LinkedList<>();
        LinkedList<int[]> neighborNodes = new LinkedList<>();
        LinkedList<int[]> possibleNodes = new LinkedList<>();
        left[0] = startX - 1; left[1] = startY;
        up[0] = startX;up[1] = startY - 1;
        right[0] = startX + 1;right[1] = startY;
        down[0] = startX;down[1] = startY + 1;
        neighborNodes.add(left);
        neighborNodes.add(up);
        neighborNodes.add(right);
        neighborNodes.add(down);
        pos[0] = startX;pos[1] = startY;
        end[0] = endX;end[1] = endY;
        possibleNodes = getValidNodeList(neighborNodes, labe);
        while(pos != end)
        {
            System.out.println("Recorriendo nodos adyacentes..");
            //System.out.println("\t" + possibleNodes.getFirst()[0] + "," + possibleNodes.getFirst()[1]);
            showList(possibleNodes);
            if (jumpPoint > 0) {
                if (labe[possibleNodes.getFirst()[0]][possibleNodes.getFirst()[1]] == 3) {
                    pos = possibleNodes.getFirst();
                    visitedNodes.add(pos);
                    labe[possibleNodes.getFirst()[0]][possibleNodes.getFirst()[1]] = 4;
                    jumpPoint--;
                }else if (labe[possibleNodes.getFirst()[0]][possibleNodes.getFirst()[1]] == 0) {
                    pos = possibleNodes.getFirst();
                    visitedNodes.add(pos);
                    labe[possibleNodes.getFirst()[0]][possibleNodes.getFirst()[1]] = 4;
                }
            }else {
                ListIterator li = possibleNodes.listIterator();
                while(li.hasNext()){
                    if (labe[possibleNodes.getFirst()[0]][possibleNodes.getFirst()[1]] == 0) {
                        pos = possibleNodes.getFirst();
                        visitedNodes.add(pos);
                        labe[possibleNodes.getFirst()[0]][possibleNodes.getFirst()[1]] = 4;
                    }
                }
            }
            if (possibleNodes.size() == 0)
            {
                System.out.println("\tRetornando camino");
                ListIterator li = visitedNodes.listIterator();
                while (li.hasNext()) {
                    if (labe[possibleNodes.getFirst()[0]][possibleNodes.getFirst()[1]] == 4) {
                        pos = possibleNodes.getFirst();
                        visitedNodes.add(pos);
                        System.out.println("\tRetornando camino");
                        //labe[possibleNodes.getFirst()[0]][possibleNodes.getFirst()[1]] = 4;
                        neighborNodes.clear();
                        neighborNodes.add(right);
                        neighborNodes.add(up);
                        neighborNodes.add(left);
                        neighborNodes.add(down);
                        //return labe;
                    }
                }
            }
            left[0] = pos[0] - 1;
            left[1] = pos[1];
            up[0] = pos[0];
            up[1] = pos[1] - 1;
            right[0] = pos[0] + 1;
            right[1] = pos[1];
            down[0] = pos[0];
            down[1] = pos[1] + 1;
            possibleNodes = getValidNodeList(neighborNodes, labe);
        }
        return labe;
    }
    
    public static boolean isValidNode(int[] index, int[][] labe)
    {
        int x,y;
        x = index[0];
        y = index[1];
        //Index validation
        if (x > 0 && x < labe.length && y > 0 && y < labe.length) {
            if (labe[x][y] == 0 | labe[x][y] == 3) {
                return true;
            }
        }
        return false;
    }
    
    public static void showList(LinkedList<int[]> list)
    {
        ListIterator li = list.listIterator();
        int[] x;
        while(li.hasNext())
        {
            x = (int[]) li.next();
            System.out.println("[ " + x[0] + "," + x[1]);
        }System.out.println("]");
    }
    
    public static LinkedList<int[]> getValidNodeList(LinkedList<int[]> list, int[][] labe)
    {
        LinkedList<int[]> auxList = new LinkedList<>();
        ListIterator li = list.listIterator();
        int[] x;
        //Index validation
        while(li.hasNext()){
            x = (int[]) li.next();
            System.out.println(": " + x[0] + "," + x[1]);
            if (x[0] > 0 && x[0] < labe.length && x[1] > 0 && x[1] < labe[0].length) {
                if (labe[x[0]][x[1]] == 3){
                    auxList.add(x);
                    System.out.println("Entre 3");
                }
            }
        }li = list.listIterator();
        while(li.hasNext()){
            x = (int[]) li.next();
            System.out.println("0: " + x[0] + "," + x[1]);
            if (x[0] > 0 && x[0] < labe.length && x[1] > 0 && x[1] < labe[0].length) {
                if (labe[x[0]][x[1]] == 0){
                    auxList.add(x);
                    System.out.println("Entre 0");}
            }
        }
        return auxList;
    }
}
