package port;

import java.util.logging.Logger;

public class Docker implements Runnable{
    
    private static final Logger logger = Logger.getLogger(Store.class.getName());
    Store store;

    public Docker(Store store){
        this.store = store;
    }

    @Override
    public void run() {
        logger.info("Docker start");

        while (true){
            store.shipArrive();
        } 
    }
}    
