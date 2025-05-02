package com.andy
package controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController


@RestController
class SampleController {

  @GetMapping(Array("/"))
  def default: String = {
    "Default response"
  }

  @GetMapping(Array("/hello"))
  def sayHello(): String = {
    "Hello from Spring MVC with Jetty!"
  }
}