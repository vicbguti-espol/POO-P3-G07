# Error: JavaFX runtime components are missing, and are required to run this application

**Traza**

```
cd C:\Users\Omen\OneDrive - Escuela Superior Politécnica del Litoral\ESPOL 2023-2024\OBJECT ORIENTED PROGRAMMING\Unidad 6\ControlesFXDin; "JAVA_HOME=C:\\Program Files\\Java\\jdk-19" cmd /c "\"C:\\Program Files\\NetBeans-18\\netbeans\\java\\maven\\bin\\mvn.cmd\" -Dexec.vmArgs= \"-Dexec.args=${exec.vmArgs} -classpath %classpath ${exec.mainClass} ${exec.appArgs}\" \"-Dexec.executable=C:\\Program Files\\Java\\jdk-19\\bin\\java.exe\" -Dexec.mainClass=com.pooespol.controlexfx.App -Dexec.classpathScope=runtime -Dexec.appArgs= \"-Dmaven.ext.class.path=C:\\Program Files\\NetBeans-18\\netbeans\\java\\maven-nblib\\netbeans-eventspy.jar\" --no-transfer-progress process-classes org.codehaus.mojo:exec-maven-plugin:3.1.0:exec"
Scanning for projects...

--------------------< com.pooespol:ControlesFXDin >---------------------
Building ControlesFXDin 1.0
  from pom.xml
--------------------------------[ jar ]---------------------------------

--- resources:3.3.0:resources (default-resources) @ ControlesFXDin ---
Copying 9 resources

--- compiler:3.8.0:compile (default-compile) @ ControlesFXDin ---
Changes detected - recompiling the module!
Compiling 7 source files to C:\Users\Omen\OneDrive - Escuela Superior PolitÃ©cnica del Litoral\ESPOL 2023-2024\OBJECT ORIENTED PROGRAMMING\Unidad 6\ControlesFXDin\target\classes
/C:/Users/Omen/OneDrive - Escuela Superior PolitÃ©cnica del Litoral/ESPOL 2023-2024/OBJECT ORIENTED PROGRAMMING/Unidad 6/ControlesFXDin/src/main/java/com/pooespol/controlexfx/PrimaryController.java: C:\Users\Omen\OneDrive - Escuela Superior PolitÃ©cnica del Litoral\ESPOL 2023-2024\OBJECT ORIENTED PROGRAMMING\Unidad 6\ControlesFXDin\src\main\java\com\pooespol\controlexfx\PrimaryController.java uses unchecked or unsafe operations.
/C:/Users/Omen/OneDrive - Escuela Superior PolitÃ©cnica del Litoral/ESPOL 2023-2024/OBJECT ORIENTED PROGRAMMING/Unidad 6/ControlesFXDin/src/main/java/com/pooespol/controlexfx/PrimaryController.java: Recompile with -Xlint:unchecked for details.

--- exec:3.1.0:exec (default-cli) @ ControlesFXDin ---
Error: JavaFX runtime components are missing, and are required to run this application
Command execution failed.
org.apache.commons.exec.ExecuteException: Process exited with an error: 1 (Exit value: 1)
    at org.apache.commons.exec.DefaultExecutor.executeInternal (DefaultExecutor.java:404)
    at org.apache.commons.exec.DefaultExecutor.execute (DefaultExecutor.java:166)
    at org.codehaus.mojo.exec.ExecMojo.executeCommandLine (ExecMojo.java:1000)
    at org.codehaus.mojo.exec.ExecMojo.executeCommandLine (ExecMojo.java:947)
    at org.codehaus.mojo.exec.ExecMojo.execute (ExecMojo.java:471)
    at org.apache.maven.plugin.DefaultBuildPluginManager.executeMojo (DefaultBuildPluginManager.java:126)
    at org.apache.maven.lifecycle.internal.MojoExecutor.doExecute2 (MojoExecutor.java:342)
    at org.apache.maven.lifecycle.internal.MojoExecutor.doExecute (MojoExecutor.java:330)
    at org.apache.maven.lifecycle.internal.MojoExecutor.execute (MojoExecutor.java:213)
    at org.apache.maven.lifecycle.internal.MojoExecutor.execute (MojoExecutor.java:175)
    at org.apache.maven.lifecycle.internal.MojoExecutor.access$000 (MojoExecutor.java:76)
    at org.apache.maven.lifecycle.internal.MojoExecutor$1.run (MojoExecutor.java:163)
    at org.apache.maven.plugin.DefaultMojosExecutionStrategy.execute (DefaultMojosExecutionStrategy.java:39)
    at org.apache.maven.lifecycle.internal.MojoExecutor.execute (MojoExecutor.java:160)
    at org.apache.maven.lifecycle.internal.LifecycleModuleBuilder.buildProject (LifecycleModuleBuilder.java:105)
    at org.apache.maven.lifecycle.internal.LifecycleModuleBuilder.buildProject (LifecycleModuleBuilder.java:73)
    at org.apache.maven.lifecycle.internal.builder.singlethreaded.SingleThreadedBuilder.build (SingleThreadedBuilder.java:53)
    at org.apache.maven.lifecycle.internal.LifecycleStarter.execute (LifecycleStarter.java:118)
    at org.apache.maven.DefaultMaven.doExecute (DefaultMaven.java:261)
    at org.apache.maven.DefaultMaven.doExecute (DefaultMaven.java:173)
    at org.apache.maven.DefaultMaven.execute (DefaultMaven.java:101)
    at org.apache.maven.cli.MavenCli.execute (MavenCli.java:827)
    at org.apache.maven.cli.MavenCli.doMain (MavenCli.java:272)
    at org.apache.maven.cli.MavenCli.main (MavenCli.java:195)
    at jdk.internal.reflect.DirectMethodHandleAccessor.invoke (DirectMethodHandleAccessor.java:104)
    at java.lang.reflect.Method.invoke (Method.java:578)
    at org.codehaus.plexus.classworlds.launcher.Launcher.launchEnhanced (Launcher.java:282)
    at org.codehaus.plexus.classworlds.launcher.Launcher.launch (Launcher.java:225)
    at org.codehaus.plexus.classworlds.launcher.Launcher.mainWithExitCode (Launcher.java:406)
    at org.codehaus.plexus.classworlds.launcher.Launcher.main (Launcher.java:347)
------------------------------------------------------------------------
BUILD FAILURE
------------------------------------------------------------------------
Total time:  6.361 s
Finished at: 2023-08-11T08:41:39-05:00
------------------------------------------------------------------------
Failed to execute goal org.codehaus.mojo:exec-maven-plugin:3.1.0:exec (default-cli) on project ControlesFXDin: Command execution failed.: Process exited with an error: 1 (Exit value: 1) -> [Help 1]

To see the full stack trace of the errors, re-run Maven with the -e switch.
Re-run Maven using the -X switch to enable full debug logging.

For more information about the errors and possible solutions, please read the following articles:
[Help 1] http://cwiki.apache.org/confluence/display/MAVEN/MojoExecutionException
```

**Solución**

Presionar `Build Project` y `Clean and Build Project` en el navBar de netbeans.