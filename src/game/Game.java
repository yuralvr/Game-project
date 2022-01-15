package game;

import entity.Entity;
import render.RenderWorld;
import world.World;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Game implements KeyListener
{
    /** True if the game is currently "running", i.e. the game loop is looping */
    public boolean gameRunning = true;
    public RenderWorld renderWorld;
    public World world;
    public static final int tick_frequency = 20;

    public Game()
    {
        world = new World("World 1");
        renderWorld = new RenderWorld(world);
        Dimension ss = Toolkit.getDefaultToolkit().getScreenSize();
        renderWorld.setBounds(0,0, ss.width, ss.height);
        renderWorld.setIgnoreRepaint(true);
    }

    public void loop()
    {
        long lastLoopTime = System.currentTimeMillis();

        // keep looping round til the game ends
        while (gameRunning) {
            // work out how long its been since the last update, this
            // will be used to calculate how far the entities should
            // move this loop
            long delta = System.currentTimeMillis() - lastLoopTime;
            lastLoopTime = System.currentTimeMillis();

            // Get hold of a graphics context for the accelerated
            // surface and blank it out
            world.tick();
            renderWorld.render();

            // cycle round asking each entity to move itself
            /*if (!waitingForKeyPress) {
                for (int i=0;i<entities.size();i++) {
                    Entity entity = (Entity) entities.get(i);

                    entity.move(delta);
                }
            }

            // cycle round drawing all the entities we have in the game
            for (int i=0;i<entities.size();i++) {
                Entity entity = (Entity) entities.get(i);

                entity.draw(g);
            }

            // brute force collisions, compare every entity against
            // every other entity. If any of them collide notify
            // both entities that the collision has occured
            for (int p=0;p<entities.size();p++) {
                for (int s=p+1;s<entities.size();s++) {
                    Entity me = (Entity) entities.get(p);
                    Entity him = (Entity) entities.get(s);

                    if (me.collidesWith(him)) {
                        me.collidedWith(him);
                        him.collidedWith(me);
                    }
                }
            }

            // remove any entity that has been marked for clear up
            entities.removeAll(removeList);
            removeList.clear();

            // if a game event has indicated that game logic should
            // be resolved, cycle round every entity requesting that
            // their personal logic should be considered.
            if (logicRequiredThisLoop) {
                for (int i=0;i<entities.size();i++) {
                    Entity entity = (Entity) entities.get(i);
                    entity.doLogic();
                }

                logicRequiredThisLoop = false;
            }

            // if we're waiting for an "any key" press then draw the
            // current message
            if (waitingForKeyPress) {
                g.setColor(Color.white);
                g.drawString(message,(800-g.getFontMetrics().stringWidth(message))/2,250);
                g.drawString("Press any key",(800-g.getFontMetrics().stringWidth("Press any key"))/2,300);
            }*/

            // finally, we've completed drawing so clear up the graphics
            // and flip the buffer over


            // resolve the movement of the ship. First assume the ship
            // isn't moving. If either cursor key is pressed then
            // update the movement appropraitely
            /*ship.setHorizontalMovement(0);

            if ((leftPressed) && (!rightPressed)) {
                ship.setHorizontalMovement(-moveSpeed);
            } else if ((rightPressed) && (!leftPressed)) {
                ship.setHorizontalMovement(moveSpeed);
            }

            // if we're pressing fire, attempt to fire
            if (firePressed) {
                tryToFire();
            }*/

            // finally pause for a bit. Note: this should run us at about
            // 100 fps but on windows this might vary each loop due to
            // a bad implementation of timer
            try { Thread.sleep(1000 / Game.tick_frequency); } catch (Exception e) {}
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if(e.getKeyChar()=='d' || e.getKeyChar()=='D')
        {

            Entity ent =(Entity)world.getEntities().toArray()[0];
            ent.setPosX(ent.getPosX() + 1./Game.tick_frequency);
        }

        if(e.getKeyChar()=='w' || e.getKeyChar()=='W')
        {
            Entity ent =(Entity)world.getEntities().toArray()[0];
            if (ent.velY == 0)
            {
                ent.velY += 1;
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyChar()=='a' || e.getKeyChar()=='A')
        {
            System.out.println("Huy1");
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
