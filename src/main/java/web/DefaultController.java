package web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.jmx.export.MBeanExporter;
import org.springframework.jmx.export.assembler.MBeanInfoAssembler;
import org.springframework.jmx.export.assembler.MethodNameBasedMBeanInfoAssembler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by taotao on 16/11/18.
 */
@Controller
@RequestMapping(value="/")
public class DefaultController {
    Logger logger = LoggerFactory.getLogger(DefaultController.class);
    private int testMbean = 10;

    @RequestMapping(value="/", method= RequestMethod.GET)
    public String index() {
        logger.info("test: {}", testMbean);
        return "index";
    }

    @RequestMapping(value="/str", method= RequestMethod.GET)
    @ResponseBody
    public String str() {
        return "str";
    }

    @Bean
    public MBeanExporter mBeanExporter(DefaultController defaultController, MBeanInfoAssembler assembler) {
        logger.info("mbean init...");
        MBeanExporter mBeanExporter = new MBeanExporter();
        Map<String, Object> beans = new HashMap<>();
        beans.put("application:name=DefaultController", defaultController);
        mBeanExporter.setBeans(beans);
        mBeanExporter.setAssembler(assembler);
        return mBeanExporter;
    }

    @Bean
    public MethodNameBasedMBeanInfoAssembler assembler() {
        MethodNameBasedMBeanInfoAssembler assembler = new MethodNameBasedMBeanInfoAssembler();
        assembler.setManagedMethods("getTestMbean", "setTestMbean");
        return assembler;
    }

    public int getTestMbean() {
        return testMbean;
    }

    public void setTestMbean(int testMbean) {
        this.testMbean = testMbean;
    }
}
