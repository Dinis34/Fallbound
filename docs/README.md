![bannerFallbound](./img/bannerFallbound.png)

# LDTS_t06g03 - Fallbound 

### the project was developed by:
- **Simão Barbosa** (up202306609)
- **Pedro Araújo** (up202306606)
- **Martim Cadilhe** (up202307833)

<hr>

**Fallbound** is a 2D platformer game where the player must fall down a series of platforms and gain power while defeating enemies. How far can you fall?

## CONTROLS

### GAMEPLAY
- `Left Arrow` - move left.
- `Right Arrow` - move right.
- `Space` - jump.
- `Escape` - open pause menu.

### MENUS
- `Up Arrow` - select previous option.
- `Down Arrow` - select next option.
- `Enter` - confirm selection.

### ENEMIES
- [Doppelgängers](..src/main/java/Fallbound/Model/Game/Elements/Enemies/NormalEnemy.java) - this enemy can fly. Can be defeated by bullets or a player's stomp. █
- [Spikeys](..src/main/java/Fallbound/Model/Game/Elements/Enemies/SpikeEnemy.java) - this enemy can fly and is slower than the others. Can only be defeated by bullets. Δ
- [Tourthells](..src/main/java/Fallbound/Model/Game/Elements/Enemies/ShellEnemy.java) - this enemy is bound to the platforms. Can only be defeated by stomping. ∩

### COLLECTABLES
- ♡ - EXTRA HEALTH: Increases the player’s max health.
- ↑ - EXTRA JUMP: Increases the player’s max height while jumping.
- ♥ - HEALTH REFILL: Refills the player’s health.
- 🗲 - SUPER SPEED: Makes the player move faster.
- | - EXTRA AMMO: Increases the player’s bullet count.
- ⇢ - FASTER BULLETS: The player’s bullets move faster.

### IMPLEMENTED FEATURES
- **Player Movement** - the player can move left, right, and jump.
- **Wall Collision** - the player collides with the walls.
- **Timer** - the game has a timer that counts the time the player has been playing. This will be the main score of the game.
- **Coins** - the player can collect coins to increase their power.
- **Menus** - the game has a main menu and a pause menu. There is also a game over menu that currently is only accessible by pressing the "q" key.
- **Continue Option** - allow player to unpause game.
- **Procedural World Generation** - the game has a procedural platform generation system that allows the player to play infinitely.
- **Player Health** - the player has a life count system, when the player runs out of lives the game ends.
- **Different Enemies** - the player will have to defeat several types of enemies, each with their own unique ways to be defeated.
- **Bullets** - the player will have bullets that can be used to shoot monsters and other elements.
- **Difficulty progression** - the game will get harder as time passes. Enemies will be faster and stronger.
- **Item Shop** - the game has a built-in shop with items the player can buy using the coins they collect during the gameplay, these items will consist of power-ups that give an advantage to the player.
- **Power-ups** - the player will be able to purchase power-ups that will give him special abilities.

todo:
- **Sound** - the game will have sound effects and background music.
- **Highscore Functionality** - the game will keep track of a player's score during the playthrough and will display the top scoring players.

optional :
- **Boss Battles** - during the playthrough an event is triggered that activates the spawn of a stronger monster.
- **Player Trail** - the player will have a colorful trail that follows them.
- **Challenge Mode** - the game has a challenge mode where the player must achieve a maximum score in a limited time.

## GENERAL STRUCTURE

![project structure](./img/uml/currentUML.png)

## CODE DESIGN

### STRUCTURE

#### Problem in context
Design patterns are standardized solutions that developers can apply to address recurring challenges in application or system design. For a project of this nature, it's important to have a good code design to make the project more maintainable and scalable.
When developing software with a user interface, it's crucial to implement a proper structural pattern. The code must be organized and modular to uphold the Single Responsibility Principle.

#### The Pattern
For this project, we decided to use the Model-View-Controller (MVC) pattern.

This pattern organizes the application into three connected components, isolating the internal data representations from how the information is displayed and interacted with by the user:
 - Model - represents the data and game logic;
 - View - displays the model and sends actions to the controller;
 - Controller - provides a model to view and interprets user actions.
<br>

![MVC architecture](./img/uml/mvc.png)

#### Implementation
Here is how we implemented the MVC pattern:

- [Model](../src/main/java/Fallbound/Model)

- [View](../src/main/java/Fallbound/View)

- [Controller](../src/main/java/Fallbound/Controller)


#### Consequences of using MVC:
- Keeps our code organized by separating tasks.
- Makes it easier to add new features and test parts individually.
- Improves our code's readability and maintainability.

### HANDLING GAME STATES

