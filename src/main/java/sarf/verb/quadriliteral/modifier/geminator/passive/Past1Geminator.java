package sarf.verb.quadriliteral.modifier.geminator.passive;

import java.util.*;
import sarf.verb.quadriliteral.substitution.*;
import sarf.verb.trilateral.Substitution.ExpressionInfixSubstitution;
import sarf.verb.trilateral.Substitution.Substitution;
import sarf.verb.trilateral.augmented.ConjugationResult;

/**
 * <p>Title: Sarf Program</p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2006</p>
 *
 * <p>Company: ALEXO</p>
 *
 * @author Haytham Mohtasseb Billah
 * @version 1.0
 */
public class Past1Geminator extends SubstitutionsApplier {
    private final List<Substitution> substitutions = new ArrayList<>();

    public Past1Geminator() {
        substitutions.add(new ExpressionInfixSubstitution("ْC4ِC4َ","ِC4َّ"));// EX: (اقْشُعِرَّ)
        substitutions.add(new ExpressionInfixSubstitution("ْC4ِC4ُ","ِC4ُّ"));// EX: (اقْشُعِرُّوا)
    }

    public List<Substitution> getSubstitutions() {
        return substitutions;
    }

    public boolean isApplied(ConjugationResult conjugationResult) {
        return conjugationResult.getFormulaNo() == 3;
    }
}
