package uyora.core.quest;

import org.bukkit.util.Vector;
import uyora.core.character.CharacterData;
import uyora.core.character.UyoraCharacter;
import uyora.core.compass.ICompassTrackable;


import java.util.*;

/**
 * Static object for storing general quest info, NOT PROGRESS.
 */
public class Quest implements ICompassTrackable {

	// ID for registry
	private final String id;
	private final String name;
	private final Vector location;
	private String startStage;
	private final Map<String, QuestStage> stage = new HashMap<>();
	private final List<QuestRequirement> requirement = new ArrayList<>();
	private final List<QuestReward> reward = new ArrayList<>();

	// Book core objects (String is placeholder)
	private String startMonologue;
	private String endMonologue;

	public Quest(String id, String name, Vector location) {
		this.id = id;
		this.name = name;
		this.location = location;
	}

	public void rewardPlayer(UyoraCharacter character) {
		for(QuestReward r : reward) {
			r.reward(character);
		}
	}

	public boolean checkRequirements(CharacterData character) {
		for(QuestRequirement r : requirement) {
			if(!r.isQualified(character))
				return false;
		}
		return true;
	}

	// Getter Setter
	public void registerStage(String stageId, QuestStage questStage) {
		stage.put(stageId, questStage);
	}

	public void addRequirements(QuestRequirement... requirements) {
		Collections.addAll(requirement, requirements);
	}

	public void addRewards(QuestReward... rewards) {
		Collections.addAll(reward, rewards);
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getStartStage(){
		return startStage;
	}

	public void setStartStage(String id){
		this.startStage = id;
	}

	public QuestStage getStage(String id) {
		return stage.get(id);
	}

	// Compass

	@Override
	public String getTrackerIcon(Vector vector) {
		return " ";
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
