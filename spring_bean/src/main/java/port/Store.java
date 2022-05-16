package port;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.logging.Logger;
import java.util.stream.Stream;

public class Store{

    private static final Logger logger = Logger.getLogger(Store.class.getName());
    static LinkedList <Goods> storeDict = new LinkedList<>();
    static Integer numberShip = 1;
    static Random rnd = new Random();
    static String[] goodsName = new String[]{"Wood", "Metal", "Paper", "Plastic", "Glass"};
    static ArrayBlockingQueue<Goods> queueProducer =  new ArrayBlockingQueue<>(10);
    public ArrayBlockingQueue<Ship> queueShip = new ArrayBlockingQueue<>(10);

    public synchronized void addGoodsToStore(Goods[] goods){
        Integer i = 0;
        
        // String nameStore = this.getClass().toString();
        
        for (Goods good : goods){
            for (Goods elm : storeDict)
                if (good.compareTo(elm) > 0) {
                    storeDict.get(storeDict.indexOf(elm)).amount += good.amount;

                    logger.info("Add to store " + good.name +" - " + good.amount + ". Total - " + storeDict.get(storeDict.indexOf(elm)).amount);
                    i = 1;
                    break;
                }

            if (i == 0){
                storeDict.add(good);
                logger.info(good.name + " - " + good.amount +" add to store");
            }
        }
        try{
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        notifyAll();
    }

    public synchronized boolean emptyQueueShip(){
        return queueShip.isEmpty();
    }
    
    public synchronized void shipArrive(){
        
        try{
            while (queueShip.size() == 10)
                wait();
        } catch (InterruptedException e){
            e.printStackTrace();
        }

        Ship ship = new Ship(numberShip);
        queueShip.add(ship);
        logger.info("Ship# " + numberShip + " is arrived. Need to load:  " + ship.getOrder() + "Ship in queue - " + queueShip.size());
        numberShip++;
        try{
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        notifyAll();
    }
    
    public synchronized void loadShip(){

        try{
            while (queueShip.isEmpty() || storeDict.size() < 5)
                wait();
        } catch (InterruptedException e){
            e.printStackTrace();
        }
        
        Ship ship = queueShip.poll(); 
        for (Goods goodOrder : ship.order)
            for (Goods good : storeDict)
                if (goodOrder.name.equals(good.name)){
                
                    try{
                        while (good.amount < goodOrder.amount)
                            wait();
                    } catch (InterruptedException e){
                        e.getMessage();
                    }
                
                    storeDict.get(storeDict.indexOf(good)).amount -= goodOrder.amount;
                    logger.info("Kran# " + Thread.currentThread().getId() +
                                " Load on ship " + ship.number +
                                " good: " + goodOrder.name + 
                                " - " + goodOrder.amount +
                                ". Need load - " + ship.order.length +
                                ". Total in store - " + storeDict.get(storeDict.indexOf(good)).amount);

                    ship.cargo.add(goodOrder);

                    try{
                        Thread.sleep(900);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                
        logger.info("Ship# " + ship.number + " load " + ship.getCargo() + ". Ship out from port.");       
        notifyAll();
    }
}