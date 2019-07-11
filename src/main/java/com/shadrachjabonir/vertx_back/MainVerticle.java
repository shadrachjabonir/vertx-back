package com.shadrachjabonir.vertx_back;

import com.google.gson.Gson;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServer;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;

public class MainVerticle extends AbstractVerticle {

  @Override
  public void start(Future<Void> startFuture) throws Exception {
    HttpServer server = vertx.createHttpServer();
    Router router = Router.router(vertx);
    router.route().handler(BodyHandler.create());

    router.route(HttpMethod.POST,"/post").handler(routingContext  -> {

      JsonObject json = routingContext.getBodyAsJson();
      System.out.println(json);
      TestRequest testRequest = new TestRequest();
      testRequest.setName(json.getString("name"));

      TestReponse testReponse = new TestReponse();
      testReponse.setCode("01");
      testReponse.setMessage("Hai,"+ testRequest.getName()+" welcome to Techinlabs");
      Gson gson = new Gson();

      routingContext.response()
        .setChunked(true)
        .putHeader("content-type", "text/json")
        .write(gson.toJson(testReponse))
        .end();
    });

    server.requestHandler(router).listen(8080, http ->{
      if (http.succeeded()) {
        startFuture.complete();
        System.out.println("HTTP server started on port 8080");
      } else {
        startFuture.fail(http.cause());
      }
    });


//    vertx.createHttpServer().requestHandler(req -> {
//      TestReponse testReponse = new TestReponse();
//      testReponse.setCode("01");
//      testReponse.setMessage("Hai Techinlabs");
//      Gson gson = new Gson();
//      req.response()
//        .setChunked(true)
//        .putHeader("content-type", "text/plain")
//        .write(gson.toJson(testReponse))
//        .end();
//    }).listen(8080, http -> {
//      if (http.succeeded()) {
//        startFuture.complete();
//        System.out.println("HTTP server started on port 8080");
//      } else {
//        startFuture.fail(http.cause());
//      }
//    });
  }
}
