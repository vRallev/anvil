package com.squareup.anvil.test

import com.google.common.truth.Truth.assertThat
import dagger.Component
import org.junit.Test

internal class MergeComponentTest {
  @Test fun hello() {
    assertThat(DaggerAppComponent.create().type()).isNotNull()
  }
}

@Component(modules = [AppModule::class])
internal interface AppComponent {
  fun type(): ParentType
}
