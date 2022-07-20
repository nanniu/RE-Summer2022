NFR format:
NFR# (NFR type): NFR description
NFR1 (Operational): The system shall interface with the Choice Parts System. This provides the feed of recycled parts data.

FR format:
FR#: FR description
FR1: The user shall search for the preferred repair facility using vehicle location and radius in miles.

Divider:
There is one and only one blank line between two adjacent requirements.

Trace format:
Each line shows the trace links of one FR to all the NFRs and there are no blank lines between the tracing outputs:

If the number of NFRs is 3, then
FR#,linking to NFR1,linking to NFR2,linking to NFR3
1: FR and NFR are related
0: FR and NFR are not related
FR1,0,1,0

If the number of NFRs is 4, then
FR#,linking to NFR1,linking to NFR2,linking to NFR3,linking to NFR4
1: FR and NFR are related
0: FR and NFR are not related
FR1,0,1,0,1

The output of any automation shall keep the same format (i.e., the trace format).