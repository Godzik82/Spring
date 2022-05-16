package port;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Producer implements Runnable {

    Store store;
    Goods[] goods;
    private static final Logger logger = Logger.getLogger(Store.class.getName());

    public Producer (Store store){
        this.store = store;
    }

    @Override
    public void run(){

        try {
        
            // Unit unit1 = context.getBean("AppHumanoidBean", Humanoid.class);
            // Unit unit2 = context.getBean("AppHumanoidBean", Humanoid.class);
            // System.out.println(unit1.getWeapon() + " " + unit1);
            // System.out.println(unit2.getWeapon() + " " + unit2);
            // System.out.println(unit1 == unit2);
            // System.out.println(unit1.getWeapon() == unit2.getWeapon());


            logger.info("Start Producer");
            while (true) {
                // Goods[] goods = context.getBean("Goods", Goods.class);
                Goods[] goods = Goods.createGoods();
                store.addGoodsToStore(goods);
                Thread.sleep(1000);
            } 
        } catch (InterruptedException e) {
            logger.log(Level.WARNING, "Stop produce", e);
            Thread.currentThread().interrupt();
        }
    }
}
