package port;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import java.util.logging.Logger;

public class Kran implements Runnable{


    ArrayBlockingQueue<Goods> queueLoadGoods =  new ArrayBlockingQueue<>(5);
    private static final Logger logger = Logger.getLogger(Kran.class.getName());
    static Random rnd = new Random();
    Store store;
    Ship ship;
    ExecutorService service = Executors.newFixedThreadPool(3);

    public Kran(Store store){
        this.store = store;
    }

    @Override
    public void run() {
        logger.info("Kran# " + Thread.currentThread().getId() + " start");
        while(true){
            
            store.loadShip();
            try{
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
