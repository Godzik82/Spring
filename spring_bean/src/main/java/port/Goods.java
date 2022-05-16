package port;

import java.util.LinkedList;
import java.util.Random;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Goods implements Comparable<Goods>{

    String name;
    Integer amount;
    static String[] goodsName = new String[]{"Wood", "Metal", "Paper", "Plastic", "Glass"};
    // LinkedList<String> goodsName;
    static Random rnd = new Random();
    // static ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("portContext.xml", LinkedList.class);
    
    public Goods(String name, Integer amount){
        this.name = name;
        this.amount = amount;
        this.goodsName = goodsName;
    }

    // @Override
    // public boolean equals(Object o){
    //     if (name.hashCode() == (Goods o).hashCode())
    // }
   
    // @Override
    // public int hashCode(){
    //     return name.hashCode();
    // }

    @Override
    public int compareTo(Goods g) {
        if (name.equals(g.name))
            return 1;
        else return 0;
    }

    public static Goods createGood() {
        return new Goods(goodsName[rnd.nextInt(5)], rnd.nextInt(1, 10));
    }

    public static Goods[] createGoods(){
        Integer vol = rnd.nextInt(1,5);
        Goods[] createGoods = new Goods[vol];
        Integer i = 0;
        LinkedList<String> dictGoodsName = new LinkedList<>();
        
        try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("portContext.xml")){ 
        
        do {
            createGoods[i] = context.getBean("Good", Goods.class);
            // createGoods[i] = Goods.createGood();
            if (dictGoodsName.indexOf(createGoods[i].name) == -1){
                dictGoodsName.add(createGoods[i].name);
                i++;
            }
            } while (i < vol);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return createGoods;
    }
}
