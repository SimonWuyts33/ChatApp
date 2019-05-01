package controller.handlers;

import domain.PersonService;

public class RequestHandlerFactory {

    public RequestHandler getRequestHandler(String key, PersonService model) {
        return createHandler(key, model);
    }

    private RequestHandler createHandler(String handlerName, PersonService model) {
        RequestHandler handler;
        String className = "undefined";
        try {

            className = "controller.handlers." + handlerName + "Handler";
            Class<?> handlerClass = Class.forName(className);
            Object handlerObject = handlerClass.newInstance();
            handler = (RequestHandler) handlerObject;
            handler.setModel(model);
        } catch (Exception e) {
            throw new RuntimeException("Deze pagina bestaat niet!!!! [" + className + "]");
        }
        return handler;
    }


}
