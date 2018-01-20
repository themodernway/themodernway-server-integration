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

package com.themodernway.server.integration.support

import org.springframework.integration.channel.PublishSubscribeChannel
import org.springframework.messaging.Message
import org.springframework.messaging.MessageChannel
import org.springframework.messaging.PollableChannel
import org.springframework.messaging.SubscribableChannel

import com.themodernway.server.core.json.JSONObject
import com.themodernway.server.integration.support.spring.CoreIntegrationContextInstance
import com.themodernway.server.integration.support.spring.ICoreIntegrationContext

import groovy.transform.CompileStatic
import groovy.transform.Memoized

@CompileStatic
public trait CoreIntegrationTrait
{
    @Memoized
    public ICoreIntegrationContext getCoreIntegrationContext()
    {
        CoreIntegrationContextInstance.getCoreIntegrationContextInstance()
    }

    @Memoized
    public MessageChannel getMessageChannel(String name)
    {
        getCoreIntegrationContext().getMessageChannel(name)
    }

    @Memoized
    public SubscribableChannel getSubscribableChannel(String name)
    {
        getCoreIntegrationContext().getSubscribableChannel(name)
    }

    @Memoized
    public PollableChannel getPollableChannel(String name)
    {
        getCoreIntegrationContext().getPollableChannel(name)
    }

    @Memoized
    public PublishSubscribeChannel getPublishSubscribeChannel(String name)
    {
        getCoreIntegrationContext().getPublishSubscribeChannel(name)
    }

    public boolean publish(String name, JSONObject message)
    {
        getCoreIntegrationContext().publish(name, message)
    }

    public boolean publish(String name, JSONObject message, long timeout)
    {
        getCoreIntegrationContext().publish(name, message, timeout)
    }

    public boolean publish(String name, JSONObject message, Map<String, ?> headers)
    {
        getCoreIntegrationContext().publish(name, message, headers)
    }

    public boolean publish(String name, JSONObject message, Map<String, ?> headers, long timeout)
    {
        getCoreIntegrationContext().publish(name, message, headers, timeout)
    }

    public <T> boolean publish(String name, Message<T> message)
    {
        getCoreIntegrationContext().publish(name, message)
    }

    public <T> boolean publish(String name, Message<T> message, long timeout)
    {
        getCoreIntegrationContext().publish(name, message, timeout)
    }
}
