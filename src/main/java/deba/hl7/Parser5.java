package deba.hl7;

import java.io.File;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import ca.uhn.hl7v2.DefaultHapiContext;
import ca.uhn.hl7v2.HapiContext;
import ca.uhn.hl7v2.model.Message;
import ca.uhn.hl7v2.model.Varies;
import ca.uhn.hl7v2.model.v231.group.ORU_R01_OBXNTE;
import ca.uhn.hl7v2.model.v231.group.ORU_R01_ORCOBRNTEOBXNTECTI;
import ca.uhn.hl7v2.model.v231.group.ORU_R01_PIDPD1NK1NTEPV1PV2ORCOBRNTEOBXNTECTI;
import ca.uhn.hl7v2.model.v231.message.ORU_R01;
import ca.uhn.hl7v2.model.v231.segment.MSH;
import ca.uhn.hl7v2.model.v231.segment.OBR;
import ca.uhn.hl7v2.model.v231.segment.OBX;
import ca.uhn.hl7v2.parser.Parser;

public class Parser5 {

    public static void main(String[] args) {
        try {
            String messageString = readHL7MessageFromFileAsString(
                    "E:\\Health Catalyst\\hl7 practice\\hl7\\hl7\\src\\main\\java\\deba\\hl7\\sample.hl7");


            // File file = new File("src\\main\\java\\deba\\hl7\\writeParsing.txt");
            // PrintStream stream = new PrintStream(file);
            // System.out.println("From now on "+file.getAbsolutePath()+" will be your console");
            // System.setOut(stream);
         

            HapiContext context = new DefaultHapiContext();
            Parser p = context.getGenericParser();

            Message hapiMsg;

            hapiMsg = p.parse(messageString);

            ORU_R01 oruMsg = (ORU_R01) hapiMsg;

            MSH msh = oruMsg.getMSH();

            String sendingApp = msh.getSendingApplication().encode();
            String sendingFacility = msh.getSendingFacility().encode();
            System.out.println(sendingFacility + " " + sendingApp);

            String msgType = msh.getMessageType().getMessageType().getValue();
            // output A01
            String msgTrigger = msh.getMessageType().getTriggerEvent().getValue();
            // output 4.324.23.12.113884.44.5189.10
            String sendFacility = msh.getSendingFacility().encode();
            System.out.println(msgType + " " + msgTrigger + sendFacility);

            // going inside observation

            for (ORU_R01_PIDPD1NK1NTEPV1PV2ORCOBRNTEOBXNTECTI response : oruMsg
                    .getPIDPD1NK1NTEPV1PV2ORCOBRNTEOBXNTECTIAll()) {

                // going inside obr

                for (ORU_R01_ORCOBRNTEOBXNTECTI orderObservation : response.getORCOBRNTEOBXNTECTIAll()) {

                    // in the obr

                    OBR obr = orderObservation.getOBR();

                    String fillerOrderNumber = obr.getObr3_FillerOrderNumber().encode();

                    // inside obx

                    File file = new File("src\\main\\java\\deba\\hl7\\writeParsing.txt");
                    PrintStream stream = new PrintStream(file);
                    System.out.println("From now on "+file.getAbsolutePath()+" will be your console");
                    System.setOut(stream);

                    for (ORU_R01_OBXNTE observation : orderObservation.getOBXNTEAll()) {
             

                        OBX obx = observation.getOBX();
                        String type = obx.getObx3_ObservationIdentifier().getCe2_Text().getValue();
                        String status = obx.getObservationResultStatus().getValue();
                        for (Varies varies : obx.getObx5_ObservationValue()) {
                            String value = varies.encode();
                            System.out.println(value + " " + type + " " + status);
                        }
                    }

                }

            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return;
        }
    }

    private static String readHL7MessageFromFileAsString(String fileName) throws Exception {
        return new String(Files.readAllBytes(Paths.get(fileName)));
    }

}
