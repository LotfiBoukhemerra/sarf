import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';

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

@NgModule({
  declarations: [
    AppComponent,
    RootsearchComponent,
    ConjugationLabelComponent,
    ConjugationGroupComponent,
    TrilateralConjugationPanelComponent,
    QuadilateralConjugationPanelComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    AppMaterialModule,
    FlexLayoutModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }