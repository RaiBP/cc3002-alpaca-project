# CC3002 Alpaca Project
Repository for Alpaca Project, CC3002 Programming and Design Methodologies class, Spring 2019, FCFM, Universidad de Chile.

## Project Structure
There are three packages: items, map and units. The map package wasn't modified from the initial version so the package won't be discussed for now. 

### *items* package 
This package contains one interface, common to all equipable items. The abstract class AbstractItem implements this interface, and three abstract classes are inherited from it: 
* **AbstractWeapons**: defines common behavior for items in the *weapons* sub-package. This type of item can attack other units, but are not equipable by the Sorcerer i.e. are not magic books (a.k.a spells).
* **AbstractSpell**: defines common behavior for items in the *magic* sub-package. This type of item can attack other units, but are only equipable by the Sorcerer.
* **AbstractTool**: defines common behavior for items in the *tools* sub-package. This type of item can't attack other units, but can enforce other effects onto them e.g. the Staff can heal other units.

### *units* package 
This package contains one interface, common to all units. The abstract class AbstractUnit implements this interface. There are two main Unit methods that implement the principal functionalities of units:

* **useEquippedItemOn**: this method receives another unit as parameter (a.k.a. *target unit*). Depending on the item equipped, different actions will be performed. If the item equipped is a weapon or a magic book, this method will trigger a combat against the *target unit*. If the equipped item is a tool, this will trigger a tool-specific effect on the *target unit*, e.g. the Staff will heal it. 
* **giveItem**: this method receives another unit (a.k.a. *receiving unit*) and an *item* as parameters. If *item* is not null, is part of the unit's inventory and the *receiving unit*'s inventory is not full, the *item* will be removed from the unit's inventory and added to the *receiving unit*'s inventory.

### *factory* package
This package contains abstract classes and interfaces for both units and items factories. So far, only one method is
implemented: *getDefaultUnit/Item*, which builds fairly standard units and items. All default units (except for 
the Alpaca) come with their respective default weapon/tool/spell in its inventory. This Factory design pattern was used
because it helped to abstract the Tactician from all the numbers and parameters that are needed for the construction of
an item or a unit. In future versions, more methods could be implemented in the Factory classes, in order to make items
or units more interesting, for example: *getStrongUnit* with units that deal more damage and have more HP, or *getLongItem*
with items that have long range. I think this makes this design scalable.

### *controller* package
This package contains a single class, the Game Controller class. The constructor of this class generates the players 
(Tacticians), the map and the player order. It also allows the players to initializes their units, ***but for this version
(2.0), a placeholder was put in place: a single cell will be designated randomly to each Tactician, and then a Fighter
will be placed in it***. The main methods for this class are:

* **initGame**: this method will start another game. If it's the first game played, the unit designation process won't 
take place (since it already took place in the constructor), and the same player order calculated in the constructor 
will be used. From the second game and so forth, each time this method is called, the unit designation process will take
place and a new player order will be decided.
* **endTurn**: this method marks the end of a player's turn.
* **endGame**: this methods marks the end of a game initiated by the *initGame* method.

This class also makes important decisions based on the Observer design pattern. Two events are relevant to this class,
both of which are listened and handled via *handlers*:

* **Moved Unit Event**: this event corresponds to a Tactician moving one of its units to another cell in the game map.
The game controller is then in charge disassociating the former game map cell from the Tactician (since the Tactician
no longer has a unit placed in the cell), leaving the cell free to be occupied in the next turns.
* **Retiring Tactician Event**: this event corresponds to a Tactician losing all its units. The game controller is then 
in charge of removing the Tactician from the game.

### *Tactician* class
This class represents the player of the game. This class contains a list of units, whose size must be bigger than 0 in
order to be able to keep playing. This class contains different methods for:

* **Adding/removing units**: Only *default* units are addable.
* **Moving units**: A specific unit can be moved a single time per turn. They can't be moved to cells occupied by other 
units.
* **Adding items**: Any item can be added to any unit in the unit list.
* **Combat**: there are methods for direct combat and for using tools such as the Staff (it can heal friend and foe).
* **Select/equip items**: Various methods for selection of items in the units' inventory.
* **Select units**: Various methods for selection of units.
* **Exchange of items**: the method *giveItem* is in charge of this aspect.

This class also listens to important information from its units via the Observer design pattern. Two events are 
relevant to this class, and both of them are dealt via a *handler* auxiliary class:

* **Unit Death Event**: this event corresponds to a unit (except for the Hero) reaching 0 Hit Points i.e. dying. 
The Tactician is notified of this and must remove the dead unit from its unit list, along with clearing its game map position.
* **Hero Death Event**: this event corresponds to a Hero unit dying. The Tactician is notified of this in a similar way
to the previous event, but how it handles the situation is different: if the Tactician is in its current turn (i.e. 
it's the *turn owner*), then only a parameter will be set to true (*heroStatus*), indicating that when the turn is 
finished, the Tactician will have to leave the game.  On the contrary, if the Tactician is not the *turn owner*, then it 
will leave the game immediately, since the handler will remove all of its units, activating the *Retiring 
Tactician Event* that prompts the game controller to act as soon as the Hero is dead.

## Design Choices
There are a few design choices that are worth explaining. 

* Two items are equal (according to the method **equals**) if their *name*, *power*, *minRange* and *maxRange* parameters are equal. This means that an item is relatively independent of its owner and its ownership doesn't define it.
* If an item can be equipped to an unit, but isn't on the unit's inventory, the **equipItem** method will add it automatically to the inventory.
* An item can be added to the unit's inventory only if it hasn't an owner. Adding items with previous ownership should only happen through the **giveItem** method.
* The **attack** method made protected because all attacks should be accompained by their counter-attack. The **doCombat** public method implements the attack/counter-attack dynamic using the attack method twice (inverting the attacker-victim position). The attack method is implemented and made protected in the *AbstractUnit* class. In order to make the public method **doCombat** (also implemented in the *AbstractUnit* class) be able to use the 
attack method of the *targetUnit* instance (in order to produce the counter-attack), the *targetUnit* was casted from ***IUnit*** to ***AbstractUnit***.
Casting to an abstract class is an odd design decision but I found it was the only viable solution given the limited design methodologies knowledge and possibly 
the scope of this course so far. For further discussion you can see [my post](https://www.u-cursos.cl/ingenieria/2019/2/CC3002/1/foro/o/23908866) on the course's forum along with its responses.
* In general, if the player does something prohibited, such as trying to equip a weapon with a unit that can't equip weapons, 
or trying to access an unit list index out of bounds, then the equipped/selected item/unit will become *null*. This 
**DOESN'T** happen in the case of moving a unit to a invalid cell. The unit will simply not move.
## Asumptions
* Staff are not considered weapons. For this reason spells don't inflict the 150% increse in damage to clerics that have a staff equipped. Instructions are clear: only weapons are subjected to this increase in damage.
