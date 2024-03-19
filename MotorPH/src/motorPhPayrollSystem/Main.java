package motorPhPayrollSystem;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    
    public static String[] employee;  // Array to store employee details.
    static String employeeHourlyRate; // Variable to store employee hourly rate.
    static double hoursWorked = 0.0;  // Variable to store total hours worked
    
    // Main method for the Payroll System. Throws IOException if an I/O error occurs.
    public static void main(String[] args) throws IOException {
    	   
        // Prints a header for the Payroll System.
        System.out.println("================================= MOTORPH PAYROLL SYSTEM =================================");
        
        // Starts a session loop.
        boolean isValid = true; 
        
        while(isValid) {
        	// Reset variables at the beginning of each session
            resetData();
            hoursWorked = 0.0; // Reset total hours worked
        	
            // Prints out the start of the session, indicating the beginning of the loop.
            System.out.println("===================================== START OF SESSION ===================================" + "\n");
            // Calls the getEmployeeNumber method
            getEmployeeNumber();
            // Upon reading and recognizing an employee number, the program prints out the information of the corresponding employee.
            System.out.println("\n" + "==================================== EMPLOYEE DETAILS ====================================" + "\n");
            if(employee.length != 0) {
                System.out.println("Employee No.              : " + employee[0]);
                System.out.println("Full Name                 : " + employee[1] + ", " + employee[2]);
                System.out.println("Birthdate                 : " + employee[3]);
                System.out.println("Address                   : " + sanitizeData(employee[4]));
                System.out.println("Phone Number              : " + employee[5]);
                System.out.println("Position                  : " + employee[11]);
                System.out.println("SSS #                     : " + employee[6]);
                System.out.println("philHealth #              : " + employee[7]);
                System.out.println("TIN #                     : " + employee[8]);
                System.out.println("Pag-ibig #                : " + employee[9]);
                System.out.println("Status                    : " + employee[10]);
                System.out.println("Immediate Supervisor      : " + sanitizeData(employee[12]));
                System.out.println("Rice Subsidy              : " + "₱ " + Double.parseDouble(sanitizeData(employee[14])));
                System.out.println("Phone Allowance           : " + "₱ " + Double.parseDouble(sanitizeData(employee[15])));
                System.out.println("Clothing Allowance        : " + "₱ " + Double.parseDouble(sanitizeData(employee[16])));
                System.out.println("Basic Salary              : " + "₱ " + Double.parseDouble(sanitizeData(employee[13])));
                System.out.println("Gross Semi-monthly Salary : " + "₱ " + Double.parseDouble(sanitizeData(employee[17])));
                System.out.println("Hourly Wage               : " + "₱ " + employeeHourlyRate);
                
                // Displays a line to help the user choose a month.
                System.out.println("\n" + "====================================  CHOOSE A MONTH  ====================================" + "\n");
                // Prompt the user to choose a month from 1 to 12, then read the input and retrieve the attendance record for the specified month for the first employee.
                System.out.print("Choose a month from 1 - 12: ");
                BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));
                int chosenMonth = Integer.parseInt(inputReader.readLine());
                getAttendanceRecord(employee[0], chosenMonth);
                
                // Convert the chosen month number to its corresponding name.
                String monthInWord = null;
                if		(chosenMonth == 1) 	monthInWord = "January";
                else if (chosenMonth == 2) 	monthInWord = "February";
                else if (chosenMonth == 3) 	monthInWord = "March";
                else if (chosenMonth == 4) 	monthInWord = "April";
                else if (chosenMonth == 5) 	monthInWord = "May";
                else if (chosenMonth == 6) 	monthInWord = "June";
                else if (chosenMonth == 7) 	monthInWord = "July";
                else if (chosenMonth == 8) 	monthInWord = "August";
                else if (chosenMonth == 9) 	monthInWord = "September";
                else if (chosenMonth == 10) monthInWord = "October";
                else if (chosenMonth == 11) monthInWord = "November";
                else monthInWord = "December";
                
                // Display a header for total hours worked.
                System.out.println("\n" + "=================================== TOTAL HOURS WORKED ===================================" +"\n");
                // Display the total worked hours for the chosen month.
                System.out.println("Total Worked Hours In "  + monthInWord + " [" + chosenMonth + "]: " + hoursWorked + " hrs");
                
                // Display a header for weekly gross calculation.
                System.out.println("\n" + "================================= WEEKLY GROSS CALCULATION ===============================" +"\n");
                // Weekly calculations
                System.out.println("Weekly Gross Salary       : " + "₱ " + weeklyGrossSalary());              
                System.out.println("SSS Contribution          : " + "₱ " + sssCalculation());                
                System.out.println("philHealth Contribution   : " + "₱ " + philhealthCalculation());                
                System.out.println("Pag-IBIG Contribution     : " + "₱ " + pagibigCalculation());                
                System.out.println("Total Contributions       : " + "₱ " + totalContribution());                
                System.out.println("Taxable income            : " + "₱ " + taxableMonthlyIncome());                          
                System.out.println("Withholding Tax           : " + "₱ " + withholdingTax() / 4);
                
                // Display a header for weekly net salary
                System.out.println("\n" + "================================== WEEKLY NET CALCULATION ================================" +"\n");
            	// Weekly net salary
                System.out.println("Weekly Net Salary         : " + "₱ " + weeklyNetSalary());
                
                // Display a header for monthly gross calculation.
                System.out.println("\n" + "================================= MONTHLY GROSS CALCULATION ==============================" +"\n");
                // Monthly calculations
                System.out.println("Monthly Gross Salary      : " + "₱ " + weeklyGrossSalary() * 4);
                System.out.println("SSS Contribution          : " + "₱ " + sssCalculation() * 4);
                System.out.println("philHealth Contribution   : " + "₱ " + philhealthCalculation() * 4);
                System.out.println("Pag-IBIG Contribution     : " + "₱ " + pagibigCalculation() * 4);
                System.out.println("Total Contributions       : " + "₱ " + totalContribution() * 4);
                System.out.println("Monthly Taxable income    : " + "₱ " + taxableMonthlyIncome() * 4);         
                System.out.println("Withholding Tax           : " + "₱ " + withholdingTax());
                
                // Display a header monthly net salary.
                System.out.println("\n" + "================================== MONTHLY NET CALCULATION ===============================" +"\n");                
                //Monthly net salary
                System.out.println("Monthly Net Salary        : " + "₱ " + weeklyNetSalary() * 4);
                
                // Ask the user if they want to proceed.
                System.out.println("\n" + "===================================== CONTINUE PROCESS? ==================================" + "\n");
                System.out.print("Do you want to continue the process ( Yes / No ) ?: ");
                String response = inputReader.readLine();
                
                if(!response.equalsIgnoreCase("yes")){
                	isValid = false;
                }
                
            }else {
            	// Prompt if user input an invalid number.
                System.out.println("No data found. Please try again.");
            }	// End of session or process if user does not want to continue
            	
            	// Display end of session message
            	System.out.println("\n" + "=====================================  END OF SESSION  ===================================");
            	System.out.println("==========================================================================================");                        
        }
    }
    
    // Resets employee hourly rate and clears the employee array.
    public static void resetData() {
        employeeHourlyRate = "";
        employee = new String[0];
    }
    
    /**
     * Resets data, prompts the user to enter an employee number,
     * retrieves employee details if available, and sets the employee hourly rate.
     */    
    public static void getEmployeeNumber() {
        resetData();
        
        System.out.print("Enter Employee Number     : ");
        BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));
        try {
            String userInput = inputReader.readLine();
            // Attempt to parse the user input as an integer
            int employeeNumber = Integer.parseInt(userInput);
            String employeeDetail = getEmployeeDetails(userInput);
            
            if (!employeeDetail.equals("")) {
                String[] row = employeeDetail.split(",");
                employee = row;
                employeeHourlyRate = row[18];
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid Employee Number Format!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Retrieves details of an employee with the given employee number from a CSV file.
     * Parameter employeeNumber - The employee number to search for.
     */
    public static String getEmployeeDetails(String employeeNumber) {
        String file = System.getProperty("user.dir") + "\\src\\motorPhPayrollSystem\\Employee Details.csv";

        BufferedReader reader = null;
        String line = "";
        String employeeFound = "";

        try {
            reader = new BufferedReader(new FileReader(file));

            // Skip the header line
            reader.readLine();

            while ((line = reader.readLine()) != null) {
                String replace = line.replaceAll(",(?!(([^\"]*\"){2})*[^\"]*$)", ";x;");
                String[] row = replace.split(",");

                if (Integer.parseInt(row[0]) == Integer.parseInt(employeeNumber)) {
                    employeeFound = replace;
                    break; // Exit loop once employee is found
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return employeeFound;
    }
    
    // Removes special characters and quotes from the input string.
    public static String sanitizeData(String spaces) {
        return spaces.replace(";x;", "").replace("\"", "");
    }
    
    
    /**
     * Retrieves attendance records for the specified employee and month from a CSV file.
     * Calculates the total hours worked for the month.
     * Parameter employeeNumber - The employee number to retrieve attendance records for.
     * Parameter month - The month for which attendance records are to be retrieved.
     */
    public static void getAttendanceRecord(String employeeNumber, int month) {
        String file = System.getProperty("user.dir") + "\\src\\motorPhPayrollSystem\\Attendance Record.csv";

        BufferedReader reader = null;
        String line = "";
        double lunchBreakHours = 1.0;

        try {
            reader = new BufferedReader(new FileReader(file));

            // Skip the header line
            reader.readLine();

            while ((line = reader.readLine()) != null) {
                String[] row = line.split(",");
                
                // Check if employee number matches and month matches
                if (Integer.parseInt(row[0]) == Integer.parseInt(employeeNumber)) {
                    String[] dateParts = row[1].split("/");
                    int recordMonth = Integer.parseInt(dateParts[0]);
                    if (recordMonth == month) {
                        // Calculate hours worked for the day
                        String[] timeInParts = row[2].split(":");
                        String[] timeOutParts = row[3].split(":");
                        int hoursIn = Integer.parseInt(timeInParts[0]);
                        int minutesIn = Integer.parseInt(timeInParts[1]);
                        int hoursOut = Integer.parseInt(timeOutParts[0]);
                        int minutesOut = Integer.parseInt(timeOutParts[1]);
                        
                        // Convert time to minutes for easier calculation
                        int totalMinutesIn = hoursIn * 60 + minutesIn;
                        int totalMinutesOut = hoursOut * 60 + minutesOut;
                        
                        // Subtract lunch break from the total hours worked today
                        double hoursWorkedToday = ((totalMinutesOut - totalMinutesIn) / 60.0) - lunchBreakHours;
                        hoursWorked += hoursWorkedToday;
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    /**
     * Calculates the weekly gross salary based on the actual monthly gross salary.
     * Returns the calculated weekly gross salary.
     */
    public static double weeklyGrossSalary() {
    	double weekly_gross_salary = actualMonthlyGrossSalary() / 4;
    	return weekly_gross_salary;
    }
    
    /**
     * Calculates the weekly net salary.
     * Returns the calculated weekly net salary.
     */
    public static double weeklyNetSalary() {
    	Double weekly_net_salary = ((taxableMonthlyIncome()*4) - withholdingTax())/4;
    	return weekly_net_salary;
    }
    
    /**
     * Calculates the monthly gross salary.
     * Returns the calculated monthly gross salary.
     */
    public static double monthlyGrossSalary() {
    	Double gross_monthly = Double.parseDouble(sanitizeData(employee[13]));
    	return gross_monthly;   	
    }
    
    /**
     * Calculates the actual monthly gross salary based on the employee's hourly rate and hours worked.
     * Returns the calculated actual monthly gross salary.
     */
    public static double actualMonthlyGrossSalary() {
    	double emp_hourly_rate = Double.parseDouble(employeeHourlyRate);
    	double actual_gross_monthly = emp_hourly_rate * hoursWorked;	
    	return actual_gross_monthly;
    }
    
    /**
     * Calculates the weekly SSS (Social Security System) contribution based on the actual monthly gross salary.
     * Returns the calculated weekly SSS contribution.
     */
    public static double sssCalculation() {
    	Double gross_monthly = actualMonthlyGrossSalary();
    	double sss_contribution;
    		if(gross_monthly >= 24750) {
    			sss_contribution = 1125;
    		}else if(gross_monthly >= 24250) {
    			sss_contribution = 1102.50;
    		}else if(gross_monthly >= 23750) {
    			sss_contribution = 1080.00;
    		}else if(gross_monthly >= 23250) {
    			sss_contribution = 1057.50;
    		}else if(gross_monthly >= 22750) {
    			sss_contribution = 1035.00;
    		}else if(gross_monthly >= 22250) {
    			sss_contribution = 1012.50;
    		}else if(gross_monthly >= 21750) {
    			sss_contribution = 990.00;
    		}else if(gross_monthly >= 21250) {
    			sss_contribution = 967.50;
    		}else if(gross_monthly >= 20750) {
    			sss_contribution = 945.00;
    		}else if(gross_monthly >= 20250) {
    			sss_contribution = 922.50;
    		}else if(gross_monthly >= 19750) {
    			sss_contribution = 900.00;
    		}else if(gross_monthly >= 19250) {
    			sss_contribution = 877.50;
    		}else if(gross_monthly >= 18750) {
    			sss_contribution = 855.00;
    		}else if(gross_monthly >= 18250) {
    			sss_contribution = 832.50;
    		}else if(gross_monthly >= 17750) {
    			sss_contribution = 810.00;
    		}else if(gross_monthly >= 17250) {
    			sss_contribution = 787.50;
    		}else if(gross_monthly >= 16750) {
    			sss_contribution = 765.00;
    		}else if(gross_monthly >= 16250) {
    			sss_contribution = 742.50;
    		}else if(gross_monthly >= 15750) {
    			sss_contribution = 720.00;
    		}else if(gross_monthly >= 15250) {
    			sss_contribution = 697.50;
    		}else if(gross_monthly >= 14750) {
    			sss_contribution = 675.00;
    		}else if(gross_monthly >= 14250) {
    			sss_contribution = 652.50;
    		}else if(gross_monthly >= 13750) {
    			sss_contribution = 630.00;
    		}else if(gross_monthly >= 13250) {
    			sss_contribution = 607.50;
    		}else if(gross_monthly >= 12750) {
    			sss_contribution = 585.00;
    		}else if(gross_monthly >= 12250) {
    			sss_contribution = 562.50;
    		}else if(gross_monthly >= 11750) {
    			sss_contribution = 540.00;
    		}else if(gross_monthly >= 11250) {
    			sss_contribution = 517.50;
    		}else if(gross_monthly >= 10750) {
    			sss_contribution = 495.00;
    		}else if(gross_monthly >= 10250) {
    			sss_contribution = 472.50;
    		}else if(gross_monthly >= 9750) {
    			sss_contribution = 450.00;
    		}else if(gross_monthly >= 9250) {
    			sss_contribution = 427.50;
    		}else if(gross_monthly >= 8750) {
    			sss_contribution = 405.00;
    		}else if(gross_monthly >= 8250) {
    			sss_contribution = 382.50;
    		}else if(gross_monthly >= 7750) {
    			sss_contribution = 360.00;
    		}else if(gross_monthly >= 7250) {
    			sss_contribution = 337.50;
    		}else if(gross_monthly >= 6750) {
    			sss_contribution = 315.00;
    		}else if(gross_monthly >= 6250) {
    			sss_contribution = 292.50;
    		}else if(gross_monthly >= 5750) {
    			sss_contribution = 270.00;
    		}else if(gross_monthly >= 5250) {
    			sss_contribution = 247.50;
    		}else if(gross_monthly >= 4750) {
    			sss_contribution = 225.00;
    		}else if(gross_monthly >= 4250) {
    			sss_contribution = 202.50;
    		}else if(gross_monthly >= 3750) {
    			sss_contribution = 180.00;
    		}else if(gross_monthly >= 3250) {
    			sss_contribution = 157.50;
    		}else sss_contribution = 135.00;
    	
    		return sss_contribution /4;
    }
   
    /**
     * Calculates the PhilHealth contribution.
     * Returns the calculated PhilHealth contribution.
     */
    public static double philhealthCalculation() {
    	double philhealth_contribution;
    		
    	if(actualMonthlyGrossSalary() >= 59999.99) {
    		philhealth_contribution = 1800;
    	}else if(actualMonthlyGrossSalary() >= 10000.01) {
    		philhealth_contribution = actualMonthlyGrossSalary() * 0.03;
    	}else philhealth_contribution = 300;
    	
    	return philhealth_contribution /4;
    }
    
    /**
     * Calculates the Pag-IBIG contribution.
     * Returns the calculated Pag-IBIG contribution.
     */
    public static double pagibigCalculation() {
        double grossSalary = actualMonthlyGrossSalary();
        double pagibigContribution = 0;

        if (grossSalary > 1500) {
            pagibigContribution = Math.max(grossSalary * 0.02, 100);
        } else if (grossSalary >= 1000) {
            pagibigContribution = Math.max(grossSalary * 0.01, 100);
        }

        return pagibigContribution / 4;
    }
    
    /**
     * Calculates the total contribution by summing up the SSS, PhilHealth, and Pag-IBIG contributions.
     * Returns the total contribution.
     */
    public static double totalContribution() {
    	return sssCalculation() + philhealthCalculation() + pagibigCalculation();
    }
    
    /**
     * Calculates the taxable monthly income. 
     * Returns the calculated taxable monthly income.
     */
    public static double taxableMonthlyIncome() {
    	double gross_monthly = actualMonthlyGrossSalary()/4;
    	double sss_deduction = gross_monthly - sssCalculation();
    	double philhealth_deduction = sss_deduction - philhealthCalculation();
    	double pagibig_deduction = philhealth_deduction - pagibigCalculation();
    	    	
    	return pagibig_deduction;
    }
    
    /**
     * Calculates the withholding tax. 
     * Returns the calculated withholding tax.
     */
    public static double withholdingTax() {
        double tax_deduction;
        double excess_tax = 0;
        double income = taxableMonthlyIncome() * 4;
        
        if (income >= 666667) {
            excess_tax = income - 666667;
            tax_deduction = 200833.33 + (excess_tax * 0.35);
        } else if (income >= 166667 && income < 666667) {
            excess_tax = income - 166667;
            tax_deduction = 40833.33 + (excess_tax * 0.32);
        } else if (income >= 66667 && income < 166667) {
            excess_tax = income - 66667;
            tax_deduction = 10833 + (excess_tax * 0.30);
        } else if (income >= 33333 && income < 66667) {
            excess_tax = income - 33333;
            tax_deduction = 2500 + (excess_tax * 0.25);
        } else if (income >= 20833 && income < 33333) {
            excess_tax = income - 20833;
            tax_deduction = excess_tax * 0.20;
        } else {
            tax_deduction = 0;
        }
        
        return tax_deduction;
    }
    
}