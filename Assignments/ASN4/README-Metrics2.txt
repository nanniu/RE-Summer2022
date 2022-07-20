Metrics2.java is modified based on Metrics.java. Changes in Metrics.java: Line 29, 57, 58, 84 ,85, 87, and 91.

This Metrics2.java calculates the recall, precision, and F2 between the answer set and an automated tool's output.

To run it, you shall change two parameters 'tool_outputs_path' and 'answer_set_path' in the Main method.


In the automated tool's output, each line must represent one FR to all four NFR trace links:
FR#,traceable to NFR1,traceable to NFR2,traceable to NFR3,traceable to NFR4
for example: FR1,0,1,0,1
1: FR and NFR are traceable
0: FR and NFR are not traceable

FR's id is case insensitive (i.e., FR1, fr1, fR1, and Fr1 are the same).

The Metrics2.java program will ignore blank lines

The Metrics2.java program will treat the one FR's retrieved results as empty (i.e., FR,0,0,0,0) if any of following conditions is matched:
(1): FR's id is not a match of the form: 'FR'+number (e.g., SFR1, FRX, fr 1)
(2): Less or more than 4 NFR traces are shown in one line (e.g., 'FR1,0,0', 'FR1,0,1,0,1,0,0')
(3): FR's id is out of scope of the testing data

For one FR, if its id shows in multiple lines, the Metrics2.java program will use the first one and ignore the rest.

The Metrics2.java program will print out:
(1) recall, precision, and F2 for each NFR
(2) average recall, precision, and F2
 
