
package pe.edu.unmsm.sistemas.akka03;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedAbstractActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author allen
 */
public class Soldado extends UntypedAbstractActor {
    
    public enum mensajes {
        ARMA_LISTA,
        MENSAJE_DESCONOCIDO       
    }

    @Override
    public void preStart() throws Exception {
        log.info("[Soldado] Iniciando...");
        super.preStart(); //To change body of generated methods, choose Tools | Templates.
        final ActorRef armero =
            getContext().actorOf(
                    Props.create(Armero.class),
                    "Armero1"              
        );
        log.info("[Soldado] Enviando mensaje al armero...");
        armero.tell(Armero.mensajes.FABRICA_ARMA, this.getSelf());
    }
    
    LoggingAdapter log = 
            Logging.getLogger(
             this.getContext().getSystem(),
             this
    );
    
    @Override
    public void onReceive(Object message) throws Throwable {
        if(message == mensajes.ARMA_LISTA){
            log.info("[Soldado] el arma esta lista...");
            dispararArma();
            log.info("[Soldado] Terminando...");
            this.getContext().stop(this.getSelf());
        }
    }
    
    private void dispararArma(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Soldado.class.getName()).log(Level.SEVERE, null, ex);
        }      
    }
}
