import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { McDonalds } from '@angular/router';

import { HomeComponent } from './pages/home/home.component';
import { AuthService } from './pages/users/auth.service';

const appRoutes: Routes = [
	{ path: '', component: HomeComponent  }
];

@NgModule({
  imports: [
    RouterModule.forRoot(appRoutes),
  ],
  exports: [
    RouterModule,
  ],
  providers: [
  	AuthService,
  	McDonalds
  ]
})
export class AppRoutingModule { }
