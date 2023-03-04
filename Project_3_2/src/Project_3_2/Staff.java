package Project_3_2;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Staff {
    static String[] staffType = {"Intern", "Mechanic", "Salesperson","Driver"};
    static ArrayList<String> names = new ArrayList<>();
    static ArrayList<String>[] staff = new ArrayList[4];
    static ArrayList<String>[] staff_status = new ArrayList[4];
    static ArrayList<String>[] dep_staff = new ArrayList[4];
    static int[] normal_pay = {500, 1000, 1500, 2000};
    static ArrayList<Integer>[] total_normal_pay = new ArrayList[4];
    static ArrayList<Integer>[] bonus = new ArrayList[4];
    static ArrayList<Integer>[] total_bonus = new ArrayList[4];
    static ArrayList<Integer>[] dep_total_bonus = new ArrayList[4];
    static ArrayList<Integer>[] dep_total_normal_pay = new ArrayList[4];
    static ArrayList<Integer>[] salary = new ArrayList[4];
    static ArrayList<Integer>[] total_salary = new ArrayList[4];
    static ArrayList<Integer>[] dep_total_salary = new ArrayList[4];
    static ArrayList<Integer>[] days_worked = new ArrayList[4];
    static ArrayList<Integer>[] dep_days_worked = new ArrayList[4];
    static ArrayList<Integer> race_won = new ArrayList<>();//only for drivers
    static ArrayList<Integer> dep_race_won = new ArrayList<>();//only for drivers
    static double prob = 0;
    static ArrayList<Integer> quit_status = new ArrayList<>(3);
    static int bonus_val=0;//for individual bonus of any action
    Random rand = new Random();

    //Initializing the variables
    public void init() {
        for (int i = 0; i < 4; i++) {
            staff[i] = new ArrayList<>();
            staff_status[i] = new ArrayList<>();
            days_worked[i] = new ArrayList<>();
            total_bonus[i] = new ArrayList<>();
            bonus[i] = new ArrayList<>();
            total_normal_pay[i]= new ArrayList<>();
            salary[i] = new ArrayList<>();
            total_salary[i] = new ArrayList<>();
            dep_staff[i] = new ArrayList<>();
            dep_days_worked[i] = new ArrayList<>();
            dep_total_bonus[i] = new ArrayList<>();
            dep_total_normal_pay[i] = new ArrayList<>();
            dep_total_salary[i] = new ArrayList<>();
            quit_status.add(0);
        }
        race_won = new ArrayList<>();
        dep_race_won = new ArrayList<>();
    }
    //The following method gives a set of names for staff
    public void add_names() {
        names.addAll(Arrays.asList(
                "Emma", "Olivia", "Ava", "Isabella", "Sophia", "Charlotte", "Mia", "Amelia", "Harper", "Evelyn",
                "Abigail", "Emily", "Elizabeth", "Mila", "Ella", "Avery", "Sofia", "Camila", "Aria", "Scarlett",
                "Victoria", "Madison", "Luna", "Grace", "Chloe", "Penelope", "Layla", "Riley", "Zoey", "Nora",
                "Lily", "Eleanor", "Hannah", "Lillian", "Addison", "Aubrey", "Ellie", "Stella", "Natalie", "Leah",
                "Audrey", "Savannah", "Brooklyn", "Bella", "Claire", "Skylar", "Lucy", "Paisley", "Eva", "Anna",
                "Caroline", "Nova", "Genesis", "Emilia", "Kennedy", "Samantha", "Maya", "Willow", "Kinsley", "Naomi",
                "Aaliyah", "Elena", "Gabriella", "Aurora", "Isla", "Janine", "Adeline", "Kaylee", "Arianna", "Avery",
                "Alyssa", "Brielle", "Elliot", "Madalyn", "Hailey", "Mackenzie", "Payton", "Aubree", "Kylie", "Mikayla",
                "Reagan", "Lila", "Ariel", "Gianna", "Sawyer", "Bianca", "Isabelle", "Jasmine", "Eliana", "Michael"));
    }

    public String getName() {
        String name;
        int i = rand.nextInt(names.size());
        name = names.get(i);
        names.remove(i);
        return name;
    }
    //Adding initial staff members and their data for the first day
    public void addStaff() {
        staff[0].add("Desmond");
        staff[0].add("Altair");
        staff[0].add("Ezio");
        staff[1].add("Haytham");
        staff[1].add("Conner");
        staff[1].add("Edward");
        staff[2].add("Shay");
        staff[2].add("Arno");
        staff[2].add("Jacob");
        staff[3].add("Bayek");
        staff[3].add("Alexios");
        staff[3].add("Eivor");
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 3; j++) {
                staff_status[i].add("Active");
                days_worked[i].add(0);
                total_bonus[i].add(0);
                bonus[i].add(0);
                salary[i].add(0);
                total_normal_pay[i].add(0);
                total_salary[i].add(0);
            }
        }
        for (int i = 0; i < 3; i++) {
            race_won.add(0);
        }
    }
    //method to maintain the staff data at the end of the day if any staff quits
    public void updateDepartedStaff() {
        //General case where any worker quits, he/she is removed from the staff list and added to departed staff list
        System.out.println("\nDay End");
        for (int i = 0; i < 3; i++) { //Drivers do not quit so, it is not 4
            for (int j = 0; j < staff_status[i].size(); j++) {
                prob = rand.nextDouble();
                if (prob < 0.1) {
                    if(quit_status.get(i)==0){//If one person from same staff type has already quit then another one will not quit for that day.
                        System.out.println(staffType[i]+" " +staff[i].get(j)+" quit the FNCD.");//printing staff status
                        dep_staff[i].add(staff[i].get(j));//adding all staff details to departed staff list
                        staff[i].remove(j);//removing the staff details after the staff quits and adding to departed staff list.
                        staff_status[i].remove(j);
                        dep_days_worked[i].add(days_worked[i].get(j));
                        days_worked[i].remove(j);
                        dep_total_bonus[i].add(total_bonus[i].get(j));
                        total_bonus[i].remove(j);
                        bonus[i].remove(j);
                        dep_total_normal_pay[i].add(total_normal_pay[i].get(j));
                        total_normal_pay[i].remove(j);
                        dep_total_salary[i].add(total_salary[i].get(j));
                        total_salary[i].remove(j);
                        salary[i].remove(j);
                        quit_status.set(i,1);//so that at the most only one from each staff type may quit
                        j--;
                    }
                }
            }
        }
    }
}
//Strategy Pattern
interface WashBehavior{
    void wash(int j);
}
class Chemical extends Intern implements WashBehavior{
    public void wash(int j){
        Vehicle obj = new Vehicle();
        if(Vehicle.cleanliness[0].size()!=0){           //Checks if there is any 'Dirty' vehicle present, then only intern can wash it
            cleanliness_choice = rand.nextInt( Vehicle.cleanliness[0].size());
            vehicle_1 =  Vehicle.cleanliness[0].get(cleanliness_choice);
            prob = rand.nextDouble();                   //probability for updating cleanliness
            if (prob >= 0.1 && prob < 0.9) {
                Vehicle.cleanliness[1].add(vehicle_1);  //update cleanliness to 'Clean'
                System.out.println(staffType[0]+" " +staff[0].get(j)+" washed a "+Vehicle.carCleanliness[0]+" "+Vehicle.carType[obj.getCarType(vehicle_1)]+" "+vehicle_1+" using Chemical method and made it "+Vehicle.carCleanliness[1]);
                Vehicle.cleanliness[0].remove(cleanliness_choice);
            } else if (prob >= 0.9) {
                Vehicle.cleanliness[2].add(vehicle_1);  //Update cleanliness to 'Sparkling'
                Intern.getBonus(j,vehicle_1);                  //Intern gets bonus
                System.out.println(staffType[0]+" " +staff[0].get(j)+" washed a "+Vehicle.carCleanliness[0]+" "+Vehicle.carType[obj.getCarType(vehicle_1)]+" "+vehicle_1+" using Chemical method and made it "+Vehicle.carCleanliness[2]+" (and earned bonus of $"+bonus_val+")");
                Vehicle.cleanliness[0].remove(cleanliness_choice);
            } else{
                System.out.println(staffType[0]+" " +staff[0].get(j)+" washed a "+Vehicle.carCleanliness[0]+" "+Vehicle.carType[obj.getCarType(vehicle_1)]+" "+vehicle_1+" using Chemical method but it was still "+Vehicle.carCleanliness[0]);
            }
            prob = rand.nextDouble();                   //probability for updating condition
            if (prob < 0.1){
                System.out.println("The "+vehicle_1+" has become Broken");
                obj.updateCondition(0,vehicle_1);         //Update condition to 'Broken'
            }
        }
        if(Vehicle.cleanliness[1].size()!=0){           //Checks if there is any 'Clean' vehicle present, then only intern can wash it
            cleanliness_choice = rand.nextInt( Vehicle.cleanliness[1].size());
            vehicle_2 =  Vehicle.cleanliness[1].get(cleanliness_choice);
            prob = rand.nextDouble();
            if (prob < 0.1) {
                Vehicle.cleanliness[0].add(vehicle_2);  //update cleanliness back to 'Dirty'
                System.out.println(staffType[0]+" " +staff[0].get(j)+" washed a "+Vehicle.carCleanliness[1]+" "+Vehicle.carType[obj.getCarType(vehicle_2)]+" "+vehicle_2+" using Chemical method but made it "+Vehicle.carCleanliness[0]);
                Vehicle.cleanliness[1].remove(cleanliness_choice);
            } else if(prob >= 0.8) {
                Vehicle.cleanliness[2].add(vehicle_2);  //update cleanliness to 'Sparkling'
                Intern.getBonus(j,vehicle_2);                  //Intern gets bonus
                System.out.println(staffType[0]+" " +staff[0].get(j)+" washed a "+Vehicle.carCleanliness[1]+" "+Vehicle.carType[obj.getCarType(vehicle_2)]+" "+vehicle_2+" using Chemical method and made it "+Vehicle.carCleanliness[2]+" (and earned a bonus of $"+bonus_val+")");
                Vehicle.cleanliness[1].remove(cleanliness_choice);
            } else{
                System.out.println(staffType[0]+" " +staff[0].get(j)+" washed a "+Vehicle.carCleanliness[1]+" "+Vehicle.carType[obj.getCarType(vehicle_2)]+" "+vehicle_2+" using Chemical method but it was still "+Vehicle.carCleanliness[1]);
            }
            prob = rand.nextDouble();                   //probability for updating condition
            if (prob < 0.1){
                System.out.println("The "+vehicle_2+" has become Broken");
                obj.updateCondition(0,vehicle_2);         //Update condition to 'Broken'
            }
        }
    }
}
class ElbowGrease extends Intern implements WashBehavior{
    public void wash(int j){
        Vehicle obj = new Vehicle();
        if(Vehicle.cleanliness[0].size()!=0){           //Checks if there is any 'Dirty' vehicle present, then only intern can wash it
            cleanliness_choice = rand.nextInt( Vehicle.cleanliness[0].size());
            vehicle_1 =  Vehicle.cleanliness[0].get(cleanliness_choice);
            prob = rand.nextDouble();                   //probability for updating cleanliness
            if (prob >= 0.25 && prob < 0.95) {
                Vehicle.cleanliness[1].add(vehicle_1);  //update cleanliness to 'Clean'
                System.out.println(staffType[0]+" " +staff[0].get(j)+" washed a "+Vehicle.carCleanliness[0]+" "+Vehicle.carType[obj.getCarType(vehicle_1)]+" "+vehicle_1+" using Elbow Grease method and made it "+Vehicle.carCleanliness[1]);
                Vehicle.cleanliness[0].remove(cleanliness_choice);
            } else if (prob >= 0.95) {
                Vehicle.cleanliness[2].add(vehicle_1);  //Update cleanliness to 'Sparkling'
                Intern.getBonus(j,vehicle_1);                  //Intern gets bonus
                System.out.println(staffType[0]+" " +staff[0].get(j)+" washed a "+Vehicle.carCleanliness[0]+" "+Vehicle.carType[obj.getCarType(vehicle_1)]+" "+vehicle_1+" using Elbow Grease method and made it "+Vehicle.carCleanliness[2]+" (and earned bonus of $"+bonus_val+")");
                Vehicle.cleanliness[0].remove(cleanliness_choice);
            } else{
                System.out.println(staffType[0]+" " +staff[0].get(j)+" washed a "+Vehicle.carCleanliness[0]+" "+Vehicle.carType[obj.getCarType(vehicle_1)]+" "+vehicle_1+" using Elbow Grease method but it was still "+Vehicle.carCleanliness[0]);
            }
            prob = rand.nextDouble();                   //probability for updating condition
            if (prob < 0.1){
                System.out.println("The "+vehicle_1+" has become Like New");
                obj.updateCondition(2,vehicle_1);         //Update condition to 'Like New'
            }
        }
        if(Vehicle.cleanliness[1].size()!=0){           //Checks if there is any 'Clean' vehicle present, then only intern can wash it
            cleanliness_choice = rand.nextInt( Vehicle.cleanliness[1].size());
            vehicle_2 =  Vehicle.cleanliness[1].get(cleanliness_choice);
            prob = rand.nextDouble();
            if (prob < 0.15) {
                Vehicle.cleanliness[0].add(vehicle_2);  //update cleanliness back to 'Dirty'
                System.out.println(staffType[0]+" " +staff[0].get(j)+" washed a "+Vehicle.carCleanliness[1]+" "+Vehicle.carType[obj.getCarType(vehicle_2)]+" "+vehicle_2+" using Elbow Grease method but made it "+Vehicle.carCleanliness[0]);
                Vehicle.cleanliness[1].remove(cleanliness_choice);
            } else if(prob >= 0.85) {
                Vehicle.cleanliness[2].add(vehicle_2);  //update cleanliness to 'Sparkling'
                Intern.getBonus(j,vehicle_2);                  //Intern gets bonus
                System.out.println(staffType[0]+" " +staff[0].get(j)+" washed a "+Vehicle.carCleanliness[1]+" "+Vehicle.carType[obj.getCarType(vehicle_2)]+" "+vehicle_2+" using Elbow Grease method and made it "+Vehicle.carCleanliness[2]+" (and earned a bonus of $"+bonus_val+")");
                Vehicle.cleanliness[1].remove(cleanliness_choice);
            } else{
                System.out.println(staffType[0]+" " +staff[0].get(j)+" washed a "+Vehicle.carCleanliness[1]+" "+Vehicle.carType[obj.getCarType(vehicle_2)]+" "+vehicle_2+" using Elbow Grease method but it was still "+Vehicle.carCleanliness[1]);
            }
            prob = rand.nextDouble();                   //probability for updating condition
            if (prob < 0.1){
                System.out.println("The "+vehicle_2+" has become Like New");
                obj.updateCondition(2,vehicle_2);         //Update condition to 'Like New'
            }
        }
    }
}
class Detailed extends Intern implements WashBehavior{
    public void wash(int j){
        Vehicle obj = new Vehicle();
        if(Vehicle.cleanliness[0].size()!=0){           //Checks if there is any 'Dirty' vehicle present, then only intern can wash it
            cleanliness_choice = rand.nextInt( Vehicle.cleanliness[0].size());
            vehicle_1 =  Vehicle.cleanliness[0].get(cleanliness_choice);
            prob = rand.nextDouble();                   //probability for updating cleanliness
            if (prob >= 0.2 && prob < 0.8) {
                Vehicle.cleanliness[1].add(vehicle_1);  //update cleanliness to 'Clean'
                System.out.println(staffType[0]+" " +staff[0].get(j)+" washed a "+Vehicle.carCleanliness[0]+" "+Vehicle.carType[obj.getCarType(vehicle_1)]+" "+vehicle_1+" using Detailed method and made it "+Vehicle.carCleanliness[1]);
                Vehicle.cleanliness[0].remove(cleanliness_choice);
            } else if (prob >= 0.8) {
                Vehicle.cleanliness[2].add(vehicle_1);  //Update cleanliness to 'Sparkling'
                Intern.getBonus(j,vehicle_1);                  //Intern gets bonus
                System.out.println(staffType[0]+" " +staff[0].get(j)+" washed a "+Vehicle.carCleanliness[0]+" "+Vehicle.carType[obj.getCarType(vehicle_1)]+" "+vehicle_1+" using Detailed method and made it "+Vehicle.carCleanliness[2]+" (and earned bonus of $"+bonus_val+")");
                Vehicle.cleanliness[0].remove(cleanliness_choice);
            } else{
                System.out.println(staffType[0]+" " +staff[0].get(j)+" washed a "+Vehicle.carCleanliness[0]+" "+Vehicle.carType[obj.getCarType(vehicle_1)]+" "+vehicle_1+" using Detailed method but it was still "+Vehicle.carCleanliness[0]);
            }
        }
        if(Vehicle.cleanliness[1].size()!=0){           //Checks if there is any 'Clean' vehicle present, then only intern can wash it
            cleanliness_choice = rand.nextInt( Vehicle.cleanliness[1].size());
            vehicle_2 =  Vehicle.cleanliness[1].get(cleanliness_choice);
            prob = rand.nextDouble();
            if (prob < 0.05) {
                Vehicle.cleanliness[0].add(vehicle_2);  //update cleanliness back to 'Dirty'
                System.out.println(staffType[0]+" " +staff[0].get(j)+" washed a "+Vehicle.carCleanliness[1]+" "+Vehicle.carType[obj.getCarType(vehicle_2)]+" "+vehicle_2+" using Detailed method but made it "+Vehicle.carCleanliness[0]);
                Vehicle.cleanliness[1].remove(cleanliness_choice);
            } else if(prob >= 0.6) {
                Vehicle.cleanliness[2].add(vehicle_2);  //update cleanliness to 'Sparkling'
                Intern.getBonus(j,vehicle_2);                  //Intern gets bonus
                System.out.println(staffType[0]+" " +staff[0].get(j)+" washed a "+Vehicle.carCleanliness[1]+" "+Vehicle.carType[obj.getCarType(vehicle_2)]+" "+vehicle_2+" using Detailed method and made it "+Vehicle.carCleanliness[2]+" (and earned a bonus of $"+bonus_val+")");
                Vehicle.cleanliness[1].remove(cleanliness_choice);
            } else{
                System.out.println(staffType[0]+" " +staff[0].get(j)+" washed a "+Vehicle.carCleanliness[1]+" "+Vehicle.carType[obj.getCarType(vehicle_2)]+" "+vehicle_2+" using Detailed method but it was still "+Vehicle.carCleanliness[1]);
            }
        }
    }
}
//Example of Inheritance
class Intern extends Staff{
//    String[] wash_method = {"Chemical", "Elbow Grease", "Detailed"};
    int cleanliness_choice;
    String vehicle_1,vehicle_2;
    WashBehavior washBehavior;
    //primary method for intern to wash vehicles and update cleanliness and condition of cars
    public void setWashBehavior(){
        System.out.println("\nWashing");
        for (int j=0;j<staff[0].size();j++){
            int i = rand.nextInt(3);
            if (i==0){
                washBehavior = new Chemical();
            } else if (i==1) {
                washBehavior = new ElbowGrease();
            } else {
                washBehavior = new Detailed();
            }
            washBehavior.wash(j);
        }
    }
    //Getting total days worked
    public void getTotalDays(){
        for(int j=0;j<staff[0].size();j++){
            days_worked[0].set(j, days_worked[0].get(j)+1);//Total days worked increases by 1 after each day an intern was active
        }
    }
    //Calculating the bonus
    public static void getBonus(int k, String vehicle){
        for(int i=0; i<6;i++){
            for(int j = 0; j<  Vehicle.vehicle[i].size(); j++) {
                if ( Vehicle.vehicle[i].get(j).equals(vehicle)){
                    bonus[0].set(k, bonus[0].get(k) +  Vehicle.vehicle_wash_bonus[i]);//Bonus is decided based on type of car
                    bonus_val = Vehicle.vehicle_wash_bonus[i];
                    break;
                }
            }
        }
    }
    //Calculating the salary based on normal pay and bonus play
    public void getSalary(){
        for(int j=0;j<staff[0].size();j++){
            salary[0].set(j, normal_pay[0]+bonus[0].get(j));    //salary is calculated by adding bonus and normal pay for the day
            if(Operation.budget-salary[0].get(j)<=0){           //If we run out of budget, $250000 is added to the budget
                Operation.budget=Operation.budget+250000;
                Operation.added_money=Operation.added_money+250000;
            }
            Operation.budget=Operation.budget-salary[0].get(j); //Salary is reduced from the budget
            total_normal_pay[0].set(j,total_normal_pay[0].get(j)+normal_pay[0]);
            total_salary[0].set(j, total_salary[0].get(j)+salary[0].get(j));
            total_bonus[0].set(j, total_bonus[0].get(j)+bonus[0].get(j));
        }
    }
    //Adding a new intern
    public void addIntern(){
        while(staff[0].size()<3){      //If number of Interns is less than 3, add new interns
            staff[0].add(getName());
            System.out.println("Hired a new Intern "+(staff[0].get(staff[0].size()-1))+".");
            staff_status[0].add("Active");
            days_worked[0].add(0);
            bonus[0].add(0);
            total_bonus[0].add(0);
            salary[0].add(0);
            total_normal_pay[0].add(0);
            total_salary[0].add(0);
        }
        for (int i =0;i<staff.length;i++){
            for (int j=0;j<staff[i].size();j++){
                bonus[i].set(j,0);  //after the end of the day, initializing the bonus amount to 0 for the next day
                quit_status.set(i,0);//after the end of the day, initializing quit status for the next day
            }
        }
    }
    //Updating the list of staff if any of the staff leaves
    // by replacing the departed staff with an existing intern and adding a new intern
    public void dropIntern(){
        //Special cases where Mechanic or Salesperson Quits and Intern replaces him/her
        //and then dropped from Intern array
        if(staff[1].size()<3){ //If Mechanic quits
            staff[1].add(staff[0].get(0));//Intern is promoted to Mechanic
            staff[0].remove(0);
            staff_status[1].add(staff_status[0].get(0));//Intern details are added to Mechanic
            staff_status[0].remove(0);            //and then removed from Intern list
            days_worked[1].add(days_worked[0].get(0));
            days_worked[0].remove(0);
            total_bonus[1].add(total_bonus[0].get(0));
            total_bonus[0].remove(0);
            bonus[1].add(bonus[0].get(0));
            bonus[0].remove(0);
            salary[1].add(salary[0].get(0));
            salary[0].remove(0);
            total_normal_pay[1].add(total_normal_pay[0].get(0));
            total_normal_pay[0].remove(0);
            total_salary[1].add(total_salary[0].get(0));
            total_salary[0].remove(0);
        }
        if (staff[2].size()<3){ //If Salesperson quits
            staff[2].add(staff[0].get(0)); //Intern is promoted to Salesperson
            staff[0].remove(0);
            staff_status[2].add(staff_status[0].get(0)); //Intern details are added to Salesperson
            staff_status[0].remove(0);             //and then removed from Intern list
            days_worked[2].add(days_worked[0].get(0));
            days_worked[0].remove(0);
            total_bonus[2].add(total_bonus[0].get(0));
            total_bonus[0].remove(0);
            bonus[2].add(bonus[0].get(0));
            bonus[0].remove(0);
            salary[2].add(salary[0].get(0));
            salary[0].remove(0);
            total_normal_pay[2].add(total_normal_pay[0].get(0));
            total_normal_pay[0].remove(0);
            total_salary[2].add(total_salary[0].get(0));
            total_salary[0].remove(0);
        }
    }
}
class Mechanic extends Staff {
    int index,vehicle_choice;
    String vehicle;
    //primary method for mechanic to repair cars and update condition and cleanliness
    public void repair(){
        System.out.println("\nRepairing");
        Vehicle obj = new Vehicle();
        for (int j=0;j<staff[1].size();j++){
            for(int k=0;k<2;k++){
                index = rand.nextInt(2);     //we don't want to select a 'like new' car for repairing
                if(Vehicle.condition[index].size()==0){//If there is no car from selected condition (eg. Broken)
                    if (index==0){
                        index=1;                   //If the selected condition was 'Broken' then select condition 'Used'
                    }
                    else{
                        index=0;                   //If the selected condition was 'Used' then select condition 'Broken'
                    }
                }
                if(Vehicle.condition[index].size()!=0){  //If there is a car from selected condition, repair it
                    repair_update(j,obj);
                }
            }
        }
    }
    // a method which decides whether the car was fixed and condition was updated
    // along with degrading the car cleanliness
    public void repair_update(int j,Vehicle obj){
        vehicle_choice = rand.nextInt(Vehicle.condition[index].size());
        vehicle = Vehicle.condition[index].get(vehicle_choice);
        prob = rand.nextDouble();
        if (prob < 0.8) {
            getBonus(j, vehicle);    //Mechanic gets bonus if car condition level is fixed to next condition (e.g. from dirty to clean)
            System.out.print(staffType[1]+" "+staff[1].get(j)+" repaired a "+Vehicle.carCondition[obj.getCondition(vehicle)]+" "+Vehicle.carType[obj.getCarType(vehicle)]+" "+vehicle+" and made it "+Vehicle.carCondition[obj.getCondition(vehicle)+1]+" (and earned a bonus of $"+bonus_val+")");
            Vehicle.condition[index+1].add(vehicle);
            //Example of Delegation
            obj.updateSalesPrice(index,vehicle); //when condition of car is fixed to next level, its sales prices is updated
            Vehicle.condition[index].remove(vehicle_choice);
        } else{
            System.out.print(staffType[1]+" "+staff[1].get(j)+" repaired a "+Vehicle.carCondition[obj.getCondition(vehicle)]+" "+Vehicle.carType[obj.getCarType(vehicle)]+" "+vehicle+" but it was still "+Vehicle.carCondition[obj.getCondition(vehicle)]);
        }
        obj.updateCleanliness(vehicle); //Degrade the cleanliness by one level
        System.out.println(" and the vehicle became "+Vehicle.carCleanliness[obj.getCleanliness(vehicle)]);
    }

