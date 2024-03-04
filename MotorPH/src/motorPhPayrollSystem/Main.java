package motorPhPayrollSystem;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        boolean continueProcess = true;
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
                    System.out.println("Error: Invalid input. Please enter a valid employee number. " + "\n" + "\n");
                    System.out.print("Enter Employee Number: " + "\n");
                    scanner.next(); // Consume invalid input
                }
            }

            // For attempts on accessing the information of the chosen employee by the user
            try {
                // For displaying of employee information
                Employee employee = new Employee(employeeNo);
                employee.displayInfo();

                // For calculation of hours worked
                HoursWorked hoursWorked = new HoursWorked();
                double hours = hoursWorked.calculateHoursWorked();

                // For calculation of gross wage
                GrossWage grossWage = new GrossWage();
                double hourlyRate = employee.getHourlyRate();
                double gross = grossWage.calculateGrossWage(hours, hourlyRate);

                // For calculation of net wage
                NetWage netWage = new NetWage();
                double net = netWage.calculateNetWage(gross);

                // For showing the final output of hours worked, gross, and net wage
                System.out.println("Hours Worked: " + hours);
                System.out.println("Gross Wage: " + gross);
                System.out.println("Net Wage: " + net + "\n");
                
                // Ask the user if they wish to continue
                System.out.println("Do you want to find another employee? (yes/no): ");
                String choice = scanner.next();
                System.out.println();
                if (!choice.equalsIgnoreCase("yes")) {
                    continueProcess = false;
                }

            // For showing errors
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
                // No need to prompt for continuing if there's an error, as the loop will continue automatically
            }
        } while (continueProcess);

        // For closing the scanner
        scanner.close();
    }
}