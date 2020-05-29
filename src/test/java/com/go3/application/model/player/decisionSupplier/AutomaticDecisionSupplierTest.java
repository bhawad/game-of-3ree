package com.go3.application.model.player.decisionSupplier;

import com.go3.application.model.player.TurnDecision;
import com.go3.infrastructure.exception.ValidationException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.function.Supplier;

@RunWith(JUnit4.class)
public class AutomaticDecisionSupplierTest {


    @Test(expected = ValidationException.class)
    public void testAutomaticDecisionSupplierWithNullInput() {
        new AutomaticDecisionSupplier(null);
    }

    @Test
    public void testAutomaticDecisionSupplierForSupplyingZeroDecision() {
        Supplier<TurnDecision> testSupplier = new AutomaticDecisionSupplier(15);

        Assert.assertEquals("Decision must be Zero", TurnDecision.ZERO, testSupplier.get());
    }

    @Test
    public void testAutomaticDecisionSupplierForSupplyingPlusOneDecision() {
        Supplier<TurnDecision> testSupplier = new AutomaticDecisionSupplier(14);

        Assert.assertEquals("Decision must be +1", TurnDecision.PLUS_ONE, testSupplier.get());

    }

    @Test
    public void testAutomaticDecisionSupplierForSupplyingMinusOneDecision() {
        Supplier<TurnDecision> testSupplier = new AutomaticDecisionSupplier(16);

        Assert.assertEquals("Decision must be -1", TurnDecision.MINUS_ONE, testSupplier.get());

    }
}