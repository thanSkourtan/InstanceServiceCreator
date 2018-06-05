package com.eurobank;

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

    public CmdData processOptions() throws ParseException{

        CommandLine cmd = cmdParser.parse(options, args);
        formatter.printHelp("isc", "",options, "\nex: isc -x " +
                        ".\\EuroBankiSeriesJCA.xml -s GetPropertyFireInsurance\n",
                true);

        if (!cmd.hasOption("xml") && !cmd.hasOption("service")){
            System.out.println("Please enter lala");
            System.exit(-1);
        }
        //todo:exception handling here
        String project = cmd.hasOption("project")?
                cmd.getOptionValue("project"):
                getDirectoriesRoots().get(getXmlFileName(cmd.getOptionValue("xml")));

        System.out.println("stem " + project);


        cmdData = this.new CmdData();
        cmdData.filename = cmd.getOptionValue("xml");
        cmdData.serviceName = cmd.getOptionValue("service");

        //test
        return cmdData;
    }
}