    public void getTotalDays(){
        for(int j=0;j<staff[1].size();j++){
            days_worked[1].set(j, days_worked[1].get(j)+1); //Total days worked increases by 1 after each day a mechanic was active
        }
    }
    public void getBonus(int k, String vehicle){
        for(int i=0; i<6;i++){
            for(int j = 0; j< Vehicle.vehicle[i].size(); j++) {
                if (Vehicle.vehicle[i].get(j).equals(vehicle)) {
                    bonus[1].set(k, bonus[1].get(k) + Vehicle.vehicle_repair_bonus[i]); //Bonus is decided based on type of car
                    bonus_val=Vehicle.vehicle_repair_bonus[i];
                    break;
                }
            }
        }
    }
    public void getSalary(){
        for(int j=0;j<staff[1].size();j++){
            salary[1].set(j, normal_pay[1]+bonus[1].get(j));//salary is calculated by adding bonus and normal pay for the day
            if(Operation.budget-salary[1].get(j)<=0){       //If we run out of budget, $250000 is added to the budget
                Operation.budget=Operation.budget+250000;
                Operation.added_money=Operation.added_money+250000;
            }
            Operation.budget=Operation.budget-salary[1].get(j);
            total_normal_pay[1].set(j,total_normal_pay[1].get(j)+normal_pay[1]);
            total_salary[1].set(j, total_salary[1].get(j)+salary[1].get(j));
            total_bonus[1].set(j, total_bonus[1].get(j)+bonus[1].get(j));
        }
    }
}
class Salesperson extends Staff{
    int index2,buyer_index1,buyer_index2,buyer_choice,vehicle_choice;
    String salesperson,buyer,vehicle;
    //primary method for salespersons to sell cars
    public void sale(){
        System.out.println("\nSelling");
        Operation.total_sales = 0;//Initializing the total sales at the beginning of the day
        Buyer buyer1 = new Buyer();
        Vehicle[] vehicle_object = {new Car(),new Pickup(),new PerformanceCar(),new Motorcycles(),new MonsterTrucks(),new ElectricCar()};
        //Checks if there are any buyers present
        if (Buyer.buyer_no !=0){
            for(int i = 0; i< Buyer.buyer_no; i++){
                index2 = rand.nextInt(staff[2].size());
                salesperson = staff[2].get(index2);   //Salesperson is randomly assigned
                buyer="Buyer"+(i+1);
                buyer_index1=buyer1.getBuyerIndex1(buyer);
                buyer_index2=buyer1.getBuyerIndex2(buyer);
                buyer_choice= Buyer.buyer_choice[buyer_index1].get(buyer_index2);
                Vehicle obj= new Vehicle();
                obj.VehicleTopPrice();      //getting car with top sales price from buyers choice
                if(Vehicle.max_sale_price[buyer_choice] != 0){
                    for(int j = 0; j< Vehicle.sales_price[buyer_choice].size(); j++){
                        if(Vehicle.sales_price[buyer_choice].get(j)== Vehicle.max_sale_price[buyer_choice]){
                            vehicle= Vehicle.vehicle[buyer_choice].get(j);
                            vehicle_choice=j;
                            if(obj.getCondition(vehicle)==2){   //checking the condition of the car to be Like New
                                //add 10% chance to buyer
                                Buyer.buyer_prob[buyer_index1].set(buyer_index2,(Buyer.buyer_prob[buyer_index1].get(buyer_index2)+0.1));
                            }
                            if(obj.getCleanliness(vehicle)==2){   //checking the cleanliness of the car to be Sparkling
                                //add 10% chance to buyer
                                Buyer.buyer_prob[buyer_index1].set(buyer_index2,(Buyer.buyer_prob[buyer_index1].get(buyer_index2)+0.1));
                            }
                            if(Vehicle.race_won[buyer_choice].get(vehicle_choice)>=1){ //checking the count of races won by vehicle
                                //add 10% chance to buyer
                                Buyer.buyer_prob[buyer_index1].set(buyer_index2,(Buyer.buyer_prob[buyer_index1].get(buyer_index2)+0.1));
                            }
                            break;
                        }
                    }
                }else{             //If Buyer's choice is not found, the vehicle top price from rest of the cars is selected
                    buyer_choice=obj.totalVehicleTopPrice();
                    for(int j = 0; j< Vehicle.sales_price[buyer_choice].size(); j++){
                        if(Vehicle.sales_price[buyer_choice].get(j)== Vehicle.max_sale_price[buyer_choice]){
                            vehicle= Vehicle.vehicle[buyer_choice].get(j);
                            vehicle_choice=j;
                            if(obj.getCondition(vehicle)==2){    //checking the condition of the car to be Like New
                                //add 10% chance to buyer
                                Buyer.buyer_prob[buyer_index1].set(buyer_index2,(Buyer.buyer_prob[buyer_index1].get(buyer_index2)+0.1));
                            }
                            if(obj.getCleanliness(vehicle)==2){  //checking the cleanliness of the car to be Sparkling
                                //add 10% chance to buyer
                                Buyer.buyer_prob[buyer_index1].set(buyer_index2,(Buyer.buyer_prob[buyer_index1].get(buyer_index2)+0.1));
                            }
                            if(Vehicle.race_won[buyer_choice].get(vehicle_choice)>=1){ //checking the count of races won by vehicle
                                //add 10% chance to buyer
                                Buyer.buyer_prob[buyer_index1].set(buyer_index2,(Buyer.buyer_prob[buyer_index1].get(buyer_index2)+0.1));
                            }
                            //now reducing 20% chance from buyer due to unavailability of buyer's choice of car
                            if(Buyer.buyer_prob[buyer_index1].get(buyer_index2)>=0.2){
                                Buyer.buyer_prob[buyer_index1].set(buyer_index2,(Buyer.buyer_prob[buyer_index1].get(buyer_index2)-0.2));
                            }else {
                                //if buyer has less than 20% chance of buying, reducing the by 20% would result in negative value.
                                // Hence, we are making it zero.
                                Buyer.buyer_prob[buyer_index1].set(buyer_index2,0.0);
                            }
                            break;
                        }
                    }
                }
                //updating vehicles, budget, and conditions and cleanliness of vehicles
                prob = rand.nextDouble();
                if (prob < Buyer.buyer_prob[buyer_index1].get(buyer_index2)){
                    //if buyer buys the vehicle, check for add on purchases using decorator pattern
                    Vehicle vecl = vehicle_object[buyer_choice];//creating object of one of the car types of buyer's choice
                    vecl = new ExtendedWarranty(buyer_choice,vehicle_choice);//wrapping it with decorator components
                    vecl = new Undercoating(buyer_choice,vehicle_choice);
                    vecl = new RoadRescueCoverage(buyer_choice,vehicle_choice);
                    vecl = new SatelliteRadio(buyer_choice,vehicle_choice);
                    Operation.budget = Operation.budget + Vehicle.sales_price[buyer_choice].get(vehicle_choice);//Sales price of car added to budget
                    Operation.total_sales = Operation.total_sales + Vehicle.sales_price[buyer_choice].get(vehicle_choice);//updated Total sales of the day
                    getBonus(index2, vehicle);    //Salesperson gets a bonus
                    Vehicle.soldVehicles[buyer_choice].add(vehicle); //Car is added to list of sold vehicles
                    System.out.println("salesperson "+salesperson +" has sold a "+Vehicle.carCleanliness[obj.getCleanliness(vehicle)]+" "+Vehicle.carCondition[obj.getCondition(vehicle)]+" "+Vehicle.carType[buyer_choice] +" "+ vehicle +" to "+buyer+" for $"+Vehicle.sales_price[buyer_choice].get(vehicle_choice)+" (and earned a bonus of $"+bonus_val+")");
                    Vehicle.vehicle[buyer_choice].remove(vehicle_choice); //Car is removed from Vehicles in stock
                    Vehicle.status[buyer_choice].remove(vehicle_choice);
                    Vehicle.condition[obj.getCondition(vehicle)].remove(obj.getCondition2(vehicle));
                    Vehicle.cleanliness[obj.getCleanliness(vehicle)].remove(obj.getCleanliness2(vehicle));
                    Vehicle.cost_price[buyer_choice].remove(vehicle_choice);
                    Vehicle.sales_price[buyer_choice].remove(vehicle_choice);
                }
            }
        }
    }
    public void getTotalDays(){
        for(int j=0;j<staff[2].size();j++){
            days_worked[2].set(j, days_worked[2].get(j)+1);  //Total days worked increases by 1 after each day a salesperson was active
        }
    }
    public void getBonus(int k, String vehicle){
        for(int i=0; i<6;i++){
            for(int j = 0; j< Vehicle.vehicle[i].size(); j++) {
                if (Vehicle.vehicle[i].get(j).equals(vehicle)){
                    bonus[2].set(k, bonus[2].get(k) + Vehicle.vehicle_sale_bonus[i]);  //Bonus is decided based on type of car
                    bonus_val=Vehicle.vehicle_sale_bonus[i];
                    break;
                }
            }
        }
    }
    public void getSalary(){
        for(int j=0;j<staff[2].size();j++){
            salary[2].set(j, normal_pay[2]+bonus[2].get(j));//salary is calculated by adding bonus and normal pay for the day
            if(Operation.budget-salary[2].get(j)<=0){       //If we run out of budget, $250000 is added to the budget
                Operation.budget=Operation.budget+250000;
                Operation.added_money=Operation.added_money+250000;
            }
            Operation.budget=Operation.budget-salary[2].get(j);
            total_normal_pay[2].set(j,total_normal_pay[2].get(j)+normal_pay[2]);
            total_salary[2].set(j, total_salary[2].get(j)+salary[2].get(j));
            total_bonus[2].set(j, total_bonus[2].get(j)+bonus[2].get(j));
        }
    }
}

