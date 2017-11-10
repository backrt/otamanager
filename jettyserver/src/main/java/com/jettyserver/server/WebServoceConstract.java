package com.jettyserver.server;

/**
 * Author：lhb on 2017/11/10 15:35
 * Mail：lihaibo@znds.com
 */

public class WebServoceConstract {

    public interface IWebServiceViewer{

        void onOpenJettyServer(boolean state);

        void onCloseJettyServer(boolean state);
    }

    public interface IWebServicePresenter{

        void openJettyServer();

        void closeJettyServer();
    }

}
