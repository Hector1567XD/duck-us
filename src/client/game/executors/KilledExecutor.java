/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.game.executors;

import client.game.engine.GameContainer;
import client.game.nodes.OPlayer;
import client.game.nodes.Player;
import common.networking.packets.PlayerKilledPacket;
import java.util.ArrayList;

/**
 *
 * @author Ortiz pc
 */
public class KilledExecutor {
    public static void execute(GameContainer container, PlayerKilledPacket packet) {
      if (packet.getPlayerId()==0){
        Player jugador = container.getController().getNodes().findByName("Player");
        jugador.setIsDead(true);
      }else {
          // Buscando a la victima
        OPlayer victima = KilledExecutor.getJugadorById(container, packet.getPlayerId());
        
        if (victima == null) { // :) No se encontro al jugador
            return;
        }
        
        victima.setIsDead(true);
      }
      
    }

 
    public static OPlayer getJugadorById(GameContainer container, int playerId) {
      
        OPlayer currentPlayer = null;
        
        ArrayList <OPlayer> lista = container.getController().getNodes().getListByTag("Oplayer");
        
        for (OPlayer player: lista){
            if (player.getPlayerId() == playerId) {
                currentPlayer = player;
            }
        }
        return currentPlayer;
    }
        
    
    }