class Driver extends Staff{
    int choice,pos;
    ArrayList<Integer> positions,race_position;
    ArrayList<String> vehicles_choice,race_vehicles,injured_drivers;
    Vehicle obj = new Vehicle();

    public void getRaceVehicles(){      //All methods in Driver will check for day to be Wednesday or Sunday
        if(Operation.day_count==3 || Operation.day_count==10 || Operation.day_count==17 || Operation.day_count==24 ||
           Operation.day_count==7 || Operation.day_count==14 || Operation.day_count==21 || Operation.day_count==28) {
            System.out.println("\nRacing");
            vehicles_choice = new ArrayList<>();
            race_vehicles = new ArrayList<>();
            injured_drivers = new ArrayList<>();
            choice = rand.nextInt(1,5);                 //selecting a vehicle other than regular or electric car
            vehicles_choice = Vehicle.vehicle[choice];
            for (int i=0;i<vehicles_choice.size();i++){
                if(obj.getCondition(vehicles_choice.get(i))!=0){    //condition to check if the vehicle is not broken
                    race_vehicles.add(vehicles_choice.get(i));
                }
                if(race_vehicles.size()==3){                        //If 3 vehicles already selected, stop
                    break;
                }
            }
        }
    }
    //The vehicles in race_vehicles will be associated with the drivers (all names from staff[3]) having same index numbers
    //for example, race_vehicles.get(i) will have a driver staff[3].get(i) and its count of races won will be race_won.get(i)
    public void race(){
        if(Operation.day_count==3 || Operation.day_count==10 || Operation.day_count==17 || Operation.day_count==24 ||
           Operation.day_count==7 || Operation.day_count==14 || Operation.day_count==21 || Operation.day_count==28) {
            positions = new ArrayList<>();
            if (race_vehicles.size()!=0){
                positions.addAll(Arrays.asList(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20));
                race_position = new ArrayList<>();
                for (int i=0;i<race_vehicles.size();i++){
                    pos=rand.nextInt(positions.size());
                    race_position.add(positions.get(pos));              //selecting a final position for vehicle
                    positions.remove(pos);                                //removing that position for other vehicles
                    if(race_position.get(i)<4){                           //If vehicle wins the race
                        for (int j=0;j<vehicles_choice.size();j++){
                            if (vehicles_choice.get(j).equals(race_vehicles.get(i))) {
                                Vehicle.race_won[choice].set(j,Vehicle.race_won[choice].get(j)+1);//updating race won count for that vehicle
                                race_won.set(i, race_won.get(i)+1);       //updating race won count for the driver of the vehicle
                                getBonus(i,vehicles_choice.get(j));       //Giving bonus to driver
                                System.out.println(staffType[3]+" "+staff[3].get(i)+" finished the race at position "+race_position.get(i)+" with the "+Vehicle.carType[choice]+" "+race_vehicles.get(i)+" and Won the race (and earned a bonus of $"+bonus_val+")");
                                break;
                            }
                        }
                    } else if (race_position.get(i)>15){                  //If vehicle gets damaged
                        for (int j=0;j<vehicles_choice.size();j++){
                            if (vehicles_choice.get(j).equals(race_vehicles.get(i))) {
                                Vehicle.condition[0].add(vehicles_choice.get(j));//update the condition of vehicle to broken
                                Vehicle.condition[obj.getCondition(vehicles_choice.get(j))].remove(obj.getCondition2(vehicles_choice.get(j)));//removing the previous condition
                                System.out.println(staffType[3]+" "+staff[3].get(i)+" finished the race at position "+race_position.get(i)+" with the "+Vehicle.carType[choice]+" "+race_vehicles.get(i)+" and the Vehicle became Broken ");
                                prob=rand.nextDouble();
                                if (prob<0.3){
                                    injured_drivers.add(staff[3].get(i)); //The Driver is injured, and he is added to injured drivers
                                    System.out.println("Driver "+staff[3].get(i)+" got injured and quit the FNCD");
                                }
                                break;
                            }
                        }
                    } else {
                        for (int j=0;j<vehicles_choice.size();j++) {
                            if (vehicles_choice.get(j).equals(race_vehicles.get(i))) {
                                System.out.println(staffType[3]+" "+staff[3].get(i)+" finished the race at position "+race_position.get(i)+" with the "+Vehicle.carType[choice]+" "+race_vehicles.get(i));
                                break;
                            }
                        }
                    }
                }
            }
        }
    }
    public void getTotalDays(){
        if(Operation.day_count==3 || Operation.day_count==10 || Operation.day_count==17 || Operation.day_count==24 ||
           Operation.day_count==7 || Operation.day_count==14 || Operation.day_count==21 || Operation.day_count==28) {
            for(int j=0;j<staff[3].size();j++){
                days_worked[3].set(j, days_worked[3].get(j)+1);  //Total days worked increases by 1 after each day a salesperson was active
            }
        }
    }
    public void getBonus(int k, String vehicle){
        if(Operation.day_count==3 || Operation.day_count==10 || Operation.day_count==17 || Operation.day_count==24 ||
           Operation.day_count==7 || Operation.day_count==14 || Operation.day_count==21 || Operation.day_count==28) {
            for(int i=1; i<5;i++){                               //So that General and Electric cars are not considered while getting Driver bonus
                for(int j = 0; j< Vehicle.vehicle[i].size(); j++) {
                    if (Vehicle.vehicle[i].get(j).equals(vehicle)){
                        bonus[3].set(k, bonus[3].get(k) + Vehicle.race_win_bonus[i]);  //Bonus is decided based on type of car
                        bonus_val=Vehicle.race_win_bonus[i];
                        break;
                    }
                }
            }
        }
    }
    public void getSalary(){
        if(Operation.day_count==3 || Operation.day_count==10 || Operation.day_count==17 || Operation.day_count==24 ||
           Operation.day_count==7 || Operation.day_count==14 || Operation.day_count==21 || Operation.day_count==28) {
            for(int j=0;j<staff[3].size();j++){
                salary[3].set(j, normal_pay[3]+bonus[3].get(j));//salary is calculated by adding bonus and normal pay for the day
                if(Operation.budget-salary[3].get(j)<=0){       //If we run out of budget, $250000 is added to the budget
                    Operation.budget=Operation.budget+250000;
                    Operation.added_money=Operation.added_money+250000;
                }
                Operation.budget=Operation.budget-salary[3].get(j);
                total_normal_pay[3].set(j,total_normal_pay[3].get(j)+normal_pay[3]);
                total_salary[3].set(j, total_salary[3].get(j)+salary[3].get(j));
                total_bonus[3].set(j, total_bonus[3].get(j)+bonus[3].get(j));
            }
        }
    }
    public void addDriver(){
        if(Operation.day_count==3 || Operation.day_count==10 || Operation.day_count==17 || Operation.day_count==24 ||
           Operation.day_count==7 || Operation.day_count==14 || Operation.day_count==21 || Operation.day_count==28) {
            while(staff[3].size()<3){      //If number of Drivers is less than 3, add new drivers
                staff[3].add(getName());
                System.out.println("Hired a new Driver "+(staff[3].get(staff[3].size()-1))+".");
                staff_status[3].add("Active");
                days_worked[3].add(0);
                bonus[3].add(0);
                total_bonus[3].add(0);
                salary[3].add(0);
                total_normal_pay[3].add(0);
                total_salary[3].add(0);
                race_won.add(0);
            }
            for (int i =0;i<staff.length;i++){
                for (int j=0;j<staff[i].size();j++){
                    bonus[i].set(j,0);  //after the end of the day, initializing the bonus amount to 0 for the next day
                    quit_status.set(i,0);//after the end of the day, initializing quit status for the next day
                }
            }
        }
    }
    public void dropDriver(){
        if(Operation.day_count==3 || Operation.day_count==10 || Operation.day_count==17 || Operation.day_count==24 ||
           Operation.day_count==7 || Operation.day_count==14 || Operation.day_count==21 || Operation.day_count==28) {
            for (int i=0;i< staff[3].size();i++){
                for (int j=0;j<injured_drivers.size();j++) {
                    if (injured_drivers.get(j).equals(staff[3].get(i))) {
                        dep_staff[3].add(staff[3].get(i));    //The injured Driver quits, and he is added to departed staff
                        staff[3].remove(i);                   //removing the driver details from active drivers
                        injured_drivers.remove(j);            //removing the driver from injured drivers
                        staff_status[3].remove(i);
                        dep_days_worked[3].add(days_worked[3].get(i));
                        days_worked[3].remove(i);
                        dep_total_bonus[3].add(total_bonus[3].get(i));
                        total_bonus[3].remove(i);
                        bonus[3].remove(i);
                        dep_total_normal_pay[3].add(total_normal_pay[3].get(i));
                        total_normal_pay[3].remove(i);
                        dep_total_salary[3].add(total_salary[3].get(i));
                        total_salary[3].remove(i);
                        salary[3].remove(i);
                        dep_race_won.add(race_won.get(i));
                        race_won.remove(i);
                        i--;
                        break;
                    }
                }
            }
        }
    }
}

