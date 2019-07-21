package sarf.noun.trilateral.unaugmented.modifier.exaggeration.vocalizer;

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
public class B2Vocalizer extends TrilateralNounSubstitutionApplier implements IUnaugmentedTrilateralNounModificationApplier {
    private List<Substitution> substitutions = new ArrayList<>();

    public B2Vocalizer() {
        substitutions.add(new InfixSubstitution("ُوي", "ِيّ"));// EX: (جَنِيّ، قَوِيّ، وَفِيّ )
    }

    public List<Substitution> getSubstitutions() {
        return substitutions;
    }

    public boolean isApplied(ConjugationResult conjugationResult) {
        String nounFormula = conjugationResult.getNounFormula();
        if (!nounFormula.equals("فَعُول")) {
            return false;
        }

        int kov = conjugationResult.getKov();
        var noc = conjugationResult.getRoot().getConjugation();

        switch (kov) {
            case 26:
                switch (noc) {
                    case Second:
                    case Third:
                    case Forth:
                        return true;
                }
            case 28:
                return noc == Conjugation.Second || noc == Conjugation.Forth;
            case 30:
                switch (noc) {
                    case Second:
                    case Forth:
                    case Sixth:
                        return true;
                }

        }
        return false;
    }
}