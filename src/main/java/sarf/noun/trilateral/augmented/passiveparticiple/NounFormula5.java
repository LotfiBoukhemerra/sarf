package sarf.noun.trilateral.augmented.passiveparticiple;

import sarf.noun.trilateral.augmented.AugmentedTrilateralNoun;
import sarf.util.ArabCharUtil;
import sarf.verb.trilateral.augmented.AugmentedTrilateralRoot;

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
public class NounFormula5 extends AugmentedTrilateralNoun {
    public NounFormula5(AugmentedTrilateralRoot root, String suffix) {
        super(root, suffix);
    }

    /**
     * form
     *
     * @return String
     * @todo Implement this sarf.noun.trilateral.TrilateralNoun method
     */
    public String form() {
        return "م"+ArabCharUtil.DAMMA+root.getC1()+ArabCharUtil.SKOON+"ت"+ArabCharUtil.FATHA+root.getC2()+ArabCharUtil.FATHA+root.getC3()+suffix;
    }
}
