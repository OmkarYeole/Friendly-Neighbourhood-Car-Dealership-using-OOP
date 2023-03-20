package Project_3_2;

import java.io.IOException;

class Mechanic extends Staff {
    int index,vehicle_choice;
    String vehicle;
    //primary method for mechanic to repair cars and update condition and cleanliness
    public void repair(FNCDdata fnc) throws IOException {
        System.out.println("\nRepairing in North FNCD");
        fnc.dayAct(2,0);//obs
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
                    repair_update(j,obj,fnc);
                }
            }
        }
    }
    // a method which decides whether the car was fixed and condition was updated
    // along with degrading the car cleanliness
    public void repair_update(int j,Vehicle obj,FNCDdata fnc) throws IOException {
        vehicle_choice = rand.nextInt(Vehicle.condition[index].size());
        vehicle = Vehicle.condition[index].get(vehicle_choice);
        prob = rand.nextDouble();
        if (prob < 0.8) {
            getBonus(j, vehicle);    //Mechanic gets bonus if car condition level is fixed to next condition (e.g. from dirty to clean)
            System.out.print(staffType[1]+" "+staff[1].get(j)+" repaired a "+Vehicle.carCondition[obj.getCondition(vehicle)]+" "+Vehicle.carType[obj.getCarType(vehicle)]+" "+vehicle+" and made it "+Vehicle.carCondition[obj.getCondition(vehicle)+1]+" (and earned a bonus of $"+bonus_val+")");
            fnc.repairOutcome(1,j,obj,vehicle,0,bonus_val);//obs
            Vehicle.condition[index+1].add(vehicle);
            //Example of Delegation
            obj.updateSalesPrice(index,vehicle); //when condition of car is fixed to next level, its sales prices is updated
            Vehicle.condition[index].remove(vehicle_choice);
        } else{
            System.out.print(staffType[1]+" "+staff[1].get(j)+" repaired a "+Vehicle.carCondition[obj.getCondition(vehicle)]+" "+Vehicle.carType[obj.getCarType(vehicle)]+" "+vehicle+" but it was still "+Vehicle.carCondition[obj.getCondition(vehicle)]);
            fnc.repairOutcome(1,j,obj,vehicle,1,0);//obs
        }
        obj.updateCleanliness(vehicle); //Degrade the cleanliness by one level
        fnc.repairOutcome(obj,vehicle);//obs
        System.out.println(" and the vehicle became "+Vehicle.carCleanliness[obj.getCleanliness(vehicle)]);
    }

    public void getTotalDays(){
        for(int j=0;j<staff[1].size();j++){
            days_worked[1].set(j, days_worked[1].get(j)+1); //Total days worked increases by 1 after each day a mechanic was active
        }
    }
    public static void getBonus(int k, String vehicle){
        for(int i=0; i<9;i++){
            for(int j = 0; j< Vehicle.vehicle[i].size(); j++) {
                if (Vehicle.vehicle[i].get(j).equals(vehicle)) {
                    bonus[1].set(k, bonus[1].get(k) + Vehicle.vehicle_repair_bonus[i]); //Bonus is decided based on type of car
                    bonus_val=Vehicle.vehicle_repair_bonus[i];
                    break;
                }
            }
        }
    }
    public void getSalary(FNCDdata fnc) throws IOException {
        for(int j=0;j<staff[1].size();j++){
            salary[1].set(j, normal_pay[1]+bonus[1].get(j));//salary is calculated by adding bonus and normal pay for the day
            Operation.addBudget(1,j,fnc);
            Operation.budget=Operation.budget-salary[1].get(j);
            Operation.Staff_money=Operation.Staff_money+salary[1].get(j);
            total_normal_pay[1].set(j,total_normal_pay[1].get(j)+normal_pay[1]);
            total_salary[1].set(j, total_salary[1].get(j)+salary[1].get(j));
            total_bonus[1].set(j, total_bonus[1].get(j)+bonus[1].get(j));
        }
    }
}
