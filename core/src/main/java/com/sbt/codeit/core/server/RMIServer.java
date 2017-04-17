package com.sbt.codeit.core.server;

import com.sbt.codeit.core.control.GameController;
import com.sbt.codeit.core.control.ServerListener;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by Roman on 08.02.2017.
 */
public class RMIServer {

    private GameController controller;

    public RMIServer(GameController controller) {
        this.controller = controller;
    }

    public void start() {
        try {
            GameController stub = (GameController) UnicastRemoteObject.exportObject(controller, 0);
            Registry registry = LocateRegistry.createRegistry(ServerListener.PORT);
            registry.bind(ServerListener.STUB_NAME, stub);
        } catch (RemoteException | AlreadyBoundException e) {
            e.printStackTrace();
        }
    }

}