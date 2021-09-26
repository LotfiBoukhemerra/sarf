import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { Routes, RouterModule} from '@angular/router';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AppMaterialModule } from './material-module';
import { FlexLayoutModule } from '@angular/flex-layout';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import { RootsearchComponent } from './rootsearch/rootsearch.component';
import { ConjugationLabelComponent } from './conjugation-label/conjugation-label.component';
import { ConjugationGroupComponent } from './conjugation-group/conjugation-group.component';
import { TrilateralConjugationPanelComponent } from './trilateral-conjugation-panel/trilateral-conjugation-panel.component';
import { QuadilateralConjugationPanelComponent } from './quadilateral-conjugation-panel/quadilateral-conjugation-panel.component';
import { StartComponent } from './start/start.component';
import { NotFoundComponent } from './not-found/not-found.component';
import { InitialSarfComponent } from './initial-sarf/initial-sarf.component';
import { ActiveVerbsComponent } from './active-verbs/active-verbs.component';

const appRoutes: Routes = [
  {path: '', component: StartComponent},
  {path: 'tri/:root', component: TrilateralConjugationPanelComponent},
  {path: 'quad/:root', component: QuadilateralConjugationPanelComponent},
  {path: 'verb/tri/u/:verb/:class', component: InitialSarfComponent},
  {path: 'verb/tri/u/:verb/:class/active', component: ActiveVerbsComponent},
  {path: 'verb/tri/a/:verb/:formula', component: InitialSarfComponent},
  {path: 'verb/tri/a/:verb/:formula/active', component: ActiveVerbsComponent},
  {path: 'verb/quad/u/:verb', component: InitialSarfComponent},
  {path: 'verb/quad/a/:verb/:formula', component: InitialSarfComponent},
]

@NgModule({
  declarations: [
    AppComponent,
    RootsearchComponent,
    ConjugationLabelComponent,
    ConjugationGroupComponent,
    TrilateralConjugationPanelComponent,
    QuadilateralConjugationPanelComponent,
    StartComponent,
    NotFoundComponent,
    InitialSarfComponent,
    ActiveVerbsComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    AppMaterialModule,
    FlexLayoutModule,
    HttpClientModule,
    RouterModule.forRoot(appRoutes, {onSameUrlNavigation: 'reload'})
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
