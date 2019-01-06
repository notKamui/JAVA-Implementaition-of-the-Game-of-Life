# JAVA Implementation of the Game of Life
## A simple JAVA implementation of Conway's Game of Life

### Important details
Only follow each step without trying to corrupt the game by entering absurd inputs.\
Please use this in a terminal/console in fullscreen on a Unix system with a recommended font size of 10px.\
The only way to get out of the game after being lauched is to forcequit it by pressing CTRL+C.

To run the game, open a terminal/console and move to the current folder using cd commands, then `java -jar GoL.jar`

### Importing a pre-made world
To import a world, create a .txt file and call it via `java -jar GoL.jar [absolutePathOfYourTextFile]`

> You can drag and drop the file in the terminal to get the path.

Make sure to only use these characters in it : living : ⬜ ; dead : ⬛.\
Make sure that the world is perfectly rectangle.\
Any error in the text file will cause the game to crash.\
You can use the sample worlds as you wish.

### Other
For further informations, check out GoL.pdf\
Implemented by notKamui -- December 2018\
Inspired by Conway's Game of Life
