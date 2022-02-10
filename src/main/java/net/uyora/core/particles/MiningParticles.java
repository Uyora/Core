package net.uyora.core.particles;

import net.uyora.core.Core;
import org.bukkit.Color;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.entity.Entity;


public class MiningParticles {

    private Core main;

    public MiningParticles(Core main){
        this.main = main;
    }


    public void hitNode(Entity node){
        World world = node.getWorld();
        world.spawnParticle(Particle.REDSTONE,node.getLocation().add(0,2,0),6,.5, .5,.5,0, new Particle.DustOptions(Color.GRAY, 1));
    }

    public void breakNode(Entity node){
        World world = node.getWorld();
        world.spawnParticle(Particle.REDSTONE,node.getLocation().add(0,2,0),6,.15,3,.15,0, new Particle.DustOptions(Color.GRAY, 2));
    }


}
