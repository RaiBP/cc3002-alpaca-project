# CC3002 Alpaca Project
Repository for Alpaca Project, CC3002 Programming and Design Methodologies class, Spring 2019, FCFM 

## Project Structure
There are three packages: items, map and units. The map package wasn't modified from the initial version so the package won't be discussed for now. 

### *items* package 
This package contains one interface, common to all equipable items. The abstract class AbstractItem implements this interface, and three abstract classes are inhereted from it: 
* **AbstractWeapons**: defines common behavior for items in the *weapons* sub-package. This type of item can attack other units, but are not equippable by the Sorcerer i.e. are not magic books (a.k.a spells).
* **AbstractSpell**: defines common behavior for items in the *magic* sub-package. This type of item can attack other units, but are only equippable by the Sorcerer.
* **AbstractTool**: defines common behavior for items in the *tools* sub-package. This type of item can't attack other units, but can enforce other effects onto them e.g. the Staff can heal other units.

### *units* package 
This package contains one interface, common to all units. The abstract class AbstractUnit implements this interface. There are two main Unit methods that implement the principal functionalities of units:

* **useEquippedItemOn**: this method receives another unit as parameter (a.k.a. *target unit*). Depending on the item equipped, different actions will be performed. If the item equipped is a weapon or a magic book, this method will trigger a combat against the *target unit*. If the equipped item is a tool, this will trigger a tool-specific effect on the *target unit*, e.g. the Staff will heal it. 
* **giveItem**: this method receives another unit (a.k.a. *receiving unit*) and an *item* as parameters. If *item* is not null, is part of the unit's inventory and the *receiving unit*'s inventory is not full, the *item* will be removed from the unit's inventory and added to the *receiving unit*'s inventory.

## Design Choices
There are a few design choices that are worth explaining. 

* Two items are equal (according to the method **equals**) if their *name*, *power*, *minRange* and *maxRange* parameters are equal. This means that an item is relatively independent of its owner and its ownership doesn't define it.
* If an item can be equipped to an unit, but isn't on the unit's inventory, the **equipItem** method will add it automatically to the inventory.
* An item can be added to the unit's inventory only if it hasn't an owner. Adding items with previous ownership should only happen through the **giveItem** method.
* The **attack** method made protected because all attacks should be accompained by their counter-attack. The **doCombat** public method implements the attack/counter-attack dynamic using the attack method twice (inverting the attacker-victim position). The attack method is implemented and made protected in the *AbstractUnit* class. In order to make the public method **doCombat** (also implemented in the *AbstractUnit* class) be able to use the 
attack method of the *targetUnit* instance (in order to produce the counter-attack), the *targetUnit* was casted from ***IUnit*** to ***AbstractUnit***.
Casting to an abstract class is an odd design decision but I found it was the only viable solution given the limited design methodologies knowledge and possibly 
the scope of this course so far. For further discussion you can see [my post](https://www.u-cursos.cl/ingenieria/2019/2/CC3002/1/foro/o/23908866) on the course's forum along with its responses.

## Asumptions
* Staff are not considered weapons. For this reason spells don't inflict the 150% increse in damage to clerics that have a staff equipped. Instructions are clear: only weapons are subjected to this increase in damage.
