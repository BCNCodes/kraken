package com.octoperf.kraken.security.entity.functions.spring;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static com.octoperf.kraken.security.entity.owner.ApplicationOwnerTest.APPLICATION_OWNER;
import static com.octoperf.kraken.security.entity.owner.PublicOwnerTest.PUBLIC_OWNER;
import static com.octoperf.kraken.security.entity.owner.UserOwnerTest.USER_OWNER;

public class SpringOwnerToApplicationIdTest {

  SpringOwnerToApplicationId toApplicationId;

  @BeforeEach
  public void setUp() {
    toApplicationId = new SpringOwnerToApplicationId();
  }

  @Test
  public void shouldConvertApplicationOwner() {
    Assertions.assertThat(toApplicationId.apply(APPLICATION_OWNER)).isEqualTo(Optional.of(APPLICATION_OWNER.getApplicationId()));
  }

  @Test
  public void shouldConvertUserOwner() {
    Assertions.assertThat(toApplicationId.apply(USER_OWNER)).isEqualTo(Optional.of(USER_OWNER.getApplicationId()));
  }

  @Test
  public void shouldConvertPublicOwner() {
    Assertions.assertThat(toApplicationId.apply(PUBLIC_OWNER)).isEqualTo(Optional.empty());
  }
}