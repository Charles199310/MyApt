package com.sunchenhao.lib_compiler;

import com.google.auto.service.AutoService;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;

/**
 * @description:
 * @author:
 * @date: 2020/1/5
 **/
@AutoService(Processor.class)
@SupportedAnnotationTypes({"com.sunchenhao.lib_annotation.Test"})
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class TestProcessor extends AbstractProcessor {

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        MethodSpec getString = MethodSpec.methodBuilder("getString")
                .addModifiers(Modifier.PUBLIC)
                .returns(String.class)
                .addCode("return \"Hello APT\";\n")
                .build();
        TypeSpec testType = TypeSpec.classBuilder("APTTest").addModifiers(Modifier.PUBLIC).addMethod(getString).build();
        JavaFile javaFile = JavaFile.builder("com.sunchenhao.myapt", testType)
                .build();
        try {
            javaFile.writeTo(processingEnv.getFiler());
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e);
        }
        return true;
    }
}
