package us.kpatrick;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * TICKETS CLASS IS USED FOR THE ACTIONS TAKEN ON A TICKET
 * @author kpatr
 * @version 1.0.0
 */
public class Tickets {
    public final static double LOST_TICKET_FEE = 25.00;
    private final double BASE_PARKING_FEE = 5.00;
    private final double MAX_PARKING_FEE = 15.00;
    private final static FileInput indata = new FileInput("parking.csv");

    public int ID;
    public String checkIn;
    public String checkOut;
    public boolean lostTicket;

    public double CheckIn_Total = -1;
    public int CheckIn_Count = -1;
    public double LostTicket_Total = -1;
    public int LostTicket_Count = -1;
    public int Pending_Count = -1;

    public int hrs = 0;
    /**
     * INITIAL CONSTRUCTOR OF THE CLASS - IT LITERALLY DOES NOTHING
     */
    Tickets() {

    }

    Tickets (int ID) {
        for (Tickets t : Main.tickets) {

            if (t.ID == ID) {
                this.ID = t.ID;
                this.checkIn = t.checkIn;
                this.checkOut = t.checkOut;
                this.lostTicket = t.lostTicket;
                break;
            }
        }
    }

    /**
     * CONSTRUCTOR TO ACCEPT ALL ELEMENTS TO BE USED TO LOAD OBJECTS INTO TICKET ARRAY LIST
     * @param ID ID OF THE TICKET
     * @param CheckIn CHECK IN TIME
     * @param CheckOut CHECK OUT TIME
     * @param LostTicket IF TICKET WAS LOST
     */
    Tickets(int ID, String CheckIn, String CheckOut, Boolean LostTicket)
    {
        this.ID = ID;
        this.checkIn = CheckIn;
        this.checkOut = CheckOut;
        this.lostTicket = LostTicket;

    }

    /**
     * RUN CALCULATION ON THE TICKETS AND SAVE TO BE USED IN A SUMMARY REPORT
     */
    public void CalculateTicketTotals() {
        CheckIn_Total = 0;
        CheckIn_Count = 0;
        LostTicket_Total = 0;
        LostTicket_Count = 0;
        Pending_Count = 0;

        for (Tickets t : Main.tickets) {

            if (!t.checkOut.equals("null")) {
                long mins = CustomDate.minuteDifference1(t.checkIn, t.checkOut);
                CheckIn_Total += CalculateFee(mins, t.lostTicket);
                CheckIn_Count++;
            } else {
                Pending_Count++;
            }
            if (t.lostTicket) {
                LostTicket_Total += LOST_TICKET_FEE;
                LostTicket_Count++;
            }
        }
    }

    /**
     * UPDATE THE TICKETS INTO AN ARRAY LIST
     * @return ARRAY LIST OF TICKETS
     */
    public static ArrayList<Tickets> GetTickets() {
        ArrayList<Tickets> tickets = new ArrayList();

        String line;
        String[] fields;
        while ((line = indata.fileReadLine()) != null) {
            fields = line.split(",");
            int ID = Integer.parseInt(fields[0]);
            String d1 = fields[1];
            String d2 = fields[2];
            Boolean lost = Boolean.parseBoolean(fields[3]);
            tickets.add(new Tickets(ID, d1, d2, lost));
        }
        indata.fileClose();
        return tickets;
    }

    /**
     * GET THE LAST TICKET ID FROM THE ARRAY LIST
     * @return LAST TICKET ID
     */
    private int LastTicketNum() {
        int ticketNum = 0;

        for (Tickets t : Main.tickets){
            if (t.ID > ticketNum) {
                ticketNum = (t.ID);
            }
        }

        return ticketNum;
    }

    /**
     * ADD A NEW TICKET TO THE ARRAY LIST AND RETURN THE TICKET ID
     * @return NEW TICKET ID
     */
    public int CreateNewTicket() {
        int ticketNum = this.LastTicketNum()+1;
        String checkInTime = CustomTime.MorningTime();
        Main.tickets.add(new Tickets(ticketNum, checkInTime, "null", false));
        //Main.tickets.add(new Tickets(ticketNum, LocalDateTime.now().toString(),null,false));
        return ticketNum;
    }

    /**
     * THIS METHOD WILL AUTOMATICALLY CREATE A CLOSE A NEW TICKET FOR TICKETS THAT WERE LOST AND CALCULATE FEE ACCORDINGLY
     * @return double RETURNS THE FEE FOR PARKING - LOST TICKETS HAVE A SPECIAL COST WHICH IS HANDLED WITHIN THE CALCULATION METHOD.  BE SURE TO SET lostTicket ON THE CalculateFee METHOD TO true
     */
    public int saveLostTicket() {
        int ticketNum = this.LastTicketNum() +1;
        Main.tickets.add(new Tickets(ticketNum, LocalDateTime.now().toString(), LocalDateTime.now().toString(),true));
        return ticketNum;
    }

    /**
     * DRIVER IS CHECKING OUT OF GARAGE WITH PROVIDED TICKET (ID) NUMBER
     * @return double OF THE BALANCE THAT THE DRIVER OWES BASED ON THE DURATION OF THEIR STAY
     */
    public double CloseTicket() {
        double balanceOwed = 0;
        this.checkOut = CustomTime.EveningTime();
        long mins = CustomDate.minuteDifference1(this.checkIn, this.checkOut);
        balanceOwed = CalculateFee(mins, this.lostTicket);
        for (Tickets t : Main.tickets) {
            if (t.ID == ID){
                t.checkOut = CustomTime.EveningTime();
                break;
            }
        }
        return balanceOwed;
    }

    /**
     * CALCULATE THE FEE FOR DURATION OF THE VEHICLES STAY.  IF lostTicket IS TRUE THEN THE VALUE IN minutes IS IRRELEVANT
     * @param minutes long TOTAL MINUTES VEHICLE STAYED ON PREMISES
     * @param lostTicket boolean INDICATES IF THE TICKET WAS LOST.
     * @return double TOTAL COST OF PARKING BASED ON IF TICKET WAS LOST OR TOTAL MINUTES PARKED
     */
    private double CalculateFee(long minutes, boolean lostTicket) {
        double fee = 0;
        if (lostTicket) {
            fee = LOST_TICKET_FEE;
        } else {
            fee = BASE_PARKING_FEE;

            if (minutes > 180) {
                long additional = 0;
                additional = (long) (Math.ceil((double)(minutes - 180) / 60));

                fee += additional;
            }
            if (fee > MAX_PARKING_FEE ) {
                fee = MAX_PARKING_FEE;
            }
        }
        CalculateHrs((int)minutes);
        return fee;
    }

    /**
     * CALCULATE HOURS STAYED
     * @param minutes provide minutes stayed
     */
    private void CalculateHrs(int minutes) {
        int hrs = (int) Math.ceil((double)minutes/60);
        this.hrs = hrs;
    }

    /**
     * WRITE THE TICKETS TO THE OUTPUT FILE
     */
    public static void SaveTickets() {
        FileOutput outData = new FileOutput("parking.csv");
        for (Tickets t : Main.tickets) {
            outData.fileWrite(t.ID + "," + t.checkIn + "," + t.checkOut + "," + t.lostTicket);
        }
        outData.fileClose();


    }
}
