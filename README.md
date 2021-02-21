# Dagger bug

The module `:integration-tests:library` uses Anvil to generate Dagger factories. Anvil generates Kotlin code and not Java like the normal Dagger annotation processor.

The module `:integration-tests:tests` depends on the former module, but uses the Dagger annotation processor to generate components. It seems like the Dagger annotation processor gets the wrong method name from the Kotlin metadata for internal provider functions.

To reproduce run 

```
./gradlew :integration-tests:tests:test

# This will give you the following error:
> Task :integration-tests:tests:compileTestJava FAILED
/Users/ralf/Development/projects/square/anvil/integration-tests/tests/build/generated/source/kapt/test/com/squareup/anvil/test/DaggerAppComponent.java:25: error: cannot find symbol
    return AppModule_ProvideType$libraryFactory.provideType$library();
           ^
  symbol:   variable AppModule_ProvideType$libraryFactory
  location: class DaggerAppComponent
  

If you disable Anvil, use the Dagger annotation processor and generate Java instead of Kotlin code for factories, then this problem is gone:

./gradlew :integration-tests:tests:test -Psquare.generateDaggerFactoriesWithAnvil=false  
```
