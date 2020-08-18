
package pe.edu.unmsm.sistemas.akka03;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedAbstractActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import java.util.logging.Level;
import sun.util.logging.PlatformLogger;


/**
 *
 * @author allen
 */
public class Armero extends UntypedAbstractActor{
    
    private ActorRef logistico;
    private ActorRef soldado;
    LoggingAdapter log = 
            Logging.getLogger(
             this.getContext().getSystem(),
             this
    );
    public enum mensajes{
        FABRICA_ARMA,
        MATERIALES_LISTO   
    }
    
    @Override
    public void preStart() throws Exception {
        super.preStart();
        logistico =
            this.getContext().actorOf(
                    Props.create(Logistico.class),
                    "Logistico01"              
        );
    }
    
    @Override
    public void onReceive(Object message) throws Throwable {
        if (message == mensajes.FABRICA_ARMA){
            log.info("[Armero] recibio la orden");
            soldado = this.getSender();
            logistico.tell(Logistico.mensajes.COMPRA_MATERIALES, this.getSelf());
        }else if (message == mensajes.MATERIALES_LISTO){          
            fabricarArma();
            log.info("[Armero] ya tiene el arma lista");
            soldado.tell(Soldado.mensajes.ARMA_LISTA, this.getSelf());

        }
    }
    
    private void fabricarArma(){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException ex) {
            log.info("Error",null,ex);
        }
        
        return;
    }
    
}
