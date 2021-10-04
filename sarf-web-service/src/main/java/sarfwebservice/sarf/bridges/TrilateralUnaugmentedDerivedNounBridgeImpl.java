package sarfwebservice.sarf.bridges;

import com.google.common.collect.ImmutableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sarf.ConjugationResult;
import sarf.KindOfVerb;
import sarf.WordPresenter;
import sarf.noun.GenericNounSuffixContainer;
import sarf.noun.TrilateralUnaugmentedNouns;
import sarf.noun.trilateral.unaugmented.UnaugmentedTrilateralActiveParticipleConjugator;
import sarf.noun.trilateral.unaugmented.UnaugmentedTrilateralPassiveParticipleConjugator;
import sarf.noun.trilateral.unaugmented.assimilate.AssimilateAdjectiveConjugator;
import sarf.noun.trilateral.unaugmented.elative.ElativeNounConjugator;
import sarf.noun.trilateral.unaugmented.elative.ElativeSuffixContainer;
import sarf.noun.trilateral.unaugmented.exaggeration.NonStandardExaggerationConjugator;
import sarf.noun.trilateral.unaugmented.exaggeration.StandardExaggerationConjugator;
import sarf.noun.trilateral.unaugmented.instrumental.NonStandardInstrumentalConjugator;
import sarf.noun.trilateral.unaugmented.instrumental.StandardInstrumentalConjugator;
import sarf.noun.trilateral.unaugmented.modifier.activeparticiple.ActiveParticipleModifier;
import sarf.noun.trilateral.unaugmented.modifier.assimilate.AssimilateModifier;
import sarf.noun.trilateral.unaugmented.modifier.elative.ElativeModifier;
import sarf.noun.trilateral.unaugmented.modifier.exaggeration.ExaggerationModifier;
import sarf.noun.trilateral.unaugmented.modifier.instrumental.InstrumentalModifier;
import sarf.noun.trilateral.unaugmented.modifier.passiveparticiple.PassiveParticipleModifier;
import sarf.noun.trilateral.unaugmented.modifier.timeandplace.TimeAndPlaceModifier;
import sarf.noun.trilateral.unaugmented.timeandplace.TimeAndPlaceConjugator;
import sarf.verb.trilateral.unaugmented.UnaugmentedTrilateralRoot;
import sarfwebservice.models.DerivedNounConjugation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static sarfwebservice.models.NounConjugations.ACTIVE_PARTICIPLE_KEY;
import static sarfwebservice.models.NounConjugations.PASSIVE_PARTICIPLE_KEY;

@Service
public class TrilateralUnaugmentedDerivedNounBridgeImpl implements TrilateralUnaugmentedDerivedNounBridge {

    private final NonStandardExaggerationConjugator nonStandardExaggerationConjugator;
    private final NonStandardInstrumentalConjugator nonStandardInstrumentalConjugator;
    private final ElativeNounConjugator elativeNounConjugator;
    private final AssimilateAdjectiveConjugator assimilateAdjectiveConjugator;
    private final TrilateralUnaugmentedNouns trilateralUnaugmentedNouns;
    private final StandardExaggerationConjugator standardExaggerationConjugator;
    private final StandardInstrumentalConjugator standardInstrumentalConjugator;
    private final TimeAndPlaceConjugator timeAndPlaceConjugator;
    private final UnaugmentedTrilateralPassiveParticipleConjugator unaugmentedTrilateralPassiveParticipleConjugator;
    private final UnaugmentedTrilateralActiveParticipleConjugator unaugmentedTrilateralActiveParticipleConjugator;
    private final GenericNounSuffixContainer genericNounSuffixContainer;
    private final ActiveParticipleModifier activeParticipleModifier;
    private final PassiveParticipleModifier passiveParticipleModifier;
    private final ExaggerationModifier exaggerationModifier;
    private final InstrumentalModifier instrumentalModifier;
    private final TimeAndPlaceModifier timeAndPlaceModifier;
    private final ElativeModifier elativeModifier;
    private final AssimilateModifier assimilateModifier;

