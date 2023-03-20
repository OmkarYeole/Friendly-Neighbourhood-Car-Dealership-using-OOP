package Project_3_2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

class Driver extends Staff{
    int choice_index,choice,pos;
    int[] race_choice = {1,2,3,4,7,8};
    ArrayList<Integer> positions;
    static ArrayList<Integer> race_position;
    ArrayList<String> vehicles_choice;
    static ArrayList<String> race_vehicles;
    ArrayList<String> injured_drivers;
    Vehicle obj = new Vehicle();

    public void getRaceVehicles(FNCDdata fnc) throws IOException {      //All methods in Driver will check for day to be Wednesday or Sunday
        if(Operation.day_count==3 || Operation.day_count==10 || Operation.day_count==17 || Operation.day_count==24 ||
                Operation.day_count==7 || Operation.day_count==14 || Operation.day_count==21 || Operation.day_count==28) {
            System.out.println("\nRacing in North FNCD");
            fnc.dayAct(4,0);//obs
            vehicles_choice = new ArrayList<>();
            race_vehicles = new ArrayList<>();
            injured_drivers = new ArrayList<>();
            choice_index = rand.nextInt(race_choice.length);        //selecting a vehicle eligible for racing
            choice = race_choice[choice_index];
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
    public void race(FNCDdata fnc) throws IOException {
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
                                fnc.raceOutcome(3,i,choice,bonus_val,0);//obs
                                break;
                            }
                        }
                    } else if (race_position.get(i)>15){                  //If vehicle gets damaged
                        for (int j=0;j<vehicles_choice.size();j++){
                            if (vehicles_choice.get(j).equals(race_vehicles.get(i))) {
                                Vehicle.condition[0].add(vehicles_choice.get(j));//update the condition of vehicle to broken
                                Vehicle.condition[obj.getCondition(vehicles_choice.get(j))].remove(obj.getCondition2(vehicles_choice.get(j)));//removing the previous condition
                                System.out.println(staffType[3]+" "+staff[3].get(i)+" finished the race at position "+race_position.get(i)+" with the "+Vehicle.carType[choice]+" "+race_vehicles.get(i)+" and the Vehicle became Broken ");
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
                                System.out.println(staffType[3]+" "+staff[3].get(i)+" finished the race at position "+race_position.get(i)+" with the "+Vehicle.carType[choice]+" "+race_vehicles.get(i));
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
        if(Operation.day_count==3 || Operation.day_count==10 || Operation.day_count==17 || Operation.day_count==24 ||
                Operation.day_count==7 || Operation.day_count==14 || Operation.day_count==21 || Operation.day_count==28) {
            for(int j=0;j<staff[3].size();j++){
                days_worked[3].set(j, days_worked[3].get(j)+1);  //Total days worked increases by 1 after each day a salesperson was active
            }
        }
    }
    public static void getBonus(int k, String vehicle){
        if(Operation.day_count==3 || Operation.day_count==10 || Operation.day_count==17 || Operation.day_count==24 ||
                Operation.day_count==7 || Operation.day_count==14 || Operation.day_count==21 || Operation.day_count==28) {
            for(int i=0; i<9;i++){
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
    public void getSalary(FNCDdata  fnc) throws IOException {
        if(Operation.day_count==3 || Operation.day_count==10 || Operation.day_count==17 || Operation.day_count==24 ||
                Operation.day_count==7 || Operation.day_count==14 || Operation.day_count==21 || Operation.day_count==28) {
            for(int j=0;j<staff[3].size();j++){
                salary[3].set(j, normal_pay[3]+bonus[3].get(j));//salary is calculated by adding bonus and normal pay for the day
                Operation.addBudget(3,j,fnc);
                Operation.budget=Operation.budget-salary[3].get(j);
                Operation.Staff_money=Operation.Staff_money+salary[3].get(j);
                total_normal_pay[3].set(j,total_normal_pay[3].get(j)+normal_pay[3]);
                total_salary[3].set(j, total_salary[3].get(j)+salary[3].get(j));
                total_bonus[3].set(j, total_bonus[3].get(j)+bonus[3].get(j));
            }
        }
    }
    public void addDriver(FNCDdata fnc) throws IOException {
        if(Operation.day_count==3 || Operation.day_count==10 || Operation.day_count==17 || Operation.day_count==24 ||
                Operation.day_count==7 || Operation.day_count==14 || Operation.day_count==21 || Operation.day_count==28) {
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
