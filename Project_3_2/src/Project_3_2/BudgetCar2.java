package Project_3_2;

import java.io.IOException;

//For 2nd FNCD
class BudgetCar2 extends Vehicle2{
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