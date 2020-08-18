/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.edu.unmsm.sistemas.akka03;

import akka.actor.ActorRef;
import akka.actor.UntypedAbstractActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author allen
 */
public class Logistico extends UntypedAbstractActor{
    
    public enum mensajes{
        COMPRA_MATERIALES,      
    }
    
    LoggingAdapter log = 
            Logging.getLogger(
             this.getContext().getSystem(),
             this
    );
    
    @Override
    public void onReceive(Object message) throws Throwable {
        if(message == mensajes.COMPRA_MATERIALES){
            log.info("[Logistico] comprando materiales");
            compraMateriales();
            ActorRef armero = this.getSender();
            armero.tell(Armero.mensajes.MATERIALES_LISTO, this.getSelf());
        }
    }
    
    private void compraMateriales(){
        log.info("[Logistico] comprando materiales");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Logistico.class.getName()).log(Level.SEVERE, null, ex);
        }
        return;
        
    }
        
}
