/*
 * Copyright (c) 2018, The Modern Way. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.themodernway.server.integration.support.spring;

import java.util.Map;

import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.PollableChannel;
import org.springframework.messaging.SubscribableChannel;

import com.themodernway.server.core.json.JSONObject;
import com.themodernway.server.core.support.spring.IServerContext;

public interface ICoreIntegrationContext extends IServerContext
{
    public ICoreIntegrationProvider getCoreIntegrationProvider();

    public MessageChannel getMessageChannel(String name);

    public SubscribableChannel getSubscribableChannel(String name);

    public PollableChannel getPollableChannel(String name);

    public PublishSubscribeChannel getPublishSubscribeChannel(String name);

    public boolean publish(String name, JSONObject message);

    public boolean publish(String name, JSONObject message, long timeout);

    public boolean publish(String name, JSONObject message, Map<String, ?> headers);

    public boolean publish(String name, JSONObject message, Map<String, ?> headers, long timeout);

    public <T> boolean publish(String name, Message<T> message);

    public <T> boolean publish(String name, Message<T> message, long timeout);
}
