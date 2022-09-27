// package deba.hl7;

// import ca.uhn.hl7v2.model.Message;
// import ca.uhn.hl7v2.model.Varies;
// import ca.uhn.hl7v2.model.v23.group.ORF_R02_QUERY_RESPONSE;
// import ca.uhn.hl7v2.model.v23.group.ORU_R01_OBSERVATION;
// import ca.uhn.hl7v2.model.v23.group.ORU_R01_ORDER_OBSERVATION;
// import ca.uhn.hl7v2.model.v23.message.ORU_R01;
// import ca.uhn.hl7v2.parser.PipeParser;
// import ca.uhn.hl7v2.model.v23.segment.MSH;
// import ca.uhn.hl7v2.model.v23.segment.OBR;
// import ca.uhn.hl7v2.model.v23.segment.OBX;
// import ca.uhn.hl7v2.model.v23.segment.VAR;

// /**
//  * Hl7Parser3
//  */
// public class Hl7Parser3 {

//     PipeParser pipeParser = new PipeParser();
    
//     Message message = pipeParser.parse(m);
//     ORU_R01 oru = (ORU_R01) message;
//     MSH msh = oru.getMSH();
//     String sendingApp = msh.getSendingApplication().encode();
//     String sendingFacility = msh.getSendingFacility().encode();

//     for (ORF_R02_QUERY_RESPONSE response : oru.getRESPONSEAll()) {
//         for (ORU_R01_ORDER_OBSERVATION orderObservation : response.getORDER_OBSERVATIONAll()) {
//             OBR obr = orderObservation.getOBR();
//             String fillerOrderNumber = obr.getObr3_FillerOrderNumber().encode();
//             for (ORU_R01_OBSERVATION observation : orderObservation.getOBSERVATIONAll()) {
//                 OBX obx = observation.getOBX();
//                 String type = obx.getObx3_ObservationIdentifier().getCe2_Text().getValue();
//                 String status = obx.getObservResultStatus().getValue();
//                 for (Varies varies : obx.getObx5_ObservationValue()) {
//                     String value = varies.encode();
//                     System.out.println(value + " " + type + " " +status);
//                   }

//                 }
//             }
//         }}}

