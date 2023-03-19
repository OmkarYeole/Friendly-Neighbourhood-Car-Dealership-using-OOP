package Project_3_2;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.io.IOException;
import java.util.Scanner;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;

//For 2nd FNCD
public class Staff2 {
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
                "Adam", "Alexis", "Angel", "Ashley", "Brianna", "Cameron", "Carter", "Cecilia", "Chase", "Christian",
                "Christopher", "Cole", "Connor", "Dakota", "Daniel", "David", "Dylan", "Elizabeth", "Ethan", "Evan",
                "Faith", "Gabriel", "Gavin", "Grace", "Haley", "Hunter", "Isaac", "Jaden", "Jasmine", "Jason",
                "Jayden", "Jocelyn", "Jordan", "Joseph", "Joshua", "Julia", "Justin", "Kaitlyn", "Katherine", "Kendall",
                "Kevin", "Lauren", "Leah", "Logan", "Luis", "Luke", "Mackenzie", "Madison", "Makayla", "Maria",
                "Mason", "Matthew", "Max", "Megan", "Melanie", "Melissa", "Mia", "Mikayla", "Miranda", "Natalie",
                "Nathan", "Nicholas", "Oliver", "Olivia", "Oscar", "Paige", "Patrick", "Rachel", "Rebecca", "Riley",
                "Ryan", "Samantha", "Santiago", "Sarah", "Savannah", "Sebastian", "Sierra", "Sophie", "Sydney", "Taylor",
                "Tristan", "Tyler", "Valeria", "Victoria", "Vincent", "William", "Wyatt", "Xavier", "Zachary", "Shawn"));
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
        staff[0].add("Thibaut");
        staff[0].add("Daniel");
        staff[0].add("David");
        staff[1].add("Antonio");
        staff[1].add("Ferland");
        staff[1].add("Eduardo");
        staff[2].add("Toni");
        staff[2].add("Luka");
        staff[2].add("Federico");
        staff[3].add("Vinicius");
        staff[3].add("Karim");
        staff[3].add("Carlo");
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
    public void updateDepartedStaff(FNCDdata2 fnc) throws IOException {
        //General case where any worker quits, he/she is removed from the staff list and added to departed staff list
        for (int i = 0; i < 3; i++) { //Drivers do not quit so, it is not 4
            for (int j = 0; j < staff_status[i].size(); j++) {
                prob = rand.nextDouble();
                if (prob < 0.1) {
                    if(quit_status.get(i)==0){//If one person from same staff type has already quit then another one will not quit for that day.
                        System.out.println(staffType[i]+" " +staff[i].get(j)+" quit the FNCD.");//printing staff status
                        fnc.dayEnd(i,j,0);//obs
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
interface WashBehavior2{
    void wash(int j, FNCDdata2 fnc) throws IOException;
}
//For 2nd FNCD
class Chemical2 extends Intern2 implements WashBehavior2{
    public void wash(int j, FNCDdata2 fnc) throws IOException {
        Vehicle2 obj = new Vehicle2();
        if(Vehicle2.cleanliness[0].size()!=0){           //Checks if there is any 'Dirty' vehicle present, then only intern can wash it
            cleanliness_choice = rand.nextInt( Vehicle2.cleanliness[0].size());
            vehicle_1 =  Vehicle2.cleanliness[0].get(cleanliness_choice);
            prob = rand.nextDouble();                   //probability for updating cleanliness
            if (prob >= 0.1 && prob < 0.9) {
                Vehicle2.cleanliness[1].add(vehicle_1);  //update cleanliness to 'Clean'
                System.out.println(staffType[0]+" " +staff[0].get(j)+" washed a "+Vehicle2.carCleanliness[0]+" "+Vehicle2.carType[obj.getCarType(vehicle_1)]+" "+vehicle_1+" using Chemical method and made it "+Vehicle2.carCleanliness[1]);
                fnc.washOutcome(0,j,0,obj,vehicle_1,0,0,1,0);//obs
                Vehicle2.cleanliness[0].remove(cleanliness_choice);
            } else if (prob >= 0.9) {
                Vehicle2.cleanliness[2].add(vehicle_1);  //Update cleanliness to 'Sparkling'
                Intern2.getBonus(j,vehicle_1);                  //Intern gets bonus
                System.out.println(staffType[0]+" " +staff[0].get(j)+" washed a "+Vehicle2.carCleanliness[0]+" "+Vehicle2.carType[obj.getCarType(vehicle_1)]+" "+vehicle_1+" using Chemical method and made it "+Vehicle2.carCleanliness[2]+" (and earned bonus of $"+bonus_val+")");
                fnc.washOutcome(0,j,0,obj,vehicle_1,0,0,2,bonus_val);//obs
                Vehicle2.cleanliness[0].remove(cleanliness_choice);
            } else{
                System.out.println(staffType[0]+" " +staff[0].get(j)+" washed a "+Vehicle2.carCleanliness[0]+" "+Vehicle2.carType[obj.getCarType(vehicle_1)]+" "+vehicle_1+" using Chemical method but it was still "+Vehicle2.carCleanliness[0]);
                fnc.washOutcome(0,j,0,obj,vehicle_1,0,1,0,0);//obs
            }
            prob = rand.nextDouble();                   //probability for updating condition
            if (prob < 0.1){
                System.out.println("The "+vehicle_1+" has become Broken");
                fnc.washOutcome(vehicle_1,0,0);
                obj.updateCondition(0,vehicle_1);         //Update condition to 'Broken'
            }
        }
        if(Vehicle2.cleanliness[1].size()!=0){           //Checks if there is any 'Clean' vehicle present, then only intern can wash it
            cleanliness_choice = rand.nextInt( Vehicle2.cleanliness[1].size());
            vehicle_2 =  Vehicle2.cleanliness[1].get(cleanliness_choice);
            prob = rand.nextDouble();
            if (prob < 0.1) {
                Vehicle2.cleanliness[0].add(vehicle_2);  //update cleanliness back to 'Dirty'
                System.out.println(staffType[0]+" " +staff[0].get(j)+" washed a "+Vehicle2.carCleanliness[1]+" "+Vehicle2.carType[obj.getCarType(vehicle_2)]+" "+vehicle_2+" using Chemical method but made it "+Vehicle2.carCleanliness[0]);
                fnc.washOutcome(0,j,1,obj,vehicle_2,0,2,0,0);//obs
                Vehicle2.cleanliness[1].remove(cleanliness_choice);
            } else if(prob >= 0.8) {
                Vehicle2.cleanliness[2].add(vehicle_2);  //update cleanliness to 'Sparkling'
                Intern2.getBonus(j,vehicle_2);                  //Intern gets bonus
                System.out.println(staffType[0]+" " +staff[0].get(j)+" washed a "+Vehicle2.carCleanliness[1]+" "+Vehicle2.carType[obj.getCarType(vehicle_2)]+" "+vehicle_2+" using Chemical method and made it "+Vehicle2.carCleanliness[2]+" (and earned a bonus of $"+bonus_val+")");
                fnc.washOutcome(0,j,1,obj,vehicle_2,0,0,2,bonus_val);//obs
                Vehicle2.cleanliness[1].remove(cleanliness_choice);
            } else{
                System.out.println(staffType[0]+" " +staff[0].get(j)+" washed a "+Vehicle2.carCleanliness[1]+" "+Vehicle2.carType[obj.getCarType(vehicle_2)]+" "+vehicle_2+" using Chemical method but it was still "+Vehicle2.carCleanliness[1]);
                fnc.washOutcome(0,j,1,obj,vehicle_2,0,1,1,0);//obs
            }
            prob = rand.nextDouble();                   //probability for updating condition
            if (prob < 0.1){
                System.out.println("The "+vehicle_2+" has become Broken");
                fnc.washOutcome(vehicle_2,0,0);
                obj.updateCondition(0,vehicle_2);         //Update condition to 'Broken'
            }
        }
    }
}
//For 2nd FNCD
class ElbowGrease2 extends Intern2 implements WashBehavior2{
    public void wash(int j, FNCDdata2 fnc) throws IOException {
        Vehicle2 obj = new Vehicle2();
        if(Vehicle2.cleanliness[0].size()!=0){           //Checks if there is any 'Dirty' vehicle present, then only intern can wash it
            cleanliness_choice = rand.nextInt( Vehicle2.cleanliness[0].size());
            vehicle_1 =  Vehicle2.cleanliness[0].get(cleanliness_choice);
            prob = rand.nextDouble();                   //probability for updating cleanliness
            if (prob >= 0.25 && prob < 0.95) {
                Vehicle2.cleanliness[1].add(vehicle_1);  //update cleanliness to 'Clean'
                System.out.println(staffType[0]+" " +staff[0].get(j)+" washed a "+Vehicle2.carCleanliness[0]+" "+Vehicle2.carType[obj.getCarType(vehicle_1)]+" "+vehicle_1+" using Elbow Grease method and made it "+Vehicle2.carCleanliness[1]);
                fnc.washOutcome(0,j,0,obj,vehicle_1,1,0,1,0);//obs
                Vehicle2.cleanliness[0].remove(cleanliness_choice);
            } else if (prob >= 0.95) {
                Vehicle2.cleanliness[2].add(vehicle_1);  //Update cleanliness to 'Sparkling'
                Intern2.getBonus(j,vehicle_1);                  //Intern gets bonus
                System.out.println(staffType[0]+" " +staff[0].get(j)+" washed a "+Vehicle2.carCleanliness[0]+" "+Vehicle2.carType[obj.getCarType(vehicle_1)]+" "+vehicle_1+" using Elbow Grease method and made it "+Vehicle2.carCleanliness[2]+" (and earned bonus of $"+bonus_val+")");
                fnc.washOutcome(0,j,0,obj,vehicle_1,1,0,2,bonus_val);//obs
                Vehicle2.cleanliness[0].remove(cleanliness_choice);
            } else{
                System.out.println(staffType[0]+" " +staff[0].get(j)+" washed a "+Vehicle2.carCleanliness[0]+" "+Vehicle2.carType[obj.getCarType(vehicle_1)]+" "+vehicle_1+" using Elbow Grease method but it was still "+Vehicle2.carCleanliness[0]);
                fnc.washOutcome(0,j,0,obj,vehicle_1,1,1,0,0);//obs
            }
            prob = rand.nextDouble();                   //probability for updating condition
            if (prob < 0.1){
                System.out.println("The "+vehicle_1+" has become Like New");
                fnc.washOutcome(vehicle_1,2,0);
                obj.updateCondition(2,vehicle_1);         //Update condition to 'Like New'
            }
        }
        if(Vehicle2.cleanliness[1].size()!=0){           //Checks if there is any 'Clean' vehicle present, then only intern can wash it
            cleanliness_choice = rand.nextInt( Vehicle2.cleanliness[1].size());
            vehicle_2 =  Vehicle2.cleanliness[1].get(cleanliness_choice);
            prob = rand.nextDouble();
            if (prob < 0.15) {
                Vehicle2.cleanliness[0].add(vehicle_2);  //update cleanliness back to 'Dirty'
                System.out.println(staffType[0]+" " +staff[0].get(j)+" washed a "+Vehicle2.carCleanliness[1]+" "+Vehicle2.carType[obj.getCarType(vehicle_2)]+" "+vehicle_2+" using Elbow Grease method but made it "+Vehicle2.carCleanliness[0]);
                fnc.washOutcome(0,j,1,obj,vehicle_2,1,2,0,0);//obs
                Vehicle2.cleanliness[1].remove(cleanliness_choice);
            } else if(prob >= 0.85) {
                Vehicle2.cleanliness[2].add(vehicle_2);  //update cleanliness to 'Sparkling'
                Intern2.getBonus(j,vehicle_2);                  //Intern gets bonus
                System.out.println(staffType[0]+" " +staff[0].get(j)+" washed a "+Vehicle2.carCleanliness[1]+" "+Vehicle2.carType[obj.getCarType(vehicle_2)]+" "+vehicle_2+" using Elbow Grease method and made it "+Vehicle2.carCleanliness[2]+" (and earned a bonus of $"+bonus_val+")");
                fnc.washOutcome(0,j,1,obj,vehicle_2,1,0,2,bonus_val);//obs
                Vehicle2.cleanliness[1].remove(cleanliness_choice);
            } else{
                System.out.println(staffType[0]+" " +staff[0].get(j)+" washed a "+Vehicle2.carCleanliness[1]+" "+Vehicle2.carType[obj.getCarType(vehicle_2)]+" "+vehicle_2+" using Elbow Grease method but it was still "+Vehicle2.carCleanliness[1]);
                fnc.washOutcome(0,j,1,obj,vehicle_2,1,1,1,0);//obs
            }
            prob = rand.nextDouble();                   //probability for updating condition
            if (prob < 0.1){
                System.out.println("The "+vehicle_2+" has become Like New");
                fnc.washOutcome(vehicle_2,2,0);
                obj.updateCondition(2,vehicle_2);         //Update condition to 'Like New'
            }
        }
    }
}
//For 2nd FNCD
class Detailed2 extends Intern2 implements WashBehavior2{
    public void wash(int j, FNCDdata2 fnc) throws IOException {
        Vehicle2 obj = new Vehicle2();
        if(Vehicle2.cleanliness[0].size()!=0){           //Checks if there is any 'Dirty' vehicle present, then only intern can wash it
            cleanliness_choice = rand.nextInt( Vehicle2.cleanliness[0].size());
            vehicle_1 =  Vehicle2.cleanliness[0].get(cleanliness_choice);
            prob = rand.nextDouble();                   //probability for updating cleanliness
            if (prob >= 0.2 && prob < 0.8) {
                Vehicle2.cleanliness[1].add(vehicle_1);  //update cleanliness to 'Clean'
                System.out.println(staffType[0]+" " +staff[0].get(j)+" washed a "+Vehicle2.carCleanliness[0]+" "+Vehicle2.carType[obj.getCarType(vehicle_1)]+" "+vehicle_1+" using Detailed method and made it "+Vehicle2.carCleanliness[1]);
                fnc.washOutcome(0,j,0,obj,vehicle_1,2,0,1,0);//obs
                Vehicle2.cleanliness[0].remove(cleanliness_choice);
            } else if (prob >= 0.8) {
                Vehicle2.cleanliness[2].add(vehicle_1);  //Update cleanliness to 'Sparkling'
                Intern2.getBonus(j,vehicle_1);                  //Intern gets bonus
                System.out.println(staffType[0]+" " +staff[0].get(j)+" washed a "+Vehicle2.carCleanliness[0]+" "+Vehicle2.carType[obj.getCarType(vehicle_1)]+" "+vehicle_1+" using Detailed method and made it "+Vehicle2.carCleanliness[2]+" (and earned bonus of $"+bonus_val+")");
                fnc.washOutcome(0,j,0,obj,vehicle_1,2,0,2,bonus_val);//obs
                Vehicle2.cleanliness[0].remove(cleanliness_choice);
            } else{
                System.out.println(staffType[0]+" " +staff[0].get(j)+" washed a "+Vehicle2.carCleanliness[0]+" "+Vehicle2.carType[obj.getCarType(vehicle_1)]+" "+vehicle_1+" using Detailed method but it was still "+Vehicle2.carCleanliness[0]);
                fnc.washOutcome(0,j,0,obj,vehicle_1,2,1,0,0);//obs
            }
        }
        if(Vehicle2.cleanliness[1].size()!=0){           //Checks if there is any 'Clean' vehicle present, then only intern can wash it
            cleanliness_choice = rand.nextInt( Vehicle2.cleanliness[1].size());
            vehicle_2 =  Vehicle2.cleanliness[1].get(cleanliness_choice);
            prob = rand.nextDouble();
            if (prob < 0.05) {
                Vehicle2.cleanliness[0].add(vehicle_2);  //update cleanliness back to 'Dirty'
                System.out.println(staffType[0]+" " +staff[0].get(j)+" washed a "+Vehicle2.carCleanliness[1]+" "+Vehicle2.carType[obj.getCarType(vehicle_2)]+" "+vehicle_2+" using Detailed method but made it "+Vehicle2.carCleanliness[0]);
                fnc.washOutcome(0,j,1,obj,vehicle_2,2,2,0,0);//obs
                Vehicle2.cleanliness[1].remove(cleanliness_choice);
            } else if(prob >= 0.6) {
                Vehicle2.cleanliness[2].add(vehicle_2);  //update cleanliness to 'Sparkling'
                Intern2.getBonus(j,vehicle_2);                  //Intern gets bonus
                System.out.println(staffType[0]+" " +staff[0].get(j)+" washed a "+Vehicle2.carCleanliness[1]+" "+Vehicle2.carType[obj.getCarType(vehicle_2)]+" "+vehicle_2+" using Detailed method and made it "+Vehicle2.carCleanliness[2]+" (and earned a bonus of $"+bonus_val+")");
                fnc.washOutcome(0,j,1,obj,vehicle_2,2,0,2,bonus_val);//obs
                Vehicle2.cleanliness[1].remove(cleanliness_choice);
            } else{
                System.out.println(staffType[0]+" " +staff[0].get(j)+" washed a "+Vehicle2.carCleanliness[1]+" "+Vehicle2.carType[obj.getCarType(vehicle_2)]+" "+vehicle_2+" using Detailed method but it was still "+Vehicle2.carCleanliness[1]);
                fnc.washOutcome(0,j,1,obj,vehicle_2,2,1,1,0);//obs
            }
        }
    }
}
//Example of Inheritance
//For 2nd FNCD
class Intern2 extends Staff2{
    static String[] clean_outcome = {" and made it "," but it was still "," but made it "};
    static String[] wash_method = {"Chemical", "Elbow Grease", "Detailed"};
    int cleanliness_choice;
    String vehicle_1,vehicle_2;
    WashBehavior2 washBehavior;
    //primary method for intern to wash vehicles and update cleanliness and condition of cars
    public void setWashBehavior(FNCDdata2 fnc) throws IOException {
        System.out.println("\nWashing in South FNCD");
        fnc.dayAct(1,0);//obs
        for (int j=0;j<staff[0].size();j++){
            int i = rand.nextInt(3);
            if (i==0){
                washBehavior = new Chemical2();
            } else if (i==1) {
                washBehavior = new ElbowGrease2();
            } else {
                washBehavior = new Detailed2();
            }
            washBehavior.wash(j,fnc);
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
        for(int i=0; i<9;i++){//prj4
            for(int j = 0; j<  Vehicle2.vehicle[i].size(); j++) {
                if ( Vehicle2.vehicle[i].get(j).equals(vehicle)){
                    bonus[0].set(k, bonus[0].get(k) +  Vehicle2.vehicle_wash_bonus[i]);//Bonus is decided based on type of car
                    bonus_val = Vehicle2.vehicle_wash_bonus[i];
                    break;
                }
            }
        }
    }
    //Calculating the salary based on normal pay and bonus play
    public void getSalary(FNCDdata2 fnc) throws IOException {
        for(int j=0;j<staff[0].size();j++){
            salary[0].set(j, normal_pay[0]+bonus[0].get(j));    //salary is calculated by adding bonus and normal pay for the day
            Operation2.addBudget(0,j,fnc);
            Operation2.budget=Operation2.budget-salary[0].get(j); //Salary is reduced from the budget
            Operation2.Staff_money=Operation2.Staff_money+salary[0].get(j);//Adding salary to total money earned by Staff
            total_normal_pay[0].set(j,total_normal_pay[0].get(j)+normal_pay[0]);
            total_salary[0].set(j, total_salary[0].get(j)+salary[0].get(j));
            total_bonus[0].set(j, total_bonus[0].get(j)+bonus[0].get(j));
        }
    }
    //Adding a new intern
    public void addIntern(FNCDdata2 fnc) throws IOException {
        while(staff[0].size()<3){      //If number of Interns is less than 3, add new interns
            staff[0].add(getName());
            System.out.println("Hired a new Intern "+(staff[0].get(staff[0].size()-1))+".");
            fnc.dayEnd(0,staff[0].size()-1,1);//obs
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
//For 2nd FNCD
class Mechanic2 extends Staff2{
    int index,vehicle_choice;
    String vehicle;
    //primary method for mechanic to repair cars and update condition and cleanliness
    public void repair(FNCDdata2 fnc) throws IOException {
        System.out.println("\nRepairing in South FNCD");
        fnc.dayAct(2,0);//obs
        Vehicle2 obj = new Vehicle2();
        for (int j=0;j<staff[1].size();j++){
            for(int k=0;k<2;k++){
                index = rand.nextInt(2);     //we don't want to select a 'like new' car for repairing
                if(Vehicle2.condition[index].size()==0){//If there is no car from selected condition (eg. Broken)
                    if (index==0){
                        index=1;                   //If the selected condition was 'Broken' then select condition 'Used'
                    }
                    else{
                        index=0;                   //If the selected condition was 'Used' then select condition 'Broken'
                    }
                }
                if(Vehicle2.condition[index].size()!=0){  //If there is a car from selected condition, repair it
                    repair_update(j,obj,fnc);
                }
            }
        }
    }
    // a method which decides whether the car was fixed and condition was updated
    // along with degrading the car cleanliness
    public void repair_update(int j,Vehicle2 obj,FNCDdata2 fnc) throws IOException {
        vehicle_choice = rand.nextInt(Vehicle2.condition[index].size());
        vehicle = Vehicle2.condition[index].get(vehicle_choice);
        prob = rand.nextDouble();
        if (prob < 0.8) {
            getBonus(j, vehicle);    //Mechanic gets bonus if car condition level is fixed to next condition (e.g. from dirty to clean)
            System.out.print(staffType[1]+" "+staff[1].get(j)+" repaired a "+Vehicle2.carCondition[obj.getCondition(vehicle)]+" "+Vehicle2.carType[obj.getCarType(vehicle)]+" "+vehicle+" and made it "+Vehicle2.carCondition[obj.getCondition(vehicle)+1]+" (and earned a bonus of $"+bonus_val+")");
            fnc.repairOutcome(1,j,obj,vehicle,0,bonus_val);//obs
            Vehicle2.condition[index+1].add(vehicle);
            //Example of Delegation
            obj.updateSalesPrice(index,vehicle); //when condition of car is fixed to next level, its sales prices is updated
            Vehicle2.condition[index].remove(vehicle_choice);
        } else{
            System.out.print(staffType[1]+" "+staff[1].get(j)+" repaired a "+Vehicle2.carCondition[obj.getCondition(vehicle)]+" "+Vehicle2.carType[obj.getCarType(vehicle)]+" "+vehicle+" but it was still "+Vehicle2.carCondition[obj.getCondition(vehicle)]);
            fnc.repairOutcome(1,j,obj,vehicle,1,0);//obs
        }
        obj.updateCleanliness(vehicle); //Degrade the cleanliness by one level
        fnc.repairOutcome(obj,vehicle);//obs
        System.out.println(" and the vehicle became "+Vehicle2.carCleanliness[obj.getCleanliness(vehicle)]);
    }

    public void getTotalDays(){
        for(int j=0;j<staff[1].size();j++){
            days_worked[1].set(j, days_worked[1].get(j)+1); //Total days worked increases by 1 after each day a mechanic was active
        }
    }
    public static void getBonus(int k, String vehicle){
        for(int i=0; i<9;i++){//prj4
            for(int j = 0; j< Vehicle2.vehicle[i].size(); j++) {
                if (Vehicle2.vehicle[i].get(j).equals(vehicle)) {
                    bonus[1].set(k, bonus[1].get(k) + Vehicle2.vehicle_repair_bonus[i]); //Bonus is decided based on type of car
                    bonus_val=Vehicle2.vehicle_repair_bonus[i];
                    break;
                }
            }
        }
    }
    public void getSalary(FNCDdata2 fnc) throws IOException {
        for(int j=0;j<staff[1].size();j++){
            salary[1].set(j, normal_pay[1]+bonus[1].get(j));//salary is calculated by adding bonus and normal pay for the day
            Operation2.addBudget(1,j,fnc);
            Operation2.budget=Operation2.budget-salary[1].get(j);
            Operation2.Staff_money=Operation2.Staff_money+salary[1].get(j);
            total_normal_pay[1].set(j,total_normal_pay[1].get(j)+normal_pay[1]);
            total_salary[1].set(j, total_salary[1].get(j)+salary[1].get(j));
            total_bonus[1].set(j, total_bonus[1].get(j)+bonus[1].get(j));
        }
    }
}
//For 2nd FNCD
class Salesperson2 extends Staff2{
    static int index2,buyer_index1,buyer_index2,buyer_choice,vehicle_choice;
    static Vehicle2[] vehicle_object = {new Car2(),new Pickup2(),new PerformanceCar2(),new Motorcycles2(),new MonsterTrucks2(),new ElectricCar2(),new BudgetCar2(),new LuxuryCar2(),new SuperCar2()};
    String salesperson,buyer,vehicle;
    //primary method for salespersons to sell cars
    public void sale(FNCDdata2 fnc) throws IOException {
        System.out.println("\nSelling in South FNCD");
        fnc.dayAct(3,0);//obs
        Operation2.total_sales = 0;//Initializing the total sales at the beginning of the day
        Buyer2 buyer1 = new Buyer2();
        //Checks if there are any buyers present
        if (Buyer2.buyer_no !=0){
            for(int i = 0; i< Buyer2.buyer_no; i++){
                index2 = rand.nextInt(staff[2].size());
                salesperson = staff[2].get(index2);   //Salesperson is randomly assigned
                buyer="Buyer"+(i+1);
                buyer_index1=buyer1.getBuyerIndex1(buyer);
                buyer_index2=buyer1.getBuyerIndex2(buyer);
                buyer_choice= Buyer2.buyer_choice[buyer_index1].get(buyer_index2);
                Vehicle2 obj= new Vehicle2();
                obj.VehicleTopPrice();      //getting car with top sales price from buyers choice
                if(Vehicle2.max_sale_price[buyer_choice] != 0){
                    for(int j = 0; j< Vehicle2.sales_price[buyer_choice].size(); j++){
                        if(Vehicle2.sales_price[buyer_choice].get(j)== Vehicle2.max_sale_price[buyer_choice]){
                            vehicle= Vehicle2.vehicle[buyer_choice].get(j);
                            vehicle_choice=j;
                            if(obj.getCondition(vehicle)==2){   //checking the condition of the car to be Like New
                                //add 10% chance to buyer
                                Buyer2.buyer_prob[buyer_index1].set(buyer_index2,(Buyer2.buyer_prob[buyer_index1].get(buyer_index2)+0.1));
                            }
                            if(obj.getCleanliness(vehicle)==2){   //checking the cleanliness of the car to be Sparkling
                                //add 10% chance to buyer
                                Buyer2.buyer_prob[buyer_index1].set(buyer_index2,(Buyer2.buyer_prob[buyer_index1].get(buyer_index2)+0.1));
                            }
                            if(Vehicle2.race_won[buyer_choice].get(vehicle_choice)>=1){ //checking the count of races won by vehicle
                                //add 10% chance to buyer
                                Buyer2.buyer_prob[buyer_index1].set(buyer_index2,(Buyer2.buyer_prob[buyer_index1].get(buyer_index2)+0.1));
                            }
                            break;
                        }
                    }
                }else{             //If Buyer's choice is not found, the vehicle top price from rest of the cars is selected
                    buyer_choice=obj.totalVehicleTopPrice();
                    for(int j = 0; j< Vehicle2.sales_price[buyer_choice].size(); j++){
                        if(Vehicle2.sales_price[buyer_choice].get(j)== Vehicle2.max_sale_price[buyer_choice]){
                            vehicle= Vehicle2.vehicle[buyer_choice].get(j);
                            vehicle_choice=j;
                            if(obj.getCondition(vehicle)==2){    //checking the condition of the car to be Like New
                                //add 10% chance to buyer
                                Buyer2.buyer_prob[buyer_index1].set(buyer_index2,(Buyer2.buyer_prob[buyer_index1].get(buyer_index2)+0.1));
                            }
                            if(obj.getCleanliness(vehicle)==2){  //checking the cleanliness of the car to be Sparkling
                                //add 10% chance to buyer
                                Buyer2.buyer_prob[buyer_index1].set(buyer_index2,(Buyer2.buyer_prob[buyer_index1].get(buyer_index2)+0.1));
                            }
                            if(Vehicle2.race_won[buyer_choice].get(vehicle_choice)>=1){ //checking the count of races won by vehicle
                                //add 10% chance to buyer
                                Buyer2.buyer_prob[buyer_index1].set(buyer_index2,(Buyer2.buyer_prob[buyer_index1].get(buyer_index2)+0.1));
                            }
                            //now reducing 20% chance from buyer due to unavailability of buyer's choice of car
                            if(Buyer2.buyer_prob[buyer_index1].get(buyer_index2)>=0.2){
                                Buyer2.buyer_prob[buyer_index1].set(buyer_index2,(Buyer2.buyer_prob[buyer_index1].get(buyer_index2)-0.2));
                            }else {
                                //if buyer has less than 20% chance of buying, reducing the by 20% would result in negative value.
                                // Hence, we are making it zero.
                                Buyer2.buyer_prob[buyer_index1].set(buyer_index2,0.0);
                            }
                            break;
                        }
                    }
                }
                //updating vehicles, budget, and conditions and cleanliness of vehicles
                prob = rand.nextDouble();
                if (prob < Buyer2.buyer_prob[buyer_index1].get(buyer_index2)){
                    //if buyer buys the vehicle, check for add on purchases using decorator pattern
                    Vehicle2 vecl = vehicle_object[buyer_choice];//creating object of one of the car types of buyer's choice
                    vecl = new ExtendedWarranty2(buyer_choice,vehicle_choice,fnc);//wrapping it with decorator components
                    vecl = new Undercoating2(buyer_choice,vehicle_choice,fnc);
                    vecl = new RoadRescueCoverage2(buyer_choice,vehicle_choice,fnc);
                    vecl = new SatelliteRadio2(buyer_choice,vehicle_choice,fnc);
                    Operation2.budget = Operation2.budget + Vehicle2.sales_price[buyer_choice].get(vehicle_choice);//Sales price of car added to budget
                    Operation2.total_sales = Operation2.total_sales + Vehicle2.sales_price[buyer_choice].get(vehicle_choice);//updated Total sales of the day
                    getBonus(index2, vehicle);    //Salesperson gets a bonus
                    Vehicle2.soldVehicles[buyer_choice].add(vehicle); //Car is added to list of sold vehicles
                    System.out.println(staffType[2]+" "+salesperson +" has sold a "+Vehicle2.carCleanliness[obj.getCleanliness(vehicle)]+" "+Vehicle2.carCondition[obj.getCondition(vehicle)]+" "+Vehicle2.carType[buyer_choice] +" "+ vehicle +" to "+buyer+" for $"+Vehicle2.sales_price[buyer_choice].get(vehicle_choice)+" (and earned a bonus of $"+bonus_val+")");
                    fnc.sellOutcome(2,salesperson,obj,vehicle,buyer_choice,buyer,vehicle_choice,bonus_val);//obs
                    Vehicle2.vehicle[buyer_choice].remove(vehicle_choice); //Car is removed from Vehicles in stock
                    Vehicle2.status[buyer_choice].remove(vehicle_choice);
                    Vehicle2.condition[obj.getCondition(vehicle)].remove(obj.getCondition2(vehicle));
                    Vehicle2.cleanliness[obj.getCleanliness(vehicle)].remove(obj.getCleanliness2(vehicle));
                    Vehicle2.cost_price[buyer_choice].remove(vehicle_choice);
                    Vehicle2.sales_price[buyer_choice].remove(vehicle_choice);
                }
            }
        }
    }
    public void sale2(FNCDdata2 fnc) throws IOException {        //sale for the 31st day
        fnc.dayAct(3,0);//obs
        Vehicle2 obj = new Vehicle2();
        Operation2.budget = Operation2.budget + Vehicle2.sales_price[commandReceiver.user_choice].get(commandReceiver.vehicle_choice);//Sales price of car added to budget
        Operation2.total_sales = Operation2.total_sales + Vehicle2.sales_price[commandReceiver.user_choice].get(commandReceiver.vehicle_choice);//updated Total sales of the day
        getBonus(index2, commandReceiver.vehicle);    //Salesperson gets a bonus
        Vehicle2.soldVehicles[commandReceiver.user_choice].add(commandReceiver.vehicle); //Car is added to list of sold vehicles
        System.out.println(staffType[2]+" "+commandReceiver.salesperson +" has sold a "+commandReceiver.vehicle_cleanliness+" "+commandReceiver.vehicle_condition+" "+commandReceiver.vehicle_type+" "+ commandReceiver.vehicle +" to User for $"+commandReceiver.sales_price+" (and earned a bonus of $"+bonus_val+")");
        fnc.sellOutcome(2,commandReceiver.salesperson,obj,commandReceiver.vehicle,commandReceiver.user_choice,"User",commandReceiver.vehicle_choice,bonus_val);//obs
        Vehicle2.vehicle[commandReceiver.user_choice].remove(commandReceiver.vehicle_choice); //Car is removed from Vehicles in stock
        Vehicle2.status[commandReceiver.user_choice].remove(commandReceiver.vehicle_choice);
        Vehicle2.condition[obj.getCondition(commandReceiver.vehicle)].remove(obj.getCondition2(commandReceiver.vehicle));
        Vehicle2.cleanliness[obj.getCleanliness(commandReceiver.vehicle)].remove(obj.getCleanliness2(commandReceiver.vehicle));
        Vehicle2.cost_price[commandReceiver.user_choice].remove(commandReceiver.vehicle_choice);
        Vehicle2.sales_price[commandReceiver.user_choice].remove(commandReceiver.vehicle_choice);
    }
    public void getTotalDays(){
        for(int j=0;j<staff[2].size();j++){
            days_worked[2].set(j, days_worked[2].get(j)+1);  //Total days worked increases by 1 after each day a salesperson was active
        }
    }
    public static void getBonus(int k, String vehicle){
        for(int i=0; i<9;i++){
            for(int j = 0; j< Vehicle2.vehicle[i].size(); j++) {
                if (Vehicle2.vehicle[i].get(j).equals(vehicle)){
                    bonus[2].set(k, bonus[2].get(k) + Vehicle2.vehicle_sale_bonus[i]);  //Bonus is decided based on type of car
                    bonus_val=Vehicle2.vehicle_sale_bonus[i];
                    break;
                }
            }
        }
    }
    public void getSalary(FNCDdata2 fnc) throws IOException {
        for(int j=0;j<staff[2].size();j++){
            salary[2].set(j, normal_pay[2]+bonus[2].get(j));//salary is calculated by adding bonus and normal pay for the day
            Operation2.addBudget(2,j,fnc);
            Operation2.budget=Operation2.budget-salary[2].get(j);
            Operation2.Staff_money=Operation2.Staff_money+salary[2].get(j);
            total_normal_pay[2].set(j,total_normal_pay[2].get(j)+normal_pay[2]);
            total_salary[2].set(j, total_salary[2].get(j)+salary[2].get(j));
            total_bonus[2].set(j, total_bonus[2].get(j)+bonus[2].get(j));
        }
    }
}

//For 2nd FNCD
class Driver2 extends Staff2{
    int choice_index,choice,pos;//prj4
    int[] race_choice = {1,2,3,4,7,8};//prj4
    ArrayList<Integer> positions;
    static ArrayList<Integer> race_position;
    ArrayList<String> vehicles_choice;
    static ArrayList<String> race_vehicles;
    ArrayList<String> injured_drivers;
    Vehicle2 obj = new Vehicle2();

    public void getRaceVehicles(FNCDdata2 fnc) throws IOException {      //All methods in Driver will check for day to be Wednesday or Sunday
        if(Operation2.day_count==3 || Operation2.day_count==10 || Operation2.day_count==17 || Operation2.day_count==24 ||
                Operation2.day_count==7 || Operation2.day_count==14 || Operation2.day_count==21 || Operation2.day_count==28) {
            System.out.println("\nRacing in South FNCD");
            fnc.dayAct(4,0);//obs
            vehicles_choice = new ArrayList<>();
            race_vehicles = new ArrayList<>();
            injured_drivers = new ArrayList<>();
            choice_index = rand.nextInt(race_choice.length);        //selecting a vehicle eligible for racing//prj4
            choice = race_choice[choice_index];//prj4
            vehicles_choice = Vehicle2.vehicle[choice];
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
    public void race(FNCDdata2 fnc) throws IOException {
        if(Operation2.day_count==3 || Operation2.day_count==10 || Operation2.day_count==17 || Operation2.day_count==24 ||
                Operation2.day_count==7 || Operation2.day_count==14 || Operation2.day_count==21 || Operation2.day_count==28) {
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
                                Vehicle2.race_won[choice].set(j,Vehicle2.race_won[choice].get(j)+1);//updating race won count for that vehicle
                                race_won.set(i, race_won.get(i)+1);       //updating race won count for the driver of the vehicle
                                getBonus(i,vehicles_choice.get(j));       //Giving bonus to driver
                                System.out.println(staffType[3]+" "+staff[3].get(i)+" finished the race at position "+race_position.get(i)+" with the "+Vehicle2.carType[choice]+" "+race_vehicles.get(i)+" and Won the race (and earned a bonus of $"+bonus_val+")");
                                fnc.raceOutcome(3,i,choice,bonus_val,0);//obs
                                break;
                            }
                        }
                    } else if (race_position.get(i)>15){                  //If vehicle gets damaged
                        for (int j=0;j<vehicles_choice.size();j++){
                            if (vehicles_choice.get(j).equals(race_vehicles.get(i))) {
                                Vehicle2.condition[0].add(vehicles_choice.get(j));//update the condition of vehicle to broken
                                Vehicle2.condition[obj.getCondition(vehicles_choice.get(j))].remove(obj.getCondition2(vehicles_choice.get(j)));//removing the previous condition
                                System.out.println(staffType[3]+" "+staff[3].get(i)+" finished the race at position "+race_position.get(i)+" with the "+Vehicle2.carType[choice]+" "+race_vehicles.get(i)+" and the Vehicle became Broken ");
                                fnc.raceOutcome(3,i,choice,0,2);//obs
                                prob=rand.nextDouble();
                                if (prob<0.3){
                                    injured_drivers.add(staff[3].get(i)); //The Driver is injured, and he is added to injured drivers
                                    System.out.println("Driver "+staff[3].get(i)+" got injured and quit the FNCD");
                                    fnc.raceOutcome(3,i);//obs
                                }
                                break;
                            }
                        }
                    } else {
                        for (int j=0;j<vehicles_choice.size();j++) {
                            if (vehicles_choice.get(j).equals(race_vehicles.get(i))) {
                                System.out.println(staffType[3]+" "+staff[3].get(i)+" finished the race at position "+race_position.get(i)+" with the "+Vehicle2.carType[choice]+" "+race_vehicles.get(i));
                                fnc.raceOutcome(3,i,choice,0,1);//obs
                                break;
                            }
                        }
                    }
                }
            }
        }
    }
    public void getTotalDays(){
        if(Operation2.day_count==3 || Operation2.day_count==10 || Operation2.day_count==17 || Operation2.day_count==24 ||
                Operation2.day_count==7 || Operation2.day_count==14 || Operation2.day_count==21 || Operation2.day_count==28) {
            for(int j=0;j<staff[3].size();j++){
                days_worked[3].set(j, days_worked[3].get(j)+1);  //Total days worked increases by 1 after each day a salesperson was active
            }
        }
    }
    public static void getBonus(int k, String vehicle){
        if(Operation2.day_count==3 || Operation2.day_count==10 || Operation2.day_count==17 || Operation2.day_count==24 ||
                Operation2.day_count==7 || Operation2.day_count==14 || Operation2.day_count==21 || Operation2.day_count==28) {
            for(int i=0; i<9;i++){                               //prj4
                for(int j = 0; j< Vehicle2.vehicle[i].size(); j++) {
                    if (Vehicle2.vehicle[i].get(j).equals(vehicle)){
                        bonus[3].set(k, bonus[3].get(k) + Vehicle2.race_win_bonus[i]);  //Bonus is decided based on type of car
                        bonus_val=Vehicle2.race_win_bonus[i];
                        break;
                    }
                }
            }
        }
    }
    public void getSalary(FNCDdata2 fnc) throws IOException {
        if(Operation2.day_count==3 || Operation2.day_count==10 || Operation2.day_count==17 || Operation2.day_count==24 ||
                Operation2.day_count==7 || Operation2.day_count==14 || Operation2.day_count==21 || Operation2.day_count==28) {
            for(int j=0;j<staff[3].size();j++){
                salary[3].set(j, normal_pay[3]+bonus[3].get(j));//salary is calculated by adding bonus and normal pay for the day
                Operation2.addBudget(3,j,fnc);
                Operation2.budget=Operation2.budget-salary[3].get(j);
                Operation2.Staff_money=Operation2.Staff_money+salary[3].get(j);
                total_normal_pay[3].set(j,total_normal_pay[3].get(j)+normal_pay[3]);
                total_salary[3].set(j, total_salary[3].get(j)+salary[3].get(j));
                total_bonus[3].set(j, total_bonus[3].get(j)+bonus[3].get(j));
            }
        }
    }
    public void addDriver(FNCDdata2 fnc) throws IOException {
        if(Operation2.day_count==3 || Operation2.day_count==10 || Operation2.day_count==17 || Operation2.day_count==24 ||
                Operation2.day_count==7 || Operation2.day_count==14 || Operation2.day_count==21 || Operation2.day_count==28) {
            while(staff[3].size()<3){      //If number of Drivers is less than 3, add new drivers
                staff[3].add(getName());
                System.out.println("Hired a new Driver "+(staff[3].get(staff[3].size()-1))+".");
                fnc.dayEnd(3,staff[3].size()-1,2);//obs
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
        if(Operation2.day_count==3 || Operation2.day_count==10 || Operation2.day_count==17 || Operation2.day_count==24 ||
                Operation2.day_count==7 || Operation2.day_count==14 || Operation2.day_count==21 || Operation2.day_count==28) {
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
//Command Pattern
interface Command{
    public void execute();
}

class commandInvoker{
    private Command command;
    public void setCommand(Command command) {
        this.command = command;
    }

    public void executeCommand() {
        command.execute();
    }
}

class commandReceiver{
    Random rand = new Random();
    Scanner scanner = new Scanner(System.in);
    static String salesperson=null;
    static int FNCD_choice,user_choice,user_decision,user_decision2,vehicle_choice,races_won=0,sales_price=0;
    static String vehicle = null,vehicle_type = null,vehicle_condition = null,vehicle_cleanliness = null;
    // methods called as the concrete methods require them
    public void getFNCDLocation(){
        System.out.println("\n\n********************Selling with User inputs as Buyer********************\n");
        System.out.println("Enter the FNCD location (0 for North, 1 for South): ");
        FNCD_choice = scanner.nextInt();
        if (FNCD_choice == 0){
            System.out.println("\nFNCD Selected is: FNCD North");
        } else if (FNCD_choice == 1){
            System.out.println("\nFNCD Selected is: FNCD South");
        }
    }
    public void getSalesName(){
        if(FNCD_choice==0){
            Salesperson.index2 = rand.nextInt(Salesperson.staff[2].size());
            salesperson = Salesperson.staff[2].get(Salesperson.index2);
        } else if (FNCD_choice == 1){
            Salesperson2.index2 = rand.nextInt(Salesperson2.staff[2].size());
            salesperson = Salesperson2.staff[2].get(Salesperson2.index2);
        }
        System.out.println("\nSalesperson Selected is: " + salesperson);
    }

    public void getTime(){
        System.out.println("\nCurrent time is: "+ java.time.LocalDateTime.now().toLocalTime());
    }
    public void getDifferentSalesperson(){
        int i,index,diff_sal;
        System.out.println("\nDo you want different Salesperson? (0 for Yes, 1 for No): ");
        diff_sal = scanner.nextInt();
        if (diff_sal == 0){
            if (FNCD_choice ==0){
                for (i = 0; i < Staff.staff[2].size(); i++) {
                    if(Staff.staff[2].get(i) == salesperson){
                        break;
                    }
                }
                index = rand.nextInt(3);
                while(index == i){
                    index = rand.nextInt(3);
                }
                salesperson = Staff.staff[2].get(index);
            } else if (FNCD_choice == 1){
                for (i = 0; i < Staff2.staff[2].size(); i++) {
                    if(Staff2.staff[2].get(i) == salesperson){
                        break;
                    }
                }
                index = rand.nextInt(3);
                while(index == i){
                    index = rand.nextInt(3);
                }
                salesperson = Staff2.staff[2].get(index);
            }
            System.out.println("\nDifferent Salesperson Selected is: "+ salesperson);
        }

    }
    public void getInventory(){
        System.out.println("\nCurrent available Inventory of Vehicles:");
        if (FNCD_choice ==0){
            for (int i=0;i<9;i++){
                System.out.println(Vehicle.carType[i]+"s: "+Vehicle.vehicle[i]);
            }
        } else if (FNCD_choice == 1){
            for (int i=0;i<9;i++){
                System.out.println(Vehicle2.carType[i]+"s: "+Vehicle2.vehicle[i]);
            }
        }
    }
    public void getSelectedInventoryDetails() {
        if (FNCD_choice == 0){
            Vehicle obj = new Vehicle();
            System.out.println("\nEnter the choice of Vehicle Type (e.g. 0 for Car, 1 for Pickup, 8 for Super Car):   ");
            user_choice = scanner.nextInt();
            obj.VehicleTopPrice();      //getting car with top sales price from User's choice
            if(Vehicle.max_sale_price[user_choice] == 0) {
                user_choice = obj.totalVehicleTopPrice();
            }
            for(int j = 0; j< Vehicle.sales_price[user_choice].size(); j++){
                if(Vehicle.sales_price[user_choice].get(j)== Vehicle.max_sale_price[user_choice]){
                    vehicle= Vehicle.vehicle[user_choice].get(j);
                    vehicle_choice = j;
                    vehicle_type= Vehicle.carType[user_choice];
                    vehicle_condition = Vehicle.carCondition[obj.getCondition(vehicle)];
                    vehicle_cleanliness = Vehicle.carCleanliness[obj.getCleanliness(vehicle)];
                    races_won = Vehicle.race_won[user_choice].get(j);
                    sales_price = Vehicle.sales_price[user_choice].get(j);
                    break;
                }
            }
        } else if (FNCD_choice == 1){
            Vehicle2 obj= new Vehicle2();
            System.out.println("\nEnter the choice of Vehicle Type (e.g. 0 for Car, 1 for Pickup, 8 for Super Car:   ");
            user_choice = scanner.nextInt();
            obj.VehicleTopPrice();      //getting car with top sales price from User's choice
            if(Vehicle2.max_sale_price[user_choice] == 0) {
                user_choice = obj.totalVehicleTopPrice();
            }
            for(int j = 0; j< Vehicle2.sales_price[user_choice].size(); j++){
                if(Vehicle2.sales_price[user_choice].get(j)== Vehicle2.max_sale_price[user_choice]){
                    vehicle= Vehicle2.vehicle[user_choice].get(j);
                    vehicle_choice = j;
                    vehicle_type= Vehicle2.carType[user_choice];
                    vehicle_condition = Vehicle2.carCondition[obj.getCondition(vehicle)];
                    vehicle_cleanliness = Vehicle2.carCleanliness[obj.getCleanliness(vehicle)];
                    races_won = Vehicle2.race_won[user_choice].get(j);
                    sales_price = Vehicle2.sales_price[user_choice].get(j);
                    break;
                }
            }
        }
        System.out.println("\nUser selected Vehicle Type: "+vehicle_type+"\nSelected Vehicle for selling: "+vehicle);
        System.out.println("Condition of Vehicle: "+vehicle_condition+"\nCleanliness of Vehicle: "+vehicle_cleanliness);
        System.out.println("Races won by Vehicle: "+races_won+"\nSales Price of Vehicle: $"+sales_price);
        System.out.println("\nWould you like to purchase the vehicle? (0 for Yes, 1 for No): ");
        user_decision = scanner.nextInt();
    }
    public void buyVehicleAddOn() {
        if (user_decision==0){
            System.out.println("\nWould you like to purchase Add-ons? (0 for Yes, 1 for No): ");
            user_decision2 = scanner.nextInt();
            if (user_decision2==0){// Assumption: if user wants add-ons, it will be purchased based on probability,
                if(FNCD_choice==0){// so it may happen that even if user says yes to buy add-ons, no add-ons will be added due to low probability
                    Vehicle vecl = Salesperson.vehicle_object[user_choice];//creating object of one of the car types of buyer's choice
                    vecl = new ExtendedWarranty(user_choice,vehicle_choice);//wrapping it with decorator components
                    vecl = new Undercoating(user_choice,vehicle_choice);
                    vecl = new RoadRescueCoverage(user_choice,vehicle_choice);
                    vecl = new SatelliteRadio(user_choice,vehicle_choice);
                }else if (FNCD_choice == 1){
                    Vehicle2 vecl = Salesperson2.vehicle_object[user_choice];//creating object of one of the car types of buyer's choice
                    vecl = new ExtendedWarranty2(user_choice,vehicle_choice);//wrapping it with decorator components
                    vecl = new Undercoating2(user_choice,vehicle_choice);
                    vecl = new RoadRescueCoverage2(user_choice,vehicle_choice);
                    vecl = new SatelliteRadio2(user_choice,vehicle_choice);
                }
            }
        }
    }
    public void buyVehicle(){
        if(user_decision==0) {
            System.out.println("\nUser Purchased the " + vehicle_type + " " + vehicle + " for: $" + sales_price);
        }
    }
    public void endInteraction(){
        System.out.println("\nUser Interaction ended\n");
    }
}

class concreteCommand implements Command{
    private commandReceiver receiver;

    public concreteCommand(commandReceiver receiver) {
        this.receiver = receiver;
    }
    public void execute() {
        receiver.getFNCDLocation();
        receiver.getSalesName();
        receiver.getTime();
        receiver.getDifferentSalesperson();
        receiver.getInventory();
        receiver.getSelectedInventoryDetails();
        receiver.buyVehicleAddOn();
        receiver.buyVehicle();
        receiver.endInteraction();
    }
}

//For 2nd FNCD
class Vehicle2{
    static ArrayList<String> car_names = new ArrayList<>();
    static ArrayList<String> monster_truck_names = new ArrayList<>();
    static String[] carType = {"Car", "Pickup", "Performance Car","Motorcycle","Monster Truck","Electric Car","Budget Car","Luxury Car","Super Car"};//prj4
    static String[] carCondition = {"Broken", "Used", "Like New"};
    static String[] carCleanliness = {"Dirty","Clean","Sparkling"};
    static ArrayList<String>[] vehicle = new ArrayList[9];//prj4
    static ArrayList<Integer>[] race_won = new ArrayList[9];//prj4
    static ArrayList<String>[] status = new ArrayList[9];//prj4
    static ArrayList<String>[] condition = new ArrayList[3];
    static ArrayList<String>[] cleanliness = new ArrayList[3];
    static ArrayList<Integer>[] cost_price = new ArrayList[9];//prj4
    static ArrayList<Integer>[] sales_price = new ArrayList[9];//prj4
    static ArrayList<String>[] soldVehicles = new ArrayList[9];//prj4
    static int[] max_sale_price = {0,0,0,0,0,0,0,0,0};
    String conditionSelected = null;
    String lastOccurrence = null;
    static ArrayList<Integer> range = new ArrayList<>();
    static ArrayList<Double> engine_size = new ArrayList<>();
    Random rand= new Random();
    static int[] vehicle_wash_bonus = {50, 75, 100, 50, 75, 100, 50, 75, 100};//prj4
    static int[] vehicle_repair_bonus = {100, 125, 150, 100, 125, 150, 100, 125, 150};//prj4
    static int[] vehicle_sale_bonus = {150, 175, 200, 150, 175, 200, 150, 175, 200};//prj4
    static int[] race_win_bonus = {0, 200, 225, 250, 275, 0, 0, 300, 325};//prj4
    static String[] addon={"Extended Warranty","Undercoating","Road Rescue Coverage","Satellite Radio"};

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
        vehicle[0].addAll(Arrays.asList("Hyundai Elantra","Nissan Sentra","Chevrolet Spark","Kia Forte","Honda Accord","Hyundai Sonata"));//prj4
        vehicle[1].addAll(Arrays.asList("Ford Ranger","GMC Canyon","Chevrolet Colorado","Ram 1500 Classic","Nissan Frontier","Toyota Tacoma"));//prj4
        vehicle[2].addAll(Arrays.asList("Audi RS7","BMW M8","Mercedes-AMG GT","Porsche Panamera","Ferrari SF90 Stradale","Porsche 718 Cayman GT4"));//prj4
        vehicle[3].addAll(Arrays.asList("Aston Martin Vantage","McLaren Artura","Lamborghini Huracan","Porsche Taycan","Suzuki Hayabusa","Yamaha YZF-R6"));//prj4
        vehicle[4].addAll(Arrays.asList("Swamp Thing","Sudden Impact","USA-1","Bear Foot F-150","Iron Outlaw","Rampage"));//prj4
        vehicle[5].addAll(Arrays.asList("Audi R8","BMW i8","Mercedes-AMG GT 4-Door Coupe","Rolls-Royce Cullinan","Rivian R1T","Polestar 2"));//prj4
        vehicle[6].addAll(Arrays.asList("Toyota Yaris","Honda Fit","Kia Rio","Hyundai Accent","Nissan Versa","Suzuki Swift"));//prj4
        vehicle[7].addAll(Arrays.asList("Rolls-Royce Phantom","Lexus LS","BMW 7 Series","Jaguar XJ","Mercedes-Benz E-Class","Acura RLX"));//prj4
        vehicle[8].addAll(Arrays.asList("Bugatti Veyron","McLaren 765LT","Ferrari 812 Superfast","Pagani Huayra","Aston Martin Valkyrie","Rimac C2"));//prj4
        for (int i = 0; i < 9; i++) {//prj4
            for (int j = 0; j < 6; j++) {//prj4
                setCondition(vehicle[i].get(j));
                setCleanliness(vehicle[i].get(j));
                status[i].add("In Stock");
                race_won[i].add(0);
            }
        }
        //Initially added cost price and sales price using the logic sales price = cost price*2
        //But cost price is reduced according to the car condition So, after reducing the cost price, we added it to the list of cost price
        //And hence we see the difference between cost and sales price to be more that 2x for some cars
        cost_price[0].addAll(Arrays.asList(12738, 15840, 14099, 15548, 13875, 6660));//prj4
        cost_price[1].addAll(Arrays.asList(10138, 14950, 29560, 12342, 13175, 18538));//prj4
        cost_price[2].addAll(Arrays.asList(24427, 28972, 15907, 27326, 37178, 24469));//prj4
        cost_price[3].addAll(Arrays.asList(13981, 15556, 16421, 8289, 8417, 11666));//prj4
        cost_price[4].addAll(Arrays.asList(10600, 23285, 38495, 28509, 19278, 26031));//prj4
        cost_price[5].addAll(Arrays.asList(20170, 20619, 30528, 36973, 24910, 32723));//prj4
        cost_price[6].addAll(Arrays.asList(7812, 5678, 8937, 9543, 6789, 8520));//prj4
        cost_price[7].addAll(Arrays.asList(38754, 42678, 31905, 45621, 33987, 47500));//prj4
        cost_price[8].addAll(Arrays.asList(51543, 49810, 44237, 58401, 46695, 58931));//prj4
        sales_price[0].addAll(Arrays.asList(25476, 31680, 28198, 31096, 27750, 13320));//prj4
        sales_price[1].addAll(Arrays.asList(20276, 29900, 59120, 24684, 26350, 37076));//prj4
        sales_price[2].addAll(Arrays.asList(48854, 57944, 31814, 54652, 74356, 48938));//prj4
        sales_price[3].addAll(Arrays.asList(27962, 31112, 32842, 16578, 16834, 23332));//prj4
        sales_price[4].addAll(Arrays.asList(21200, 46570, 76990, 57018, 38556, 52062));//prj4
        sales_price[5].addAll(Arrays.asList(40340, 41238, 61056, 73946, 49820, 65446));//prj4
        sales_price[6].addAll(Arrays.asList(15624, 11356, 17874, 19086, 13578,17040));//prj4
        sales_price[7].addAll(Arrays.asList(77508, 85356, 63810, 91242, 67974,95000));//prj4
        sales_price[8].addAll(Arrays.asList(103086, 99620, 88474, 116802, 93390, 117862));//prj4
        Operation2.budget =500000;//Initial Day 1 beginning budget
    }
    //when the count of a vehicle type is less than 6, new vehicle is purchased  //prj4
    public void addVehicle(FNCDdata2 fnc) throws IOException {
        for (int i = 0; i < 9; i++) {//prj4
            while (vehicle[i].size() < 6) {//prj4
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
                    Car2 c = new Car2();
                    c.buyVehicle(fnc);
                } else if(i == 1){
                    Pickup2 p = new Pickup2();
                    p.buyVehicle(fnc);
                } else if(i == 2){
                    PerformanceCar2 pc = new PerformanceCar2();
                    pc.buyVehicle(fnc);
                } else if (i == 3) {
                    Motorcycles2 mc = new Motorcycles2();
                    mc.buyVehicle(fnc);
                } else if (i == 4) {
                    MonsterTrucks2 mt = new MonsterTrucks2();
                    mt.buyVehicle(fnc);
                } else if (i == 5) {
                    ElectricCar2 ec = new ElectricCar2();
                    ec.buyVehicle(fnc);
                } else if (i == 6) {//prj
                    BudgetCar2 bc = new BudgetCar2();
                    bc.buyVehicle(fnc);
                } else if (i == 7) {
                    LuxuryCar2 lc = new LuxuryCar2();
                    lc.buyVehicle(fnc);
                }
                else{
                    SuperCar2 sc = new SuperCar2();
                    sc.buyVehicle(fnc);
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
        for(int i=0; i<9;i++) {//prj4
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
            for(int i=0; i<9;i++) {//prj4
                for (int j = 0; j < vehicle[i].size(); j++) {
                    if (vehicle[i].get(j).equals(req_vehicle)) {
                        sales_price[i].set(j, (int) (sales_price[i].get(j) * 1.5));
                        break;
                    }
                }
            }
        } else {
            for(int i=0; i<9;i++) {//prj4
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
        for (int i=0;i<9;i++){
            System.out.println(carType[i]+"s: "+vehicle[i]);
        }
        System.out.println("\nCondition of Vehicles:");
        for (int i=0;i<3;i++){
            System.out.println(carCondition[i]+"s: "+condition[i]);
        }
        for (int i=0;i<3;i++){
            System.out.println(carCleanliness[i]+"s: "+cleanliness[i]);
        }
        System.out.println("\nStatus of Vehicles:");
        for (int i=0;i<9;i++){
            System.out.println(carType[i]+"s: "+status[i]);
        }
        System.out.println("\nCost price of Vehicles:");
        for (int i=0;i<9;i++){
            System.out.println(carType[i]+"s: "+cost_price[i]);
        }
        System.out.println("\nSales price of Vehicles:");
        for (int i=0;i<9;i++){
            System.out.println(carType[i]+"s: "+sales_price[i]);
        }
        System.out.println("\nList of Sold Vehicles:");
        for (int i=0;i<9;i++){
            System.out.println(carType[i]+"s: "+soldVehicles[i]);
        }
        System.out.println("Remaining Budget of South FNCD: "+Operation2.budget);
    }
}

//For 2nd FNCD
class Car2 extends Vehicle2{
    // adding cost and sales price to the car
    public void buyVehicle(FNCDdata2 fnc) throws IOException {
        int cp1 = rand.nextInt(10000, 20001);
        cost_price[0].add(cp1);
        sales_price[0].add(cp1 * 2);
        if(getCondition(vehicle[0].get(vehicle[0].size()-1))==0){//If vehicle is initially broken, reducing its cost price by 50%.
            cost_price[0].set((vehicle[0].size()-1), (int)(cost_price[0].get(vehicle[0].size()-1)*0.5));
        } else if (getCondition(vehicle[0].get(vehicle[0].size()-1))==1){//If vehicle is initially used, reducing its cost price by 20%.
            cost_price[0].set((vehicle[0].size()-1), (int)(cost_price[0].get(vehicle[0].size()-1)*0.8));
        }
        Operation2.addBudget(0,fnc);
        Operation2.budget = Operation2.budget - cost_price[0].get(vehicle[0].size()-1);
        System.out.println("Bought "+carType[0]+" "+vehicle[0].get(vehicle[0].size()-1)+" for $"+cost_price[0].get(cost_price[0].size()-1));
        fnc.dayEnd(0,vehicle[0].size()-1,3);//obs
    }

}
//For 2nd FNCD
class Pickup2 extends Vehicle2{
    // adding cost and sales price to the pickup vehicle
    public void buyVehicle(FNCDdata2 fnc) throws IOException {
        int cp1 = rand.nextInt(10000, 40001);
        cost_price[1].add(cp1);
        sales_price[1].add(cp1 * 2);
        if (getCondition(vehicle[1].get(vehicle[1].size()-1)) == 0) {//If vehicle is initially broken, reducing its cost price by 50%.
            cost_price[1].set((vehicle[1].size()-1), (int) (cost_price[1].get(vehicle[1].size()-1) * 0.5));
        } else if (getCondition(vehicle[1].get(vehicle[1].size()-1)) == 1) {//If vehicle is initially used, reducing its cost price by 20%.
            cost_price[1].set((vehicle[1].size()-1), (int) (cost_price[1].get(vehicle[1].size()-1) * 0.8));
        }
        Operation2.addBudget(1,fnc);
        Operation2.budget = Operation2.budget - cost_price[1].get(vehicle[1].size()-1);
        System.out.println("Bought "+carType[1]+" "+vehicle[1].get(vehicle[1].size()-1)+" for $"+cost_price[1].get(cost_price[1].size()-1));
        fnc.dayEnd(1,vehicle[1].size()-1,3);//obs
    }
}
//For 2nd FNCD
class PerformanceCar2 extends Vehicle2{
    // adding cost and sales price to the performance car
    public void buyVehicle(FNCDdata2 fnc) throws IOException {
        int cp1 = rand.nextInt(20000, 40001);
        cost_price[2].add(cp1);
        sales_price[2].add(cp1 * 2);
        if(getCondition(vehicle[2].get(vehicle[2].size()-1))==0){//If vehicle is initially broken, reducing its cost price by 50%.
            cost_price[2].set((vehicle[2].size()-1), (int)(cost_price[2].get(vehicle[2].size()-1)*0.5));
        } else if (getCondition(vehicle[2].get(vehicle[2].size()-1))==1){//If vehicle is initially used, reducing its cost price by 20%.
            cost_price[2].set((vehicle[2].size()-1), (int)(cost_price[2].get(vehicle[2].size()-1)*0.8));
        }
        Operation2.addBudget(2,fnc);
        Operation2.budget = Operation2.budget - cost_price[2].get(vehicle[2].size()-1);
        System.out.println("Bought "+carType[2]+" "+vehicle[2].get(vehicle[2].size()-1)+" for $"+cost_price[2].get(cost_price[2].size()-1));
        fnc.dayEnd(2,vehicle[2].size()-1,3);//obs
    }
}

//For 2nd FNCD
class Motorcycles2 extends Vehicle2{

    double engine_size_gen = 0.0;
    public double setEngineSize(){
        return rand.nextGaussian()*300+700;
    }
    public void buyVehicle(FNCDdata2 fnc) throws IOException {
        int cp1 = rand.nextInt(10000, 20001);
        cost_price[3].add(cp1);
        sales_price[3].add(cp1 * 2);
        // Setting the Engine size for Motorcycles using truncated Normal Distribution with mean 700 and std dev 300.
        while (engine_size.size() < 6) {//prj4
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
        Operation2.addBudget(3,fnc);
        Operation2.budget = Operation2.budget - cost_price[3].get(vehicle[3].size()-1);
        System.out.println("Bought "+carType[3]+" "+vehicle[3].get(vehicle[3].size()-1)+" for $"+cost_price[3].get(cost_price[3].size()-1));
        fnc.dayEnd(3,vehicle[3].size()-1,3);//obs
    }
}

//For 2nd FNCD
class MonsterTrucks2 extends Vehicle2{
    public void buyVehicle(FNCDdata2 fnc) throws IOException {
        int cp1 = rand.nextInt(20000, 40001);
        cost_price[4].add(cp1);
        sales_price[4].add(cp1 * 2);
        if(getCondition(vehicle[4].get(vehicle[4].size()-1))==0){//If vehicle is initially broken, reducing its cost price by 50%.
            cost_price[4].set((vehicle[4].size()-1), (int)(cost_price[4].get(vehicle[4].size()-1)*0.5));
        } else if (getCondition(vehicle[4].get(vehicle[4].size()-1))==1){//If vehicle is initially used, reducing its cost price by 20%.
            cost_price[4].set((vehicle[4].size()-1), (int)(cost_price[4].get(vehicle[4].size()-1)*0.8));
        }
        Operation2.addBudget(4,fnc);
        Operation2.budget = Operation2.budget - cost_price[4].get(vehicle[4].size()-1);
        System.out.println("Bought "+carType[4]+" "+vehicle[4].get(vehicle[4].size()-1)+" for $"+cost_price[4].get(cost_price[4].size()-1));
        fnc.dayEnd(4,vehicle[4].size()-1,3);//obs
    }
}

//For 2nd FNCD
class ElectricCar2 extends Vehicle2{
    public void buyVehicle(FNCDdata2 fnc) throws IOException {
        int cp1 = rand.nextInt(30000, 50001);
        cost_price[5].add(cp1);
        sales_price[5].add(cp1 * 2);

        // Assigning a range for the Electric Cars based on their condition
        int range_select = rand.nextInt(60, 401);
        while (range.size() < 6) {//prj4
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
        Operation2.addBudget(5,fnc);
        Operation2.budget = Operation2.budget - cost_price[5].get(vehicle[5].size()-1);
        System.out.println("Bought "+carType[5]+" "+vehicle[5].get(vehicle[5].size()-1)+" for $"+cost_price[5].get(cost_price[5].size()-1));
        fnc.dayEnd(5,vehicle[5].size()-1,3);//obs
    }
}

//For 2nd FNCD
class BudgetCar2 extends Vehicle2{//prj4
    // adding cost and sales price to the car
    public void buyVehicle(FNCDdata2 fnc) throws IOException {
        int cp1 = rand.nextInt(5000, 10001);
        cost_price[6].add(cp1);
        sales_price[6].add(cp1 * 2);
        if(getCondition(vehicle[6].get(vehicle[6].size()-1))==0){//If vehicle is initially broken, reducing its cost price by 50%.
            cost_price[6].set((vehicle[6].size()-1), (int)(cost_price[6].get(vehicle[6].size()-1)*0.5));
        } else if (getCondition(vehicle[6].get(vehicle[6].size()-1))==1){//If vehicle is initially used, reducing its cost price by 20%.
            cost_price[6].set((vehicle[6].size()-1), (int)(cost_price[6].get(vehicle[6].size()-1)*0.8));
        }
        Operation2.addBudget(0,fnc);
        Operation2.budget = Operation2.budget - cost_price[6].get(vehicle[6].size()-1);
        System.out.println("Bought "+carType[6]+" "+vehicle[6].get(vehicle[6].size()-1)+" for $"+cost_price[6].get(cost_price[6].size()-1));
        fnc.dayEnd(0,vehicle[6].size()-1,3);//obs
    }

}

//For 2nd FNCD
class LuxuryCar2 extends Vehicle2{//prj4
    // adding cost and sales price to the car
    public void buyVehicle(FNCDdata2 fnc) throws IOException {
        int cp1 = rand.nextInt(30000, 50001);
        cost_price[7].add(cp1);
        sales_price[7].add(cp1 * 2);
        if(getCondition(vehicle[7].get(vehicle[7].size()-1))==0){//If vehicle is initially broken, reducing its cost price by 50%.
            cost_price[7].set((vehicle[7].size()-1), (int)(cost_price[7].get(vehicle[7].size()-1)*0.5));
        } else if (getCondition(vehicle[7].get(vehicle[7].size()-1))==1){//If vehicle is initially used, reducing its cost price by 20%.
            cost_price[7].set((vehicle[7].size()-1), (int)(cost_price[7].get(vehicle[7].size()-1)*0.8));
        }
        Operation2.addBudget(0,fnc);
        Operation2.budget = Operation2.budget - cost_price[7].get(vehicle[7].size()-1);
        System.out.println("Bought "+carType[7]+" "+vehicle[7].get(vehicle[7].size()-1)+" for $"+cost_price[7].get(cost_price[7].size()-1));
        fnc.dayEnd(0,vehicle[7].size()-1,3);//obs
    }

}

//For 2nd FNCD
class SuperCar2 extends Vehicle2{//prj4
    // adding cost and sales price to the car
    public void buyVehicle(FNCDdata2 fnc) throws IOException {
        int cp1 = rand.nextInt(40000, 60001);
        cost_price[8].add(cp1);
        sales_price[8].add(cp1 * 2);
        if(getCondition(vehicle[8].get(vehicle[8].size()-1))==0){//If vehicle is initially broken, reducing its cost price by 50%.
            cost_price[8].set((vehicle[8].size()-1), (int)(cost_price[8].get(vehicle[8].size()-1)*0.5));
        } else if (getCondition(vehicle[8].get(vehicle[8].size()-1))==1){//If vehicle is initially used, reducing its cost price by 20%.
            cost_price[8].set((vehicle[8].size()-1), (int)(cost_price[8].get(vehicle[8].size()-1)*0.8));
        }
        Operation2.addBudget(0,fnc);
        Operation2.budget = Operation2.budget - cost_price[8].get(vehicle[8].size()-1);
        System.out.println("Bought "+carType[8]+" "+vehicle[8].get(vehicle[8].size()-1)+" for $"+cost_price[8].get(cost_price[8].size()-1));
        fnc.dayEnd(0,vehicle[8].size()-1,3);//obs
    }

}

//Decorator Pattern
//For 2nd FNCD
abstract class Addon_purchaser2 extends Vehicle2{
    //    Vehicle vehicle;
    int i, j;
    abstract void addonPrice(FNCDdata2 fnc) throws IOException;
}

//For 2nd FNCD
class ExtendedWarranty2 extends Addon_purchaser2{
    public ExtendedWarranty2(int i,int j) {
        this.i = i;
        this.j = j;
        addonPrice();
    }
    public ExtendedWarranty2(int i,int j,FNCDdata2 fnc) throws IOException {
        this.i = i;
        this.j = j;
        addonPrice(fnc);
    }
    public void addonPrice() {
        double prob = rand.nextDouble();
        if(prob < 0.25){
            System.out.println("Purchased Add-on Extended Warranty");
            System.out.print("Sales Price of the "+carType[i]+" "+vehicle[i].get(j)+" increased from $"+sales_price[i].get(j));
            sales_price[i].set(j, (int)(sales_price[i].get(j)*1.2));
            System.out.println(" to $"+sales_price[i].get(j));
            commandReceiver.sales_price = sales_price[i].get(j);
        }
    }
    public void addonPrice(FNCDdata2 fnc) throws IOException {
        double prob = rand.nextDouble();
        if(prob < 0.25){
            System.out.print("Sales Price of the "+carType[i]+" "+vehicle[i].get(j)+" increased from $"+sales_price[i].get(j));
            fnc.sellOutcome(i,j,0,0);//obs
            sales_price[i].set(j, (int)(sales_price[i].get(j)*1.2));
            System.out.println(" to $"+sales_price[i].get(j)+ " after adding on Extended Warranty");
            fnc.sellOutcome(i,j,1,0);//obs
        }
    }
}

//For 2nd FNCD
class Undercoating2 extends Addon_purchaser2{
    public Undercoating2(int i,int j) {
        this.i = i;
        this.j = j;
        addonPrice();
    }
    public Undercoating2(int i,int j,FNCDdata2 fnc) throws IOException {
        this.i = i;
        this.j = j;
        addonPrice(fnc);
    }
    public void addonPrice() {
        double prob = rand.nextDouble();
        if(prob < 0.10){
            System.out.println("Purchased Add-on Undercoating");
            System.out.print("Sales Price of the "+carType[i]+" "+vehicle[i].get(j)+" increased from $"+sales_price[i].get(j));
            sales_price[i].set(j, (int)(sales_price[i].get(j)*1.05));
            System.out.println(" to $"+sales_price[i].get(j));
            commandReceiver.sales_price = sales_price[i].get(j);
        }
    }
    public void addonPrice(FNCDdata2 fnc) throws IOException {
        double prob = rand.nextDouble();
        if(prob < 0.10){
            System.out.print("Sales Price of the "+carType[i]+" "+vehicle[i].get(j)+" increased from $"+sales_price[i].get(j));
            fnc.sellOutcome(i,j,0,1);//obs
            sales_price[i].set(j, (int)(sales_price[i].get(j)*1.05));
            System.out.println(" to $"+sales_price[i].get(j)+ " after adding on Undercoating");
            fnc.sellOutcome(i,j,1,1);//obs
        }
    }
}

//For 2nd FNCD
class RoadRescueCoverage2 extends Addon_purchaser2{
    public RoadRescueCoverage2(int i,int j) {
        this.i = i;
        this.j = j;
        addonPrice();
    }
    public RoadRescueCoverage2(int i,int j,FNCDdata2 fnc) throws IOException {
        this.i = i;
        this.j = j;
        addonPrice(fnc);
    }
    public void addonPrice() {
        double prob = rand.nextDouble();
        if(prob <0.05){
            System.out.println("Purchased Add-on Road Rescue Coverage");
            System.out.print("Sales Price of the "+carType[i]+" "+vehicle[i].get(j)+" increased from $"+sales_price[i].get(j));
            sales_price[i].set(j, (int)(sales_price[i].get(j)*1.02));
            System.out.println(" to $"+sales_price[i].get(j));
            commandReceiver.sales_price = sales_price[i].get(j);
        }
    }
    public void addonPrice(FNCDdata2 fnc) throws IOException {
        double prob = rand.nextDouble();
        if(prob <0.05){
            System.out.print("Sales Price of the "+carType[i]+" "+vehicle[i].get(j)+" increased from $"+sales_price[i].get(j));
            fnc.sellOutcome(i,j,0,2);//obs
            sales_price[i].set(j, (int)(sales_price[i].get(j)*1.02));
            System.out.println(" to $"+sales_price[i].get(j)+ " after adding on Road Rescue Coverage");
            fnc.sellOutcome(i,j,1,2);//obs
        }
    }
}

//For 2nd FNCD
class SatelliteRadio2 extends Addon_purchaser2{
    public SatelliteRadio2(int i,int j) {
        this.i = i;
        this.j = j;
        addonPrice();
    }
    public SatelliteRadio2(int i,int j,FNCDdata2 fnc) throws IOException {
        this.i = i;
        this.j = j;
        addonPrice(fnc);
    }
    public void addonPrice() {
        double prob = rand.nextDouble();
        if(prob < 0.40){
            System.out.println("Purchased Add-on Satellite Radio");
            System.out.print("Sales Price of the "+carType[i]+" "+vehicle[i].get(j)+" increased from $"+sales_price[i].get(j));
            sales_price[i].set(j, (int)(sales_price[i].get(j)*1.05));
            System.out.println(" to $"+sales_price[i].get(j));
            commandReceiver.sales_price = sales_price[i].get(j);
        }
    }
    public void addonPrice(FNCDdata2 fnc) throws IOException {
        double prob = rand.nextDouble();
        if(prob < 0.40){
            System.out.print("Sales Price of the "+carType[i]+" "+vehicle[i].get(j)+" increased from $"+sales_price[i].get(j));
            fnc.sellOutcome(i,j,0,3);//obs
            sales_price[i].set(j, (int)(sales_price[i].get(j)*1.05));
            System.out.println(" to $"+sales_price[i].get(j)+ " after adding on Satellite Radio");
            fnc.sellOutcome(i,j,1,3);//obs
        }
    }
}

//For 2nd FNCD
class Buyer2 extends Staff2{
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
        if ((Operation2.day_count<=4)||(Operation2.day_count>=7 && Operation2.day_count<=11)||(Operation2.day_count>=14 && Operation2.day_count<=18)
                ||(Operation2.day_count>=21 && Operation2.day_count<=25)||(Operation2.day_count>=28 && Operation2.day_count<=30)){
            buyer_no = rand.nextInt(0, 6);
            for (int j = 0; j < buyer_no; j++) {
                addBuyerType(j);
            }
        } else if (Operation2.day_count==5 ||Operation2.day_count==6 ||Operation2.day_count==12||Operation2.day_count==13||
                Operation2.day_count==19||Operation2.day_count==20||Operation2.day_count==26||Operation2.day_count==27) {
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
        int vehicleChoice = rand.nextInt(9);//prj4
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
//Obs start
interface Publisher2{
    public void registerSubscriber(Subscriber2 s);
    public void removeSubscriber(Subscriber2 s);
    public void notifySubscriberAct(Subscriber2 s,int i,int j) throws IOException;
    //example of Polymorphism (method overloading)
    public void notifySubscriberWash(Subscriber2 s,int i,int j,int k,Vehicle2 obj,String vehicle,int l,int m,int n,int bonus) throws IOException;
    //example of Polymorphism (method overloading)
    public void notifySubscriberWash(Subscriber2 s,String vehicle,int i,int j) throws IOException;
    public void notifySubscriberRepair(Subscriber2 s,int i,int j, Vehicle2 obj,String vehicle,int k,int bonus) throws IOException;
    public void notifySubscriberRepair(Subscriber2 s,Vehicle2 obj,String vehicle) throws IOException;
    public void notifySubscriberSell(Subscriber2 s,int i,String person,Vehicle2 obj,String vehicle,int buyer_choice,String buyer,int vehicle_choice,int bonus) throws IOException;
    public void notifySubscriberSell(Subscriber2 s,int i,int j,int k,int l) throws IOException;
    public void notifySubscriberRace(Subscriber2 s,int j,int i,int choice,int bonus,int k) throws IOException;
    public void notifySubscriberRace(Subscriber2 s,int j,int i) throws IOException;
    public void notifySubscriberEnd(Subscriber2 s,int i,int j,int k) throws IOException;
    public void notifySubscriberTracker(Subscriber2 s,int i,int j) throws IOException;
}
interface Subscriber2{
    public void update(Subscriber2 s,int i,int j) throws IOException;
    public void updateAct(Subscriber2 s,int i,int j) throws IOException;
    public void updateWash(int i, int j, int k,Vehicle2 obj, String vehicle, int l, int m, int n, int bonus) throws IOException;
    public void updateWash(String vehicle,int i,int j) throws IOException;
    public void updateRepair(Subscriber2 s,int i,int j,Vehicle2 obj,String vehicle,int k,int bonus) throws IOException;
    public void updateRepair(Subscriber2 s,Vehicle2 obj,String vehicle) throws IOException;
    public void updateSell(Subscriber2 s,int i,String person,Vehicle2 obj,String vehicle,int buyer_choice,String buyer,int vehicle_choice,int bonus_val) throws IOException;
    public void updateSell(Subscriber2 s,int i,int j,int k,int l) throws IOException;
    public void updateRace(Subscriber2 s,int j,int i,int choice,int bonus,int k) throws IOException;
    public void updateRace(Subscriber2 s,int j,int i) throws IOException;
    public void updateEnd(Subscriber2 s,int i,int j,int k) throws IOException;
}
//For 2nd FNCD
class FNCDdata2 implements Publisher2{
    Subscriber2 s;
    static ArrayList<Subscriber2> subscribers;
    public FNCDdata2(){
        subscribers = new ArrayList<Subscriber2>();
    }
    public void registerSubscriber(Subscriber2 s){
        subscribers.add(s);
    }
    public void removeSubscriber(Subscriber2 s){
        int i = subscribers.indexOf(s);
        if (i >= 0) {
            subscribers.remove(i);
        }
    }
    public void dayAct(int i,int j) throws IOException {
        s= subscribers.get(1);
        notifySubscriberAct(s,i,j);         //To add daily activities e.g. washing, repairing.
    }
    public void notifySubscriberAct(Subscriber2 s,int i,int j) throws IOException {
        s.updateAct(s,i,j);         //Update the subscriber with activities
    }
    public void washOutcome(int i,int j,int k,Vehicle2 obj,String vehicle,int l,int m,int n,int bonus) throws IOException {
        s= subscribers.get(1);
        notifySubscriberWash(s,i,j,k,obj,vehicle,l,m,n,bonus);      //To add details of washing method, intern bonus, cleanliness
    }
    public void washOutcome(String vehicle,int i,int j) throws IOException {
        s= subscribers.get(1);
        notifySubscriberWash(s,vehicle,i,j);      //To add change in the vehicle condition after washing
    }
    public void notifySubscriberWash(Subscriber2 s,int i,int j,int k,Vehicle2 obj,String vehicle,int l,int m,int n,int bonus) throws IOException {
        s.updateWash(i,j,k,obj,vehicle,l,m,n,bonus);        //Update the subscriber with wash activity
    }
    public void notifySubscriberWash(Subscriber2 s,String vehicle,int i,int j) throws IOException {
        s.updateWash(vehicle,i,j);        //Update the subscriber with wash activity
    }
    public void repairOutcome(int i,int j, Vehicle2 obj,String vehicle,int k,int bonus) throws IOException {
        s= subscribers.get(1);
        notifySubscriberRepair(s,i,j,obj,vehicle,k,bonus);      //To add details of repairing, condition, mechanic bonus
    }
    public void repairOutcome(Vehicle2 obj,String vehicle) throws IOException {
        s= subscribers.get(1);
        notifySubscriberRepair(s,obj,vehicle);      //To add change in vehicle cleanliness after repairing
    }
    public void notifySubscriberRepair(Subscriber2 s,int i,int j,Vehicle2 obj,String vehicle,int k,int bonus) throws IOException {
        s.updateRepair(s,i,j,obj,vehicle,k,bonus);          //Update the subscriber with repair activity
    }
    public void notifySubscriberRepair(Subscriber2 s,Vehicle2 obj,String vehicle) throws IOException {
        s.updateRepair(s,obj,vehicle);          //Update the subscriber with repair activity
    }
    public void sellOutcome(int i,String person,Vehicle2 obj,String vehicle,int buyer_choice,String buyer,int vehicle_choice,int bonus) throws IOException {
        s= subscribers.get(1);
        notifySubscriberSell(s,2,person,obj,vehicle,buyer_choice,buyer,vehicle_choice,bonus);   //To add details of selling, salesperson bonus
    }
    public void sellOutcome(int i,int j,int k,int l) throws IOException {
        s= subscribers.get(1);
        notifySubscriberSell(s,i,j,k,l);        //To add change in salesprice of vehicle after addons
    }
    public void notifySubscriberSell(Subscriber2 s,int i,String person,Vehicle2 obj,String vehicle,int buyer_choice,String buyer,int vehicle_choice,int bonus) throws IOException {
        s.updateSell(s,2,person,obj,vehicle,buyer_choice,buyer,vehicle_choice,bonus);   //Update the subscriber with sale activity
    }
    public void notifySubscriberSell(Subscriber2 s,int i,int j,int k,int l) throws IOException {
        s.updateSell(s,i,j,k,l);        //Update the subscriber with sale activity
    }
    public void raceOutcome(int j,int i,int choice,int bonus,int k) throws IOException {
        s= subscribers.get(1);
        notifySubscriberRace(s,j,i,choice,bonus,k);     //To add details of racing events, driver bonus
    }
    public void raceOutcome(int j,int i) throws IOException {
        s= subscribers.get(1);
        notifySubscriberRace(s,j,i);        //To add change in vehicle condition and injury of drivers
    }
    public void notifySubscriberRace(Subscriber2 s,int j,int i,int choice,int bonus,int k) throws IOException {
        s.updateRace(s,j,i,choice,bonus,k);     //Update the subscriber with race activity
    }
    public void notifySubscriberRace(Subscriber2 s,int j,int i) throws IOException {
        s.updateRace(s,j,i);        //Update the subscriber with race activity
    }
    public void dayEnd(int i,int j,int k) throws IOException {
        s= subscribers.get(1);
        notifySubscriberEnd(s,i,j,k);       //To add details of staff quitting, hiring new staff, buying new vehicles
    }
    public void notifySubscriberEnd(Subscriber2 s,int i,int j,int k) throws IOException {
        s.updateEnd(s,i,j,k);       //Update the subscriber with the day end activities
    }
    public void trackerOutcome(int i,int j) throws IOException {
        s= subscribers.get(0);
        notifySubscriberTracker(s,i,j);     //To add total money earned by staff and FNCD
    }
    public void notifySubscriberTracker(Subscriber2 s,int i,int j) throws IOException {
        s.update(s,i,j);        //Update the subscriber with the tracker activity
    }
}
//Obs end
//For 2nd FNCD
class Operation2 extends Staff2{
    static int budget = 500000;
    static int total_sales = 0;
    static int day_count;
    static int added_money=0;
    static int Staff_money=0;
    static int FNCD_money=0;
    static String file_name=null;
    //printing the summary of staff
    static void addBudget(int i,int j,FNCDdata2 fnc) throws IOException {
        if(budget-salary[i].get(j)<=0){       //If we run out of budget, $250000 is added to the budget
            budget=budget+250000;
            System.out.println("Added $250000 in South FNCD budget due to low budget while processing Salary for Staff");
            added_money=added_money+250000;
            fnc.dayEnd(0,0,4);//obs
        }
    }
    static void addBudget(int i,FNCDdata2 fnc) throws IOException {
        if(budget-Vehicle2.cost_price[i].get(Vehicle2.vehicle[i].size()-1)<=0){
            budget=budget+250000;       //If we run out of budget, $250000 is added to the budget
            System.out.println("Added $250000 in South FNCD budget due to low budget while buying required Vehicles");
            added_money=added_money+250000;
            fnc.dayEnd(0,0,5);//obs
        }
    }
    public void Print(){
        System.out.println("\n************************************************************SUMMARY OF SOUTH FNCD************************************************************");
        System.out.println("List of Staff:");
        for (int i=0;i<4;i++){
            System.out.println(staffType[i]+"s: "+staff[i]);
        }
        System.out.println("\nStatus of Staff:");
        for (int i=0;i<4;i++){
            System.out.println(staffType[i]+"s: "+staff_status[i]);
        }
        System.out.println("\nTotal Days worked by Staff:");
        for (int i=0;i<4;i++){
            System.out.println(staffType[i]+"s: "+days_worked[i]);
        }
        System.out.println("\nTotal Bonus of Staff:");
        for (int i=0;i<4;i++){
            System.out.println(staffType[i]+"s: "+total_bonus[i]);
        }
        System.out.println("\nTotal Normal Pay of Staff:");
        for (int i=0;i<4;i++){
            System.out.println(staffType[i]+"s: "+total_normal_pay[i]);
        }
        System.out.println("\nList of Departed Staff:");
        for (int i=0;i<4;i++){
            System.out.println(staffType[i]+"s: "+dep_staff[i]);
        }
        System.out.println("\nTotal Days worked by Departed Staff:");
        for (int i=0;i<4;i++){
            System.out.println(staffType[i]+"s: "+dep_days_worked[i]);
        }
        System.out.println("\nTotal Bonus of Departed Staff:");
        for (int i=0;i<4;i++){
            System.out.println(staffType[i]+"s: "+dep_total_bonus[i]);
        }
        System.out.println("\nTotal Normal Pay of Departed Staff:");
        for (int i=0;i<4;i++){
            System.out.println(staffType[i]+"s: "+dep_total_normal_pay[i]);
        }
    }

    public static void allOperations() throws IOException {
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
        FNCDdata fnc1 = new FNCDdata();//obs
        Staff2 staff2 = new Staff2();
        staff2.init();
        staff2.add_names();
        staff2.addStaff();
        Intern2 int2 = new Intern2();
        Mechanic2 mec2 = new Mechanic2();
        Salesperson2 sal2 = new Salesperson2();
        Buyer2 buy2 = new Buyer2();
        Driver2 dri2 = new Driver2();
        Vehicle2 vel2 = new Vehicle2();
        vel2.init2();
        vel2.add_vehicle_names();
        vel2.add_MonsterTruck_names();
        vel2.addVehicles();
        Operation2 op2 = new Operation2();
        FNCDdata2 fnc2 = new FNCDdata2();//obs
        Tracker tra2 = Tracker.getInstance2();//obs//prj4
        Tester t = new Tester();//prj4
        for (day_count=1;day_count<32;day_count++){
            Operation.day_count = day_count;
            Logger log1 = Logger.getInstance(fnc1);//Obs//prj4
            Operation.outputLogger();
            Logger log2 = Logger.getInstance2(fnc2);//Obs//prj4
            buy1.init3();
            buy2.init3();
            System.out.println("\n***Day "+day_count+"***");//Display Day Number
            System.out.println("\nCurrent Budget of North FNCD: "+Operation.budget);
            System.out.println("Current Budget of South FNCD: "+budget);
            fnc1.dayAct(0,Operation.budget);//obs
            fnc2.dayAct(0,budget);//obs
            buy1.addBuyer();
            buy2.addBuyer();
            int1.setWashBehavior(fnc1);
            int2.setWashBehavior(fnc2);
            mec1.repair(fnc1);
            mec2.repair(fnc2);
            if (day_count ==31){ //if day is 31 then use command patter for user inputs to buy vehicle
                commandReceiver receiver = new commandReceiver();
                Command command = new concreteCommand(receiver);
                commandInvoker invoker = new commandInvoker();
                invoker.setCommand(command);
                invoker.executeCommand();
                if(commandReceiver.FNCD_choice==0 && commandReceiver.user_decision == 0) {
                    sal1.sale2(fnc1);
                } else if (commandReceiver.FNCD_choice==1 && commandReceiver.user_decision == 0) {
                    sal2.sale2(fnc2);
                }
            }
            else{
                sal1.sale(fnc1);
                sal2.sale(fnc2);
            }
            dri1.getRaceVehicles(fnc1);
            dri1.race(fnc1);
            dri2.getRaceVehicles(fnc2);
            dri2.race(fnc2);
            System.out.println("\nDay End in North FNCD");
            fnc1.dayAct(5,0);//obs
            int1.getSalary(fnc1);
            int1.getTotalDays();
            mec1.getSalary(fnc1);
            mec1.getTotalDays();
            sal1.getSalary(fnc1);
            sal1.getTotalDays();
            dri1.getSalary(fnc1);
            dri1.getTotalDays();
            staff1.updateDepartedStaff(fnc1);
            int1.dropIntern();
            int1.addIntern(fnc1);
            dri1.dropDriver();
            dri1.addDriver(fnc1);
            vel1.addVehicle(fnc1);
            System.out.println("\nDay End in South FNCD");
            fnc2.dayAct(5,0);//obs
            int2.getSalary(fnc2);
            int2.getTotalDays();
            mec2.getSalary(fnc2);
            mec2.getTotalDays();
            sal2.getSalary(fnc2);
            sal2.getTotalDays();
            dri2.getSalary(fnc2);
            dri2.getTotalDays();
            staff2.updateDepartedStaff(fnc2);
            int2.dropIntern();
            int2.addIntern(fnc2);
            dri2.dropDriver();
            dri2.addDriver(fnc2);
            vel2.addVehicle(fnc2);
            System.out.println("\nTotal sales for the day in North FNCD: "+Operation.total_sales);
            System.out.println("Total sales for the day in South FNCD: "+total_sales);
            fnc1.dayAct(6,Operation.total_sales);//obs
            fnc2.dayAct(6,total_sales);//obs
            System.out.println("Remaining Budget of North FNCD: "+Operation.budget);
            System.out.println("Remaining Budget of South FNCD: "+budget+"\n");
            fnc1.trackerOutcome(day_count,0);//obs
            fnc1.trackerOutcome(day_count,1);
            fnc2.trackerOutcome(day_count,1);
            Operation.writer1.close();
        }
        op1.Print();
        vel1.Print2();
        op2.Print();
        vel2.Print2();

        //junit testing
        t.checkCars();//prj4
        t.checkPickups();
        t.checkPerformanceCars();
        t.checkMotorcyclesCars();
        t.checkMonsterTrucks();
        t.checkElectricCars();
        t.checkBudgetCars();
        t.checkLuxuryCars();
        t.checkSuperCars();
        t.checkInterns();
        t.checkMechanics();
        t.checkSalespersons();
        t.checkDrivers();
        t.checkBudgetFNCDNorth();
        t.checkBudgetFNCDSouth();
    }
    //main method
    public static void main(String[] args) throws IOException {
        allOperations();
    }
}

class Tester{
    Tester(){}
    @BeforeEach
    void runInitiatingmethods() {
        Operation2 o2 = new Operation2();
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
        FNCDdata fnc1 = new FNCDdata();//obs
        Staff2 staff2 = new Staff2();
        staff2.init();
        staff2.add_names();
        staff2.addStaff();
        Intern2 int2 = new Intern2();
        Mechanic2 mec2 = new Mechanic2();
        Salesperson2 sal2 = new Salesperson2();
        Buyer2 buy2 = new Buyer2();
        Driver2 dri2 = new Driver2();
        Vehicle2 vel2 = new Vehicle2();
        vel2.init2();
        vel2.add_vehicle_names();
        vel2.add_MonsterTruck_names();
        vel2.addVehicles();
        Operation2 op2 = new Operation2();
        FNCDdata2 fnc2 = new FNCDdata2();//obs
        Tracker tra2 = Tracker.getInstance2();//obs//prj4
    }
    @Test
    void checkCars() {
        // Testing the starting statements to verify starting objects for Vehicle class of North FNCD
        assertEquals(6, Vehicle.vehicle[0].size());

        // Testing the starting statements to verify starting objects for Vehicle class of South FNCD
        assertEquals(6, Vehicle2.vehicle[0].size());
    }
    @Test
    void checkPickups() {
        // Testing the starting statements to verify starting objects for Vehicle class of North FNCD
        assertEquals(6, Vehicle.vehicle[1].size());

        // Testing the starting statements to verify starting objects for Vehicle class of South FNCD
        assertEquals(6, Vehicle2.vehicle[1].size());
    }
    @Test
    void checkPerformanceCars() {
        // Testing the starting statements to verify starting objects for Vehicle class of North FNCD
        assertEquals(6, Vehicle.vehicle[2].size());

        // Testing the starting statements to verify starting objects for Vehicle class of South FNCD
        assertEquals(6, Vehicle2.vehicle[2].size());
    }
    @Test
    void checkMotorcyclesCars() {
        // Testing the starting statements to verify starting objects for Vehicle class of North FNCD
        assertEquals(6, Vehicle.vehicle[3].size());

        // Testing the starting statements to verify starting objects for Vehicle class of South FNCD
        assertEquals(6, Vehicle2.vehicle[3].size());
    }
    @Test
    void checkMonsterTrucks() {
        // Testing the starting statements to verify starting objects for Vehicle class of North FNCD
        assertEquals(6, Vehicle.vehicle[4].size());

        // Testing the starting statements to verify starting objects for Vehicle class of South FNCD
        assertEquals(6, Vehicle2.vehicle[4].size());
    }
    @Test
    void checkElectricCars() {
        // Testing the starting statements to verify starting objects for Vehicle class of North FNCD
        assertEquals(6, Vehicle.vehicle[5].size());

        // Testing the starting statements to verify starting objects for Vehicle class of South FNCD
        assertEquals(6, Vehicle2.vehicle[5].size());
    }
    @Test
    void checkBudgetCars() {
        // Testing the starting statements to verify starting objects for Vehicle class of North FNCD
        assertEquals(6, Vehicle.vehicle[6].size());

        // Testing the starting statements to verify starting objects for Vehicle class of South FNCD
        assertEquals(6, Vehicle2.vehicle[6].size());
    }
    @Test
    void checkLuxuryCars() {
        // Testing the starting statements to verify starting objects for Vehicle class of North FNCD
        assertEquals(6, Vehicle.vehicle[7].size());

        // Testing the starting statements to verify starting objects for Vehicle class of South FNCD
        assertEquals(6, Vehicle2.vehicle[7].size());
    }
    @Test
    void checkSuperCars() {
        // Testing the starting statements to verify starting objects for Vehicle class of North FNCD
        assertEquals(6, Vehicle.vehicle[8].size());

        // Testing the starting statements to verify starting objects for Vehicle class of South FNCD
        assertEquals(6, Vehicle2.vehicle[8].size());
    }
    @Test
    void checkInterns() {
        // Testing the starting statements to verify starting objects for Staff class of North FNCD
        assertEquals(3, Staff.staff[0].size());

        // Testing the starting statements to verify starting objects for Staff class of South FNCD
        assertEquals(3, Staff2.staff[0].size());
    }
    @Test
    void checkMechanics() {
        // Testing the starting statements to verify starting objects for Staff class of North FNCD
        assertEquals(3, Staff.staff[1].size());

        // Testing the starting statements to verify starting objects for Staff class of South FNCD
        assertEquals(3, Staff2.staff[1].size());
    }
    @Test
    void checkSalespersons() {
        // Testing the starting statements to verify starting objects for Staff class of North FNCD
        assertEquals(3, Staff.staff[2].size());

        // Testing the starting statements to verify starting objects for Staff class of South FNCD
        assertEquals(3, Staff2.staff[2].size());
    }
    @Test
    void checkDrivers() {
        // Testing the starting statements to verify starting objects for Staff class of North FNCD
        assertEquals(3, Staff.staff[3].size());

        // Testing the starting statements to verify starting objects for Staff class of South FNCD
        assertEquals(3, Staff2.staff[3].size());
    }
    @Test
    void checkBudgetFNCDNorth() {
        // Testing the starting statements to verify budget of North FNCD
        assertEquals(true, Operation.budget >= 0);
    }
    @Test
    void checkBudgetFNCDSouth() {
        // Testing the starting statements to verify budget of South FNCD
        assertEquals(true, Operation2.budget >= 0);
    }
}