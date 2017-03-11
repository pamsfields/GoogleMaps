package com.PamFields;

import com.google.maps.ElevationApi;
import com.google.maps.GeoApiContext;
import com.google.maps.model.ElevationResult;
import com.google.maps.model.LatLng;
import com.google.maps.GeocodingApi;
import java.io.BufferedReader;
import java.io.FileReader;

public class Main {

    public static void main(String[] args) throws Exception {
	// write your code here
        String key = null;
        //Read key from file
        try(BufferedReader reader = new BufferedReader(new FileReader("key"))){
            key = reader.readLine();
            //System.out.println(key); //just checking...
        } catch (Exception ioe){
            System.out.println("No key file found, or could not read key. Please verify key.txt present");
            System.exit(-1); //quit program - need to fic before continuing
        }
        GeoApiContext context = new GeoApiContext().setApiKey(key);
        LatLng mctcLatLng = new LatLng(44.973074, -93.283356);
        ElevationResult[] results = ElevationApi.getByPoints(context, mctcLatLng).await();

        if(results.length >=1){
            ElevationResult mctcElevation = results[0];
            System.out.println("The elevation of MCTC above sea level is " + mctcElevation.elevation +" meters");
            System.out.println(String.format("The elevation of MCTC above sea level is %.2f meters.", mctcElevation.elevation));
        }

    }
}
