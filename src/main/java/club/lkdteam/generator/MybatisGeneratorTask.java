package club.lkdteam.generator;

import org.gradle.api.DefaultTask;
import org.gradle.api.Task;
import org.gradle.api.tasks.TaskAction;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zain on 2017/5.
 */
public class MybatisGeneratorTask extends DefaultTask {

  public static final String TASK_NAME = "codeGen";

  Task compile;

  Task resources;

  @TaskAction
  private void action() throws InvalidConfigurationException, IOException, XMLParserException, SQLException, InterruptedException {
    if (this.getProject().getExtensions().getByType(MybatisGeneratorExtension.class).isGenerate) {
      this.getProject().getExtensions()
          .getByType(MybatisGeneratorExtension.class).config =
          this.getProject().getProjectDir() + "/config/generatorConfig.xml";
      String generatedSrcJava = this.getProject().getExtensions()
          .getByType(MybatisGeneratorExtension.class).generatedSrc + "/main/java";
      String generatedSrcResources = this.getProject().getExtensions()
          .getByType(MybatisGeneratorExtension.class).generatedSrc + "/main/resources";

      new File(generatedSrcJava).mkdirs();
      new File(generatedSrcResources).mkdirs();
      File configurationXml = new File(this.getProject().getExtensions()
          .getByType(MybatisGeneratorExtension.class).config);
      if (configurationXml.exists()) {
        List<String> warnings = new ArrayList<String>();
        boolean overwrite = true;
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(configurationXml);
        config.getContexts().forEach(it -> {
          it.getJavaModelGeneratorConfiguration()
              .setTargetProject(generatedSrcJava);
          it.getJavaClientGeneratorConfiguration()
              .setTargetProject(generatedSrcJava);
          it.getSqlMapGeneratorConfiguration().setTargetProject(generatedSrcResources);
        });
        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);

        myBatisGenerator.generate(null);
        File outputDir = new File(generatedSrcJava);
        compile.setProperty("outputs", outputDir);
        resources.setProperty("inputs", new File(generatedSrcResources));
      } else {
        System.out.println("no file:{configurationXml.path}");
      }
    }
  }
}
