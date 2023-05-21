package wtf.agent.client.module.visual;

import wtf.agent.client.listener.bus.Listener;
import wtf.agent.client.listener.events.render.EventRender3D;
import wtf.agent.client.module.Category;
import wtf.agent.client.module.Module;
import wtf.agent.client.util.math.Vec3D;
import wtf.agent.inject.asm.wrapper.impl.entity.EntityPlayerWrapper;

import java.util.List;

import static org.lwjgl.input.Keyboard.KEY_V;
import static org.lwjgl.opengl.GL11.*;

public class Tracers extends Module {
    public Tracers() {
        super("Tracers", "Draws lines to entities", Category.VISUAL);

        getBind().setKey(KEY_V);
    }

    @Listener
    public void onRender3D(EventRender3D event) {
        List<EntityPlayerWrapper> playerEntities = mc.getWorld().getLoadedPlayers();
        if (playerEntities == null || playerEntities.isEmpty()) return;

        for (EntityPlayerWrapper playerEntity : playerEntities) {
            double x = playerEntity.getX() - mc.getRenderManager().getRenderPosX();
            double y = playerEntity.getY() - mc.getRenderManager().getRenderPosY();
            double z = playerEntity.getZ() - mc.getRenderManager().getRenderPosZ();

            glPushMatrix();

            glDisable(GL_DEPTH_TEST);
            glDisable(GL_TEXTURE_2D);

            glEnable(GL_BLEND);
            glBlendFunc(770, 771);

            glEnable(GL_LINE_SMOOTH);
            glHint(GL_LINE_SMOOTH_HINT, GL_NICEST);
            glLineWidth(2.0f);

            glLoadIdentity();
            mc.getEntityRenderer().orientCamera(event.getPartialTicks());

            float distance = (float) (mc.getPlayer().getDistance(playerEntity) / 20.0);
            glColor4f(2.0f - distance, distance, 0.0f, 1.0f);

            glBegin(GL_LINES);
            {
                Vec3D eyes = mc.getPlayer().getVectorForRotation();
                glVertex3d(eyes.getX(), eyes.getY() + mc.getPlayer().getEyeHeight(), eyes.getZ());
                glVertex3d(x, y, z);
            }
            glEnd();

            glEnable(GL_DEPTH_TEST);
            glEnable(GL_TEXTURE_2D);

            glPopMatrix();

        }
    }
}
