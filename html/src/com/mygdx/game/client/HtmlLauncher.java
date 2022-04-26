package com.mygdx.game.client;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.client.ws.EventListenerCallback;
import com.mygdx.game.client.ws.WebSocket;

import java.util.concurrent.atomic.AtomicBoolean;

public class HtmlLauncher extends GwtApplication {
        @Override
        public GwtApplicationConfiguration getConfig () {

                return new GwtApplicationConfiguration(true);

        }

        private native WebSocket getWebSocket(String url)
                /*-{
                        return new WebSocket(url);
                }-*/
        ;

        private native void log(Object obj)
                /*-{
                        console.log(obj);
                }-*/
        ;

        @Override
        public ApplicationListener createApplicationListener () {
                WebSocket client = getWebSocket("ws://localhost:8888/ws");
                AtomicBoolean once = new AtomicBoolean(false);

                MyGdxGame myGdxGame = new  MyGdxGame();

                EventListenerCallback callback = event -> {
                        if (!once.get()) {
                                client.send("Suhariki");
                                once.set(true);
                        }
                        log(event.getData());
                };
                client.addEventListener("open", callback);
                client.addEventListener("close", callback);
                client.addEventListener("error", callback);
                client.addEventListener("message", callback);

                return myGdxGame;
        }
}