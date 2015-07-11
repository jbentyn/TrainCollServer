package com.bentyn.traincoll.commons.algorithms;

public class CDAlgorithmResponse {

	private boolean detected;
	private boolean imminent;
	private long collisionTimestamp;
	
	public boolean isDetected() {
		return detected;
	}
	public void setDetected(boolean detected) {
		this.detected = detected;
	}
	public boolean isImminent() {
		return imminent;
	}
	public void setImminent(boolean imminent) {
		this.imminent = imminent;
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
		builder.append("CDAlgorithmResponse [detected=").append(detected).append(", imminent=").append(imminent).append(", collisionTimestamp=").append(collisionTimestamp).append("]");
		return builder.toString();
	}
	
	
	
}
