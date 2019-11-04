/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laberinto_ia;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Angel
 */
public class Tablero extends JFrame{
    
    int pathX = 1; //recorre en columnas
    int pathY = 14; //reccorre en filas
    List<Integer> path = new ArrayList<Integer>();
    private JPanel contentPane;
    
    int [][] laberinto = 
        { {1,1,1,1,1,1,1,1,1,1,1,1,1},
          {1,0,0,0,0,0,0,0,0,0,0,0,9},
          {1,2,0,0,0,2,2,0,0,0,2,2,1},
          {1,0,2,0,0,0,0,0,0,0,0,0,1},
          {1,0,0,0,0,0,0,0,0,0,0,2,1},
          {1,2,0,0,2,0,0,2,0,2,0,0,1},
          {1,2,0,0,0,2,2,0,0,2,0,0,1},
          {1,0,0,0,0,0,0,0,0,0,0,0,1},
          {1,0,2,2,0,0,0,2,0,0,2,2,1},
          {1,0,0,0,0,0,0,0,0,0,0,0,1},
          {1,2,0,2,0,0,0,0,0,0,0,0,1},
          {1,2,0,0,0,0,0,0,2,0,0,2,1},
          {1,0,0,0,2,0,2,0,0,2,0,0,1},
          {1,0,0,2,0,0,0,0,0,2,0,0,1},
          {1,9,1,1,1,1,1,1,1,1,1,1,1}
        };
    
     public Tablero()
     {
        setTitle("LABERINTO");
        setSize(1280,720);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        //Añadiendo los botones
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JButton puntoSalto = new JButton("Regla 1");
        puntoSalto.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                generaPuntoSalto(15);
            }
        });
        puntoSalto.setBounds(35, 482, 90, 25);
        contentPane.add(puntoSalto);
        
        JButton Resolver = new JButton("Regla 2");
        Resolver.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                generaPuntoSalto(15);
            }
        });
        Resolver.setBounds(135, 482, 90, 25);
        contentPane.add(Resolver);
        
        JButton resetTab = new JButton("Reset");
        resetTab.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                reset();
            }
        });
        resetTab.setBounds(235, 482, 90, 25);
        contentPane.add(resetTab);
        
        JButton resolver = new JButton("Resolver");
        resolver.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //Bien vamos a probar DepthFirst comprobando el primer y el último elemento de la lista de rutas
                DFS_new.buscarPath(laberinto, 1, 13, path,5);
                int pathIndex = path.size() - 2;
                System.out.println(pathIndex);
                System.out.println(path);
                //DFS_old.searchPath(laberinto, 14, 1, 1, 13, 5);
                repaint();
            }
        });
        resolver.setBounds(335, 482, 90, 25);
        contentPane.add(resolver);
        
        setBounds(0,0,800,600);
     }
     
     public void paint(Graphics g) {
        super.paint(g);
        g.translate(50, 50);
        int x = 30, y = 30;
        
        //Dibujamos el laberinto
        for (int fila = 0; fila < laberinto.length; fila++) {
            for (int columna = 0; columna < laberinto[0].length; columna++) {
                Color color;
                switch(laberinto[fila][columna]){
                    case 1: color = Color.BLACK; break;
                    case 2: color = Color.CYAN; break;
                    case 4: color = Color.GREEN; break;
                    case 9: color = Color.RED; break;
                    default : color = Color.WHITE;
                }
                //Pintamos los cuadrados(posicion x, posicion y, anchura y altura )
                g.setColor(color);
                g.fillRect(30*columna, 30*fila, 30, 30);
                g.setColor(color.BLACK);
                g.drawRect(30*columna, 30*fila, 30, 30);
                if(laberinto[fila][columna] == 3)
                {
                    //Ahora vamos a dibujar la bolita en el tablero
                    g.setColor(Color.red);
                    g.fillOval(columna * 30, fila * 30, 28, 28);
                }
            }        
        }
        /*
         //finalmente, vamos a dibujar la lista de rutas
         for (int p = 0; p < path.size(); p += 2) {
             int pathX = path.get(p);
             int pathY = path.get(p + 1);
             g.setColor(Color.GREEN);
             g.fillRect(pathX * 30, pathY * 30, 30, 30);
             g.setColor(Color.BLACK);
             g.drawRect(30 * pathX, 30 * pathY, 30, 30);
         }
        g.setColor(Color.blue);
        g.fillOval(pathX*30, pathY*30, 30, 30);
        */
    }
     
    public void drawAgent(Graphics h,int x, int y)
    {
        pathX = x;
        pathY = y;
        repaint();
    }
    
    public void generaPuntoSalto(int n)
    {
        Random ran = new Random();
        int x, y;
        for(int i = 0;i < n;i ++)
        {
            x = Math.abs(ran.nextInt()%laberinto.length);
            y = Math.abs(ran.nextInt()%laberinto[0].length);
            if(laberinto[x][y] == 0)
                laberinto[x][y] = 3;
            else
                i --;
        }
        repaint();
    }
    
    public void reset()
    {
        int pathX = 1;
        int pathY = 14;
        int[][] laberinto
            = {{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 9},
            {1, 2, 0, 0, 0, 2, 2, 0, 0, 0, 2, 2, 1},
            {1, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 1},
            {1, 2, 0, 0, 2, 0, 0, 2, 0, 2, 0, 0, 1},
            {1, 2, 0, 0, 0, 2, 2, 0, 0, 2, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 2, 2, 0, 0, 0, 2, 0, 0, 2, 2, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 2, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 2, 0, 0, 0, 0, 0, 0, 2, 0, 0, 2, 1},
            {1, 0, 0, 0, 2, 0, 2, 0, 0, 2, 0, 0, 1},
            {1, 0, 0, 2, 0, 0, 0, 0, 0, 2, 0, 0, 1},
            {1, 9, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
            };
        this.pathX = pathX;
        this.pathY = pathY;
        this.laberinto = laberinto;
        repaint();
    }
    
    public static void main(String args[]) throws InterruptedException 
    {
        Tablero t1 = new Tablero();
        t1.setVisible(true);
        //Graphics g = t1.getGraphics();
        //g.dispose();
        Thread.sleep(1000);
        //t1.drawAgent(g, 1, 12);
        /*
        int[][] laber = 
                {{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 9},
                {1, 2, 0, 0, 3, 2, 2, 3, 0, 3, 2, 2, 1},
                {1, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 2, 1},
                {1, 2, 0, 0, 2, 0, 0, 2, 0, 2, 0, 0, 1},
                {1, 2, 0, 0, 3, 2, 2, 3, 0, 2, 0, 0, 1},
                {1, 3, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 1},
                {1, 3, 2, 2, 3, 0, 0, 2, 0, 3, 2, 2, 1},
                {1, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 2, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 2, 0, 0, 0, 0, 0, 0, 2, 3, 0, 2, 1},
                {1, 3, 0, 0, 2, 0, 2, 0, 0, 2, 0, 0, 1},
                {1, 0, 3, 2, 0, 0, 0, 0, 0, 2, 0, 0, 1},
                {1, 9, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
                };
        t1.laberinto = laber;
        t1.repaint();
        */
        //Thread.sleep(1000);
        //t1.drawAgent(g, 2, 12);
        /*
        Thread.sleep(1000);
        t1.dibujaPuntos(g, laber);
        Thread.sleep(1000);
        t1.drawJumpPoint(g, 4, 3);
        t1.repaint();
        g.setColor(Color.red);
        g.fillOval(4*30, 3*30, 25, 25);
*/
        
        
        //t1.drawAgent(g, 4, 3);
    }
}
