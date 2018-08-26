package io.smallrye.reactive.messaging.beans;

import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Message;
import org.eclipse.microprofile.reactive.messaging.Outgoing;

import javax.enterprise.context.ApplicationScoped;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

@ApplicationScoped
public class BeanProducingACompletableFutureOfMessage {

  @Incoming("count")
  @Outgoing("sink")
  public CompletionStage<Message<String>> process(Message<Integer> value) {
    System.out.println("Got " + value);
    return CompletableFuture.supplyAsync(() -> Integer.toString(value.getPayload() + 1))
      .thenApply(Message::of);
  }
}
