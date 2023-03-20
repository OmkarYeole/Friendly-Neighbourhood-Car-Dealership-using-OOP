package Project_3_2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

//JUnit testing cases
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
        Tracker tra2 = Tracker.getInstance2();//obs
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