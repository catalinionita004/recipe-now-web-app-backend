package com.example.recipenowwebappbackend.services;

import com.example.recipenowwebappbackend.models.Interaction;
import com.example.recipenowwebappbackend.repositories.InteractionRepository;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Service
public class ImportService {

    @Autowired
    InteractionRepository interactionRepository;

//    public void importData() {
//
//        String csvFile = "C:\\Users\\catal\\Documents\\LICENTA\\untitled1\\src\\main\\resources\\RAW_interactions2.csv";
//        try (
//                CSVParser parser = new CSVParser(new FileReader(new File(csvFile)), CSVFormat.DEFAULT)) {
//
//            // iterate over each record in the CSV file
//            for (CSVRecord record : parser) {
//                if (!record.get(0).equals("user_id")) {// access the values in each record by column index
//                    String value1 = record.get(0);
//                    String value2 = record.get(1);
//                    String value3 = record.get(2);
//                    String value4 = record.get(3);
//                    String value5 = record.get(4);
//
//                    Interaction interaction = new Interaction();
//                    interaction.setUserId(Long.parseLong(value1));
//                    interaction.setRecipeId(Long.parseLong(value2));
//                    interaction.setDate(new SimpleDateFormat("yyyy-dd-MM").parse(value3));
//                    interaction.setRating(Integer.parseInt(value4));
//                    interaction.setReview(value5);
//                    // System.out.println(interaction);
//
//                    interactionRepository.save(interaction);
//                    // do something with the values, e.g. print them to console
//                    //System.out.println(value1 + " | " + value2 + " | " + value3 + " | " + value4 + " | " + value5);
//                }
//            }
//
//        } catch (IOException | ParseException e) {
//            e.printStackTrace();
//        }
//    }
}
