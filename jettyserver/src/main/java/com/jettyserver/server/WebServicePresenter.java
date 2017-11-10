package com.jettyserver.server;

import com.jettyserver.config.ServlertConfig;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.observers.DefaultObserver;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Author：lhb on 2017/11/10 15:39
 * Mail：lihaibo@znds.com
 */

public class WebServicePresenter implements WebServoceConstract.IWebServicePresenter {

    private final int PORT = 8089;
    private Server server;

    WebServoceConstract.IWebServiceViewer viewer;

    public WebServicePresenter(WebServoceConstract.IWebServiceViewer viewer) {
        this.server = new Server(PORT);
        this.viewer = viewer;
    }

    @Override
    public void openJettyServer() {
        Observable
                .just("openJettyServer")
                .filter(new Predicate<String>() {
                    @Override
                    public boolean test(@NonNull String s) throws Exception {
                        return server != null;
                    }
                })
                .map(new Function<String, Boolean>() {
                    @Override
                    public Boolean apply(@NonNull String s) throws Exception {
                        ServletContextHandler contextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
                        contextHandler.setContextPath("/");
                        server.setHandler(contextHandler);
                        ServlertConfig.config(contextHandler);
                        server.start();
                        return true;
                    }
                })
                .observeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new DefaultObserver<Boolean>() {
                    @Override
                    public void onNext(@NonNull Boolean aBoolean) {
                        viewer.onOpenJettyServer(aBoolean);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        viewer.onOpenJettyServer(false);
                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }

    @Override
    public void closeJettyServer() {

        Observable
                .just("closeJettyServer")
                .filter(new Predicate<String>() {
                    @Override
                    public boolean test(@NonNull String s) throws Exception {
                        return server != null;
                    }
                })
                .map(new Function<String, Boolean>() {
                    @Override
                    public Boolean apply(@NonNull String s) throws Exception {
                        server.stop();
                        return null;
                    }
                })
                .observeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<Boolean>() {

                    @Override
                    public void onNext(@NonNull Boolean aBoolean) {
                        viewer.onCloseJettyServer(aBoolean);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        viewer.onCloseJettyServer(false);
                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }
}
