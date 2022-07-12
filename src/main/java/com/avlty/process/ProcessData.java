package com.avlty.process;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProcessData {
	
	public static void main(String[] args) {

        Map<String, BufferedWriter> companyMap = new HashMap<>();

        Map<String, Integer> userVersionMap = new HashMap<>();

        Map<String, List<String>> userValuesMap = new HashMap<>();

        String userKey;

        Integer userVersion;

        //  List<List<String>> records = new ArrayList<>();

        BufferedWriter writer = null;
        try (BufferedReader br = new BufferedReader(new FileReader("input-files/test-data.csv"))) {
            String line;

            //Iterate each row in input/source text file and add to respective company output file
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                String companyName = values[3];
                System.out.println("Company Name is " + companyName);

                if (!companyMap.containsKey(companyName)) {
                    writer = Files.newBufferedWriter(Paths.get("output-files/" + companyName + ".csv"));
                    companyMap.put(companyName, writer);
                }

                userKey = values[0] + "~" + companyName;
                userVersion = Integer.valueOf(values[2]);
                if (!userVersionMap.containsKey(userKey)) {
                    userVersionMap.put(userKey, Integer.valueOf(userVersion));
                }

                if(userVersionMap.containsKey(userKey) && userVersion >= userVersionMap.get(userKey) ){
                    userValuesMap.put(userKey, Arrays.asList(values));
                }
            }

            for (var entry : userValuesMap.entrySet()) {
                System.out.println(entry.getKey() + "/" + entry.getValue());
                String companyNameFromMap = entry.getKey().split("\\~")[1];
                System.out.println("Company Name From Map is: " + companyNameFromMap);

                writer = companyMap.get(companyNameFromMap);
                writer.append(String.join(",", (entry.getValue())));
                writer.newLine();

            }

            //Iterate each key/value and close the BufferReader
            for (BufferedWriter writer1 : companyMap.values()) {
                writer1.close();
            }


        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

}