class Vehicle{
    static ArrayList<String> car_names = new ArrayList<>();
    static ArrayList<String> monster_truck_names = new ArrayList<>();
    static String[] carType = {"Car", "Pickup", "Performance Car","Motorcycle","Monster Truck","Electric Car"};
    static String[] carCondition = {"Broken", "Used", "Like New"};
    static String[] carCleanliness = {"Dirty","Clean","Sparkling"};
    static ArrayList<String>[] vehicle = new ArrayList[6];
    static ArrayList<Integer>[] race_won = new ArrayList[6];
    static ArrayList<String>[] status = new ArrayList[6];
    static ArrayList<String>[] condition = new ArrayList[3];
    static ArrayList<String>[] cleanliness = new ArrayList[3];
    static ArrayList<Integer>[] cost_price = new ArrayList[6];
    static ArrayList<Integer>[] sales_price = new ArrayList[6];
    static ArrayList<String>[] soldVehicles = new ArrayList[6];
    static int[] max_sale_price = {0,0,0,0,0,0};
    String conditionSelected = null;
    String lastOccurrence = null;
    static ArrayList<Integer> range = new ArrayList<>();
    static ArrayList<Double> engine_size = new ArrayList<>();
    Random rand= new Random();
    static int[] vehicle_wash_bonus = {50, 75, 100, 50, 75, 100};
    static int[] vehicle_repair_bonus = {100, 125, 150, 100, 125, 150};
    static int[] vehicle_sale_bonus = {150, 175, 200, 150, 175, 200};
    static int[] race_win_bonus = {0, 200, 225, 250, 275, 0};

