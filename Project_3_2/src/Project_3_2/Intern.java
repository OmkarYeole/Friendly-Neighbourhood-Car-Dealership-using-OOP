package Project_3_2;

import java.io.IOException;


//Strategy Pattern
interface WashBehavior{
    void wash(int j, FNCDdata fnc) throws IOException;
}
class Chemical extends Intern implements WashBehavior{
    public void wash(int j, FNCDdata fnc) throws IOException {
        Vehicle obj = new Vehicle();
        if(Vehicle.cleanliness[0].size()!=0){           //Checks if there is any 'Dirty' vehicle present, then only intern can wash it
            cleanliness_choice = rand.nextInt( Vehicle.cleanliness[0].size());
            vehicle_1 =  Vehicle.cleanliness[0].get(cleanliness_choice);
            prob = rand.nextDouble();                   //probability for updating cleanliness
            if (prob >= 0.1 && prob < 0.9) {
                Vehicle.cleanliness[1].add(vehicle_1);  //update cleanliness to 'Clean'
                System.out.println(staffType[0]+" " +staff[0].get(j)+" washed a "+Vehicle.carCleanliness[0]+" "+Vehicle.carType[obj.getCarType(vehicle_1)]+" "+vehicle_1+" using Chemical method and made it "+Vehicle.carCleanliness[1]);
                fnc.washOutcome(0,j,0,obj,vehicle_1,0,0,1,0);//obs
                Vehicle.cleanliness[0].remove(cleanliness_choice);
            } else if (prob >= 0.9) {
                Vehicle.cleanliness[2].add(vehicle_1);  //Update cleanliness to 'Sparkling'
                Intern.getBonus(j,vehicle_1);                  //Intern gets bonus
                System.out.println(staffType[0]+" " +staff[0].get(j)+" washed a "+Vehicle.carCleanliness[0]+" "+Vehicle.carType[obj.getCarType(vehicle_1)]+" "+vehicle_1+" using Chemical method and made it "+Vehicle.carCleanliness[2]+" (and earned bonus of $"+bonus_val+")");
                fnc.washOutcome(0,j,0,obj,vehicle_1,0,0,2,bonus_val);//obs
                Vehicle.cleanliness[0].remove(cleanliness_choice);
            } else{
                System.out.println(staffType[0]+" " +staff[0].get(j)+" washed a "+Vehicle.carCleanliness[0]+" "+Vehicle.carType[obj.getCarType(vehicle_1)]+" "+vehicle_1+" using Chemical method but it was still "+Vehicle.carCleanliness[0]);
                fnc.washOutcome(0,j,0,obj,vehicle_1,0,1,0,0);//obs
            }
            prob = rand.nextDouble();                   //probability for updating condition
            if (prob < 0.1){
                System.out.println("The "+vehicle_1+" has become Broken");
                fnc.washOutcome(vehicle_1,0);
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
                fnc.washOutcome(0,j,1,obj,vehicle_2,0,2,0,0);//obs
                Vehicle.cleanliness[1].remove(cleanliness_choice);
            } else if(prob >= 0.8) {
                Vehicle.cleanliness[2].add(vehicle_2);  //update cleanliness to 'Sparkling'
                Intern.getBonus(j,vehicle_2);                  //Intern gets bonus
                System.out.println(staffType[0]+" " +staff[0].get(j)+" washed a "+Vehicle.carCleanliness[1]+" "+Vehicle.carType[obj.getCarType(vehicle_2)]+" "+vehicle_2+" using Chemical method and made it "+Vehicle.carCleanliness[2]+" (and earned a bonus of $"+bonus_val+")");
                fnc.washOutcome(0,j,1,obj,vehicle_2,0,0,2,bonus_val);//obs
                Vehicle.cleanliness[1].remove(cleanliness_choice);
            } else{
                System.out.println(staffType[0]+" " +staff[0].get(j)+" washed a "+Vehicle.carCleanliness[1]+" "+Vehicle.carType[obj.getCarType(vehicle_2)]+" "+vehicle_2+" using Chemical method but it was still "+Vehicle.carCleanliness[1]);
                fnc.washOutcome(0,j,1,obj,vehicle_2,0,1,1,0);//obs
            }
            prob = rand.nextDouble();                   //probability for updating condition
            if (prob < 0.1){
                System.out.println("The "+vehicle_2+" has become Broken");
                fnc.washOutcome(vehicle_2,0);
                obj.updateCondition(0,vehicle_2);         //Update condition to 'Broken'
            }
        }
    }
}
class ElbowGrease extends Intern implements WashBehavior{
    public void wash(int j, FNCDdata fnc) throws IOException {
        Vehicle obj = new Vehicle();
        if(Vehicle.cleanliness[0].size()!=0){           //Checks if there is any 'Dirty' vehicle present, then only intern can wash it
            cleanliness_choice = rand.nextInt( Vehicle.cleanliness[0].size());
            vehicle_1 =  Vehicle.cleanliness[0].get(cleanliness_choice);
            prob = rand.nextDouble();                   //probability for updating cleanliness
            if (prob >= 0.25 && prob < 0.95) {
                Vehicle.cleanliness[1].add(vehicle_1);  //update cleanliness to 'Clean'
                System.out.println(staffType[0]+" " +staff[0].get(j)+" washed a "+Vehicle.carCleanliness[0]+" "+Vehicle.carType[obj.getCarType(vehicle_1)]+" "+vehicle_1+" using Elbow Grease method and made it "+Vehicle.carCleanliness[1]);
                fnc.washOutcome(0,j,0,obj,vehicle_1,1,0,1,0);//obs
                Vehicle.cleanliness[0].remove(cleanliness_choice);
            } else if (prob >= 0.95) {
                Vehicle.cleanliness[2].add(vehicle_1);  //Update cleanliness to 'Sparkling'
                Intern.getBonus(j,vehicle_1);                  //Intern gets bonus
                System.out.println(staffType[0]+" " +staff[0].get(j)+" washed a "+Vehicle.carCleanliness[0]+" "+Vehicle.carType[obj.getCarType(vehicle_1)]+" "+vehicle_1+" using Elbow Grease method and made it "+Vehicle.carCleanliness[2]+" (and earned bonus of $"+bonus_val+")");
                fnc.washOutcome(0,j,0,obj,vehicle_1,1,0,2,bonus_val);//obs
                Vehicle.cleanliness[0].remove(cleanliness_choice);
            } else{
                System.out.println(staffType[0]+" " +staff[0].get(j)+" washed a "+Vehicle.carCleanliness[0]+" "+Vehicle.carType[obj.getCarType(vehicle_1)]+" "+vehicle_1+" using Elbow Grease method but it was still "+Vehicle.carCleanliness[0]);
                fnc.washOutcome(0,j,0,obj,vehicle_1,1,1,0,0);//obs
            }
            prob = rand.nextDouble();                   //probability for updating condition
            if (prob < 0.1){
                System.out.println("The "+vehicle_1+" has become Like New");
                fnc.washOutcome(vehicle_1,2);
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
                fnc.washOutcome(0,j,1,obj,vehicle_2,1,2,0,0);//obs
                Vehicle.cleanliness[1].remove(cleanliness_choice);
            } else if(prob >= 0.85) {
                Vehicle.cleanliness[2].add(vehicle_2);  //update cleanliness to 'Sparkling'
                Intern.getBonus(j,vehicle_2);                  //Intern gets bonus
                System.out.println(staffType[0]+" " +staff[0].get(j)+" washed a "+Vehicle.carCleanliness[1]+" "+Vehicle.carType[obj.getCarType(vehicle_2)]+" "+vehicle_2+" using Elbow Grease method and made it "+Vehicle.carCleanliness[2]+" (and earned a bonus of $"+bonus_val+")");
                fnc.washOutcome(0,j,1,obj,vehicle_2,1,0,2,bonus_val);//obs
                Vehicle.cleanliness[1].remove(cleanliness_choice);
            } else{
                System.out.println(staffType[0]+" " +staff[0].get(j)+" washed a "+Vehicle.carCleanliness[1]+" "+Vehicle.carType[obj.getCarType(vehicle_2)]+" "+vehicle_2+" using Elbow Grease method but it was still "+Vehicle.carCleanliness[1]);
                fnc.washOutcome(0,j,1,obj,vehicle_2,1,1,1,0);//obs
            }
            prob = rand.nextDouble();                   //probability for updating condition
            if (prob < 0.1){
                System.out.println("The "+vehicle_2+" has become Like New");
                fnc.washOutcome(vehicle_2,2);
                obj.updateCondition(2,vehicle_2);         //Update condition to 'Like New'
            }
        }
    }
}
class Detailed extends Intern implements WashBehavior{
    public void wash(int j, FNCDdata fnc) throws IOException {
        Vehicle obj = new Vehicle();
        if(Vehicle.cleanliness[0].size()!=0){           //Checks if there is any 'Dirty' vehicle present, then only intern can wash it
            cleanliness_choice = rand.nextInt( Vehicle.cleanliness[0].size());
            vehicle_1 =  Vehicle.cleanliness[0].get(cleanliness_choice);
            prob = rand.nextDouble();                   //probability for updating cleanliness
            if (prob >= 0.2 && prob < 0.8) {
                Vehicle.cleanliness[1].add(vehicle_1);  //update cleanliness to 'Clean'
                System.out.println(staffType[0]+" " +staff[0].get(j)+" washed a "+Vehicle.carCleanliness[0]+" "+Vehicle.carType[obj.getCarType(vehicle_1)]+" "+vehicle_1+" using Detailed method and made it "+Vehicle.carCleanliness[1]);
                fnc.washOutcome(0,j,0,obj,vehicle_1,2,0,1,0);//obs
                Vehicle.cleanliness[0].remove(cleanliness_choice);
            } else if (prob >= 0.8) {
                Vehicle.cleanliness[2].add(vehicle_1);  //Update cleanliness to 'Sparkling'
                Intern.getBonus(j,vehicle_1);                  //Intern gets bonus
                System.out.println(staffType[0]+" " +staff[0].get(j)+" washed a "+Vehicle.carCleanliness[0]+" "+Vehicle.carType[obj.getCarType(vehicle_1)]+" "+vehicle_1+" using Detailed method and made it "+Vehicle.carCleanliness[2]+" (and earned bonus of $"+bonus_val+")");
                fnc.washOutcome(0,j,0,obj,vehicle_1,2,0,2,bonus_val);//obs
                Vehicle.cleanliness[0].remove(cleanliness_choice);
            } else{
                System.out.println(staffType[0]+" " +staff[0].get(j)+" washed a "+Vehicle.carCleanliness[0]+" "+Vehicle.carType[obj.getCarType(vehicle_1)]+" "+vehicle_1+" using Detailed method but it was still "+Vehicle.carCleanliness[0]);
                fnc.washOutcome(0,j,0,obj,vehicle_1,2,1,0,0);//obs
            }
        }
        if(Vehicle.cleanliness[1].size()!=0){           //Checks if there is any 'Clean' vehicle present, then only intern can wash it
            cleanliness_choice = rand.nextInt( Vehicle.cleanliness[1].size());
            vehicle_2 =  Vehicle.cleanliness[1].get(cleanliness_choice);
            prob = rand.nextDouble();
            if (prob < 0.05) {
                Vehicle.cleanliness[0].add(vehicle_2);  //update cleanliness back to 'Dirty'
                System.out.println(staffType[0]+" " +staff[0].get(j)+" washed a "+Vehicle.carCleanliness[1]+" "+Vehicle.carType[obj.getCarType(vehicle_2)]+" "+vehicle_2+" using Detailed method but made it "+Vehicle.carCleanliness[0]);
                fnc.washOutcome(0,j,1,obj,vehicle_2,2,2,0,0);//obs
                Vehicle.cleanliness[1].remove(cleanliness_choice);
            } else if(prob >= 0.6) {
                Vehicle.cleanliness[2].add(vehicle_2);  //update cleanliness to 'Sparkling'
                Intern.getBonus(j,vehicle_2);                  //Intern gets bonus
                System.out.println(staffType[0]+" " +staff[0].get(j)+" washed a "+Vehicle.carCleanliness[1]+" "+Vehicle.carType[obj.getCarType(vehicle_2)]+" "+vehicle_2+" using Detailed method and made it "+Vehicle.carCleanliness[2]+" (and earned a bonus of $"+bonus_val+")");
                fnc.washOutcome(0,j,1,obj,vehicle_2,2,0,2,bonus_val);//obs
                Vehicle.cleanliness[1].remove(cleanliness_choice);
            } else{
                System.out.println(staffType[0]+" " +staff[0].get(j)+" washed a "+Vehicle.carCleanliness[1]+" "+Vehicle.carType[obj.getCarType(vehicle_2)]+" "+vehicle_2+" using Detailed method but it was still "+Vehicle.carCleanliness[1]);
                fnc.washOutcome(0,j,1,obj,vehicle_2,2,1,1,0);//obs
            }
        }
    }
}

