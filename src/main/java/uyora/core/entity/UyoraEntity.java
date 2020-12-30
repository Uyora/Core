package uyora.core.entity;

import org.bukkit.entity.Entity;

public abstract class UyoraEntity {

	protected final Entity baseEntity;


	public UyoraEntity(Entity entity) {
		baseEntity = entity;
	}

}
