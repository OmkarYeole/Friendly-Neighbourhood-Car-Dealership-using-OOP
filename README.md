# Project3_CSCI_5448

Team Members - Omkar Yeole, Abhishek Limaye

Java version - 19.0.1

Assumptions made in the code:

Day 1 is considered as Monday. Therefore the 7th day, 14th day, 21st day and 28th days are Sundays.
A predefined ArrayList has been defined to get the names of the staff and the vehicles.
All the data has been stored in Arraylists of arraylists.
For Staff ArrayLists Staff[0] represents Interns.
For Staff ArrayLists Staff[1] represents Mechanics.
For Staff ArrayLists Staff[2] represents Salesperson.
For Staff ArrayLists Staff[3] represents Driver.
For all the variables from Staff class such as staff_status, days_worked, staff_salary 0th ArrayList will correspond to Interns, 1st ArrayList will correspond to Mechanics and 2nd ArrayList will correspond to Salesperson.
For vehicle ArrayLists vehicle[0] represents Cars.
For vehicle ArrayLists vehicle[1] represents Pickup.
For vehicle ArrayLists vehicle[2] represents PerformanceCar.
For vehicle ArrayLists vehicle[3] represents Motorcycles.
For vehicle ArrayLists vehicle[4] represents MonsterTrucks.
For vehicle ArrayLists vehicle[5] represents ElectricCars.
For all the variables from Vehicle class such as vehicle, status, cost_price, sales_price 0th ArrayList will correspond to Cars, 1st ArrayList will correspond to Pickup, 2nd ArrayList will correspond to PerformanceCar, 3rd ArrayList will correspond to Motorcycles, 4th ArrayList will correspond to MonsterTrucks, 5th ArrayList will correspond to ElectricCars.
Bonus is decided based on the car type. Pickup gets 200, PerformanceCar 225, Motorcycles 250, Monster Trucks 275.
Driver gets a normal pay of 2000.
The vehicles in race_vehicles will be associated with the drivers (all names from staff[3]) having same index numbers. For example, race_vehicles.get(i) will have a driver staff[3].get(i) and its count of races won will be race_won.get(i).
After the end of each day bonus amount is initialized to 0 for the next day.
quit status is also initialized again for the next day.
After the driver gets injured and quits, driver details are also removed from active drivers.
Sale bonus for Motorcycles, Monster Trucks and Electric Cars is 150, 175, 200.
