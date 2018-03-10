package sarf.verb.trilateral.augmented.modifier.vocalizer.nakes.wawi.active;

import java.util.*;

import sarf.verb.trilateral.Substitution.*;
import sarf.verb.trilateral.augmented.modifier.*;
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
public class Present3Vocalizer extends SubstitutionsApplier implements IAugmentedTrilateralModifier {

    private List substitutions = new LinkedList();

    public Present3Vocalizer() {
        substitutions.add(new SuffixSubstitution("وُ", "ي")); // EX: (هو يُسَمِّي)
        substitutions.add(new SuffixSubstitution("وْ", "")); // EX: (لم يُسَمِّ)
        substitutions.add(new InfixSubstitution("ِّوُ", "ُّ")); // EX: (أنتم تُسَمُّونَ)
        substitutions.add(new InfixSubstitution("وْن", "ين")); // EX: (أنتن تُسَمِّينَ)
        substitutions.add(new InfixSubstitution("وَ", "يَ")); // EX: (لن يُسَمِّيَ)
        substitutions.add(new InfixSubstitution("وِ", "")); // EX: (أنتِ تُسَمِّينَ)
    }


    public List getSubstitutions() {
        return substitutions;
    }

    public boolean isApplied(ConjugationResult conjugationResult) {
        int kov = conjugationResult.getKov();
        int formulaNo = conjugationResult.getFormulaNo();

        return (kov == 21 || kov == 23) && formulaNo == 2;
    }
}