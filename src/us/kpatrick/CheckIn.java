package us.kpatrick;

import java.text.NumberFormat;

/**
 * CHECKIN CLASS IS THE MACHINE THAT IS USED TO CHECK IN VEHICLES
 * @author kpatr
 * @version 1.0.0
 */
public class CheckIn {

    /**
     * STATIC CLASS TO ALLOW THE USER TO SELECT THEIR OPTION.
     * @return CheckinOptions Enum INDICATING CHECK_IN OR CLOSE_GARAGE
     */
    public static CheckinOptions CheckIn() {
        int ans = 0;
        System.out.println("BEST VALUE PARKING GARAGE");
        System.out.println("=========================");
        System.out.println("1 - Check In");
        System.out.println("3 - Close Garage");
        while (ans != 1 && ans != 3) {
            ans = ReadUserInput.GetInt_Positive();
            if (ans != 1 && ans !=3) {
                System.out.println("That was an invalid number.  Please Try again.");
            }
        }

        if (ans == 1) {
            return CheckinOptions.CHECK_IN;
        } else {
            return CheckinOptions.CLOSE_GARAGE;
        }
    }

    /**
     * ADD A NEW TICKET TO THE LIST FOR THE NEWLY CHECKED IN VEHICLE
     */
    public static void CheckVehicle() {
        Tickets ticket = new Tickets();
        int ticketNum = ticket.CreateNewTicket();
        System.out.println("Your vehicle has been checked In with ID: " + ticketNum);

    }

    /**
     * METHOD USED TO DISPLAY THE RESULTS OF CURRENT ACTIVITY TO DATE
     */
    public static void CloseGarage() {
        NumberFormat formatter = NumberFormat.getCurrencyInstance();

        Tickets t = new Tickets();
        t.CalculateTicketTotals();
        System.out.println("BEST VALUE PARKING GARAGE");
        System.out.println("=========================");
        System.out.println("Activity To Date");
        System.out.println("");
        System.out.println(formatter.format(t.CheckIn_Total) + " Collected from " + t.CheckIn_Count +  " Check Ins.");
        System.out.println(formatter.format(t.LostTicket_Total) + " Collected from " + t.LostTicket_Count + " Lost Tickets.");
        System.out.println(t.Pending_Count + " Tickets have not been Checked out.");
        System.out.println("");
        System.out.println(formatter.format((t.CheckIn_Total + t.LostTicket_Total)) + " was collected overall!");
    }
}
