package game;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

    public static void main(String[] args){

        try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml")){
        
            Unit unit1 = context.getBean("AppHumanoidBean", Humanoid.class);
            Unit unit2 = context.getBean("AppHumanoidBean", Humanoid.class);
            System.out.println(unit1.getWeapon() + " " + unit1);
            System.out.println(unit2.getWeapon() + " " + unit2);
            System.out.println(unit1 == unit2);
            System.out.println(unit1.getWeapon() == unit2.getWeapon());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
}
