package Project_3_2;

import java.io.IOException;

class MonsterTrucks extends Vehicle{
    public void buyVehicle(FNCDdata fnc) throws IOException {
        int cp1 = rand.nextInt(20000, 40001);
        cost_price[4].add(cp1);
        sales_price[4].add(cp1 * 2);
        if(getCondition(vehicle[4].get(vehicle[4].size()-1))==0){//If vehicle is initially broken, reducing its cost price by 50%.
            cost_price[4].set((vehicle[4].size()-1), (int)(cost_price[4].get(vehicle[4].size()-1)*0.5));
        } else if (getCondition(vehicle[4].get(vehicle[4].size()-1))==1){//If vehicle is initially used, reducing its cost price by 20%.
            cost_price[4].set((vehicle[4].size()-1), (int)(cost_price[4].get(vehicle[4].size()-1)*0.8));
        }
        Operation.addBudget(4,fnc);
        Operation.budget = Operation.budget - cost_price[4].get(vehicle[4].size()-1);
        System.out.println("Bought "+carType[4]+" "+vehicle[4].get(vehicle[4].size()-1)+" for $"+cost_price[4].get(cost_price[4].size()-1));
        fnc.dayEnd(4,vehicle[4].size()-1,3);//obs
    }
}
