package com.framework.core;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataProviderUtils {

    @DataProvider(name = "JsonDataProvider")
    public static Object[][] getDataFromJsonFile(Method mName) throws IOException, ParseException {
        String testName = mName.getName();
        System.out.println("testName is: " + testName);
        DataProviderArguments testArguments = mName.getAnnotation(DataProviderArguments.class);
        String testValues = testArguments.value();
        System.out.println(testValues);
        String testCaseName = testValues.split("=")[0];
        String testColumns = testValues.split("=")[1];
        List<String> fields = Arrays.asList(testColumns.split(","));
        System.out.println(fields);

        File testDataFile = new File(System.getProperty("user.dir") + "//src//test//resources//testData//" + EnvPropertiesLoader.environment + "-testData.json");
        FileReader fReader = new FileReader(testDataFile);

        JSONParser jParser = new JSONParser();
        Object object = jParser.parse(fReader);
        System.out.println(object);

        JSONObject testCaseObject = (JSONObject) object;
        JSONArray testSets = (JSONArray) testCaseObject.get(testCaseName);

        List<List<String>> listOfSets = new ArrayList<>();
        for (int i = 0; i < testSets.size(); i++) {
            List<String> subsetList = new ArrayList<String>();
            JSONObject subset = (JSONObject) testSets.get(i);
            for (int j = 0; j < fields.size(); j++) {
                String column = fields.get(j);
                String value = subset.get(column).toString();
                subsetList.add(value);
            }
            listOfSets.add(subsetList);

        }
        System.out.println(listOfSets);

        Object[][] two2D = new Object[listOfSets.size()][listOfSets.get(0).size()];
        for (int i = 0; i < listOfSets.size(); i++) {
            List<String> subset = listOfSets.get(i);//For getting sets of Row
            for (int j = 0; j < subset.size(); j++) { //For getting sets of columns for particular row
                two2D[i][j] = subset.get(j);
            }
        }

        return two2D;
    }
}
