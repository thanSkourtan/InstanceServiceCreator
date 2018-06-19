package com.eurobank.routing;

import com.eurobank.jclasses.JMainFileClassData;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

/**
 * Created by v-askourtaniotis on 19/6/2018. mailTo: thanskourtan@gmail.com
 */
public class CreatedClassesRouter {

    public List<File> getAllProjectNames (String projectStem, Properties props) {
        PathMatcher matcher = FileSystems.getDefault().getPathMatcher("regex:" + "^.*\\\\(ESB|BRM){1}"
                + projectStem + "(Exits|Exit|Impl|)$");

        File directory = new File((String) props.get("root"));

        File[] files = directory.listFiles(File::isDirectory);
        List<File> projectNames =  Arrays.asList(files).stream()
                .map(File::toPath)
                .filter(matcher::matches)
                .map(Path::toFile)
                .collect(Collectors.toList());

        System.out.println("projectNames: " + projectNames);
        return projectNames;
    }


    public void route(String projectStem, Properties props) throws IOException{

        //directory placement logic goes here
//        JMainFileClassData.getBrmMessagesCodeModel().build(new File("src//main//resources//resources2"));
//        JMainFileClassData.getEsbBusinessLogicCodeModel().build(new File("src//main//resources//resources3"));
//        JMainFileClassData.getEsbMessagesCodeModel().build(new File("src//main//resources//resources4"));
//        JMainFileClassData.getBrmBusinessLogicCodeModel().build(new File("src//main//resources//resources1"));

        //todo: replace with if statement
        List<File> projectNames = getAllProjectNames(projectStem, props);

        JMainFileClassData.getBrmMessagesCodeModel().build(new File(projectNames.stream()
                .filter(x->x.getName().startsWith("BRM") && !x.getName().endsWith("Exits"))
                .findFirst()
                .orElseThrow(NullPointerException::new), "src"));
        JMainFileClassData.getEsbBusinessLogicCodeModel().build(new File(projectNames.stream()
                .filter(x->x.getName().endsWith("Impl"))
                .findFirst()
                .orElseThrow(NullPointerException::new), "src"));
        JMainFileClassData.getEsbMessagesCodeModel().build(new File(projectNames.stream()
                .filter(x->x.getName().startsWith("ESB") && !x.getName().endsWith("Impl"))
                .findFirst()
                .orElseThrow(NullPointerException::new), "src"));
        JMainFileClassData.getBrmBusinessLogicCodeModel().build(new File(projectNames.stream()
                .filter(x->x.getName().endsWith("Exits"))
                .findFirst()
                .orElseThrow(NullPointerException::new), "src"));
    }


}
