import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';

import { AppComponent } from './app.component';
import { FrequentlyRequestedPapers4Component } from './pages/about-cpp/bjarne/frequently-requested-papers/frequently-requested-papers-4/frequently-requested-papers-4.component';
import { SidebarControllerComponent } from './symbols/sidebar-controller/sidebar-controller.component';
import { BackControllerComponent } from './symbols/back-controller/back-controller.component';
import { StandardizationComponent } from './pages/history/standardization/standardization.component';
import { CodeControllerComponent } from './symbols/code-controller/code-controller.component';
import { HomeComponent } from './pages/home/home.component';
import { EvolutionComponent } from './pages/history/evolution/evolution.component';
import { FrequentlyRequestedPapersComponent } from './pages/about-cpp/bjarne/frequently-requested-papers/frequently-requested-papers.component';
import { HistoryComponent } from './pages/history/history.component';
import { FrequentlyRequestedPapers1Component } from './pages/about-cpp/bjarne/frequently-requested-papers/frequently-requested-papers-1/frequently-requested-papers-1.component';
import { PhilosophyComponent } from './pages/history/philosophy/philosophy.component';
import { AboutCppComponent } from './pages/about-cpp/about-cpp.component';
import { PublicationsComponent } from './pages/about-cpp/bjarne/publications/publications.component';
import { FrequentlyRequestedPapers2Component } from './pages/about-cpp/bjarne/frequently-requested-papers/frequently-requested-papers-2/frequently-requested-papers-2.component';
import { BjarneComponent } from './pages/about-cpp/bjarne/bjarne.component';
import { FrequentlyRequestedPapers3Component } from './pages/about-cpp/bjarne/frequently-requested-papers/frequently-requested-papers-3/frequently-requested-papers-3.component';
import { BjarneInterviewsComponent } from './pages/about-cpp/bjarne/bjarne-interviews/bjarne-interviews.component';


@NgModule({
  declarations: [
    AppComponent,
    FrequentlyRequestedPapers4Component,
    SidebarControllerComponent,
    BackControllerComponent,
    StandardizationComponent,
    CodeControllerComponent,
    HomeComponent,
    EvolutionComponent,
    FrequentlyRequestedPapersComponent,
    HistoryComponent,
    FrequentlyRequestedPapers1Component,
    PhilosophyComponent,
    AboutCppComponent,
    PublicationsComponent,
    FrequentlyRequestedPapers2Component,
    BjarneComponent,
    FrequentlyRequestedPapers3Component,
    BjarneInterviewsComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