    //Initializing variables
    public void init2(){
        for(int i = 0; i < vehicle.length; i++){
            vehicle[i] = new ArrayList<>();
            status[i] = new ArrayList<>();
            cost_price[i] = new ArrayList<>();
            sales_price[i] = new ArrayList<>();
            soldVehicles[i] = new ArrayList<>();
            race_won[i] = new ArrayList<>();
        }
        for(int j = 0;j < 3;j++){
            condition[j] = new ArrayList<>();
            cleanliness[j] = new ArrayList<>();
        }
    }

    //this method gives a list of car names
    public void add_vehicle_names() {
        car_names.addAll(Arrays.asList(
                "Toyota Camry", "Honda Civic", "Ford Mustang", "Chevrolet Corvette", "Mazda MX-5 Miata",
                "Porsche 911", "Dodge Charger", "Nissan Altima", "Jeep Wrangler", "Audi A4",
                "BMW M3", "Mercedes-Benz S-Class", "Tesla Model S", "Kia Telluride", "Subaru Outback",
                "Lamborghini Aventador", "Ferrari F8 Tributo", "Maserati Levante", "Jaguar F-Type", "Land Rover Defender",
                "Rolls-Royce Ghost", "Bentley Continental GT", "Aston Martin DB11", "McLaren 720S", "Bugatti Chiron",
                "Ford F-150", "Chevrolet Silverado", "Dodge Ram", "GMC Sierra", "Toyota Tundra",
                "Nissan Titan", "Honda Ridgeline", "Jeep Gladiator", "Tesla Cybertruck", "Ford Escape",
                "Chevrolet Equinox", "Mazda CX-5", "Toyota RAV4", "Honda CR-V", "Jeep Grand Cherokee",
                "Subaru Forester", "Lexus RX", "Audi Q5", "BMW X5", "Mercedes-Benz GLE",
                "Tesla Model X", "Kia Sorento", "Ford Explorer", "Chevrolet Traverse", "Dodge Durango",
                "Nissan Pathfinder", "Honda Pilot", "Jeep Cherokee", "Toyota 4Runner", "Subaru Ascent",
                "GMC Acadia", "Volvo XC90", "Lincoln Navigator", "Cadillac Escalade", "Porsche Cayenne",
                "Land Rover Range Rover", "Bentley Bentayga", "Audi Q7", "BMW X7", "Mercedes-Benz GLS",
                "Tesla Model Y", "Kia Carnival", "Chrysler Pacifica", "Toyota Sienna", "Honda Odyssey",
                "Dodge Grand Caravan", "Nissan Quest", "Jeep Grand Wagoneer", "Ford Bronco", "Chevrolet Blazer",
                "Jeep Renegade", "Nissan Kicks", "Toyota C-HR", "Honda HR-V", "Ford EcoSport",
                "Kia Soul", "Mazda CX-30", "Subaru Crosstrek", "Hyundai Kona", "Chevrolet Trax",
                "Jeep Compass", "Nissan Rogue Sport", "Toyota Corolla Cross","Koenigsegg Jesko","Lexus LC 500"));
    }
    // 44 Monster Truck names
    public void add_MonsterTruck_names(){
        monster_truck_names.addAll(Arrays.asList("Air Force Afterburner", "Avenger", "Bad News Travels Fast", "Batman",
                "Backwards Bob","Bear Foot 1979", "Bear Foot F-150", "Bear Foot 2xtreme", "Bear Foot Silverado",
                "Bear Foot USA", "BigFoot", "Black Stallion", "Blacksmith", "Blue Thunder", "Bounty Hunter",
                "Brutus", "Bulldozer", "Captain's Curse", "Cyborg", "El Toro Loco", "Grave Digger", "Grinder", "Gunslinger",
                "Jurassic Attack", "King Krunch", "Lucas Oil Crusader", "Madusa", "Maximum Destruction Max-D", "Mohawk Warrior",
                "Monster Mutt", "Predator", "Shell Camino", "Raminator", "Snake Bite", "Stone Crusher",
                "Sudden Impact", "Swamp Thing", "The Destroyer", "The Felon", "USA-1", "War Wizard", "WCW Nitro Machine", "Zombie"));
    }

    public String getMonsterTruckName(){
        String mt_name;
        int i = rand.nextInt(monster_truck_names.size());
        mt_name = monster_truck_names.get(i);
        monster_truck_names.remove(i);
        return mt_name;
    }

