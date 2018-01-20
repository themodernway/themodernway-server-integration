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
import com.themodernway.server.core.support.spring.ServerContextInstance;

public class CoreIntegrationContextInstance extends ServerContextInstance implements ICoreIntegrationContext
{
    private static final CoreIntegrationContextInstance INSTANCE = new CoreIntegrationContextInstance();

    public static final CoreIntegrationContextInstance getCoreIntegrationContextInstance()
    {
        return INSTANCE;
    }

    protected CoreIntegrationContextInstance()
    {
    }

    @Override
    public ICoreIntegrationProvider getCoreIntegrationProvider()
    {
        return requireNonNull(getBeanSafely("CoreIntegrationProvider", ICoreIntegrationProvider.class), "CoreIntegrationProvider is null, initialization error.");
    }

    @Override
    public final MessageChannel getMessageChannel(final String name)
    {
        MessageChannel channel = getBeanSafely(requireNonNull(name), MessageChannel.class);

        if (null != channel)
        {
            return channel;
        }
        channel = getSubscribableChannel(name);

        if (null != channel)
        {
            return channel;
        }
        channel = getPublishSubscribeChannel(name);

        if (null != channel)
        {
            return channel;
        }
        return getPollableChannel(name);
    }

    @Override
    public final SubscribableChannel getSubscribableChannel(final String name)
    {
        return getBeanSafely(requireNonNull(name), SubscribableChannel.class);
    }

    @Override
    public final PollableChannel getPollableChannel(final String name)
    {
        return getBeanSafely(requireNonNull(name), PollableChannel.class);
    }

    @Override
    public PublishSubscribeChannel getPublishSubscribeChannel(final String name)
    {
        return getBeanSafely(requireNonNull(name), PublishSubscribeChannel.class);
    }

    @Override
    public final boolean publish(final String name, final JSONObject message)
    {
        return publish(requireNonNull(name), JSONMessageBuilder.createMessage(requireNonNull(message)));
    }

    @Override
    public final boolean publish(final String name, final JSONObject message, final long timeout)
    {
        return publish(requireNonNull(name), JSONMessageBuilder.createMessage(requireNonNull(message)), timeout);
    }

    @Override
    public final boolean publish(final String name, final JSONObject message, final Map<String, ?> headers)
    {
        return publish(requireNonNull(name), JSONMessageBuilder.createMessage(requireNonNull(message), requireNonNull(headers)));
    }

    @Override
    public final boolean publish(final String name, final JSONObject message, final Map<String, ?> headers, final long timeout)
    {
        return publish(requireNonNull(name), JSONMessageBuilder.createMessage(requireNonNull(message), requireNonNull(headers)), timeout);
    }

    @Override
    public final <T> boolean publish(final String name, final Message<T> message)
    {
        final MessageChannel channel = getMessageChannel(requireNonNull(name));

        if (null != channel)
        {
            return channel.send(requireNonNull(message));
        }
        throw new IllegalArgumentException("MessageChannel " + name + " does not exist.");
    }

    @Override
    public final <T> boolean publish(final String name, final Message<T> message, final long timeout)
    {
        final MessageChannel channel = getMessageChannel(requireNonNull(name));

        if (null != channel)
        {
            return channel.send(requireNonNull(message), timeout);
        }
        throw new IllegalArgumentException("MessageChannel " + name + " does not exist.");
    }
}
