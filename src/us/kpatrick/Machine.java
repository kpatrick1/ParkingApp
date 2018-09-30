package us.kpatrick;

/**
 * MACHINE CLASS USED AS A PRECURSOR TO DETERMINE WHICH MACHINE IS GOING TO BE USED.
 * @author kpatr
 * @version 1.0.0
 */
public class Machine {

    /**
     * THIS METHOD ALLOWS THE USER TO DETERMINE WHICH MACHINE THEY WILL BE USING
     * @return MachineType IS RETURN INDICATING IF IT WAS A CHECK_IN OR CHECK_OUT MACHINE
     */
    public static MachineType SetMachineType() {
        int ans = 0;
        System.out.println("BEST VALUE PARKING GARAGE");
        System.out.println("=========================");
        System.out.println("1 - Check In Machine");
        System.out.println("3 - Check Out Machine");

        while (ans != 1 && ans != 3) {
            ans = ReadUserInput.GetInt_Positive();
            if (ans != 1 && ans !=3) {
                System.out.println("That was an invalid number.  Please Try again.");
            }
        }

        if (ans == 1) {
            return MachineType.CHECK_IN;
        } else {
            return MachineType.CHECK_OUT;
        }


    }


}
