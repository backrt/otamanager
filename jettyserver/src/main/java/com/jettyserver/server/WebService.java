package com.jettyserver.server;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class WebService extends Service implements WebServoceConstract.IWebServiceViewer {

    WebServoceConstract.IWebServicePresenter iWebServicePresenter;

    @Override
    public void onCreate() {
        super.onCreate();
        iWebServicePresenter = new WebServicePresenter(this);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        startForeground(9999, new Notification());
        startServer();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        stopServer();
        super.onDestroy();
    }

    private void startServer() {
        iWebServicePresenter.openJettyServer();
    }


    private void stopServer() {
        iWebServicePresenter.closeJettyServer();
    }


    @Override
    public void onOpenJettyServer(boolean state) {
        System.out.println("openJettyServer :" + state);

        if (state) {
//            Toast.makeText(this, "jetty-server:open", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onCloseJettyServer(boolean state) {
        if (state) {
//            Toast.makeText(this, "jetty-server:close", Toast.LENGTH_LONG).show();
        }
    }
}
