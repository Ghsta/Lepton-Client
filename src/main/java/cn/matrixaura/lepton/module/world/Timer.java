package cn.matrixaura.lepton.module.world;

import cn.matrixaura.lepton.listener.bus.Listener;
import cn.matrixaura.lepton.listener.events.player.EventUpdate;
import cn.matrixaura.lepton.module.Category;
import cn.matrixaura.lepton.module.Module;
import cn.matrixaura.lepton.module.ModuleInfo;
import cn.matrixaura.lepton.setting.Setting;
import org.lwjgl.input.Keyboard;

@ModuleInfo(name = "Timer", description = "Change your game speed", category = Category.World, key = Keyboard.KEY_G)
public class Timer extends Module {

    private Setting<Number> timer = setting("Timer", 2, 0, 10, 0.1);

    @Listener
    public void onUpdate(EventUpdate ignored) {
        mc.getTimer().setTimerSpeed(timer.getValue().floatValue());
    }

    @Override
    public void onDisable() {
        mc.getTimer().setTimerSpeed(1F);
    }
}
