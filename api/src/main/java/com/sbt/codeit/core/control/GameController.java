package com.sbt.codeit.core.control;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by Roman on 08.02.2017.
 */
public interface GameController extends Remote {

    void register(ServerListener serverListener, String name) throws RemoteException;
    void start(ServerListener serverListener) throws RemoteException;
    void stop(ServerListener serverListener) throws  RemoteException;
    void up(ServerListener serverListener) throws RemoteException;
    void down(ServerListener serverListener) throws RemoteException;
    void left(ServerListener serverListener) throws RemoteException;
    void right(ServerListener serverListener) throws RemoteException;
    void fire(ServerListener serverListener) throws RemoteException;

}
