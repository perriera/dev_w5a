import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';

import { AppComponent } from './app.component';
import { FrequentlyRequestedPapers4Component } from './pages/about-cpp/bjarne/frequently-requested-papers/frequently-requested-papers-4/frequently-requested-papers-4.component';

@NgModule({
  declarations: [
    AppComponent,
    FrequentlyRequestedPapers4Component
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