    public String getcarName() {
        String name;
        int i = rand.nextInt(car_names.size());
        name = car_names.get(i);
        car_names.remove(i);
        return name;
    }
    //Adding initial vehicles and data for the first day
    public void addVehicles(){
        vehicle[0].add("Hyundai Elantra");
        vehicle[0].add("Nissan Sentra");
        vehicle[0].add("Chevrolet Spark");
        vehicle[0].add("Kia Forte");
        vehicle[1].add("Ford Ranger");
        vehicle[1].add("GMC Canyon");
        vehicle[1].add("Chevrolet Colorado");
        vehicle[1].add("Ram 1500 Classic");
        vehicle[2].add("Audi RS7");
        vehicle[2].add("BMW M8");
        vehicle[2].add("Mercedes-AMG GT");
        vehicle[2].add("Porsche Panamera");
        vehicle[3].add("Aston Martin Vantage");
        vehicle[3].add("McLaren Artura");
        vehicle[3].add("Lamborghini Huracan");
        vehicle[3].add("Porsche Taycan");
        vehicle[4].add("Swamp Thing");
        vehicle[4].add("Sudden Impact");
        vehicle[4].add("USA-1");
        vehicle[4].add("Bear Foot F-150");
        vehicle[5].add("Audi R8");
        vehicle[5].add("BMW i8");
        vehicle[5].add("Mercedes-AMG GT 4-Door Coupe");
        vehicle[5].add("Rolls-Royce Cullinan");
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 4; j++) {
                setCondition(vehicle[i].get(j));
                setCleanliness(vehicle[i].get(j));
                status[i].add("In Stock");
                race_won[i].add(0);
            }
        }
        //Initially added cost price and sales price using the logic sales price = cost price*2
        //But cost price is reduced according to the car condition So, after reducing the cost price, we added it to the list of cost price
        //And hence we see the difference between cost and sales price to be more that 2x for some cars
        cost_price[0].addAll(Arrays.asList(12738, 15840, 14099, 15548));
        cost_price[1].addAll(Arrays.asList(9138, 14950, 29560, 12342));
        cost_price[2].addAll(Arrays.asList(24427, 28972, 15907, 27326));
        cost_price[3].addAll(Arrays.asList(13981, 15556, 16421, 8289));
        cost_price[4].addAll(Arrays.asList(10600, 23285, 38495, 28509));
        cost_price[5].addAll(Arrays.asList(20170, 20619, 30528, 36973));
        sales_price[0].addAll(Arrays.asList(25476, 39602, 28198, 38870));
        sales_price[1].addAll(Arrays.asList(22846, 37376, 73902, 30856));
        sales_price[2].addAll(Arrays.asList(61068, 72430, 63628, 68316));
        sales_price[3].addAll(Arrays.asList(27962, 31112, 32842, 20724));
        sales_price[4].addAll(Arrays.asList(42400, 46570, 76990, 57018));
        sales_price[5].addAll(Arrays.asList(80682, 82478, 76322, 73946));
        Operation.budget = 500000;//Initial Day 1 beginning budget
    }
    //when the count of a vehicle type is less than 4, new vehicle is purchased
    public void addVehicle() {
        for (int i = 0; i < 6; i++) {
            while (vehicle[i].size() < 4) {
                if (i == 4) {
                    String name = getMonsterTruckName();
                    for (int j = vehicle[i].size() - 1; j >= 0; j--) {
                        if (vehicle[i].get(j).contains(name)) {       //check if generated name already exists
                            lastOccurrence = vehicle[i].get(j);
                            char last_char = lastOccurrence.charAt(lastOccurrence.length()-1);
                            if(Character.isDigit(last_char)){         //if already a name exists more than once, e.g. "Viper 2"
                                name = name+" "+(Integer.parseInt(String.valueOf(last_char))+1);  //so now, adding new name as "Viper 3"
                            } else {                                  //name exists once, eg."Viper"
                                name = name + " 2";                   //so now, adding new name as "Viper 2"
                            }
                            break;
                        }                                             //if not, add the name as it is, e.g. "Viper"
                    }
                    vehicle[i].add(name);
                }
                else{
                    vehicle[i].add(getcarName());
                }
                setCondition(vehicle[i].get(vehicle[i].size()-1));
                setCleanliness(vehicle[i].get(vehicle[i].size()-1));
                status[i].add("In Stock");
                if(i == 0){
                    Car c = new Car();
                    c.buyVehicle();
                } else if(i == 1){
                    Pickup p = new Pickup();
                    p.buyVehicle();
                } else if(i == 2){
                    PerformanceCar pc = new PerformanceCar();
                    pc.buyVehicle();
                } else if (i == 3) {
                    Motorcycles mc = new Motorcycles();
                    mc.buyVehicle();
                } else if (i == 4) {
                    MonsterTrucks mt = new MonsterTrucks();
                    mt.buyVehicle();
                }
                else{
                    ElectricCar ec = new ElectricCar();
                    ec.buyVehicle();
                }
            }
        }
    }

    //randomly choose a condition for newly added vehicle
    public void setCondition(String chosenVehicle) {
        int i = rand.nextInt(carCondition.length);
        conditionSelected = carCondition[i];
        if (conditionSelected.equals("Broken")) {
            condition[0].add(chosenVehicle);//list of broken vehicles
        } else if (conditionSelected.equals("Used")) {
            condition[1].add(chosenVehicle);//list of used vehicles
        } else {
            condition[2].add(chosenVehicle);//list of like new vehicles
        }
    }
    //randomly choose a cleanliness for newly added vehicle
    public void setCleanliness (String chosenVehicle) {
        double prob = rand.nextDouble();
        if (prob < 0.60) {
            cleanliness[0].add(chosenVehicle);//list of Dirty vehicle
        } else if (prob < 0.95) {
            cleanliness[1].add(chosenVehicle);//list of Clean vehicle
        } else {
            cleanliness[2].add(chosenVehicle);//list of Sparkling vehicle
        }
    }
    //Finding the type of Vehicle
    public int getCarType(String vehicle1){
        //initializing to a value which is different from potential values of index (i.e. 0,1,2)
        int condition_index=10;
        for(int i=0; i<6;i++) {
            for (int j = 0; j < vehicle[i].size(); j++) {
                if (vehicle[i].get(j).equals(vehicle1)) {
                    condition_index = i;
                    break;
                }
            }
        }
        return condition_index;
    }
    //Finding the condition type of vehicle
    public int getCondition(String vehicle){
        //initializing to a value which is different from potential values of index (i.e. 0,1,2)
        int condition_index=10;
        for(int i=0; i<3;i++) {
            for (int j = 0; j < condition[i].size(); j++) {
                if (condition[i].get(j).equals(vehicle)) {
                    condition_index = i;
                    break;
                }
            }
        }
        return condition_index;
    }
    //Finding the position of vehicle in the list of selected condition type
    public int getCondition2(String vehicle){
        //initializing to a value which is different from potential values of index (i.e. 0,1,2)
        int condition_index=10;
        for(int i=0; i<3;i++) {
            for (int j = 0; j < condition[i].size(); j++) {
                if (condition[i].get(j).equals(vehicle)) {
                    condition_index = j;
                    break;
                }
            }
        }
        return condition_index;
    }
    //Finding the cleanliness type of vehicle
    public int getCleanliness(String vehicle){
        //initializing to a value which is different from potential values of index (i.e. 0,1,2)
        int cleanliness_index=10;
        for(int i=0; i<3;i++) {
            for (int j = 0; j < cleanliness[i].size(); j++) {
                if (cleanliness[i].get(j).equals(vehicle)) {
                    cleanliness_index = i;
                    break;
                }
            }
        }
        return cleanliness_index;
    }
    //Finding the position of vehicle in the list of selected cleanliness type
    public int getCleanliness2(String vehicle){
        //initializing to a value which is different from potential values of index (i.e. 0,1,2)
        int cleanliness_index=10;
        for(int i=0; i<3;i++) {
            for (int j = 0; j < cleanliness[i].size(); j++) {
                if (cleanliness[i].get(j).equals(vehicle)) {
                    cleanliness_index = j;
                    break;
                }
            }
        }
        return cleanliness_index;
    }
    //gets maximum sales price of vehicle from each car type
    public void VehicleTopPrice(){
        int maxValue = 0;
        // maximum sales_price in car
        for(int i=0;i<sales_price.length;i++){
            for(int j=0;j<sales_price[i].size();j++){
                // checking for maximum sales price of car where the car is not broken
                if((sales_price[i].get(j) > maxValue) && getCondition(vehicle[i].get(j))!= 0){
                    maxValue = sales_price[i].get(j);
                }
            }
            max_sale_price[i] = maxValue;
            maxValue = 0;
        }
    }
    //gets maximum sales price of vehicle from all car types
    public int totalVehicleTopPrice(){
        int maxValue = 0,top_price_index=0;
        for(int j=0;j<max_sale_price.length;j++){
            if(max_sale_price[j] >  maxValue){
                maxValue = max_sale_price[j];
                top_price_index = j;
            }
        }
        return top_price_index;
    }
    //update the sales price when condition of vehicle is fixed to next level
    public void updateSalesPrice(int index, String req_vehicle){
        if (index==0){
            for(int i=0; i<6;i++) {
                for (int j = 0; j < vehicle[i].size(); j++) {
                    if (vehicle[i].get(j).equals(req_vehicle)) {
                        sales_price[i].set(j, (int) (sales_price[i].get(j) * 1.5));
                        break;
                    }
                }
            }
        } else {
            for(int i=0; i<6;i++) {
                for (int j = 0; j < vehicle[i].size(); j++) {
                    if (vehicle[i].get(j).equals(req_vehicle)) {
                        sales_price[i].set(j, (int) (sales_price[i].get(j) * 1.25));
                        break;
                    }
                }
            }
        }
    }
    //update the cleanliness of vehicle to degrade by one level if not already dirty
    public void updateCleanliness(String req_vehicle){
        for(int i=0; i<3;i++){
            for(int j=0; j<cleanliness[i].size();j++){
                if(cleanliness[i].get(j).equals(req_vehicle)){
                    if (i != 0) {
                        cleanliness[i - 1].add(req_vehicle);
                        cleanliness[i].remove(j);
                    }
                    break;
                }
            }
        }
    }
    //update the condition of vehicle to desired one if it's not already in that condition
    public void updateCondition(int index, String req_vehicle){
        for(int i=0; i<3;i++){
            for(int j=0; j<condition[i].size();j++){
                if(condition[i].get(j).equals(req_vehicle)){  //searching for current condition
                    if (i != index) {                         //if it's not already in desired condition
                        condition[index].add(req_vehicle);    //update the condition
                        condition[i].remove(j);
                    }
                    break;
                }
            }
        }
    }
    //printing summary of vehicle data
    public void Print2(){
        System.out.println("\nList of Vehicles:");
        System.out.println("Car: "+vehicle[0]);
        System.out.println("Pickup: "+vehicle[1]);
        System.out.println("Performance Car: "+vehicle[2]);
        System.out.println("Motorcycles: "+vehicle[3]);
        System.out.println("Monster Trucks: "+vehicle[4]);
        System.out.println("Electric Cars: "+vehicle[5]+"\n");
        System.out.println("Condition of Vehicles:");
        System.out.println("Broken: "+condition[0]);
        System.out.println("Used: "+condition[1]);
        System.out.println("Like New: "+condition[2]+"\n");
        System.out.println("Cleanliness of Vehicles:");
        System.out.println("Dirty: "+cleanliness[0]);
        System.out.println("Clean: "+cleanliness[1]);
        System.out.println("Sparkling: "+cleanliness[2]+"\n");
        System.out.println("Status of Vehicles:");
        System.out.println("Car: "+status[0]);
        System.out.println("Pickup: "+status[1]);
        System.out.println("Performance Car: "+status[2]);
        System.out.println("Motorcycles: "+status[3]);
        System.out.println("Monster Trucks: "+status[4]);
        System.out.println("Electric Cars: "+status[5]+"\n");
        System.out.println("Cost price of Vehicles:");
        System.out.println("Car: "+cost_price[0]);
        System.out.println("Pickup: "+cost_price[1]);
        System.out.println("Performance Car: "+cost_price[2]);
        System.out.println("Motorcycles: "+cost_price[3]);
        System.out.println("Monster Trucks: "+cost_price[4]);
        System.out.println("Electric Cars: "+cost_price[5]+"\n");
        System.out.println("Sales price of Vehicles:");
        System.out.println("Car: "+sales_price[0]);
        System.out.println("Pickup: "+sales_price[1]);
        System.out.println("Performance Car: "+sales_price[2]);
        System.out.println("Motorcycles: "+sales_price[3]);
        System.out.println("Monster Trucks: "+sales_price[4]);
        System.out.println("Electric Cars: "+sales_price[5]+"\n");
        System.out.println("List of Sold Vehicles:");
        System.out.println("Car: "+soldVehicles[0]);
        System.out.println("Pickup: "+soldVehicles[1]);
        System.out.println("Performance Car: "+soldVehicles[2]);
        System.out.println("Motorcycles: "+soldVehicles[3]);
        System.out.println("Monster Trucks: "+soldVehicles[4]);
        System.out.println("Electric Cars: "+soldVehicles[5]+"\n");
        System.out.println("Total sales for the day: "+Operation.total_sales);
        System.out.println("Remaining Budget: "+Operation.budget);
    }
}

