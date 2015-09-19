/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servidor;

import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Guto L
 */
public class Servidor {
    
    public void main(String [] args){
        
            try {
                
                ServerSocket servidor = new ServerSocket(5000);
                System.out.println("Servidor Pronto...");
                
                while(true){
                
                
                    Socket conexao = servidor.accept();
                    
                    ThreadConexao t = new ThreadConexao();
                    t.start();
                    
                }
                
                
            } catch (Exception e) {
            }
            
            
        
        }
    
}
