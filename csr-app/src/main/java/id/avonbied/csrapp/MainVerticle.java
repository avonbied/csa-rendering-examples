package id.avonbied.csrapp;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.StaticHandler;

public class MainVerticle extends AbstractVerticle {
	public static int calls = 0;

	public List<ExampleObject> generateValues(ExampleObject[] objects) {
		for (int i = 0; i < objects.length; i += 1) {
			objects[i].update(i + (MainVerticle.calls * 10));
		}
		
		MainVerticle.calls += 1;
		return Arrays.asList(objects);
	}
	
	@Override
	public void start(Promise<Void> startPromise) throws Exception {
		
		ExampleObject[] objects = Stream.generate(() -> new ExampleObject())
			.limit(10)
			.toArray(ExampleObject[]::new);

		Router router = Router.router(vertx);

		router.get().handler(StaticHandler.create().setIndexPage("index.html"));
		router.get("/hello").respond(ctx -> ctx.response()
			.putHeader("content-type", "text/plain")
			.end("Hello from Vert.x!"));

		router.get("/data").respond(ctx -> ctx
			.response()
			.putHeader("Content-Type", "application/json")
			.end("["+generateValues(objects).stream().map(ExampleObject::toJsonString).collect(Collectors.joining(", "))+"]"));

		vertx.createHttpServer()
			.requestHandler(router)
			.listen(8080, http -> {
				if (http.succeeded()) {
					startPromise.complete();
					System.out.println("HTTP server started on port 8080");
				} else {
					startPromise.fail(http.cause());
				}
			});
	}
}
