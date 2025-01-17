package cn.matrixaura.lepton.server;

import cn.matrixaura.lepton.server.handlers.*;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

public class LeptonHttpServer {

    public static void start() throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress("localhost", 8080), 0);
        server.createContext("/", new RootHttpHandler());
        server.createContext("/api/setStatus", new StatusHttpHandler());
        server.createContext("/api/modulesList", new ModulesHttpHandler());
        server.createContext("/api/updateModulesInfo", new ModuleInfoHttpHandler());
        server.createContext("/api/categoriesList", new CategoriesHttpHandler());
        server.createContext("/api/getModuleSetting", new SettingsHttpHandler());
        server.createContext("/api/setModuleSettingValue", new SetSettingHttpHandler());
        server.createContext("/api/setBind", new BindHttpHandler());
        server.setExecutor(Executors.newFixedThreadPool(10));
        server.start();
    }

}
