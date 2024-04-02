package com.weitw.lc;

import com.weitw.lc.annotation.Difficulty;
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

/**
 * 运行该程序，可以将records包下的所有刷题记录的处理情况整理编写到README文档中，以时间倒序展示
 * @author weitw
 * @date 2024/3/29 11:39
 */

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
                List<LCSolution> methodInfoList = new ArrayList<>();
                for (Method method : methods) {
                    LCSolution lcMethod = method.getAnnotation(LCSolution.class);
                    if (lcMethod != null) {
                        methodInfoList.add(lcMethod);
                    }
                }
                methodInfoList.sort(Comparator.comparing(LCSolution::index));
                ClazzInfo clazzInfo = new ClazzInfo(lcName.date(), lcName.name(), lcName.index(), lcName.difficulty(), methodInfoList);
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
                    write(writer, "## " + (i) + "." + clazzInfo.getName() + "(" + clazzInfo.getDifficulty().getVal() + ")\n");
                    write(writer, "\n");
                    List<LCSolution> methodList = clazzInfo.getMethodList();
                    int j = 1;
                    for (LCSolution methodInfo : methodList) {
                        write(writer, "### " + (i) + "." + j++ + " " + methodInfo.name() + "(" + methodInfo.date() + ")\n");
                        write(writer, "用时超过" + methodInfo.time() + "，消耗内存超过" + methodInfo.memory() + "\n\n");
                        write(writer, methodInfo.remark() + "\n");
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
        private Difficulty difficulty; // 难度
        private List<LCSolution> methodList;
    }
}