package port;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Start {

    public static void main(String[] args) {
        
        // LinkedList<Goods> storeDict = new LinkedList<>();
        // storeDict.add(new Goods("Metal", 1));
        // storeDict.add(new Goods("Wood", 1));
        // storeDict.add(new Goods("Paper", 1));

        // Goods compareGood = new Goods("Glass", 1);

        // // System.out.println(compareGood.hashCode());

        // System.out.println(compareGood.compareTo(storeDict.element()));
        // try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("portContext.xml")) {
        //     Test test = new Test(list);
        start1();

    }

    public static void start1(){
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter numer of Kran:");
        Integer numKran = 0;
        try {
            numKran = Integer.parseInt(reader.readLine());
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("portContext.xml")) {
            
            // Store store = context.getBean("Store", Store.class);
            // Producer producer = new Producer(store);
            // Docker docker = new Docker(store);
            Producer producer = context.getBean("Producer", Producer.class);
            Docker docker = context.getBean("Docker", Docker.class);
        
            ExecutorService service = Executors.newFixedThreadPool(numKran);
            for (int i = 0; i < numKran; i++)
                // service.submit(new Kran(store));
                service.submit(context.getBean("Kran", Kran.class));
            
            new Thread(producer).start();
            new Thread(docker).start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}