package Project_3_2;

import java.io.IOException;

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
