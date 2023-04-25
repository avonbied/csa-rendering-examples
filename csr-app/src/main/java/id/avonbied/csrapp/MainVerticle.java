package id.avonbied.csrapp;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;

public class MainVerticle extends AbstractVerticle {

	@Override
	public void start(Promise<Void> startPromise) throws Exception {
		Router router = Router.router(vertx);

		router.get().handler(ctx -> {
			ctx.response()
				.putHeader("content-type", "text/plain")
				.end("Hello from Vert.x!");
		});

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
