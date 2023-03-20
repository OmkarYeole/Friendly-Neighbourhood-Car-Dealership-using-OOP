package Project_3_2;

import java.io.IOException;

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
        for(int i=0; i<9;i++){
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
