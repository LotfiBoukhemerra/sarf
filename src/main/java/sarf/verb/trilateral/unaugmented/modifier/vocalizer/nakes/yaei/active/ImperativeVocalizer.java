package sarf.verb.trilateral.unaugmented.modifier.vocalizer.nakes.yaei.active;

import java.util.*;

import sarf.Conjugation;
import sarf.verb.trilateral.Substitution.*;
import sarf.verb.trilateral.unaugmented.modifier.*;
import sarf.verb.trilateral.unaugmented.ConjugationResult;

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
public class ImperativeVocalizer extends SubstitutionsApplier implements IUnaugmentedTrilateralModifier {
    private List<Substitution> substitutions = new ArrayList<>();

    public ImperativeVocalizer() {
        substitutions.add(new SuffixSubstitution("ِيْ","ِ"));// EX: (أنتَ ارمِ)
        substitutions.add(new InfixSubstitution("ِيِ","ِ"));// EX: (أنتِ ارمي)
        substitutions.add(new InfixSubstitution("ِيْ","ِي"));// EX: (انتن ارمين)
        substitutions.add(new InfixSubstitution("ِيُ","ُ"));// EX: (أنتم ارموا)
        substitutions.add(new SuffixSubstitution("َيْ","َ"));// EX: (اسعَ، اخشَ)
        substitutions.add(new InfixSubstitution("َيِي","َيْ"));// EX: (أنتِ اسعَيْ، اخشي )
        substitutions.add(new InfixSubstitution("َيُو","َوْ"));// EX: (أنتم اسعَوْا، اخشَوْا )
        substitutions.add(new InfixSubstitution("َيُن","َوُن"));// EX: (أنتم اسعَوُنَّ، اخشَوُنَّ )
    }

    public List<Substitution> getSubstitutions() {
        return substitutions;
    }

    public boolean isApplied(ConjugationResult conjugationResult) {
        int kov = conjugationResult.getKov();
        var noc = conjugationResult.getRoot().getConjugation();
        return ((kov == 24 || kov == 26) && (noc == Conjugation.Second)) ||
                ((kov == 24 || kov == 25 || kov == 26) && (noc == Conjugation.Third || noc == Conjugation.Forth));
    }
}