
package Servidor;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

//RENAN "pej3163" //fan125
//RENATO "kls2940" //fan125
//ALCINO1 "pfv4175" //carro branco --- tretando
//ALCINO2 "kfg5169" // fiat preto --- tretando
//ERIK "pek3471" //moto de erik
//BIANCA "peu0189" // carro de bianca --- tretando
//MADRINHA "pfv9044" 
//ANA "pge7113"
//MAYU "pfu6110"
//BARROS "klp0875"
//GUTO "nis3236" //--- tretando

/**
 * Classe com método estático que consulta no site do Detran a situação do veículo.
 * Parâmetro: placa do veículo.
 * 
 * Exemplo de uso.: Detran.consulta("Nis-3236");
 * 
 * @author arthur
 */

public class Detran {
    
    static String consulta(String placa) {
        
        String importante="";
        ArrayList<String> aux = new ArrayList<>();
        boolean legend=false;
        URL url;
        
        try {
            
            url = new URL("http://online4.detran.pe.gov.br//ServicosWeb/Veiculo/frmDetalhamentoDebitos.aspx?pPlaca="+ placa.replace("-", "") +"&pExtrato=N&pTerceiros=I&pPlacaOutraUF=N");

            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
            
            for (String line; (line = reader.readLine()) != null;) {
                
                if ((legend && !line.contains("value=\"Imprimir Detalhamento de Débitos\"")) || line.contains("<legend>Detalhamento de Débitos")){
                    legend=true;
                    
                    for (int i=0; i<line.length(); i++){
                        
                        if (!line.substring(i, i+1).equals("<")){
                            
                            importante = importante + line.substring(i, i+1);
                        }
                        
                        else if (line.substring(i, i+1).equals("<")){
                            
                            while(true){
                                
                                i++;
                                if (line.substring(i, i+1).equals(">")) break;
                            }
                            
                            aux.add(importante);
                            importante="";
                        }
                    }
                }
                
                for (int i=0; i<aux.size(); i++){
                    if (aux.get(i).contains("   ") || aux.get(i).contains("&nbsp") || aux.get(i).contains("\t") || aux.get(i).length() < 2)                         
                        aux.remove(i);
                }  
            }    
        
        } catch (Exception ex) {    
            //algum tratamento de erro aq
        }
        
        for (int i=0; i<aux.size(); i++){
            
            if (aux.get(i).contains("Detalhamento de Débitos")) importante += aux.get(i) + aux.get(i+1) + "\n";
            
            else if (aux.get(i).contains("Restrição")){
                if (aux.get(i+1).contains("LICENCIAMENTO")) {
                    importante += "RESTRIÇÃO: NADA CONSTA\n"; i--;
                }
                else importante += "RESTRIÇÃO: "+ aux.get(i+1) +"\n";
            }
            
            else if (aux.get(i).contains("LICENCIAMENTO")) {
                if(!aux.get(i+1).contains("NADA CONSTA")) importante += aux.get(i).replace(":", "") + ": HÁ DÉBITOS\n";
                else importante += aux.get(i).replace(":", "") + ": " + aux.get(i+1) + "\n";
            }
            
            else if (aux.get(i).contains("TAXAS DETRAN")) importante += aux.get(i).replace(":", "") + ": " + aux.get(i+1) + "\n";
            
            else if (aux.get(i).contains("Débitos")) {
                importante += "\nDébitos\n"; i--;
            }
            
            else if (aux.get(i).contains("MULTAS")) importante += aux.get(i).replace(":", "") + ": " + aux.get(i+1) + "\n";
            
            else if (aux.get(i).contains("Total em Cota Única")) {
                importante += aux.get(i).replace(":", "") + ": " + aux.get(i+1) + "\n"; i--;
            }
            
            i++;
        }
        
        return importante;
    }
}

