package edu.ship.engr.communication;

import edu.ship.engr.communication.handlers.*;
import edu.ship.engr.messages.Message;

public class MessageHandlerSet {
    public MessageHandlerSet() throws ClassNotFoundException {
    }

    private static class HandlerMapping {
        Class<?> objectType;
        Handler handler;

        public HandlerMapping(Class<?> objectType, Handler handler) {
            this.objectType = objectType;
            this.handler = handler;
        }
    }

    private final HandlerMapping[] handlerMappings = {
            new HandlerMapping(Class.forName("edu.ship.engr.messages.MovementMessage"), new MovementMessageHandler()),
            new HandlerMapping(Class.forName("edu.ship.engr.messages.KillMessage"), new KillMessageHandler()),
            new HandlerMapping(Class.forName("edu.ship.engr.messages.ShootMessage"), new ShootMessageHandler()),
            new HandlerMapping(Class.forName("edu.ship.engr.messages.ScoreMessage"), new ScoreMessageHandler()),
    };


    public void process(Message<?> msg) {
        HandlerMapping mapping = searchForMapping(msg);
        mapping.handler.processMessage(msg);
    }

    private HandlerMapping searchForMapping(Message<?> msg) {
        int i = 0;
        while ((i < handlerMappings.length) &&
                !handlerMappings[i].objectType.equals(msg.getObjectType())) {
            i++;
        }
        if (i == handlerMappings.length) {
            throw new RuntimeException("No handler found for " + msg);
        }
        return handlerMappings[i];
    }

}
