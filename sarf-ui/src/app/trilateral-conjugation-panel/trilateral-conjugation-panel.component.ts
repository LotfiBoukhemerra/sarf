import { Component, OnInit, Input } from '@angular/core';
import { TrilateralConjugationGroup } from '../models/trilateral-conjugation-group';
import { ConjugationGroup } from '../models/conjugationgroup';
import { ConjugationClass } from '../models/conjugationclass';
import { SarfService } from '../services/sarf-service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-trilateral-conjugation-panel',
  templateUrl: './trilateral-conjugation-panel.component.html',
  styleUrls: ['./trilateral-conjugation-panel.component.css']
})
export class TrilateralConjugationPanelComponent implements OnInit {
  public conjugationGroup: TrilateralConjugationGroup;
  public alternatives: Array<any>;

  constructor(private sarfService: SarfService,
    private route: ActivatedRoute,
    private router: Router) {}

  ngOnInit(): void {
    const currentRoot = this.resetSearch();

    this.sarfService.findTrilateralConjugations(currentRoot).subscribe(rootResult => {
      console.log(rootResult);
      this.processTriResult(rootResult);
    }, n => console.log(n));
  }

  private resetSearch() {
    const currentRoot = this.route.snapshot.paramMap.get('root');
    this.conjugationGroup = null;
    this.alternatives = null;
    return currentRoot;
  }

  public hasSingleResult(): boolean {
    return this.conjugationGroup != null;
  }

  public hasAlternatives(): boolean {
    return this.alternatives != null && this.alternatives.length > 1;
  }

  public navigatTo(path: string) {
    this.router.navigateByUrl('/', {skipLocationChange: true})
    .then(() => this.router.navigate([path]));
  }

  private processTriResult(rootResult: any) {

    if (rootResult == null || rootResult.length == 0) {
      return;
    }

    if (rootResult.length > 1) {
      this.alternatives = rootResult.map(root => ({
        "path": `/tri/${root.root}`,
        "display": root.root
      }));
      return;
    }

    const result = rootResult[0];
    const unaugmented = this.buildUnaugmentedConjugationClasses(result.unaugmentedRoots);
    const augmentedByOne = this.buildAugmentedByOneConjugationClasses(result.conjugationResults);
    const augmentedByTwo = this.buildAugmentedByTwoConjugationClasses(result.conjugationResults);
    const augmentedByThreeOrMore = this.buildAugmentedByThreeConjugationClasses(result.conjugationResults);

    this.conjugationGroup = new TrilateralConjugationGroup(unaugmented, augmentedByOne, augmentedByTwo, augmentedByThreeOrMore);
  }

  private buildUnaugmentedConjugationClasses(unaugmentedRoots): ConjugationGroup {
    if (!unaugmentedRoots) {
      return;
    }

    const first = this.getTriUnaugmentedRootText(unaugmentedRoots, "First");
    const second = this.getTriUnaugmentedRootText(unaugmentedRoots, "Second");
    const third = this.getTriUnaugmentedRootText(unaugmentedRoots, "Third");
    const forth = this.getTriUnaugmentedRootText(unaugmentedRoots, "Forth");
    const fifth = this.getTriUnaugmentedRootText(unaugmentedRoots, "Fifth");
    const sixth = this.getTriUnaugmentedRootText(unaugmentedRoots, "Sixth");
    const conjugationClasses: ConjugationClass[] =
      [
        new ConjugationClass(1, ConjugationClass.TriFirstConjugationClass, first)
        , new ConjugationClass(2, ConjugationClass.TriSecondConjugationClass, second)
        , new ConjugationClass(3, ConjugationClass.TriThirdConjugationClass, third)
        , new ConjugationClass(4, ConjugationClass.TriForthConjugationClass, forth)
        , new ConjugationClass(5, ConjugationClass.TriFifthConjugationClass, fifth)
        , new ConjugationClass(6, ConjugationClass.TriSixthConjugationClass, sixth)
      ];
    return new ConjugationGroup(ConjugationGroup.TriUnaugmentedLabel, conjugationClasses);
  }

  private getTriUnaugmentedRootText(unaugmentedRoots: any, conjugationclass: string): string {
    return unaugmentedRoots.filter(r => r.root.conjugation === conjugationclass)
      .map(r => r.display)
      .join('');
  }

  private buildAugmentedByOneConjugationClasses(conjugationResults): ConjugationGroup {
    var formula1 = this.getTriAugmentedRootText(conjugationResults, 1);
    var formula2 = this.getTriAugmentedRootText(conjugationResults, 2);
    var formula3 = this.getTriAugmentedRootText(conjugationResults, 3);
    const conjugationClasses: ConjugationClass[] = [
      new ConjugationClass(1, ConjugationClass.TriAugmentedByOneFirstConjugationClass, formula1),
      new ConjugationClass(2, ConjugationClass.TriAugmentedByOneSecondConjugationClass, formula2),
      new ConjugationClass(3, ConjugationClass.TriAugmentedByOneThirdConjugationClass, formula3)
    ];
    return new ConjugationGroup(ConjugationGroup.TriAugmentedByOneLabel, conjugationClasses);
  }

  private buildAugmentedByTwoConjugationClasses(conjugationResults): ConjugationGroup {
    var formula4 = this.getTriAugmentedRootText(conjugationResults, 4);
    var formula5 = this.getTriAugmentedRootText(conjugationResults, 5);
    var formula6 = this.getTriAugmentedRootText(conjugationResults, 6);
    var formula7 = this.getTriAugmentedRootText(conjugationResults, 7);
    var formula8 = this.getTriAugmentedRootText(conjugationResults, 8);

    const conjugationClasses: ConjugationClass[] = [
      new ConjugationClass(1, ConjugationClass.TriAugmentedByTwoFirstConjugationClass, formula4),
      new ConjugationClass(2, ConjugationClass.TriAugmentedByTwoSecondConjugationClass, formula5),
      new ConjugationClass(3, ConjugationClass.TriAugmentedByTwoThirdConjugationClass, formula6),
      new ConjugationClass(4, ConjugationClass.TriAugmentedByTwoForthConjugationClass, formula7),
      new ConjugationClass(5, ConjugationClass.TriAugmentedByTwoFifthConjugationClass, formula8)];
    return new ConjugationGroup(ConjugationGroup.TriAugmentedByTwoLabel, conjugationClasses);
  }

  private buildAugmentedByThreeConjugationClasses(conjugationResults): ConjugationGroup {
    var formula9 = this.getTriAugmentedRootText(conjugationResults, 9);
    var formula10 = this.getTriAugmentedRootText(conjugationResults, 10);
    var formula11 = this.getTriAugmentedRootText(conjugationResults, 11);
    var formula12 = this.getTriAugmentedRootText(conjugationResults, 12);

    const conjugationClasses: ConjugationClass[] = [
      new ConjugationClass(1, ConjugationClass.TriAugmentedByThreeFirstConjugationClass, formula9),
      new ConjugationClass(2, ConjugationClass.TriAugmentedByThreeSecConjugationClass, formula10),
      new ConjugationClass(3, ConjugationClass.TriAugmentedByThreeThirdConjugationClass, formula11),
      new ConjugationClass(4, ConjugationClass.TriAugmentedByThreeForthConjugationClass, formula12)];
    return new ConjugationGroup(ConjugationGroup.TriAugmentedByThreeLabel, conjugationClasses);
  }

  private getTriAugmentedRootText(conjugationResults: any, formulaNo: number) {
    return conjugationResults.filter(r => r.conjugationResult.formulaNo === formulaNo)
      .map(r => r.display)
      .join('');
  }
}
