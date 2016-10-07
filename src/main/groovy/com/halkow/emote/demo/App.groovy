package com.halkow.emote.demo

import com.halkow.emote.api.EmoteClient
import com.halkow.emote.api.EmoteDevice


class App {
  static void main(String[] args) {

    // API client
    def client = new EmoteClient()

    // register discovery event handler
    client.discovered = { EmoteDevice device ->
      println "device ${device.name} discovered at ${device.socketAddress}"

      // register for volume change events
      device.volumeChanged = { volume ->
        println "device ${device} volume changed to ${volume}"
      }
    }

    // discover devices
    client.discover()

    // wait for user input
    System.in.read()

    // stop receiving events
    client.stop()
  }
}
