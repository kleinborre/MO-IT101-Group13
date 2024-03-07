package motorPhPayrollSystem;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean continueProcess = true;
        
        // Indicate start of operation
        System.out.println("************* Welcome To MotorPH Payroll System *************");
        
        do {
            // For handling chosen employee number by the user input
        	System.out.print("Enter Employee Number: ");
            System.out.println();

            
            // For validating employee on user input
            int employeeNo = 0;
            while (true) {
                if (scanner.hasNextInt()) {
                    employeeNo = scanner.nextInt();
                    break;
                } else {
                	// For handling reset for invalidation
                    System.out.println("\n" + "Error: Invalid input! Please enter a valid employee number! " + "\n");
                    System.out.print("Enter Employee Number: " + "\n");
                    scanner.next(); // Consume invalid input
                }
            }

            // For accessing the information of the chosen employee by the user
            try {
                // For displaying of employee information
                Employee employee = new Employee(employeeNo);
                employee.displayInfo();

                // For calculation of hours worked
                HoursWorked hoursWorked = new HoursWorked(employeeNo);
                hoursWorked.displayInfo();

               // For calculation of gross wage
                GrossWage grossWage = new GrossWage();

               // For calculation of net wage
                NetWage netWage = new NetWage();
                
                // Prompt the user if they wish to continue
                System.out.println("\n" + "*************************************************************");
                System.out.print("Do you wish to continue? (Yes/No): ");
                String choice = scanner.next();
                
                if (!choice.equalsIgnoreCase("yes")) {
                    continueProcess = false;
                    System.out.println("\n" + "******************* End of Operation ************************");
                }

            // For showing errors on invalid operation
            } catch (IllegalArgumentException e) {
                System.out.println("\n" + "Error: " + e.getMessage());
            }
        } while (continueProcess);

        // For closing the scanner
        scanner.close();
    }
}