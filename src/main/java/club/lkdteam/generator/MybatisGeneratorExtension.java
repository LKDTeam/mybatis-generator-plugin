package club.lkdteam.generator;

/**
 * Created by zain on 2017/5.
 */
@SuppressWarnings("unused")
public class MybatisGeneratorExtension {
  boolean isGenerate = true;
  String config;
  String generatedSrc;

  public boolean isGenerate() {
    return isGenerate;
  }

  public void setGenerate(boolean generate) {
    isGenerate = generate;
  }

  public String getConfig() {
    return config;
  }

  public void setConfig(String config) {
    this.config = config;
  }

  public String getGeneratedSrc() {
    return generatedSrc;
  }

  public void setGeneratedSrc(String generatedSrc) {
    this.generatedSrc = generatedSrc;
  }
}
