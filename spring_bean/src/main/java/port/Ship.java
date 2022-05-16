package port;

import java.util.LinkedList;

public class Ship {

    LinkedList <Goods> cargo = new LinkedList<>();
    Goods[] order;
    Store store;
    Integer number;

    public Ship (Integer number){
        this.order = Goods.createGoods();
        this.number = number;
    }

    public String getCargo(){
        StringBuilder strCargo = new StringBuilder();
        for (Goods good : cargo)
            strCargo.append(good.name + " - " + good.amount + "; ");
        
        return strCargo.toString(); 
    }

    public String getOrder(){
        StringBuilder strOrder = new StringBuilder();
        for (Goods good : order)
            strOrder.append(good.name + " - " + good.amount + "; ");
        
        return strOrder.toString(); 
    }
}