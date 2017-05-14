package club.ldkteam.generator;

import club.lkdteam.generator.Generator;
import org.junit.Test;

/**
 * Created by whh on 2017/5/14.
 */
public class GeneratorTest {
    @Test
    public void generate(){
        Generator generator=new Generator();
        try {
            generator.generate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
