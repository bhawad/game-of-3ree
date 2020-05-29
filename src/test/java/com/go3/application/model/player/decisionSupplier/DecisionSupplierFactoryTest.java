package com.go3.application.model.player.decisionSupplier;

import com.go3.application.model.player.TurnDecision;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.function.Supplier;

@RunWith(JUnit4.class)
public class DecisionSupplierFactoryTest {

    private final DecisionSupplierFactory factory;

    public DecisionSupplierFactoryTest() {
        this.factory = new DecisionSupplierFactory();
    }


    @Test
    public void testCreatingManualDecisionSupplier() {
        Supplier<TurnDecision> manualSupplierTest = factory.create(15, TurnDecision.ZERO, false);

        Assert.assertTrue("Supplier must be manual", manualSupplierTest instanceof ManualDecisionSupplier);
    }

    @Test
    public void testCreatingAutomaticDecisionSupplier() {
        Supplier<TurnDecision> manualSupplierTest = factory.create(15, null, true);

        Assert.assertTrue("Supplier must be automatic", manualSupplierTest instanceof AutomaticDecisionSupplier);
    }

}