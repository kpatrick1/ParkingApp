package us.kpatrick;

import java.text.NumberFormat;
import java.time.LocalDateTime;

/**
 * CHECKOUT CLASS IS THE MACHINE THAT IS USED TO CHECK OUT VEHICLES
 * @author kpatr
 * @version 1.0.0
 */
public class CheckOut {

    /**
     * STATIC CLASS TO ALLOW THE USER TO SELECT THEIR OPTION.
     * @return CheckoutOptions Enum INDICATING CHECK_OUT OR LOST_TICKET
     */
    public static CheckoutOptions CheckOut() {
        int ans = 0;
        System.out.println("BEST VALUE PARKING GARAGE");
        System.out.println("=========================");
        System.out.println("1 - Check Out");
        System.out.println("3 - Lost Ticket");
        while (ans != 1 && ans != 3) {
            ans = ReadUserInput.GetInt_Positive();
            if (ans != 1 && ans !=3) {
                System.out.println("That was an invalid number.  Please Try again.");
            }
        }

        if (ans == 1) {
            return CheckoutOptions.CHECK_OUT;
        } else {
            return CheckoutOptions.LOST_TICKET;
        }
    }

    /**
     * PROMPTS THE USER FOR THEIR TICKET NUMBER AND USES THAT TO DETERMINE THEIR AMOUNT DUE.
     */
    public static void CheckVehicle() {
        NumberFormat formatter = NumberFormat.getCurrencyInstance();

        int ans = 0;
        while (ans == 0 ) {
            System.out.println("Please Enter your Ticket Number");
            ans = ReadUserInput.GetInt_Positive();
        }
        Tickets t = new Tickets(ans);
        double balance = t.CloseTicket();
        System.out.println("BEST VALUE PARKING GARAGE");
        System.out.println("=========================");
        System.out.println("Receipt for a vehicle ID " + t.ID);
        System.out.println("");
        System.out.println(t.hrs + " hours Parked: " + LocalDateTime.parse( t.checkIn).toLocalTime() + " - " + LocalDateTime.parse(t.checkOut).toLocalTime() );
        System.out.println(formatter.format(balance));

    }

    /**
     * METHOD USED WHEN AN OPERATOR LOOSES THEIR TICKET - WILL AUTOMATICALLY ADD A NEW TICKET AND CALCULATE THEIR NEW BALANCE AT THE CURRENT RATE OF LOST TICKETS
     */
    public static void LostTicket() {
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        Tickets t = new Tickets();
        int ticketnum = t.saveLostTicket();
        System.out.println("BEST VALUE PARKING GARAGE");
        System.out.println("=========================");
        System.out.println("Receipt for a vehicle ID " + ticketnum);
        System.out.println("");
        System.out.println("Lost Ticket");
        System.out.println(formatter.format(Tickets.LOST_TICKET_FEE));

    }

}
