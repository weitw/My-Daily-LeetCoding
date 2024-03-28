package com.weitw.lc;

import com.weitw.lc.annotation.LCSolution;
import com.weitw.lc.annotation.LCName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.reflections.Reflections;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.*;

public class AnnotationProcessor {

    private static final String readmeTitle = "My-Daily-LeetCoding";

    private static final String readmeDesc = "LeetCode做题记录，持续锻炼思维能力";

    public static void main(String[] args) {
        String packageName = "com.weitw.lc.records"; // 替换为实际的包名
        String outputFilePath = "README.md"; // Markdown文件输出路径
        processAnnotations(packageName, outputFilePath);
    }

    public static void processAnnotations(String packageName, String outputFilePath) {
        Reflections reflections = new Reflections(packageName);
        Set<Class<?>> classes = reflections.getTypesAnnotatedWith(LCName.class); // 获取带有LCName注解的所有类
        Map<String, List<ClazzInfo>> map = new LinkedHashMap<>();

        for (Class<?> clazz : classes) {
            LCName lcName = clazz.getAnnotation(LCName.class);
            if (lcName != null) {
                String clazzDate = lcName.date();
                Method[] methods = clazz.getDeclaredMethods();
                List<MethodInfo> methodInfoList = new ArrayList<>();
                for (Method method : methods) {
                    LCSolution lcMethod = method.getAnnotation(LCSolution.class);
                    if (lcMethod != null) {
                        MethodInfo methodInfo = new MethodInfo(lcMethod.date(), lcMethod.name(), lcMethod.index(), lcMethod.remark());
                        methodInfoList.add(methodInfo);
                    }
                }
                methodInfoList.sort(Comparator.comparing(MethodInfo::getDate));
                ClazzInfo clazzInfo = new ClazzInfo(lcName.date(), lcName.name(), lcName.index(), methodInfoList);
                map.computeIfAbsent(clazzDate, k -> new ArrayList<>()).add(clazzInfo);
            }
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath))) {
            // 编写标题
            writeReadmeTitleDes(writer);
            map.forEach((date, v) -> {
                write(writer, "# " + date + "\n\n");
                int i = 1;
                for (ClazzInfo clazzInfo : v) {
                    write(writer, "## " + (i) + "." + clazzInfo.getName() + "\n");
                    write(writer, "\n");
                    List<MethodInfo> methodList = clazzInfo.getMethodList();
                    int j = 1;
                    for (MethodInfo methodInfo : methodList) {
                        write(writer, "### " + (i) + "." + j++ + " " + methodInfo.getName() + "(" + methodInfo.getDate() + ")\n");
                        write(writer, methodInfo.getRemark() + "\n");
                        write(writer, "\n");
                    }
                    i++;
                }
                write(writer, "\n\n");
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 编写readme.md的标题和简介
     * @param writer
     */
    public static void writeReadmeTitleDes(BufferedWriter writer) {
        try {
            writer.write("# " + readmeTitle + "\n");
            writer.write(readmeDesc + "\n\n");
        } catch (IOException e) {
            System.out.println("写异常，" + e.getMessage());
        }
    }

    public static void write(BufferedWriter writer, String text) {
        try {
            writer.write(text);
        } catch (IOException e) {
            System.out.println("写异常，" + e.getMessage());
        }
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    static class ClazzInfo {
        private String date;
        private String name; // 解法的名称
        private int index; // 解法的索引，可以根据需要展示或使用
        private List<MethodInfo> methodList;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    // 一个简单的内部类来保存方法注解信息
    static class MethodInfo {
        private String date;
        private String name; // 解法的名称
        private int index; // 解法的索引，可以根据需要展示或使用
        private String remark; // 解决方案具体描述
    }
}