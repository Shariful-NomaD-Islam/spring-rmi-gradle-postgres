package com.nomad.srgp.utility;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Shariful Islam
 */
public class SrgpUtils {

    public static void logMemoryStatus(String prefix) {
        DecimalFormat formatter = new DecimalFormat("#0000.0000");
        long total = Runtime.getRuntime().totalMemory();
        double totald = ((total * 1.0) / 1024) / 1024;
        long free = Runtime.getRuntime().freeMemory();
        double freed = ((free * 1.0) / 1024) / 1024;
    }

    public static List<String> asList(String csv) {
        if (csv == null) {
            return new ArrayList<String>();
        }

        return Arrays.asList(csv
                .replaceAll("^[,\\s]+", "")
                .replaceAll("[,\\s]+$", "")
                .replaceAll("[,\\s]+", ";").split(";"));
    }

}
