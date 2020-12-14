package com.xiaoshuai.utils;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;



import java.util.Scanner;

/**
 * @author 傅帅  QQ:1766281636
 * @creat 2020- 10-11-下午 13:18
 **/
public class
MybatisPlusGenerator {
        /**
         * <p>
         * 读取控制台内容
         * </p>
         */
        public static String scanner(String tip) {
            Scanner scanner = new Scanner(System.in);
            StringBuilder help = new StringBuilder();
            help.append("请输入" + tip + "：");
            System.out.println(help.toString());
            if (scanner.hasNext()) {
                String ipt = scanner.next();
                if (StringUtils.isNotBlank(ipt)) {
                    return ipt;
                }
            }
            throw new MybatisPlusException("请输入正确的" + tip + "！");
        }
        public static void main(String[] args) {
            // 代码生成器
            AutoGenerator mpg = new AutoGenerator();
            // 全局配置
            GlobalConfig gc = new GlobalConfig();
            //String projectPath = System.getProperty("user.dir\\admin-web");
            gc.setOutputDir("G:\\Idea_workspace\\myblog\\myblog-web" + "/src/main/java");
            gc.setAuthor("xiaoshuai");
            //是否自动生成之后打开资源管理器
            gc.setOpen(false);
            //再次生产时是否覆盖文件
            gc.setFileOverride(false);
            //生成默认生成时IService，变成Service
            gc.setServiceName("%sService");
            gc.setIdType(IdType.AUTO);
            //设置data的类型，只是用java.util.data代替
            gc.setDateType(DateType.ONLY_DATE);
            //实体属性 swagger2属性
            gc.setSwagger2(true); 
            mpg.setGlobalConfig(gc);

            // 数据源配置
            DataSourceConfig dsc = new DataSourceConfig();
            dsc.setUrl("jdbc:mysql://localhost:3306/newblog?useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=UTC");
            // dsc.setSchemaName("public");
            dsc.setDriverName("com.mysql.cj.jdbc.Driver");
            dsc.setUsername("root");
            dsc.setPassword("root");
            dsc.setDbType(DbType.MYSQL);
            mpg.setDataSource(dsc);

            // 包配置
            PackageConfig pc = new PackageConfig();
            //pc.setModuleName(scanner("模块名"));
            pc.setParent("com.xiaoshuai");
            pc.setController("controller");
            pc.setService("service");
            pc.setServiceImpl("service.impl");
            pc.setMapper("mapper");
            pc.setEntity("entity");
            mpg.setPackageInfo(pc);


            // 策略配置
            StrategyConfig strategy = new StrategyConfig();
            //驼峰命名
            strategy.setNaming(NamingStrategy.underline_to_camel);
            strategy.setColumnNaming(NamingStrategy.underline_to_camel);
            //strategy.setSuperEntityClass("你自己的父类实体,没有就不用设置!");
            strategy.setEntityLombokModel(true);
            strategy.setRestControllerStyle(true);
            // 公共父类
           // strategy.setSuperControllerClass("你自己的父类控制器,没有就不用设置!");
//            // 写于父类中的公共字段
//            strategy.setSuperEntityColumns("id");
            strategy.setInclude(scanner("表名，多个英文逗号分割").split(","));
              //驼峰转连接符
            strategy.setControllerMappingHyphenStyle(true);
            strategy.setTablePrefix(pc.getModuleName() + "tb_");
            mpg.setStrategy(strategy);
            mpg.execute();
        }

    }

