/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
 package org.apache.usergrid.scenarios

import io.gatling.core.Predef._
import io.gatling.http.Predef._
 import org.apache.usergrid.settings.{Headers, Settings}
 import scala.concurrent.duration._

/**
 *
 * Creates a new no-op notifier
 *
 *
 * Expects:
 *
 * authToken The auth token to use when creating the application
 * orgName The name of the org
 * appName The name of the app
 *
 * Produces:
 *
 * notifierName The name of the created notifier
 *
 */
object NotifierScenarios {
  
  val notifier = Settings.pushNotifier
  val provider = Settings.pushProvider
  val org = Settings.org
  val app = Settings.app

  /**
   * Create a notifier
   */
  val createNotifier = exec(
      session => {
        session.set("notifier", notifier)
        session.set("provider", provider)
      }
    )

    .exec(http("Create Notifier")
    .post(Settings.baseAppUrl+"/notifiers")
    .headers(Headers.jsonAuthorized)
    .body(StringBody("{\"name\":\"" + notifier + "\",\"provider\":\"" + provider + "\"}"))
    .check(status.in(200 to 400)))

  val checkNotifier = exec(http("Get Notifier")
    .get(Settings.baseAppUrl+"/notifiers/"+notifier)
    .headers(Headers.jsonAuthorized)
    .check(status.is(200),status.saveAs("notifierStatus"))
  )


}