    @Autowired
    public TrilateralUnaugmentedDerivedNounBridgeImpl(NonStandardExaggerationConjugator nonStandardExaggerationConjugator
            , NonStandardInstrumentalConjugator nonStandardInstrumentalConjugator
            , ElativeNounConjugator elativeNounConjugator
            , AssimilateAdjectiveConjugator assimilateAdjectiveConjugator
            , TrilateralUnaugmentedNouns trilateralUnaugmentedNouns
            , StandardExaggerationConjugator standardExaggerationConjugator
            , StandardInstrumentalConjugator standardInstrumentalConjugator
            , TimeAndPlaceConjugator timeAndPlaceConjugator
            , UnaugmentedTrilateralPassiveParticipleConjugator unaugmentedTrilateralPassiveParticipleConjugator
            , UnaugmentedTrilateralActiveParticipleConjugator unaugmentedTrilateralActiveParticipleConjugator
            , GenericNounSuffixContainer genericNounSuffixContainer
            , ActiveParticipleModifier activeParticipleModifier
            , PassiveParticipleModifier passiveParticipleModifier
            , ExaggerationModifier exaggerationModifier
            , InstrumentalModifier instrumentalModifier
            , TimeAndPlaceModifier timeAndPlaceModifier
            , ElativeModifier elativeModifier
            , AssimilateModifier assimilateModifier){

        this.nonStandardExaggerationConjugator = nonStandardExaggerationConjugator;
        this.nonStandardInstrumentalConjugator = nonStandardInstrumentalConjugator;
        this.elativeNounConjugator = elativeNounConjugator;
        this.assimilateAdjectiveConjugator = assimilateAdjectiveConjugator;
        this.trilateralUnaugmentedNouns = trilateralUnaugmentedNouns;
        this.standardExaggerationConjugator = standardExaggerationConjugator;
        this.standardInstrumentalConjugator = standardInstrumentalConjugator;
        this.timeAndPlaceConjugator = timeAndPlaceConjugator;
        this.unaugmentedTrilateralPassiveParticipleConjugator = unaugmentedTrilateralPassiveParticipleConjugator;
        this.unaugmentedTrilateralActiveParticipleConjugator = unaugmentedTrilateralActiveParticipleConjugator;
        this.genericNounSuffixContainer = genericNounSuffixContainer;
        this.activeParticipleModifier = activeParticipleModifier;
        this.passiveParticipleModifier = passiveParticipleModifier;
        this.exaggerationModifier = exaggerationModifier;
        this.instrumentalModifier = instrumentalModifier;
        this.timeAndPlaceModifier = timeAndPlaceModifier;
        this.elativeModifier = elativeModifier;
        this.assimilateModifier = assimilateModifier;
    }

    @Override
    public List<DerivedNounConjugation> getActiveParticiple(UnaugmentedTrilateralRoot root, KindOfVerb kov) {
        var formulaName = ACTIVE_PARTICIPLE_KEY;
        this.genericNounSuffixContainer.selectInDefiniteMode();
        var conjugatedNouns = this.unaugmentedTrilateralActiveParticipleConjugator.createNounList(root, formulaName);
        var indefiniteResult = activeParticipleModifier.build(root, kov, conjugatedNouns, formulaName);

        this.genericNounSuffixContainer.selectAnnexedMode();
        conjugatedNouns = this.unaugmentedTrilateralActiveParticipleConjugator.createNounList(root, formulaName);
        var annexedResult = activeParticipleModifier.build(root, kov, conjugatedNouns, formulaName);

        this.genericNounSuffixContainer.selectDefiniteMode();
        conjugatedNouns = this.unaugmentedTrilateralActiveParticipleConjugator.createNounList(root, formulaName);
        var definiteResult = activeParticipleModifier.build(root, kov, conjugatedNouns, formulaName);

        var derivedNounConjugation = new DerivedNounConjugation();
        derivedNounConjugation.setIndefiniteNouns(indefiniteResult.getFinalResult().stream().map(wp -> wp.toString()).toList());
        derivedNounConjugation.setAnnexedNouns(annexedResult.getFinalResult().stream().map(wp -> wp.toString()).toList());
        derivedNounConjugation.setDefiniteNouns(definiteResult.getFinalResult().stream().map(wp -> wp.toString()).toList());
        derivedNounConjugation.setKey(formulaName);
        return List.of(derivedNounConjugation);
    }

