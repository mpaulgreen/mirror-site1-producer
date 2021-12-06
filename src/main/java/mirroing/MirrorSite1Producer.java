/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements. See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package mirroing;

import org.apache.qpid.jms.JmsConnectionFactory;

import javax.jms.*;

/**
 * On this example, two brokers are mirrored.
 * Everything that is happening on the first broker will be mirrored on the second, and Vice Versa.
 */
public class MirrorSite1Producer {

    public static void main(final String[] args) throws Exception {
        ConnectionFactory cfServer0 = new JmsConnectionFactory("amqp://192.168.2.12:5772");

        try (Connection connection = cfServer0.createConnection()) {
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Queue queue = session.createQueue("exampleQueue");
            MessageProducer producer = session.createProducer(queue);

            for (int i = 0; i < 100; i++) {
                producer.send(session.createTextMessage("Message " + i));
            }
        }
    }
}
