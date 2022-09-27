package deba.hl7;

import java.nio.file.Files;
import java.nio.file.Paths;

import ca.uhn.hl7v2.DefaultHapiContext;
import ca.uhn.hl7v2.HapiContext;
import ca.uhn.hl7v2.model.Message;
import ca.uhn.hl7v2.model.v25.message.ORU_R01;
import ca.uhn.hl7v2.parser.Parser;
import ca.uhn.hl7v2.util.Terser;

/**
 * Parser4
 */
public class Parser4 {
    public static void main(String[] args)  {

        try {
            String messageString = readHL7MessageFromFileAsString(
            "E:\\Health Catalyst\\hl7 practice\\hl7\\hl7\\src\\main\\java\\deba\\hl7\\subra.hl7");

            HapiContext context = new DefaultHapiContext();
            Parser p = context.getGenericParser();
            Message hapiMsg = p.parse(messageString);

            Terser terser = new Terser(hapiMsg);

            terser.set("/.MSH-3-1", "new_sending_app");

            ORU_R01 oru = new ORU_R01();
        oru.initQuickstart("ORU", "R01", "P");
        
        terser = new Terser(oru);
        for (int i = 0; i < 5; i++) {
            terser.set("/.PATIENT_RESULT/ORDER_OBSERVATION/OBSERVATION(" + i + ")/OBX-1", "" + (i + 1));
            terser.set("/.PATIENT_RESULT/ORDER_OBSERVATION/OBSERVATION(" + i + ")/OBX-3", "ST");
            terser.set("/.PATIENT_RESULT/ORDER_OBSERVATION/OBSERVATION(" + i + ")/OBX-5", "This is the value for rep " + i);
        }
        
        System.out.println(p.encode(oru));

            
            
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    
   
   
    }
            private static String readHL7MessageFromFileAsString(String fileName) throws Exception {
        return new String(Files.readAllBytes(Paths.get(fileName)));
    }
}