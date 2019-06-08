package utils

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.scala.DefaultScalaModule

object JsonObjectMapper {

  val mapper = new ObjectMapper
  mapper.registerModule(DefaultScalaModule)
}
