The Design:

When reading the bowling scoring rule, I see a scoring card in.
Each scoring card has ten frames. The tenth is special on which has max three rolls.

1. Class Frame represent the real frame on the scoring card. It has two roll and a additional roll.
2. ScoringCard has max ten frames. It has a calculate method for all the frames scoring.
3. ScoringCardBuilder is the Object that parsing all the scoring input string and get the ScoringCard.

How to run:
1. Make sure you have installed git, java, gradle or other IDE editor.
   Java is 1.7 or higher needed.
2. Clone the project to you local
   --  git clone https://github.com/coderliux/bowling_scoring.git
3. Run command 'gradle test'