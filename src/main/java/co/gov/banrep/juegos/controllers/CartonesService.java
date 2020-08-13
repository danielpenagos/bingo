package co.gov.banrep.juegos.controllers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class CartonesService {

	private List<Integer[][]> cartonesArray = new ArrayList<Integer[][]>();
	
	@Value("${json.config.cartones}")
    String fileCartones;
	
	@PostConstruct
    public void init() throws JsonParseException, JsonMappingException, IOException {
		
        ObjectMapper jsonMapper = new ObjectMapper();
        

        File jsonFile = new File(fileCartones);
        ArrayList cartones = jsonMapper.readValue(jsonFile, ArrayList.class);
        
        /*
         * 
        ClassLoader classLoader = new CartonesService().getClass().getClassLoader();
        String fileName = "cartones.js";
        File jsonFile = new File(classLoader.getResource(fileName).getFile());
        ArrayList cartones = jsonMapper.readValue(jsonFile, ArrayList.class);
        */
        for(Object obj : cartones) {        	
        	JSONArray array = new JSONArray(obj.toString());
        	Integer[][]carton = new Integer[5][5];
        	for(int i=0; i<array.length();i++) {
        		JSONArray subArreglo = array.getJSONArray(i);
        		for(int j=0;j<subArreglo.length();j++) {
        			carton[j][i]=subArreglo.getInt(j);
        		}
        	}
        	cartonesArray.add(carton);
        }
        
        for(Integer[][] c: cartonesArray) {
			for(int i=0;i<5;i++) {
				StringBuffer sb = new StringBuffer();
				for(int j=0;j<5;j++) {
					sb.append(c[i][j]);
					sb.append(",");
				}
			}
		}
		
    }

	public List<Integer[][]> getCartonesArray() {
		return cartonesArray;
	}
}
