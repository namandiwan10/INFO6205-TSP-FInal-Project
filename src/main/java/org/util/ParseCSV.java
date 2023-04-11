package org.util;

import org.structs.Node;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Double.parseDouble;

public class ParseCSV {
    public static List<Node> nodeList= new ArrayList<>();
    public List<Node> ParseCSV() {

        String filePath = "src/main/resources/Datasets/info6205.spring2023.teamproject.csv";
        //String filePath = "src/main/resources/Datasets/crimeSample.csv";
        List<String[]> parsedRows = parseCSVFile(filePath, 2, 586);



        // Example usage: Print the parsed rows
        for (String[] row : parsedRows) {
                //System.out.println(row[0]+"\t"+row[1] + "\t" + row[2]);
                Node node=new Node(row[0], parseDouble(row[1]), parseDouble(row[2]));
                nodeList.add(node);
                //System.out.println("Entered node: "+node);
           //System.out.println();
        }
       /* System.out.println("Iterating loop...");
        for (Node node : nodeList) {
            System.out.println(node);
        }*/
        return nodeList;

    }

    public static List<String[]> parseCSVFile(String filePath, int startRow, int endRow) {
        List<String[]> parsedRows = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            int rowCount = 0;
            while ((line = br.readLine()) != null) {
                rowCount++;
                if (rowCount >= startRow && rowCount <= endRow) {
                    String[] cells = line.split(",");
                    //System.out.println("yoo..."+Arrays.toString(cells));
                    parsedRows.add(cells);
                }
                if (rowCount > endRow) {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return parsedRows;
    }
}
