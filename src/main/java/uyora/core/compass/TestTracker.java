package uyora.core.compass;

import org.bukkit.Location;
import org.bukkit.util.Vector;

public class TestTracker implements ICompassTrackable{

	private final Vector location;

	public TestTracker(Location location) {
		this.location = location.toVector();
	}

	@Override
	public String getTrackerIcon(Vector vector) {
		double dd = getDistanceSquared(vector);
		if(dd <= 400)
			return "I";
		else if(dd > 400 && dd <= 1600)
			return "J";
		else if(dd > 1600 && dd <= 3600)
			return "K";
		return "L";
	}

	@Override
	public double getDistanceSquared(Vector vector) {
		return vector.distanceSquared(location);
	}

	@Override
	public float getAngle(Vector vector) {
		Vector temp = location.clone().subtract(vector);
		return (float) Math.toDegrees(Math.atan2(-temp.getX(), temp.getZ())) + 180;
	}

}
