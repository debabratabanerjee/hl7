package deba.hl7;

    import java.nio.file.Files;
    import java.nio.file.Paths;
    import ca.uhn.hl7v2.model.Message;
    import ca.uhn.hl7v2.parser.PipeParser;
    import ca.uhn.hl7v2.util.Terser;


public class Hl7Parser2 {

    

    public static void main(String[] args) {

    try {

//learning ADT_AXX

        String messageString = readHL7MessageFromFileAsString(
            "E:\\Health Catalyst\\hl7 practice\\hl7\\hl7\\src\\main\\java\\deba\\hl7\\sample.hl7");

            PipeParser ourPipeParser = new PipeParser();
            ourPipeParser.setValidationContext(new ca.uhn.hl7v2.validation.impl.NoValidation());

            Message orderResultsHl7Message = ourPipeParser.parse(messageString);

            Terser terser = new Terser(orderResultsHl7Message);

            OurTerserHelper terserHelper = new OurTerserHelper(terser);
            
            //for MSH
            String terserExpression = "MSH-5";

            String dataRetrieved = terserHelper.getData(terserExpression);
            System.out.printf("Field 6 of MSH segment using expression '%s' was: '%s' \n\n", terserExpression,dataRetrieved);

            //for PID

            // terserExpression = "PID-5-2";
            // dataRetrieved = terserHelper.getData(terserExpression);
            // System.out.printf("Field 5 and Component 2 of the PID segment using expression '%s' was: '%s' \n\n", terserExpression, dataRetrieved);

            //for OBX

            for (int i = 0; i < 12; i++) {
                terserExpression = "/.OBX(i)-5-1";
                dataRetrieved = terserHelper.getData(terserExpression);
                System.out.printf("Field 5 and Component 2 of the PID segment using expression '%s' was: '%s' \n\n", terserExpression, dataRetrieved);
                
            }
                  
            
            


    } catch (Exception e) {
        // TODO: handle exception
        e.printStackTrace();
    }

}

    public static String readHL7MessageFromFileAsString(String fileName) throws Exception {
        return new String(Files.readAllBytes(Paths.get(fileName)));
    }


    
}
