package com.cloud.screw;

import cn.smallbun.screw.core.Configuration;
import cn.smallbun.screw.core.engine.EngineConfig;
import cn.smallbun.screw.core.engine.EngineFileType;
import cn.smallbun.screw.core.engine.EngineTemplateType;
import cn.smallbun.screw.core.execute.DocumentationExecute;
import cn.smallbun.screw.core.process.ProcessConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Arrays;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ScrewApplicationTests {

    @Autowired
    ApplicationContext applicationContext;

    @Test
    public void contextLoads() {
        DataSource dataSourceMysql = applicationContext.getBean(DataSource.class);
        // 生成文件配置
        EngineConfig engineConfig = EngineConfig.builder()
                // 生成文件路径，自己mac本地的地址，这里需要自己更换下路径
                .fileOutputDir("D:/database/files")
                // 打开目录
                .openOutputDir(false)
                // 文件类型
                .fileType(EngineFileType.MD)
                // 生成模板实现
                .produceType(EngineTemplateType.freemarker).build();
        // 生成文档配置（包含以下自定义版本号、描述等配置连接）
        Configuration config = Configuration.builder()
                .version("1.0.0")
                .description("随访库")
                .dataSource(dataSourceMysql)
                .engineConfig(engineConfig)
                .produceConfig(getProcessConfig())
                .build();
        // 执行生成
        new DocumentationExecute(config).execute();
        System.out.println("生成文档成功");
    }

    /**
     * 配置想要生成的表+ 配置想要忽略的表
     *
     * @return 生成表配置
     */
    public static ProcessConfig getProcessConfig() {
        // 忽略表名
//        List<String> ignoreTableName = Arrays.asList("a", "test_group");
        // 忽略表前缀，如忽略a开头的数据库表
//        List<String> ignorePrefix = Arrays.asList("a", "t");
        // 忽略表后缀
//        List<String> ignoreSuffix = Arrays.asList("_test", "czb_");
        return ProcessConfig.builder()
                //根据名称指定表生成
                .designatedTableName(Arrays.asList(
                        "a_mb_sequence",
                        "edu_health_form",
                        "edu_health_form_category",
                        "edu_health_form_history",
                        "edu_health_form_history_content",
                        "edu_health_plan",
                        "edu_health_plan_filt",
                        "edu_health_plan_filt_fail",
                        "edu_health_plan_job",
                        "edu_health_plan_pat",
                        "edu_health_plan_pat_form",
                        "edu_health_plan_pat_form_content",
                        "edu_health_plan_staff",
                        "edu_health_plan_task",
                        "file_storage",
                        "fol_hosfoll_filt",
                        "fol_hosfoll_filt_fail",
                        "fol_hosfoll_job",
                        "fol_hosfoll_pat",
                        "fol_hosfoll_pat_form",
                        "fol_hosfoll_plan",
                        "fol_hosfoll_plan_task",
                        "fol_hosfoll_rule",
                        "fol_hosfoll_rule_task",
                        "rep_category",
                        "rep_form",
                        "sat_survey_form",
                        "sat_survey_form_category",
                        "sat_survey_form_history",
                        "sat_survey_form_history_content",
                        "sat_survey_plan",
                        "sat_survey_plan_filt",
                        "sat_survey_plan_filt_fail",
                        "sat_survey_plan_job",
                        "sat_survey_plan_pat",
                        "sat_survey_plan_pat_form",
                        "sat_survey_plan_pat_form_content",
                        "sat_survey_plan_staff",
                        "sat_survey_plan_task"
                ))
                //根据表前缀生成
                .designatedTablePrefix(new ArrayList<String>())
                //根据表后缀生成
                .designatedTableSuffix(new ArrayList<String>())
                //忽略表名
//                .ignoreTableName(ignoreTableName)
                //忽略表前缀
//                .ignoreTablePrefix(ignorePrefix)
                //忽略表后缀
//                .ignoreTableSuffix(ignoreSuffix)
                .build();
    }

}
