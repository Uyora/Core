package net.uyora.core.particles;

import net.uyora.core.Core;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class LevelUp {

    private Player player;
    private Core main;
    private World world;

    public LevelUp(Player player, Core main){
        this.player = player;
        this.main = main;
        this.world = player.getWorld();
        LevelUpEffect();
    }

    public void LevelUpEffect(){
        Spiral();
    }

    public void Spiral(){
        new BukkitRunnable() {
            double t = 0;
            double r = 1;

            @Override

            public void run() {
                Location loc = player.getLocation();
                t = t + Math.PI/4.5;
                double x = r*Math.cos(t);
                double y = t/8;
                double z = r*Math.sin(t);
                loc.add(x,y,z);
                player.getWorld().spawnParticle(Particle.REDSTONE, loc, 1,0,0,0,0, new Particle.DustOptions(Color.YELLOW, 1.5f));
                if (t > Math.PI * 4) {
                    this.cancel();
                    Strike();
                } else {
                    loc.subtract(x,y,z);
                }
            }

        }.runTaskTimer(main, 0,1);
    }


    public void Strike(){
        new BukkitRunnable() {
            double t = player.getLocation().getY() + 6;

            @Override
            public void run() {
                Location loc = player.getLocation();

                t = t - .5;
                double y = t;
                loc.setY(y);
                player.getWorld().spawnParticle(Particle.REDSTONE, loc, 1,0,0,0,0, new Particle.DustOptions(Color.YELLOW, 1.5f));
                if (t <= player.getLocation().getY()) {
                    Circle();
                    this.cancel();
                }
            }
        }.runTaskTimer(main, 0, 1);

    }

    public void Circle() {
        int timer = 0;
        int particles = 50;
        double radius = 1.2d;
        int points = 30;
        for (int i = 0; i < points; i++) {
            double angle = 2 * Math.PI * i / points;
            Location point = player.getLocation().clone().add(radius * Math.sin(angle), 0.0d, radius * Math.cos(angle));
            player.getWorld().spawnParticle(Particle.REDSTONE, point, 1,0,0,0,0, new Particle.DustOptions(Color.YELLOW, 2));
            timer = i;
        }
    }


}
