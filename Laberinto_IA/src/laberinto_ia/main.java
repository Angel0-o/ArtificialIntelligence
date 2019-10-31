/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laberinto_ia;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author theol
 */
public class main extends javax.swing.JFrame {

    /**
      * Convenciones:
      * Laberinto [fila] [col]
      * 
      * Valores: 
      * 0 = nodo no visitado
      * 1 = pared (bloqueada)
      * 2 = nodo visitado
      * 9 = nodo objetivo
      * 
      * Las fronteras se deben rellenar con "1" para anular la excepción ArrayIndexOutOfBounds.
      */
    
    int pathX = 1; //recorre en columnas
    int pathY = 1; //reccorre en filas
    
    private net.sourceforge.jFuzzyLogic.FIS _FIS;
    private net.sourceforge.jFuzzyLogic.plot.JDialogFis dialogoFIS;
    private net.sourceforge.jFuzzyLogic.plot.JFuzzyChart chart;
    
     private static int [][] laberinto = 
        { {1,1,1,1,1,1,1,1,1,1,1,1,1},
          {1,0,1,0,1,0,1,0,0,0,0,0,1},
          {1,0,1,0,0,0,1,0,1,1,1,0,1},
          {1,0,0,0,1,1,1,0,0,0,0,0,1},
          {1,0,1,0,0,0,0,0,1,1,1,0,1},
          {1,0,1,0,1,1,1,0,1,0,0,0,1},
          {1,0,1,0,1,0,0,0,1,1,1,0,1},
          {1,0,1,0,1,1,1,0,1,0,1,0,1},
          {1,0,0,0,0,0,0,0,0,0,1,9,1}, //Y definimos el nodo objetivo como "9" en la posición (11,8)
          {1,1,1,1,1,1,1,1,1,1,1,1,1}
        };
    //private List<Integer> path= new ArrayList<Integer>();
    //private int pathIndex;
    
    public main() {
        //initComponents();
        setTitle("LABERINTO");
        setSize(640,480);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        _FIS= net.sourceforge.jFuzzyLogic.FIS.load("game.fcl",true);
        dialogoFIS=new net.sourceforge.jFuzzyLogic.plot.JDialogFis(_FIS);
        
        if(_FIS==null){
            javax.swing.JOptionPane.showMessageDialog(null, "ERROR");
            System.exit(1);
        }
        
        //Bien vamos a probar DepthFirst comprobando el primer y el último elemento de la lista de rutas
        //DFS.buscarPath(laberinto, 1, 1, path);
        //pathIndex = path.size() - 2;
        //System.out.println(pathIndex);
        //System.out.println(path);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.translate(50, 50);
        
        //Dibujamos el laberinto
        for (int fila = 0; fila < laberinto.length; fila++) {
            for (int columna = 0; columna < laberinto[0].length; columna++) {
                Color color;
                switch(laberinto[fila][columna]){
                    case 1: color = Color.BLACK; break;
                    case 9: color = Color.RED; break;
                    default : color = Color.WHITE;
                }
                g.setColor(color);
                g.fillRect(30*columna, 30*fila, 30, 30);
                g.setColor(color.BLACK);
                g.drawRect(30*columna, 30*fila, 30, 30);
            }        
        }
        
        //finalmente, vamos a dibujar la lista de rutas
        /*for (int p = 0; p < path.size(); p+=2) {
            int pathX = path.get(p);
            int pathY = path.get(p+1);
            g.setColor(Color.GREEN);
            g.fillRect(pathX*30, pathY*30, 30, 30);
        }*/
        
        //Ahora vamos a dibujar la bolita en el tablero
        g.setColor(Color.blue);
        g.fillOval(pathX*30, pathY*30, 30, 30);
    }
    
    
    //Vamos a mover una pelota a través de la ruta usando las teclas izquierda y derecha

    
    @Override
    protected void processKeyEvent(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_DOWN){
            if(e.getID()!=KeyEvent.KEY_RELEASED){
                if(laberinto[pathY+1][pathX]!=1){
                    pathY=pathY+1;
                    _FIS.setVariable("xcol", pathX);
                    _FIS.setVariable("yfil", pathY);
                    _FIS.evaluate();
        
                    double objetivo = _FIS.getVariable("objetivo").getLatestDefuzzifiedValue();
                    dialogoFIS.repaint();
        
                    System.out.println("objetivo: " +objetivo);
                    System.out.println(laberinto[pathY][pathX]);
                    System.out.println("COL: "+pathX+" FIL: "+pathY);
                }                
            }
        }else if(e.getKeyCode()==KeyEvent.VK_UP){
            if(e.getID()!=KeyEvent.KEY_RELEASED){
                if(laberinto[pathY-1][pathX]!=1){
                    pathY=pathY-1;  
                    _FIS.setVariable("xcol", pathX);
                    _FIS.setVariable("yfil", pathY);
                    _FIS.evaluate();
        
                    double objetivo = _FIS.getVariable("objetivo").getLatestDefuzzifiedValue();
                    dialogoFIS.repaint();
        
                    System.out.println("objetivo: " +objetivo);
                    System.out.println(laberinto[pathY][pathX]);
                    System.out.println("COL: "+pathX+" FIL: "+pathY);
                }                
            }
        }else if(e.getKeyCode()==KeyEvent.VK_RIGHT){
            if(e.getID()!=KeyEvent.KEY_RELEASED){
                if(laberinto[pathY][pathX+1]!=1){
                    pathX=pathX+1;  
                    _FIS.setVariable("xcol", pathX);
                    _FIS.setVariable("yfil", pathY);
                    _FIS.evaluate();
        
                    double objetivo = _FIS.getVariable("objetivo").getLatestDefuzzifiedValue();
                    dialogoFIS.repaint();
        
                    System.out.println("objetivo: " +objetivo);
                    System.out.println(laberinto[pathY][pathX]);
                    System.out.println("COL: "+pathX+" FIL: "+pathY);
                }                
            }
        }else if(e.getKeyCode()==KeyEvent.VK_LEFT){
            if(e.getID()!=KeyEvent.KEY_RELEASED){
                if(laberinto[pathY][pathX-1]!=1){
                    pathX=pathX-1;  
                    
                    _FIS.setVariable("xcol", pathX);
                    _FIS.setVariable("yfil", pathY);
                    _FIS.evaluate();
        
                    double objetivo = _FIS.getVariable("objetivo").getLatestDefuzzifiedValue();
                    dialogoFIS.repaint();
        
                    System.out.println("objetivo: " +objetivo);
                    System.out.println(laberinto[pathY][pathX]);
                    System.out.println("COL: "+pathX+" FIL: "+pathY);
                }                
            }
        }
        repaint();
        if(e.getID()!=KeyEvent.KEY_RELEASED){
            if(laberinto[pathY][pathX]==9){
                javax.swing.JOptionPane.showMessageDialog(null, "GANASTEEE!!");
                System.exit(0);
            }  
        }
        
    }
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new main().setVisible(true);
            }
        });
    }
    
    //Ahora vamos a implementar DFS (Depth First Search), Busqueda en profundidad

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
