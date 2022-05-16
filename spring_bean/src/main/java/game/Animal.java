package game;

public class Animal implements Unit {

    // String name;
    Weapon weapon;

    public Animal (Weapon weapon){
        this.weapon = weapon;
    }


    // @Override
    // public String getName() {
    //     return "Animal: " + name;
    // }

    @Override
    public Weapon getWeapon(){
        System.out.println(weapon + "Animal");
        return weapon;
    }
    
}
