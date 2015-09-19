/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package searchdetran;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;

/**
 *
 * @author Guto L
 */
public class Cliente {
    
    public static void main(String [] args){
        
        boolean chek = false;
        boolean trocarIP = false;
        boolean enviar = true;
        
        while(enviar){
            try {
             
            Socket conexao;
            
            
        
            } catch (Exception e) {
            
            
            }
        }//fim do while
        
       
       
    
    }
    
    
    public void descobreServidor(){
    
        int porta = 5000;
        String broadcast = "255.255.255.255";
        
                
                
        try {
            
            InetAddress endereco = InetAddress.getByName(broadcast);
            DatagramSocket socket = new DatagramSocket(porta,endereco);
            
            String pergunta = "quem eh servidor?";
            byte mensagem[] = pergunta.getBytes();
            
            DatagramPacket pacote = new DatagramPacket(mensagem,mensagem.length,endereco,porta);
            socket.send(pacote);
            
            DatagramPacket resposta = new DatagramPacket (mensagem,mensagem.length);
            socket.receive(resposta);
            InetAddress IP_servidor = resposta.getAddress();
            
            socket.close();
             
            
            
        } catch (Exception e) {
            e.printStackTrace();
            
        }
        
        
    
    }
    
    
}
