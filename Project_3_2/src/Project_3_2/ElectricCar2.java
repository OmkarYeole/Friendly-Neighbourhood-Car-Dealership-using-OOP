package Project_3_2;

import java.io.IOException;

//For 2nd FNCD
class ElectricCar2 extends Vehicle2{
    public void buyVehicle(FNCDdata2 fnc) throws IOException {
        int cp1 = rand.nextInt(30000, 50001);
        cost_price[5].add(cp1);
        sales_price[5].add(cp1 * 2);

        // Assigning a range for the Electric Cars based on their condition
        int range_select = rand.nextInt(60, 401);
        while (range.size() < 6) {
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