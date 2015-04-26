package com.bentyn.traincoll.simulator.gpx;

import java.io.File;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.bentyn.traincoll.simulator.gpx.schema.GpxType;
import com.bentyn.traincoll.simulator.gpx.schema.TrkType;

public class GpxParser {

	public static GpxType fromFile(String fileDir){
		GpxType gpx = null;
	    try {
	        JAXBContext jc = JAXBContext.newInstance("com.bentyn.traincoll.simulator.gpx.schema");
	        Unmarshaller unmarshaller = jc.createUnmarshaller();
	        JAXBElement<GpxType> root = (JAXBElement<GpxType>)unmarshaller .unmarshal(new File(fileDir));
	        gpx = root.getValue();
	    } catch(JAXBException ex) {
	       ex.printStackTrace();
	    }

	    List<TrkType> tracks = gpx.getTrk();
	    for(TrkType track : tracks) {
	        System.out.println(track.getName());
	    }
	    return gpx;
	}
	
}
