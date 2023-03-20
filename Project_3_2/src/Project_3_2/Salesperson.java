package Project_3_2;

import java.io.IOException;

class Salesperson extends Staff{
    static int index2,buyer_index1,buyer_index2,buyer_choice,vehicle_choice;
    String salesperson,buyer,vehicle;
    static Vehicle[] vehicle_object = {new Car(),new Pickup(),new PerformanceCar(),new Motorcycles(),new MonsterTrucks(),new ElectricCar(),new BudgetCar(),new LuxuryCar(),new SuperCar()};
    //primary method for salespersons to sell cars
    public void sale(FNCDdata fnc) throws IOException {
        System.out.println("\nSelling in North FNCD");
        fnc.dayAct(3,0);//obs
        Operation.total_sales = 0;//Initializing the total sales at the beginning of the day
        Buyer buyer1 = new Buyer();
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
                    vecl = new ExtendedWarranty(buyer_choice,vehicle_choice,fnc);//wrapping it with decorator components
                    vecl = new Undercoating(buyer_choice,vehicle_choice,fnc);
                    vecl = new RoadRescueCoverage(buyer_choice,vehicle_choice,fnc);
                    vecl = new SatelliteRadio(buyer_choice,vehicle_choice,fnc);
                    Operation.budget = Operation.budget + Vehicle.sales_price[buyer_choice].get(vehicle_choice);//Sales price of car added to budget
                    Operation.total_sales = Operation.total_sales + Vehicle.sales_price[buyer_choice].get(vehicle_choice);//updated Total sales of the day
                    getBonus(index2, vehicle);    //Salesperson gets a bonus
                    Vehicle.soldVehicles[buyer_choice].add(vehicle); //Car is added to list of sold vehicles
                    System.out.println(staffType[2]+" "+salesperson +" has sold a "+Vehicle.carCleanliness[obj.getCleanliness(vehicle)]+" "+Vehicle.carCondition[obj.getCondition(vehicle)]+" "+Vehicle.carType[buyer_choice] +" "+ vehicle +" to "+buyer+" for $"+Vehicle.sales_price[buyer_choice].get(vehicle_choice)+" (and earned a bonus of $"+bonus_val+")");
                    fnc.sellOutcome(2,salesperson,obj,vehicle,buyer_choice,buyer,vehicle_choice,bonus_val);//obs
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
    public void sale2(FNCDdata fnc) throws IOException {         //sale for the 31st day
        fnc.dayAct(3,0);//obs
        Vehicle obj = new Vehicle();
        Operation.budget = Operation.budget + Vehicle.sales_price[commandReceiver.user_choice].get(commandReceiver.vehicle_choice);//Sales price of car added to budget
        Operation.total_sales = Operation.total_sales + Vehicle.sales_price[commandReceiver.user_choice].get(commandReceiver.vehicle_choice);//updated Total sales of the day
        getBonus(index2, commandReceiver.vehicle);    //Salesperson gets a bonus
        Vehicle.soldVehicles[commandReceiver.user_choice].add(commandReceiver.vehicle); //Car is added to list of sold vehicles
        System.out.println(staffType[2]+" "+commandReceiver.salesperson +" has sold a "+commandReceiver.vehicle_cleanliness+" "+commandReceiver.vehicle_condition+" "+commandReceiver.vehicle_type+" "+ commandReceiver.vehicle +" to User for $"+commandReceiver.sales_price+" (and earned a bonus of $"+bonus_val+")");
        fnc.sellOutcome(2,commandReceiver.salesperson,obj,commandReceiver.vehicle,commandReceiver.user_choice,"User",commandReceiver.vehicle_choice,bonus_val);//obs
        Vehicle.vehicle[commandReceiver.user_choice].remove(commandReceiver.vehicle_choice); //Car is removed from Vehicles in stock
        Vehicle.status[commandReceiver.user_choice].remove(commandReceiver.vehicle_choice);
        Vehicle.condition[obj.getCondition(commandReceiver.vehicle)].remove(obj.getCondition2(commandReceiver.vehicle));
        Vehicle.cleanliness[obj.getCleanliness(commandReceiver.vehicle)].remove(obj.getCleanliness2(commandReceiver.vehicle));
        Vehicle.cost_price[commandReceiver.user_choice].remove(commandReceiver.vehicle_choice);
        Vehicle.sales_price[commandReceiver.user_choice].remove(commandReceiver.vehicle_choice);
    }
    public void getTotalDays(){
        for(int j=0;j<staff[2].size();j++){
            days_worked[2].set(j, days_worked[2].get(j)+1);  //Total days worked increases by 1 after each day a salesperson was active
        }
    }
    public static void getBonus(int k, String vehicle){
        for(int i=0; i<9;i++){
            for(int j = 0; j< Vehicle.vehicle[i].size(); j++) {
                if (Vehicle.vehicle[i].get(j).equals(vehicle)){
                    bonus[2].set(k, bonus[2].get(k) + Vehicle.vehicle_sale_bonus[i]);  //Bonus is decided based on type of car
                    bonus_val=Vehicle.vehicle_sale_bonus[i];
                    break;
                }
            }
        }
    }
    public void getSalary(FNCDdata fnc) throws IOException {
        for(int j=0;j<staff[2].size();j++){
            salary[2].set(j, normal_pay[2]+bonus[2].get(j));//salary is calculated by adding bonus and normal pay for the day
            Operation.addBudget(2,j,fnc);
            Operation.budget=Operation.budget-salary[2].get(j);
            Operation.Staff_money=Operation.Staff_money+salary[2].get(j);
            total_normal_pay[2].set(j,total_normal_pay[2].get(j)+normal_pay[2]);
            total_salary[2].set(j, total_salary[2].get(j)+salary[2].get(j));
            total_bonus[2].set(j, total_bonus[2].get(j)+bonus[2].get(j));
        }
    }
}
