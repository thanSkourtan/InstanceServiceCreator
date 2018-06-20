package com.eurobank.util;

import com.eurobank.exceptions.ApplicationException;
import com.eurobank.routing.PlacementDirectory;
import org.apache.commons.cli.*;

import static com.eurobank.routing.PlacementDirectory.getDirectoriesRoots;
import static com.eurobank.util.UtilityMethods.getXmlFileName;

/**
 * Created by v-askourtaniotis on 5/6/2018. mailTo: thanskourtan@gmail.com
 */
public class OptionsProcessor {

    private CommandLineParser cmdParser;
    private HelpFormatter formatter;
    private String[] args;
    private Options options;
    private OptionsProcessor.CmdData cmdData;

    public class CmdData{
        private String serviceName;
        private String filename;
        private String project;
        private boolean delete;

        public String getServiceName() {
            return serviceName;
        }

        public void setServiceName(String serviceName) {
            this.serviceName = serviceName;
        }

        public String getFilename() {
            return filename;
        }

        public void setFilename(String filename) {
            this.filename = filename;
        }

        public String getProject() {
            return project;
        }

        public void setProject(String project) {
            this.project = project;
        }

        public boolean isDelete() {
            return delete;
        }

        public void setDelete(boolean delete) {
            this.delete = delete;
        }
    }

    public OptionsProcessor (String[] args)  {
        options = new Options();
        options.addOption("x", "xml", true, "The path of the xml file to be parsed.");
        options.addOption("s", "service", true, "The name of the service to be handled.");
        options.addOption("p", "project", true, "The name (stem actually) of the project folders to place the classes.");
        options.addOption("v", "verbose", false, "Shows logs.");
        options.addOption("d", "delete", false, "Deletes the classes related to the declared service.");

        cmdParser = new DefaultParser();
        this.args = args;
        formatter = new HelpFormatter();

    }

    public CmdData processOptions() throws ApplicationException, ParseException{

        CommandLine cmd = cmdParser.parse(options, args);

        if(cmd.getOptions().length == 0) {
            formatter.printHelp("isc", "",options, "\nex: isc -s GetPropertyFireInsurance\n",
                    true);
            System.exit(-1);
        }

        if (!cmd.hasOption("service")){
            throw new ApplicationException("Please enter a service");
        }

        //todo:exception handling here
        cmdData = this.new CmdData();
        cmdData.filename = cmd.getOptionValue("xml");
        cmdData.serviceName = cmd.getOptionValue("service");
        cmdData.project = cmd.hasOption("project") ? cmd.getOptionValue("project"): null;
        cmdData.delete = cmd.hasOption("delete");

        return cmdData;
    }

    public void setProjectAfterParsing(OptionsProcessor.CmdData cmdData, String xmlFileName) throws ApplicationException {
        if(cmdData.getProject() == null) {
            String xmlFile = xmlFileName;
            String project = PlacementDirectory.getDirectoriesRoots().get(xmlFile);

            if(project != null) {
                cmdData.setProject(project);
            } else {
                throw new ApplicationException("There is not a default location for Services declared in " + xmlFileName + ". Please provide the " +
                        "stem of the project name as an argument. For example in \"ESBLoansImpl\" the stem is \"Loans\".");
            }

        }
    }
}
