package com.monolito.kiros.search

import akka.actor.ActorSystem
import akka.event.Logging
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer
import akka.http.scaladsl.server.Directives
import akka.http.scaladsl.server.Route
import akka.stream.scaladsl.Source
import akka.stream.scaladsl.Sink
import akka.http.scaladsl.model.headers.RawHeader
import org.elasticsearch.node.NodeBuilder
import org.elasticsearch.common.settings.Settings

import java.io.File

object System {
  implicit val system = ActorSystem("Proxy")
  implicit val materializer = ActorMaterializer()
  implicit val ec = system.dispatcher

  trait LoggerExecutor extends BaseComponent {
    protected implicit val executor = system.dispatcher
    protected implicit val log = Logging(system, "app")
  }
}

object Main extends App with Config {
  import System._

  val tempDir = "/home/alex/estest"

  val elasticSearchNode = NodeBuilder
    .nodeBuilder()
    .local(false)
    .clusterName("kiros")
    .settings(
      Settings.builder()
        .put("http.enabled", "true")
        .put("index.number_of_shards", "1")
        .put("index.number_of_replicas", "0")
        .put("path.home", new File(tempDir).getAbsolutePath())).node();
  elasticSearchNode.start();

  val proxy = Route { context =>
    val request = context.request
    val flow = Http(system).outgoingConnection(request.uri.authority.host.address(), 9200)
    val handler = Source.single(context.request.withUri(request.uri.toRelative))
      .via(flow)
      .runWith(Sink.head)
      .flatMap(context.complete(_))
    handler
  }

  Http().bindAndHandle(handler = proxy, interface = httpConfig.interface, port = httpConfig.port)
}