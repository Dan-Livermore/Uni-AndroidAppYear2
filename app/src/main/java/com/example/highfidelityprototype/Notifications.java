package com.example.highfidelityprototype;

public class Notifications {
        //creates variables that can be accessed but not ammended from anywhere in the program
        static Boolean devicenotifications;
        static Boolean isClaimed;
        static Boolean afterupdate;

        //updates the static classes based on the output of the AdminNotifications window
        public static void updateNotifications(Boolean x, Boolean y, Boolean z){
                if (x == Boolean.TRUE){
                        devicenotifications = Boolean.TRUE;
                }
                if (y == Boolean.TRUE){
                        isClaimed = Boolean.TRUE;
                }
                if (z == Boolean.TRUE){
                        afterupdate = Boolean.TRUE;
                }

        }
}


