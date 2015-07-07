package com.bentyn.traincoll.simulator;

import java.util.ArrayList;
import java.util.List;

import javax.xml.datatype.XMLGregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;

import com.bentyn.traincoll.commons.communication.Message;
import com.bentyn.traincoll.commons.communication.MessageType;
import com.bentyn.traincoll.commons.data.TrainData;
import com.bentyn.traincoll.simulator.gpx.schema.GpxType;
import com.bentyn.traincoll.simulator.gpx.schema.TrksegType;
import com.bentyn.traincoll.simulator.gpx.schema.WptType;
import com.bentyn.traincoll.commons.utils.GeoUtils;

public class TrainThread implements Runnable{

	private GpxType gpx ;
	private String trainId;
	private List<SimulationData> simulations = new ArrayList<>();
	private int timeSpeed=1;
	private TrainEndpoint  endpoint;
	private volatile boolean stop;

	public TrainThread(GpxType gpx,String trainId, int timeSpeed,TrainEndpoint  endpoint) {
		super();
		this.gpx = gpx;
		this.trainId = trainId;
		this.timeSpeed = timeSpeed;
		this.endpoint = endpoint;
	}
	
	@Override
	public void run() {
		prepareData();
		for(SimulationData sim:simulations){
			System.out.println(sim);
		}

		System.out.println("\n Starting simulation\n");
		int i=0;
		try {
		while ( !stop){
			SimulationData sim = simulations.get(i);
			//setCurrent timestamp
			sim.getTrainData().setTimestamp(System.currentTimeMillis());
			endpoint.getMessageHandler().sendPositionUpdate(sim.getTrainData());
			System.out.println(sim);
			i= (i>=simulations.size()-1) ? 0 :i+1 ;
			Thread.sleep(sim.getTimeDiff());
		}
		
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void prepareData(){
		List<TrksegType> trackSegments= gpx.getTrk().get(0).getTrkseg();
		
		XMLGregorianCalendar firstTimestamp= trackSegments.get(0).getTrkpt().get(0).getTime();
		long startTime= firstTimestamp.toGregorianCalendar().getTimeInMillis();
		
		for (TrksegType segment:trackSegments){
			
			for (int i=0; i< segment.getTrkpt().size(); i++ ){
				WptType point = segment.getTrkpt().get(i);
				
				double speed,heading,lat,lon;
				speed=heading=0;
				long timestamp= point.getTime().toGregorianCalendar().getTimeInMillis();
				long timeDiff=0;
				
				lat=point.getLat().doubleValue();
				lon=point.getLon().doubleValue();
				
				if (i != segment.getTrkpt().size()-1){
					WptType nextPoint=segment.getTrkpt().get(i+1);
					double nextLat=nextPoint.getLat().doubleValue();
					double nextLon=nextPoint.getLon().doubleValue();
					double distance=GeoUtils.distFrom(lat, lon,nextLat,nextLon );
					timeDiff=nextPoint.getTime().toGregorianCalendar().getTimeInMillis()-timestamp;
					speed=GeoUtils.speed(distance, timeDiff);
					heading=GeoUtils.heading(lat, lon,nextLat,nextLon);
				}
				
				
				TrainData td= new TrainData();
				td.setId(trainId);
				td.setLatitude(lat);
				td.setLongitude(lon);
				td.setSpeed(speed);
				td.setHeading(heading);
				
				SimulationData sim = new SimulationData();
				sim.setTrainData(td);
				sim.setTimeDiff( timeDiff/timeSpeed);
				simulations.add(sim);
				
			}
		}
	}

	public boolean isStop() {
		return stop;
	}

	public void setStop(boolean stop) {
		this.stop = stop;
	}
	
}
