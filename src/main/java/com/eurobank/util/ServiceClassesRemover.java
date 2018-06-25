package com.eurobank.util;

import com.eurobank.exceptions.ApplicationException;
import com.eurobank.routing.CreatedClassesRouter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
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

        allClassesNamesSet.values().forEach(x -> {
            try {
                if (isABReqClassName(x) || isABRespClassName(x) || isABRMDTOClassName(x) || isABeanClassName(x)) {
                    String project = allProjectNames.stream()
                            .filter(z -> z.getName().startsWith("BRM") && !z.getName().endsWith("Exits"))
                            .map(File::toString)
                            .findFirst()
                            .orElseThrow(() -> new ApplicationException("No BRM" + projectStem + " exists."));
                    Path tempPath = Paths.get(project, "src", x.replace(".", "/") + ".java");
                    boolean temp = Files.deleteIfExists(tempPath);
                    if (temp) System.out.println("Deleted the file " + tempPath + ".");
                } else if (isAnSReqClassName(x) || isAnSRespClassName(x) || isAnESBDTOClassName(x)) {
                    String project = allProjectNames.stream()
                                                    .filter(z -> z.getName().startsWith("ESB") && !z.getName().endsWith("Impl"))
                                                    .map(File::toString)
                                                    .findFirst()
                                                    .orElseThrow(() -> new ApplicationException("No ESB" + projectStem + "Impl exists."));
                    Path tempPath = Paths.get(project,"src", x.replace(".", "/") + ".java");
                    boolean temp = Files.deleteIfExists(tempPath);
                    if (temp) System.out.println("Deleted the file " + tempPath + ".");
                } else if (isAnExitClassName(x)) {
                    String project = allProjectNames.stream()
                                                    .filter(z -> z.getName().startsWith("BRM") && z.getName().endsWith("Exits"))
                                                    .map(File::toString)
                                                    .findFirst()
                                                    .orElseThrow(() -> new ApplicationException("No BRM" + projectStem + "Exits exists."));
                    Path tempPath = Paths.get(project,"src", x.replace(".", "/") + ".java");
                    boolean temp = Files.deleteIfExists(tempPath);
                    if (temp) System.out.println("Deleted the file " + tempPath + ".");
                } else if (isAnSPClassName(x)) {
                    String project = allProjectNames.stream()
                                                    .filter(z -> z.getName().endsWith("Impl"))
                                                    .map(File::toString)
                                                    .findFirst()
                                                    .orElseThrow(() -> new ApplicationException("No ESB" + projectStem + "Impl exists."));
                    Path tempPath = Paths.get(project,"src", x.replace(".", "/") + ".java");
                    boolean temp = Files.deleteIfExists(tempPath);
                    if (temp) System.out.println("Deleted the file " + tempPath + ".");
                }

            } catch (IOException | ApplicationException e) {
                System.out.println(e);
                System.exit(-1);
            }


                }
        );

    }
}