    @Override
    public List<DerivedNounConjugation> getPassiveParticiple(UnaugmentedTrilateralRoot root, KindOfVerb kov) {
        var formulaName = PASSIVE_PARTICIPLE_KEY;
        this.genericNounSuffixContainer.selectInDefiniteMode();
        var conjugatedNouns = this.unaugmentedTrilateralPassiveParticipleConjugator.createNounList(root, formulaName);
        var indefiniteResult = passiveParticipleModifier.build(root, kov, conjugatedNouns, formulaName);

        this.genericNounSuffixContainer.selectAnnexedMode();
        conjugatedNouns = this.unaugmentedTrilateralPassiveParticipleConjugator.createNounList(root, formulaName);
        var annexedResult = passiveParticipleModifier.build(root, kov, conjugatedNouns, formulaName);


        this.genericNounSuffixContainer.selectDefiniteMode();
        conjugatedNouns = this.unaugmentedTrilateralPassiveParticipleConjugator.createNounList(root, formulaName);
        var definiteResult = passiveParticipleModifier.build(root, kov, conjugatedNouns, formulaName);

        var derivedNounConjugation = new DerivedNounConjugation();
        derivedNounConjugation.setIndefiniteNouns(indefiniteResult.getFinalResult().stream().map(wp -> wp.toString()).toList());
        derivedNounConjugation.setAnnexedNouns(annexedResult.getFinalResult().stream().map(wp -> wp.toString()).toList());
        derivedNounConjugation.setDefiniteNouns(definiteResult.getFinalResult().stream().map(wp -> wp.toString()).toList());
        derivedNounConjugation.setKey(formulaName);
        return List.of(derivedNounConjugation);
    }

    @Override
    public List<DerivedNounConjugation> getTimeAndPlaceNouns(UnaugmentedTrilateralRoot root, KindOfVerb kov) {
        var nouns = trilateralUnaugmentedNouns.getTimeAndPlaces(root);

        if (nouns == null || nouns.isEmpty()) {
            return Collections.emptyList();
        }
        var keys = timeAndPlaceConjugator.getAppliedFormulaList(root);
        var derivedNounConjugations = new ArrayList<DerivedNounConjugation>();
        for (var key : keys) {
            this.genericNounSuffixContainer.selectInDefiniteMode();
            var conjugatedNouns = timeAndPlaceConjugator.createNounList(root, key);
            var indefiniteResult = timeAndPlaceModifier.build(root, kov, conjugatedNouns, key);

            this.genericNounSuffixContainer.selectAnnexedMode();
            conjugatedNouns = timeAndPlaceConjugator.createNounList(root, key);
            var annexedResult = timeAndPlaceModifier.build(root, kov, conjugatedNouns, key);

            this.genericNounSuffixContainer.selectDefiniteMode();
            conjugatedNouns = timeAndPlaceConjugator.createNounList(root, key);
            var definiteResult = timeAndPlaceModifier.build(root, kov, conjugatedNouns, key);
            var derivedNounConjugation = new DerivedNounConjugation();
            derivedNounConjugation.setKey(key);
            derivedNounConjugation.setIndefiniteNouns(indefiniteResult.getFinalResult().stream().map(wp -> wp.toString()).toList());
            derivedNounConjugation.setAnnexedNouns(annexedResult.getFinalResult().stream().map(wp -> wp.toString()).toList());
            derivedNounConjugation.setDefiniteNouns(definiteResult.getFinalResult().stream().map(wp -> wp.toString()).toList());

            derivedNounConjugations.add(derivedNounConjugation);
        }
        return derivedNounConjugations;
    }

