package com.squareup.anvil.test

import dagger.Module
import dagger.Provides

public interface ParentType

internal object SomeType : ParentType

@Module
public object AppModule {
  @Provides internal fun provideType(): ParentType = SomeType
}
