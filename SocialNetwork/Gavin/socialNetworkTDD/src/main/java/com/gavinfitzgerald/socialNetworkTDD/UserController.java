package com.gavinfitzgerald.socialNetworkTDD;

import java.util.ArrayList;
import java.util.List;

public class UserController {
    private static List<String> timeline = new ArrayList<String>();

    public void post(String message){
        String newLine = "\n";
        UserController.timeline.add(message+newLine);
    }

    public String readTimeline() {
        StringBuilder formatTimeline = new StringBuilder();
        for (String message: timeline) {
            formatTimeline.append(message);
        }
        return formatTimeline.toString();
    }
}
