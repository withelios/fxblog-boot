package com.fxtahe.fxblog.mapper;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;


/**
 * @author weizy_mci
 * @program fxblog-boot
 * @description 代码生成器
 * @create 2020-04-14
 */
public class MybatisGenerator {

    public static void main(String[] args) {

        String packageName = "com.fxtahe.fxblog";   // 生成的包名
        boolean serviceNameStartWithI = false;//user -> UserService, 设置成true: user -> IUserService

        String dir = "C:\\file\\personal\\dev\\code\\fxblog\\fxblog-boot\\src\\main\\java";

        //  tablenames  是需要生成的数据库名称
        // 默认是全部表  如果需要生成个别张表,  可以打开56行的注释并注释掉57行
        generateByTables(serviceNameStartWithI,
                packageName,
                dir, new String[]{"author"}
        );
    }
    private static void generateByTables(boolean serviceNameStartWithI, String packageName, String dir, String... tableNames ) {

        GlobalConfig config = new GlobalConfig();
        //mysql
        String dbUrl = "jdbc:mysql://127.0.0.1:3306/fxblog?serverTimezone=CTT&useUnicode=true&characterEncoding=utf-8&allowMultiQueries=true";
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setDbType(DbType.MYSQL)
                .setUrl(dbUrl)
                .setUsername("root")
                .setPassword("root")
                .setDriverName("com.mysql.jdbc.Driver");

        // 策略配置
        StrategyConfig strategyConfig = new StrategyConfig();

        strategyConfig.setInclude(tableNames);
        strategyConfig.setExclude(null);
        strategyConfig
                .setCapitalMode(false)   // 全局大写命名
                .setEntityLombokModel(true)   // Lombok
                .setRestControllerStyle(true)
                .setNaming(NamingStrategy.underline_to_camel); //// underline_to_camel数据库表映射到实体的命名策略


        // 全局配置
        config.setActiveRecord(true)   //是否支持AR模式
                .setAuthor("fxtahe")   // 设置作者
                .setOutputDir(dir)   // 设置生成的目标路径（绝对路径）
                .setIdType(IdType.AUTO) //主键策略
                .setBaseColumnList(true)  // 设置sql片段
                .setBaseResultMap(true)  // resultMap
                .setEnableCache(false)   // 不开缓存
                .setMapperName("%sMapper")
                .setXmlName("%sMapper")
                .setControllerName("%sController")
                .setServiceName("%sService")
                .setServiceImplName("%sServiceImpl")
                .setOpen(false) //生成之后 默认打开文件夹
                .setFileOverride(false);  // 每一次生成需要覆盖
        if (!serviceNameStartWithI) {
            config.setServiceName("%sService");
        }
        new AutoGenerator().setGlobalConfig(config)
                .setDataSource(dataSourceConfig)
                .setStrategy(strategyConfig)
                .setPackageInfo(
                        new PackageConfig()
                                .setParent(packageName)
                                .setController("controller")
                                .setService("service")
                                .setServiceImpl("service.Impl")
                                .setEntity("entity")
                ).execute();
    }

    private void generateByTables(String packageName, String dir,String... tableNames) {
        generateByTables(true, packageName, dir, tableNames);
    }


}
