/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package searchdetran;

import java.io.OutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;

/**
 *
 * @author Guto L
 */
public class Cliente {
  
        boolean chek = true;
        boolean trocarIP = false;
        boolean enviar = true;
        int porta = 2500;// PORTA QUE O SERVIDOR VAI ESCUTAR, BLZ?
        
    public void pedirPlaca(){
    
        int conectar = 0;
        InetAddress IP_servidor = null;
        
        while(chek == true){
        
            try {
        
                if(conectar == 0){
                    
                    System.out.println("Tentando pegar ip...");
                    IP_servidor = this.descobreServidor();
                    
                }
                
                System.out.println("Consegui");
                Socket conexao = new Socket(IP_servidor,this.porta);
                
                String placa = "PLA-0000"; // DEPOIS IMPLEMENTA PRA PEGAR DO USUARIO
                OutputStream stream = conexao.getOutputStream();
                
                stream.write(placa.getBytes());
                        
                conexao.close();
                chek = false;
                
            } catch (Exception e) {
                System.out.println("Problemas");
                try {
                    
                   Thread.sleep(3000);
                   conectar++;
                   
                   if(conectar > 3){
                   
                       conectar = 0;
                       
                   }
                   
                } catch (Exception e1) {
                }
                
                
            }
            
            
        }// fim do while
        
        
        
        
    }//fim do método de pedir placa    
   
    public InetAddress descobreServidor(){
        
        String broadcast = "255.255.255.255";
                
        try {
            
            InetAddress endereco = InetAddress.getByName(broadcast);
            DatagramSocket socket = new DatagramSocket(2500, endereco);
            socket.setBroadcast(true);
            
            String pergunta = "quem eh servidor?";
            byte mensagem[] = pergunta.getBytes();
            
            DatagramPacket pacote = new DatagramPacket(mensagem,mensagem.length,endereco,this.porta);
            socket.send(pacote);
            
            DatagramPacket resposta = new DatagramPacket(mensagem,mensagem.length);
            socket.receive(resposta); 
            InetAddress IP_servidor = resposta.getAddress();
            
            socket.close();
            
            System.out.println("OK");
            return IP_servidor;
                    
            
            
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("fudeu");
        }
            return null;
        
        
    
    }
    
    
}
