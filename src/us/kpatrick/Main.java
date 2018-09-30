package us.kpatrick;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * MAIN CLASS FOR THE PARKING APP
 * @author kpatr
 * @version 1.0.0
 */
public class Main {
    private static Scanner k = new Scanner(System.in);
    public static ArrayList<Tickets> tickets = new ArrayList<>();

    /**
     * MAIN STARTING POINT OF THE APPLICATION
     * @param args INITIAL ARGUMENTS SENT WHEN THE PROGRAM STARTS
     */
    public static void main(String[] args) {

        System.out.println( CustomTime.MorningTime() );
        System.out.println( CustomTime.EveningTime() );

        tickets = Tickets.GetTickets();
        boolean closeApp = false;
        int i = 0;
        while (!closeApp) {
            int ans = 0;
            //SELECT MACHINE
            MachineType machine = Machine.SetMachineType();
            if (machine == MachineType.CHECK_IN) {
                //CHECK IN MACHINE
                CheckinOptions checkin =  CheckIn.CheckIn();
                if (checkin == CheckinOptions.CHECK_IN) {
                    //CHECK IN
                    CheckIn.CheckVehicle();
                } else {
                    //CLOSE GARAGE
                    closeApp = true;
                }
            } else {
                //CHECK OUT MACHINE
                CheckoutOptions checkout = CheckOut.CheckOut();
                if (checkout == CheckoutOptions.CHECK_OUT) {
                    //CHECKOUT VEHICLE
                    CheckOut.CheckVehicle();
                } else {
                    //LOST TICKET
                    CheckOut.LostTicket();
                }
            }
        }
        // CALCULATE DAILY TOTALS
        CheckIn.CloseGarage();
        Tickets.SaveTickets();
    }
}