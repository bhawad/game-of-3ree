package com.go3.application.model.player.decisionSupplier;

import com.go3.application.model.player.TurnDecision;
import com.go3.infrastructure.exception.ValidationException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.function.Supplier;

@RunWith(JUnit4.class)
public class ManualDecisionSupplierTest {


    @Test(expected = ValidationException.class)
    public void testManualDecisionSupplierWithNullInput() {
        new ManualDecisionSupplier(null);
    }

    @Test
    public void testManualDecisionSupplierForUserDecision() {
        Supplier<TurnDecision> testSupplier = new ManualDecisionSupplier(TurnDecision.ZERO);

        Assert.assertEquals("Manual decision must be the same like input decision", TurnDecision.ZERO, testSupplier.get());
    }
}