#### Problem in Context
When developing a game, it's important to have a clear way to manage different states, such as the main menu, pause menu or the gameplay. Without proper organization, transitions between these states can become complicated and error-prone.
There are multiple ways to obtain this, such as creating classes that use several boolean statements to check which state the game is currently on.
For example, a menu class that changes its behavior according to the state of the application (i.e. pause, game over or start). 
However, this solution violates two of the principles of Object-Oriented design, the Single Responsibility Principle and the Open-Closed Principle, as having all possible types of menus within the same class, forces them to be dependent on each other. Also, if there is ever need to add another state to the application, it would force the modification of the entire class.

#### State Pattern
To address this, we decided to use the State Pattern. This pattern simplifies state management by organizing behaviors into several distinct classes that extend an abstract class, in accordance with the Open-Closed Principle and the Single Responsibility Principle, ensuring cleaner, less rigid and more modular code.
The state class is merely responsible for handling transitions between states not the behavior of the states themselves.

#### Implementation
Here are some examples of the State Pattern at work:

- [State](../src/main/java/Fallbound/State)

#### Consequences of using States:
- If not carefully implemented, state transitions might lead to tightly coupled state objects, making changes harder in the long run.
- Frequent state changes or complex state transitions might introduce performance overhead or make debugging more challenging.

### SINGLE STATE INSTANCE

#### Problem in context
This game follows the MVC design structure, hereby taking into account multiple components at the same time that interact with each other, such as the UI, the controller and the models.
With these components, the need to control the game's state consistently across the different components rise.

#### Singleton Pattern
A solution to this is the use of the Singleton pattern to ensure that a class, in this case, the State class, has only one instance and can be accessed from all parts of the project.
This simplified design reduces the need for synchronization and simplifies code maintenance by adhering to the Single Responsibility Principle, as only the State class is responsible for game state management.

#### Implementation
Here is the Singleton pattern at work:

- [State](../src/main/java/Fallbound/State) 

- [Game](../src/main/java/Fallbound/Game.java)

#### Consequences of using Singleton:

- The use of Singleton can make testing more difficult, as the global instance may maintain state between tests.
- Although convenient, global access can lead to hidden dependencies in the code.
- Since the Singleton instance is globally accessible, modifying the State class could impact multiple classes, making the design harder to extend without changing existing code, contradicting the Open/Closed Principle.

### GAME LOOP

#### Problem in Context
An important characteristic to every application's design is the need to constantly update every entity while said application is running.
Having a way to control how fast the game runs is extremely important and must be considered during its design.

#### Game Loop Pattern
To ensure that the game is responsive and updates occur at a constant rate, we need to apply the Game Loop. This was applied to the Game class and describes the continuous cycle of updating and rendering required to run a game efficiently and smoothly, consisting of three main stages that repeat throughout the game's execution:

- Input - represents the key presses;
- Update - involves calculating the new game state, player movement, collisions, etc.
- Render - the loop calls the methods to draw or render the current game state on the screen.

#### Implementation
Here are some examples of the Game Loop pattern at work:

- [Game](../src/main/java/Fallbound/Game.java)

#### Consequences of using Game Loop:

- If not carefully optimized the game loop can consume a lot of processing power, leading to performance issues or excessive battery consumption.
- As the game grows in complexity the game loop may need to accommodate additional processing. Without scalable design, the loop could become overloaded and rigid, leading to degraded performance or the need for a major refactor.


### Collectables

#### Problem in context
For our game, we envisioned several collectable items that can be obtained throughout the game, that give the player a certain advantage.
These collectables, despite giving different advantages, have to behave in a cohesive and organized manner. 
A way to obtain this would be to let the scene handle the creation of the collectables, via a method similar to the one used for platform generation.
However, this would violate the Open/Closed principle, as if we ever wanted to add another type of collectable, it would require modification to the entire scene class, which is just unnecessary.

#### Factory Pattern
The way we chose to deal with this issue is by using the Factory pattern.
A "factory" class was created to specifically handle the creation of said collectables according to their type and with no need to specify which class the object belongs to.
This way, we abide by the Single Responsibility Principle and the Open/Closed Principle, making our code less susceptible to code decay.

#### Implementation
Here is where we implemented the Factory method:

- [Collectable](../src/main/java/Fallbound/Model/Game/Elements/Collectibles/CollectibleFactory.java) for now doesn't work xd

#### Consequences of using Collectables
- Introducing a Factory class adds an additional layer of abstraction. While this can simplify object creation for the scene class, it also makes the codebase more complex overall.
- If collectables were to ever require dynamic parameters or context-specific initialization, the factory pattern can become rigid or cumbersome.
- While the Factory pattern helps the scene class adhere to OCP, the factory itself might still need modifications when new collectable types are introduced


#### KNOWN CODE SMELLS
- todo

### TESTING todo
screenshot
link to mutation

### SELF-EVALUATION todo
como fui eu que fiz esta parte mereço 20 ass: martim

**EXAMPLE**
martim: 100%