    @Override
    public List<DerivedNounConjugation> getExaggeratedActiveParticiples(UnaugmentedTrilateralRoot root, KindOfVerb kov) {
        var standardExaggeratedActiveParticiples = getStandardExaggeratedActiveParticiples(root, kov);
        var nonStandardExaggeratedActiveParticiples = getNonStandardExaggeratedActiveParticiples(root, kov);
        if(standardExaggeratedActiveParticiples.isEmpty() && nonStandardExaggeratedActiveParticiples.isEmpty()) {
            return Collections.emptyList();
        }

        return List.of(standardExaggeratedActiveParticiples, nonStandardExaggeratedActiveParticiples)
                .stream().flatMap(Collection::stream).toList();
    }

    private List<DerivedNounConjugation> getStandardExaggeratedActiveParticiples(UnaugmentedTrilateralRoot root, KindOfVerb kov) {
        var nouns = trilateralUnaugmentedNouns.getStandardExaggerations(root);
        if(nouns == null || nouns.isEmpty())
        {
            return Collections.emptyList();
        }

        var keys = standardExaggerationConjugator.getAppliedFormulaList(root);
        var derivedNounConjugations = new ArrayList<DerivedNounConjugation>();
        for(var key : keys) {
            var derivedNounConjugation = new DerivedNounConjugation();
            derivedNounConjugation.setKey(key);
            this.genericNounSuffixContainer.selectInDefiniteMode();
            var conjugatedNouns = standardExaggerationConjugator.createNounList(root, key);
            var indefiniteResult = exaggerationModifier.build(root, kov, conjugatedNouns, key);
            derivedNounConjugation.setIndefiniteNouns(indefiniteResult.getFinalResult().stream().map(wp -> wp.toString()).toList());

            this.genericNounSuffixContainer.selectAnnexedMode();
            conjugatedNouns = standardExaggerationConjugator.createNounList(root, key);
            var annexedResult = exaggerationModifier.build(root, kov, conjugatedNouns, key);
            derivedNounConjugation.setAnnexedNouns(annexedResult.getFinalResult().stream().map(wp -> wp.toString()).toList());

            this.genericNounSuffixContainer.selectDefiniteMode();
            conjugatedNouns = standardExaggerationConjugator.createNounList(root, key);
            var definiteResult = exaggerationModifier.build(root, kov, conjugatedNouns, key);
            derivedNounConjugation.setDefiniteNouns(definiteResult.getFinalResult().stream().map(wp -> wp.toString()).toList());
            derivedNounConjugations.add(derivedNounConjugation);
        }
        return derivedNounConjugations;
    }

