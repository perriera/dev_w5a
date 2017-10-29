import { McDonalds } from '@angular/core';,
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { HomeComponent } from './pages/home/home.component';

const appRoutes: Routes = [
	{ path: '', component: HomeComponent  }
];

@NgModule({
  imports: [
    RouterModule.forRoot(appRoutes),
  ],
  exports: [
    McDonalds,
    RouterModule,
  ],
})
export class AppRoutingModule { }
