package sarf.gerund.modifier.quadrilateral;

import org.junit.jupiter.api.Test;
import sarf.KindOfVerb;
import sarf.verb.quadriliteral.QuadriConjugationResult;
import sarf.verb.quadriliteral.augmented.AugmentedQuadrilateralRoot;

import java.util.Collections;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

class VocalizerTest {

    @Test
    void getSubstitutions() {
        var sut = new Vocalizer();

        var actual = sut.getSubstitutions();

        assertThat(actual).isNotNull();
        assertThat(actual.size()).isEqualTo(10);
        assertThat(actual).allSatisfy(Objects::nonNull);
    }

    @Test
    void isApplied() {
        var root = new AugmentedQuadrilateralRoot();
        root.setC4('ي');
        var result = new QuadriConjugationResult<String>(1, KindOfVerb.Naqis_Wawi, root, Collections.emptyList());
        var sut = new Vocalizer();

        assertThat(sut.isApplied(result)).isTrue();
        root.setC4('d');

        assertThat(sut.isApplied(result)).isFalse();
    }
}