## Online parser view
![image](https://user-images.githubusercontent.com/47970328/192078420-bc2e3bb8-ea64-437a-88c8-1d7307b6f741.png)

#### Facing issue

1. And familyname value is comming null
  ![image](https://user-images.githubusercontent.com/47970328/192078597-3e6ed74a-6c61-4e19-8fb8-67fd1e31a896.png)
  
  <b>Solved</b>, after removing EVN i.e., event type segment data from the string
  ![image](https://user-images.githubusercontent.com/47970328/192100397-c8b66c6c-5d08-4bae-8f5a-eaf8de5c71b8.png)


2.The given file has Version 2.5.1 and TriggerEvent of ADT_A08
  But they could not import ADT_A08 for v251, so I reduced the version and the event to 2.2 and ADT_A01 repectively for now.



phn number in PID-13 sample.hl7 = (408)-355-9426^PRN^PH^^1^920^2523000^^

and OBR-17 920-746-0510^^PH^^^920^746-0510~920-743-2798^^FX^^^920^743-2798

OBR-32 RENFREWD^Renfrew^Donald^L^^^MD

OBR-35 RENFREWD^Renfrew^Donald^L^^^MD



Learned to use the Documentation as the names changes with versions of HL7.

## As asked its running in Parser5.java
