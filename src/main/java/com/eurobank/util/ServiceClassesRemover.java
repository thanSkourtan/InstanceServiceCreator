package com.eurobank.util;

import com.eurobank.routing.CreatedClassesRouter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import static com.eurobank.util.EsbClassesNamesCreator.addEsbClasses;
import static com.eurobank.util.UtilityMethods.*;

/**
 * Created by v-askourtaniotis on 19/6/2018. mailTo: thanskourtan@gmail.com
 */
public class ServiceClassesRemover {

    public static void deleteServiceClasses (Set<String> brmClassNamesSet, String projectStem, Properties props) {
        Map<Integer, String> allClassesNamesSet = addEsbClasses(brmClassNamesSet);
        List<File> allProjectNames = new CreatedClassesRouter().getAllProjectNames (projectStem,  props);

        System.out.println(allClassesNamesSet);
        System.out.println(allProjectNames);

        allClassesNamesSet.values().stream().forEach(x -> {
                    if (isABReqClassName(x) || isABRespClassName(x) || isABRMDTOClassName(x) || isABeanClassName(x)) {
                        try {
                            Files.deleteIfExists(Paths.get(allProjectNames.stream()
                                                                            .filter(z->z.getName().startsWith("BRM") && !z.getName().endsWith("Exits"))
                                                                            .map(File::toString)
                                                                            .findFirst()
                                                                            .orElseThrow(NullPointerException::new),
                                                                            "src", x.replace(".", "/") + ".java"));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else if (isAnSReqClassName(x) || isAnSRespClassName(x) || isAnESBDTOClassName(x)) {
                        try {
                            Files.deleteIfExists(Paths.get(allProjectNames.stream()
                                                                            .filter(z->z.getName().startsWith("ESB") && !z.getName().endsWith("Impl"))
                                                                            .map(File::toString)
                                                                            .findFirst()
                                                                            .orElseThrow(NullPointerException::new),
                                                                            "src", x.replace(".", "/") + ".java"));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else if (isAnExitClassName(x)) {
                        try {
                            Files.deleteIfExists(Paths.get(allProjectNames.stream()
                                            .filter(z->z.getName().startsWith("BRM") && z.getName().endsWith("Exits"))
                                            .map(File::toString)
                                            .findFirst()
                                            .orElseThrow(NullPointerException::new),
                                    "src", x.replace(".", "/") + ".java"));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else if (isAnSPClassName(x)) {
                        try {
                            Files.deleteIfExists(Paths.get(allProjectNames.stream()
                                            .filter(z->z.getName().endsWith("Impl"))
                                            .map(File::toString)
                                            .findFirst()
                                            .orElseThrow(NullPointerException::new),
                                    "src", x.replace(".", "/") + ".java"));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
        );

    }
}
