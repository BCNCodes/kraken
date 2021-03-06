package com.octoperf.kraken.runtime.context.environment;

import com.google.common.collect.ImmutableList;
import com.octoperf.kraken.runtime.context.api.environment.EnvironmentPublisher;
import com.octoperf.kraken.runtime.context.entity.ExecutionContextBuilder;
import com.octoperf.kraken.runtime.entity.environment.ExecutionEnvironmentEntry;
import com.octoperf.kraken.runtime.entity.task.TaskType;
import com.octoperf.kraken.security.entity.functions.api.OwnerToApplicationId;
import com.octoperf.kraken.security.entity.functions.api.OwnerToUserId;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.List;

import static com.octoperf.kraken.runtime.entity.environment.ExecutionEnvironmentEntrySource.BACKEND;
import static com.octoperf.kraken.tools.environment.KrakenEnvironmentKeys.*;

@Component
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
final class ContextPublisher implements EnvironmentPublisher {

  @NonNull OwnerToApplicationId toApplicationId;
  @NonNull OwnerToUserId toUserId;

  @Override
  public boolean test(final TaskType taskType) {
    return true;
  }

  @Override
  public Mono<List<ExecutionEnvironmentEntry>> apply(final ExecutionContextBuilder context) {
    return Mono.just(ImmutableList.of(
        ExecutionEnvironmentEntry.builder().from(BACKEND).scope("").key(KRAKEN_TASK_ID.name()).value(context.getTaskId()).build(),
        ExecutionEnvironmentEntry.builder().from(BACKEND).scope("").key(KRAKEN_DESCRIPTION.name()).value(context.getDescription()).build(),
        ExecutionEnvironmentEntry.builder().from(BACKEND).scope("").key(KRAKEN_APPLICATION_ID.name()).value(toApplicationId.apply(context.getOwner()).orElseThrow()).build(),
        ExecutionEnvironmentEntry.builder().from(BACKEND).scope("").key(KRAKEN_USER_ID.name()).value(toUserId.apply(context.getOwner()).orElseThrow()).build(),
        ExecutionEnvironmentEntry.builder().from(BACKEND).scope("").key(KRAKEN_TASK_TYPE.name()).value(context.getTaskType().toString()).build(),
        ExecutionEnvironmentEntry.builder().from(BACKEND).scope("").key(KRAKEN_EXPECTED_COUNT.name()).value(context.getContainersCount().toString()).build()
    ));
  }
}