package cn.matrixaura.lepton.module;

import cn.matrixaura.lepton.module.combat.AutoClicker;
import cn.matrixaura.lepton.module.combat.NoClickDelay;
import cn.matrixaura.lepton.module.combat.Reach;
import cn.matrixaura.lepton.module.movement.BridgeAssist;
import cn.matrixaura.lepton.module.movement.Speed;
import cn.matrixaura.lepton.module.movement.Sprint;
import cn.matrixaura.lepton.module.visual.HUD;
import cn.matrixaura.lepton.module.visual.Tracers;
import cn.matrixaura.lepton.module.world.Timer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ModuleManager {

    private static final Logger logger = LogManager.getLogger("Modules");
    private final Map<Class<? extends Module>, Module> classModuleMap = new HashMap<>();

    public ModuleManager() {

        putModule(
                AutoClicker.class, NoClickDelay.class, Reach.class, // Combat
                BridgeAssist.class, Sprint.class, Speed.class, // Movement
                HUD.class, Tracers.class, // Visual
                Timer.class // World
        );

        logger.info("Loaded {} modules", classModuleMap.size());
    }

    @SafeVarargs
    private final void putModule(Class<? extends Module>... modules) {
        for (Class<? extends Module> module : modules) {
            try {
                classModuleMap.put(module, module.newInstance());
            } catch (Exception e) {
                logger.error("Failed to initialize modules: {}", module.getName());
            }
        }
    }

    public <T extends Module> T get(Class<T> clazz) {
        return (T) classModuleMap.getOrDefault(clazz, null);
    }

    public Collection<Module> get() {
        return classModuleMap.values();
    }

    public <T extends Module> T get(String name) {
        if (classModuleMap.values().stream().anyMatch(it -> it.getName().equals(name)))
            return (T) classModuleMap.values().stream()
                    .filter(it -> it.getName().equals(name))
                    .findAny()
                    .get();
        return null;
    }
}
