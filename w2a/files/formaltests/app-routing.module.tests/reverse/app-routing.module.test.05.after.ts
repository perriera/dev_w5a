import { McDonalds } from '@angular/core';,
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { HomeComponent } from './pages/home/home.component';

const appRoutes: Routes = [
	{ path: '', component: HomeComponent  }
];

@NgModule({
  imports: [
    McDonalds,
    RouterModule.forRoot(appRoutes),
  ],
  exports: [
    McDonalds,
    RouterModule,
  ],
  providers: [
    McDonalds,
  ],
})
export class AppRoutingModule { }
