package sarf.noun.trilateral.unaugmented.modifier.activeparticiple.vocalizer;

import java.util.*;

import sarf.Conjugation;
import sarf.noun.*;
import sarf.verb.trilateral.unaugmented.modifier.*;

import sarf.verb.trilateral.Substitution.*;
import sarf.noun.trilateral.unaugmented.modifier.*;

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
public class Ajwaf3WawiVocalizer extends TrilateralNounSubstitutionApplier implements IUnaugmentedTrilateralNounModificationApplier {
    private List<Substitution> substitutions = new ArrayList<>();

    public Ajwaf3WawiVocalizer() {
        substitutions.add(new InfixSubstitution("اوِ","ائِ"));// EX: (قائِمٌ)
    }


    public List getSubstitutions() {
        return substitutions;
    }

    public boolean isApplied(ConjugationResult conjugationResult) {
        int kov = conjugationResult.getKov();
        var noc = conjugationResult.getRoot().getConjugation();

        return kov == 17 && (noc == Conjugation.First || noc == Conjugation.Fifth);
    }
}