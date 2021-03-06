package com.octoperf.kraken.runtime.event;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.octoperf.kraken.runtime.entity.task.Task;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class TaskRemovedEvent implements TaskEvent {
  Task task;

  @JsonCreator
  TaskRemovedEvent(
      @NonNull @JsonProperty("task") final Task task
  ) {
    super();
    this.task = task;
  }
}
