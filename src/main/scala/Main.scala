package com.andy

import org.eclipse.jetty.ee10.servlet.{ServletContextHandler, ServletHolder}
import org.eclipse.jetty.server.{Server, ServerConnector}
import org.eclipse.jetty.util.thread.QueuedThreadPool
import org.springframework.web.context.ContextLoaderListener
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext
import org.springframework.web.servlet.DispatcherServlet


object Main {

  private val serverPort = 8080

  def main(args: Array[String]): Unit = {
    println("Started server at port: " + serverPort)

    // Create and configure a ThreadPool.// Create and configure a ThreadPool.
    val threadPool = new QueuedThreadPool()
    threadPool.setName("jetty-thread-pool")

    // Create a Server instance.
    val server = new Server(threadPool)

    // Create a ServerConnector to accept connections from clients.
    val connector = new ServerConnector(server)
    connector.setPort(serverPort)
    connector.setHost("localhost")

    // Add the Connector to the Server
    server.addConnector(connector)

    // Spring context
    val webAppContext = new AnnotationConfigWebApplicationContext()
    webAppContext.register(classOf[WebConfig])

    val servletContextHandler = new ServletContextHandler()
    servletContextHandler.setContextPath("/")
    servletContextHandler.addEventListener(new ContextLoaderListener(webAppContext))

    val dispatcherServlet = new DispatcherServlet(webAppContext)
    val servletHolder = new ServletHolder("dispatcher", dispatcherServlet)
    servletContextHandler.addServlet(servletHolder, "/app1/*")

    server.setHandler(servletContextHandler)

    // Start the Server to start accepting connections from clients.
    server.start()

    try {
      server.join()
    } catch {
      case ex: InterruptedException =>
        println("Program interrupted.")
        println("\nServer shutting down.\n")
        server.stop()
    }
  }

}
