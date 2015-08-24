package com.bentyn.traincoll.commons.algorithms;

public class CDAlgorithmResponse {

	private boolean detected;
	private long collisionTimestamp;
	
	public boolean isDetected() {
		return detected;
	}
	public void setDetected(boolean detected) {
		this.detected = detected;
	}
	public long getCollisionTimestamp() {
		return collisionTimestamp;
	}
	public void setCollisionTimestamp(long collisionTimestamp) {
		this.collisionTimestamp = collisionTimestamp;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CDAlgorithmResponse [detected=").append(detected).append(", collisionTimestamp=").append(collisionTimestamp).append("]");
		return builder.toString();
	}
	
	
	
}
