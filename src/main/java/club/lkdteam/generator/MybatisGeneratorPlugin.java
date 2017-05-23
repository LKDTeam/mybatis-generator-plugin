package club.lkdteam.generator;

import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.Task;
import org.gradle.api.plugins.JavaBasePlugin;

/**
 * Created by zain on 2017/5.
 */
public class MybatisGeneratorPlugin implements Plugin<Project> {

  private Project project;

  @Override
  public void apply(Project project) {
    this.project = project;
    project.getPlugins().apply(JavaBasePlugin.class);
    createExtensions();
    bindTask();
  }

  void createExtensions() {
    project.getExtensions().create("mybatis", MybatisGeneratorExtension.class);
    project.getExtensions()
        .getByType(MybatisGeneratorExtension.class).generatedSrc = "$project.buildDir/generated/source/mybatis";
  }

  void bindTask() {
    MybatisGeneratorTask generatorTask = project.getTasks()
        .create(MybatisGeneratorTask.TASK_NAME, MybatisGeneratorTask.class);
    Task javaCompileTask = project.getTasks().getByName("compileJava");
    generatorTask.compile = javaCompileTask;
    generatorTask.resources = project.getTasks().getByName("processResources");
  }
}