class Car extends Vehicle {
    // adding cost and sales price to the car
    public void buyVehicle(){
        int cp1 = rand.nextInt(10000, 20001);
        cost_price[0].add(cp1);
        sales_price[0].add(cp1 * 2);
        if(getCondition(vehicle[0].get(vehicle[0].size()-1))==0){//If vehicle is initially broken, reducing its cost price by 50%.
            cost_price[0].set((vehicle[0].size()-1), (int)(cost_price[0].get(vehicle[0].size()-1)*0.5));
        } else if (getCondition(vehicle[0].get(vehicle[0].size()-1))==1){//If vehicle is initially used, reducing its cost price by 20%.
            cost_price[0].set((vehicle[0].size()-1), (int)(cost_price[0].get(vehicle[0].size()-1)*0.8));
        }
        if(Operation.budget-cost_price[0].get(vehicle[0].size()-1)<=0){
            Operation.budget=Operation.budget+250000;
            Operation.added_money=Operation.added_money+250000;
        }
        Operation.budget = Operation.budget - cost_price[0].get(vehicle[0].size()-1);
        System.out.println("Bought "+carType[0]+vehicle[0].get(vehicle[0].size()-1)+" for $"+cost_price[0].get(cost_price[0].size()-1));
    }

}
class Pickup extends Vehicle {
    // adding cost and sales price to the pickup vehicle
    public void buyVehicle() {
        int cp1 = rand.nextInt(10000, 40001);
        cost_price[1].add(cp1);
        sales_price[1].add(cp1 * 2);
        if (getCondition(vehicle[1].get(vehicle[1].size()-1)) == 0) {//If vehicle is initially broken, reducing its cost price by 50%.
            cost_price[1].set((vehicle[1].size()-1), (int) (cost_price[1].get(vehicle[1].size()-1) * 0.5));
        } else if (getCondition(vehicle[1].get(vehicle[1].size()-1)) == 1) {//If vehicle is initially used, reducing its cost price by 20%.
            cost_price[1].set((vehicle[1].size()-1), (int) (cost_price[1].get(vehicle[1].size()-1) * 0.8));
        }
        if(Operation.budget-cost_price[1].get(vehicle[1].size()-1)<=0){
            Operation.budget=Operation.budget+250000;
            Operation.added_money=Operation.added_money+250000;
        }
        Operation.budget = Operation.budget - cost_price[1].get(vehicle[1].size()-1);
        System.out.println("Bought "+carType[1]+" "+vehicle[1].get(vehicle[1].size()-1)+" for $"+cost_price[1].get(cost_price[1].size()-1));
    }
}
class PerformanceCar extends Vehicle {
    // adding cost and sales price to the performance car
    public void buyVehicle(){
        int cp1 = rand.nextInt(20000, 40001);
        cost_price[2].add(cp1);
        sales_price[2].add(cp1 * 2);
        if(getCondition(vehicle[2].get(vehicle[2].size()-1))==0){//If vehicle is initially broken, reducing its cost price by 50%.
            cost_price[2].set((vehicle[2].size()-1), (int)(cost_price[2].get(vehicle[2].size()-1)*0.5));
        } else if (getCondition(vehicle[2].get(vehicle[2].size()-1))==1){//If vehicle is initially used, reducing its cost price by 20%.
            cost_price[2].set((vehicle[2].size()-1), (int)(cost_price[2].get(vehicle[2].size()-1)*0.8));
        }
        if(Operation.budget-cost_price[2].get(vehicle[2].size()-1)<=0){
            Operation.budget=Operation.budget+250000;
            Operation.added_money=Operation.added_money+250000;
        }
        Operation.budget = Operation.budget - cost_price[2].get(vehicle[2].size()-1);
        System.out.println("Bought "+carType[2]+" "+vehicle[2].get(vehicle[2].size()-1)+" for $"+cost_price[2].get(cost_price[2].size()-1));
    }
}

class Motorcycles extends Vehicle{

    double engine_size_gen = 0.0;
    public double setEngineSize(){
        return rand.nextGaussian()*300+700;
    }
    public void buyVehicle(){
        int cp1 = rand.nextInt(10000, 20001);
        cost_price[3].add(cp1);
        sales_price[3].add(cp1 * 2);
        // Setting the Engine size for Motorcycles using truncated Normal Distribution with mean 700 and std dev 300.
        while (engine_size.size() < 4) {
            engine_size_gen = setEngineSize();
            if(engine_size_gen>=50){
                engine_size.add(engine_size_gen);
            }
        }
        if(getCondition(vehicle[3].get(vehicle[3].size()-1))==0){//If vehicle is initially broken, reducing its cost price by 50%.
            cost_price[3].set((vehicle[3].size()-1), (int)(cost_price[3].get(vehicle[3].size()-1)*0.5));
        } else if (getCondition(vehicle[3].get(vehicle[3].size()-1))==1){//If vehicle is initially used, reducing its cost price by 20%.
            cost_price[3].set((vehicle[3].size()-1), (int)(cost_price[3].get(vehicle[3].size()-1)*0.8));
        }
        if(Operation.budget-cost_price[3].get(vehicle[3].size()-1)<=0){
            Operation.budget=Operation.budget+250000;
            Operation.added_money=Operation.added_money+250000;
        }
        Operation.budget = Operation.budget - cost_price[3].get(vehicle[3].size()-1);
        System.out.println("Bought "+carType[3]+" "+vehicle[3].get(vehicle[3].size()-1)+" for $"+cost_price[3].get(cost_price[3].size()-1));
    }
}

class MonsterTrucks extends Vehicle{
    public void buyVehicle(){
        int cp1 = rand.nextInt(20000, 40001);
        cost_price[4].add(cp1);
        sales_price[4].add(cp1 * 2);
        if(getCondition(vehicle[4].get(vehicle[4].size()-1))==0){//If vehicle is initially broken, reducing its cost price by 50%.
            cost_price[4].set((vehicle[4].size()-1), (int)(cost_price[4].get(vehicle[4].size()-1)*0.5));
        } else if (getCondition(vehicle[4].get(vehicle[4].size()-1))==1){//If vehicle is initially used, reducing its cost price by 20%.
            cost_price[4].set((vehicle[4].size()-1), (int)(cost_price[4].get(vehicle[4].size()-1)*0.8));
        }
        if(Operation.budget-cost_price[4].get(vehicle[4].size()-1)<=0){
            Operation.budget=Operation.budget+250000;
            Operation.added_money=Operation.added_money+250000;
        }
        Operation.budget = Operation.budget - cost_price[4].get(vehicle[4].size()-1);
        System.out.println("Bought "+carType[4]+" "+vehicle[4].get(vehicle[4].size()-1)+" for $"+cost_price[4].get(cost_price[4].size()-1));
    }
}

class ElectricCar extends Vehicle{
    public void buyVehicle(){
        int cp1 = rand.nextInt(30000, 50001);
        cost_price[5].add(cp1);
        sales_price[5].add(cp1 * 2);

        // Assigning a range for the Electric Cars based on their condition
        int range_select = rand.nextInt(60, 401);
        while (range.size() < 4) {
            if(getCondition(vehicle[5].get(vehicle[5].size()-1))==2){
                range.add(range_select+100);
            }
            else{
                range.add(range_select);
            }
        }

        if(getCondition(vehicle[5].get(vehicle[5].size()-1))==0){//If vehicle is initially broken, reducing its cost price by 50%.
            cost_price[5].set((vehicle[5].size()-1), (int)(cost_price[5].get(vehicle[5].size()-1)*0.5));
        } else if (getCondition(vehicle[5].get(vehicle[5].size()-1))==1){//If vehicle is initially used, reducing its cost price by 20%.
            cost_price[5].set((vehicle[5].size()-1), (int)(cost_price[5].get(vehicle[5].size()-1)*0.8));
        }
        if(Operation.budget-cost_price[5].get(vehicle[5].size()-1)<=0){
            Operation.budget=Operation.budget+250000;
            Operation.added_money=Operation.added_money+250000;
        }
        Operation.budget = Operation.budget - cost_price[5].get(vehicle[5].size()-1);
        System.out.println("Bought "+carType[5]+" "+vehicle[5].get(vehicle[5].size()-1)+" for $"+cost_price[5].get(cost_price[5].size()-1));
    }
}
//Decorator Pattern
abstract class Addon_purchaser extends Vehicle{
//    Vehicle vehicle;
    int i, j;
    abstract void addonPrice();
}

