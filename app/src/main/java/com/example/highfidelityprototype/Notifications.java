package com.example.highfidelityprototype;

public class Notifications {
        static Boolean devicenotifications;
        static Boolean isClaimed;
        static Boolean afterupdate;

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
