/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servidor;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Guto L
 */
public class ThreadConexao extends Thread{
    
        Socket conexao;

    public ThreadConexao() {
        
    }

    public ThreadConexao(Socket conexao) {
        this.conexao = conexao;
    }
        
    
    public void run(){
    
            try {
                
                // ISSO AQUI É A PLACA DO CARRO QUE O CLIENTE MANDOU
                InputStream entrada = this.conexao.getInputStream(); 
                
            } catch (IOException ex) {
                Logger.getLogger(ThreadConexao.class.getName()).log(Level.SEVERE, null, ex);
            }
    
    }
        
    
}