    private List<DerivedNounConjugation> getNonStandardExaggeratedActiveParticiples(UnaugmentedTrilateralRoot root, KindOfVerb kov) {
        var nouns = trilateralUnaugmentedNouns.getNonStandardExaggerations(root);
        if(nouns == null || nouns.isEmpty())
        {
            return Collections.emptyList();
        }

        var keys = nonStandardExaggerationConjugator.getAppliedFormulaList(root);
        var derivedNounConjugations = new ArrayList<DerivedNounConjugation>();
        for(var key : keys) {
            var derivedNounConjugation = new DerivedNounConjugation();
            derivedNounConjugation.setKey(key);
            this.genericNounSuffixContainer.selectInDefiniteMode();
            var conjugatedNouns = nonStandardExaggerationConjugator.createNounList(root, key);
            var indefiniteResult = exaggerationModifier.build(root, kov, conjugatedNouns, key);
            derivedNounConjugation.setIndefiniteNouns(indefiniteResult.getFinalResult().stream().map(wp -> wp.toString()).toList());

            this.genericNounSuffixContainer.selectAnnexedMode();
            conjugatedNouns = nonStandardExaggerationConjugator.createNounList(root, key);
            var annexedResult = exaggerationModifier.build(root, kov, conjugatedNouns, key);
            derivedNounConjugation.setAnnexedNouns(annexedResult.getFinalResult().stream().map(wp -> wp.toString()).toList());

            this.genericNounSuffixContainer.selectDefiniteMode();
            conjugatedNouns = nonStandardExaggerationConjugator.createNounList(root, key);
            var definiteResult = exaggerationModifier.build(root, kov, conjugatedNouns, key);
            derivedNounConjugation.setDefiniteNouns(definiteResult.getFinalResult().stream().map(wp -> wp.toString()).toList());
            derivedNounConjugations.add(derivedNounConjugation);
        }
        return derivedNounConjugations;
    }

    @Override
    public List<DerivedNounConjugation> getInstrumentalNouns(UnaugmentedTrilateralRoot root, KindOfVerb kov) {
        var standardInstrumentalNouns = getStandardInstrumentalNouns(root, kov);
        var nonStandardInstrumentalNouns = getNonStandardInstrumentalNouns(root, kov);
        if(standardInstrumentalNouns.isEmpty() && nonStandardInstrumentalNouns.isEmpty()) {
            return Collections.emptyList();
        }

        return List.of(standardInstrumentalNouns, nonStandardInstrumentalNouns)
                .stream().flatMap(Collection::stream).toList();
    }

    @Override
    public List<DerivedNounConjugation> getElatives(UnaugmentedTrilateralRoot root, KindOfVerb kov) {
        var nouns = trilateralUnaugmentedNouns.getElatives(root);
        if(nouns == null || nouns.isEmpty()) {
            return Collections.emptyList();
        }

        var derivedNouns = new ArrayList<DerivedNounConjugation>();
        var keys = elativeNounConjugator.getAppliedFormulaList(root);
        for(var key: keys) {
            var derivedNoun = new DerivedNounConjugation();
            derivedNoun.setKey(key);

            //TODO: getInstance has to return a new instance, we cannot maintain a singleton in a web app.
            ElativeSuffixContainer.getInstance().selectInDefiniteMode();
            var conjugatedNouns = elativeNounConjugator.createNounList(root, key);
            var annexedToIndefinite = elativeModifier.build(root, kov, conjugatedNouns, key);
            derivedNoun.setAnnexedToIndefinite(annexedToIndefinite.getFinalResult().stream().map(wp -> wp.toString()).toList());

            ElativeSuffixContainer.getInstance().selectAnnexedMode();
            conjugatedNouns = elativeNounConjugator.createNounList(root, key);
            var annexedToDefinite = elativeModifier.build(root, kov, conjugatedNouns, key);
            derivedNoun.setAnnexedToDefinite(annexedToDefinite.getFinalResult().stream().map(wp -> wp.toString()).toList());

            ElativeSuffixContainer.getInstance().selectNotAnnexedMode();
            conjugatedNouns = elativeNounConjugator.createNounList(root, key);
            var notAnnexed = elativeModifier.build(root, kov, conjugatedNouns, key);
            derivedNoun.setIndefiniteNouns(notAnnexed.getFinalResult().stream().map(wp -> wp.toString()).toList());

            ElativeSuffixContainer.getInstance().selectDefiniteMode();
            conjugatedNouns = elativeNounConjugator.createNounList(root, key);
            var definite = elativeModifier.build(root, kov, conjugatedNouns, key);
            derivedNoun.setDefiniteNouns(definite.getFinalResult().stream().map(wp -> wp.toString()).toList());

            derivedNouns.add(derivedNoun);
        }
        return derivedNouns;
    }

