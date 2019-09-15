package model.combat;

import model.items.tools.Staff;
import model.units.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestClericCombat extends AbstractTestUnit {
    private Cleric cleric;
    private SwordMaster swordMasterTarget;
    private Sorcerer sorcererTarget;
    private Archer archerTarget;
    private Hero heroTarget;
    private Cleric clericTarget;
    private Fighter fighterTarget;

    public void testStaff(IUnit target, int damageDoneToTarget, int expectedHPAfterHealing) {
        int newHP = target.getMaxHitPoints() - damageDoneToTarget;
        target.setHitPoints(newHP);

        cleric.useEquippedItemOn(target);

        assertEquals(newHP, target.getCurrentHitPoints());

        cleric.equipItem(staff);

        cleric.useEquippedItemOn(target);

        assertEquals(expectedHPAfterHealing, target.getCurrentHitPoints());
    }

    @BeforeEach
    public void setTargets() {
        swordMasterTarget = new SwordMaster(50, 2, field.getCell(0, 1));
        sorcererTarget = new Sorcerer(50, 2, field.getCell(0, 1));
        archerTarget = new Archer(50, 2, field.getCell(0, 1));
        heroTarget = new Hero(50, 2, field.getCell(0, 1));
        clericTarget = new Cleric(50, 2, field.getCell(0, 1));
        fighterTarget = new Fighter(50, 2, field.getCell(0, 1));
    }

    @Test
    public void testStaffSwordMaster() {
        testStaff(swordMasterTarget, 20, 40);
    }

    @Test
    public void testStaffSwordMasterOutOfRange() {
        swordMasterTarget.setLocation(field.getCell(0, 3));
        testStaff(swordMasterTarget, 20, 30);
    }

    @Test
    public void testStaffSwordMasterDead() {
        swordMasterTarget.setLocation(field.getCell(0, 1));
        testStaff(swordMasterTarget, 50, 0);
    }

    @Test
    public void testStaffSwordMasterOverhealing() {
        swordMasterTarget.setLocation(field.getCell(0, 1));
        testStaff(swordMasterTarget, 5, 50);
    }

    @Test
    public void testStaffArcher() {
        testStaff(archerTarget, 20, 40);
    }

    @Test
    public void testStaffArcherOutOfRange() {
        archerTarget.setLocation(field.getCell(0, 3));
        testStaff(archerTarget, 20, 30);
    }

    @Test
    public void testStaffArcherDead() {
        archerTarget.setLocation(field.getCell(0, 1));
        testStaff(archerTarget, 50, 0);
    }

    @Test
    public void testStaffArcherOverhealing() {
        archerTarget.setLocation(field.getCell(0, 1));
        testStaff(archerTarget, 5, 50);
    }

    @Test
    public void testStaffClericOutOfRange() {
        clericTarget.setLocation(field.getCell(0, 3));
        testStaff(clericTarget, 20, 30);
    }

    @Test
    public void testStaffCleric() {
        testStaff(clericTarget, 20, 40);
    }

    @Test
    public void testStaffClericDead() {
        clericTarget.setLocation(field.getCell(0, 1));
        testStaff(clericTarget, 50, 0);
    }

    @Test
    public void testStaffClericOverhealing() {
        clericTarget.setLocation(field.getCell(0, 1));
        testStaff(clericTarget, 5, 50);
    }

    @Test
    public void testStaffSorcererOutOfRange() {
        sorcererTarget.setLocation(field.getCell(0, 3));
        testStaff(sorcererTarget, 20, 30);
    }

    @Test
    public void testStaffSorcerer() {
        testStaff(sorcererTarget, 20, 40);
    }

    @Test
    public void testStaffSorcererDead() {
        sorcererTarget.setLocation(field.getCell(0, 1));
        testStaff(sorcererTarget, 50, 0);
    }

    @Test
    public void testStaffSorcererOverhealing() {
        sorcererTarget.setLocation(field.getCell(0, 1));
        testStaff(sorcererTarget, 5, 50);
    }

    @Test
    public void testStaffHero() {
        testStaff(heroTarget, 20, 40);
    }

    @Test
    public void testStaffHeroOutOfRange() {
        heroTarget.setLocation(field.getCell(0, 3));
        testStaff(heroTarget, 20, 30);
    }

    @Test
    public void testStaffHeroDead() {
        heroTarget.setLocation(field.getCell(0, 1));
        testStaff(heroTarget, 50, 0);
    }

    @Test
    public void testStaffHeroOverhealing() {
        heroTarget.setLocation(field.getCell(0, 1));
        testStaff(heroTarget, 5, 50);
    }

    @Test
    public void testStaffFighter() {
        testStaff(fighterTarget, 20, 40);
    }

    @Test
    public void testStaffFighterOutOfRange() {
        fighterTarget.setLocation(field.getCell(0, 3));
        testStaff(fighterTarget, 20, 30);
    }

    @Test
    public void testStaffFighterDead() {
        fighterTarget.setLocation(field.getCell(0, 1));
        testStaff(fighterTarget, 50, 0);
    }

    @Test
    public void testStaffFighterOverhealing() {
        fighterTarget.setLocation(field.getCell(0, 1));
        testStaff(fighterTarget, 5, 50);
    }


    @Test
    public void testUseOnAllUnitsCleric() {
        Staff otherStaff = new Staff("Staff", 10, 1, 2);
        testUseOnAllUnits(otherStaff, 30, 30 + otherStaff.getPower());
    }

    @Test
    public void testUseOnOutOfRangeCleric() {
        Staff otherStaff = new Staff("Staff", 50, 1, 2);
        testUseOnOutOfRangeTargetAllUnits(otherStaff);
    }

    @Override
    public void setTestUnit() {
        cleric = new Cleric(50, 2, field.getCell(0, 0));
    }

    @Override
    public IUnit getTestUnit() {
        return cleric;
    }
}