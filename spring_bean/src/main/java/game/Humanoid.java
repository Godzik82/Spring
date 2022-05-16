package game;

public class Humanoid implements Unit {

    // String name;
    Weapon weapon;

    public Humanoid (Weapon weapon){
        this.weapon = weapon;
    }

    // @Override
    // public String getName() {
    //     return "Humanoid: " + name;
    // }

    public void init(){
        System.out.println("Инициализация объекта ...");
    }

    public void del(){
        System.out.println("Удаление объекта ...");
    }

    @Override
    public Weapon getWeapon(){
        // System.out.println(weapon + " Humanoid");
        return weapon;
    }

    public static Humanoid factory(){
        return new Humanoid(new Weapon(20));
    }
    
}