class ExtendedWarranty extends Addon_purchaser{
    public ExtendedWarranty(int i, int j) {
        this.i = i;
        this.j = j;
        addonPrice();
    }
    public void addonPrice(){
        double prob = rand.nextDouble();
        if(prob < 0.25){
            System.out.print("Sales Price of the "+carType[i]+" "+vehicle[i].get(j)+" increased from $"+sales_price[i].get(j));
            sales_price[i].set(j, (int)(sales_price[i].get(j)*1.2));
            System.out.println(" to $"+sales_price[i].get(j)+ " after adding on Extended Warranty");
        }
    }
}

class Undercoating extends Addon_purchaser{
    public Undercoating(int i, int j) {
        this.i = i;
        this.j = j;
        addonPrice();
    }
    public void addonPrice(){
        double prob = rand.nextDouble();
        if(prob < 0.10){
            System.out.print("Sales Price of the "+carType[i]+" "+vehicle[i].get(j)+" increased from $"+sales_price[i].get(j));
            sales_price[i].set(j, (int)(sales_price[i].get(j)*1.05));
            System.out.println(" to $"+sales_price[i].get(j)+ " after adding on Undercoating");
        }
    }
}

class RoadRescueCoverage extends Addon_purchaser{
    public RoadRescueCoverage(int i, int j) {
        this.i = i;
        this.j = j;
        addonPrice();
    }
    public void addonPrice(){
        double prob = rand.nextDouble();
        if(prob <0.05){
            System.out.print("Sales Price of the "+carType[i]+" "+vehicle[i].get(j)+" increased from $"+sales_price[i].get(j));
            sales_price[i].set(j, (int)(sales_price[i].get(j)*1.02));
            System.out.println(" to $"+sales_price[i].get(j)+ " after adding on Road Rescue Coverage");
        }
    }
}

class SatelliteRadio extends Addon_purchaser{
    public SatelliteRadio(int i, int j) {
        this.i = i;
        this.j = j;
        addonPrice();
    }
    public void addonPrice(){
        double prob = rand.nextDouble();
        if(prob < 0.40){
            System.out.print("Sales Price of the "+carType[i]+" "+vehicle[i].get(j)+" increased from $"+sales_price[i].get(j));
            sales_price[i].set(j, (int)(sales_price[i].get(j)*1.05));
            System.out.println(" to $"+sales_price[i].get(j)+ " after adding on Satellite Radio");
        }
    }
}

class Buyer extends Staff{
    static ArrayList<String>[] buyers = new ArrayList[3];
    static ArrayList<Integer>[] buyer_choice = new ArrayList[3];
    static ArrayList<Double>[] buyer_prob = new ArrayList[3];
    static int buyer_no;
    Random rand = new Random();
    //initializing the variables
    public void init3() {
        for (int i = 0; i < buyers.length; i++) {
            buyers[i] = new ArrayList<>();
            buyer_choice[i] = new ArrayList<>();
            buyer_prob[i] = new ArrayList<>();
        }
    }
    //adding the number of buyers according to the day of the week
    //1= Monday, 2=Tuesday and so on
    //Assuming same number of buyers will be added on 7=Sunday as on Monday-Thursday
    public void addBuyer() {
        if ((Operation.day_count<=4)||(Operation.day_count>=7 && Operation.day_count<=11)||(Operation.day_count>=14 && Operation.day_count<=18)
            ||(Operation.day_count>=21 && Operation.day_count<=25)||(Operation.day_count>=28 && Operation.day_count<=30)){
            buyer_no = rand.nextInt(0, 6);
            for (int j = 0; j < buyer_no; j++) {
                addBuyerType(j);
            }
        } else if (Operation.day_count==5 ||Operation.day_count==6 ||Operation.day_count==12||Operation.day_count==13||
                   Operation.day_count==19||Operation.day_count==20||Operation.day_count==26||Operation.day_count==27) {
            buyer_no = rand.nextInt(2, 9);
            for (int j = 0; j < buyer_no; j++) {
                addBuyerType(j);
            }
        }
    }
    //randomly adding buyer type to buyers
    public void addBuyerType(int j){
        int prob;
        prob = rand.nextInt(3);
        if(prob == 0){
            buyers[0].add("Buyer"+ (j+1));
            buyer_prob[0].add(0.1);
            addBuyerChoice(0); //buyer added to the type Just Looking
        }
        else if(prob == 1){
            buyers[1].add("Buyer"+ (j+1));
            buyer_prob[1].add(0.4);
            addBuyerChoice(1); //buyer added to the type Wants one
        }
        else{
            buyers[2].add("Buyer"+ (j+1));
            buyer_prob[2].add(0.7);
            addBuyerChoice(2); //buyer added to the type Needs one
        }
    }
    //adds choice of vehicle to the buyer
    public void addBuyerChoice(int j){
        int vehicleChoice = rand.nextInt(6);//now 6 vehicles instead of 3
        buyer_choice[j].add(vehicleChoice);
    }
    //getting the type of buyer
    public int getBuyerIndex1(String buyer){
        //initializing to a value which is different from potential values of index (i.e. 0,1,2)
        int buyer_index1 = 0;
        for(int i=0; i<3;i++) {
            for (int j = 0; j < buyers[i].size(); j++) {
                if (buyers[i].get(j).equals(buyer)){
                    buyer_index1 = i;
                    break;
                }
            }
        }
        return buyer_index1;
    }
    //getting the position of buyer in the list of the type of buyer
    public int getBuyerIndex2(String buyer){
        //initializing to a value which is different from potential values of index (i.e. 0,1,2)
        int buyer_index2=0;
        for(int i=0; i<3;i++) {
            for (int j = 0; j < buyers[i].size(); j++) {
                if (buyers[i].get(j).equals(buyer)) {
                    buyer_index2 = j;
                    break;
                }
            }
        }
        return buyer_index2;
    }
}
class Operation extends Staff{
    static int budget = 500000;
    static int total_sales = 0;
    static int day_count;
    static int added_money=0;
    //printing the summary of staff
    public void Print(){
        System.out.println("\n***************SUMMARY***************");
        System.out.println("List of Staff:");
        System.out.println("Interns: "+staff[0]);
        System.out.println("Mechanics: "+staff[1]);
        System.out.println("Salespersons: "+staff[2]);
        System.out.println("Drivers: "+staff[3]+"\n");
        System.out.println("Status of Staff:");
        System.out.println("Interns: "+staff_status[0]);
        System.out.println("Mechanics: "+staff_status[1]);
        System.out.println("Salespersons: "+staff_status[2]);
        System.out.println("Drivers: "+staff_status[3]+"\n");
        System.out.println("Total Days worked by Staff:");
        System.out.println("Interns: "+days_worked[0]);
        System.out.println("Mechanics: "+days_worked[1]);
        System.out.println("Salespersons: "+days_worked[2]);
        System.out.println("Drivers: "+days_worked[3]+"\n");
        System.out.println("Total Bonus of Staff:");
        System.out.println("Interns: "+total_bonus[0]);
        System.out.println("Mechanics: "+total_bonus[1]);
        System.out.println("Salespersons: "+total_bonus[2]);
        System.out.println("Drivers: "+total_bonus[3]+"\n");
        System.out.println("Total Normal Pay of Staff:");
        System.out.println("Interns: "+total_normal_pay[0]);
        System.out.println("Mechanics: "+total_normal_pay[1]);
        System.out.println("Salespersons: "+total_normal_pay[2]);
        System.out.println("Drivers: "+total_normal_pay[3]+"\n");
        System.out.println("List of Departed Staff:");
        System.out.println("Interns: "+dep_staff[0]);
        System.out.println("Mechanics: "+dep_staff[1]);
        System.out.println("Salespersons: "+dep_staff[2]);
        System.out.println("Drivers: "+dep_staff[3]+"\n");
        System.out.println("Total Days worked by Departed Staff:");
        System.out.println("Interns: "+dep_days_worked[0]);
        System.out.println("Mechanics: "+dep_days_worked[1]);
        System.out.println("Salespersons: "+dep_days_worked[2]);
        System.out.println("Drivers: "+dep_days_worked[3]+"\n");
        System.out.println("Total Bonus of Departed Staff:");
        System.out.println("Interns: "+dep_total_bonus[0]);
        System.out.println("Mechanics: "+dep_total_bonus[1]);
        System.out.println("Salespersons: "+dep_total_bonus[2]);
        System.out.println("Drivers: "+dep_total_bonus[3]+"\n");
        System.out.println("Total Normal Pay of Departed Staff:");
        System.out.println("Interns: "+dep_total_normal_pay[0]);
        System.out.println("Mechanics: "+dep_total_normal_pay[1]);
        System.out.println("Salespersons: "+dep_total_normal_pay[2]);
        System.out.println("Drivers: "+dep_total_normal_pay[3]+"\n");
    }
    //main method
    public static void main(String[] args) {
        Staff staff1 = new Staff();
        staff1.init();
        staff1.add_names();
        staff1.addStaff();
        Intern int1 = new Intern();
        Mechanic mec1 = new Mechanic();
        Salesperson sal1 = new Salesperson();
        Buyer buy1 = new Buyer();
        Driver dri1 = new Driver();
        Vehicle vel1 = new Vehicle();
        vel1.init2();
        vel1.add_vehicle_names();
        vel1.add_MonsterTruck_names();
        vel1.addVehicles();
        Operation op1 = new Operation();
        for (day_count=1;day_count<31;day_count++){
            buy1.init3();
            System.out.println("\n***Day "+day_count+"***");//Display Day Number
            System.out.println("\nCurrent Budget: "+budget);
            buy1.addBuyer();
            int1.setWashBehavior();
            mec1.repair();
            sal1.sale();
            dri1.getRaceVehicles();
            dri1.race();
            int1.getSalary();
            int1.getTotalDays();
            mec1.getSalary();
            mec1.getTotalDays();
            sal1.getSalary();
            sal1.getTotalDays();
            dri1.getSalary();
            dri1.getTotalDays();
            staff1.updateDepartedStaff();
            int1.dropIntern();
            int1.addIntern();
            dri1.dropDriver();
            dri1.addDriver();
            vel1.addVehicle();
            System.out.println("Total sales for the day: "+Operation.total_sales);
            System.out.println("Remaining Budget: "+Operation.budget);
        }
        op1.Print();
        vel1.Print2();
    }
}