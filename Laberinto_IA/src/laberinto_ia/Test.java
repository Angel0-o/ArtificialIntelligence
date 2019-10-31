/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laberinto_ia;

import static java.lang.Thread.sleep;
import java.util.Random;

/**
 *
 * @author Angel
 */
public class Test {
    public static void main(String[] args) throws InterruptedException
    {
        Random ran = new Random(1);
        while(true)
        {
            int n = Math.abs(ran.nextInt()%15);
            System.out.println("Numero: " + n);
            sleep(2000);
        }
    }
}