    private List<DerivedNounConjugation> getStandardInstrumentalNouns(UnaugmentedTrilateralRoot root, KindOfVerb kov) {
        var nouns = trilateralUnaugmentedNouns.getStandardInstrumentals(root);
        if(nouns == null || nouns.isEmpty()) {
            return Collections.emptyList();
        }

        var derivedNounConjugations = new ArrayList<DerivedNounConjugation>();
        var keys = standardInstrumentalConjugator.getAppliedFormulaList(root);
        for(var key: keys) {
            var derivedNounConjugation = new DerivedNounConjugation();
            derivedNounConjugation.setKey(key);
            this.genericNounSuffixContainer.selectInDefiniteMode();
            var conjugatedNouns = standardInstrumentalConjugator.createNounList(root, key);
            var indefiniteResult = instrumentalModifier.build(root, kov, conjugatedNouns, key);
            derivedNounConjugation.setIndefiniteNouns(indefiniteResult.getFinalResult().stream().map(wp -> wp.toString()).toList());

            this.genericNounSuffixContainer.selectAnnexedMode();
            conjugatedNouns = standardInstrumentalConjugator.createNounList(root, key);
            var annexedResult = instrumentalModifier.build(root, kov, conjugatedNouns, key);
            derivedNounConjugation.setAnnexedNouns(annexedResult.getFinalResult().stream().map(wp -> wp.toString()).toList());

            this.genericNounSuffixContainer.selectDefiniteMode();
            conjugatedNouns = standardInstrumentalConjugator.createNounList(root, key);
            var definiteResult = instrumentalModifier.build(root, kov, conjugatedNouns, key);
            derivedNounConjugation.setDefiniteNouns(definiteResult.getFinalResult().stream().map(wp -> wp.toString()).toList());
            derivedNounConjugations.add(derivedNounConjugation);
        }
        return derivedNounConjugations;
    }

    private List<DerivedNounConjugation> getNonStandardInstrumentalNouns(UnaugmentedTrilateralRoot root, KindOfVerb kov) {
        var nouns = trilateralUnaugmentedNouns.getNonStandardInstrumentals(root);
        if(nouns == null || nouns.isEmpty()) {
            return Collections.emptyList();
        }
        var derivedNounConjugations = new ArrayList<DerivedNounConjugation>();
        var keys = nonStandardInstrumentalConjugator.getAppliedFormulaList(root);
        for(var key: keys) {
            var derivedNounConjugation = new DerivedNounConjugation();
            derivedNounConjugation.setKey(key);
            this.genericNounSuffixContainer.selectInDefiniteMode();
            var conjugatedNouns = nonStandardInstrumentalConjugator.createNounList(root, key);
            var indefiniteResult = instrumentalModifier.build(root, kov, conjugatedNouns, key);
            derivedNounConjugation.setIndefiniteNouns(indefiniteResult.getFinalResult().stream().map(wp -> wp.toString()).toList());

            this.genericNounSuffixContainer.selectAnnexedMode();
            conjugatedNouns = nonStandardInstrumentalConjugator.createNounList(root, key);
            var annexedResult = instrumentalModifier.build(root, kov, conjugatedNouns, key);
            derivedNounConjugation.setAnnexedNouns(annexedResult.getFinalResult().stream().map(wp -> wp.toString()).toList());

            this.genericNounSuffixContainer.selectDefiniteMode();
            conjugatedNouns = nonStandardInstrumentalConjugator.createNounList(root, key);
            var definiteResult = instrumentalModifier.build(root, kov, conjugatedNouns, key);
            derivedNounConjugation.setDefiniteNouns(definiteResult.getFinalResult().stream().map(wp -> wp.toString()).toList());
            derivedNounConjugations.add(derivedNounConjugation);
        }
        return derivedNounConjugations;
    }
}
