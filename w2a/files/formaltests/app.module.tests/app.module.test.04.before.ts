import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';

import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';
import { EvenComponent } from './symbols/even/even.component';
import { GameControlComponent } from './symbols/game-control/game-control.component';
import { HomeComponent } from './pages/home/home.component';
import { OddComponent } from './symbols/odd/odd.component';
import { ServerComponent } from './symbols/server/server.component';

@NgModule({
  declarations: [
    AppComponent,
    EvenComponent,
    GameControlComponent,
    HomeComponent,
    OddComponent,
    ServerComponent,
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
