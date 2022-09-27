package deba.hl7;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import ca.uhn.hl7v2.DefaultHapiContext;
import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.HapiContext;
import ca.uhn.hl7v2.model.Message;
import ca.uhn.hl7v2.model.v22.datatype.PN;
import ca.uhn.hl7v2.model.v22.message.ADT_A01;
import ca.uhn.hl7v2.model.v22.segment.MSH;
import ca.uhn.hl7v2.model.v22.segment.PID;
import ca.uhn.hl7v2.parser.EncodingNotSupportedException;
import ca.uhn.hl7v2.parser.Parser;
import ca.uhn.hl7v2.util.Hl7InputStreamMessageIterator;

public class Hl7Parser {

    public static void main(String[] args) throws FileNotFoundException {
        String msg = "MSH|^~\\&|EPIC|4.324.23.12.113884.44.5189.10^Eren Health Clinics|JEROME||30461918215050|AUTO|ADT^A01||P|2.2|||||||||||\r"

                + "PID|1||100001^^^^^4.324.23.12.113884.44.5189.10~E11474907^^^^^4.324.23.12.113884.44.5189.10||PINCER^BINTO^HIGEKU^^^^||19141231|Female|PINCER^BINTO^^~PINCER^BINTO^S^~DIBLE^BINTO^S^~DIBLE^BINTO^HIGEKU^|White|6839 42ND ST^^SAN JUAN ISLAND^WA^54110^USA^C^^CALUMET|CALUMET|197-7183^PRN^7^^^920^5880050||English|Single|2550 StoneBech||221-32-4244|||NON-HISPANIC OR LATINO||||||||N|||||||||\r";

        HapiContext context = new DefaultHapiContext();
        Parser p = context.getGenericParser();

        Message hapiMsg;
        try {
            // The parse method performs the actual parsing
            hapiMsg = p.parse(msg);
        } catch (EncodingNotSupportedException e) {
            e.printStackTrace();
            return;
        } catch (HL7Exception e) {
            e.printStackTrace();
            return;
        }

        ADT_A01 adtMsg = (ADT_A01) hapiMsg;

        MSH msh = adtMsg.getMSH();

        // parsing msh

        // output ADT
        String msgType = msh.getMessageType().getMessageType().getValue();
        // output A01
        String msgTrigger = msh.getMessageType().getTriggerEvent().getValue();
        // output 4.324.23.12.113884.44.5189.10
        String sendFacility = msh.getSendingFacility().getValue();
        System.out.println(msgType + " " + msgTrigger + sendFacility);

        PN pid = adtMsg.getPID().getPatientName();

        String familyName = pid.getFamilyName().getValue();
        // output PINCER

        String givenName = pid.getGivenName().getValue();
        // output BINTO

        String extraName = pid.getMiddleInitialOrName().getValue();

        // output The Patient name family name, given name and extraName are
        // PINCER,BINTO and HIGEKU ,respectively
        System.out.printf("The Patient name family name, given name and extraName are %s,%s and %s ,respectively",
                familyName, givenName, extraName);

        
              

    }
}