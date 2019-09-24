package sarf.noun.quadriliteral.augmented.passiveparticiple;

import sarf.noun.GenericNounSuffixContainer;
import sarf.noun.quadriliteral.augmented.AugmentedQuadrilateralNoun;
import sarf.util.ArabCharUtil;
import sarf.verb.quadriliteral.augmented.AugmentedQuadrilateralRoot;

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
public class NounFormula3 extends AugmentedQuadrilateralNoun {
    public NounFormula3(AugmentedQuadrilateralRoot root, String suffix, GenericNounSuffixContainer genericNounSuffixContainer) {
        super(root, suffix, genericNounSuffixContainer);
    }

    /**
     * form
     *
     * @return String
     * @todo Implement this sarf.noun.quadriliteral.QuadriliteralNoun method
     */
    public String form() {
        return "م"+ArabCharUtil.DAMMA+root.getC1()+ArabCharUtil.SKOON+root.getC2()+ArabCharUtil.FATHA+root.getC3()+ArabCharUtil.SKOON+root.getC4()+ArabCharUtil.FATHA+root.getC4()+suffix;
    }
}
