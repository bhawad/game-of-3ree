# **Game Of 3REE**


Game Of Three is a game between two players or more, where the first player infers a number at the game start, then
Through out the game turns between players, every player will give in a decision from three, either to add one, subtract one
or do nothing (Zero), in order to make the last reached number divisible by 3. 

After the player choice, the number will then be divided by three and used as the next turn input.
If after dividing by 3, the result is 1, this means we have a winner.

If the reached result is 0 due to wrong plays within the turns and/or the last turn, this means that the game has ended 
without winners.

Players can choose to do the turns automatically, so they will play with each other still in turn, but instead of being required 
of giving in a decision, the game will automatically play their turn instead of them. This option can be turned normally with every
turn.

## Game Play

The game has an interface that will enable you to do one thing of two,
  * Either that you type in your name and an initial game number, and **Create a New Game**
  * Or you type in your name and a Game ID to **Join an Existing Game**
  
 ## Technologies used to develop the game
  
  * Java as programming language for Backend
  * Spring boot framework (Spring Web / Spring Websocket)
  * HTML/JQuery/CSS/Bootstrap/Javascript for the Frontend/UI
  
 ## How to setup
 
  * After checking out the code, please use a suitable IDE to open it (Lombok plugin is required for the IDE)
  * Configure the IDE to run the Spring Application main file : ``` Go3Application ```
 
 ## How to Play
  
  * After setting up, Run the game
  * The game server starts on port ``` 9090 ``` and it is configurable via the configuration file ``` application.properties ```
  * Open a browser tab and go to ``` localhost:9090 ```
  * The game will open and will connect to server automatically
  * Create a new game by typing in your name and giving the initial game number
  * Copy the game code in the thin top status bar that is in the form of ``` UUID CODE  ``` and pass it to your friend to play
  * Keep waiting till your friend connects
  * For them to connect, they also have to open a browser and go to the server address as mentioned above
  * And for them, they will have to use the second form for joining an existing game
  * They will have to type in their name and give in the same Game ID of yours
  * After they are able to connect you will see that they are connected via the game status column on the right of the page
  * On your turn, you can always give in your decision, via the buttons that will show up only when you have the turn
  * You can any time select the  ``` AUTO-PLAY ``` checkbox to continue playing automatically AFTER this turn
  * And to ask the game to play automatically for you only for one turn, just press on the ``` AUTOMATIC ``` button when you have the turn
  * And enjoy playing !!!
  