//Example of Inheritance
class Intern extends Staff{
    static String[] clean_outcome = {" and made it "," but it was still "," but made it "};
    static String[] wash_method = {"Chemical", "Elbow Grease", "Detailed"};
    int cleanliness_choice;
    String vehicle_1,vehicle_2;
    WashBehavior washBehavior;
    //primary method for intern to wash vehicles and update cleanliness and condition of cars
    public void setWashBehavior(FNCDdata fnc) throws IOException {
        System.out.println("\nWashing in North FNCD");
        fnc.dayAct(1,0);//obs
        for (int j=0;j<staff[0].size();j++){
            int i = rand.nextInt(3);
            if (i==0){
                washBehavior = new Chemical();
            } else if (i==1) {
                washBehavior = new ElbowGrease();
            } else {
                washBehavior = new Detailed();
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
        for(int i=0; i<9;i++){
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
    public void getSalary(FNCDdata fnc) throws IOException {
        for(int j=0;j<staff[0].size();j++){
            salary[0].set(j, normal_pay[0]+bonus[0].get(j));    //salary is calculated by adding bonus and normal pay for the day
            Operation.addBudget(0,j,fnc);
            Operation.budget=Operation.budget-salary[0].get(j); //Salary is reduced from the budget
            Operation.Staff_money=Operation.Staff_money+salary[0].get(j);//Adding salary to total money earned by Staff
            total_normal_pay[0].set(j,total_normal_pay[0].get(j)+normal_pay[0]);
            total_salary[0].set(j, total_salary[0].get(j)+salary[0].get(j));
            total_bonus[0].set(j, total_bonus[0].get(j)+bonus[0].get(j));
        }
    }
    //Adding a new intern
    public void addIntern(FNCDdata fnc) throws IOException {
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